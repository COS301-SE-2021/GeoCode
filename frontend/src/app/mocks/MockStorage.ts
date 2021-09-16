import {Storage} from '@ionic/storage-angular';

// Mocks the Ionic Storage service
export class MockStorage {

  private data: {[key: string]: any} = {};

  static provider() {
    return { provide: Storage, useValue: new MockStorage() };
  }

  async get(key: string) {
    return this.data[key];
  }

  async set(key: string, value: any) {
    this.data[key] = value;
    return value;
  }

  async remove(key: string) {
    const output = this.data[key];
    delete this.data[key];
    return output;
  }

  async clear() {
    this.data = {};
  }
}
