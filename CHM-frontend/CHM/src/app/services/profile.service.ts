import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Profile } from '../classes/profile.model';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private readonly PROFILE_URL = "http://localhost:9091/profile";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })}

  constructor(private http: HttpClient) { }


  /*
  *  Post method for creating profile:
  */
  
  public createProfile(profile: Profile): Observable<HttpResponse<number>> {

    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      
    

    return this.http.post<HttpResponse<number>>(this.PROFILE_URL, JSON.stringify(profile), this.httpOptions );
  }

  public readAllProfiles(): Observable<Profile[]> {

    return this.http.get<Profile[]>(this.PROFILE_URL);
  }
}
