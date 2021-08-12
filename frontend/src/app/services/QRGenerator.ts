import {Injectable} from '@angular/core';
import jsQR, {QRCode} from 'jsqr';
import IQrCodeWithLogo, {BaseOptions} from 'qrcode-with-logos'

@Injectable({ providedIn: 'root' })
export class QRGenerator {
  generate(code){

    const image = document.createElement('IMAGE');
    // @ts-ignore
    // @ts-ignore
    const qr: BaseOptions ={
      //canvas:  document.getElementById('canvas'),
      content: code,
      downloadName: 'qrcode',
      //image: image,
      logo: './assets/images/logo.png',
      width: 380
    };
    const qrcode = new IQrCodeWithLogo(qr);

    // const qrcode = new IQrCodeWithLogo({
    //   canvas: document.getElementById('canvas'),
    //   content: qr,
    //   width: 380,
    //   //   download: true,
    //   image: document.getElementById('image'),
    //   logo: {
    //     src: 'https://avatars1.githubusercontent.com/u/28730619?s=460&v=4'
    //   }
    // });

    qrcode.toCanvas().then(() =>{
      qrcode.toImage().then(()=>{
        setTimeout(()=>{
          qrcode.downloadImage('qrcode');
        },2000);
      });
    });
  }
}
