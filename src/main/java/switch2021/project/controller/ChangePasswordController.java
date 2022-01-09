package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

public class ChangePasswordController {

    private Company company;

    public ChangePasswordController(Company company, SystemUser user) {
        this.company = company;
    }

    public boolean changePassword(String email, String oldpasswordUI, String newpassword) {


       SystemUser su = this.company.getUserByEmail(email);
       su.updatePassword(oldpasswordUI, newpassword);
       return this.company.saveSystemUser(su);
    }
}


