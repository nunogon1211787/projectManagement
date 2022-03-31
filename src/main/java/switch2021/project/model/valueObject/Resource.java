package switch2021.project.model.valueObject;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.SystemUser.SystemUser;
import java.time.LocalDate;
import java.util.Objects;

@Getter @Setter
public class Resource {

    /**
     * Resource's Attribute
     **/
    private SystemUser user;
    private ProjectRole role;
    private LocalDate startDate;
    private LocalDate endDate;

    private double costPerHour;
    private double percentageOfAllocation;


    /**
     * Resource's Constructor
     **/
    public Resource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
        checkStartDateEndDate(startDate, endDate);
        checkCostPerHour(costPerHour);
        checkSystemUser(user);

        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
    }


    /**
     * Resource's Copy
     **/
    public Resource(Resource originalResource) {
        this.user = originalResource.getUser();
        this.startDate = originalResource.getStartDate();
        this.endDate = originalResource.getEndDate();
        this.costPerHour = originalResource.getCostPerHour();
        this.percentageOfAllocation = originalResource.getPercentageOfAllocation();
        this.role = originalResource.getRole();
    }


    /**
     * This method checks if the Resource is that what we are looking for
     **/
    // Check by User
    public boolean isYourEmail(SystemUser user) {
        return this.user == user;
    }

    // Check by Role
    public boolean isYourEmail(ProjectRole projectRole) {
        boolean result = false;
        if(this.role != null){
            result = this.role.equals(projectRole);
        }
        return result;
    }

    // Check Resource Email
    public boolean isYourEmail(String email) {
        return this.user.isYourEmail(email);
    }

    // Check Resource Name
    public boolean isYourName(String name) {
        return this.user.hasName(name);
    }

    //Check Resource Role
    public boolean isYourRole(String role) {
        return this.role.getName().getText().equals(role);
    }

    //Check if is current
    public boolean isCurrent() {

        boolean result = false;

        //To check start date
        if((this.startDate.isBefore(LocalDate.now())
                || this.startDate.isEqual(LocalDate.now())) && (this.endDate.isAfter(LocalDate.now())
                || this.endDate.isEqual(LocalDate.now()))){
                result = true;
            }
        return result;
    }

    /**
     * Method to Check if the Resource is Available in this Period of Time (starDate to endDate)
     **/

    public boolean isAvailableToSprint(LocalDate sprintStartDate, int sprintDurationDays) {
        boolean msg = false;

        LocalDate sprintEndDate = sprintStartDate.plusDays(sprintDurationDays - 1L);

        if((this.startDate.isBefore(sprintStartDate) || this.startDate.isEqual(sprintStartDate)) &&
                (this.endDate.isAfter(sprintEndDate) || this.endDate.isEqual(sprintEndDate))){
                msg = true;
            }
        return msg;
    }

    /**
     * Método check se o período que queremos alocar é coincidente com o período que o resource está alocado ao projecto (para depois podermos somar e confirmar que a alocação total não é maior que 1) (Carolina US007)
     **/
    public boolean checkAllocationPeriod(LocalDate startDate, LocalDate endDate) {
        boolean msg = false;
        if ((startDate.isEqual(this.startDate) || startDate.isEqual(this.endDate) || endDate.isEqual(this.endDate)
                || endDate.isEqual(this.startDate))
            || (startDate.isBefore(this.startDate) && endDate.isAfter(this.startDate))
            || (startDate.isBefore(this.endDate) && endDate.isAfter(this.endDate))
            || (startDate.isAfter(this.startDate) && endDate.isBefore(this.endDate))) {
            msg = true;
        }
        return msg;
    }

    public void checkStartDateEndDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End Date must be after Start Date");
        }
    }

    private void checkCostPerHour(double costPerHour) {
        if (costPerHour < 0) {
            throw new IllegalArgumentException("Cost Per Hour must be valid.");
        }
    }

    private void checkSystemUser(SystemUser user) {
        if(user == null) {
            throw new NullPointerException("Resource can not have a System User as null.");
        }
    }


    /**
     * Override Equals (Carolina US007)
     **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Double.compare(resource.costPerHour, costPerHour) == 0 && Double.compare(resource.percentageOfAllocation, percentageOfAllocation)
                == 0 && user.equals(resource.user) && Objects.equals(role, resource.role) && Objects.equals(startDate,
                resource.startDate) && Objects.equals(endDate, resource.endDate);

    }

    @Override
    public int hashCode() {
        return Objects.hash(user,role,startDate,endDate,costPerHour,percentageOfAllocation);
    }

}
