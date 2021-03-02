import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {API_BASE_URL} from "./offer.service";
import {SessionStorageService} from "ngx-webstorage";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authSessionKey = "auth";

  constructor(private httpClient: HttpClient, private sessionStorage: SessionStorageService) {
  }

  private loginUri: string = "/authenticate";

  public authenticate(username: string, password: string) {

    const authenticationHeaderValue: string = username && password ? 'Basic ' + btoa(username + ':' + password) : null;
    let headers = new HttpHeaders(authenticationHeaderValue ? {Authorization: authenticationHeaderValue} : {});

    this.httpClient.get(`${API_BASE_URL}${this.loginUri}`, {headers: headers})
      .subscribe(response => {
        this.putAuthenticationToSession(authenticationHeaderValue)
      }, error => this.removeAuthenticationFromSession())
  }

  public isAuthenticated(): boolean {
    return this.sessionStorage.retrieve(this.authSessionKey) != null;
  }

  public getAuthenticationHeaderValue(): string {
    return this.sessionStorage.retrieve(this.authSessionKey);
  }

  private putAuthenticationToSession(authenticationHeaderValue: string) {
    console.log('Authenicated succesfully');
    this.sessionStorage.store(this.authSessionKey, authenticationHeaderValue);
  }

  private removeAuthenticationFromSession() {
    console.log('Authenticion removed');
    this.sessionStorage.clear(this.authSessionKey);
  }

}
