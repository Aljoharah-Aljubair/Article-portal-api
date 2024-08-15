import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ArticleService } from 'src/app/service//article/article.service';
import { Article } from 'src/app/classes/article';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';
import { PaginatedArticleResponse } from 'src/app/classes/paginated-article-response ';
import { ImageProcessingService } from 'src/app/service/image/image-processing.service';
import { map } from 'rxjs';

@Component({
  selector: 'app-list-article',
  templateUrl: './list-article.component.html',
  styleUrls: ['./list-article.component.css']
})
export class ListArticleComponent implements OnInit {
  articles: Article[] = [];
  totalElements: number = 0;
  pageNo: number = 0;
  pageSize: number = 5;

  constructor(private articleService: ArticleService,
    private authService: AuthService,
    private router: Router,
    private imageProcessingService:ImageProcessingService
  ) { }

  ngOnInit(): void {
    this.getArticles(this.pageNo, this.pageSize);
  }

  getArticles(pageNo: number, pageSize: number): void {
    this.articleService.getArticles(pageNo, pageSize)
      .pipe(
        map((response: PaginatedArticleResponse) => {
          response.content = response.content.map((article: Article) => 
            this.imageProcessingService.createImages(article)
          );
          return response;
        })
      )
      .subscribe(
        (response: PaginatedArticleResponse) => {
          this.articles = response.content;
          this.totalElements = response.totalElements;
        },
        (error) => {
          console.error('Error fetching articles', error);
        }
      );
  }
  onPageChange(event: any): void {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getArticles(this.pageNo, this.pageSize);
  }

  viewArticle(id: number): void {
    console.log(`Navigating to article with ID: ${id}`);
    this.router.navigate(['/article', id]);
  }

  getUser(): string {
    return this.authService.getCurrentUsername();
  }

}
