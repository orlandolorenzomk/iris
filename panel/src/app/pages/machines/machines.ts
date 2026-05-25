import { Component, inject, signal, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../core/api.service';

@Component({
  selector: 'app-machines',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './machines.html',
  styleUrl: './machines.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class MachinesComponent implements OnInit {
  private api = inject(ApiService);

  machines = signal<any[]>([]);
  showForm = signal(false);
  error    = signal('');

  form = { name: '', host: '', sshPort: 22, sshUser: 'root', sshKeyEnc: '', secretKeyId: '', mgmtIface: 'eth0' };

  statuses = ['BOOTSTRAPPING', 'ACTIVE', 'UNAVAILABLE'];

  ngOnInit() { this.load(); }

  load() { this.api.getMachines().subscribe({ next: m => this.machines.set(m), error: () => {} }); }

  create() {
    if (!this.form.name || !this.form.host) { this.error.set('Name and host are required.'); return; }
    this.api.createMachine(this.form).subscribe({
      next: () => { this.showForm.set(false); this.form = { name: '', host: '', sshPort: 22, sshUser: 'root', sshKeyEnc: '', secretKeyId: '', mgmtIface: 'eth0' }; this.error.set(''); this.load(); },
      error: () => this.error.set('Failed to register machine.')
    });
  }

  updateStatus(id: string, status: string) {
    this.api.updateMachineStatus(id, status).subscribe({ next: () => this.load(), error: () => {} });
  }

  delete(id: string) {
    this.api.deleteMachine(id).subscribe({ next: () => this.load(), error: () => {} });
  }
}
