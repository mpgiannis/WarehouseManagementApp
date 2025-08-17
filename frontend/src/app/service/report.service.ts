import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Report {
  id?: number;
  dateRep: string;
  type: string;
  descriptionReason: string;
  receivedDeliveredBy: string;
  infos: string;
}

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private apiUrl = 'http://localhost:8080/reports';

  constructor(private http: HttpClient) {}

  addReport(report: Report): Observable<Report> {
  return this.http.post<Report>(this.apiUrl, report);
  }
  getAllReports(): Observable<Report[]> {
    return this.http.get<Report[]>(this.apiUrl);
  }



}
