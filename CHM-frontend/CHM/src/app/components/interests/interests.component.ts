import { Component, EventEmitter, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Interests } from 'src/app/classes/interests';
import { InterestService } from 'src/app/services/interest.service';

@Component({
  selector: 'app-interests',
  templateUrl: './interests.component.html',
  styleUrls: ['./interests.component.css']
})
export class InterestsComponent implements OnInit {

  interests = ['Dogs','Cats','Snakes','Pigs','Horses','Whales'];
  limit: number = 3;
  checked: number = 0;

  ngOnInit(): void {

  }

  
  checkChanged(interest: any) {
    if (interest.checked){ 
      this.checked++;
    }
    else{
      this.checked--;
    }

    console.log('Interest is '+ interest);
    console.log('Checked is '+ this.checked);
  }

  /* onSubmit(){
    //do something
  } */
}
