package switch2021.project.controller;

import switch2021.project.model.*;


public class ProfileRequestController {

    /**
     * Attributes
     **/

    private final Company company;
    private Request request;


    /**
     * Constructor to test (without SINGLETON)
     **/

    public ProfileRequestController(Company company) {
        this.company = company;
    }

    /**
     * Methods
     **/

    public Request createProfileRequest(String email, String nameProfile) {

        UserProfile profRequest = this.company.getUserProfileStore().getUserProfile(nameProfile);
        SystemUser user = this.company.getSystemUserStore().getUserByEmail(email);
        this.request = this.company.getRequestStore().createProfileRequest(profRequest, user);
        return this.request;
    }

    public boolean saveRequest() {
        return this.company.getRequestStore().saveRequest(this.request);
    }

}
