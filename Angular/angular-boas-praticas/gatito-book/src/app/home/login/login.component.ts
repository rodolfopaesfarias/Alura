import { AutenticacaoService } from './../../autenticacao/autenticacao.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public usuario: string = '';
  public senha: string = '';

  constructor(
    private autenticacaoService: AutenticacaoService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  login() {
    this.autenticacaoService.autenticar(this.usuario, this.senha).subscribe(
      () => {
        this.router.navigate(['animais']);
      },
      (error) => {
        alert('Erro ao autenticar!');
        console.log(error);
      }
    );
  }
}
