import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RackC } from './rack';

describe('Rack', () => {
  let component: RackC;
  let fixture: ComponentFixture<RackC>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RackC]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RackC);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
