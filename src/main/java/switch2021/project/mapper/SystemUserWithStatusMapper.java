package switch2021.project.mapper;

import switch2021.project.dto.SystemUserWithStatusDto;
import switch2021.project.model.SystemUser;

import java.util.ArrayList;
import java.util.List;

public class SystemUserWithStatusMapper {

    /**
     * Method to change data in to a System User Status DTO List
     **/

        public List<SystemUserWithStatusDto> toDto(List<SystemUser> systemUserList) {
            List<SystemUserWithStatusDto> systemUserWithStatusDtoList = new ArrayList<>();
            for (SystemUser systemUser : systemUserList) {
                SystemUserWithStatusDto systemUserWithStatusDto = new SystemUserWithStatusDto(systemUser.getUserName(),
                        systemUser.getEmail(), systemUser.getActivateUserStatus());
                systemUserWithStatusDtoList.add(systemUserWithStatusDto);
            }
            return systemUserWithStatusDtoList;
        }
    }
