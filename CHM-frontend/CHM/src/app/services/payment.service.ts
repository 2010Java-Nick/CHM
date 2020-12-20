import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders,  } from '@angular/common/http'
import { Payment } from 'src/app/classes/payment'
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  public PAYMENT_URL = "http://localhost:9091/payment"

  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })};

  constructor(private httpClient: HttpClient) { }

  /*
  *  post method for creating  a payment:
  */
  public createPayment (payment : Payment) : Observable<number> {
    return this.httpClient.post<number>(this.PAYMENT_URL, JSON.stringify(payment), this.httpOptions)
    .pipe(
      catchError(this.handleError)
    );
  }
  /*
  *  Patch method for updating a payment:
  */
 public updatePayment(payment : Payment): Observable<Payment> {

  return this.httpClient.patch<Payment>(this.PAYMENT_URL, JSON.stringify(payment), this.httpOptions);
}

/*
  *  Get method for reading payment by Id:
  */
 public readPayment(paymentId: number): Observable<Payment> {

  const readUrl = `${this.PAYMENT_URL}/${paymentId}`;

  return this.httpClient.get<Payment>(readUrl, this.httpOptions);
}

/*
  *  Get method for reading payment by profileId:
  */
 public readPaymentByProfileId(profileId: number): Observable<Payment> {

  const readUrl = `${this.PAYMENT_URL}/profile/${profileId}`;

  return this.httpClient.get<Payment>(readUrl, this.httpOptions);
}

/*
*  Get method for reading all payments:
*/
public readAllPayment(): Observable<Payment[]> {

  return this.httpClient.get<Payment[]>(this.PAYMENT_URL);
}

/*
*  Delete method for deleting a payment:
*/
public deleteProfile(paymentId: number): Observable<boolean> {

  const deleteUrl = `${this.PAYMENT_URL}/${paymentId}`;

  return this.httpClient.delete<boolean>(deleteUrl, this.httpOptions);
}

/*
Handling error from backend
*/
private handleError(error: HttpErrorResponse) {
  
  if(`${error.status} == 400`){
      alert("Please fill up the form with valid entry");
      
  }
  // Return an observable with a user-facing error message.
  return throwError(
    'Something bad happened; please try again later.');
}
}
