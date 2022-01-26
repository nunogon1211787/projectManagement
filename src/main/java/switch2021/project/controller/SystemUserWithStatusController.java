package switch2021.project.controller;

import switch2021.project.Mapper.SystemUserMapper;
import switch2021.project.dto.SystemUserDto;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.utils.App;

import java.util.List;

public class SystemUserWithStatusController {

    private final Company company;
    private SystemUserStore systemUserStore;
    private List<SystemUser> systemUserList;


    /**
     * Constructor to UI (with SINGLETON).
     */
    public SystemUserWithStatusController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */

    public SystemUserWithStatusController(Company company) {
        this.company = company;
    }

    public List<SystemUserDto> getListSystemUserWithStatus() {
        this.systemUserStore = this.company.getSystemUserStore();
        this.systemUserList = this.systemUserStore.getSystemUserList();

        List<SystemUserDto> systemUserDto = SystemUserMapper.toDto(this.systemUserList);
        return systemUserDto;
    }
}
