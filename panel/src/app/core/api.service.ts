import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private http   = inject(HttpClient);
  private router = inject(Router);
  private api    = 'http://localhost:8081';

  // Auth
  login(email: string, password: string) {
    return this.http.post<any>(`${this.api}/auth/login`, { email, password }).pipe(
      tap(res => {
        localStorage.setItem('jwt_iris', res.token);
        localStorage.setItem('iris_user', JSON.stringify({ id: res.id, email: res.email, role: res.role }));
      })
    );
  }

  logout() {
    localStorage.removeItem('jwt_iris');
    localStorage.removeItem('iris_user');
    this.router.navigate(['/login']);
  }

  getUser(): { id: string; email: string; role: string } | null {
    const raw = localStorage.getItem('iris_user');
    return raw ? JSON.parse(raw) : null;
  }

  // Spaces
  getSpaces(userId: string)         { return this.http.get<any[]>(`${this.api}/spaces/user/${userId}`); }
  getSpace(id: string)              { return this.http.get<any>(`${this.api}/spaces/${id}`); }
  createSpace(dto: any)             { return this.http.post<any>(`${this.api}/spaces`, dto); }
  updateSpace(id: string, dto: any) { return this.http.put<any>(`${this.api}/spaces/${id}`, dto); }
  deleteSpace(id: string)           { return this.http.delete(`${this.api}/spaces/${id}`); }

  // Space Networks
  getSpaceNetworks(spaceId: string)          { return this.http.get<any[]>(`${this.api}/space-networks/space/${spaceId}`); }
  createSpaceNetwork(dto: any)               { return this.http.post<any>(`${this.api}/space-networks`, dto); }
  updateNetworkStatus(id: string, status: string) { return this.http.patch(`${this.api}/space-networks/${id}/status`, null, { params: { status } }); }
  deleteSpaceNetwork(id: string)             { return this.http.delete(`${this.api}/space-networks/${id}`); }

  // Machines
  getMachines()                       { return this.http.get<any[]>(`${this.api}/machines`); }
  getMachine(id: string)              { return this.http.get<any>(`${this.api}/machines/${id}`); }
  createMachine(dto: any)             { return this.http.post<any>(`${this.api}/machines`, dto); }
  updateMachine(id: string, dto: any) { return this.http.put<any>(`${this.api}/machines/${id}`, dto); }
  updateMachineStatus(id: string, status: string) { return this.http.patch(`${this.api}/machines/${id}/status`, null, { params: { status } }); }
  deleteMachine(id: string)           { return this.http.delete(`${this.api}/machines/${id}`); }

  // Cluster Settings
  getClusterSettings()        { return this.http.get<any>(`${this.api}/cluster-settings`); }
  updateClusterSettings(dto: any) { return this.http.patch<any>(`${this.api}/cluster-settings`, dto); }
}
