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
import { PromotionComponent } from "./dashboard/admindashboard/promotion/promotion.component";
import { ManagePromotionComponent } from "./dashboard/admindashboard/promotion/manage-promotion/manage-promotion.component";
import { EditPromotionComponent } from "./dashboard/admindashboard/promotion/edit-promotion/edit-promotion.component";
import { BudgetComponent } from "./budget/budget.component";


const appRoute : Routes= [
    { path: '', pathMatch: 'full', redirectTo: '/login' },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate: [RoleGuardService]},
    {path: 'profile', component: ProfileComponent, canActivate: [RoleGuardService], resolve: {
        profile : ProfileDataResolver
    }},
    { path: 'supplier', component: SupplierComponent, canActivate: [RoleGuardService],data: {expectedRole: 'ROLE_VENDOR'}},
    { path: 'manager', component: ManagerComponent, canActivate: [RoleGuardService],data: {expectedRole: 'ROLE_CM'}},
    { path: 'budget', component: BudgetComponent, canActivate: [RoleGuardService],data: {expectedRole: 'ROLE_VENDOR'}},
    { path: 'admin', component: AdmindashboardComponent,canActivate: [RoleGuardService],
        children: [
            {path: 'register', component: RegisterdashboardComponent, resolve:{
                roles : RoleDropdownResolver
            } },
            {path: 'profile', component: ProfileComponent, resolve: {
                profile : ProfileDataResolver
            }},
            {path: 'promotion/new', component: PromotionComponent},
            {path: 'promotion/edit', component: EditPromotionComponent},
            {path: 'promotion/show', component: ManagePromotionComponent}
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