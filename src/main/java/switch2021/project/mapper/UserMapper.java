package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.Typology.Typology;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    List<OutputUserDTO> outputUserDTOList;

    public OutputUserDTO toDto(User user) {
        OutputUserDTO outDTO = new OutputUserDTO();
        outDTO.userName=user.getUserName().getText();
        outDTO.email=user.getSystemUserId().getEmail().getEmailText();
        outDTO.function=user.getFunction().getText();
        outDTO.photo=user.getPhoto().getExtension();
        outDTO.isActive=user.isActive();

        return outDTO;
    }

    public List<OutputUserDTO> usersToDto(List<User> userList) {
        this.outputUserDTOList = new ArrayList<>();

        for(User user : userList) {
            OutputUserDTO outUserDTO = toDto(user);
            this.outputUserDTOList.add(outUserDTO);
        }
        return this.outputUserDTOList;
    }
}
