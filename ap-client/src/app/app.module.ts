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
import { ProfileDataResolver } from './route-guards/profile-resolve';
import { NouisliderModule } from 'ng2-nouislider';
import { ProductSelectionModalComponent } from './modal/product-selection-modal/product-selection-modal.component';
import { ModalDialogModule } from 'ngx-modal-dialog';
import { PromotionRejectModalComponent } from './modal/promotion-reject-modal/promotion-reject-modal.component';
import { AddPromotionComponent } from './modal/add-promotion/add-promotion.component';


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
    HomeComponent,
    ProductSelectionModalComponent,
    PromotionRejectModalComponent,
    AddPromotionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    NouisliderModule,
    ModalDialogModule.forRoot()
  ],
  //For Modal
  entryComponents: [
    ProductSelectionModalComponent,
    PromotionRejectModalComponent,
    AddPromotionComponent
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
