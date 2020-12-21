import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Interests } from '../classes/interests';

@Injectable({
  providedIn: 'root'
})

export class InterestService {

  private readonly INTERESTS_URL = 'http://localhost:9091/interest';

  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })}

  constructor(private httpClient : HttpClient) { }

  public createInterests(interests : string)  {

  console.log(interests)
  return this.httpClient.post(this.INTERESTS_URL, interests, this.httpOptions);
    
  }
}
