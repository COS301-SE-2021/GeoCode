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
  toolbox: Blockly.utils.toolbox.ToolboxInfo = {
    kind: 'flyoutToolbox',
    contents: []
  };

  config: Blockly.BlocklyOptions = {
    readOnly: false,
    trashcan: true,
    move: {
      scrollbars: true,
      drag: true,
      wheel: true
    },
    toolbox: this.toolbox,
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
  }

  async ngAfterViewInit() {
    if (this.toolboxBlocks !== null) {
      await this.readInputToolbox();
    } else {
      await this.readDefaultToolbox();
    }
    this.workspace = Blockly.inject(this.blocklyDiv.nativeElement, this.config);

    setTimeout(() => this.forceResize(), 500);

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
    console.log(blocks);
    for (const block of blocks) {
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
    this.runProgram(code);
  }

  runProgram(code: string, inputFunction: (promptRequest: string) => string = prompt, outputFunction: (output: string) => boolean = confirm) {
    const interpreter = new Interpreter(code, (interpreter2, scope) => {
      const promptWrapper = (text) => {
        text = text ? text.toString() : '';
        const input = inputFunction(text);
        if (input === null) {
          throw new Error('User stopped execution');
        }
        return input;
      };
      interpreter2.setProperty(scope, 'prompt', interpreter2.createNativeFunction(promptWrapper));
      const alertWrapper = (text) => {
        text = text ? text.toString() : '';
        const continueExecution = outputFunction(text);
        if (!continueExecution) {
          throw new Error('User stopped execution');
        }
      };
      interpreter2.setProperty(scope, 'alert', interpreter2.createNativeFunction(alertWrapper));
    });

    let stepsAllowed = 10000;
    while (interpreter.step() && stepsAllowed) {
      stepsAllowed--;
    }
    if (!stepsAllowed) {
      throw EvalError('Infinite loop.');
    }
  }

  generateCode() {
    return BlocklyJsGenerator.workspaceToCode(this.workspace);
  }

  setToolboxVisibility(visibility: boolean) {
    this.workspace.getToolbox().setVisible(visibility);
    this.forceResize();
  }

  async readInputToolbox() {
    for (const [blockName, maxInstances] of Object.entries(this.toolboxBlocks)) {
      // @ts-ignore
      this.toolbox.contents.push({
        kind: 'block',
        type: blockName
      });
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

}






