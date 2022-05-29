import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryStatisticsComponent } from './delivery-statistics.component';

describe('DeliveryStatisticsComponent', () => {
  let component: DeliveryStatisticsComponent;
  let fixture: ComponentFixture<DeliveryStatisticsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeliveryStatisticsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
