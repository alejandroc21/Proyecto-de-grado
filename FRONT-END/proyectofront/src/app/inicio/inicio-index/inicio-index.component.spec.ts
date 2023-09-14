import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InicioIndexComponent } from './inicio-index.component';

describe('InicioIndexComponent', () => {
  let component: InicioIndexComponent;
  let fixture: ComponentFixture<InicioIndexComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InicioIndexComponent]
    });
    fixture = TestBed.createComponent(InicioIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
