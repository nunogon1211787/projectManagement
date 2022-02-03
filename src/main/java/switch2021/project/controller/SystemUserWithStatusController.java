package switch2021.project.controller;

import switch2021.project.mapper.SystemUserWithStatusMapper;
import switch2021.project.dto.SystemUserWithStatusDto;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.stores.SystemUserStore;

import java.util.List;

public class SystemUserWithStatusController {

    /**
     * Attributes
     **/

    private final Company company;
    private SystemUserStore systemUserStore;
    private List<SystemUser> systemUserList;
    private List<SystemUserWithStatusDto> systemUserWithStatusDtoList;
    private SystemUserWithStatusMapper mapper;

    /**
     * Constructor to UI (with SINGLETON)
    **/

//    public SystemUserWithStatusController() {
//        this.company = App.getInstance().getCompany();
//    }

    /**
     * Constructor to test (without SINGLETON)
     **/

    public SystemUserWithStatusController(Company company, SystemUserWithStatusMapper mapper) {
        this.company = company;
        this.mapper = mapper;
    }

    /**
     * Methods
     **/

    public List<SystemUserWithStatusDto> getListSystemUserWithStatus() {
        this.systemUserStore = this.company.getSystemUserStore();
        this.systemUserList = this.systemUserStore.getSystemUsers();
        this.systemUserWithStatusDtoList = this.mapper.toDto(this.systemUserList);
        return systemUserWithStatusDtoList;
    }
}
