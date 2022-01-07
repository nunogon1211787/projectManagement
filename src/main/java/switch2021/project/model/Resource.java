package switch2021.project.model;

import java.time.LocalDate;

public class Resource {
    /**
     * Atributos da classe Resource
     **/
    private SystemUser user;
    private Project project;

    private LocalDate startDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/
    private LocalDate endDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/

    private double costPerHour;
    private double percentageOfAllocation;

    /**
     * Construtor de Resource
     **/

    public Resource(int userId, String projectCode, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){

        SystemUser userToAdd = new Company().getSyUser(userId);
        Project projectToAdd = new Company().getProj(projectCode);

        this.user = userToAdd;
        this.project = projectToAdd;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
    }

    /** Métodos "Getter" dos atributos **/

    //public ArrayList<Double> getAllocationList(){ return percentageOfAllocation};

}
