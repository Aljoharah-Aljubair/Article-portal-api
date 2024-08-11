import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  isSpinning:boolean =false;

  signupForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService:AuthService,
    private router: Router,
    private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      username: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  register(){
    console.log(this.signupForm.value);
  }
  onSubmit(): void {
    if (this.signupForm.valid) {
      console.log('Form Submitted', this.signupForm.value);
      this.isSpinning = true; 
  
      this.authService.register(this.signupForm.value).subscribe({
        next: (res) => {
          console.log(res);
          this.isSpinning = false; 
          this.snackBar.open('Registration successful!', 'OK', { duration: 3000 });
          this.router.navigate(['/login']);
        },
        error: (error) => {
          this.isSpinning = false; 
          const errorMessage = error.error?.message || 'An unexpected error occurred';
          this.snackBar.open(errorMessage, 'Error', { duration: 3000 });
        }
      });
    }
  }
    
}
