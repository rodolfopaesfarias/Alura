import { Router } from '@angular/router';
import { NovoUsuarioService } from './novo-usuario.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NovoUsuario } from './novo-usuario';
import { minusculoValidator } from './minusculo.validator';
import { UsuarioExisteService } from './usuario-existe.service';
import { usuarioSenhaIguaisValidator } from './usuario-senha-iguais.validator';

@Component({
  selector: 'app-novo-usuario',
  templateUrl: './novo-usuario.component.html',
  styleUrls: ['./novo-usuario.component.css'],
})
export class NovoUsuarioComponent implements OnInit {
  novoUsuarioForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private novoUsuarioService: NovoUsuarioService,
    private usuarioJaExisteService: UsuarioExisteService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.novoUsuarioForm = this.formBuilder.group(
      {
        email: ['', [Validators.required, Validators.email]],
        fullName: ['', [Validators.minLength(4)]],
        userName: ['', [], [this.usuarioJaExisteService.usuarioExiste()]],
        password: [''],
      },
      { validators: [usuarioSenhaIguaisValidator] }
    );
  }

  cadastrar(): void {
    if (this.novoUsuarioForm.valid) {
      const novoUsuario: NovoUsuario =
        this.novoUsuarioForm.getRawValue() as NovoUsuario;
      this.novoUsuarioService.cadastrarUsuario(novoUsuario).subscribe(
        () => this.router.navigate(['']),
        (error) => console.log(error)
      );
    }
  }
}
