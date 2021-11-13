import { TransferenciasService } from './../services/transferencias.service';
import { Component, Output, EventEmitter } from '@angular/core';
import { Transferencia } from '../models/transferencia.model';

@Component({
  selector: 'app-nova-transferencia',
  templateUrl: './nova-transferencia.component.html',
  styleUrls: ['./nova-transferencia.component.scss'],
})
export class NovaTransferenciaComponent {
  constructor(private transferenciasService: TransferenciasService) {}

  valor?: number;
  destino?: string;

  transferir() {
    const transferencia: Transferencia = {
      valor: this.valor,
      destino: this.destino,
    };
    this.transferenciasService.adicionar(transferencia).subscribe(
      (transferencia: Transferencia) => {
        console.log(transferencia);
        this.limparCampos();
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  limparCampos() {
    this.valor = undefined;
    this.destino = undefined;
  }
}
