import { Component, inject, signal, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../core/api.service';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './settings.html',
  styleUrl: './settings.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SettingsComponent implements OnInit {
  private api = inject(ApiService);

  saved       = signal(false);
  error       = signal('');
  lastUpdated: string | null = null;

  form = { sdnZoneType: 'VXLAN', sdnZoneName: '', natsUrl: '' };

  ngOnInit() {
    this.api.getClusterSettings().subscribe({
      next: s => {
        this.form = { sdnZoneType: s.sdnZoneType, sdnZoneName: s.sdnZoneName, natsUrl: s.natsUrl };
        this.lastUpdated = s.updatedAt ?? null;
      },
      error: () => {}
    });
  }

  save() {
    this.error.set('');
    this.api.updateClusterSettings(this.form).subscribe({
      next: (s) => { this.lastUpdated = s?.updatedAt ?? new Date().toISOString(); this.saved.set(true); setTimeout(() => this.saved.set(false), 2500); },
      error: () => this.error.set('Failed to save settings.')
    });
  }
}
