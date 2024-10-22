import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
 public Username!:string;
  constructor(private authService: AuthService,
    private router: Router,
    private snackBar: MatSnackBar){ }

  ngOnInit(): void {
    this.logoutUser();
  }
  
  logoutUser(): void {
    this.Username=this.authService.getCurrentUsername();
    this.authService.logout();
    this.snackBar.open(`Logged out Successfully! See you again: ${this.Username}`, "Ok", { duration: 2000 });
    this.router.navigate(['list-article']);
  }

}
