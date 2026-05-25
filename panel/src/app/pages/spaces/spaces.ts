import { Component, inject, signal, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../../core/api.service';

@Component({
  selector: 'app-spaces',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './spaces.html',
  styleUrl: './spaces.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SpacesComponent implements OnInit {
  private api    = inject(ApiService);
  private router = inject(Router);

  spaces      = signal<any[]>([]);
  showForm    = signal(false);
  error       = signal('');

  form = { name: '', cpuLimit: 4, ramLimit: 8192, storageLimit: 100 };

  ngOnInit() {
    this.load();
  }

  load() {
    const user = this.api.getUser();
    if (user) this.api.getSpaces(user.id).subscribe({ next: s => this.spaces.set(s), error: () => {} });
  }

  create() {
    const user = this.api.getUser();
    if (!user || !this.form.name) return;
    this.api.createSpace({ userId: user.id, ...this.form }).subscribe({
      next: () => { this.showForm.set(false); this.form = { name: '', cpuLimit: 4, ramLimit: 8192, storageLimit: 100 }; this.load(); },
      error: () => this.error.set('Failed to create space.')
    });
  }

  delete(id: string, e: Event) {
    e.stopPropagation();
    this.api.deleteSpace(id).subscribe({ next: () => this.load(), error: () => {} });
  }

  open(id: string) { this.router.navigate(['/spaces', id]); }
}
