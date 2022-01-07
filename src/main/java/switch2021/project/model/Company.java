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
    List<SystemUser> arraySyUser;// = new ArrayList<>();
    List<Profile> arrayProfile;
    List<Request> arrayRequest = new ArrayList<>();

    /**
     * Constructors with data (Ivan)
     **/
    public Company(){
        this.arraySyUser = new ArrayList<>();
        this.arrayProfile = new ArrayList<>();

        arrayProfile.add(new Profile("Visitor","System Profile"));
        arrayProfile.add(new Profile("Administrator","System Profile"));
        /*arrayProfile.add(new Profile(2,"Director","System Profile"));
        arrayProfile.add(new Profile(3,"Project Manager", "Special Profile"));
        arrayProfile.add(new Profile(4, "Product Owner", "Special Profile"));
        arrayProfile.add(new Profile(5, "Scrum Master", "Special Profile"));
        arrayProfile.add(new Profile(6, "Project Team", "Special Profile"));*/
    }

    /** Metodo create de Projectos (Paulo - US005) **/

    public Project createProject(String code, String name, String description, Customer customer, Typology typology,
                                 BusinessSector businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        return new Project(code,name, description, customer, typology, businessSector,
                                    startDate,numberOfSprints, budget);

    }
    /** Metodo create de SystemUsers (Nuno) **/

    public SystemUser createSystemUser(String userName, String email, String password, String function) {
        return new SystemUser(userName, email, password, function, arrayProfile.get(0));
    }

    public SystemUser createSystemUser(String userName, String email, String password, String function, String photo) {
        return new SystemUser(userName, email, password, function,photo, arrayProfile.get(0));
    }

    //Método alterado porque estava com um erro (Joana).

    public boolean validateSystemUser(SystemUser user) {
        if (user == null && this.arraySyUser.contains(user)) {
            return false;
        }
        return true;
    }

    /**
     * Método para adicionar objetos ás listas.
     **/

    public boolean add(Project proj) {
        this.arrayProj.add(proj);
        return true;
    }

    /**
     * Method to save system user data (username, function, photo) in System User List
     */

    public boolean saveSystemUserData(SystemUser user) {

        if (!validateSystemUser(user)){
            return false;
        }else{
            return this.arraySyUser.add(user);
        }
    }

    public boolean addSystemUser(SystemUser syUser) {
        this.arraySyUser.add(syUser);
        return true;
    }

    public boolean add(Profile profile) {

        if(!validateProfile(profile)){
            return false;
        }
        arrayProfile.add(profile);
        return true;
    }

    public boolean add(Request request) {
        //this.arrayRequest.add(request);
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
     * Methods method to get the project from code (Cris US009)
     **/

    public Project getProj(String code) {

        for (Project proj : arrayProj) {
            if (proj.getCode().equalsIgnoreCase(code)) {
                return proj;
            }
        }
        return null;
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

    public SystemUser getUserByEmail(String email) {

        SystemUser user = null;

        for (int i = 0; i < this.arraySyUser.size(); i++) {
            if(this.arraySyUser.get(i).isYourEmail(email)){
                user = this.arraySyUser.get(i);
                break;
            }
        }

        return user;
    }
    public Profile getProfile(int index) {
        return new Profile(arrayProfile.get(index));
    }

    public Profile getProfile(String name) {
        Profile pro = null;
        for (int i = 0; i < arrayProfile.size(); i++) {
            if(getProfile(i).getName() == name) {
                pro = getProfile(i);
                break;
            }
        }
        return pro;
    }
      /**
     * Method to create a new profile (Cris-US013)
     **/

    public Profile createProfile(String name, String type) {
        return new Profile(name, type);
    }

    /**
     * Method to generate a new ID for the profile - begin in number 1 (Cris-US013)
     **/
    private int generateNewProfileID() {
        int lastIndex = 0;
        for (Profile profile : arrayProfile) {
            lastIndex = Math.max(profile.getId(), lastIndex);
        }
        return lastIndex + 1;
    }

    /**
     * Method to validate profile (Cris-US013)
     **/

    private boolean validateProfile(Profile profile) {
        //Check empty fields on name and type
        if(profile.getName().trim().isEmpty() || profile.getType().trim().isEmpty()){
            return false;
        }

        //Check if the profile type is valid
        if(!profile.getType().equalsIgnoreCase("System Profile") && !profile.getType().equalsIgnoreCase("Special Profile")){
            return false;
        }

        //Check if profile already exist
        for (Profile up : arrayProfile){
            if(up.equals(profile)){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to validate if project exists (to associate US i need to validate that codeProject exists) (Cris-US009)
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
     * Método para validar o projeto criado.
     */
    // PAULO FAVOR VERIFICAR ESSE MÉTODO. NÃO EXISTIA E SÓ CRIEI AQUI PARA NÃO DAR ERRO.
    public boolean validateProject(Project proj) {

        return true;
    }

    /**
     * Método para validar se um profile existe.
     **/

    public boolean searchProfileById(int id) {

        boolean valid = false;

        for (int i = 0; i < arrayProfile.size(); i++) {

            if (arrayProfile.get(i).isValidId(id)) {
                valid = true;
            }

        }

        return valid;
    }

    /**
     * Métodos para localizar um ou mais system user na lista.
     */
    public List<SystemUser> searchUsers(String name, String email, String function, int isActive, int [] profileList) {

        int listSize = this.arraySyUser.size();
        List<SystemUser> foundUsersList = new ArrayList<>();

        if (listSize != 0) {

            for (SystemUser systemUser : this.arraySyUser)
                if (systemUser.hasThisData(name, email, function, isActive, profileList)) {
                    foundUsersList.add(new SystemUser(systemUser));
                }

        }

        return foundUsersList;
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
            lista [i] =  this.arrayProj.get(i).getCode();
        }
        return lista;
    }

    /**
     * Method to Validate Allocation (Caroli US007)
     */

    public boolean validateAllocation(SystemUser user, double percentageOfAllocation, LocalDate startDate, LocalDate endDate) {
        double sum = 0;
        boolean msg = false;

        for (int i = 0; i < arrayProj.size(); i++) {
            for (int j = 0; j < arrayProj.get(i).getProjectTeam().size(); j++) {
                if (arrayProj.get(i).getTeamMemberByIndex(j).equals(user) &&
                        arrayProj.get(i).getTeamMemberByIndex(j).checkAllocationPeriod(startDate, endDate)) {
                        sum = +arrayProj.get(i).getTeamMemberByIndex(j).getPercentageOfAllocation();
                }
            }
        }
        if(sum + percentageOfAllocation < 1){
            msg = true;
        }
        return msg;
    }
}