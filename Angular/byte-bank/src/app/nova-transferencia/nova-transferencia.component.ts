import { Component } from '@angular/core';

@Component({
  selector: 'nova-transferencia',
  templateUrl: './nova-transferencia.component.html',
  styleUrls: ['./nova-transferencia.component.scss'],
})
export class NovaTransferenciaComponent {
  valor!: number;
  destino!: number;

  transferir() {
    console.log('Solicitada transferência', this.valor, this.destino);
  }
}
