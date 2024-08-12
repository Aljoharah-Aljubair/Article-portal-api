import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private BASIC_URL = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}
  
  public login(username: string, password: string): Observable<any> {
    const credentials = `${username} + ${btoa(password)}`;
    const headers = new HttpHeaders({
      'Authorization': `Basic ${credentials}`
    });
    return this.http.get(`${this.BASIC_URL}/login`, { headers });
  }
}
