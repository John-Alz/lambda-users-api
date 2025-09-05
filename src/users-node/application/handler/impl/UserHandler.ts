import { IUserServicePort } from "../../../domain/api/IUserServicePort";
import { UserRequestDto } from "../../dto/request/UserRequestDto";
import { UserResponseDto } from "../../dto/response/UserResponseDto";
import { UserMapper } from "../../mapper/UserMapper";
import { IUserHandler } from "../IUserHandler";

export class UserHandler implements IUserHandler {

    constructor(private userServicePort: IUserServicePort){}
    
    getUsers(): UserResponseDto[] {
        return this.userServicePort.getUsers().map(UserMapper.toResponse);
    }

    saveUser(userDto: UserRequestDto): UserResponseDto {
        const user = this.userServicePort.saveUser(UserMapper.toDomain(userDto))
        return UserMapper.toResponse(user);
    }
    
}