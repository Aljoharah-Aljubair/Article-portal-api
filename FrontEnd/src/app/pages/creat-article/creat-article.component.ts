import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Article } from 'src/app/classes/article';
import { FileHandel } from 'src/app/classes/file-handle';
import { ArticleService } from 'src/app/service/article/article.service';

@Component({
  selector: 'app-creat-article',
  templateUrl: './creat-article.component.html',
  styleUrls: ['./creat-article.component.css']
})
export class CreatArticleComponent implements OnInit {

  postForm!: FormGroup;

  article:Article={
    title: "", 
     body:"" ,
     authorUsername:"",
     id:0,
     numberOfLikes:0,
     numberOfDislikes:0,
     articleImages:[] 
  }

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private articleService: ArticleService,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.postForm = this.fb.group({
      title: ['', [Validators.required, Validators.maxLength(100)]],
      body: ['', [Validators.required, Validators.maxLength(500)]],
      disabled: [false]
    });
  }

  addArticle(articleForm: NgForm){
    const articleFormData = this.prepareFormData(this.article);

    this.articleService.addArticle(articleFormData).subscribe({
            next: (res) => {
              this.snackBar.open("Article Created Successfully", "Ok", { duration: 3000 });
              this.router.navigateByUrl("list-article");
            },
            error: (error) => {
              this.snackBar.open("Something went wrong: " + error.error.message, "Ok", { duration: 3000 });
            }
          });

  }

  prepareFormData(article:Article):FormData{
    const formData = new FormData();
    formData.append(
      'article',
      new Blob([JSON.stringify(article)],{type:'application/json'})
    );

    for(var i= 0 ; i<article.articleImages.length;i++){
      formData.append(
        'imageFile',
        article.articleImages[i].file,
        article.articleImages[i].file.name
      );
    }
    return formData;
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement | null;
    if (input?.files) {
      const file =input.files[0];
      console.log(file);

      const fileHandel:FileHandel={
        file: file, //left hand-side from FileHandel class , right hand-side from cons file
        url: this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file)
        )
      }
      this.article.articleImages.push(fileHandel);

    }
  }


  removeImages(i:number){
    this.article.articleImages.splice(i,1);

  }
  
}
