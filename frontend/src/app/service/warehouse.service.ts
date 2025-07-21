import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Rack } from './rack.service';
import { tap } from 'rxjs/operators';

export interface Warehouse {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  racks: Rack[];
}

@Injectable({
  providedIn: 'root',
})
export class WarehouseService {
  private apiUrl = 'http://localhost:8080/warehouse';

  private warehouses: Warehouse[] = [];

  constructor(private http: HttpClient) {}

  getWarehouses(): Observable<Warehouse[]> {
    if (this.warehouses.length > 0) {
      return of(this.warehouses); 
    } else {
      return this.http.get<Warehouse[]>(this.apiUrl).pipe(
        tap(data => this.warehouses = data) 
      );
    }
  }

  getWarehouseById(id: number): Warehouse | undefined {
    return this.warehouses.find(w => w.id === id);
  }

  updateWarehouse(warehouse: Warehouse): Observable<Warehouse> {
    return this.http.put<Warehouse>(`${this.apiUrl}`, warehouse);
  }


}