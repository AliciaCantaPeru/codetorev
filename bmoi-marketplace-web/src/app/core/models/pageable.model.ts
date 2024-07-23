
export interface IPage<T> {
  content: T[];
  pageable: IPageable;
  totalPages: number;
  last: boolean;
  totalElements: number;
  size: number;
  number: number;
  sort: ISort2;
  numberOfElements: number;
  first: boolean;
  empty: boolean;
}

export interface IContent {
  ubigeo: string;
  departamento: string;
  provincia: string;
  distrito: string;
  estado: boolean;
}

export interface ISort {
  sorted: boolean;
  unsorted: boolean;
  empty: boolean;
}

export interface IPageable {
  sort: ISort;
  offset: number;
  pageSize: number;
  pageNumber: number;
  unpaged: boolean;
  paged: boolean;
}

export interface ISort2 {
  sorted: boolean;
  unsorted: boolean;
  empty: boolean;
}



