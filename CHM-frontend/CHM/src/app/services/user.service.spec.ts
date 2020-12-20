import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule,  HttpTestingController  } from '@angular/common/http/testing';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { UserService } from './user.service';
import { User } from 'src/app/classes/user';


describe('UserService', () => {
  let userServ: UserService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    //TestBed allows us to implement Angular testing module that will contain an instance of our service we are testing
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers : [UserService]
    });

    userServ = TestBed.inject(UserService);

    //mocks the requests and response, no need to call our backend controller
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    //verifies there are no pending requests to be resolved
    httpTestingController.verify();
  })

  it('should be created', () => {
    expect(userServ).toBeTruthy();
  });

  it('should create user', () => {

    const dummyUser :  User = {id : 100, username : "testUser", password : "password"};

    //returning the dummy data when http request is subscribed
    userServ.createUser(dummyUser).subscribe((createdUser) => {
      expect(createdUser).toBe(dummyUser);
    });

    //controller making sure only one request and one mocked response is returned
    const req = httpTestingController.expectOne(userServ.USER_URL);

    expect(req.cancelled).toBeFalsy();
    expect(req.request.responseType).toEqual('json');

    //helps to resolve the request and return response with additional http information
    req.flush(dummyUser);

  })
});
