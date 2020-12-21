import { Injectable } from '@angular/core';
import { Match } from '../classes/match.model';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { profile } from 'console';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private readonly MATCH_URL = "http://localhost:9091/match";
  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })};

  constructor(private httpClient: HttpClient) { }

  /*
  *  Post method for creating match:
  */
 public createMatch(match: Match): Observable<number> {

  return this.httpClient.post<number>(this.MATCH_URL, JSON.stringify(match), this.httpOptions);
}

/*
*  Get method for reading match by Id:
*/
public readMatch(matchId: number): Observable<Match> {

  const readUrl = `${this.MATCH_URL}/${matchId}`;

  return this.httpClient.get<Match>(readUrl, this.httpOptions);
}

/*
*  Get method for reading all matches:
*/
public readAllMatches(): Observable<Match[]> {

  return this.httpClient.get<Match[]>(this.MATCH_URL);
}

/*
*  Get method for reading all matches:
*/
public readAllPotentialMatcheByProfileId(profileId:number): Observable<Match[]> {

  return this.httpClient.get<Match[]>(`${this.MATCH_URL}/potential/${profileId}`);
}

/*
*  Patch method for updating a match:
*/
public updateMatch(match: Match): Observable<Match> {

  return this.httpClient.patch<Match>(this.MATCH_URL, JSON.stringify(match), this.httpOptions);
}

/*
*  Delete method for deleting a match:
*/
public deleteMatch(matchId: number): Observable<boolean> {

  const deleteUrl = `${this.MATCH_URL}/${matchId}`;

  return this.httpClient.delete<boolean>(deleteUrl, this.httpOptions);
}

}
