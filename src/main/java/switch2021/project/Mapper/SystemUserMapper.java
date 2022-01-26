package switch2021.project.Mapper;

import switch2021.project.dto.SystemUserDto;
import switch2021.project.model.SystemUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SystemUserMapper {

//
//    public static SystemUserDto toDto1(SystemUser systemUser){
//        return SystemUserDto.builder().userName(systemUser.getUserName()).activateUser(systemUser.getActivateUserStatus()).build();
//    }
//
//    public static List<SystemUserDto> toDto1(List<SystemUser> systemUserList){
//        return systemUserList.stream().map(SystemUserMapper::toDto).collect(Collectors.toList());
//    }


//    public static SystemUserDto toDto(SystemUser systemUser){
//        return new SystemUserDto(systemUser.getUserName(), systemUser.getEmail(), systemUser.getActivateUserStatus());
//    }

    //percorrer a lista de systemUser e acrescentar a uma nova lista DTO
    public static List<SystemUserDto> toDto(List<SystemUser> systemUserList){
        List<SystemUserDto> systemUserDtoList = new ArrayList<>();
        for (SystemUser systemUser: systemUserList) {
            SystemUserDto systemUserDto = new SystemUserDto(systemUser.getUserName(), systemUser.getEmail(), systemUser.getActivateUserStatus());
            systemUserDtoList.add(systemUserDto);
        }
        return systemUserDtoList;
    }
}
