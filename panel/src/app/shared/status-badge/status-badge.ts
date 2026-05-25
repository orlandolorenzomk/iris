import { Component, Input, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-status-badge',
  standalone: true,
  imports: [CommonModule],
  template: `<span class="badge" [ngClass]="color">{{ status }}</span>`,
  styles: [`
    .badge {
      display: inline-block;
      font-size: 11px;
      font-weight: 600;
      padding: 2px 8px;
      border-radius: 2px;
      text-transform: uppercase;
      letter-spacing: 0.3px;

      &.green  { background: var(--success-bg); color: var(--success-fg); border-left: 3px solid var(--success-fg); }
      &.amber  { background: var(--warning-bg); color: var(--warning-fg); border-left: 3px solid var(--warning-fg); }
      &.red    { background: var(--error-bg);   color: var(--error-fg);   border-left: 3px solid var(--error-fg); }
      &.gray   { background: #F2F3F3;            color: #5F6B7A;           border-left: 3px solid #AAB7B8; }
    }
  `],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class StatusBadgeComponent {
  @Input() status = '';

  get color(): string {
    const s = this.status?.toUpperCase();
    if (['ACTIVE','READY','RUNNING'].includes(s))                                      return 'green';
    if (['BOOTSTRAPPING','PENDING_APPLY','PROVISIONING','PENDING'].includes(s))        return 'amber';
    if (['UNAVAILABLE','FAILED','DESTROYED'].includes(s))                              return 'red';
    return 'gray';
  }
}
