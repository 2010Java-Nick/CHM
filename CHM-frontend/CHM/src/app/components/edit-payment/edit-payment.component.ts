import { Component, OnInit, Input } from '@angular/core';
import { Payment } from 'src/app/classes/payment';
import { PaymentService } from 'src/app/services/payment.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-payment',
  templateUrl: './edit-payment.component.html',
  styleUrls: ['./edit-payment.component.css']
})
export class EditPaymentComponent implements OnInit {

  paymentForm: FormGroup;

  @Input()
  newPayment: Payment;

  constructor(private paymentService: PaymentService,
              private router : Router) { }

  ngOnInit(): void {

    this.paymentForm = new FormGroup({
      creditcardNameHolder: new FormControl('',
        [ Validators.pattern('[a-zA-Z]*')]
      ),

      paymentAmount: new FormControl('',
        Validators.pattern('[0-9]*')
      ),

      cvc : new FormControl('',
      [Validators.maxLength(3),
        Validators.pattern('[0-9]*')]),

      creditCardNumber: new FormControl('',
        Validators.pattern('[0-9]*')
      ),
        
      expirationDate: new FormControl('',
      Validators.pattern('^(0[1-9]|1[0-2])\/?\-?([0-9]{4}|[0-9]{2})$')
      )
    })
  }

  onSubmit() {
    const { creditcardNameHolder,  paymentAmount, cvc, creditCardNumber, expirationDate } = this.paymentForm.value;

    this.newPayment = new Payment();
    this.newPayment.creditcardNameHolder = creditcardNameHolder;
    this.newPayment.paymentAmount = paymentAmount;
    this.newPayment.cvc = cvc;
    this.newPayment.creditCardNumber = creditCardNumber;
    this.newPayment.expirationDate = expirationDate;  
    this.newPayment.profile.profileId =  1;

    this.paymentService.createPayment(this.newPayment).subscribe(
      payment => console.log(payment)
    );

    if(this.paymentForm.valid){
      this.paymentService.updatePayment(this.newPayment).subscribe(
        payment => {
           this.router.navigate(['/home']);
         
        }
      );
    }else {
      window.alert("Please fix invalid fields");
    }

  }

  get creditcardNameHolder(): any { return this.paymentForm.get('creditcardNameHolder')};

  get paymentAmount(): any { return this.paymentForm.get('paymentAmount')};

  get cvc() : any { return this.paymentForm.get('cvc')};

  get creditCardNumber() : any { return this.paymentForm.get('creditCardNumber')};

  get expirationDate() : any { return this.paymentForm.get('expirationDate')};

}
