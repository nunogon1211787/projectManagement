package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {

    /**
     * Atributos da Classe
     **/

    List<Project> arrayProject;
    List<SystemUser> arraySyUser;
    List<Profile> arrayProfile;
    List<Request> arrayRequest;

    /**
     * Constructors with data
     **/
    public Company() {
        this.arraySyUser = new ArrayList<>();
        this.arrayProfile = new ArrayList<>();
        this.arrayProject = new ArrayList<>();
        this.arrayRequest = new ArrayList<>();

        arrayProfile.add(new Profile("Visitor", "System Profile"));
        arrayProfile.add(new Profile("Administrator", "System Profile"));
        arrayProfile.add(new Profile("Director", "System Profile"));
        arrayProfile.add(new Profile("Project Manager", "Special Profile"));
        arrayProfile.add(new Profile("Product Owner", "Special Profile"));
        arrayProfile.add(new Profile("Scrum Master", "Special Profile"));
        arrayProfile.add(new Profile("Project Team", "Special Profile"));
    }

    // Project

    /**
     * Create Methods
     **/

    public Project createProject(String code, String name, String description, Customer customer, Typology typology,
                                 BusinessSector businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        return new Project(code, name, description, customer, typology, businessSector,
                startDate, numberOfSprints, budget);
    }

    /**
     * Add Method
     **/

    public boolean addProject(Project proj) {
        this.arrayProject.add(proj);
        return true;

    }

    /**
     * Getters Methods
     **/

    public List<Project> getArrayProject() {
        return this.arrayProject;
    }

    public Project getProject(String code) {

        for (Project proj : arrayProject) {
            if (proj.getCode().equalsIgnoreCase(code)) {
                return proj;
            }
        }
        return null;
    }

    ////Talvez mudar para não buscar por index
    public Project getProjByIndex(int index) {
        return arrayProject.get(index);
    }

    public List<Project> getProjectListWithPORight(String email) {
        List<Project> projectList = new ArrayList<>();
        if (email == null || email.trim().isEmpty()) {
            return projectList;
        }
        for (Project project : arrayProject) {
            if (project.getProductOwner() != null && email.equals(project.getProductOwner().getEmail())) {
                projectList.add(project);
            }
        }
        return projectList;
    }

    /**
     * Validation Methods
     **/

    public boolean checkProjectExists(String code) {

        for (Project proj : arrayProject) {
            if (proj.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateProject(Project project) {
        // check if project exists
        if (checkProjectExists(project.getCode())) {
            return false;
        }

        //check if numbers in Number of Sprints and budget are valid
        if (project.getBudget() < 0 || project.getNumberOfSprints() < 0) {
            return false;
        }

        //Check empty fields on code, name and description
        return !project.getProjectName().trim().isEmpty()
                && !project.getCode().trim().isEmpty()
                && !project.getDescription().trim().isEmpty();
    }

    public boolean validateAllocation(SystemUser user, double percentageOfAllocation, LocalDate startDate, LocalDate
            endDate) {
        double sum = 0;
        boolean msg = false;

        for (int i = 0; i < arrayProject.size(); i++) {
            for (int j = 0; j < arrayProject.get(i).getProjectTeam().size(); j++) {
                if (arrayProject.get(i).getTeamMemberByIndex(j).getUser().equals(user) &&
                        arrayProject.get(i).getTeamMemberByIndex(j).checkAllocationPeriod(startDate, endDate)) {
                    sum = sum + arrayProject.get(i).getTeamMemberByIndex(j).getPercentageOfAllocation();
                }
            }
        }
        if ((sum + percentageOfAllocation) <= 1) {
            msg = true;
        }
        return msg;
    }

    /**
     * Save Methods
     */

    public boolean saveProject(Project proj, int index) {
        validateProject(proj);
        overwriteProject(proj, index);
        return true;
    }

    public void overwriteProject(Project proj, int index) {
        this.arrayProject.set(index, proj);
    }


    // System user

    /**
     * Create Methods
     **/

    public SystemUser createSystemUser(String userName, String email, String function, String password) {
        return new SystemUser(userName, email, function, password, arrayProfile.get(0));
    }
    ///// Apenas manter um construtor !!!

    public SystemUser createSystemUser(String userName, String email, String function, String photo, String password) {
        return new SystemUser(userName, email, function, photo, password, arrayProfile.get(0));
    }

    /**
     * Add Method
     **/

    public boolean addSystemUser(SystemUser syUser) {
        this.arraySyUser.add(syUser);
        return true;
    }

    /**
     * Getter Methods
     */
    public List<SystemUser> getArraySyUser() {
        return this.arraySyUser;
    }

    public SystemUser getUserByEmail(String email) {

        SystemUser user = null;

        for (int i = 0; i < this.arraySyUser.size(); i++) {
            if (this.arraySyUser.get(i).isYourEmail(email)) {
                user = this.arraySyUser.get(i);
                break;
            }
        }

        return user;
    }

    public List<SystemUser> searchUsers(String name, String email, String function, int isActive, int[] profileList) {

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
     * Validation Methods
     */

    public boolean validateSystemUser(SystemUser user) {
        if (user == null) {
            return false;
        }
        if (hasEmail(user.getEmail())) {
            return false;
        }
        if (hasUserName(user.getUserName())) {
            return false;
        }
        return !this.arraySyUser.contains(user);
    }

    boolean hasEmail(String newUserEmail) {
        for (SystemUser newUser : arraySyUser) {
            if (newUser.getEmail().trim().equalsIgnoreCase(newUserEmail.trim())) {
                return true;
            }
        }
        return false;
    }

    //// Dois utilizadores podem existir com o mesmo nome, podem existir dois Nunos!!
    boolean hasUserName(String newUserName) {
        for (SystemUser newUser : arraySyUser) {
            if (newUser.getUserName().trim().equalsIgnoreCase(newUserName.trim())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Save Method
     */

    public boolean saveSystemUser(SystemUser user) {
        boolean result = true;

        if (!validateSystemUser(user)) {
            result = false;
        } else {
            this.arraySyUser.add(user);
        }
        return result;
    }



    // Profile

    /**
     * Create Method
     **/

    public Profile createProfile(String name, String type) {
        return new Profile(name, type);
    }

    /**
     * Add Method
     **/

    public boolean addProfile(Profile profile) {

        if (!validateProfile(profile)) {
            return false;
        }
        arrayProfile.add(profile);
        return true;
    }

    /**
     * Getter Methods
     **/

    public List<Profile> getArrayProfile() {
        return this.arrayProfile;
    }

    public List<Profile> getArrayProfileWithType(String type) {

        List<Profile> foundList = new ArrayList<>();

        for (int i = 0; i < this.arrayProfile.size(); i++) {

            if (this.arrayProfile.get(i).hasType(type)) {
                foundList.add(this.arrayProfile.get(i));
            }

        }

        return foundList;
    }

    ////Talvez mudar para não buscar por index
    public Profile getProfile(int index) {
        return new Profile(arrayProfile.get(index));
    }

    public Profile getProfile(String name) {
        Profile pro = null;
        for (int i = 0; i < arrayProfile.size(); i++) {
            if (Objects.equals(getProfile(i).getName(), name)) {
                pro = getProfile(i);
                break;
            }
        }
        return pro;
    }

    /**
     * Validation Method
     **/

    private boolean validateProfile(Profile profile) {
        //Check empty fields on name and type
        if (profile.getName().trim().isEmpty() || profile.getType().trim().isEmpty()) {
            return false;
        }

        //Check if the profile type is valid
        if (!profile.getType().equalsIgnoreCase("System Profile") && !profile.getType().equalsIgnoreCase("Special Profile")) {
            return false;
        }

        //Check if profile already exist
        for (Profile up : arrayProfile) {
            if (up.equals(profile)) {
                return false;
            }
        }
        return true;
    }



    //Request

    /**
     * Add Method
     */

    public boolean addRequest(Request request) {
        this.arrayRequest.add(request);
        return true;

    }

    /**
     * Validation Method
     */

    private boolean validateRequest(Request newRequest) {

        //Check if request already exist
        for (Request up : arrayRequest) {
            if (up.equals(newRequest)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Save Method
     */

    public boolean saveRequest(Request newRequest) {

        boolean result = false;

        if (validateRequest(newRequest)) {
            addRequest(newRequest);
            result = true;
        }

        return result;

    }



}