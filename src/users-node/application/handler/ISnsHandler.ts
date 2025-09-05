export interface ISnsHnadler {
    getUserSqsEvent(user: User): Promise<void>;
}