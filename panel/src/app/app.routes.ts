import { Routes } from '@angular/router';
import { authGuard } from './core/auth.guard';

export const routes: Routes = [
  { path: 'login', loadComponent: () => import('./pages/login/login').then(m => m.LoginComponent) },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', canActivate: [authGuard], loadComponent: () => import('./pages/dashboard/dashboard').then(m => m.DashboardComponent) },
  { path: 'spaces', canActivate: [authGuard], loadComponent: () => import('./pages/spaces/spaces').then(m => m.SpacesComponent) },
  { path: 'spaces/:id', canActivate: [authGuard], loadComponent: () => import('./pages/space-detail/space-detail').then(m => m.SpaceDetailComponent) },
  { path: 'machines', canActivate: [authGuard], loadComponent: () => import('./pages/machines/machines').then(m => m.MachinesComponent) },
  { path: 'settings', canActivate: [authGuard], loadComponent: () => import('./pages/settings/settings').then(m => m.SettingsComponent) },
  { path: '**', redirectTo: 'dashboard' }
];
