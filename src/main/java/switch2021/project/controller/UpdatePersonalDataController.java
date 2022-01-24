package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.utils.App;

public class UpdatePersonalDataController {

    private Company company;
    private SystemUser user;


    /**
     * Constructor to UI (with SINGLETON).
     */
    public UpdatePersonalDataController(){ this.company = App.getInstance().getCompany();}

    /**
     * Constructor to test (without SINGLETON).
     */
    public UpdatePersonalDataController(Company company) { this.company = company; }


    public SystemUser getUser(String email) {
        return this.user= this.company.getSystemUserStore().getUserByEmail(email); }

    public boolean updateSystemUserData(String username, String function, String photo) {
        return this.user.setAllData(username, function, photo);
    }
}
