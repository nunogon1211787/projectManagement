package switch2021.project.stores;

import switch2021.project.model.*;
import switch2021.project.utils.App;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectStore {

    /**
     * Atributos da Classe
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

    public Project createProject(String name, String description, Customer customer, Typology typology,
                                 BusinessSector businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        Company company = App.getInstance().getCompany();



        ProjectStatus status = company.getProjectStatusStore().getProjectStatusByDescription("Planned");

        return new Project(name, description, customer, typology, businessSector,
                startDate, status, numberOfSprints, budget);
    }

    /**
     * Getters Methods
     **/

    public List<Project> getProjectList() {
        return new ArrayList<>(this.projectList);
    }

    public Project getProjectByCode(String code) {
        for (Project proj : projectList) {
            if (proj.getCode().equalsIgnoreCase(code)) {
                return proj;
            }
        }
        return null;
    }

    /**
     * Validation Methods
     **/

    public boolean checkProjectExists(Project project) {

        for (Project proj : projectList) {
            if (proj.equals(project)) {
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
                    sum = sum + project.getTeamMemberByIndex(j).getPercentageOfAllocation();
                }
            }
        }
        if ((sum + percentageOfAllocation) <= 1) {
            msg = true;
        }
        return msg;
    }

    /**
     * Save Methods
     */

    public boolean saveNewProject(Project proj) {
        boolean status = false;
        String codeG = "Project_" + LocalDate.now().getYear() + "_" + (this.projectList.size()+1);
        if (!checkProjectExists(proj)) {
            proj.setCode(codeG);
            this.projectList.add(proj);

            status = true;
        }
        return status;
    }

    public List<Project> getProjectListByUserEmail(String email) {
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

    public List<Project> getCurrentProjectListByUserEmail(String email) {
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
            throw new IllegalArgumentException("Email don't exist in system");
        }
        return true;
    }
}