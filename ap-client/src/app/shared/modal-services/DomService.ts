import { Injectable, Injector, ApplicationRef, ComponentFactoryResolver, EmbeddedViewRef } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class DomService {

    private childComponentRef : any;
    
    constructor(private injector: Injector, private applicationRef: ApplicationRef,
        private componentFactoryResolver : ComponentFactoryResolver) {}

    public appendToParent(parentId:string, child:any, childConfig?: childConfig) {

        const childRef = this.componentFactoryResolver.resolveComponentFactory(child)
            .create(this.injector);
            
        this.attachConfig(childConfig, childRef);    

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

    private attachConfig(config, componentRef){
        let inputs = config.inputs;
        let outputs = config.outputs;
        for(var key in inputs){
          componentRef.instance[key] = inputs[key];
        }
        for(var key in outputs){
          componentRef.instance[key] = outputs[key];
        }
        
      }

}

interface childConfig{
    inputs:object,
    outputs:object
}