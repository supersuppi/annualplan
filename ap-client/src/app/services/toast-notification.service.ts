import {Injectable,ViewContainerRef} from '@angular/core';
import { ToastrService, ToastrConfig } from 'ngx-toastr';

@Injectable()
export class ToastNotificationService {

    constructor(private toastr: ToastrService) {
    }

    showSuccess(msg:string) {
        this.toastr.success(msg,'', {
          timeOut: 4000,
          positionClass: 'toast-top-center'
        });
      }
    
    showError(msg:string) {
        this.toastr.error(msg,'', {
          timeOut: 4000,
          positionClass: 'toast-top-center'
        });
      }
    
    showWarning(msg:string) {
        this.toastr.warning(msg,'', {
          timeOut: 3000,
          positionClass: 'toast-top-center'
        });
      }
    
    showInfo(msg:string) {
        this.toastr.info(msg,'', {
          timeOut: 3000,
          positionClass: 'toast-top-center'
        });
      }
 
}