package switch2021.project.repositories.old;

import org.springframework.stereotype.Repository;
import switch2021.project.interfaces.IResourceRepo;
import switch2021.project.model.Resource.ResourceReeng;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@Repository
public class ResourceRepository {

    /**
     * ProjectTeam's Attribute
     **/
    private final List<ResourceReeng> resources;  //Resource´s List in a Project.


    /**
     * ProjectTeam's Constructor
     **/
    public ResourceRepository() {
        this.resources = new ArrayList<>();
    }


    /**
     * Implementing Find methods of the Interface.
     **/

//    @Override
//    public ResourceReeng findById(String id) { //TODO Must implement
//        return null;
//    }
//
//    @Override
//    public boolean existsById(String id) {
//        return false;
//    }
//
//    @Override
//    public List<ResourceReeng> findAllByProject(String projectId) {
//        return null;
//    }
//
//    @Override
//    public List<ResourceReeng> findAllByUser(String systemUserId) {
//        return null;
//    }
//    //Find the resource with resourceID in the list
//    public Optional<Resource> findByResourceId(ResourceId resId){
//        Optional<Resource> res = Optional.empty();
//
//        for (Resource resource : this.projectTeamList) {
//            if (resource.hasResourceId(resId)) {
//                res = Optional.of(resource);
//                break;
//            }
//        }
//
//        return res;
//    }
//
//    //Find all resources in the project (projectId)
//    public List<Resource> findAllByProjectId(ProjectId projId){
//        List<Resource> resList = new ArrayList<>();
//
//        for (Resource resource : this.projectTeamList) {
//            if (resource.hasProjectId(projId)) {
//                resList.add(resource);
//            }
//        }
//
//        return resList;
//    }
//
//    //Find all resources associated with system user (systemUserId)
//    public List<Resource> findAllBySystemUserId(SystemUserId userId){
//        List<Resource> resList = new ArrayList<>();
//
//        for (Resource resource : this.projectTeamList) {
//            if (resource.hasSystemUserId(userId)) {
//                resList.add(resource);
//            }
//        }
//
//        return resList;
//    }


//    REPLACED to FINDALLBYSYSTEMUSERID() method WITHOUT ISCURRENT() check.
//    public Resource getResourceByUser(SystemUser user) {
//        Resource resource = null;
//
//        for (Resource i : projectTeamList) {
//            if (i.isYourEmail(user) && i.isCurrent()) {
//                resource = i;
//                break;
//            }
//        }
//        return resource;
//    }
//
//    This method must be moved to another class/layer and will be adapted. Probably will be moved to Application Service or any Domain service class.
//    Get resource by Role
//    public Resource getResourceByRole(ProjectRole role) {
//        Resource resource = null;
//
//        for (Resource i : projectTeamList) {
//            if (i.isYourEmail(role) && i.isCurrent()) {
//                resource = i;
//                break;
//            }
//        }
//        return resource;
//    }
//
//    Verify if will be necessary.
//    Get resource by E-mail
//    public Resource getResourceByEmail(String email) {
//        Resource resource = null;
//
//        for (Resource i : projectTeamList) {
//            if (i.isYourEmail(email) && i.isCurrent()) {
//                resource = i;
//                break;
//            }
//        }
//        return resource;
//    }
//
//    Verify if will be necessary.
//    //Get current resource by name
//    public Resource getResourceByName(String name) {
//        Resource resource = null;
//
//        for (Resource i : projectTeamList) {
//            if (i.isYourName(name) && i.isCurrent()) {
//                resource = i;
//                break;
//            }
//        }
//        return resource;
//    }
//
//    Probably will be eliminated, because the conversion of the data to a DTO will be done on Application Service.
//    public List<String> getCurrentResourcesNames() {
//        List<String> currentResourcesNames = new ArrayList<>();
//
//        for (Resource resource : this.projectTeamList) {
//            if (resource.isCurrent()) {
//                currentResourcesNames.add(resource.getUser().getUserName().getNameF());
//            }
//        }
//        return currentResourcesNames;
//    }
//
//
//    /**
//     * Method to Validate a PO and a SM exists in the ProjectTeam
//     */
//    Global validation methods probably will be done on domain service class.
//    public boolean validateProjectTeam (LocalDate startDate, int sprintDuration) {
//
//        boolean msg = true;
//
//        Resource po = getProductOwnerByStartDate(startDate, sprintDuration);
//        Resource sm = getScrumMasterByStartDate(startDate, sprintDuration);
//
//        if (po == null || sm == null){
//            msg = false;
//        }
//        return msg;
//    }
//
//
//    /**
//     * Method to Get a Specific Resource (PO), by StartDate of the Sprint
//     */
//    Probably will be moved to any domain service class.
//    public Resource getProductOwnerByStartDate(LocalDate startDate, int sprintDuration) {
//
//        Resource resource = null;
//
//        for (Resource i : projectTeamList) {
//            if (i.isYourRole("Product Owner") && i.isAvailableToSprint(startDate, sprintDuration)) {
//                resource = i;
//            }
//        }
//        return resource;
//    }
//
//
//    /**
//     * Method to Get a Specific Resource (SM), by StartDate of the Sprint
//     */
//    Same as above method.
//    public Resource getScrumMasterByStartDate(LocalDate startDate, int sprintDuration) {
//
//        Resource resource = null;
//
//        String role = "Scrum Master";
//
//        for (Resource i : projectTeamList) {
//            if (i.isYourRole(role) && i.isAvailableToSprint(startDate, sprintDuration)){
//                resource = i;
//            }
//        }
//        return resource;
//    }
//
//
//    /**
//     * Create a new Resource
//     */
//    Probably will be moved to domain service class or other domain class. Must be verified.
//    public boolean createAndAddResourceWithFac(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
//        boolean msg;
//        if(user != null) {
//            msg = this.projectTeamList.add(this.resFac.createResource(user, startDate, endDate, costPerHour, percentageOfAllocation));
//        } else{
//            msg = false;
//        }
//        return msg;
//    }
//
//    public Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
//        CostPerHour coPeHo = new CostPerHour(costPerHour);
//        PercentageOfAllocation percentage = new PercentageOfAllocation(percentageOfAllocation);
//        return new Resource(user, startDate, endDate, coPeHo, percentage);
//    }


