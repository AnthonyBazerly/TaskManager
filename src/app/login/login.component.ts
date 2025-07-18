import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { environment } from '../environment';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = ""; password = ""; error = ""; loading = false; showPassword = false;

  constructor(private router: Router, private authService: AuthService, private http: HttpClient) { }

  onLogin() {
    debugger;
    this.error = ""; 
    if (!this.email || !this.password) {
      this.error = "Email and password are required";
      return;
    }
    if (!this.email.includes('@example.com')) {
      this.error = "Please enter a valid email address";
      return;
    }
    this.loading = true;
    this.http.post<any>(`${environment.apiUrl}/api/employees/login`, { email: this.email, password: this.password }, { withCredentials: true }).subscribe({
      next: res => {
        this.loading = false;
        this.password = "";
        this.authService.setEmployee(res.employee);
        this.router.navigate(['']);
      },
      error: err => {
        debugger;
        this.loading = false;
        if (err.status === 401) {
          this.error = "Invalid email or password";
        } else if (err.status === 403) {
          this.error = "Access denied. Fix the server configuration";
          console.log(err);
        } else if (err.status === 500) {
          this.error = "Internal server error. Check the server logs";
          console.log(err);
        } else if (err.message.includes('ERR_CONNECTION_REFUSED')) {
          this.error = "Server is down. Turn on the server and try again";
          console.log(err);
        } else {
          this.error = "An unexpected error occurred. Check the console for details";
          console.log(err);
        }
      }
    });
  }
}
