import { Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ViewTasksComponent } from './view/view-tasks/view-tasks.component'
import { CreateTasksComponent } from './create/create-tasks/create-tasks.component'
import { ViewEmployeesComponent } from './view/view-employees/view-employees.component';
import { ViewChatsComponent } from './view/view-chats/view-chats.component';

export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: 'tasks', component: ViewTasksComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: 'tasks/new', component: CreateTasksComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: 'employees', component: ViewEmployeesComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: 'chats', component: ViewChatsComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: '**', redirectTo: '', pathMatch: 'full' }
];
