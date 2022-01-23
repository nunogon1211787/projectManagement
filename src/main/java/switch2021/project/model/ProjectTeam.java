package switch2021.project.model;

import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectTeam {

    /**
     * ProjectTeam's Attribute
     **/
    private List<Resource> projectTeamList;  //Lista de Resources no Project.


    /**
     * ProjectTeam's Constructor
     **/
    public ProjectTeam() {
        this.projectTeamList = new ArrayList<>();
    }

//    public ProjectTeam(Resource projectManager) {
//        this.projectTeamList = new ArrayList<>();
//        this.projectTeamList.add(projectManager);
//        projectManager.setRole(role.getProjectRoleByName("Project Manager"));
//    }


    /**
     * Getters and Setters
     **/
    public List<Resource> getProjectTeamList() {
        return new ArrayList<>(this.projectTeamList);
    }

    //Get resource by User
    public Resource getResource(SystemUser user) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYour(user) && i.isCurrent()) {
                resource = i;
            }
        }
        return resource;
    }

    //Get resource by Role
    public Resource getResource(ProjectRole role) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYour(role) && i.isCurrent()) {
                resource = i;
            }
        }
        return resource;
    }

    //Get resource by E-mail
    public Resource getResource(String email) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYour(email) && i.isCurrent()) {
                resource = i;
            }
        }
        return resource;
    }


    /**
     * Setter new Role
     **/
    public boolean assignProjectRole(Resource originalResource, LocalDate startDateNewRole, int sprintDuration, ProjectRole projectRole) {

        boolean result = false;

        if (originalResource.checkIfResourceCanBeAssignedToRoleByDate(startDateNewRole, sprintDuration)) {

            Resource newResource = new Resource(originalResource); //copyResource
            newResource.setRole(projectRole);                      //change copyResource role
            newResource.setStartDate(startDateNewRole);            //change copyResource Start Date

            originalResource.setEndDate(startDateNewRole.minusDays(1));     //change originalResource end date

            if (saveResource(newResource)) {   //add copy to Project Team List
                result = true;
            }

        }
        return result;
    }


    /**
     * Add Method
     **/
    public void addResourceToTeam(Resource resource) {
        this.projectTeamList.add(resource); //Precisa ter validações <-------------
    }


    /**
     * Method which saves new resource at ProjectTeam List
     **/
    private boolean saveResource(Resource newResource) {
        boolean msg;
        if (checkIfRoleCurrentExistInTheProjectTeam(newResource.getRole(), newResource.getStartDate())) {
//            Resource oldResourceRole = getResource(newResource.getRole()); // Old Resource with Project Role that must be unique
            Resource changeRole = new Resource(newResource); // Copy of Old Resource that must be updated
            newResource.setEndDate(newResource.getStartDate().minusDays(1)); // change end date of old resource
            changeRole.setStartDate(newResource.getStartDate()); // change start date of copy of old resource
            changeRole.setRole(App.getInstance().getCompany().getProjectRoleStore().getProjectRole("Team Member")); // change role of copy of old resource
            this.projectTeamList.add(newResource); // save in project team the new resource that was assigned to a new role
            this.projectTeamList.add(changeRole); // save in project team the old resource with new role
            msg = true;
        } else {                //-----------> Validação a fazer <------------
            this.projectTeamList.add(newResource);
            msg = true;
        }
        return msg;
    }
    //
           /* Resource copyOldResource = getResource(newResource.getRole());
            getResource(newResource.getRole()).setEndDate(newResource.getStartDate());
            copyOldResource.setRole(null);
            copyOldResource.setStartDate(newResource.getStartDate());
            addResourceToTeam(copyOldResource);
            */


    /**
     * Validation Methods
     **/
    public boolean checkIfRoleCurrentExistInTheProjectTeam(ProjectRole role, LocalDate startDate) {
        boolean msg = false;
        if (!role.isValidName("Team Member")) {
            for (Resource i : projectTeamList) {
                if (!i.isYour(role) && i.getEndDate().isAfter(startDate)) {
                    msg = true;
                    break;
                }
            }
        }
        return msg;
    }

    public boolean hasCurrentResource(String email) {
        boolean msg = false;

        for (Resource resource : this.projectTeamList) {
            if (resource.isYour(email) && resource.isCurrent()) {
                msg = true;
            }
        }
        return msg;
    }

    public boolean hasResource(String email) {
        boolean msg = false;
        for (Resource resource : this.projectTeamList) {
            if (resource.isYour(email)) {
                msg = true;
            }
        }
        return msg;
    }


    /** Override **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTeam)) return false;
        ProjectTeam that = (ProjectTeam) o;
        return Objects.equals(this.projectTeamList, that.projectTeamList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectTeamList);
    }

}

