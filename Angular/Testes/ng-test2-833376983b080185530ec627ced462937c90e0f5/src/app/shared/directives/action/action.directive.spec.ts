import { ActionDirectiveModule } from './action.module';
import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ActionDirective } from './action.directive';
import { Component } from '@angular/core';
import { By } from '@angular/platform-browser';

describe(ActionDirective.name, () => {
  let fixture: ComponentFixture<ActionDirectiveTestComponent>;
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActionDirectiveTestComponent],
      imports: [ActionDirectiveModule],
    });

    fixture = TestBed.createComponent(ActionDirectiveTestComponent);
  });

  it(`(D) (@Output appAction) should emit event with payload when ENTER key is pressed`, () => {
    // const divElement: HTMLElement = fixture.nativeElement.querySelector('.dummy-component');
    const divElement: HTMLElement = fixture.debugElement.query(By.directive(ActionDirective)).nativeElement;
    const event = new KeyboardEvent('keyup', { key: 'Enter'})
    divElement.dispatchEvent(event);
    expect(fixture.componentInstance.hasEvent).toBeTruthy();
  });

  it(`(D) (@Output appAction) should emit event with payload when clicked`, () => {
    const divElement: HTMLElement = fixture.nativeElement.querySelector('.dummy-component');
    divElement.click();
    expect(fixture.componentInstance.hasEvent).toBeTruthy();
  });
});

@Component({
  template: `<div class="dummy-component" (appAction)="actionHandler($event)"></div>`
})
class ActionDirectiveTestComponent {
  private event: Event = null;

  public actionHandler(event: Event): void {
    this.event = event;
  }

  public hasEvent(): boolean {
    return !!this.event;
  }
}
