import {Profile} from '../classes/profile.model';

export class Payment {

    public id: number;
    public profile = {} as Profile;
    public creditcardNameHolder: string;
    public cvc : number;
    public paymentAmount: number;
    public creditCardNumber: number;
    public expirationDate: string;

    constructor(){}
}
