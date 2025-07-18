import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { RouterOutlet, Router } from '@angular/router';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { AuthService } from './auth.service'
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { environment } from './environment';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'project';

  constructor(
    private router: Router,
    private http: HttpClient,
    private authService: AuthService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      document.cookie = "XSRF-TOKEN=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    }
    this.http.get<any>(`${environment.apiUrl}/api/employees/get`, { withCredentials: true })
    .pipe(
      catchError(err => {
        if (err.status === 403) {
        } else if (err.status === 500) {
          console.error("Internal server error. Check the server logs", err);
        } else if (err.message && err.message.includes('ERR_CONNECTION_REFUSED')) {
          console.error("Server is down. Turn on the server and try again", err);
        } else {
          console.error("An unexpected error occurred. Check the console for details", err);
        }
        return of(null);
      })
    )
    .subscribe();
  }

  get isLoggedIn() {
    return this.authService.getEmployee() !== null;
  }

  NavigateTo(route: String) {
    this.router.navigate([route]);
  }

  Logout() {
    this.authService.setEmployee(null);
    if (isPlatformBrowser(this.platformId)) {
      document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    }
    this.router.navigate(["/login"]);
  }
}
