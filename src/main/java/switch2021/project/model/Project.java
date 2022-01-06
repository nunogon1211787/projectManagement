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

    private Customer customer;
    private Typology typology;
    private ProjectStatus projectStatus;

    private BusinessSector businessSector;  // Para já coloquei em tipo Business Sector e não lista. Depois será para mudar.
    private List<UserStory> productBacklog;
    private List<Resource> projectTeam; /** lista de resources alocados ao projecto (Carolina) **/

    private LocalDate startDate;
    private LocalDate endDate;

    private int numberOfSprints;
    private int budget;

    /**
     * Construtor de Projecto (Paulo - US005)
     **/

    public Project(String code, String name, String description, Customer customer, Typology typology,
                   BusinessSector businessSector, LocalDate startDate, int numberOfSprints, int budget) {

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
     * Método que obtem a data actual no momento do uso do proprio metodo; (Paulo - US005)
     **/

    public boolean setEndDate() {

        this.endDate = LocalDate.now();
        return true;
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

    public BusinessSector getBusinessSector() {
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

    public void setBusinessSector(BusinessSector businessSector) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project that = (Project) o;

        return (       this.code.equals(that.code)
                    && this.projectName.equals(that.projectName)
                    && this.description.equals(that.description)
                    && this.typology.equals(that.typology)
                    && this.businessSector.equals(that.businessSector)
                    && this.customer.equals(that.customer)
                    && this.projectStatus.equals(that.projectStatus)
                    && this.startDate.equals(that.startDate)
                    && this.budget==that.budget
                    && this.numberOfSprints== that.numberOfSprints);
    }

}

