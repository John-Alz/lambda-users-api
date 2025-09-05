export interface IUserServicePort {
    getUsers(): User[];
    saveUser(user: User): User;
}