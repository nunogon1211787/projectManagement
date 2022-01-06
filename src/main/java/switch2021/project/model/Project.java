package switch2021.project.model;

import java.time.LocalDate;
import java.util.List;

public class Project {

    /**
     * Project Atributes
     **/

    private String code;
    private String projectName;
    private String description;

    private Customer customer;
    private Typology typology;
    private ProjectStatus projectStatus;

    private List<BusinessSector> businessSector;
    private List<UserStory> productBacklog;
    private List<Resource> projectTeam; /** list of resources allocated to a project **/

    private LocalDate startDate;
    private LocalDate endDate;

    private int numberOfSprints;
    private int budget;

    /**
     * Project Constructor
     **/

    public Project(String code, String name, String description, Customer customer, Typology typology,
                   List<BusinessSector> businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        this.code = code;
        this.projectName = name;
        this.description = description;
        this.customer = customer;
        this.typology = typology;
        this.projectStatus = new ProjectSettings().getProjectStatusById(0);
        this.businessSector = businessSector;
        this.startDate = startDate;
        this.numberOfSprints = numberOfSprints;
        this.budget = budget;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Method to get atual data
     * Método que obtem a data actual no momento do uso do proprio metodo; (Paulo)
     **/

    public void setEndDate() {

        this.endDate = LocalDate.now();
    }

    /** Métodos "Getter" dos atributos (Paulo, menos o productBacklog) **/

    public String getCode() {
        return code;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Typology getTypology() {
        return typology;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public List<BusinessSector> getBusinessSector() {
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

    /** Metodos Setter de atributos **/

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTypology(Typology typology) {
        this.typology = typology;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public void setBusinessSector(List<BusinessSector> businessSector) {
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


    /** Metodo verificador de existencia de user story **/

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

    public void changeProjectStatus(String status){
        this.projectStatus = projectStatus.setDescription(status);
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

    /** Método para criar resource (Carolina) **/

    public Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){
        Resource newResource = new Resource(user, startDate, endDate, costPerHour, percentageOfAllocation);
        //this.projectTeam.add(newResource);
        //return this.projectTeam.contains(newResource);
        return newResource;
    }

    /**
     * Method to get Allocation (Caroli US007)
     */

    public double sumAllocation(SystemUser user, LocalDate startDate, LocalDate endDate){
        double sum = 0;
        for(int i = 0; i < this.projectTeam.size();i++){
            if (this.projectTeam.get(i).isYourUser(user)) {
                if (this.projectTeam.get(i).checkAllocationPeriod(startDate, endDate)) {
                    sum = sum + this.projectTeam.get(i).getPercentageOfAllocation();
                }
            }
        }
        return sum;
    }

    public boolean validateAllocation(SystemUser user, LocalDate startDate, LocalDate endDate, double percentageOfAllocation){
        boolean msg = true;
        if(sumAllocation(user, startDate, endDate) + percentageOfAllocation > 1){
            msg = false;
        }
        return msg;
        }
    }

