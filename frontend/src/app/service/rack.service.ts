import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './product.service';

export interface Rack {
  id: number;
  description: string;
  warehouseId?: number;
  products?: Product[];
}

@Injectable({
  providedIn: 'root',
})
export class RackService {
  private apiUrl = 'http://localhost:8080/racks';

  constructor(private http: HttpClient) {}

  addRackToWarehouse(rack: { description: string; warehouseId: number }): Observable<Rack> {
    return this.http.post<Rack>(this.apiUrl, rack);
  }
  getRackById(id: number): Observable<Rack> {
    return this.http.get<Rack>(`${this.apiUrl}/${id}`);
  }
}