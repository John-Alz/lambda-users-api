import { UserResponseDto } from "../dto/response/UserResponseDto";

export class UserMapper {
  static toResponse(user: User): UserResponseDto {
    return { id: user.id, name: user.name, email: user.email };
  }

  static toDomain(dto: { name: string; email: string }): User {
    return { id: 0, name: dto.name, email: dto.email };
  }
}