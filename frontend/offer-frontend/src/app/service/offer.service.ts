import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {OfferTotals, Page, Pageable} from "../domain/model";
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  private apiBaseUrl: string = "http://localhost:8081/api";
  private offerTotalsUri: string = "/offer/totals";

  private offersSubject: BehaviorSubject<Page<OfferTotals>> = new BehaviorSubject<Page<OfferTotals>>({
    content: [],
    totalPages: 0,
    totalElements: 0
  });
  public offers$: Observable<Page<OfferTotals>> = this.offersSubject.asObservable();


  constructor(private httpClient: HttpClient) {
  }

  findOfferTotals(pageable: Pageable, name: string, startDate: Date, endDate: Date): void {

    let httpParams = new HttpParams()
      .set('sort', `${pageable.sortItem},${pageable.sortDirection}`)
      .set('size', pageable.pageSize.toString())
      .set('page', pageable.pageNumber.toString());
    if (name) {
      httpParams = httpParams.set('name', name);
    }
    if (startDate) {
      httpParams = httpParams.set('start', moment(startDate).format(DATE_TIME_FORMAT));
    }
    if (endDate) {
      httpParams = httpParams.set('end', moment(endDate).format(DATE_TIME_FORMAT));
    }

    this.httpClient.get<Page<OfferTotals>>(
      `${this.apiBaseUrl}${this.offerTotalsUri}`,
      {params: httpParams})
      .subscribe(page => this.offersSubject.next(page));
  }


}

export const DATE_TIME_FORMAT = 'YYYY-MM-DD HH:mm:ss';
