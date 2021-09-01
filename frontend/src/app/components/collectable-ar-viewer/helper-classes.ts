import {Collectable} from '../../services/geocode-api';

export class Position {
  x: number;
  y: number;

  /* Take x (left-right), y (up-down) with reference to a wall */
  constructor(x: number, y: number) {
    this.x = x;
    this.y = y;
  }

  /* Convert to A-Frame's position format which is based on looking downward */
  imagePos = () => `${this.x} 0.05 ${this.y+0.05}`;
  backgroundPos = () => `${this.x} 0 ${this.y}`;
  textPos = () => `${this.x} 0.05 ${this.y-0.3}`;
  touchPos = () => `${this.x} 0.1 ${this.y}`;
}

export class CollectableData {
  raw: Collectable;
  position: Position;

  height = 0.5;
  width = 0.5;

  constructor(collectable: Collectable, position: Position) {
    this.raw = collectable;
    this.position = position;
  }

  async loadDimensions(): Promise<void> {
    return new Promise(resolve => {
      const image = new Image();
      image.onload = () => {
        if (image.width < image.height) {
          this.width = (this.height/image.height)*image.width;
        } else if (image.width > image.height) {
          this.height = (this.width/image.width)*image.height;
        }
        resolve();
      };
      image.onerror = () => resolve();
      image.src = this.raw.type.image;
    });
  }
}
