import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdmindashboardComponent } from './dashboard/admindashboard/admindashboard.component';
import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './header/header.component';
import { DropdownDirective } from './shared/dropdown.directive';
import { RegisterdashboardComponent } from './dashboard/admindashboard/registerdashboard/registerdashboard.component';
import { ProfileComponent } from './profile/profile.component';
import { RolesService } from './services/roles.service';
import { HttpRequestInterceptor } from './shared/interceptors/httpRequestInterceptors';
import { UserService } from './services/user.service';
import { RoleDropdownResolver } from './route-guards/roles-dropdown-resolve';
import { SupplierComponent } from './supplier/supplier.component';
import { ManagerComponent } from './manager/manager.component';
import { HomeComponent } from './home/home.component';
import { RoleGuardService } from './route-guards/auth-guard-roles';
import { AuthService } from './services/auth.service';
import { JwtHelper } from './helper/JWTHelper';
<<<<<<< HEAD
import { ProfileDataResolver } from './route-guards/profile-resolve';
=======
import { Ng2SmartTableModule } from 'ng2-smart-table'; // 3rd party table
>>>>>>> 89ca9b0121bef3d75216eac04da736691b6d11d7

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdmindashboardComponent,
    HeaderComponent,
    DropdownDirective,
    RegisterdashboardComponent,
    ProfileComponent,
    SupplierComponent,
    ManagerComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    Ng2SmartTableModule
  ],
  providers: [RolesService, UserService,
    // Adding request header interceptors.
    {
      provide:HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true
    }, RoleDropdownResolver, RoleGuardService, AuthService, JwtHelper, ProfileDataResolver],
  bootstrap: [AppComponent]
})
export class AppModule { }
