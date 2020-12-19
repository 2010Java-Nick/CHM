import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Login } from '../classes/login';
import { Observable, Subject, BehaviorSubject } from '../../../node_modules/rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly loginUrl = environment.loginUrl;

  private jWT = this.retrieveLocalStore();

  private expires!: number;

  isLoggedIn: Subject<boolean> = new BehaviorSubject<boolean>(this.checkLocalStore());
  jWTChange: Subject<string> = new BehaviorSubject<string>(this.jWT);

  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })};

  constructor(private httpClient: HttpClient) { }

  private retrieveLocalStore(): string {
    return (localStorage.getItem(`token`) != null) ? localStorage.getItem(`token`)! : '';
  }

  private checkLocalStore(): boolean {
    if (this.jWT !== '') {
      return true;
    } else { return false; }
  }

  public login(username: string, password: string, rememberMe: boolean): Observable<HttpResponse<string>> {

    rememberMe = ((rememberMe != null) ? true : false);

    const login = new Login(username, password, rememberMe);

    return this.httpClient.post<string>(this.loginUrl, login, { observe: 'response' });

  }

  public setJWT(jWT: string): void {
    this.jWT = jWT;
    console.log(`changed jWT in login service to ` + jWT);
    this.jWTChange.next(jWT);
    localStorage.setItem('auth', jWT);
    if (jWT !== '') {
      console.log(`inside setJWT and logged in correctly`);
      this.isLoggedIn.next(true);
    } else { this.isLoggedIn.next(false); }
  }

  public setExpires(expires: number): void {
    this.expires = expires;
    localStorage.setItem('expires', expires.toString());
  }

  public getJWT(time: number): string {
    if (time > this.expires) {
      this.setJWT(``);
      this.isLoggedIn.next(false);
    }
    return this.jWT;
  }
}
