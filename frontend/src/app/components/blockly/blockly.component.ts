import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';
import * as Blockly from 'blockly';
import * as BlocklyJsGenerator from 'blockly/javascript';

@Component({
  selector: 'app-blockly',
  templateUrl: './blockly.component.html',
  styleUrls: ['./blockly.component.scss'],
})
export class BlocklyComponent implements AfterViewInit {

  @ViewChild('blocklyDiv', {static:false}) blocklyDiv: ElementRef<HTMLDivElement>;
  private workspace: Blockly.WorkspaceSvg = null;
  private toolbox: Blockly.utils.toolbox.ToolboxInfo = {
    kind: 'flyoutToolbox',
    contents: []
  };

  private config: Blockly.BlocklyOptions = {
    readOnly: false,
    trashcan: true,
    move: {
      scrollbars: true,
      drag: true,
      wheel: true
    },
    toolbox: this.toolbox
  };

  constructor() {}

  ngAfterViewInit() {
    this.workspace = Blockly.inject(this.blocklyDiv.nativeElement, this.config);
    this.addToToolbox('controls_if');
    this.addToToolbox('controls_whileUntil');
  }

  addToToolbox(blockName: string) {
    // @ts-ignore use pre-made blocks, no need to define them ourselves
    this.toolbox.contents.push({
      kind: 'block',
      type: blockName
    });
    this.workspace.updateToolbox(this.toolbox);
  }

  generateCode(): string {
    return BlocklyJsGenerator.workspaceToCode(this.workspace);
  }

}






