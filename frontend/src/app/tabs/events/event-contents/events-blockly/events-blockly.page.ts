import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Event, EventService, TestCase, UserEventStatus} from '../../../../services/geocode-api';
import {CurrentUserDetails} from '../../../../services/CurrentUserDetails';
import {zip} from 'rxjs';
import {BlocklyComponent} from '../../../../components/blockly/blockly.component';

@Component({
  selector: 'app-events-blockly',
  templateUrl: './events-blockly.page.html',
  styleUrls: ['./events-blockly.page.scss'],
})
export class EventsBlocklyPage implements OnInit {

  @ViewChild('blockly', {static: false}) blockly: BlocklyComponent;

  event: Event = null;
  eventID: string = null;
  status: UserEventStatus = null;

  submitting = false;
  testCases: TestCase[] = null;
  inputs: string[] = null;
  outputs: string[] = null;

  toolboxVisible = true;

  constructor(
    route: ActivatedRoute,
    router: Router,
    private eventApi: EventService,
    private currentUser: CurrentUserDetails
  ) {
    const state = router.getCurrentNavigation().extras.state;
    if (state) {
      this.event = state.event;
      this.eventID = this.event.id;
      this.status = state.status;
    } else {
      this.eventID = route.snapshot.paramMap.get('eventID');
    }
  }

  async ngOnInit() {
    if (this.event == null) {
      const responses = await zip(
        this.eventApi.getEvent({eventID: this.eventID}),
        this.eventApi.getCurrentEventStatus({eventID: this.eventID, userID: this.currentUser.getID()})
      ).toPromise();
      this.event = responses[0].foundEvent;
      this.status = responses[1].status;
    }
  }

  getBlocks(): {[blockName: string]: number} {
    // TODO Set the available blocks in line with what is in the user's status details
    return {};
  }

  async submitOutput() {
    await this.blockly.saveProgramToStorage();
    this.submitting = true;
    if (this.testCases === null) {
      // @ts-ignore TODO check what the format of inputs is
      this.testCases = (await this.eventApi.getInputs({eventID: this.eventID}).toPromise()).inputs;
    }

    const code = this.blockly.generateCode();
    const caseOutputs: string[] = [];
    for (const testCase of this.testCases) {
      this.inputs = testCase.inputs;
      this.outputs = [];
      this.blockly.runProgram(code);
      caseOutputs.push(this.outputs.join('\n'));
    }
    this.submitting = false;

    const response = await this.eventApi.checkOutput({eventID: this.eventID, outputs: caseOutputs}).toPromise();
    if (response.success) {
      alert('Success');
    } else {
      alert('Your program output did not match the model.');
    }
  }

  toggleToolbox() {
    this.toolboxVisible = !this.toolboxVisible;
    this.blockly.setToolboxVisibility(this.toolboxVisible);
  }

  viewDescription() {
    alert(this.event.properties.problemDescription);
  }

  blocklyInput = (promptRequest: string) => {
    if (this.submitting) {
      this.outputs.push(promptRequest);
      return this.inputs.shift();
    } else {
      return prompt(promptRequest);
    }
  };

  blocklyOutput = (outputText: string) => {
    if (this.submitting) {
      this.outputs.push(outputText);
    } else {
      alert(outputText);
    }
  };
}
