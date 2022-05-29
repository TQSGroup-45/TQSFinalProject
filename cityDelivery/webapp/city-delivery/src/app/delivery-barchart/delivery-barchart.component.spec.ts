import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryBarchartComponent } from './delivery-barchart.component';

describe('DeliveryBarchartComponent', () => {
  let component: DeliveryBarchartComponent;
  let fixture: ComponentFixture<DeliveryBarchartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeliveryBarchartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryBarchartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
