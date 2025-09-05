import { IUserSnsServicePort } from "../../../domain/api/IUserSnsServicePort";
import { ISnsHnadler } from "../ISnsHandler";

export class SnsHandler implements ISnsHnadler {

    constructor( private userSnsServicePort: IUserSnsServicePort) {}

    async getUserSqsEvent(user: User): Promise<void> {
        console.log("DATA DE HAANDLER: ", {
            name: user.name,
            email: user.email
        });
        await this.userSnsServicePort.sendWelcomeNotification(user);
    }
    
}