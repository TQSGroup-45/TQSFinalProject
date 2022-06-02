import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryTimeBarchartComponent } from './deliveryTime-barchart.component';

describe('DeliveryTimesBarchartComponent', () => {
  let component: DeliveryTimeBarchartComponent;
  let fixture: ComponentFixture<DeliveryTimeBarchartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeliveryTimeBarchartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryTimeBarchartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
