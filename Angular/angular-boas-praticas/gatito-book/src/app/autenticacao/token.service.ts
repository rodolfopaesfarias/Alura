import { Injectable } from '@angular/core';

const KEY: string = 'token';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  retornarToken(): string {
    return localStorage.getItem(KEY) ?? '';
  }

  salvarToken(token: string): void {
    localStorage.setItem(KEY, token);
  }

  excluirToken(): void {
    localStorage.removeItem(KEY);
  }

  possuiToken(): boolean {
    return !!this.retornarToken();
  }
}
