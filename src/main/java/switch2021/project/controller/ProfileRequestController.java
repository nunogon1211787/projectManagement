package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.Request;
import switch2021.project.model.valueObject.UserProfileId;

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
    public boolean createProfileRequest(String email, String nameProfile) {
        UserProfileId profileIdRequest = this.company.getUserProfileStore().getUserProfile(nameProfile).getUserProfileId();
        SystemUser user = this.company.getSystemUserStore().findSystemUserByEmail(email);
        /*this.request = this.company.getRequestStore().createProfileRequest(profRequest, user);
        return this.request;*/
        return user.createAndSaveProfileRequest(profileIdRequest);
    }
/*
    public boolean saveRequest() {
        return this.company.getRequestStore().addProfileRequest(this.request);
    }*/
}
