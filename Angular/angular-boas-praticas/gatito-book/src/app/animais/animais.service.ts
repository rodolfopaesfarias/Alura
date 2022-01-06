import { environment } from './../../environments/environment';
import { TokenService } from './../autenticacao/token.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Animais } from './animal';

const URL_API = environment.apiURL;

@Injectable({
  providedIn: 'root',
})
export class AnimaisService {
  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService
  ) {}

  getListaAnimaisDoUsuario(nomeUsuario: string): Observable<Animais> {
    const token = this.tokenService.retornarToken();
    const headers = new HttpHeaders().append('x-access-token', token);
    return this.httpClient.get<Animais>(`${URL_API}/${nomeUsuario}/photos`, {
      headers,
    });
  }
}
