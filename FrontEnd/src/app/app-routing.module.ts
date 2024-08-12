import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatArticleComponent } from './pages/creat-article/creat-article.component';
import { LoginComponent } from './auth/login/login.component';
import { ListArticleComponent } from './pages/list-article/list-article.component';
import { ArticleDetailComponent } from './pages/article-detail/article-detail.component';
import { SignupComponent } from './auth/signup/signup.component';
import { LogoutComponent } from './auth/logout/logout.component';
import { DisabeledArticleComponent } from './pages/disabeled-article/disabeled-article.component';

const routes: Routes = [
  {path:'creat-article' , component: CreatArticleComponent},
  {path:'list-article',component:ListArticleComponent},
  { path: 'article/:id', component: ArticleDetailComponent },
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path:'register' ,component:SignupComponent},
  {path:'disabeled-article',component:DisabeledArticleComponent},
  {path:'',component:ListArticleComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
