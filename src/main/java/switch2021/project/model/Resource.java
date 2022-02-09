package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
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
    public boolean isYour(SystemUser user) {
        return this.user == user;
    }

    // Check by Role
    public boolean isYour(ProjectRole projectRole) {
        boolean result = false;
        if(this.role != null){
            result = this.role.equals(projectRole);
        }
        return result;
    }

    // Check by Email
    public boolean isYour(String email) {
        return this.user.isYourEmail(email);
    }

    // Check by Name
    public boolean isYourName(String name) {
        return this.user.hasName(name);
    }

    //Check if is current
    public boolean isCurrent() {

        boolean result = false;

        //To check start date
        if(this.startDate.isBefore(LocalDate.now()) || this.startDate.isEqual(LocalDate.now())){
            if(this.endDate.isAfter(LocalDate.now()) || this.endDate.isEqual(LocalDate.now())){
                result = true;
            }
        }

        return result;
    }

    /**
     * Method to Check if the Resource is Available in this Period of Time (starDate to endDate)
     **/

    public boolean isAvailableToSprint(LocalDate startDate, int sprintDuration) {
        boolean msg = false;

        LocalDate endDate = startDate.plusDays(sprintDuration * 7L - 1);

        if((this.startDate.isBefore(startDate) || this.startDate.isEqual(startDate)) &&
                (this.endDate.isAfter(endDate) || this.endDate.isEqual(endDate))){
                msg = true;
            }
        return msg;
    }

    /**
     * Método check se o período que queremos alocar é coincidente com o período que o resource está alocado ao projecto (para depois podermos somar e confirmar que a alocação total não é maior que 1) (Carolina US007)
     **/
    public boolean checkAllocationPeriod(LocalDate startDate, LocalDate endDate) {
        boolean msg = false;
        if ((startDate.isEqual(this.startDate) || startDate.isEqual(this.endDate) || endDate.isEqual(this.endDate) || endDate.isEqual(this.startDate))
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
        if (!(o instanceof Resource)) return false;
        Resource that = (Resource) o;
        if (this.role == null && that.role == null) return true;
        return
                (this.user.equals(that.user)) &&
                        (this.role.equals(that.role)) &&
                        (this.startDate.equals(that.startDate)) &&
                        (this.endDate.equals(that.endDate)) &&
                        (this.costPerHour == that.costPerHour) &&
                        (this.percentageOfAllocation == that.percentageOfAllocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user,role,startDate,endDate,costPerHour,percentageOfAllocation);
    }
}
