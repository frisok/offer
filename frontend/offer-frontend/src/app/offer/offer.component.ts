import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {OfferDatasource} from "../service/offer.datasource";
import {OfferService} from "../service/offer.service";
import {MatPaginator} from "@angular/material/paginator";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.scss']
})
export class OfferComponent implements OnInit, AfterViewInit {

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  displayedColumns: string[] = ['offerId', 'name', 'url', 'payoutTotal', 'receivedTotal'];
  datasource: OfferDatasource;

  constructor(private offerService: OfferService) {
  }

  ngOnInit(): void {
    this.datasource = new OfferDatasource(this.offerService)
    this.datasource.loadOffers({pageNumber: 0, pageSize: 10, sortItem: 'id', sortDirection: 'asc'});
  }

  ngAfterViewInit(): void {
    this.paginator.page
      .pipe(
        tap(() => this.loadOffers())
      )
      .subscribe();
  }

  private loadOffers(): void {
    this.datasource.loadOffers({
      pageNumber: this.paginator.pageIndex,
      pageSize: this.paginator.pageSize,
      sortItem: 'id',
      sortDirection: 'asc'
    });
  }

}
