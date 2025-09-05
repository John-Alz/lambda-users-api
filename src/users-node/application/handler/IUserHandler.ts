import { UserRequestDto } from "../dto/request/UserRequestDto";
import { UserResponseDto } from "../dto/response/UserResponseDto";

export interface IUserHandler {
    getUsers(): UserResponseDto[];
    saveUser(userDto: UserRequestDto): UserResponseDto;
}