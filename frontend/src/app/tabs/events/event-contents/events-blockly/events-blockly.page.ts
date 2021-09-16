import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Event, EventService, UserEventStatus} from '../../../../services/geocode-api';
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
  blocks: {[blockName: string]: number} = {};

  testCases: string[][] = null;

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

      for (const block of this.status.details.blocks.split('#')) {
        if (block !== '') {
          this.blocks[block] = 999;
        }
      }
    }
  }

  async submitOutput() {
    await this.blockly.saveProgramToStorage();
    if (this.testCases === null) {
      this.testCases = (await this.eventApi.getInputs({eventID: this.eventID}).toPromise()).inputs;
    }

    const code = this.blockly.generateCode();
    const caseOutputs: string[] = [];
    for (const testCase of this.testCases) {
      const inputs = [];
      for (const input of testCase) {
        inputs.push(input);
      }
      const outputs = [];

      this.blockly.runProgram(code, (promptRequest: string) => {
        return inputs.shift();
      }, (outputText: string) => {
        outputs.push(outputText);
        return true;
      });

      caseOutputs.push(outputs.join('\n'));
    }

    const response = await this.eventApi.checkOutput({eventID: this.eventID, outputs: caseOutputs}).toPromise();
    if (response.success) {
      alert(response.message);
    } else {
      alert('Your program output did not match the expected output.');
    }
  }

  toggleToolbox() {
    this.toolboxVisible = !this.toolboxVisible;
    this.blockly.setToolboxVisibility(this.toolboxVisible);
  }

  viewDescription() {
    alert(this.event.properties.problemDescription);
  }
}
