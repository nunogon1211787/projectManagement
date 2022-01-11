package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

public class NonRegisteredUserActivationController {
    private Company company;

    public NonRegisteredUserActivationController(Company company) {
        this.company = company;
    }

    public boolean getUserbyEmail (String email) {
        this.company.getUserByEmail(email);

        return true;
    }

}
