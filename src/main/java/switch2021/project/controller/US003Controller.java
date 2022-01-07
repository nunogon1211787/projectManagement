package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Profile;
import switch2021.project.model.Request;
import switch2021.project.model.SystemUser;

import java.time.LocalDate;
import java.util.List;

public class US003Controller {

    private Company co;
    private Request req;

    /**
     * Construtor da Classe
     */

    public US003Controller() { this.co = new Company();}

    /**
     * Método Get.
     */

    public Company getCompany() {return this.co;}

    /**
     * Método para ...
     */

    public List<Profile> getProfileList (){

        String type = "System Profile";

        return co.getArrayProfileWithType(type);

    }

    public Request createProfileRequest(LocalDate data, LocalDate time, int profileIdx, String email){

        Profile profRequest = co.getProfile(profileIdx);
        SystemUser user = co.getUserByEmail(email);

        this.req = new Request(data, time, profRequest, user);

        return this.req;

    }

    public boolean saveRequest(){

        return co.saveRequest(this.req);

    }
}
