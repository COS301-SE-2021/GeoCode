import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
// import {NgxBlocklyComponent, NgxBlocklyConfig} from 'ngx-blockly';
import {Router} from '@angular/router';
import * as Blockly from 'blockly';


@Component({
  selector: 'app-blockly',
  templateUrl: './blockly.component.html',
  styleUrls: ['./blockly.component.scss'],
})
export class BlocklyComponent implements OnInit {
  @ViewChild('blockly',{static:false}) blocklyElement;
  public config = {
    toolbox: '<xml id="toolbox" style="display: block">' +

      '</xml>',
    scrollbars: true,
    trashcan: true
  };

  constructor(private router: Router) {

    // if (this.router.getCurrentNavigation()) {
    //   const state = this.router.getCurrentNavigation().extras.state;
    //   console.log(state);
    //   if (state) {
    //
    //   } else {

    //   }
    // }

    }
  ngOnInit(){
    const blocklyDiv = document.getElementById('blockly');

    Blockly.inject(blocklyDiv, {
      readOnly: false,
      media: 'media/',
      trashcan: true,
      move: {
        scrollbars: true,
        drag: true,
        wheel: true
      },
      toolbox: `
      <xml xmlns="https://developers.google.com/blockly/xml" id="toolbox-simple" style="display: none">
        <block type="controls_ifelse"></block>
        <block type="logic_compare"></block>
        <block type="logic_operation"></block>
        <block type="controls_repeat_ext">
            <value name="TIMES">
                <shadow type="math_number">
                    <field name="NUM">10</field>
                </shadow>
            </value>
        </block>
        <block type="logic_operation"></block>
        <block type="logic_negate"></block>
        <block type="logic_boolean"></block>
        <block type="logic_null" disabled="true"></block>
        <block type="logic_ternary"></block>
        <block type="text_charAt">
            <value name="VALUE">
                <block type="variables_get">
                    <field name="VAR">text</field>
                </block>
            </value>
        </block>
      </xml>
        `
    } as Blockly.BlocklyOptions);
  }

  }






