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
import { ModalDialogModule } from 'ngx-modal-dialog';
import { PromotionRejectModalComponent } from './modal/promotion-reject-modal/promotion-reject-modal.component';
import { AddPromotionComponent } from './modal/add-promotion/add-promotion.component';
import { SupplierPromotionService } from './services/supplier.promotion.service';
import { BrandPromotionModalComponent } from './modal/brand-promotion-modal/brand-promotion-modal.component';
import { PromotionModalComponent } from './modal/promotion-modal/promotion-modal.component';
import { SkuPromotionModalComponent } from './modal/sku-promotion-modal/sku-promotion-modal.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng2SearchPipeModule } from 'ng2-search-filter'; 
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { GanttChartComponent } from './helper/gantt-chart.component';
import { TruncatePipe } from './helper/truncate.pipe';
import { ServerConfig } from './config/server.config';


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
    BrandPromotionModalComponent,
    PromotionRejectModalComponent,
    AddPromotionComponent,
    PromotionModalComponent,
    SkuPromotionModalComponent,
    GanttChartComponent,
    TruncatePipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    NouisliderModule,
    Ng2SearchPipeModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
    ModalDialogModule.forRoot()
  ],
  //For Modal
  entryComponents: [
    BrandPromotionModalComponent,
    PromotionRejectModalComponent,
    AddPromotionComponent,
    PromotionModalComponent,
    SkuPromotionModalComponent
  ],
  providers: [RolesService, UserService,ServerConfig,
    // Adding request header interceptors.
    {
      provide:HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true
    }, RoleDropdownResolver, RoleGuardService, AuthService, JwtHelper, ProfileDataResolver,
    SupplierPromotionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
