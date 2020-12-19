import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/classes/user';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})

export class UserService {

  public USER_URL = "http://localhost:9091/user";
  
  constructor(private httpClient : HttpClient) { }

  public createUser(user : User) : Observable<User>  {
    return this.httpClient.post<User>(this.USER_URL, user); 
  }

}
