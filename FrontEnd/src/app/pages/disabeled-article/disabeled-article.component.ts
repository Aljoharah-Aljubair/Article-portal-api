import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Article } from 'src/app/classes/article';
import { ArticleService } from 'src/app/service/article/article.service';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-disabeled-article',
  templateUrl: './disabeled-article.component.html',
  styleUrls: ['./disabeled-article.component.css']
})
export class DisabeledArticleComponent implements OnInit {
  
  articles: Article[] = [];

  constructor( private snackBar: MatSnackBar,
    private articleService: ArticleService,
    private router: Router,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.getDisabeledArticles();
  }


  getDisabeledArticles(): void {
    this.articleService.getDisabeledArticlesList()
      .subscribe(
        data => {
          this.articles=data;}
      );
      console.log(`role= ${localStorage.getItem("user_role")}`);
  }
  viewArticle(id: number): void {
    console.log(`Navigating to article with ID: ${id}`);
    this.router.navigate(['/article', id]);
  }

}
