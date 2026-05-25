import { Component, inject, ChangeDetectionStrategy } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive, Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { filter, map } from 'rxjs/operators';
import { toSignal } from '@angular/core/rxjs-interop';
import { ApiService } from './core/api.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class App {
  private router = inject(Router);
  api = inject(ApiService);

  isLogin = toSignal(
    this.router.events.pipe(
      filter(e => e instanceof NavigationEnd),
      map((e: any) => e.urlAfterRedirects === '/login')
    ),
    { initialValue: false }
  );

  nav = [
    { label: 'Dashboard', path: '/dashboard', icon: '▦' },
    { label: 'Spaces',    path: '/spaces',    icon: '◫' },
    { label: 'Machines',  path: '/machines',  icon: '⬡' },
    { label: 'Settings',  path: '/settings',  icon: '⚙' },
  ];

  get user() { return this.api.getUser(); }

  logout() { this.api.logout(); }
}
