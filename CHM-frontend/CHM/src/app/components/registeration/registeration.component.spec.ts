import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { UserService } from 'src/app/services/user.service';
import { ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { User } from 'src/app/classes/user';
import { RegisterationComponent } from './registeration.component';
import { HttpClientTestingModule,  HttpTestingController  } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { delay} from 'rxjs/operators';

/*
*******INCOMPLETE TEST***********
*/

describe('RegisterationComponent', () => {
  let component: RegisterationComponent;
  let fixture: ComponentFixture<RegisterationComponent>;

  let userServiceStub : Partial<UserService>;

  //create new instance of form builder
  const formBuilder = new FormBuilder();

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      //need to import otherwise gives NullInjectionError
      imports: [HttpClientTestingModule,
                ReactiveFormsModule,
                RouterTestingModule],

      declarations: [ RegisterationComponent ],

      providers : [
        {provide: FormBuilder, useValue : formBuilder},
        {provide: UserService, useValue : userServiceStub}
      ]
    })
    .compileComponents();
   // userService = TestBed.inject(UserService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterationComponent);
    component = fixture.componentInstance;

    //passing fake data to form
    component.registrationForm = formBuilder.group({
      username : "dummy",
      password : "password"
    });
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call createUser and return user', fakeAsync(() => {

    //creating fake data for form
    // const userName = fixture.nativeElement;
    // const password = fixture.nativeElement;

    // expect(userName.querySelector("#username").textContent).toContain("dummyUser");
    // expect(password.querySelector("#password").textContent).toContain("password");

    const dummyUser :  User = {id : 100, username : "testUser", password : "password"};

    //fake API call
    let userService = fixture.debugElement.injector.get(UserService);

    //returns fake responses to API call
    let spy =spyOn(userService, "createUser").and.callFake(() =>{
      return of(dummyUser);
    });

    expect(component.onSubmit).toEqual(dummyUser);



  }));


  it('[username-check] should check username is invalid', () => {
    let username = component.registrationForm.controls['username'];
    expect(username.valid).toBeFalsy();
    expect(username.pristine).toBeTruthy();
    username.setValue('a');
    //expect(username.errors['required']).toBeTruthy();

  })
});
