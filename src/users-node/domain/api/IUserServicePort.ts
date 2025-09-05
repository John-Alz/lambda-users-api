export interface IUserServicePort {
    getUsers(): Promise<User[]>;
    saveUser(user: User): Promise<User>;
}