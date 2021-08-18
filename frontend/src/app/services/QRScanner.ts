import {Injectable} from '@angular/core';
import {Camera, CameraResultType, CameraSource} from '@capacitor/camera';
import jsQR, {QRCode} from 'jsqr';

@Injectable({ providedIn: 'root' })
export class QRScanner {

  async scan() {
    const image = await Camera.getPhoto({
      quality: 100,
      allowEditing: false,
      resultType: CameraResultType.Uri,
      source: CameraSource.Camera
    });
    const qrCode = await this.decodeQR(image.webPath);
    if (qrCode) {
      return qrCode.data;
    } else {
      return null;
    }
  }

  // This function is based on https://github.com/cozmo/jsQR/issues/96#issuecomment-467911783
  private decodeQR(url: string): Promise<QRCode> {
    return new Promise<QRCode>((resolve) => {
      const img: HTMLImageElement = document.createElement('img');
      img.onload = () => {
        const canvas: HTMLCanvasElement = document.createElement('canvas');
        const context: CanvasRenderingContext2D = canvas.getContext('2d');

        canvas.width = img.width;
        canvas.height = img.height;
        context.drawImage(img, 0, 0);

        const imageData: ImageData = context.getImageData(0, 0, img.width, img.height);
        const qrCode: QRCode = jsQR(imageData.data, imageData.width, imageData.height);
        resolve(qrCode);
      };
      img.src = url;
    });
  }
}
