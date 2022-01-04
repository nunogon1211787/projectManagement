package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    /**
     * Classe de Company que contém listas de projectos,  System users e Profiles.
     **/


    /**
     * Atrubutos da Classe
     **/
    List<Project> arrayProj = new ArrayList<>();
    List<SystemUser> arraySyUser = new ArrayList<>();
    List<Profile> arrayProfile = new ArrayList<>();

    /**
     * Método para adicionar objetos ás listas.
     **/
    public boolean add(Project proj) {
        this.arrayProj.add(proj);
        return true;
    }

    public boolean add(SystemUser syUser) {
        this.arraySyUser.add(syUser);
        return true;
    }

    public boolean add(Profile profile) {
        this.arrayProfile.add(profile);
        return true;
    }

    /**
     * Getting and Setting Methods
     **/
    public List<Project> getArrayProj() {
        return this.arrayProj;
    }

    public List<SystemUser> getArraySyUser() {
        return this.arraySyUser;
    }

    public List<Profile> getArrayProfile() {
        return this.arrayProfile;
    }

    /**
     * Método para retornar instancia/objeto da lista através do index.
     **/
    public Project getProj(int index) {
        return this.arrayProj.get(index);
    }

    public SystemUser getSyUser(int index) {

        SystemUser user;

            if (index >= 0) {
               user = new SystemUser(this.arraySyUser.get(index));
            } else {
                throw new IllegalArgumentException("Illegal index number.");
            }

        return user;
    }

    public Profile getProfile(int index) { return new Profile(this.arrayProfile.get(index)); }

    /**
     * Método para validar se um projeto já existe (para que consiga associar uma US preciso
     * de validar que o projeto ao qual vou associar a US exista)
         **/
    public boolean checkProjectExists(String projectCode) {

        for (Project proj : this.arrayProj) {
            if (proj.getCode().equalsIgnoreCase(projectCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para validar se um profile existe.
     **/
    public boolean searchProfileById(int id){

        boolean valid = false;

        for (int i = 0; i < this.arrayProfile.size(); i++) {

            if(this.arrayProfile.get(i).isValidId(id)){ valid = true; }

        }

        return valid;
    }

    /**
     * Métodos para localizar um ou mais system user na lista.
     */
    public List<SystemUser> searchUsersByEmail(String email) {

        int listSize = this.arraySyUser.size();
        List<SystemUser> foundUsersList = new ArrayList<>();

        if(listSize != 0) {

            for (int i = 0; i <listSize; i++) {

                if(this.arraySyUser.get(i).isYourEmail(email)){
                    foundUsersList.add(new SystemUser(this.arraySyUser.get(i)));
                }

            }

        }

        return foundUsersList;
    }

    public List<SystemUser> searchUsersByProfile(int id) {

        List<SystemUser> userWithProfile = new ArrayList<>();
        int listSize = this.arraySyUser.size();

        if(listSize != 0) {

            for (int i = 0; i < listSize; i++) {

                if(this.arraySyUser.get(i).isYourProfile(id)){
                    userWithProfile.add(new SystemUser(this.arraySyUser.get(i)));
                }

            }

        }

        return userWithProfile;
    }


}