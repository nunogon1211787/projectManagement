package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {

    /**
     * Atributos da Classe
     **/

    List<Project> arrayProj = new ArrayList<>();
    List<SystemUser> arraySyUser = new ArrayList<>();
    List<Profile> arrayProfile = new ArrayList<>();
    List<Request> arrayRequest = new ArrayList<>();

    public Company() {
    }

    /** Metodo create de Projectos (Paulo) **/

    public Project createProject(String code, String name, String description, String customer, String typology,
                                 List<String> businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        return new Project(code,name, description, customer, typology, businessSector,
                                    startDate,numberOfSprints, budget);

    }

    /**
     * Método para adicionar objetos ás listas.
     **/

    public boolean add(Project proj) {
        arrayProj.add(proj);
        return true;
    }

    public boolean add(SystemUser syUser) {
        this.arraySyUser.add(syUser);
        return true;
    }

    public boolean add(Profile profile) {

        //Check empty fields on name and type
        if(profile.getName().trim().isEmpty() || profile.getType().trim().isEmpty()){
            return false;
        }

        //Check if the profile type is valid
        if(!profile.getType().equalsIgnoreCase("SYSTEMUSER") && !profile.getType().equalsIgnoreCase("SPECIALPROFILE")){
            return false;
        }

        //Check if profile already exist
        for (Profile up : this.arrayProfile){
            if(up.equals(profile)){
                return false;
            }
        }
        this.arrayProfile.add(profile);
        return true;
    }

    public boolean add(Request request) {
        this.arrayRequest.add(request);
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

    public Project getProj(int index) {
        return arrayProj.get(index);
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

    public SystemUser getUserById(String email) {

        SystemUser user = null;

        for (int i = 0; i < this.arraySyUser.size(); i++) {
            if(this.arraySyUser.get(i).isYourEmail(email)){
                user = this.arraySyUser.get(i);
                break;
            }
        }

        return user;
    }

    public Profile createProfile(String name, String type) {
        return new Profile(generateNewProfileID(), name, type);
    }

    private int generateNewProfileID() {
        int lastIndex = 0;
        for (Profile profile : this.arrayProfile) {
            lastIndex = Math.max(profile.getId(), lastIndex);
        }
        return lastIndex + 1;
    }

    public Profile getProfile(int index) {
        return new Profile(this.arrayProfile.get(index));
    }

    /**
     * Método para validar se um projeto já existe (para que consiga associar uma US preciso
     * de validar que o projeto ao qual vou associar a US exista)
     **/
    public boolean checkProjectExists(String code) {

        for (Project proj : arrayProj) {
            if (proj.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para validar se um profile existe.
     **/

    public boolean searchProfileById(int id) {

        boolean valid = false;

        for (int i = 0; i < this.arrayProfile.size(); i++) {

            if (this.arrayProfile.get(i).isValidId(id)) {
                valid = true;
            }

        }

        return valid;
    }

    /**
     * Métodos para localizar um ou mais system user na lista.
     */

    public List<SystemUser> searchUsersByEmail(String email) {

        int listSize = this.arraySyUser.size();
        List<SystemUser> foundUsersList = new ArrayList<>();

        if (listSize != 0) {

            for (int i = 0; i < listSize; i++) {

                if (this.arraySyUser.get(i).isYourEmail(email)) {
                    foundUsersList.add(new SystemUser(this.arraySyUser.get(i)));
                }

            }

        }

        return foundUsersList;
    }

    public List<SystemUser> searchUsersByProfile(int id) {

        List<SystemUser> userWithProfile = new ArrayList<>();
        int listSize = this.arraySyUser.size();

        if (listSize != 0) {

            for (int i = 0; i < listSize; i++) {

                if (this.arraySyUser.get(i).isYourProfile(id)) {
                    userWithProfile.add(new SystemUser(this.arraySyUser.get(i)));
                }

            }

        }

        return userWithProfile;
    }

    /**
     * Método para procurar um projeto com um dado código
     */

    public Project searchProject(String codeX) {
        int count = 0;
        for (int i = 0; i < this.arrayProj.size(); i++) {

            if (Objects.equals(this.getProj(i).getCode(), codeX)) {
                break;
            }
            count++;
        }
        return getProj(count);
    }

    /**
     * Método para gravar informação editada de um projeto por cima INCOMPLETO
     */

    public void overrideProject(Project x, String name, LocalDate startDate, LocalDate endDate, int numberOfSprints, String statusDescription, int sprintDuration) {

        x.saveProject(name, startDate, endDate, numberOfSprints);
//        x.changeSprintDuration(sprintDuration);
//        x.changeStatus(statusDescription);
    }

    /**
     * Método para get lista de ID de todos os projects
     */

    public String[] getProjectIDList() {
        String[] lista = new String[arrayProj.size()];

        for (int i = 0; i < this.arrayProj.size(); i++) {

            lista [i] = this.getProj(i).getCode();
        }
        return lista;
    }

    /**
     * Method to Update User's List
     */

    public void updateUserList () {

        for (SystemUser systemUser:arraySyUser) {
            //if (systemUser.getuser )
        }
    }
}