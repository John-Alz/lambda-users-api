
export interface IUserSnsPort {
    sendSns(data: NotificationData): Promise<void>
}