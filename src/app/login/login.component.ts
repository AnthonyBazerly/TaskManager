import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environment';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = ""; password = ""; error = ""; loading = false;

  constructor(private router: Router, private authService: AuthService, private http: HttpClient) { }

  onLogin() {
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
    this.http.post<any>(`${environment.apiUrl}/api/employees/login`, { email: this.email, password: this.password }).subscribe({
      next: res => {
        this.loading = false;
        this.password = "";
        this.authService.setEmployee(res.employee);
        this.router.navigate(['']);
      },
      error: err => {
        this.loading = false;
        if (err.status === 401) {
          this.error = "Invalid email or password";
        } else {
          this.error = "An unexpected error occurred. Please try again later";
        }
      }
    });
  }
}
