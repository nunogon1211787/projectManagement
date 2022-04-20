package switch2021.project.repositories;

import lombok.Getter;

import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.model.Project.Project;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ProjectStore implements ProjectRepositoryInterface {

    /**
     * Class Attributes
     **/
    private final List<Project> projectList;


    /**
     * Constructors with data
     **/
    public ProjectStore() {
        this.projectList = new ArrayList<>();
    }


    /**
     * Project creator
     **/
    public Project createAndSaveProject(String name, String description, Customer customer, Typology typology,
                                        BusinessSector businessSector, LocalDate startDate, int numberOfSprints, int budget) {
        Project newProject = new Project(name, description, customer, typology, businessSector,
                startDate, numberOfSprints, budget);

        newProject.setProjectCode(new ProjectID(projectList.size()+1));
        projectList.add(newProject);

        return newProject;
    }


    /**
     * Getters Methods
     **/
    public List<Project> findAllProjects() {

        return new ArrayList<>(this.projectList);
    }


    public Project findProjectByID(String code) {
        Project res = null;
        for (Project proj : projectList) {
            if (proj.hasCode(code)) {
                res = proj;
            }
        }
        return res;
    }

    public boolean hasProjectId(String id) {

        for (Project proj : projectList) {
            if (proj.getProjectCode().getCode().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateAllocation(SystemUser user, double percentageOfAllocation, LocalDate startDate, LocalDate
            endDate) {
        double sum = 0;
        boolean msg = false;

        for (Project project : projectList) {
            for (int j = 0; j < project.getProjectTeam().getProjectTeamList().size(); j++) {
                if (project.getTeamMemberByIndex(j).getUser().equals(user) &&
                        project.getTeamMemberByIndex(j).checkAllocationPeriod(startDate, endDate)) {
                    sum = sum + project.getTeamMemberByIndex(j).getPercentageOfAllocation().getPercentage();
                }
            }
        }
        if ((sum + percentageOfAllocation) <= 1) {
            msg = true;
        }
        return msg;
    }


    public List<Project> getProjectsByUserEmail(String email) {
        List<Project> projectListByUser = new ArrayList<>();

        if (isValidGetProjListEmail(email)) {
            for (Project project : this.projectList) {
                if (project.hasProjectTeamMember(email)) {
                    projectListByUser.add(project);
                }
            }
        }
        return projectListByUser;
    }

    public List<Project> getCurrentProjectsByUserEmail(String email) {
        List<Project> currentProjectListByUser = new ArrayList<>();

        for (Project project : this.projectList) {
            if (project.hasCurrentProjectTeamMember(email) && project.getEndDate() == null ||
                    project.hasCurrentProjectTeamMember(email) && project.getEndDate().isAfter(LocalDate.now())) {
                currentProjectListByUser.add(project);
            }
        }
        return currentProjectListByUser;
    }

    private boolean isValidGetProjListEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }

        boolean isEmailExist = false;

        for (Project project : projectList) {
            if (project.hasProjectTeamMember(email)) {
                isEmailExist = true;
                break;
            }
        }
        if (!isEmailExist) {
            throw new IllegalArgumentException("Email is not associated with any project");
        }
        return true;
    }


    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectStore)) return false;
        ProjectStore that = (ProjectStore) o;
        return Objects.equals(this.projectList, that.projectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectList);
    }
}