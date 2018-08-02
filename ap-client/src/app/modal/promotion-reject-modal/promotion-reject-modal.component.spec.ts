import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionRejectModalComponent } from './promotion-reject-modal.component';

describe('PromotionRejectModalComponent', () => {
  let component: PromotionRejectModalComponent;
  let fixture: ComponentFixture<PromotionRejectModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PromotionRejectModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PromotionRejectModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
