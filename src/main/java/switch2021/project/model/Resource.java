package switch2021.project.model;

public class Resource {
    /**
     * Atributos da classe Resource
     **/
    private String userEmail;
    private String projectCode;

    private CharSequence startDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/
    private CharSequence endDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/

    private double costPerHour;
    private double percentageOfAllocation;

    /**
     * Construtor de Resource
     **/

    public Resource(String userEmail, String projectCode, CharSequence startDate, CharSequence endDate, double costPerHour, double percentageOfAllocation){

        this.userEmail = userEmail;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
    }

    /** Métodos "Getter" dos atributos **/

    //public ArrayList<Double> getAllocationList(){ return percentageOfAllocation};

}
