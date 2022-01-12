package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

import java.util.List;

public class US004Controller {

    private Company co;

    /**
     * Construtor da Classe
     */

    public US004Controller() { this.co = new Company();}

    /**
     * Método Get.
     */

    public Company getCompany() {return this.co;}

    /**
     * Método para procurar um ou mais Users no domínio.
     */

    /*public List<SystemUser> searchUsers (String name, String email, String function, int state, int[] profileList){

        return co.getSystemUserStore().searchUsers(name, email, function, state, profileList);

    }*/

}
