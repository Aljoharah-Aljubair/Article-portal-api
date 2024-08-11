import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth/auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  isSpinning: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      this.isSpinning = true;
      this.authService.login(this.loginForm.value).subscribe({
        next: (res) => {
          this.isSpinning = false;
          this.snackBar.open(`Logged in Successfully ${this.loginForm.value.username}`, "Ok", { duration: 2000 });
          this.router.navigate(['list-article']);
        },
        error: (error) => {
          this.isSpinning = false;
          const errorMessage = error.error?.message || 'An unexpected error occurred';
          this.snackBar.open(errorMessage, "Error", { duration: 2000 });
        }
      });
    }
  }
}