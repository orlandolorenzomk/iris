import { Component, inject, signal, ChangeDetectionStrategy } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../../core/api.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginComponent {
  private api    = inject(ApiService);
  private router = inject(Router);

  email    = '';
  password = '';
  error    = signal('');
  loading  = signal(false);

  submit() {
    if (!this.email || !this.password) { this.error.set('Email and password are required.'); return; }
    this.loading.set(true);
    this.error.set('');
    this.api.login(this.email, this.password).subscribe({
      next: () => this.router.navigate(['/dashboard']),
      error: () => { this.error.set('Invalid email or password.'); this.loading.set(false); }
    });
  }
}
