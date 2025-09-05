import { IUserSnsServicePort } from "../api/IUserSnsServicePort";
import { IUserSnsPort } from "../spi/IUserSnsPort";

export class NotificationUseCase implements IUserSnsServicePort {

    constructor(private userSnsPort: IUserSnsPort){}


    async sendWelcomeNotification(user: User): Promise<void> {
        console.log("DATA DE USECASE USER: ", {
            name: user.name,
            email: user.email
        });
        
        const subject = `¡Bienvenido, ${user.name}!`;
        const message = `Hola ${user.name},\n\nTu registro ha sido completado exitosamente.`;

        const notification: NotificationData = {subject, message};

        await this.userSnsPort.sendSns(notification)
        console.log(`Notificación de bienvenida preparada para el usuario: ${user.name}`);
    }

    

}