import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PotenMatchListComponent } from './poten-match-list.component';

describe('PotenMatchListComponent', () => {
  let component: PotenMatchListComponent;
  let fixture: ComponentFixture<PotenMatchListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PotenMatchListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PotenMatchListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
