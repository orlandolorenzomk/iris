import { Component, inject, signal, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ApiService } from '../../core/api.service';
import { StatusBadgeComponent } from '../../shared/status-badge/status-badge';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink, StatusBadgeComponent],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DashboardComponent implements OnInit {
  private api = inject(ApiService);

  spaces   = signal<any[]>([]);
  machines = signal<any[]>([]);

  get totalSpaces()       { return this.spaces().length; }
  get activeMachines()    { return this.machines().filter(m => m.status === 'ACTIVE').length; }
  get unavailMachines()   { return this.machines().filter(m => m.status === 'UNAVAILABLE').length; }

  ngOnInit() {
    const user = this.api.getUser();
    if (user) {
      this.api.getSpaces(user.id).subscribe({ next: s => this.spaces.set(s), error: () => {} });
    }
    this.api.getMachines().subscribe({ next: m => this.machines.set(m), error: () => {} });
  }
}
