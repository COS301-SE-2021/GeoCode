import {Injectable} from '@angular/core';
import QRCodeStyling from 'qr-code-styling';

@Injectable({ providedIn: 'root' })
export class QRGenerator {

  public download(code: string, filename?: string) {
    if (!(filename)) filename = 'qrcode'
    const canvas = this.getCanvas(code);
    const link = document.createElement('a');
    link.download = filename+'.png';
    link.href = canvas.toDataURL()
    link.click();
  }

  public getCanvas(code: string) {
    const input = this.generate(code)._canvas.getCanvas();
    const output = document.createElement('canvas');
    const ctx = output.getContext('2d');

    output.width = 550;
    output.height = 715;

    /* White background */
    ctx.fillStyle = "white";
    ctx.fillRect(0, 0, 550, 715);

    /* URL header */
    ctx.font = '900 55px Helvetica';
    ctx.fillStyle = 'red';
    ctx.fillText('geocodeapp.tech', 25, 65, 500);

    /* Border around QR code */
    ctx.fillStyle = "black";
    ctx.fillRect(25, 90, 500, 500);

    /* QR code */
    ctx.drawImage(input, 50, 115);

    /* Code text at bottom */
    ctx.font = 'bold 105px Courier New';
    ctx.fillStyle = 'red';
    ctx.fillText(code, 25, 675, 500);

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
