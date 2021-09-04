import {Injectable} from '@angular/core';
import IQrCodeWithLogo, {BaseOptions} from 'qrcode-with-logos'

@Injectable({ providedIn: 'root' })
export class QRGenerator {

  public download(code) {
    this.generate(code).downloadImage('qrcode');
  }

  public async getCanvas(code) {
    return await this.generate(code).getCanvas();
  }

  private generate(code): IQrCodeWithLogo {
    return new IQrCodeWithLogo({
      content: code,
      downloadName: 'qrcode',
      logo: './assets/images/MagnifyingGlass Blk.png',
      width: 1500
    });
  }
}
