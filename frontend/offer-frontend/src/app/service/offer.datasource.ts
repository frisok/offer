import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {OfferTotals, Page, Pageable} from "../domain/model";
import {BehaviorSubject, Observable, of} from "rxjs";
import {OfferService} from "./offer.service";
import {catchError, finalize} from "rxjs/operators";

export class OfferDatasource implements DataSource<OfferTotals> {

  private offerSubject: BehaviorSubject<OfferTotals[]> = new BehaviorSubject<OfferTotals[]>([]);

  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();

  private lengthSubject = new BehaviorSubject<number>(0);
  public length$ = this.lengthSubject.asObservable();


  constructor(private offerService: OfferService) {
    this.offerService.offers$
      .pipe(
        catchError(() => of<Page<OfferTotals>>({content: [], totalPages: 0, totalElements: 0})),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe(data => {
        this.loadingSubject.next(false);
        this.offerSubject.next(data.content);
        this.lengthSubject.next(data.totalElements);
      });

  }

  connect(collectionViewer: CollectionViewer): Observable<OfferTotals[]> {
    return this.offerSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.offerSubject.complete();
    this.loadingSubject.complete();
  }

  loadOffers(pageable: Pageable, name?: string, startDate?: Date, endDate?: Date): void {
    this.loadingSubject.next(true);
    this.offerService.findOfferTotals(pageable, name, startDate, endDate);
  }

}
