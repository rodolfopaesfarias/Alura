import { TransferenciasService } from './../services/transferencias.service';
import { Component, Output, EventEmitter } from '@angular/core';
import { Transferencia } from '../models/transferencia.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nova-transferencia',
  templateUrl: './nova-transferencia.component.html',
  styleUrls: ['./nova-transferencia.component.scss'],
})
export class NovaTransferenciaComponent {
  constructor(private transferenciasService: TransferenciasService, private router: Router) {}

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
        this.router.navigateByUrl('extrato');
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
