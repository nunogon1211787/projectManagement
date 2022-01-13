package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Request;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;

import java.time.LocalDate;
import java.util.List;

public class US003Controller {

    private Company company;
    private Request request;

    /**
     * Construtor da Classe
     */

    public US003Controller() { this.company = new Company();}

    /**
     * Método Get.
     */

    public Company getCompany() {return this.company;}

    /**
     * Método para ...
     */

    /*public List<UserProfile> getProfileList (){

        String type = "System Profile";

        return company.getUserProfileStore().getUserProfileListWithType(type);

    }*/

    public Request createProfileRequest(LocalDate data, LocalDate time, String profileName, String email){

        UserProfile profRequest = company.getUserProfileStore().getProfileByName(profileName);
        SystemUser user = company.getSystemUserStore().getUserByEmail(email);

        this.request = new Request(data, time, profRequest, user);

        return this.request;

    }

    public boolean saveRequest(){

        return company.saveRequest(this.request);

    }
}
