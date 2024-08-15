import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import { MaterialModule } from './material.module'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CreatArticleComponent } from './pages/creat-article/creat-article.component';
import { LoginComponent } from './auth/login/login.component';
import { ListArticleComponent } from './pages/list-article/list-article.component';
import { MatTableModule } from '@angular/material/table';
import { ArticleDetailComponent } from './pages/article-detail/article-detail.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from './service/auth/auth.service';
import { SignupComponent } from './auth/signup/signup.component';
import { LogoutComponent } from './auth/logout/logout.component';
import { MatRadioModule } from '@angular/material/radio';
import { HeaderComponent } from './pages/header/header.component';
import { DisabeledArticleComponent } from './pages/disabeled-article/disabeled-article.component';
import { MatGridListModule } from '@angular/material/grid-list';

@NgModule({
  declarations: [
    AppComponent,
    CreatArticleComponent,
    LoginComponent,
    ListArticleComponent,
    ArticleDetailComponent,
    SignupComponent,
    LogoutComponent,
    HeaderComponent,
    DisabeledArticleComponent,
    
  ],
  imports: [
    MatRadioModule,
    MatTableModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    MatListModule,
    MatIconModule,
    MatGridListModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
