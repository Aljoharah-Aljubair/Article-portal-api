import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatArticleComponent } from './creat-article.component';

describe('CreatArticleComponent', () => {
  let component: CreatArticleComponent;
  let fixture: ComponentFixture<CreatArticleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatArticleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
