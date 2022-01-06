import { Usuario } from './usuario';
import { TokenService } from './../token.service';
import { Injectable } from '@angular/core';
import jwt_decode from 'jwt-decode';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private usuarioSubject = new BehaviorSubject<Usuario>({});
  constructor(private tokenService: TokenService) {
    if (this.tokenService.possuiToken()) {
      this.decodificarJWT();
    }
  }

  private decodificarJWT(): void {
    const token: string = this.tokenService.retornarToken();
    const usuario: Usuario = jwt_decode(token) as Usuario;
    this.usuarioSubject.next(usuario);
  }

  retornarUsuario(): Observable<Usuario> {
    return this.usuarioSubject.asObservable();
  }

  salvarToken(token: string): void {
    this.tokenService.salvarToken(token);
    this.decodificarJWT();
  }

  logout(): void {
    this.tokenService.excluirToken();
    this.usuarioSubject.next({});
  }

  estaLogado(): boolean {
    return this.tokenService.possuiToken();
  }
}
