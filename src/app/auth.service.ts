import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private employee: any = null;

  getEmployee(): any {
    return this.employee;
  }

  setEmployee(employee: any) {
    this.employee = employee;
  }
}
