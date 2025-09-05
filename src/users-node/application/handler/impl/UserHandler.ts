import { IUserServicePort } from "../../../domain/api/IUserServicePort";
import { UserRequestDto } from "../../dto/request/UserRequestDto";
import { UserResponseDto } from "../../dto/response/UserResponseDto";
import { UserMapper } from "../../mapper/UserMapper";
import { IUserHandler } from "../IUserHandler";

export class UserHandler implements IUserHandler {

    constructor(private userServicePort: IUserServicePort){}
    
    async getUsers(): Promise<UserResponseDto[]> {
        const users = await this.userServicePort.getUsers();
        return users.map(UserMapper.toResponse);
    }

    async saveUser(userDto: UserRequestDto): Promise<UserResponseDto> {
        const user = await this.userServicePort.saveUser(UserMapper.toDomain(userDto))
        return UserMapper.toResponse(user);
    }
    
}