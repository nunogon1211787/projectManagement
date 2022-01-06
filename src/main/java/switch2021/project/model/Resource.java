package switch2021.project.model;

import java.time.LocalDate;

public class Resource {
    /**
     * Atributos da classe Resource
     **/
    private SystemUser user;
    //private Project project;

    private LocalDate startDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/
    private LocalDate endDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/

    private double costPerHour;



    private double percentageOfAllocation;

    private Profile profile;

    /**
     * Construtor de Resource
     **/

    public Resource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){

        this.user = user;
        //this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
    }

    /** Métodos "Getter" dos atributos **/

    public double getPercentageOfAllocation() {
        return percentageOfAllocation;
    }

    public boolean isYourUser(SystemUser user){
        return this.user == user;
    }

    public boolean checkAllocationPeriod(LocalDate startDate, LocalDate endDate){
//        boolean checkstartDate = startDate.isAfter(this.startDate) || startDate.isEqual(this.startDate) || startDate.isBefore(this.endDate);
//        boolean checkEndDate = endDate.isBefore(this.endDate) || endDate.isEqual(this.endDate);
//        if(startDate.isAfter(){
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





    //public ArrayList<Double> getAllocationList(){ return percentageOfAllocation};

}
