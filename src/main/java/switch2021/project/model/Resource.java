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

        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
        this.role = null;
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
     * Getters and Setters Methods
     **/
    public SystemUser getUser() {
        return user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public double getPercentageOfAllocation() {
        return percentageOfAllocation;
    }

    public ProjectRole getRole() {
        return role;
    }

    public void setRole(ProjectRole role) {
        this.role = role;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
        return this.role == projectRole;
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
        return (this.startDate.isBefore(LocalDate.now()) && this.endDate.isAfter(LocalDate.now()));
    }

    /**
     * Method to Check if the Resource is Available in this Period of Time (starDate and endDate)
     **/

    public boolean isAvailableToSprint(LocalDate startDate, int sprintDuration) {

        boolean msg = false;

        LocalDate currentEndDate = startDate.plusDays(sprintDuration * 7L - 1);

        if(this.startDate.isBefore(startDate) || this.startDate.isEqual(startDate)) {
            if (this.endDate.isAfter(currentEndDate) || this.endDate.isEqual(currentEndDate)) {
                msg = true;
            }
        }
        return msg;
    }


    /**
     * Método check se o período que queremos alocar é coincidente com o período que o resource está alocado ao projecto (para depois podermos somar e confirmar que a alocação total não é maior que 1) (Carolina US007)
     **/
    public boolean checkAllocationPeriod(LocalDate startDate, LocalDate endDate) {
        boolean msg = false;
        if (startDate.isEqual(this.startDate) || startDate.isEqual(this.endDate) || endDate.isEqual(this.endDate) || endDate.isEqual(this.startDate)) {
            msg = true;
        } else if (startDate.isBefore(this.startDate) && endDate.isAfter(this.startDate)) {
            msg = true;
        } else if (startDate.isBefore(this.endDate) && endDate.isAfter(this.endDate)) {
            msg = true;
        } else if (startDate.isAfter(this.startDate) && endDate.isBefore(this.endDate)) {
            msg = true;
        }
        return msg;
    }

    public void checkStartDateEndDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End Date must be after Start Date");
        }
    }

    public boolean checkIfResourceCanBeAssignedToRoleByDate(LocalDate startDate, int sprintDuration){
        LocalDate endDateSprint = startDate.plusDays((sprintDuration * 7L) -1);
        return startDate.isAfter(this.startDate) && endDateSprint.isBefore(this.endDate);
    }

    private void checkCostPerHour(double costPerHour) {
        if (costPerHour < 0) {
            throw new IllegalArgumentException("Cost Per Hour must be valid.");
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
        return
                (this.user.equals(that.getUser())) &&
                        (this.startDate.equals(that.getStartDate())) &&
                        (this.endDate.equals(that.getEndDate())) &&
                        (this.costPerHour == that.getCostPerHour()) &&
                        (this.percentageOfAllocation == that.getPercentageOfAllocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user,startDate,endDate,costPerHour,percentageOfAllocation);
    }
}
