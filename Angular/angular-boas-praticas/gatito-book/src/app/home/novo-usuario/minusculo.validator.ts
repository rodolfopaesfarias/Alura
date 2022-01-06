import { AbstractControl } from "@angular/forms";

export function minusculoValidator(control: AbstractControl) {
  const texto = control.value as string;
  if (texto != texto.toLowerCase()) {
    return {minusculo: true};
  } else {
    return null;
  }
}
