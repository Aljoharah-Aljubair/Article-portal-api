import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from 'src/app/classes/comment';

const BASIC_URL = 'http://localhost:8080/';


@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) {}


  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('auth_token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  getCommentsByArticleId(articleId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(BASIC_URL+`article/${articleId}/comment`);
  }

  addComment(content: string, articleId: number): Observable<Comment> {
    const headers = this.getAuthHeaders();
    const commentData = { content };
    return this.http.post<Comment>(BASIC_URL+`article/${articleId}/comment`, commentData,{headers});
  }
}