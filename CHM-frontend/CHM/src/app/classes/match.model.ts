import { Profile } from "../classes/profile.model";

export interface Match {
    matchId: number,
    profile1: Profile,
    profile2: Profile,
    blocked: boolean,
    compatability: number,
    matched: boolean
}
