import { Injectable, Injector, ApplicationRef, ComponentFactoryResolver, EmbeddedViewRef } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class DomService {

    private childComponentRef : any;
    
    constructor(private injector: Injector, private applicationRef: ApplicationRef,
        private componentFactoryResolver : ComponentFactoryResolver) {}

    public appendToParent(parentId:string, child:any) {

        const childRef = this.componentFactoryResolver.resolveComponentFactory(child)
            .create(this.injector);

        this.childComponentRef = childRef;

        this.applicationRef.attachView(childRef.hostView);

        const childDom = (childRef.hostView as EmbeddedViewRef<any>)
            .rootNodes[0] as HTMLElement;

        document.getElementById(parentId).appendChild(childDom);
    }

    public removeComponent() {
        this.applicationRef.detachView(this.childComponentRef.hostView);
        this.childComponentRef.destroy();
    }

}