import {Injectable} from '@angular/core';
import IQrCodeWithLogo, {BaseOptions} from 'qrcode-with-logos'

@Injectable({ providedIn: 'root' })
export class QRGenerator {
  generate(code){

    const qr: BaseOptions ={
      content: code,
      downloadName: 'qrcode',
      logo: './assets/images/x.png',
      width: 1500
    };
    const qrcode = new IQrCodeWithLogo(qr);
    qrcode.downloadImage('qrcode');

  }
}
