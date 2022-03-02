import { Directive, ElementRef, Output, EventEmitter } from '@angular/core';

@Directive({
  selector: '[appKmItem]',
})
export class KeyboardManagedItemDirective {
  constructor(private elementRef: ElementRef<HTMLElement>) {}

  @Output() public focused = new EventEmitter<void>();

  public focus(): void {
    this.elementRef.nativeElement.focus();
    this.focused.emit();
  }

  public isFocused(): boolean {
    return this.elementRef.nativeElement === document.activeElement;
  }
}
