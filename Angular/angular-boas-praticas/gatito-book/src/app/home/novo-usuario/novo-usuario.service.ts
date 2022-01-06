import { environment } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NovoUsuario } from './novo-usuario';

const API_URL:string = environment.apiURL;

@Injectable({
  providedIn: 'root',
})
export class NovoUsuarioService {
  constructor(private http: HttpClient) {}

  cadastrarUsuario(novoUsuario: NovoUsuario) {
    return this.http.post(`${API_URL}/signup`, novoUsuario);
  }

  usuarioJaExiste(nomeUsuario: string) {
    return this.http.get(`${API_URL}/user/exists/${nomeUsuario}`);
  }
}
