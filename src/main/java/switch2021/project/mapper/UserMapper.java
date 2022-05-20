package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.model.SystemUser.User;

@Component
public class UserMapper {

    public OutputUserDTO toDto(User user) {
        OutputUserDTO outDTO = new OutputUserDTO();
        outDTO.userName=user.getUserName().getText();
        outDTO.email=user.getSystemUserId().getEmail().getEmailText();
        outDTO.function=user.getFunction().getText();
        outDTO.photo=user.getPhoto().getExtension();
        outDTO.isActive=user.isActive() ? "True" : "False";

        return outDTO;
    }
}
