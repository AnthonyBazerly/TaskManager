import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}
              // can access data in route
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const isLoggedIn = this.authService.getEmployee() !== null;
    
    if (state.url === '/login' && isLoggedIn) {
      this.router.navigate(['']);
      return false;
    } 
    else if (state.url !== '/login' && !isLoggedIn) {
      this.router.navigate(['/login']);
      return false;
    }
    
    return true;
  }
}