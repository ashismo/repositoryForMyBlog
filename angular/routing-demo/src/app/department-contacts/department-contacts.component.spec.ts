import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentContactsComponent } from './department-contacts.component';

describe('DepartmentContactsComponent', () => {
  let component: DepartmentContactsComponent;
  let fixture: ComponentFixture<DepartmentContactsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DepartmentContactsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepartmentContactsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
