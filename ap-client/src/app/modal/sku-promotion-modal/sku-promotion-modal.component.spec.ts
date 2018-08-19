import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkuPromotionModalComponent } from './sku-promotion-modal.component';

describe('SkuPromotionModalComponent', () => {
  let component: SkuPromotionModalComponent;
  let fixture: ComponentFixture<SkuPromotionModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkuPromotionModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SkuPromotionModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
