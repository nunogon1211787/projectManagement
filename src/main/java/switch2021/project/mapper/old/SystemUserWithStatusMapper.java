package switch2021.project.mapper.old;

import switch2021.project.dto.old.SystemUserWithStatusDto;
import switch2021.project.model.SystemUser.User;

import java.util.ArrayList;
import java.util.List;

public class SystemUserWithStatusMapper {

    /**
     * Method to change data in to a System User Status DTO List
     **/

        public List<SystemUserWithStatusDto> toDto(List<User> userList) {
            List<SystemUserWithStatusDto> systemUserWithStatusDtoList = new ArrayList<>();
            for (User user : userList) {
                SystemUserWithStatusDto systemUserWithStatusDto = new SystemUserWithStatusDto(user.getUserName().getText(),
                        user.getSystemUserId().getEmail().getEmailText(), user.isActive());
                systemUserWithStatusDtoList.add(systemUserWithStatusDto);
            }
            return systemUserWithStatusDtoList;
        }
    }
