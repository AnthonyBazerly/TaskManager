import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { config } from './app/app.config.server';
import { HTTP_INTERCEPTORS, provideHttpClient, withFetch, withInterceptorsFromDi } from '@angular/common/http';
import { CsrfInterceptor } from './app/csrf.interceptor';

const bootstrap = () =>
  bootstrapApplication(AppComponent, {
    ...config,
    providers: [
      provideHttpClient(withInterceptorsFromDi(), withFetch()),
      { provide: HTTP_INTERCEPTORS, useClass: CsrfInterceptor, multi: true }
    ],
  });

export default bootstrap;
