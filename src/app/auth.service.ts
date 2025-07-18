import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private employee: any = null;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  getEmployee(): any {
    return this.employee;
  }

  setEmployee(employee: any) {
    this.employee = employee;
  }

  getCsrfToken(): string | undefined {
    if (isPlatformBrowser(this.platformId)) {
      return document.cookie
          .split('; ')
          .find(c => c.startsWith('XSRF-TOKEN='))
          ?.split('=')[1];
    }
    return undefined;
  }
}
