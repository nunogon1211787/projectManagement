package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.UserProfileStore;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

public class ProfileRequestController {

    /**
     * Attributes
     **/

    private Company company;
    private Request request;

    /**
     * Constructor to UI (with SINGLETON)
     **/

    public ProfileRequestController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     **/

    public ProfileRequestController(Company company) {
        this.company = company;
    }

    /**
     * Method
     **/

    public Request createProfileRequest(String email, String nameProfile) {

        UserProfile profRequest = this.company.getUserProfileStore().getUserProfile(nameProfile);
        SystemUser user = this.company.getSystemUserStore().getUserByEmail(email);
        return this.request = this.company.getRequestStore().createProfileRequest(profRequest, user);
    }

    public boolean saveRequest() {
        return this.company.getRequestStore().saveRequest(this.request);
    }

}
