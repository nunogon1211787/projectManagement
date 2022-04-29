package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.model.SystemUser.SystemUser;

//@Component
public class SystemUserMapper {

    public OutputUserDTO toDto(SystemUser user) {
        OutputUserDTO outDTO = new OutputUserDTO();
        outDTO.userName=user.getUserName().getText();
        outDTO.email=user.getSystemUserId().getEmail().getEmailText();
        outDTO.function=user.getFunction().getText();
        outDTO.photo=user.getPhoto().getExtension();
        outDTO.isActive=user.isActive();

        return outDTO;
    }
}
