package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    /**
     * Classe de Company que contem listas de projectos e System users
     * Será necessário adicionar aqui novas listas pertencentes a Company, nomedamente a dos System users.
     * Metodos possivelmente necessarios da class Company
     *      * getListOfProjects()
     *      * getListOfSystemUsers()
     **/


    /**
     * Atrubutos da Classe
     **/
    ArrayList<Project> arrayProj = new ArrayList<Project>();
    ArrayList<SystemUser> arraySyUser = new ArrayList<SystemUser>();

    /**
     * Método para adicionar projectos á lista.
     **/
    public boolean add(Project proj) {
        arrayProj.add(proj);
        return true;
    }

    public boolean add(SystemUser syUser) {
        arraySyUser.add(syUser);
        return true;
    }

    /**
     * Getting and Setting Methods
     **/

    public ArrayList<Project> getArrayProj() {
        return arrayProj;
    }

    public ArrayList<SystemUser> getArraySyUser() {
        return arraySyUser;
    }

    /**
     * Método para retornar instancia da lista atraves do indice
     **/
    public Project getProj(int index) {
        return arrayProj.get(index);
    }

    public SystemUser getSyUser(int email) {
        return arraySyUser.get(email);
    }

}