package switch2021.project.model;

import lombok.Getter;
import switch2021.project.factoryInterface.ResourceFactoryInterface;
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
    private ResourceFactoryInterface resFac;

    private final List<Resource> projectTeamList;  //Resource´s List in a Project.

    /**
     * ProjectTeam's Constructor
     **/
    public ProjectTeam(ResourceFactoryInterface resourceFac) {
        this.resFac = resourceFac;
        this.projectTeamList = new ArrayList<>();
    }

//    public ProjectTeam(){
//        this.projectTeamList = new ArrayList<>();
//    }

    /**
     * Getters and Setters
     **/
    //Get resource by User
    public Resource getResourceByUser(SystemUser user) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYourEmail(user) && i.isCurrent()) {
                resource = i;
                break;
            }
        }
        return resource;
    }

    //Get resource by Role
    public Resource getResourceByRole(ProjectRole role) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYourEmail(role) && i.isCurrent()) {
                resource = i;
                break;
            }
        }
        return resource;
    }

    //Get resource by E-mail
    public Resource getResourceByEmail(String email) {
        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYourEmail(email) && i.isCurrent()) {
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
    public Resource getProductOwnerByStartDate(LocalDate startDate, int sprintDuration) {

        Resource resource = null;

        for (Resource i : projectTeamList) {
            if (i.isYourRole("Product Owner") && i.isAvailableToSprint(startDate, sprintDuration)) {
                resource = i;
            }
        }
        return resource;
    }


    /**
     * Method to Get a Specific Resource (SM), by StartDate of the Sprint
     */
    public Resource getScrumMasterByStartDate(LocalDate startDate, int sprintDuration) {

        Resource resource = null;

        String role = "Scrum Master";

        for (Resource i : projectTeamList) {
            if (i.isYourRole(role) && i.isAvailableToSprint(startDate, sprintDuration)){
                resource = i;
            }
        }
        return resource;
    }


    /**
     * Create a new Resource
     */
    public boolean createAndAddResourceWithFac(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
        boolean msg;
        if(user != null) {
            msg = this.projectTeamList.add(this.resFac.createResource(user, startDate, endDate, costPerHour, percentageOfAllocation));
        } else{
            msg = false;
        }
        return msg;
    }

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
                Resource oldResourceRole = getResourceByRole(newResource.getRole()); //If existed, will create a copy and update the role for "Team Member".
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
    public boolean checkIfTheRoleExistAndIsCurrent(ProjectRole role, LocalDate date) {
        boolean msg = false;
        if (role != null && !role.getName().getText().equals("Team Member")) {
            for (Resource i : projectTeamList) {
                if (i.isYourEmail(role) && i.getEndDate().isAfter(date)) {
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
            if (resource.isYourEmail(email) && resource.isCurrent()) {
                msg = true;
            }
        }
        return msg;
    }


    public boolean hasResource(String email) {
        boolean msg = false;
        for (Resource resource : this.projectTeamList) {
            if (resource.isYourEmail(email)) {
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

