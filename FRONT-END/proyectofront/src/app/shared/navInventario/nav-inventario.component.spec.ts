import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavInventarioComponent } from './nav-inventario.component';

describe('NavInventarioComponent', () => {
  let component: NavInventarioComponent;
  let fixture: ComponentFixture<NavInventarioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavInventarioComponent]
    });
    fixture = TestBed.createComponent(NavInventarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
