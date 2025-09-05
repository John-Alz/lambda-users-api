import { IUserPersistencePort } from "../../../domain/spi/IUserPersistencePort";

export class UserDynamoAdapter implements IUserPersistencePort {
  
  private users: User[] = [
    { id: 1, name: "Pepe Pérez", email: "juan@example.com" },
    { id: 2, name: "Josefa Gómez", email: "maria@example.com" },
  ];

  getUsers(): User[] {
    return this.users;
  }

  saveUser(user: User): User {
      const newUser = {
        ...user,
        id: this.users.length + 1
      }
      this.users.push(newUser);
      return newUser;
  }
}
