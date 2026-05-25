import { Component, Input, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-status-badge',
  standalone: true,
  imports: [CommonModule],
  template: `<span class="badge" [ngClass]="color">{{ status }}</span>`,
  styles: [`
    .badge {
      display: inline-flex;
      align-items: center;
      gap: 5px;
      font-size: 12px;
      font-weight: 600;
      padding: 2px 10px;
      border-radius: 12px;
      text-transform: uppercase;
      letter-spacing: 0.2px;
      white-space: nowrap;

      &::before {
        content: '';
        display: inline-block;
        width: 7px;
        height: 7px;
        border-radius: 50%;
        flex-shrink: 0;
      }

      &.green {
        background: #F2FAF2;
        color: #1E8900;
        border: 1px solid #BFE6BF;
        &::before { background: #1E8900; }
      }
      &.amber {
        background: #FEF9E7;
        color: #8D6605;
        border: 1px solid #F3D78A;
        &::before { background: #D68910; }
      }
      &.red {
        background: #FDF3F1;
        color: #D13212;
        border: 1px solid #F5C6C3;
        &::before { background: #D13212; }
      }
      &.gray {
        background: #F2F3F3;
        color: #545B64;
        border: 1px solid #D5DBDB;
        &::before { background: #879596; }
      }
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
