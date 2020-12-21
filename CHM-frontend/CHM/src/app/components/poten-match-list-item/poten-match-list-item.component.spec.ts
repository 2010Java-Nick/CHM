import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PotenMatchListItemComponent } from './poten-match-list-item.component';

describe('PotenMatchListItemComponent', () => {
  let component: PotenMatchListItemComponent;
  let fixture: ComponentFixture<PotenMatchListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PotenMatchListItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PotenMatchListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
