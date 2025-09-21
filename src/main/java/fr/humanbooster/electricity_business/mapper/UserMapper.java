package fr.humanbooster.electricity_business.mapper;

import fr.humanbooster.electricity_business.dto.UserDTO;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;
import fr.humanbooster.electricity_business.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "verificationCode", ignore = true)
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "address", ignore = true)
    User toEntity(UserRegisterDTO userRegisterDTO);

    @Mapping(target = "token", source = "verificationCode")
    UserDTO toDTO(User user);
}
