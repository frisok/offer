import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private authService: AuthService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({username: [], password: []});
  }

  onLogIn(): void {
    const loginValues = this.loginForm.getRawValue();
    this.authService.authenticate(loginValues['username'], loginValues['password']);
  }

}
