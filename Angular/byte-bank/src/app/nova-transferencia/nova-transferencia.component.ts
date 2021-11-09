import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'nova-transferencia',
  templateUrl: './nova-transferencia.component.html',
  styleUrls: ['./nova-transferencia.component.scss'],
})
export class NovaTransferenciaComponent {
  @Output() aoTransferir = new EventEmitter<any>();

  valor?: number;
  destino?: number;

  transferir() {
    console.log('Solicitada transferência', this.valor, this.destino);
    const transferencia = { valor: this.valor, destino: this.destino };
    this.aoTransferir.emit(transferencia);
    this.limparCampos();
  }

  limparCampos() {
    this.valor = undefined;
    this.destino = undefined;
  }
}
