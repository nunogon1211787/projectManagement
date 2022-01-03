package switch2021.project.model;

import java.util.ArrayList;

public class Company {

    /**
     * Classe de Company que contém listas de projectos,  System users e Profiles.
     **/


    /**
     * Atrubutos da Classe
     **/
    ArrayList<Project> arrayProj = new ArrayList<>();
    ArrayList<SystemUser> arraySyUser = new ArrayList<>();
    ArrayList<Profile> arrayProfile = new ArrayList<>();

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
    public ArrayList<Project> getArrayProj() {
        return this.arrayProj;
    }

    public ArrayList<SystemUser> getArraySyUser() {
        return this.arraySyUser;
    }

    public ArrayList<Profile> getArrayProfile() {
        return this.arrayProfile;
    }

    /**
     * Método para retornar instancia/objeto da lista através do index.
     **/
    public Project getProj(int index) {
        return this.arrayProj.get(index);
    }

    public SystemUser getSyUser(int index) {

        ArrayList<SystemUser> clone = new ArrayList<>();

            if (index >= 0) {
                clone = (ArrayList<SystemUser>) this.arraySyUser.clone();
            } else {
                throw new IllegalArgumentException("Illegal index number.");
            }

        return clone.get(index);
    }

    public Profile getProfile(int index) {
        return this.arrayProfile.get(index);
    }

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
    public int searchUserByEmail(String email) {

        int idxUser = -1;
        int listSize = this.arraySyUser.size();

        if(listSize != 0) {

            for (int i = 0; i <listSize; i++) {

                if(this.arraySyUser.get(i).checkEmail(email)){
                    idxUser = i;
                    break;
                }

            }

        }

        return idxUser;
    }

    public ArrayList<SystemUser> searchUsersByProfile(int id) {

        ArrayList<SystemUser> userWithProfile = new ArrayList<>();
        int listSize = this.arraySyUser.size();

        if(listSize != 0) {

            for (int i = 0; i < listSize; i++) {

                if(this.arraySyUser.get(i).checkProfile(id)){
                    userWithProfile.add(this.arraySyUser.get(i));
                }

            }

        }

        return userWithProfile;
    }

    /**
     * Método para receber os dados de um System User.
     */
    public String[] getSystemUserData(SystemUser user){

        String[] personalData = new String[5 + user.countProfile()];

        personalData[0] = user.getUserName();
        personalData[1] = user.getEmail();
        personalData[2] = user.getFunction();
        personalData[3] = user.getPhoto();
        personalData[4] = user.getActivateUser();

        for (int i = 0; i < personalData.length - 5; i++) {

            personalData[i + 5] = user.getAssignedProfile(i);

        }


        return personalData;
    }


}