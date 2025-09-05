export interface IUserSqsPort {
    sendSqs(user: User): Promise<void>;
}
