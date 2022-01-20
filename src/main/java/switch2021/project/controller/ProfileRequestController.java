package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.UserProfileStore;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

public class ProfileRequestController {

    private Company company;
    private Request request;
    private UserProfileStore upStore;

    /**
     * Construtor da Classe
     */

    public ProfileRequestController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Método Get.
     */

    public Company getCompany() {return this.company;}

    /**
     * Método para ...
    */

    public List<UserProfile> getProfileList (){

        this.upStore= this.company.getUserProfileStore();
        return this.upStore.getUserProfileList();

    }

    public Request createProfileRequest(String email,String nameProfile){

        UserProfile profRequest = this.company.getUserProfileStore().getUserProfile(nameProfile);
        SystemUser user = this.company.getSystemUserStore().getUserByEmail(email);

        this.request = this.company.getRequestStore().createProfileRequest(profRequest,user);

        return this.request;

    }

    public boolean saveRequest(){
        return this.company.getRequestStore().addProfileRequest(this.request);
    }

}
