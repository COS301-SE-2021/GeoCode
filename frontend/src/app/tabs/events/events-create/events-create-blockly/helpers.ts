import {TestCase} from '../../../../services/geocode-api';

export interface TestCaseEvent {
  type: 'input'|'prompt'|'output';
  text: string;
  error: { text?: string };
}

export class DetailedTestCase {

  readonly events: TestCaseEvent[];

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
    this.events.push({type: 'input', text, error: null});
  }

  public addPrompt(text: string) {
    this.events.push({type: 'prompt', text, error: null});
  }

  public addOutput(text: string) {
    this.events.push({type: 'output', text, error: null});
  }

}
