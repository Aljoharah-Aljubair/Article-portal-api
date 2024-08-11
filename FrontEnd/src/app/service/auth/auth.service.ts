import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { JwtAuthResponse } from '../../classes/jwt-auth-response';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
// import { LOCAL_STORAGE , StorageService } from 'ngx-webstorage-service';

const BASIC_URL_AUTH = 'http://localhost:8080/api/auth/';
const BASIC_URL = 'http://localhost:8080/';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isLoggedInSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.checkInitialLoginStatus());
  private isAdminSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.checkInitialAdminStatus());

  constructor(private http: HttpClient, private router: Router) { }

  isLoggedIn(): Observable<boolean> {
    return this.isLoggedInSubject.asObservable();
  }

  isAdmin(): Observable<boolean> {
    return this.isAdminSubject.asObservable();
  }


  register(signupRequest: any) {
    return this.http.post(BASIC_URL_AUTH + 'register', signupRequest);
  }

  login(loginRequest: any): Observable<JwtAuthResponse> {
    return this.http.post<JwtAuthResponse>(BASIC_URL_AUTH + "login", loginRequest)
      .pipe(tap(res =>
        this.UserRole(loginRequest.username, res)));
        
  }

  private UserRole(username: string, authResult: JwtAuthResponse) {
    this.http.get<{ role: string }>(BASIC_URL_AUTH + 'role?username=' + username)
      .subscribe({
        next: response => {
          this.setSession(authResult, username, response.role);
        },
        error: error => {
          console.error('Failed to fetch user role', error);
        }
      });
  }

  private setSession(authResult: JwtAuthResponse, username: string, userRole: string) {
    localStorage.setItem("auth_token", authResult.accessToken);
    localStorage.setItem("current_username", username);
    localStorage.setItem("user_role", userRole);
    this.isLoggedInSubject.next(true);
    this.isAdminSubject.next(this.checkAdminStatus());
  }

  logout(): void {
    this.clearSession();
    this.router.navigate(['login']);
  }

  private clearSession() {
    localStorage.removeItem("auth_token");
    localStorage.removeItem("current_username");
    localStorage.removeItem("user_role");
    this.isLoggedInSubject.next(false);
    this.isAdminSubject.next(false);
  }

  getUserRole(): string {
    return localStorage.getItem("user_role") || '';
  }

  getToken(): string {
    return localStorage.getItem("auth_token") || '';
  }

  getCurrentUsername(): string {
    return localStorage.getItem("current_username") || '';
  }


  private checkInitialLoginStatus(): boolean {
    return false; 
  }

  private checkInitialAdminStatus(): boolean {
    return false; 
  }

  private checkAdminStatus(): boolean {
    var role = localStorage.getItem("user_role")||'';
    console.log(role)
    console.log(role.search("ADMIN")!==-1);
    return role.search("ADMIN") !== -1;
  }

  // isLoggedIn(): boolean {
  //   return localStorage.getItem('auth_token') !== null;
  // }



}