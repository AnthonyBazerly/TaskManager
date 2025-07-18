import { Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ViewTasksComponent } from './view-tasks/view-tasks.component'
import { CreateTasksComponent } from './create-tasks/create-tasks.component'

export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: 'tasks', component: ViewTasksComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: 'tasks/new', component: CreateTasksComponent, pathMatch: 'full', canActivate: [AuthGuard] },
    { path: '**', redirectTo: '', pathMatch: 'full' }
];
