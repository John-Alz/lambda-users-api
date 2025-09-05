import { IUserServicePort } from "../api/IUserServicePort";
import { IUserPersistencePort } from "../spi/IUserPersistencePort";

export class UserUseCase implements IUserServicePort {
  constructor(private userPersistencePort: IUserPersistencePort) {}

  async getUsers(): Promise<User[]> {
    return await this.userPersistencePort.getUsers();
  }

  async saveUser(user: User): Promise<User> {
    return await this.userPersistencePort.saveUser(user);
  }
}
