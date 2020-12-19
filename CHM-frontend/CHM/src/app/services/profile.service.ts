import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Profile } from '../classes/profile.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private readonly PROFILE_URL = "http://localhost:9091/profile";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })};

  constructor(private httpClient: HttpClient) { }


  /*
  *  Post method for creating profile:
  */
  public createProfile(profile: Profile): Observable<number> {

    return this.httpClient.post<number>(this.PROFILE_URL, JSON.stringify(profile), this.httpOptions);
  }

  /*
  *  Get method for reading profile by Id:
  */
  public readProfile(profileId: number): Observable<Profile> {

    const readUrl = `${this.PROFILE_URL}/?id=${profileId}`;

    return this.httpClient.get<Profile>(readUrl, this.httpOptions);
  }

  /*
  *  Get method for reading all profiles:
  */
  public readAllProfiles(): Observable<Profile[]> {

    return this.httpClient.get<Profile[]>(this.PROFILE_URL);
  }

  /*
  *  Patch method for updating a profile:
  */
  public updateProfile(profile: Profile): Observable<Profile> {

    return this.httpClient.patch<Profile>(this.PROFILE_URL, JSON.stringify(profile), this.httpOptions);
  }

  /*
  *  Delete method for deleting a profile:
  */
  public deleteProfile(profileId: Profile): Observable<boolean> {

    const deleteUrl = `${this.PROFILE_URL}/?id=${profileId}`;

    return this.httpClient.delete<boolean>(deleteUrl, this.httpOptions);
  }
}
