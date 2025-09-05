export interface IUserSnsServicePort {
    sendWelcomeNotification(user: User): Promise<void>;
}