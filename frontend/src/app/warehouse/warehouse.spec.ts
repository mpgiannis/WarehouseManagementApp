import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehouseC } from './warehouse';

describe('Warehouse', () => {
  let component: WarehouseC;
  let fixture: ComponentFixture<WarehouseC>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WarehouseC]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WarehouseC);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
