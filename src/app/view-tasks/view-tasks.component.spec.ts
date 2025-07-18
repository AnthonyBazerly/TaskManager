import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTasksComponent } from './view-tasks.component';

describe('ViewTasksComponent', () => {
  let component: ViewTasksComponent;
  let fixture: ComponentFixture<ViewTasksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ ViewTasksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
