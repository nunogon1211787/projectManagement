package switch2021.project.model;

import java.time.LocalDate;
import java.util.Objects;

public class Resource {

    /**
     * Atributos da classe Resource (Carolina US007)
     **/
    private SystemUser user;

    private LocalDate startDate;
    private LocalDate endDate;

    private double costPerHour;
    private double percentageOfAllocation;

    private Profile profile;

    /**
     * Construtor de Resource (Carolina US007)
     **/

    public Resource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){

        this.user = user;
        //this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
    }

    /** Override Equals (Carolina US007) **/

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


    /** Métodos "Getter" dos atributos (Carolina US007) **/

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

    public Profile getProfile() {
        return profile;
    }

    public double getPercentageOfAllocation() {
        return percentageOfAllocation;
    }

    /** Método check se o user é aquele que queremos (Carolina US007) **/
    public boolean isYourUser(SystemUser user){
        return this.user == user;
    }

    /** Método check se o período que queremos alocar é coincidente com o período que o resource está alocado ao projecto (para depois podermos somar e confirmar que a alocação total não é maior que 1) (Carolina US007) **/

    public boolean checkAllocationPeriod(LocalDate startDate, LocalDate endDate){
        boolean msg = false;
        if(startDate.isAfter(this.endDate) || endDate.isBefore(this.startDate)){
            msg = false;
        } else if (startDate.isEqual(this.startDate) || startDate.isEqual(this.endDate) || endDate.isEqual(this.endDate) || endDate.isEqual(this.startDate) ){
            msg = true;
        } else if (startDate.isBefore(this.startDate) && endDate.isAfter(this.startDate)){
            msg = true;
        } else if (startDate.isBefore(this.endDate) && endDate.isAfter(this.endDate)){
            msg = true;
        } else if (startDate.isAfter(this.startDate) && endDate.isBefore(this.endDate)) {
            msg = true;
        }
        return msg;
    }







}
