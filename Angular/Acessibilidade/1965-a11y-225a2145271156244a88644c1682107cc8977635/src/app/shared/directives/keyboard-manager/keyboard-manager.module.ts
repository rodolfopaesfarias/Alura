import { KeyboardManagedItemDirective } from './keyboard-managed-item.directive';
import { KeyBoardManagerDirective } from './keyboard-manager.directive';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [KeyBoardManagerDirective, KeyboardManagedItemDirective],
  imports: [CommonModule],
  exports: [KeyBoardManagerDirective, KeyboardManagedItemDirective],
})
export class KeyBoardManagerModule {}
