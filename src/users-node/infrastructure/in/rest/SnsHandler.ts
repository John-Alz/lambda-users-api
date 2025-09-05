import { SQSEvent } from "aws-lambda";
import { SnsHandler } from "../../../application/handler/impl/SnsHandler";
import { NotificationUseCase } from "../../../domain/useCase/NotificationUseCase";
import { UserSnsAdapter } from "../../out/adapter/UserSnsAdapter";

const adapter = new UserSnsAdapter();
const useCase = new NotificationUseCase(adapter);
const handler = new SnsHandler(useCase);

export const getUserBySqsEvent = async (event: SQSEvent) => {
      console.log(`Iniciando procesamiento de ${event.Records.length} evento(s) de SQS.`);

      for (const record of event.Records) {
        try {
            const user = JSON.parse(record.body) as User;
            console.log("RECORDBODY: " + record.body);
            
            console.log("DATA DE HANDLER/REST: ", {
            name: user.name,
            email: user.email
        });
            await handler.getUserSqsEvent(user);
        } catch (error) {
            console.error("ERROR: Falla al procesar un mensaje.", {
                messageId: record.messageId,
                error
            })
            throw error;
        }
      }
        console.log("Todos los eventos fueron procesados exitosamente.");

}