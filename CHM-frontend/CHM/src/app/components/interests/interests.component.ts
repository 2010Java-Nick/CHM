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

  newInterests: Interests

  interests = [
    {value:false, lable: 'Dogs'},
    {value:false, lable: 'Cats'},
    {value:false, lable: 'Snakes'},
    {value:false, lable: 'Pigs'},
    {value:false, lable: 'Horses'},
    {value:false, lable: 'Hiking'},
    {value:false, lable: 'Swimming'},
    {value:false, lable: 'Running'},
    {value:false, lable: 'Gym'},
    {value:false, lable: 'Climbing'},
    {value:false, lable: 'Cooking'},
    {value:false, lable: 'Baking'},
    {value:false, lable: 'Drawing'},
    {value:false, lable: 'Painting'},
    {value:false, lable: 'Travel'},
    {value:false, lable: 'Dancing'},
    {value:false, lable: 'Movies'},
    {value:false, lable: 'Books'},
    {value:false, lable: 'Camping'},
    {value:false, lable: 'Career'},
    {value:false, lable: 'Gardening'},
    {value:false, lable: 'Games'},
    {value:false, lable: 'Photography'}

  ];
  limit = 3;
  checked = 0;

  constructor(private interestServ: InterestService) {

  }

  ngOnInit(): void {

  }

  public onSelect(event: any, value: boolean) {
    if( event.target.checked ) {
      event.target.value = true;
      this.checked++;
      // make a call to a method to check if we should disable all unchecked checkboxs?
    } else {
      event.target.value = false;
      this.checked--;
    }
    console.log('checked: ' +  event.target.value);
    console.log('Checked is ' + this.checked);
  }

  onSubmit(){

    this.interests.forEach(element => {

      console.log(element);

      if(element.value == true){

        this.newInterests = new Interests(element.lable);

        this.interestServ.createInterests(element.lable!);
      }
    });
  }

}
