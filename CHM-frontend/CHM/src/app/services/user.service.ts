import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { User } from 'src/app/classes/user';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})

export class UserService {

  public USER_URL = "http://localhost:9091/user";
  
  constructor(private httpClient : HttpClient) { }

  public createUser(user : User) : Observable<number>  {
    return this.httpClient.post<number>(this.USER_URL, user)
    /* to get error from backend
    // .pipe(
    //   catchError(this.handleError),
    // ) ; 
    */
  }

}
