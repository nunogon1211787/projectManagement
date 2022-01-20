package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        return projectTeamList;
    }

    //Get resource by User
    public Resource getResource(SystemUser user) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYour(user) && i.getEndDate().isAfter(LocalDate.now())) {
                resource = i;
            }
        }
        return resource;
    }

    //Get resource by Role
    public Resource getResource(ProjectRole role) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYour(role) && i.getEndDate().isAfter(LocalDate.now())) {
                resource = i;
            }
        }
        return resource;
    }


    /**
     * Setter new Role
     **/
    public boolean assignProjectRole(Resource originalResource, LocalDate startDate, ProjectRole projectRole) {

        Resource newResource = new Resource(originalResource); //copyResource
        newResource.setRole(projectRole);                      //change copyResource role
        newResource.setStartDate(startDate);                   //change originalResource start date

        originalResource.setEndDate(startDate);                //change originalResource end date

        return saveResource(newResource);                      //add copy to Project Team List
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
        if (validateRoleExistent(newResource.getRole())) {
            assignProjectRole(getResource(newResource.getRole()), newResource.getStartDate(), null);
            this.projectTeamList.add(newResource);
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
    public boolean validateRoleExistent(ProjectRole role) {
        boolean msg = true;
        for (Resource i : projectTeamList) {
            if (!i.isYour(role) && i.getEndDate().isAfter(LocalDate.now())) {
                msg = false;
                break;
            }
        }
        return msg;
    }

    public boolean hasCurrentResource(String email) {
        boolean msg = false;
        for (Resource resource : this.projectTeamList) {
            if (resource.isYour(email) && resource.getStartDate().isBefore(LocalDate.now())
                    && resource.getEndDate().isAfter(LocalDate.now())) {
                msg = true;
            }
        }
        return msg;
    }

}

