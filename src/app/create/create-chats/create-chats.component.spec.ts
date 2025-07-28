import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateChatsComponent } from './create-chats.component';

describe('CreateChatsComponent', () => {
  let component: CreateChatsComponent;
  let fixture: ComponentFixture<CreateChatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateChatsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateChatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
