import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { LoginComponent } from "./login/login.component";
import { AdmindashboardComponent } from "./dashboard/admindashboard/admindashboard.component";
import { RegisterdashboardComponent } from "./dashboard/admindashboard/registerdashboard/registerdashboard.component";
import { ProfileComponent } from "./profile/profile.component";
import { SupplierComponent } from './supplier/supplier.component';
import { ManagerComponent } from './manager/manager.component';
import { HomeComponent } from './home/home.component';

import { RoleDropdownResolver } from "./route-guards/roles-dropdown-resolve";
import { RoleGuardService } from "./route-guards/auth-guard-roles";
import { ProfileDataResolver } from "./route-guards/profile-resolve";

const appRoute : Routes= [
    { path: '', pathMatch: 'full', redirectTo: '/login' },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate: [RoleGuardService]},
    {path: 'profile', component: ProfileComponent, resolve: {
        profile : ProfileDataResolver
    }},
    { path: 'supplier', component: SupplierComponent },
    { path: 'manager', component: ManagerComponent },
    { path: 'admin', component: AdmindashboardComponent, canActivate: [RoleGuardService],
        children: [
            {path: 'register', component: RegisterdashboardComponent, resolve:{
                roles : RoleDropdownResolver
            } },
            {path: 'profile', component: ProfileComponent, resolve: {
                profile : ProfileDataResolver
            }}
        ], data: {
            expectedRole: 'ROLE_ADMIN'
        }
    }
];

@NgModule({
    imports : [
        RouterModule.forRoot(appRoute)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {

}