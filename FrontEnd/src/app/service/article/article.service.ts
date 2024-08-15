import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Article } from '../../classes/article';
import { PaginatedArticleResponse } from 'src/app/classes/paginated-article-response ';

const BASIC_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('auth_token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  addArticle(data: FormData){
    const headers = this.getAuthHeaders();
    return this.http.post(BASIC_URL + 'article', data, { headers });
  }


  getArticles(pageNo: number, pageSize: number): Observable<PaginatedArticleResponse> {
    const headers = this.getAuthHeaders();
    return this.http.get<PaginatedArticleResponse>(`${BASIC_URL}article?pageNo=${pageNo}&pageSize=${pageSize}`);
  }

  getDisabeledArticlesList(): Observable<Article[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<Article[]>(BASIC_URL + 'article/disabeled', { headers });
  }

  getArticleById(id: number): Observable<Article> {
    return this.http.get<Article>(BASIC_URL + `article/${id}`);
  }

  deleteArticle(id: number): Observable<string> {
    const headers = this.getAuthHeaders();
    return this.http.delete<string>(BASIC_URL + `article/${id}`, { headers });
  }

  likeArticle(article: Article): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.put(BASIC_URL + `article/${article.id}/like`, article, { headers });
  }

  dislikeArticle(article: Article): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.put(BASIC_URL + `article/${article.id}/dislike`, article, { headers });
  }

  disableArticle(article: Article): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.put(BASIC_URL + `article/${article.id}/disable`, article, { headers });
  }

}