    /**
     * Method which saves new resource at ResourceStore List
     **/
//    @Override
//    public boolean saveResource(ResourceReeng resource) {
//        boolean msg = false;
//        if (resource != null) {
//            this.resources.add(resource);
//            msg = true;
//        }
//        return msg;
//    }


//    /**
//     * Assign new Role
//     **/
//    Probably will be moved to a domain service class and can be adapted.
//    public boolean assignProjectRole(Resource originalResource, LocalDate startDateNewRole, int sprintDuration, ProjectRole projectRole) {
//        Resource newResource = null;
//        //At this moment, will create a copy of the resource and change the role of the new resource.
//        if (originalResource.isAvailableToSprint(startDateNewRole, sprintDuration)) {
//            newResource = copyUpdateProjectRoleOfAResource(originalResource, startDateNewRole, projectRole);
//            //At this moment, will check if exist any resource active and current as SM, PO or PM.
//            if (checkIfTheRoleExistAndIsCurrent(newResource.getRole(), newResource.getStartDate())) {
//                Resource oldResourceRole = getResourceByRole(newResource.getRole()); //If existed, will create a copy and update the role for "Team Member".
//                ProjectRole teamMember = App.getInstance().getCompany().getProjectRoleStore().getProjectRole("Team Member");
//                //Copy and save
//                Resource oldResourceRoleCopy = copyUpdateProjectRoleOfAResource(oldResourceRole, startDateNewRole, teamMember);
//                saveResource(oldResourceRoleCopy);
//            }
//        }
//        return saveResource(newResource);
//    }
//
//    //This method will create a copy of a resource to update de project role assigned to this resource.
//    Verify if still be necessary.
//    private Resource copyUpdateProjectRoleOfAResource(Resource originalResource, LocalDate startDateNewRole, ProjectRole projectRole) {
//        // Old Resource with Project Role that must be unique
//        Resource newResource = new Resource(originalResource);      // Copy of Old Resource that must be updated
//        newResource.setRole(projectRole);                           // change copyResource role
//        newResource.setStartDate(startDateNewRole);                 // change copyResource Start Date
//        originalResource.setEndDate(startDateNewRole.minusDays(1)); // change end date of old resource
//        return newResource;
//    }
//
//
//    /**
//     * Validation Methods
//     **/
//    Global validation methods will be done in any domain service class.
//    public boolean checkIfTheRoleExistAndIsCurrent(ProjectRole role, LocalDate date) {
//        boolean msg = false;
//        if (role != null && !role.getName().getText().equals("Team Member")) {
//            for (Resource i : projectTeamList) {
//                if (i.isYourEmail(role) && i.getEndDate().isAfter(date)) {
//                    msg = true;
//                    break;
//                }
//            }
//        }
//        return msg;
//    }
//
//    Verify if still be necessary.
//    public boolean hasCurrentResource(String email) {
//        boolean msg = false;
//
//        for (Resource resource : this.projectTeamList) {
//            if (resource.isYourEmail(email) && resource.isCurrent()) {
//                msg = true;
//            }
//        }
//        return msg;
//    }
//
//
//    Verify if still be necessary.
//    public boolean hasResource(String email) {
//        boolean msg = false;
//        for (Resource resource : this.projectTeamList) {
//            if (resource.isYourEmail(email)) {
//                msg = true;
//            }
//        }
//        return msg;
//    }


//    /**
//     * Override methods.
//     **/
//    Equals and hashcode methods probably will not be necessary.
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ResourceStore)) return false;
//        ResourceStore that = (ResourceStore) o;
//        return Objects.equals(this.projectTeamList, that.projectTeamList);
//
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(projectTeamList);
//    }
}

