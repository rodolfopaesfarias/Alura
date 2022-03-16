import { Component, Input, Output, EventEmitter, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { debounceTime, takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-photo-frame',
  templateUrl: './photo-frame.component.html',
  styleUrls: ['./photo-frame.component.scss'],
})
export class PhotoFrameComponent implements OnInit, OnDestroy {

  @Output() public liked: EventEmitter<void> = new EventEmitter();
  @Input() public description = '';
  @Input() public src = '';
  @Input() public likes = 0;
  private debounceSubject: Subject<void> = new Subject();
  private usubscribe: Subject<void> = new Subject();

  public ngOnInit(): void {
    this.debounceSubject
    .asObservable()
    .pipe(debounceTime(500))
    .pipe(takeUntil(this.usubscribe))
    .subscribe(() => this.liked.emit());
  }

  public ngOnDestroy(): void {
    this.usubscribe.next();
    this.usubscribe.complete();
  }

  public like(): void {
    this.debounceSubject.next();
  }
}
