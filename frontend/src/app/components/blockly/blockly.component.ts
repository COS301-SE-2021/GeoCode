import {AfterViewInit, Component, ElementRef, Input, ViewChild} from '@angular/core';
import {NgxBlocklyComponent} from 'ngx-blockly';
import * as Blockly from 'blockly';
import * as BlocklyJsGenerator from 'blockly/javascript';
import Interpreter from 'js-interpreter';
import {GeoCode} from '../../services/geocode-api';

@Component({
  selector: 'app-blockly',
  templateUrl: './blockly.component.html',
  styleUrls: ['./blockly.component.scss'],
})
export class BlocklyComponent implements AfterViewInit {

  @Input() toolboxBlocks: string[] = null;

  @ViewChild('blocklyDiv', {static:false}) blocklyDiv: ElementRef<HTMLDivElement>;
  //@ViewChild('ngxBlockly', {static:false}) ngxBlockly: NgxBlocklyComponent;

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
  };

  constructor() {}

  async ngAfterViewInit() {
    if (this.toolboxBlocks !== null) {
      await this.readInputToolbox();
    } else {
      await this.readDefaultToolbox();
    }
    this.workspace = Blockly.inject(this.blocklyDiv.nativeElement, this.config);
    console.log(this.workspace);
    console.log(this.workspace.isVisible());
    //this.workspace = this.ngxBlockly.workspace;
    //window.dispatchEvent(new Event('resize'));
    setTimeout(() => window.dispatchEvent(new Event('resize')), 500);
  }

  runProgram() {
    const code = this.generateCode();
    console.log(code);
    const interpreter = new Interpreter(code, (interpreter2, scope) => {
      const alertWrapper = (text) => {
        text = text ? text.toString() : '';
        alert(text);
      };
      interpreter2.setProperty(scope, 'alert', interpreter2.createNativeFunction(alertWrapper));
      const promptWrapper = (text) => {
        text = text ? text.toString() : '';
        prompt(text);
      };
      interpreter2.setProperty(scope, 'prompt', interpreter2.createNativeFunction(promptWrapper));
    });

    let stepsAllowed = 10000;
    while (interpreter.step() && stepsAllowed) {
      stepsAllowed--;
    }
    if (!stepsAllowed) {
      throw EvalError('Infinite loop.');
    }
  }

  generateCode(): string {
    return BlocklyJsGenerator.workspaceToCode(this.workspace);
  }

  async readInputToolbox() {
    for (const blockName of this.toolboxBlocks) {
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

}






