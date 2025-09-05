import { SendMessageCommand, SQSClient } from "@aws-sdk/client-sqs";
import { IUserSqsPort } from "../../../domain/spi/IUserSqsPort";

export class UserSqsAdapter implements IUserSqsPort {
  private sqsClient: SQSClient;
  private queueUrl: string =
    "https://sqs.us-east-1.amazonaws.com/178220864854/user-events-queue";

  constructor() {
    this.sqsClient = new SQSClient({ region: "us-east-1" });
  }

  async sendSqs(user: User): Promise<void> {
    console.log("Entro a enviar sqs.")
    if (!this.queueUrl) {
      console.error("SQS Queue URL no está configurada.");
      throw new Error("SQS_QUEUE_URL environment variable is not set.");
    }
    try {
      const messageBody = JSON.stringify({
        id_usuario: user.id,
        name: user.name,
        email: user.email,
      });

      console.log("Data enviada a sqs: " + messageBody)

      const command = new SendMessageCommand({
        QueueUrl: this.queueUrl,
        MessageBody: messageBody,
      });

      await this.sqsClient.send(command);
      console.log(
        `Evento de usuario creado enviado a SQS para el usuario ID: ${user.id}`
      );
    } catch (error) {
      console.error("Error al enviar el evento de usuario a SQS:", error);
      throw error;
    }
  }
}
