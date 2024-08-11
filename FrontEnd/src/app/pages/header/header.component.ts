import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  isAdmin: boolean = false;
  isLoggedIn: boolean = false;
  private subscriptions: Subscription[] = [];

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.subscriptions.push(
      this.authService.isLoggedIn().subscribe(status => {
        this.isLoggedIn = status;
      })
    );
    this.subscriptions.push(
      this.authService.isAdmin().subscribe(status => {
        this.isAdmin = status;
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
}
