package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Company {

    /**
     * Atributos da Classe
     **/

    List<Project> arrayProj;
    List<SystemUser> arraySyUser;
    List<Profile> arrayProfile;
    List<Request> arrayRequest;

    /**
     * Constructors with data (Ivan)
     **/
    public Company(){
        this.arrayProj = new ArrayList<>();
        this.arraySyUser = new ArrayList<>();

        this.arrayProfile = new ArrayList<>();
        this.arrayProfile.add(new Profile("Visitor","System Profile"));
        this.arrayProfile.add(new Profile("Administrator","System Profile"));
        this.arrayProfile.add(new Profile("Director","System Profile"));
        this.arrayProfile.add(new Profile("Project Manager", "Special Profile"));
        this.arrayProfile.add(new Profile("Product Owner", "Special Profile"));
        this.arrayProfile.add(new Profile("Scrum Master", "Special Profile"));
        this.arrayProfile.add(new Profile("Project Team", "Special Profile"));

        this.arrayRequest = new ArrayList<>();
    }

    /** Metodo create de Projectos (Paulo) **/

    public Project createProject(String code, String name, String description, Customer customer, Typology typology,
                                 List<BusinessSector> businessSector, LocalDate startDate, int numberOfSprints, int budget) {

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

    //Nuno, alterei o método porque o anterior tinha um erro! (Joana)

    public boolean validateSystemUser(SystemUser user) {
        if (user == null & this.arraySyUser.contains(user)) {
            return false;
        }
        return true;
    }

    /**
     * Método para adicionar objetos ás listas.
     **/

    public boolean add(Project proj) {
        arrayProj.add(proj);
        return true;
    }
    /*
    public boolean saveSystemUser(SystemUser syUser) {
        this.arraySyUser.add(syUser);
        return true;
    }
     */

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

    public SystemUser getUserByEmail(String email) {

        SystemUser user = null;

        for (int i = 0; i < this.arraySyUser.size(); i++) {
            if(this.arraySyUser.get(i).isYourEmail(email)){ //get i da lista arrayList (retorna obj) + metodo serve para validar o obj
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
        return new Profile(name, type);
    }

    /**
     * Method to generate a new ID for the profile - begin in number 1 (Cris-US013)
     **/
    private int generateNewProfileID() {
        int lastIndex = 001;
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
        x.changeProjectStatus(statusDescription);
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
     * Method to save system user (if this is in a valid state) in System User List
     */

    public boolean saveSystemUser(SystemUser user) {

        if (!validateSystemUser(user)) {
            return false;
        }
        return this.arraySyUser.add(user);
    }

    /**
     * Method to Validate Allocation (Caroli US007)
     */

//    public boolean checkAllocation(SystemUser user, double percentageOfAllocation){
//        if()
//
//
//    }

}