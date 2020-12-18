import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/classes/user';



@Injectable({
  providedIn: 'root'
})

export class UserService {

  private readonly USER_URL = "http://localhost:9091/user";
  constructor(private httpClient : HttpClient) { }

  public createUser(user : User)  {
    console.log("Passing user to backend");
    return this.httpClient.post(this.USER_URL, user);
    
  }

}
