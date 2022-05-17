package switch2021.project.controller.old;

import switch2021.project.mapper.old.SystemUserWithStatusMapper;
import switch2021.project.dto.old.SystemUserWithStatusDto;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.User;
import switch2021.project.repositories.UserRepository;

import java.util.Collections;
import java.util.List;

public class SystemUserWithStatusController {

    /**
     * Attributes
     **/

    private final Company company;
    private final SystemUserWithStatusMapper mapper;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public SystemUserWithStatusController(Company company, SystemUserWithStatusMapper mapper) {
        this.company = company;
        this.mapper = mapper;
    }

    /**
     * Method

 Mutable objects are those whose state can be changed. For instance, an array is mutable, but a String is not.
 Mutable class members should never be returned to a caller or accepted and stored directly.
 Doing so leaves you vulnerable to unexpected changes in your class state.
 Instead use an unmodifiable Collection (via Collections.unmodifiableCollection, Collections.unmodifiableList, ...)
 or make a copy of the mutable object, and store or return the copy instead.
     **/

    public List<SystemUserWithStatusDto> getListSystemUserWithStatus() {
        UserRepository sysUserStore = this.company.getSystemUserStore();
        List<User> userList = sysUserStore.findAllSystemUsers();
        List<SystemUserWithStatusDto> systemUserWithStatusDtoList = this.mapper.toDto(userList);
        return Collections.unmodifiableList(systemUserWithStatusDtoList);
    }
}
