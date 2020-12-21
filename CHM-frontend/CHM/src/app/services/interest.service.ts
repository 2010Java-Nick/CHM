import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Interests } from '../classes/interests';

@Injectable({
  providedIn: 'root'
})

export class InterestService {

  private readonly INTERESTS_URL = "http://localhost:9091/interests";

  constructor(private httpClient : HttpClient) { }

  public createInterests(interests : Interests)  {

    return this.httpClient.post(this.INTERESTS_URL, interests);
    
  }
}
