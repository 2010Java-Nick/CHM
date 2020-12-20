import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Payment } from 'src/app/classes/payment'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  public PAYMENT_URL = "http://localhost:9091/payment"

  constructor(private httpClient: HttpClient) { }

  public createPayment (payment : Payment) : Observable<number> {
    return this.httpClient.post<number>(this.PAYMENT_URL, payment);
  }
}
