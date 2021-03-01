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

  private loginUri: string = "/login";

  public authenticate(credentials) {

    const authenticationHeaderValue: string = credentials ? `Basic ${btoa(credentials.username)}:${credentials.password}` : null;
    const headers = new HttpHeaders(authenticationHeaderValue ? {authorization: authenticationHeaderValue} : {});

    this.httpClient.get(`${API_BASE_URL}${this.loginUri}`, {headers: headers})
      .subscribe(response => {
          this.putAuthenticationToSession(authenticationHeaderValue)
        }, error => this.removeAuthenticationFromSession(),
        () => this.notifyAuthenticationChanged());
  }

  public isAuthenticated(): boolean {
    return this.sessionStorage.retrieve(this.authSessionKey) != null;
  }

  public createAuthenticationHeader(): HttpHeaders {
    if (this.isAuthenticated()) {
      return new HttpHeaders({authorization: this.sessionStorage.retrieve(this.authSessionKey)});
    } else {
      return new HttpHeaders({});
    }
  }

  private putAuthenticationToSession(authenticationHeaderValue: string) {
    this.sessionStorage.store(this.authSessionKey, authenticationHeaderValue);
  }

  private removeAuthenticationFromSession() {
    this.sessionStorage.clear(this.authSessionKey);
  }

  private notifyAuthenticationChanged() {

  }

}
