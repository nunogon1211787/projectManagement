package switch2021.project.controller;

import switch2021.project.dto.RegisterUserDTO;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUserService;

public class RegisterUserController {

    /**
     * Attributes
     **/
    private final Company company;
    private final SystemUserService systemUserService;

    /**
     * Constructor to test (without SINGLETON)
     **/
    public RegisterUserController(Company company, SystemUserService systemUserService) {
        this.company = company;
        this.systemUserService = systemUserService;
    }

    public OutputUserDTO registerUser(RegisterUserDTO dto) {
        return systemUserService.createAndSaveSystemUser(dto);
    }
}
