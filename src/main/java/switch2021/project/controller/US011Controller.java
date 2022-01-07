package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

public class US011Controller {

    private Company company;
    private SystemUser user;

    public US011Controller (Company company, SystemUser user) {
        this.company = company;
        this.user = user;
    }

    public boolean changePassword(String email, String oldpasswordUI, String newpassword) {

       this.company.getUserByEmail(email);
       this.user.updatePassword(oldpasswordUI, newpassword);
       this.company.saveSystemUser(user);
       return true;
    }
}


