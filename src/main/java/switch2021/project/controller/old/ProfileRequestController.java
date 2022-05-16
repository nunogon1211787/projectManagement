package switch2021.project.controller.old;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.Request;
import switch2021.project.model.valueObject.UserProfileID;
import switch2021.project.repositories.UserProfileRepository;

public class ProfileRequestController {

    /**
     * Attributes
     **/
    private UserProfileRepository userProfileRepository;
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
        UserProfileID profileIdRequest = userProfileRepository.findUserProfileByDescription(nameProfile).getUserProfileId();
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
