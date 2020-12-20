import { Component, Input, OnInit } from '@angular/core';
import { Payment } from 'src/app/classes/payment';
import { PaymentService } from 'src/app/services/payment.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  paymentForm: FormGroup;

  @Input()
  newPayment: Payment;

  constructor(private paymentService: PaymentService) { }

  ngOnInit(): void {

    this.paymentForm = new FormGroup({
      creditcardNameHolder: new FormControl(),
      paymentAmount: new FormControl(),
      creditCardNumber: new FormControl(), 
      expirationDate: new FormControl()
    })
  }

  onSubmit() {
    const { creditcardNameHolder,  paymentAmount, creditCardNumber, expirationDate } = this.paymentForm.value;

    this.newPayment = new Payment();
    this.newPayment.creditcardNameHolder = creditcardNameHolder;
    this.newPayment.paymentAmount = paymentAmount;
    this.newPayment.creditCardNumber = creditCardNumber;
    this.newPayment.expirationDate = expirationDate.toString;  
    this.newPayment.profileId =  1;

    this.paymentService.createPayment(this.newPayment).subscribe(
      payment => console.log(payment)
    );

  }

}
