import {Component, OnInit, ViewChild} from '@angular/core';
import {BlocklyComponent} from '../../../../components/blockly/blockly.component';
import {DetailedTestCase} from './helpers';
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
  currentlyRecordingTestCase: DetailedTestCase = null;

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
    const testCases: TestCase[] = [];
    for (const tc of this.testCases) {
      testCases.push(DetailedTestCase.toSimpleTestCase(tc));
    }
    const blocks: Block[] = [];
    for (const [block, instances] of Object.entries(this.blockly.getProgramBlocks())) {
      blocks.push({type: block, maxInstances: -1}); // Allow unlimited instances for now. TODO see how well we can support limits
    }
    await this.modalCtrl.dismiss({ blocks, testCases });
  }

  async dismiss() {
    await this.modalCtrl.dismiss();
  }

  async createTestCase() {
    this.currentlyRecordingTestCase = new DetailedTestCase();
    await this.blockly.saveProgramToStorage();

    const code = this.blockly.generateCode();
    this.blockly.runProgram(code);

    this.testCases.push(this.currentlyRecordingTestCase);
    this.currentlyRecordingTestCase = null;
    console.log(this.testCases);

    await this.storage.set(this.testCasesKey, this.testCases);
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

  blocklyInput = (promptRequest: string) => {
    const inputText = prompt(promptRequest);
    if (this.currentlyRecordingTestCase) {
      this.currentlyRecordingTestCase.addOutput(promptRequest);
      this.currentlyRecordingTestCase.addInput(inputText);
    }
    return inputText;
  };

  blocklyOutput = (outputText: string) => {
    alert(outputText);
    if (this.currentlyRecordingTestCase) {
      this.currentlyRecordingTestCase.addOutput(outputText);
    }
  };

}
