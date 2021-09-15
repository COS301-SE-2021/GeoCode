import {Component, OnInit, ViewChild} from '@angular/core';
import {BlocklyComponent} from '../../../../components/blockly/blockly.component';
import {DetailedTestCase, TestCaseEvent} from './helpers';
import {AlertController, ModalController} from '@ionic/angular';
import {Block, TestCase} from '../../../../services/geocode-api';
import {Storage} from '@ionic/storage-angular';

@Component({
  selector: 'app-events-create-blockly',
  templateUrl: './events-create-blockly.component.html',
  styleUrls: ['./events-create-blockly.component.scss'],
})
export class EventsCreateBlocklyComponent implements OnInit {

  @ViewChild('blockly', {static: false}) blockly: BlocklyComponent;

  currentView: 'blockly'|'testCases' = 'blockly';
  toolboxVisible = true;

  testCases: DetailedTestCase[] = [];

  readonly testCasesKey = 'createdTestCases';
  readonly programKey = 'createdProgram';

  constructor(
    private modalCtrl: ModalController,
    private storage: Storage,
    private alertCtrl: AlertController
  ) {}

  async ngOnInit() {
    const saved = await this.storage.get(this.testCasesKey);
    if (saved) {
      this.testCases = saved;
    }
  }

  async save() {
    const verified = await this.verifyTestCases();
    if (verified) {
      const testCases: TestCase[] = [];
      for (const tc of this.testCases) {
        testCases.push(DetailedTestCase.toSimpleTestCase(tc));
      }
      const blocks: Block[] = [];
      for (const [block, instances] of Object.entries(this.blockly.getProgramBlocks())) {
        blocks.push({type: block, maxInstances: -1}); // Allow unlimited instances for now. TODO see how well we can support limits
      }
      await this.modalCtrl.dismiss({ blocks, testCases });

    } else {
      await this.alert('Some test cases cannot be reproduced with the current program. Please delete or remake the invalid cases.');
      this.currentView = 'testCases';
    }
  }

  async dismiss() {
    await this.modalCtrl.dismiss();
  }

  async createTestCase() {
    const testCase = new DetailedTestCase();
    await this.blockly.saveProgramToStorage();

    const code = this.blockly.generateCode();

    await this.blockly.runProgram(code, async (promptRequest: string) => {
      const inputText = await this.blockly.input(promptRequest);
      testCase.addPrompt(promptRequest);
      testCase.addInput(inputText);
      return inputText;
    }, async (outputText: string) => {
      testCase.addOutput(outputText);
      return await this.blockly.output(outputText);
    });

    this.testCases.push(testCase);
    console.log(this.testCases);

    await this.storage.set(this.testCasesKey, this.testCases);
  }

  async alert(message: string) {
    const alert = await this.alertCtrl.create({
      header: 'Alert',
      message,
      buttons: ['OK']
    });
    await alert.present();
  }

  async clearAllTestCases() {
    const alert = await this.alertCtrl.create({
      header: 'Clear All Test Cases',
      subHeader: 'Are you sure you want to clear all test cases?',
      message: 'This action is irreversible!',
      buttons: [
        { text: 'No, Cancel', role: 'cancel' },
        { text: 'Yes, Clear', handler: () => {
          this.testCases = [];
          this.storage.remove(this.testCasesKey).then().catch();
        } }
      ]
    });
    await alert.present();
  }

  toggleToolbox() {
    this.toolboxVisible = !this.toolboxVisible;
    this.blockly.setToolboxVisibility(this.toolboxVisible);
  }

  blocklyWorkspaceChanged = (event: any) => {
    console.log(event);
  };

  async deleteTestCase(testCase: DetailedTestCase) {
    this.testCases = this.testCases.filter((v) => v !== testCase);
    await this.storage.set(this.testCasesKey, this.testCases);
  }

  async verifyTestCases() {
    await this.storage.set(this.testCasesKey, this.testCases);

    let testCasesVerified = true;
    for (const testCase of this.testCases) {
      try {
        const events: TestCaseEvent[] = [];
        for (const event of testCase.events) {
          event.error = null;
          events.push(event);
        }

        const code = this.blockly.generateCode(true);

        await this.blockly.runProgram(code, async (promptRequest: string) => {
          const expectedPrompt = events.shift();

          if (expectedPrompt.type !== 'prompt') {
            expectedPrompt.error = {};
            throw new Error('Program unexpectedly asked for input');
          } else if (expectedPrompt.text !== promptRequest) {

            expectedPrompt.error = {text: promptRequest};
            throw new Error('Program sent invalid prompt');
          }

          const testInput = events.shift();

          if (testInput.type !== 'input') {
            testInput.error = {};
            throw new Error('Program unexpectedly asked for input');
          }
          return testInput.text;
        }, async (outputText: string) => {
          const expectedOutput = events.shift();

          if (expectedOutput.type !== 'output') {
            expectedOutput.error = {};
            throw new Error('Program unexpectedly sent output');

          } else if (expectedOutput.text !== outputText) {
            expectedOutput.error = {text: outputText};
            throw new Error('Program sent invalid output');
          }

          return true;
        });

        if (events.length > 0) {
          events[0].error = {};
          throw new Error('Program did not use all test case events');
        }

      } catch (e) {
        testCasesVerified = false;
      }
    }
    return testCasesVerified;
  }

}
