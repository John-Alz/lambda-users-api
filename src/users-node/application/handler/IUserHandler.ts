import { UserRequestDto } from "../dto/request/UserRequestDto";
import { UserResponseDto } from "../dto/response/UserResponseDto";

export interface IUserHandler {
    getUsers(): Promise<UserResponseDto[]>;
    saveUser(userDto: UserRequestDto): Promise<UserResponseDto>;
}