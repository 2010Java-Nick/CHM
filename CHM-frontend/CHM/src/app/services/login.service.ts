import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Login } from '../classes/login';
import { Observable, Subject, BehaviorSubject } from '../../../node_modules/rxjs';
import { environment } from '../../environments/environment';
import { tokenize } from '@angular/compiler/src/ml_parser/lexer';

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
    let token = '';
    if (localStorage.getItem(`token`) != null) {
      // tslint:disable-next-line: no-non-null-assertion
      token = localStorage.getItem(`token`)!;
    }
    return token;
  }

  private checkLocalStore(): boolean {
    if (this.jWT !== '') {
      return true;
    } else { return false; }
  }

  public login(username: string, password: string, rememberMe: boolean): Observable<HttpResponse<any>> {

    rememberMe = ((rememberMe != null) ? true : false);

    const login = new Login(username, password, rememberMe);

    return this.httpClient.post<any>(this.loginUrl, login, { observe: 'response' });

  }

  public setJWT(jWT: string): void {
    this.jWT = jWT;
    console.log(`changed jWT in login service to ` + jWT);
    this.jWTChange.next(jWT);
    localStorage.setItem('auth', jWT);
    console.log('localStorage.getItem(\'auth\'): ' + localStorage.getItem('auth'));
    if (jWT !== '') {
      console.log(`inside setJWT and logged in correctly`);
      this.isLoggedIn.next(true);
    } else { this.isLoggedIn.next(false); }
  }

  public getJWT(time: number): string {
    if (time > this.expires) {
      this.setJWT(``);
      this.isLoggedIn.next(false);
    }
    return this.jWT;
  }

  public setProfileId(ProfileId: string | null): void {
    console.log('In setProfileId with ProfileId of : ' + ProfileId);
    let id = '';
    if (ProfileId != null){
      // tslint:disable-next-line: no-non-null-assertion
      id = ProfileId!;
    }
    localStorage.setItem('profileId', id);
  }

  public getProfileId(): string {
    let id = '';
    if (localStorage.getItem('profileId') != null){
      // tslint:disable-next-line: no-non-null-assertion
      id = localStorage.getItem('profileId')!;
    }
    return id
  }
}
