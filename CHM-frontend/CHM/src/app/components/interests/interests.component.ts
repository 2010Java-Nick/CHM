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

  // interests = ['Dogs', 'Cats', 'Snakes', 'Pigs', 'Horses', 'Whales'];
  interests = [
    {value:false, lable: 'Dogs'},
    {value:false, lable: 'Cats'},
    {value:false, lable: 'Snakes'},
    {value:false, lable: 'Pigs'},
    {value:false, lable: 'Horses'},
    {value:false, lable: 'Whales'}
  ];
  limit = 3;
  checked = 0;

  ngOnInit(): void {

  }


  // checkChanged(interest: any) {
  //   if (interest.target.checked){ 
  //     this.checked++;
  //   }
  //   else{
  //     this.checked--;
  //   }

  //   console.log('Interest is '+ interest);
  //   console.log('Checked is '+ this.checked);
  // }


  public onSelect(event: any, value: boolean) {
    if( event.target.checked ) {
      value = true;
      this.checked++;
      // make a call to a method to check if we should disable all unchecked checkboxs?
    } else {
      value = false;
      this.checked--;
    }
    console.log('checked: ' + value);
    console.log('Checked is ' + this.checked);
  }

  public isSelected(value: boolean): boolean {
    if( value ){
      console.log("isSelected was called and was true!")
      return true;
    } else {
      console.log("isSelected was called and was false!")
      return false;
    }
  }
}
