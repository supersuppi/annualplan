import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DualmailerComponent } from './dualmailer.component';

describe('DualmailerComponent', () => {
  let component: DualmailerComponent;
  let fixture: ComponentFixture<DualmailerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DualmailerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DualmailerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
