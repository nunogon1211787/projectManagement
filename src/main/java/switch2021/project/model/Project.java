package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.factory.*;
import switch2021.project.factory.ProjectTeamFactory;
import switch2021.project.factory.ResourceFactory;
import switch2021.project.factory.UserStoryFactory;
import switch2021.project.immutable.Description;
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
    private Description description;

    private final Customer customer;
    private Typology typology;
    private ProjectStatus projectStatus;
    private ProductBacklog productBacklog;
    private UserStoryFactory userStoryFactory;

    private final BusinessSector businessSector;
    private final SprintList sprintList;

    private ProjectTeam projectTeam;
    private ProjectTeamFactory projectTeamFactory;
    private ResourceFactoryInterface resFac = new ResourceFactory();

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

        validateProjectFields(name, budget, numberOfSprints);

        this.projectName = name;
        this.description = new Description(description);

        this.customer = customer;
        this.typology = typology;
        this.projectStatus = status;
        this.businessSector = businessSector;

        this.startDate = startDate;
        this.sprintList = new SprintList();

        this.numberOfSprints = numberOfSprints;
        this.budget = budget;

        this.projectTeam = new ProjectTeam(resFac);
        //this.projectTeam = this.projectTeamFactory.createProjectTeam();
//        this.projectTeam = new ProjectTeam();
        this.productBacklog = new ProductBacklog();

    }

    /**
     * Validates Project Creation Fields
     * Checks if @param projectName and @param description are empty or have the minimum characters necessary
     */
    public void validateProjectFields(String projectName, double budget, int numberOfSprints) {
        if (projectName.trim().isEmpty())
            throw new IllegalArgumentException("Project Name cannot be empty");
        if ((projectName.length() < 3))
            throw new IllegalArgumentException("Project Name must be at least 3 characters");
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
        for(Resource i : projectTeam.getProjectTeamList()){
            res = projectTeam.getProjectTeamList().get(index);
        }
        return res;
    }

    public boolean validateResource(Resource resource) {
        boolean msg = true;
        String x = resource.getUser().getEmail();

        for(Resource i : projectTeam.getProjectTeamList()){
            if (i.getUser().getEmail().equals(x)) {
                msg = false;
                break;
            }
        }
        return msg;
    }

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
      return this.sprintList.getCurrentSprint();
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
                && Objects.equals(sprintList, project.sprintList)
                && Objects.equals(projectTeam, project.projectTeam)
                && Objects.equals(startDate, project.startDate)
                && Objects.equals(endDate, project.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, projectName, description, customer, typology,
                projectStatus, productBacklog, businessSector,
                sprintList, projectTeam, startDate, endDate, numberOfSprints,
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
                ", sprints=" + sprintList +
                ", projectTeam=" + projectTeam +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfSprints=" + numberOfSprints +
                ", budget=" + budget +
                ", sprintDuration=" + sprintDuration +
                '}';
    }
}

