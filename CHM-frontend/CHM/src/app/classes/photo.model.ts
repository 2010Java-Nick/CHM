import { Byte } from "@angular/compiler/src/util";

import { Profile } from './profile.model';

export interface Photo {
    photoId : number,
    photo : any[], 
    profile: Profile
}
