import { Transferencia } from './../models/transferencia.model';
import { TransferenciasService } from './../services/transferencias.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.scss'],
})
export class ExtratoComponent implements OnInit {
  @Input() transferencias: any[] = [];

  constructor(private transferenciasService: TransferenciasService) {}

  ngOnInit(): void {
    this.transferenciasService
      .getTransferencias()
      .subscribe((transferencias: Transferencia[]) => {
        console.table(transferencias);
        this.transferencias = transferencias;
      });
  }
}
