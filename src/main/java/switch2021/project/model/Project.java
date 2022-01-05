package switch2021.project.model;

import java.time.LocalDate;
import java.util.List;

public class Project {

    /**
     * Atributos da classe Projecto
     **/

    private String code;
    private String projectName;
    private String description;
    private String customer;
    private String typology;
    private String ProjectStatus;

    private List<String> businessSector;
    private List<UserStory> productBacklog;
    private List<Resource> projectTeam; /** lista de resources alocados ao projecto (Carolina) **/


    private LocalDate startDate;  /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/
    private LocalDate endDate;

    private int numberOfSprints;
    private int budget;



    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public void setProjectStatus(String projectStatus) {
        ProjectStatus = projectStatus;
    }

    public void setBusinessSector(List<String> businessSector) {
        this.businessSector = businessSector;
    }

    public void setProductBacklog(List<UserStory> productBacklog) {
        this.productBacklog = productBacklog;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setNumberOfSprints(int numberOfSprints) {
        this.numberOfSprints = numberOfSprints;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    /**
     * Construtor de Projecto
     **/

    public Project(String code, String name, String description, String customer, String typology,
                   List<String> businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        this.code = code;
        this.projectName = name;
        this.description = description;
        this.customer = customer;
        this.typology = typology;
        this.ProjectStatus = "Status_0";
        this.businessSector = businessSector;
        this.startDate = startDate;
        this.numberOfSprints = numberOfSprints;
        this.budget = budget;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Método que obtem a data actual no momento do uso do proprio metodo;
     **/

    public void setEndDate() {

        this.endDate = LocalDate.now();
    }

    /** Métodos "Getter" dos atributos **/

    public String getCode() {
        return code;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getCustomer() {
        return customer;
    }

    public String getTypology() {
        return typology;
    }

    public String getProjectStatus() {
        return ProjectStatus;
    }

    public List<String> getBusinessSector() {
        return businessSector;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getNumberOfSprints() {
        return numberOfSprints;
    }

    public int getBudget() {
        return budget;
    }

    public List<UserStory> getProductBacklog() {
        return productBacklog;
    }

    boolean checkUserStoryExists(String description){
        for (UserStory userStory : productBacklog){
            if(userStory.getDescription().trim().equalsIgnoreCase(description.trim())){
                return true;
            }
        }
        return false;
    }

    public void saveProject(String name, LocalDate startDate, LocalDate endDate,int numberOfSprints){
        setProjectName(name);
        setStartDate(startDate);
        setEndDate(endDate);
        setNumberOfSprints(numberOfSprints);

    }



    public void changeSprintDuration(int sprintDuration){

    }

    /** Método para ir buscar Project Team (Carolina) **/
    public List<Resource> getProjectTeam() {
        return projectTeam;
    }

    /** Método para adicionar resource à Project Team (Carolina) **/

    public boolean addResource(Resource toAdd){
        this.projectTeam.add(toAdd);
        return true;
    }
}

