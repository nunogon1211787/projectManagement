package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.stores.SprintList;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Project {


    /**
     * Class Attributes
     **/
    private String code;
    private String projectName;
    private String description;

    private final Customer customer;
    private Typology typology;
    private ProjectStatus projectStatus;
    private ProductBacklog productBacklog;

    private final BusinessSector businessSector;
    private final SprintList sprints;
    private ProjectTeam projectTeam;

    private LocalDate startDate;
    private LocalDate endDate;

    private int numberOfSprints;
    private double budget;
    private int sprintDuration;


    /**
     * Project Constructor
     **/
    public Project(String name, String description, Customer customer, Typology typology,
                   BusinessSector businessSector, LocalDate startDate, ProjectStatus status, int numberOfSprints, double budget) {

        validateProjectFields(name, description, budget, numberOfSprints);

        this.projectName = name;
        this.description = description;

        this.customer = customer;
        this.typology = typology;
        this.projectStatus = status;
        this.businessSector = businessSector;

        this.startDate = startDate;
        this.sprints = new SprintList();

        this.numberOfSprints = numberOfSprints;
        this.budget = budget;

        this.productBacklog = new ProductBacklog(); // o objeto project tem objeto productbacklog (metodo) como parametro
        this.projectTeam = new ProjectTeam();
    }


    /**
     * Set End Date
     **/

    //Gives the project an end date
    public boolean setEndDate() {
        this.endDate = LocalDate.now();
        return true;
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
            this.projectTeam.saveResource(toAdd);
            msg = true;
        }
        return msg;
    }

    public Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
        return this.projectTeam.createResource(user, startDate, endDate, costPerHour, percentageOfAllocation);
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
                break;
            }
        }
        return msg;
    }

    /*public boolean hasCurrentResource(String email) {
        boolean msg = false;
        for (Resource resource : this.projectTeam.getProjectTeamList()) {
            if (hasResource(email) && resource.getStartDate().isBefore(LocalDate.now())
                    && resource.getEndDate().isAfter(LocalDate.now())) {
                msg = true;
            }
        }
        return msg;
    }*/

    public boolean hasCurrentProjectTeamMember(String email) {
        return this.projectTeam.hasCurrentResource(email);
    }

    public boolean hasProjectTeamMember(String email) {
        return this.projectTeam.hasResource(email);
    }

    public boolean hasCode(String code){ return this.code.equalsIgnoreCase(code); }

    /**
     * Get the start date of the next Sprint and end date of the current Sprint
     */
    public Sprint getCurrentSprint() {
      return this.sprints.getCurrentSprint();
    }

    /**
     * Override
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return numberOfSprints == project.numberOfSprints
                && Double.compare(project.budget, budget) == 0
                && sprintDuration == project.sprintDuration
                && Objects.equals(code, project.code)
                && Objects.equals(projectName, project.projectName)
                && Objects.equals(description, project.description)
                && Objects.equals(customer, project.customer)
                && Objects.equals(typology, project.typology)
                && Objects.equals(projectStatus, project.projectStatus)
                && Objects.equals(productBacklog, project.productBacklog)
                && Objects.equals(businessSector, project.businessSector)
                && Objects.equals(sprints, project.sprints)
                && Objects.equals(projectTeam, project.projectTeam)
                && Objects.equals(startDate, project.startDate)
                && Objects.equals(endDate, project.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, projectName, description, customer, typology,
                projectStatus, productBacklog, businessSector,
                sprints, projectTeam, startDate, endDate, numberOfSprints,
                budget, sprintDuration);
    }

    @Override
    public String toString() {
        return "Project{" +
                "code='" + code + '\'' +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", customer=" + customer +
                ", typology=" + typology +
                ", projectStatus=" + projectStatus +
                ", productBacklog=" + productBacklog +
                ", businessSector=" + businessSector +
                ", sprints=" + sprints +
                ", projectTeam=" + projectTeam +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfSprints=" + numberOfSprints +
                ", budget=" + budget +
                ", sprintDuration=" + sprintDuration +
                '}';
    }
}

