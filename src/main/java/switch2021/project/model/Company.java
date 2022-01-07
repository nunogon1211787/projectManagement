package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Company {

    /**
     * Atributos da Classe
     **/

    List<Project> arrayProj = new ArrayList<>();
    List<SystemUser> arraySyUser = new ArrayList<>();
    static List<Profile> arrayProfile = new ArrayList<>();
    List<Request> arrayRequest = new ArrayList<>();

    /**
     * Constructors with data (Ivan)
     **/
    public Company(){

        arrayProfile.add(new Profile(000,"Visitor","System Profile"));
        arrayProfile.add(new Profile (001,"Administrator","System Profile"));
        arrayProfile.add(new Profile(002,"Director","System Profile"));
        arrayProfile.add(new Profile(003,"Project Manager", "Special Profile"));
        arrayProfile.add(new Profile(004, "Product Owner", "Special Profile"));
        arrayProfile.add(new Profile(005, "Scrum Master", "Special Profile"));
        arrayProfile.add(new Profile(006, "Project Team", "Special Profile"));
    }

    /** Metodo create de Projectos (Paulo) **/

    public Project createProject(String code, String name, String description, String customer, String typology,
                                 List<String> businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        return new Project(code,name, description, customer, typology, businessSector,
                                    startDate,numberOfSprints, budget);

    }
    /** Metodo create de SystemUsers (Nuno) **/

    public SystemUser createSystemUser(String userName, String email, String password, String function) {
        return new SystemUser(userName, email, password, function);
    }

    public SystemUser createSystemUser(String userName, String email, String password, String function, String photo) {
        return new SystemUser(userName, email, password, function, photo);
    }

    public boolean validateSystemUser(SystemUser user) {
        if (user == null) {
            return false;
        }
        return this.arraySyUser.contains(user);
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

        if(!validateProfile(profile)){
            return false;
        }
        arrayProfile.add(profile);
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

    public static List<Profile> getArrayProfile() {
        return arrayProfile;
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

      /**
     * Method to create a new profile (Cris-US013)
     **/

    public Profile createProfile(String name, String type) {
        return new Profile(generateNewProfileID(), name, type);
    }

    /**
     * Method to generate a new ID for the profile - begin in number 1 (Cris-US013)
     **/
    private int generateNewProfileID() {
        int lastIndex = 000;
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
            lista [i] = this.arrayProj.get(i).getCode();
        }
        return lista;
    }

    /**
     * Method to Update User's List
     */

    public void updateUserList () {

        for (SystemUser systemUser : arraySyUser) {
            //if (systemUser.getuser )
        }

    }
}