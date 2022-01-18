package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

    /**
     * Class Atributes
     **/

    private String code;
    private String projectName;
    private String description;

    private Customer customer;
    private Typology typology;
    private ProjectStatus projectStatus;
    private ProductBacklog productBacklog;
    private SystemUser productOwner; // Verificar a necessidade de se manter este atributo

    private BusinessSector businessSector;

    private ProjectTeam projectTeam;

    private LocalDate startDate;
    private LocalDate endDate;

    private int numberOfSprints;
    private double budget;
    private int sprintDuration;

    /**
     * Project Constructor
     **/

    public Project(String code, String name, String description, Customer customer, Typology typology,
                   BusinessSector businessSector, LocalDate startDate, ProjectStatus status, int numberOfSprints, double budget) {

        validateProjectFields(name, description,budget,numberOfSprints);

        this.code = code;
        this.projectName = name;
        this.description = description;

        this.customer = customer;
        this.typology = typology;
        this.projectStatus = status;
        this.businessSector = businessSector;

        this.startDate = startDate;

        this.numberOfSprints = numberOfSprints;
        this.budget = budget;

        this.productBacklog = new ProductBacklog();
      this.projectTeam = new ProjectTeam();
//        this.projectTeam = new ProjectTeam(res);
    }

    /**
     * Getter Methods
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

    public double getBudget() {
        return budget;
    }

    public SystemUser getProductOwner() {
        return productOwner;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public int getSprintDuration() {
        return sprintDuration;
    }

    /**
     * Setter Methods
     **/

    public void setCode(String code) {
        this.code = code;
    }

    public boolean setEndDate() {
        this.endDate = LocalDate.now();
        return true;
    }

    public void setProductOwner(SystemUser productOwner) {
        this.productOwner = productOwner;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypology(Typology typology) {
        this.typology = typology;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
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

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setSprintDuration(int sprintDuration) {
        this.sprintDuration = sprintDuration;
    }

    public void setProjectStatus(String status) {
        this.projectStatus = projectStatus.setDescription(status);
    }

    public void setProjectTeam(ProjectTeam projectTeam) {
        this.projectTeam = projectTeam;
    }


    /**
     * Validates Project Creation Fields
     * Checks if @param projectName and @param description are emptry or have the minimum characters necessary
     */

    public void validateProjectFields(String projectName, String description, double budget, int numberOfSprints) {
        if (projectName.trim().isEmpty())
            throw new IllegalArgumentException("Project Name cannot be empty");
        if ((projectName.length() < 3))
            throw new IllegalArgumentException("Project Name must be at least 3 characters");
        if (description.trim().isEmpty())
            throw new IllegalArgumentException("Description cannot be empty");
        if ((description.length() < 5))
            throw new IllegalArgumentException("Description must be at least 5 characters");
        if (numberOfSprints <= 0)
            throw new IllegalArgumentException("Number of Sprints must be greater than 0");
        if (budget <= 0)
            throw new IllegalArgumentException("Budget must be greater than 0");
    }

    /**
     * Methods UserStory creation (Cris US009)
     * - Create User Story method
     **/

//    public boolean createUserStory(String userStoryStatus, int priority, String description, int timeEstimate) {
//        UserStory us = productBacklog.createUserStory(userStoryStatus, priority, description, timeEstimate);
//        return productBacklog.addUserStory(us);
//    }

    /**
     * Resource Allocation Methods - (Carolina US007)
     * - Método para criar resource
     * - Método para ir buscar Team Member a Project Team
     * - Método para Validar Resource
     **/

    public ProjectTeam getProjectTeam() {
        return projectTeam;
    }

    public boolean addResource(Resource toAdd) {
        boolean msg = false;
        if (validateResource(toAdd)) {
            this.projectTeam.addResourceToTeam(toAdd);
            msg = true;
        }
        return msg;
    }

    public Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {

        Resource res = new Resource(user, startDate, endDate, costPerHour, percentageOfAllocation);
        return res;
    }

    public Resource getTeamMemberByIndex(int index) {
        Resource res = null;
        for (int i = 0; i < projectTeam.getProjectTeamList().size(); i++) {
            res = projectTeam.getProjectTeamList().get(index);
        }
        return res;
    }

    public boolean validateResource(Resource resource) {
        boolean msg = true;
        for (int i = 0; i < projectTeam.getProjectTeamList().size(); i++) {
            if (projectTeam.getProjectTeamList().get(i).equals(resource)) {
                msg = false;
            }
        }
        return msg;
    }

    public boolean createUserStory(UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate) {
        UserStory userStory = this.productBacklog.createUserStory(userStoryStatus, priority, description, timeEstimate);
        return this.productBacklog.saveUserStory(userStory);
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

