import {Injectable} from '@angular/core';
import QRCodeStyling from 'qr-code-styling';
import {Platform} from '@ionic/angular';
import {SocialSharing} from '@ionic-native/social-sharing/ngx';

@Injectable({ providedIn: 'root' })
export class QRGenerator {

  constructor(private platform: Platform, private sharing: SocialSharing) {}

  public async download(code: string, description?: string) {
    let filename = 'qrcode.png'
    if (description) {
      filename = description+'.png';
    }
    const imageData = this.getCanvas(code).toDataURL('image/png');

    if (this.platform.is('capacitor')) {
      await this.sharing.share(description, filename, imageData, undefined);

    } else {
      const link = document.createElement('a');
      link.download = filename;
      link.href = imageData;
      link.click();
    }
  }

  public getCanvas(code: string) {
    const input = this.generate(code)._canvas.getCanvas();
    const output = document.createElement('canvas');
    const ctx = output.getContext('2d');

    output.width = 550;
    output.height = 720;

    /* White background */
    ctx.fillStyle = "white";
    ctx.fillRect(0, 0, 550, 720);

    /* URL header */
    ctx.font = '900 55px Helvetica';
    ctx.fillStyle = 'red';
    ctx.textAlign = "center";
    ctx.fillText('geocodeapp.tech', 275, 65, 500);

    /* Border around QR code */
    ctx.fillStyle = "black";
    ctx.fillRect(25, 90, 500, 500);

    /* QR code */
    ctx.drawImage(input, 50, 115);

    /* Code text at bottom */
    ctx.font = 'bold 105px Courier New';
    ctx.fillStyle = 'red';
    ctx.textAlign = "center";
    ctx.fillText(code, 275, 675, 500);

    return output;
  }

  private generate(code: string) {
    return new QRCodeStyling({
      data: 'https://geocodeapp.tech/g/'+code,
      width: 450,
      height: 450,
      margin: 0,
      qrOptions: {
        errorCorrectionLevel: 'L'
      },
      dotsOptions: {
        color: '#3AAFA9'
      },
      cornersSquareOptions: {
        color: '#000000'
      }
    });
  }

}
