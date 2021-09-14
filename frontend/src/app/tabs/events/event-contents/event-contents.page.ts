import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {AlertController, NavController, ToastController} from '@ionic/angular';
import {
  Event,
  EventService, GeoCode,
  GeoCodeService,
  GetCurrentEventStatusRequest, UserEventStatus,
} from '../../../services/geocode-api';
import {ActivatedRoute, Router} from '@angular/router';
import {MapAndInfoComponent} from '../../../components/map-and-info/map-and-info.component';
import {CurrentUserDetails} from '../../../services/CurrentUserDetails';


@Component({
  selector: 'app-event-contents',
  templateUrl: './event-contents.page.html',
  styleUrls: ['./event-contents.page.scss'],
})
export class EventContentsPage implements AfterViewInit {

  @ViewChild('mapAndInfo', {static: false}) mapAndInfo: MapAndInfoComponent;

  event: Event = null;
  eventID: string = null;
  geocode: GeoCode = null;
  status: UserEventStatus = null;

  constructor(
    route: ActivatedRoute,
    router: Router,
    private toastController: ToastController,
    private navCtrl: NavController,
    private geocodeApi: GeoCodeService,
    private eventApi: EventService,
    private alertController: AlertController,
    private currentUser: CurrentUserDetails
  ) {
    //Get passed in param from routing
    const state = router.getCurrentNavigation().extras.state;
    if (state) {
      //Set the event to the passed in geocode
      this.event = state.event;
      this.eventID = this.event.id;
    } else {
      this.event = null;
      this.eventID = route.snapshot.paramMap.get('eventID');
    }
  }

  async ngAfterViewInit() {
    await this.mapAndInfo.load();

    const levelReq: GetCurrentEventStatusRequest = {
      eventID: this.eventID,
      userID: this.currentUser.getID()
    };

    const response = await this.eventApi.getCurrentEventStatus(levelReq).toPromise();
    console.log(response);
    if (response.success) {
      this.status = response.status;
      if (response.targetGeocode === null) {
        if (response.status.details.hasOwnProperty('blocks')) {
          // eslint-disable-next-line max-len
          await this.presentAlert('You have found all the GeoCodes! Now you need to complete the Blockly coding challenge with the blocks you unlocked along the way.');
        } else {
          await this.presentAlert('Congratulations, you have completed the event!');
          await this.navCtrl.navigateBack('/events');
        }
      } else {
        this.geocode = response.targetGeocode;
        this.mapAndInfo.getMap().setOptions({
          center: { lat: this.geocode.location.latitude, lng: this.geocode.location.longitude },
          zoom: 15,
        });
      }
    } else {
      this.navCtrl.back();
      await this.presentToast();
    }

    if (this.event === null) {
      this.event = (await this.eventApi.getEvent({eventID: this.eventID}).toPromise()).foundEvent;
    };
  }

  async presentToast(){
    const toast =  await this.toastController.create({
      message: 'Error loading Event GeoCode not found',
      duration: 2000
    });
    await toast.present();
  }

  async presentAlert(text: string) {
    const alert = await this.alertController.create({
      message: text,
      buttons: ['OK']
    });
    await alert.present();
    await alert.onDidDismiss();
  }

  async openLeaderBoard() {
    const event = this.event;
    await this.navCtrl.navigateForward('/events/'+this.eventID+'/leaderboard', {state: {event}});
  }

  async openBlockly() {
    await this.navCtrl.navigateForward('/events/'+this.eventID+'/blockly', {
      state: {
        event: this.event,
        status: this.status
      }
    });
  }


}
