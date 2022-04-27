package switch2021.project.mapper.old;

import switch2021.project.dto.old.SystemUserWithStatusDto;
import switch2021.project.model.SystemUser.SystemUser;

import java.util.ArrayList;
import java.util.List;

public class SystemUserWithStatusMapper {

    /**
     * Method to change data in to a System User Status DTO List
     **/

        public List<SystemUserWithStatusDto> toDto(List<SystemUser> systemUserList) {
            List<SystemUserWithStatusDto> systemUserWithStatusDtoList = new ArrayList<>();
            for (SystemUser systemUser : systemUserList) {
                SystemUserWithStatusDto systemUserWithStatusDto = new SystemUserWithStatusDto(systemUser.getUserName().getNameF(),
                        systemUser.getSystemUserId().getEmail().getEmail(), systemUser.isActive());
                systemUserWithStatusDtoList.add(systemUserWithStatusDto);
            }
            return systemUserWithStatusDtoList;
        }
    }
