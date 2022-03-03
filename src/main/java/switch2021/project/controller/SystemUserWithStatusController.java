package switch2021.project.controller;

import switch2021.project.mapper.SystemUserWithStatusMapper;
import switch2021.project.dto.SystemUserWithStatusDto;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.stores.SystemUserStore;

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
        SystemUserStore sysUserStore = this.company.getSystemUserStore();
        List<SystemUser> systemUserList = sysUserStore.getSystemUsers();
        List<SystemUserWithStatusDto> systemUserWithStatusDtoList = this.mapper.toDto(systemUserList);
        return Collections.unmodifiableList(systemUserWithStatusDtoList);
    }
}
