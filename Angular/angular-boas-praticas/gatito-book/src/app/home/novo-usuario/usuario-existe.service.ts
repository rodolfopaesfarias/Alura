import { NovoUsuarioService } from './novo-usuario.service';
import { Injectable } from '@angular/core';
import { AbstractControl } from '@angular/forms';
import { switchMap, map, first } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioExisteService {
  constructor(private novoUsuarioService: NovoUsuarioService) {}

  usuarioExiste() {
    return (control: AbstractControl) => {
      return control.valueChanges.pipe(
        switchMap((nomeUsuario) =>
          this.novoUsuarioService.usuarioJaExiste(nomeUsuario)
        ),
        map((usuarioExiste) =>
          usuarioExiste ? { usuarioExiste: true } : null
        ),
        first()
      );
    };
  }
}
