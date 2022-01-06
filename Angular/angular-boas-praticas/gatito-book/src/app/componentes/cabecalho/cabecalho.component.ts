import { Router } from '@angular/router';
import { UsuarioService } from './../../autenticacao/usuario/usuario.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cabecalho',
  templateUrl: './cabecalho.component.html',
  styleUrls: ['./cabecalho.component.css'],
})
export class CabecalhoComponent {
  constructor(private usuarioService: UsuarioService, private router: Router) {}

  usuario$ = this.usuarioService.retornarUsuario();

  logout() {
    this.usuarioService.logout();
    this.router.navigate(['']);
  }
}
