import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { LoginComponent } from "./login/login.component";
import { AdmindashboardComponent } from "./dashboard/admindashboard/admindashboard.component";
import { RegisterdashboardComponent } from "./dashboard/admindashboard/registerdashboard/registerdashboard.component";
import { ProfileComponent } from "./profile/profile.component";
import { SupplierComponent } from './supplier/supplier.component';
import { ManagerComponent } from './manager/manager.component';

import { RoleDropdownResolver } from "./route-guards/roles-dropdown-resolve";

const appRoute : Routes= [
    { path: '', pathMatch: 'full', redirectTo: '/login' },
    { path: 'login', component: LoginComponent },
    { path: 'supplier', component: SupplierComponent },
    { path: 'manager', component: ManagerComponent },
    { path: 'admin', component: AdmindashboardComponent,children: [
        {path: 'register', component: RegisterdashboardComponent, resolve:{
            roles : RoleDropdownResolver
        }},
        {path: 'profile', component: ProfileComponent},
    ]}
];

@NgModule({
    imports : [
        RouterModule.forRoot(appRoute)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {

}