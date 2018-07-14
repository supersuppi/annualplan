import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { AdmindashboardComponent } from "./dashboard/admindashboard/admindashboard.component";

const appRoute : Routes= [
    { path: '', pathMatch: 'full', redirectTo: '/login' },
    { path: 'login', component: LoginComponent },
    { path: 'admin', component: AdmindashboardComponent}
];

@NgModule({
    imports : [
        RouterModule.forRoot(appRoute)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {

}