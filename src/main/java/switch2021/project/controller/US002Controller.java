package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

public class US002Controller {
    private Company company;


    public boolean getUserbyEmail(String email) {
        this.company.getUserByEmail(email);

        return true;
    }

}
