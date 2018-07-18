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
import { NavbarComponent } from './navbar/navbar.component';
import { RolesService } from './services/roles.service';
import { HttpRequestInterceptor } from './shared/interceptors/httpRequestInterceptors';
import { UserService } from './services/user.service';
import { RoleDropdownResolver } from './route-guards/roles-dropdown-resolve';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdmindashboardComponent,
    HeaderComponent,
    DropdownDirective,
    RegisterdashboardComponent,
    ProfileComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [RolesService, UserService,
    // Adding request header interceptors.
    {
      provide:HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true
    }, RoleDropdownResolver],
  bootstrap: [AppComponent]
})
export class AppModule { }
