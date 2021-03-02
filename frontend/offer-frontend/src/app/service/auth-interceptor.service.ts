import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {API_BASE_URL} from "./offer.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!request || !request.url || (/^http/.test(request.url) && !request.url.startsWith(API_BASE_URL))) {
      return next.handle(request);
    }

    const authenticationHeaderValue: string = this.authService.getAuthenticationHeaderValue();
    if (authenticationHeaderValue) {

      request = request.clone({
        setHeaders: {
          Authorization: authenticationHeaderValue
        }
      });
    }
    return next.handle(request);
  }
}
