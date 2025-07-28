import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from '../../environment';
import { CustomTableComponent } from './custom-table/custom-table.component';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-view-employees',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, CustomTableComponent],
  templateUrl: './view-employees.component.html',
  styleUrl: './view-employees.component.css'
})
export class ViewEmployeesComponent {
  employees: any[] = [];
  selectedEmployees: any[] = [];
  filteredEmployees: any[] = [];
  allSelected: boolean = false;
  route: string = '/employees';

  constructor(private router: Router, private http: HttpClient, private authService: AuthService) { }

  ngOnInit() {
    this.employees = this.authService.employees;
    if (this.employees.length === 0) {
      this.loadEmployees();
    }
    
    this.filteredEmployees = this.employees;
  }

  loadEmployees() {
    this.http.get<any[]>(`${environment.apiUrl}/api/employees`, { withCredentials: true }).subscribe({
      next: res => {
        this.employees = res;
        this.authService.employees = res;
      },
      error: err => {
        console.error("Failed to load employees", err);
      }
    });
  }

  createEmployee(): void { }  
  createChat(): void { }
  assignTask(): void { }
}
