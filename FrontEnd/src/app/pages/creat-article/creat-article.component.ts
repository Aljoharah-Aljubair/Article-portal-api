import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ArticleService } from 'src/app/service/article/article.service';

@Component({
  selector: 'app-creat-article',
  templateUrl: './creat-article.component.html',
  styleUrls: ['./creat-article.component.css']
})
export class CreatArticleComponent implements OnInit {

  postForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private articleService: ArticleService,
  ) {}

  ngOnInit(): void {
    this.postForm = this.fb.group({
      title: ['', [Validators.required, Validators.maxLength(100)]],
      body: ['', [Validators.required, Validators.maxLength(500)]],
      disabled: [false]
    });
  }

  createArticle() {
    if (this.postForm.valid) {
      this.articleService.createNewArticle(this.postForm.value).subscribe({
        next: (res) => {
          this.snackBar.open("Article Created Successfully", "Ok", { duration: 3000 });
          this.router.navigateByUrl("list-article");  // Navigate to the homepage or articles list
        },
        error: (error) => {
          this.snackBar.open("Something went wrong: " + error.error.message, "Ok", { duration: 3000 });
        }
      });
    } else {
      this.snackBar.open("Please fill in the form correctly.", "Ok", { duration: 3000 });
    }
  }
}
