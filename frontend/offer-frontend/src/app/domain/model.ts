export interface OfferTotals {

  offerId: number;
  name: string;
  url: string;
  payoutTotal: number;
  receivedTotal: number;
}

export interface Pageable {

  pageNumber: number;
  pageSize: number;
  sortItem: string;
  sortDirection: string;
}

export interface Page<T> {

  content: T[];
  totalPages: number;
  totalElements: number;
}


