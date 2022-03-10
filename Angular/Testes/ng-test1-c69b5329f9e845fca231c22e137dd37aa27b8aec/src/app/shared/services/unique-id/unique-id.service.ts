import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Injectable } from '@angular/core';
import { v4 as uuidv4 } from 'uuid';

@Injectable()
export class UniqueIdService {
  private numberOfGeneratedIds = 0;
  private validId = /^[A-Za-z]+[\w\-\:\.]*$/;

  public generateUniqueIdWithPrefix(prefix: string): string {
    if (!this.validId.test(prefix) || !prefix) {
      throw Error('Prefix cannot be empty!');
    }

    const uniqueId = this.generateUniqueId();
    this.numberOfGeneratedIds++;
    return `${prefix}-${uniqueId}`;
  }

  private generateUniqueId(): string {
    return uuidv4();
  }

  public getNumberOfGeneratedUniqueIds(): number {
    return this.numberOfGeneratedIds;
  }
}
