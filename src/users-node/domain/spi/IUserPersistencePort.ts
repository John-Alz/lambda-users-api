export interface IUserPersistencePort {
    getUsers(): User[];
    saveUser(user: User): User;
}