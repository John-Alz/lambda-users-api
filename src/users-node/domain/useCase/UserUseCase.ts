import { IUserServicePort } from "../api/IUserServicePort";
import { IUserPersistencePort } from "../spi/IUserPersistencePort";
import { IUserSnsPort } from "../spi/IUserSnsPort";
import { IUserSqsPort } from "../spi/IUserSqsPort";

export class UserUseCase implements IUserServicePort {

  constructor(private userPersistencePort: IUserPersistencePort, private userSqsPort: IUserSqsPort, private useSnsPort: IUserSnsPort) {}

  async getUsers(): Promise<User[]> {
    return await this.userPersistencePort.getUsers();
  }

  async saveUser(user: User): Promise<User> {
    const newUser = await this.userPersistencePort.saveUser(user);
    await this.userSqsPort.sendSqs(user)
    return newUser;
  }
}
