import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _employee: any = null;
  private _employees: any[] = [];
  private _chats: any[] = [];

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  get employee(): any {
    return this._employee;
  }

  set employee(value: any) {
    this._employee = value;
  }

  get employees(): any[] {
    return this._employees;
  }

  set employees(value: any[]) {
    this._employees = value;
  }

  get chats(): any[] {
    return this._chats;
  }

  set chats(value: any[]) {
    this._chats = value;
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
