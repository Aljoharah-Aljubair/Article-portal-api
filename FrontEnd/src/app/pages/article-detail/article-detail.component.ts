import { Component, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleService } from 'src/app/service/article/article.service';
import { CommentService } from 'src/app/service/comment/comment.service';
import { Article } from 'src/app/classes/article';
import { Comment } from 'src/app/classes/comment';
import { AuthService } from 'src/app/service/auth/auth.service';
import { Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.css']
})
export class ArticleDetailComponent implements OnInit {
  article!: Article ;
  comments: Comment[] = [];
  newComment: string = '';
  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService,
    private commentService: CommentService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  isAdmin: boolean = false;
  isLoggedIn: boolean = false;
  private subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.authService.isAdmin().subscribe(status => {
      this.isAdmin = status;
    });

    this.subscriptions.push(
      this.authService.isLoggedIn().subscribe(status => {
        this.isLoggedIn = status;
      })
    );
    this.route.params.subscribe(params => {
      const id = +params['id'];  // Convert the ID to a number
      console.log(`Fetching data for article ID: ${id}`); // Add this line
      this.loadArticle(id);
      this.loadComments(id);
    });
  }
  
  loadArticle(id: number): void {
    this.articleService.getArticleById(id).subscribe(
      (article: Article) => {
        this.article = article;
      },
      (error) => console.error('Error loading article:', error)
    );
  }
  
  loadComments(articleId: number): void {
    this.commentService.getCommentsByArticleId(articleId).subscribe(
      (Allcomments: Comment[]) => {
        this.comments = Allcomments;
      },
      (error) => console.error('Error loading comments:', error)
    );
  }
  
  submitComment(): void {
    if (this.newComment.trim() && this.article?.id) {
      this.commentService.addComment(this.newComment, this.article.id).subscribe(
        (comment: Comment) => {
          this.comments.push(comment);
          this.newComment = '';
          this.snackBar.open("Comment added successfully!", "Ok", { duration: 3000 });
        },
        (error) =>this.snackBar.open("Failed to add comment. Please try again." + error.error.message, "Ok", { duration: 3000 }));
      
    }
  }

  deleteArticle() {
    this.articleService.deleteArticle(this.article.id).subscribe(
      (res) => {
        console.log("Delete successful:", res);
        this.snackBar.open("Article deleted successfully!", "Ok", { duration: 3000 });
        this.router.navigate(['/list-article']); 
      },
      (error) => {
        console.log("Delete !successful:", error);
        const errorMessage = error.error.message ? error.error.message : "Unknown error occurred.";
        this.snackBar.open("Failed to delete the article. Please try again. " + errorMessage, "Ok", { duration: 3000 });
      }
    );
  }
  

  likeArticle() {
    this.article.numberOfLikes += 1;
    this.articleService.likeArticle(this.article).subscribe(response => {
      this.snackBar.open("Thanks for liking this article!", "Ok", { duration: 3000 });
      this.router.navigate(['/list-article']); 

    }, (error) => this.snackBar.open("Failed to register like. Please try again." + error.error.message, "Ok", { duration: 3000 }));
    }
  

  dislikeArticle() {
    this.article.numberOfDislikes += 1;
    this.articleService.dislikeArticle(this.article).subscribe(response => {
      this.snackBar.open("Thanks for your feedback!", "Ok", { duration: 3000 });
      this.router.navigate(['/list-article']); 

    }, (error) => this.snackBar.open("Failed to register dislike. Please try again." + error.error.message, "Ok", { duration: 3000 }));
    }



disable(){
  this.articleService.disableArticle(this.article).subscribe(response => {
    this.snackBar.open("Article has been disabled successfully", "Ok", { duration: 3000 });
    this.router.navigate(['/list-article']); 

    }, (error) => this.snackBar.open("Failed to disable article. Please try again." + error.error.message, "Ok", { duration: 3000 }));
    }

}
