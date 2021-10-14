import {AfterViewInit, Component, ElementRef, Input, OnDestroy, ViewChild} from '@angular/core';
import * as Blockly from 'blockly';
import * as BlocklyJsGenerator from 'blockly/javascript';
import Interpreter from 'js-interpreter';
import {Storage} from '@ionic/storage-angular';
import {AlertController} from '@ionic/angular';
import DarkTheme from '@blockly/theme-dark';
import {Mediator} from '../../services/Mediator';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-blockly',
  templateUrl: './blockly.component.html',
  styleUrls: ['./blockly.component.scss'],
})
export class BlocklyComponent implements AfterViewInit, OnDestroy {

  @Input() toolboxBlocks: {[blockName: string]: number} = null;
  @Input() workspaceChangedFunction: (event: any) => void = null;
  @Input() savedProgramID: string = null;

  @ViewChild('blocklyDiv', {static:false}) blocklyDiv: ElementRef<HTMLDivElement>;

  workspace: Blockly.WorkspaceSvg = null;

  config: Blockly.BlocklyOptions = {
    readOnly: false,
    trashcan: true,
    move: {
      scrollbars: true,
      drag: true,
      wheel: true
    },
    theme: this.getTheme(window.matchMedia('(prefers-color-scheme: dark)').matches)
  };

  themeSubscription: Subscription;

  constructor(
    private storage: Storage,
    private alertCtrl: AlertController,
    mediator: Mediator
  ) {
    this.themeSubscription = mediator.themeChanged.onReceive((isDark) => {
      this.workspace.setTheme(this.getTheme(isDark));
    });
    // @ts-ignore
    Blockly.prompt = async (message, defaultValue, callback) => {
      const text = await this.input(message, [
        { text: 'Cancel', role: 'cancel' },
        { text: 'Enter', role: 'ok' }
      ], defaultValue);
      if (text !== null) {
        callback(text);
      }
    };
    // @ts-ignore
    Blockly.alert = async (message, callback) => {
      const response = await this.output(message, [
        { text: 'OK', role: 'ok' }
      ]);
      if (response) {
        callback();
      }
    };
  }

  async sleep(milliseconds: number) {
    return new Promise<void>(resolve => setTimeout(resolve, milliseconds));
  }

  async ngAfterViewInit() {
    while (document.getElementById('blocklyDiv') === null) {
      await this.sleep(100);
    }

    if (this.toolboxBlocks !== null) {
      this.readInputToolbox();
    } else {
      await this.readDefaultToolbox();
    }
    this.workspace = Blockly.inject(this.blocklyDiv.nativeElement, this.config);

    setTimeout(this.forceResize, 500);

    // May need to call this inside the timeout
    if (this.savedProgramID !== null) {
      await this.getProgramFromStorage();
    }

    if (this.workspaceChangedFunction !== null) {
      this.workspace.addChangeListener(this.workspaceChangedFunction);
    }
  }

  ngOnDestroy() {
    this.themeSubscription.unsubscribe();
    if (this.workspaceChangedFunction !== null) {
      this.workspace.removeChangeListener(this.workspaceChangedFunction);
    }
  }

  setWorkspace(blockXML: string) {
    const dom = Blockly.Xml.textToDom(blockXML);
    Blockly.Xml.domToWorkspace(dom, this.workspace);
  }

  getWorkspace(): string {
    const dom = Blockly.Xml.workspaceToDom(this.workspace);
    return Blockly.Xml.domToText(dom);
  }

  getProgramBlocks(): {[key: string]: number} {
    const output = {};
    const blocks = this.workspace.getAllBlocks(true);
    for (const block of blocks) {
      // eslint-disable-next-line no-underscore-dangle
      if (block.styleName_ === 'procedure_blocks' || block.styleName_ === 'variable_blocks') {
        continue;
      }
      if (!output.hasOwnProperty(block.type)) {
        output[block.type] = 0;
      }
      output[block.type]++;
    }
    return output;
  }

  async getProgramFromStorage() {
    console.log('checking for saved program');
    const blockXML = await this.storage.get(this.savedProgramID);
    if (blockXML) {
      console.log('setting saved program');
      this.setWorkspace(blockXML);
    } else {
      console.log('no saved program found');
    }
  }

  async saveProgramToStorage() {
    console.log('saving program');
    const blockXML = this.getWorkspace();
    await this.storage.set(this.savedProgramID, blockXML);
  }

  async clearAllBlocksPrompt() {
    const alert = await this.alertCtrl.create({
      header: 'Clear All Blocks',
      subHeader: 'Are you sure you want to clear all blocks?',
      message: 'This action is irreversible!',
      buttons: [
        { text: 'No, Cancel', role: 'cancel' },
        { text: 'Yes, Clear', handler: () => this.clearAllBlocks() }
      ]
    });
    await alert.present();
  }

  async clearAllBlocks() {
    console.log('Clearing blocks');
    this.workspace.clear();
    if (this.savedProgramID !== null) {
      await this.saveProgramToStorage();
    }
  }

  async generateAndRunProgram() {
    const code = this.generateCode();
    console.log(code);
    if (this.savedProgramID !== null) {
      await this.saveProgramToStorage();
    }
    await this.runProgram(code);
  }

