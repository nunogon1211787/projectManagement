package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

    /**
     * Atributos da classe Projecto
     **/

    private String code;
    private String projectName;
    private String description;

    private ProductBacklog productBacklog;
    private Customer customer;
    private Typology typology;
    private ProjectStatus projectStatus;
    private SystemUser scrumMaster;   /** Adicionado **/
    private SystemUser productOwner;   /** Adicionado **/

    private BusinessSector businessSector;  // Para já coloquei em tipo Business Sector e não lista. Depois será para mudar.
    private List<Resource> projectTeam; //lista de resources alocados ao projecto (Carolina)

    private LocalDate startDate;
    private LocalDate endDate;

    private int numberOfSprints;
    private int budget;
    private int sprintDuration;   /** Adicionado **/

    /**
     * Construtor de Projecto (Paulo - US005)
     **/

    public Project(String code, String name, String description, Customer customer, Typology typology,
                   BusinessSector businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        this();
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
        this.productBacklog = new ProductBacklog();
        this.projectTeam = new ArrayList<>();
    }


    public void setScrumMaster(SystemUser scrumMaster) {
        this.scrumMaster = scrumMaster;
    }

    public void setProductOwner(SystemUser productOwner) {
        this.productOwner = productOwner;
    }

    public Project() {
        this.productBacklog = new ProductBacklog();
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

    /**
     * Métodos "Getter" dos atributos (Paulo, menos o productBacklog + Cris, productBacklog)
     **/

    public String getCode() {
        return code;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {return description;}

    public Customer getCustomer() {
        return this.customer;
    }

    public Typology getTypology() {
        return typology;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public BusinessSector getBusinessSector() {return businessSector;}

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
        return productBacklog.getUserStoryList();
    }

    public SystemUser getScrumMaster() {
        return scrumMaster;
    }

    public SystemUser getProductOwner() {
        return productOwner;
    }

    public int getSprintDuration() {
        return sprintDuration;
    }

    /**
     * Metodos Setter de atributos
     **/

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
        this.productBacklog.setUserStoryList(productBacklog);
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
     * Methods for createUserStory (Cris US009)
     **/
    public boolean createUserStory(UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate) {
        UserStory us = productBacklog.createUserStory(code, userStoryStatus, priority, description, timeEstimate);
        return us == null ? false : productBacklog.addUserStory(us);
    }


    public void setSprintDuration(int sprintDuration) {
        this.sprintDuration = sprintDuration;
    }

    public void setProjectStatus(String status) {
        this.projectStatus = projectStatus.setDescription(status);
    }

    /**
     * Método para ir buscar Project Team (Carolina)
     **/

    public List<Resource> getProjectTeam() {
        return projectTeam;
    }

    /**
     * Método para adicionar resource à Project Team (Carolina US007)
     **/

    public boolean addResource(Resource toAdd) {
        boolean msg = false;
        if(validateResource(toAdd)) {
            this.projectTeam.add(toAdd);
            msg = true;
        }
        return msg;
    }

    /**
     * Método para criar resource  (Carolina US007)
     **/

    public Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
        Resource res = new Resource(user, startDate, endDate, costPerHour, percentageOfAllocation);
        return res;
    }

    /** Método para ir buscar Team Member a Project Team (Carolina US007) **/

    public Resource getTeamMemberByIndex(int index) {
        Resource res = null;
        for (int i = 0; i < projectTeam.size(); i++) {
            if (projectTeam.get(i).equals(index)) {
                res = projectTeam.get(i);
            }
        }
        return res;
    }

    /** Método para Validar Resource (Carolina US007) **/

    public boolean validateResource(Resource resource){
        boolean msg = false;
        for (int i = 0; i < projectTeam.size(); i++) {
            if(projectTeam.get(i).equals(resource)){
        msg = true;
            }
        }
        return msg;
    }

    @Override
    public boolean equals(Object o) {
        Project that = (Project) o;
        return (this.code.equals(that.code)
                && this.projectName.equals(that.projectName)
                && this.description.equals(that.description)
                && this.typology.equals(that.typology)
                && this.businessSector.equals(that.businessSector)
                && this.customer.equals(that.customer)
                && this.projectStatus.equals(that.projectStatus)
                && this.startDate.equals(that.startDate)
                && this.budget == that.budget
                && this.numberOfSprints == that.numberOfSprints);
    }

}

