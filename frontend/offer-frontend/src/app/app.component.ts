import {Component} from '@angular/core';
import {AuthService} from "./service/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'offer-frontend';

  constructor(private authService: AuthService) {
  }

  authenticated(): boolean {
    return this.authService.isAuthenticated();
  }

}
