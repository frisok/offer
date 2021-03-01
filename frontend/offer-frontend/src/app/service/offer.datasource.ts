import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {OfferTotals, Pageable} from "../domain/model";
import {BehaviorSubject, Observable, of} from "rxjs";
import {OfferService} from "./offer.service";
import {catchError} from "rxjs/operators";

export class OfferDatasource implements DataSource<OfferTotals> {

  private offerSubject: BehaviorSubject<OfferTotals[]> = new BehaviorSubject<OfferTotals[]>([]);

  constructor(private offerService: OfferService) {
  }

  connect(collectionViewer: CollectionViewer): Observable<OfferTotals[]> {
    return this.offerSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.offerSubject.complete();
  }

  loadOffers(pageable: Pageable, name?: string, startDate?: Date, endDate?: Date): void {
    this.offerService.findOfferTotals(pageable, name, startDate, endDate)
      .pipe(
        catchError(() => of([])),
      )
      .subscribe(data => {
      data.content.forEach(d => console.log(d.name));
      this.offerSubject.next(data.content);
    });
  }

}
