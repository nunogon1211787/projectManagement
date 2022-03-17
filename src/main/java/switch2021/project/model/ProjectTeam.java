package switch2021.project.model;

import lombok.Getter;
import lombok.NonNull;
import switch2021.project.utils.App;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ProjectTeam {

    /**
     * ProjectTeam's Attribute
     **/
    private final List<Resource> projectTeamList;  //Resource´s List in a Project.


    /**
     * ProjectTeam's Constructor
     **/
    public ProjectTeam() {
        this.projectTeamList = new ArrayList<>();
    }


    /**
     * Getters and Setters
     **/
    //Get resource by User
    public Resource getResource(SystemUser user) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYour(user) && i.isCurrent()) {
                resource = i;
                break;
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
                break;
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
                break;
            }
        }
        return resource;
    }

    //Get current resource by name
    public Resource getResourceByName(String name) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYourName(name) && i.isCurrent()) {
                resource = i;
                break;
            }
        }
        return resource;
    }

    public List<String> getCurrentResourcesNames() {
        List<String> currentResourcesNames = new ArrayList<>();

        for (Resource resource : this.projectTeamList) {
            if (resource.isCurrent()) {
                currentResourcesNames.add(resource.getUser().getUserName().getNameF());
            }
        }
        return currentResourcesNames;
    }




    /**
     * Method to Validate a PO and a SM exists in the ProjectTeam
     */
    public boolean validateProjectTeam (LocalDate startDate, int sprintDuration) {

        boolean msg = true;

        Resource po = getProductOwnerByStartDate(startDate, sprintDuration);
        Resource sm = getScrumMasterByStartDate(startDate, sprintDuration);

        if (po == null || sm == null){
            msg = false;
        }
        return msg;
    }



    /**
     * Method to Get a Specific Resource (PO), by StartDate of the Sprint
     */
    private Resource getProductOwnerByStartDate(LocalDate startDate, int sprintDuration) {

        Resource resource = null;

        ProjectRole role = App.getInstance().getCompany().getProjectRoleStore().getProjectRole("Product Owner");

        for (Resource i : projectTeamList) {
            if (i.isYour(role) && i.isAvailableToSprint(startDate, sprintDuration)) {
                resource = i;
            }
        }
        return resource;
    }


    /**
     * Method to Get a Specific Resource (SM), by StartDate of the Sprint
     */
    private Resource getScrumMasterByStartDate(LocalDate startDate, int sprintDuration) {

        Resource resource = null;

        ProjectRole role = App.getInstance().getCompany().getProjectRoleStore().getProjectRole("Scrum Master");

        for (Resource i : projectTeamList) {
            if (i.isYour(role) && i.isAvailableToSprint(startDate, sprintDuration)){
                resource = i;
            }
        }
        return resource;
    }


    /**
     * Create a new Resource
     */
    public Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
        return new Resource(user, startDate, endDate, costPerHour, percentageOfAllocation);
    }


    /**
     * Method which saves new resource at ProjectTeam List
     **/
    public boolean saveResource(Resource resource) {
        boolean msg;
        if (resource == null) {
            throw new NullPointerException("Resource can not be null.");
        } else {
            this.projectTeamList.add(resource);
            msg = true;
        }
        return msg;
    }


    /**
     * Assign new Role
     **/
    public boolean assignProjectRole(Resource originalResource, LocalDate startDateNewRole, int sprintDuration, ProjectRole projectRole) {
        Resource newResource = null;
        //At this moment, will create a copy of the resource and change the role of the new resource.
        if (originalResource.isAvailableToSprint(startDateNewRole, sprintDuration)) {
            newResource = copyUpdateProjectRoleOfAResource(originalResource, startDateNewRole, projectRole);
            //At this moment, will check if exist any resource active and current as SM, PO or PM.
            if (checkIfTheRoleExistAndIsCurrent(newResource.getRole(), newResource.getStartDate())) {
                Resource oldResourceRole = getResource(newResource.getRole()); //If existed, will create a copy and update the role for "Team Member".
                ProjectRole teamMember = App.getInstance().getCompany().getProjectRoleStore().getProjectRole("Team Member");
                //Copy and save
                Resource oldResourceRoleCopy = copyUpdateProjectRoleOfAResource(oldResourceRole, startDateNewRole, teamMember);
                saveResource(oldResourceRoleCopy);
            }
        }
        return saveResource(newResource);
    }

    //This method will create a copy of a resource to update de project role assigned to this resource.
    private Resource copyUpdateProjectRoleOfAResource(Resource originalResource, LocalDate startDateNewRole, ProjectRole projectRole) {
        // Old Resource with Project Role that must be unique
        Resource newResource = new Resource(originalResource);      // Copy of Old Resource that must be updated
        newResource.setRole(projectRole);                           // change copyResource role
        newResource.setStartDate(startDateNewRole);                 // change copyResource Start Date
        originalResource.setEndDate(startDateNewRole.minusDays(1)); // change end date of old resource
        return newResource;
    }


    /**
     * Validation Methods
     **/
    public boolean checkIfTheRoleExistAndIsCurrent(ProjectRole role, LocalDate startDate) {
        boolean msg = false;
        if (role != null && !role.getName().getText().equals("Team Member")) {
            for (Resource i : projectTeamList) {
                if (i.isYour(role) && i.getEndDate().isAfter(startDate)) {
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


    /**
     * Override methods.
     **/
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

