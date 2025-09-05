export interface IUserPersistencePort {
    getUsers(): Promise<User[]>;
    saveUser(user: User): Promise<User>;
}