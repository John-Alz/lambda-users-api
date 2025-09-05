import { IUserServicePort } from "../api/IUserServicePort";
import { IUserPersistencePort } from "../spi/IUserPersistencePort";

export class UserUseCase implements IUserServicePort {
  constructor(private userPersistencePort: IUserPersistencePort) {}

  getUsers(): User[] {
    return this.userPersistencePort.getUsers();
  }

  saveUser(user: User): User {
    return this.userPersistencePort.saveUser(user);
  }
}
