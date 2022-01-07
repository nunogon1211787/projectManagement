package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<Resource> projectTeam;
    /**
     * lista de resources alocados ao projecto (Carolina)
     **/

    private LocalDate startDate;
    private LocalDate endDate;

    private int numberOfSprints;
    private int budget;

    /**
     * Construtor de Projecto (Paulo)
     **/

    public Project(String code, String name, String description, String customer, String typology,
                   List<String> businessSector, LocalDate startDate, int numberOfSprints, int budget) {
        this();
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

    public Project() {
        this.productBacklog = new ArrayList<>();
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Método que obtem a data actual no momento do uso do proprio metodo; (Paulo)
     **/

    public void setEndDate() {

        this.endDate = LocalDate.now();
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

    /**
     * Metodos Setter de atributos
     **/

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
     * Methods for createUserStory (Cris US009)
     **/
    public UserStory createUserStory(UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate) {
        return new UserStory(code, userStoryStatus, priority, description, timeEstimate);
    }

    /**
     * Methods for addUserStory to the productBacklog (Cris US009)
     **/

    public boolean addUserStory(UserStory userStory) {
        //user story data validation
        if (!isValidUserStory(userStory)) {
            return false;
        }
        this.getProductBacklog().add(userStory);
        return true;
    }

    /**
     * Methods for validate data (Cris US009)
     **/

    private boolean isValidUserStory(UserStory us) {
        //check if priority is invalid
        if (us.getPriority() < 0) {
            return false;
        }

        //check if description is invalid
        if (us.getDescription() == null || us.getDescription().trim().isEmpty()) {
            return false;
        }

        // check duplicate story
        for (UserStory userStory : productBacklog) {
            if (us.getDescription().trim().equalsIgnoreCase(userStory.getDescription().trim()) && userStory.getProjectCode().equals(us.getProjectCode())) {
                return false;
            }
        }

        // check invalid project code
        if (us.getProjectCode() == null || us.getProjectCode().trim().isEmpty()) {
            return false;
        }

        // check estimated time is invalid
        if (us.getTimeEstimate() < 0) {
            return false;
        }

        return true;
    }

    public void saveProject(String name, LocalDate startDate, LocalDate endDate, int numberOfSprints) {
        setProjectName(name);
        setStartDate(startDate);
        setEndDate(endDate);
        setNumberOfSprints(numberOfSprints);

    }

    public void changeSprintDuration(int sprintDuration) {

    }

    /**
     * Método para ir buscar Project Team (Carolina)
     **/

    public List<Resource> getProjectTeam() {
        return projectTeam;
    }

    /**
     * Método para adicionar resource à Project Team (Carolina)
     **/

    public boolean addResource(Resource toAdd) {
        this.projectTeam.add(toAdd);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        Project project = (Project) o;
        return getNumberOfSprints() == project.getNumberOfSprints() && getBudget() == project.getBudget() && Objects.equals(getCode(), project.getCode()) && Objects.equals(getProjectName(), project.getProjectName()) && Objects.equals(getDescription(), project.getDescription()) && Objects.equals(getCustomer(), project.getCustomer()) && Objects.equals(getTypology(), project.getTypology()) && Objects.equals(getProjectStatus(), project.getProjectStatus()) && Objects.equals(getBusinessSector(), project.getBusinessSector()) && Objects.equals(getProductBacklog(), project.getProductBacklog()) && Objects.equals(getProjectTeam(), project.getProjectTeam()) && Objects.equals(getStartDate(), project.getStartDate()) && Objects.equals(getEndDate(), project.getEndDate());
    }

}

