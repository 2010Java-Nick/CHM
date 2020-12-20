import { Profile } from './profile.model';

export interface User {

    userId: number;
    username: string;
    password: string;
    profile: Profile;
    premium: boolean;
    
}
