import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LifemailerComponent } from './lifemailer.component';

describe('LifemailerComponent', () => {
  let component: LifemailerComponent;
  let fixture: ComponentFixture<LifemailerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LifemailerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LifemailerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
