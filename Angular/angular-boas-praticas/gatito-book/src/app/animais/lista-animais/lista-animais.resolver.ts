import { UsuarioService } from './../../autenticacao/usuario/usuario.service';
import { AnimaisService } from './../animais.service';
import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of, switchMap, take } from 'rxjs';
import { Animais } from '../animal';

@Injectable({
  providedIn: 'root'
})
export class ListaAnimaisResolver implements Resolve<Animais> {

  constructor(private animaisService: AnimaisService, private usuarioService: UsuarioService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Animais> {
    return this.usuarioService.retornarUsuario().pipe(
      switchMap((usuario) => {
        const userName = usuario.name ?? '';
        return  this.animaisService.getListaAnimaisDoUsuario(userName);
      }),
      take(1)
    );
  }
}
