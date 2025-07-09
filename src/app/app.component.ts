import { Component } from '@angular/core';
import { RouterOutlet, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from './auth.service'

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'project';

  constructor(private router: Router, private authService: AuthService) { }

  get isLoggedIn(){
    return this.authService.getEmployee() !== null;
  }

  Logout(){
    this.authService.setEmployee(null);
    this.router.navigate(["/login"]);
  }
}
