import { PublishCommand, SNSClient } from "@aws-sdk/client-sns";
import { IUserSnsPort } from "../../../domain/spi/IUserSnsPort";

export class UserSnsAdapter implements IUserSnsPort {
  private snsClient: SNSClient;
  private SNS_TOPIC_ARN =
    "arn:aws:sns:us-east-1:178220864854:new-user-notifications";

  constructor() {
    this.snsClient = new SNSClient({ region: "us-east-1" });
  }

  async sendSns(data: NotificationData): Promise<void> {
    console.log("Iniciando envío de notificación a SNS.");

    console.log("DATA SNS: " + JSON.stringify(data));


    if (!this.SNS_TOPIC_ARN) {
      console.error("El ARN del tema de SNS no está configurado en las variables de entorno.");
      throw new Error("SNS_TOPIC_ARN environment variable is not set.");
    }

    try {
      const command = new PublishCommand({
        TopicArn: this.SNS_TOPIC_ARN,
        Subject: data.subject,
        Message: data.message,
      });

      console.log("COMMAND SNS: " + JSON.stringify(command));

      await this.snsClient.send(command);

      console.log(
        `Notificación enviada exitosamente al tema: ${this.SNS_TOPIC_ARN}}`
      );
    } catch (error) {
      console.error("Error al enviar mensaje a SNS:", error);
      throw error;
    }
  }
}