  async runProgram(
    code: string,
    inputFunction: (promptRequest: string) => Promise<string> = this.programInput,
    outputFunction: (output: string) => Promise<boolean> = this.programOutput
  ) {
    return new Promise<void> ((resolve, reject) => {

      const run = (interpreter2) => {
        try {
          if (!interpreter2.run()) {
            // if run returns false, the program has ended
            resolve();
          } // else the program will continue running
        } catch (e) {
          reject(e);
        }
      };

      const interpreter = new Interpreter('String = String2;\n'+code, (interpreter2, scope) => {
        interpreter2.setProperty(scope, 'prompt', interpreter2.createAsyncFunction(async (text, callback) => {
          const input = await inputFunction(text);
          if (input !== null) {
            callback(input);
            run(interpreter2);
          } else {
            reject('User stopped execution');
          }
        }));
        interpreter2.setProperty(scope, 'alert', interpreter2.createAsyncFunction(async (text, callback) => {
          const continueExecution = await outputFunction(text);
          if (continueExecution) {
            callback();
            run(interpreter2);
          } else {
            reject('User stopped execution');
          }
        }));
        interpreter2.setProperty(scope, 'Number', interpreter2.createNativeFunction((text) => Number(text)));
        interpreter2.setProperty(scope, 'String2', interpreter2.createNativeFunction((text) => String(text)));
      });

      run(interpreter);
    });
  }

  generateCode(preventInfiniteLoops = false) {
    if (preventInfiniteLoops) {
      // @ts-ignore
      BlocklyJsGenerator.INFINITE_LOOP_TRAP = 'if (--window.infiniteLoopTrap == 0) throw "Infinite loop";\n';
      return 'window.infiniteLoopTrap = 1000;\n'+BlocklyJsGenerator.workspaceToCode(this.workspace);
    } else {
      // @ts-ignore
      BlocklyJsGenerator.INFINITE_LOOP_TRAP = '';
      return BlocklyJsGenerator.workspaceToCode(this.workspace);
    }
  }

  setToolboxVisibility(visibility: boolean) {
    this.workspace.getToolbox().setVisible(visibility);
    this.forceResize();
  }

  readInputToolbox() {
    const unlocked = {
      kind: 'category',
      id: 'catUnlocked',
      colour: '160',
      name: 'Unlocked Blocks',
      contents: []
    };
    const locked = {
      kind: 'category',
      id: 'catUnlocked',
      colour: '160',
      name: 'Locked Blocks',
      contents: []
    };
    const separator = {
      kind: 'sep'
    };
    const variables = {
      kind: 'category',
      id: 'catVariables',
      colour: '330',
      custom: 'VARIABLE',
      name: 'Variables'
    };
    const functions = {
      kind: 'category',
      id: 'catFunctions',
      colour: '290',
      custom: 'PROCEDURE',
      name: 'Functions'
    };
    // @ts-ignore
    this.config.toolbox = { contents: [unlocked, separator, variables, functions] };
    for (const [blockName, maxInstances] of Object.entries(this.toolboxBlocks)) {
      if (maxInstances > 0) {
        // @ts-ignore
        unlocked.contents.push({
          kind: 'block',
          type: blockName
        });
      } else {
        // push locked
        locked.contents.push({
          kind: 'block',
          type: blockName
        });
      }
    }
  }

  async readDefaultToolbox() {
    return new Promise<void>((resolve, reject) => {
      const req = new XMLHttpRequest();
      req.open('GET', '/assets/blockly-default-blocks.xml', false);
      req.onreadystatechange = () => {
        if(req.readyState === 4){
          if(req.status === 200 || req.status === 0) {
            this.config.toolbox = req.responseText;
            resolve();
          } else {
            reject();
          }
        }
      };
      req.send(null); // return control
    });
  }

  public programOutput = async (output: string): Promise<boolean> => await this.output(output, [
    { text: 'Stop Running', role: 'cancel' },
    { text: 'Continue', role: 'ok' }
  ]);

  public programInput = async (promptRequest: string): Promise<string> => await this.input(promptRequest, [
    { text: 'Stop Running', role: 'cancel' },
    { text: 'Enter', role: 'ok' }
  ]);

  private forceResize() {
    window.dispatchEvent(new Event('resize'));
  }

  private getTheme(isDark: boolean): Blockly.Theme {
    if (isDark) {
      return DarkTheme;
    } else {
      return null;
    }
  }

  private input = async (message: string, buttons: any[], value: any = ''): Promise<string> => {
    const alert = await this.alertCtrl.create({
      message,
      backdropDismiss: false,
      inputs: [
        { name: 'input', type: 'text', value }
      ],
      buttons,
    });
    await alert.present();
    const {data, role} = await alert.onDidDismiss();
    if (role === 'ok') {
      return data.values.input;
    } else {
      return null;
    }
  };

  private output = async (message: string, buttons: any[]): Promise<boolean> => {
    const alert = await this.alertCtrl.create({
      message,
      backdropDismiss: false,
      buttons
    });
    await alert.present();
    const {role} = await alert.onDidDismiss();
    return role === 'ok';
  };


}






