import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {OfferTotals, Pageable} from "../domain/model";
import {map} from "rxjs/operators";
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  private apiBaseUrl: string = "http://localhost:8081/api"
  private offerTotalsUri: string = "/offer/totals"


  constructor(private httpClient: HttpClient) {
  }

  findOfferTotals(pageable: Pageable, name: string, startDate: Date, endDate: Date): Observable<any> {

    let httpParams = new HttpParams()
      .set('sort', `${pageable.sortItem},${pageable.sortDirection}`)
      .set('size', pageable.pageSize.toString())
      .set('page', pageable.pageNumber.toString());
    if (name) {
      httpParams = httpParams.set('name', name);
    }
    if (startDate) {
      httpParams = httpParams.set('start', moment(startDate). format('YYYY-MM-DD HH:mm:ss'));
    }
    if (endDate) {
      httpParams = httpParams.set('end', moment(endDate). format('YYYY-MM-DD HH:mm:ss'));
    }

    return this.httpClient.get(
      `${this.apiBaseUrl}${this.offerTotalsUri}`,
      {params: httpParams});
  }


}
