import { Component, inject, signal, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../core/api.service';
import { StatusBadgeComponent } from '../../shared/status-badge/status-badge';

@Component({
  selector: 'app-space-detail',
  standalone: true,
  imports: [CommonModule, FormsModule, StatusBadgeComponent],
  templateUrl: './space-detail.html',
  styleUrl: './space-detail.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SpaceDetailComponent implements OnInit {
  private api   = inject(ApiService);
  private route = inject(ActivatedRoute);

  space    = signal<any>(null);
  networks = signal<any[]>([]);
  showForm = signal(false);

  form = { vnetId: '', subnet: '', vni: null as number | null };

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.api.getSpace(id).subscribe({ next: s => this.space.set(s), error: () => {} });
    this.loadNetworks(id);
  }

  loadNetworks(id: string) {
    this.api.getSpaceNetworks(id).subscribe({ next: n => this.networks.set(n), error: () => {} });
  }

  addNetwork() {
    const s = this.space();
    if (!s) return;
    this.api.createSpaceNetwork({ spaceId: s.id, ...this.form }).subscribe({
      next: () => { this.showForm.set(false); this.form = { vnetId: '', subnet: '', vni: null }; this.loadNetworks(s.id); },
      error: () => {}
    });
  }

  deleteNetwork(id: string) {
    this.api.deleteSpaceNetwork(id).subscribe({
      next: () => this.loadNetworks(this.space()?.id),
      error: () => {}
    });
  }
}
