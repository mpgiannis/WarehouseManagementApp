
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Stock {
  id: number;
  productId: number;
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class StockService {

  private apiUrl = 'http://localhost:8080/stock';

  constructor(private http: HttpClient) { }

  getStock(): Observable<Stock[]> {
    return this.http.get<Stock[]>(this.apiUrl);
  }
  getStockByRackId(rackId: number): Observable<Stock[]> {
  return this.http.get<Stock[]>(`${this.apiUrl}/rack/${rackId}`);
}
}