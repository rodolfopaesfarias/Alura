import { environment } from './../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, mapTo, Observable, of, throwError } from 'rxjs';
import { Animais, Animal } from './animal';

const URL_API = environment.apiURL;
const NOT_MODIFIED = '304';

@Injectable({
  providedIn: 'root',
})
export class AnimaisService {
  constructor(private httpClient: HttpClient) {}

  getListaAnimaisDoUsuario(nomeUsuario: string): Observable<Animais> {
    return this.httpClient.get<Animais>(`${URL_API}/${nomeUsuario}/photos`);
  }

  findById(id: number): Observable<Animal> {
    return this.httpClient.get<Animal>(`${URL_API}/photos/${id}`);
  }

  excluirAnimal(id: number): Observable<Animal> {
    return this.httpClient.delete<Animal>(`${URL_API}/photos/${id}`);
  }

  curtir(id: number): Observable<boolean> {
    return this.httpClient
      .post(`${URL_API}/photos/${id}/like`, {}, { observe: 'response' })
      .pipe(
        mapTo(true),
        catchError((error) =>
          error.status == NOT_MODIFIED
            ? of(false)
            : throwError(() => new Error(error))
        )
      );
  }

  upload(
    descricao: string,
    permiteComentario: boolean,
    arquivo: File
  ) {
    const formData = new FormData();
    formData.append('description', descricao);
    formData.append('allowComments', permiteComentario ? 'true' : 'false');
    formData.append('imageFile', arquivo);

    return this.httpClient.post(`${URL_API}/photos/upload`, formData, {
      observe: 'events',
      reportProgress: true,
    });
  }
}
