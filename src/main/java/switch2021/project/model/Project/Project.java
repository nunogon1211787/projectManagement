package switch2021.project.model.Project;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.factory.ProjectTeamFactory;
import switch2021.project.factory.ResourceFactory;
import switch2021.project.factory.UserStoryFactory;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.SystemUser.User;
import switch2021.project.repositories.SprintRepository;
import switch2021.project.model.Typology.Typology;
import switch2021.project.repositories.UserStoryRepository;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.old.ProjectTeam;
import switch2021.project.utils.Entity;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Deprecated
public class Project implements Entity {


    private final Customer customer;
    private final BusinessSector businessSector;
    private final SprintRepository sprintList;
    /**
     * Class Attributes
     **/
    private ProjectID projectCode;
    private Description projectName;
    private Description description;
    private Typology typology;
    private ProjectStatusEnum projectStatus;
    private UserStoryRepository userStoryStore;
    private UserStoryFactory userStoryFactory;
    private ProjectTeam projectTeam;
    private ProjectTeamFactory projectTeamFactory;
    private ResourceFactory resFac = new ResourceFactory();

    private LocalDate startDate;
    private LocalDate endDate;

    private NumberOfSprints numberOfSprints;
    private Budget budget;
    private SprintDuration sprintDuration;


    /**
     * Project Constructor
     **/
    public Project(String name, String description, Customer customer, Typology typology,
                   BusinessSector businessSector, LocalDate startDate, int numberOfSprints, double budget) {

        this.projectName = new Description(name);
        this.description = new Description(description);

        this.customer = customer;
        this.typology = typology;
        this.projectStatus = ProjectStatusEnum.PLANNED;
        this.businessSector = businessSector;

        this.startDate = startDate;
        this.sprintList = new SprintRepository();

        this.numberOfSprints = new NumberOfSprints(numberOfSprints);
        this.budget = new Budget(budget);

        this.projectTeam = new ProjectTeam(resFac);
        this.userStoryStore = new UserStoryRepository();
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

    public Resource createResource(User user, LocalDate startDate, LocalDate endDate, double costPerHour,
                                   double percentageOfAllocation) {
        return this.projectTeam.createResource(user, startDate, endDate, costPerHour, percentageOfAllocation);
    }

    public Resource getTeamMemberByIndex(int index) {
        Resource res = null;
        for (Resource i : projectTeam.getProjectTeamList()) {
            res = projectTeam.getProjectTeamList().get(index);
        }
        return res;
    }

    public boolean validateResource(Resource resource) {
        boolean msg = true;
        String x = resource.getUser().getSystemUserId().getEmail().getEmailText();

        for (Resource i : projectTeam.getProjectTeamList()) {
            if (i.getUser().getSystemUserId().getEmail().getEmailText().equals(x)) {
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

    public boolean hasCode(String code) {
        return this.projectCode.getCode().equalsIgnoreCase(code);
    }


    /**
     * Get the start date of the next Sprint and end date of the current Sprint
     */
    public Sprint getCurrentSprint() {
        return this.sprintList.findCurrentSprint();
    }


    /**
     * Override
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return
                Objects.equals(sprintDuration, project.sprintDuration)
                        && Objects.equals(projectCode, project.projectCode)
                        && Objects.equals(projectName, project.projectName)
                        && Objects.equals(description, project.description)
                        && Objects.equals(customer, project.customer)
                        && Objects.equals(typology, project.typology)
                        && Objects.equals(projectStatus, project.projectStatus)
                        && Objects.equals(userStoryStore, project.userStoryStore)
                        && Objects.equals(businessSector, project.businessSector)
                        && Objects.equals(sprintList, project.sprintList)
                        && Objects.equals(projectTeam, project.projectTeam)
                        && Objects.equals(startDate, project.startDate)
                        && Objects.equals(endDate, project.endDate)
                        && Objects.equals(budget, project.budget)
                        && Objects.equals(numberOfSprints, project.numberOfSprints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, projectName, description, customer, typology,
                            projectStatus, userStoryStore, businessSector,
                            sprintList, projectTeam, startDate, endDate, numberOfSprints,
                            budget, sprintDuration);
    }

    @Override
    public String toString() {
        return "Project{" +
                "code='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", customer=" + customer +
                ", typology=" + typology +
                ", projectStatus=" + projectStatus +
                ", productBacklog=" + userStoryStore +
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

    @Override
    public boolean sameIdentityAs(Object other) {
        return false;
    }
}

