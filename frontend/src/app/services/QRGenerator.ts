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

    output.height = 500;
    output.width = 500;

    ctx.fillStyle = "black";
    ctx.fillRect(0, 0, output.width, output.height);
    ctx.drawImage(input, 25, 25);

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
