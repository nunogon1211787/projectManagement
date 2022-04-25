package switch2021.project.mapper;

import switch2021.project.dto.OutputUserDTO;
import switch2021.project.model.SystemUser.SystemUser;

public class SystemUserMapper {

    public OutputUserDTO toDto(SystemUser user) {
        OutputUserDTO systemUserDTO = new OutputUserDTO();
        systemUserDTO.userName=user.getUserName().getNameF();
        systemUserDTO.email=user.getSystemUserId().getEmail().getEmail();

        return systemUserDTO;
    }
}
