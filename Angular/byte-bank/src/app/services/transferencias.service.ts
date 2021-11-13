import { Transferencia } from './../models/transferencia.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class TransferenciasService {
  private _transferencias: any = [];
  private url: string = 'http://localhost:3000/transferencias';

  constructor(private httpClient: HttpClient) {}

  getTransferencias(): Observable<Transferencia[]> {
    return this.httpClient.get<Transferencia[]>(this.url);
  }

  adicionar(transferencia: Transferencia): Observable<Transferencia> {
    this.setDataTransferencia(transferencia);
    return this.httpClient.post<Transferencia>(this.url, transferencia);
  }

  private setDataTransferencia(transferencia: Transferencia) {
    transferencia.data = new Date();
  }
}
