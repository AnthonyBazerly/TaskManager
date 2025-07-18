import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { isPlatformBrowser } from '@angular/common';

@Injectable()
export class CsrfInterceptor implements HttpInterceptor {
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    if (isPlatformBrowser(this.platformId) ) {
      const csrfToken = document.cookie
        .split('; ')
        .find(row => row.startsWith('XSRF-TOKEN='))
        ?.split('=')[1];

      if (csrfToken) {
        const clonedReq = req.clone({
          headers: req.headers.set('X-XSRF-TOKEN', csrfToken)
        });
        return next.handle(clonedReq);
      }
    }

    return next.handle(req);
  }
}