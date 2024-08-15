import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisabeledArticleComponent } from './disabeled-article.component';

describe('DisabeledArticleComponent', () => {
  let component: DisabeledArticleComponent;
  let fixture: ComponentFixture<DisabeledArticleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisabeledArticleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisabeledArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
