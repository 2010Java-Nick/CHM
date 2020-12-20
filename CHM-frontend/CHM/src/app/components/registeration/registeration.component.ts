import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { User } from 'src/app/classes/user';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-registeration',
  templateUrl: './registeration.component.html',
  styleUrls: ['./registeration.component.css']
})
export class RegisterationComponent implements OnInit {

  registrationForm: FormGroup;

  @Input()
  newUser: User;

  constructor(private userServ: UserService) {}

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    });
  }

  onSubmit() {
    const { username, password } = this.registrationForm.value;

    this.newUser = new User();
    this.newUser.username = username;
    this.newUser.password = password;

    this.userServ.createUser(this.newUser).subscribe(
      user => console.log(user)
    ) ;

    
  }
}