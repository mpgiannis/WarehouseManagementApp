import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ImportsExports {
  reportId: number;
  productId: number;
  rackId: number;
  amount: number;
}

@Injectable({
  providedIn: 'root'
})
export class ImportExportService {
  private apiUrl = 'http://localhost:8080/imports_exports';

  constructor(private http: HttpClient) {}

  addImportExport(entry: ImportsExports): Observable<any> {
    return this.http.post(this.apiUrl, entry);
  }
}