import {TestCase} from '../../../../services/geocode-api';

export class DetailedTestCase {

  readonly events: {
    type: 'input'|'output';
    text: string;
  }[];

  constructor() {
    this.events = [];
  }

  public static toSimpleTestCase(input: DetailedTestCase): TestCase {
    return {
      inputs: DetailedTestCase.getSpecificEvents(input, 'input'),
      output: DetailedTestCase.getSpecificEvents(input, 'output').join('\n')
    };
  }

  private static getSpecificEvents(input: DetailedTestCase, type: 'input'|'output'): string[] {
    const toReturn = [];
    for (const event of input.events) {
      if (event.type === type) {
        toReturn.push(event.text);
      }
    }
    return toReturn;
  }

  public addInput(text: string) {
    this.events.push({type: 'input', text});
  }

  public addOutput(text: string) {
    this.events.push({type: 'output', text});
  }

}
