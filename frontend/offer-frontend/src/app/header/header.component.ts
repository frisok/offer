import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import * as moment from 'moment';
import {DATE_TIME_FORMAT, OfferService} from "../service/offer.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  searchForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private offerService: OfferService) {
  }

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({name: [], start: [], end: []});
  }

  onSearch(): void {

    const searchValues = this.searchForm.getRawValue();

    this.offerService.findOfferTotals({
      pageNumber: 0,
      pageSize: 10,
      sortItem: 'id',
      sortDirection: 'asc'
    }, searchValues['name'], searchValues['start'], searchValues['end']);
  }

}
