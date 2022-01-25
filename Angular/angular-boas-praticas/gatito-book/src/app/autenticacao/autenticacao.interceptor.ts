import { TokenService } from './token.service';
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AutenticacaoInterceptor implements HttpInterceptor {
  constructor(private tokenService: TokenService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    request = this.adicionarToken(request);
    return next.handle(request);
  }

  adicionarToken(request: HttpRequest<unknown>): HttpRequest<unknown> {
    if (this.tokenService.possuiToken()) {
      const token = this.tokenService.retornarToken();
      const headers = new HttpHeaders().append('x-access-token', token);
      const requestComToken = request.clone({ headers });
      return requestComToken;
    }
    return request;
  }
}
