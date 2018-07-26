import { Injectable } from "@angular/core";
import { DomService } from "./DomService";

@Injectable({
    providedIn:'root'
})
export class ModalService {

    private modalContainerId : string = 'modalContainer';
    private overlayId :string = 'overlay';

    constructor(private domService : DomService) {}


    init (component:any) {
        let componentConfig = {
            inputs: {},
            outputs: {}
        }; 
        
        this.domService.appendToParent(this.modalContainerId, component);
        document.getElementById(this.modalContainerId).className = 'show';
        document.getElementById(this.overlayId).className = 'show';
    }

    destroy() {
        this.domService.removeComponent();
        document.getElementById(this.modalContainerId).className = 'hidden';
        document.getElementById(this.overlayId).className = 'hidden';
    }

}