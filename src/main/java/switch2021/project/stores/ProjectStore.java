package switch2021.project.stores;

import switch2021.project.controller.ProductBacklogController;
import switch2021.project.model.*;
import switch2021.project.utils.App;

import javax.swing.plaf.PanelUI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProjectStore {

    /**
     * Atributos da Classe
     **/

    private List<Project> projectList;

    /**
     * Constructors with data
     **/

    public ProjectStore() {
        this.projectList = new ArrayList<>();
    }

    /**
     * Project creator
     **/

    public Project createProject(String code, String name, String description, Customer customer, Typology typology,
                                 BusinessSector businessSector, LocalDate startDate, int numberOfSprints, int budget) {

        Company company = App.getInstance().getCompany();

        ProjectStatus status = company.getProjectStatusStore().getProjectStatusByDescription("Planned");
        return new Project(code, name, description, customer, typology, businessSector,
                startDate, status, numberOfSprints, budget);
    }

    public void addProject(Project proj) {
        this.projectList.add(proj);
    }

    /**
     * Getters Methods
     **/

    public List<Project> getProjectList() {
        return this.projectList;
    }

    public Project getProjectByCode(String code) {
        for (Project proj : projectList) {
            if (proj.getCode().equalsIgnoreCase(code)) {
                return proj;
            }
        }
        return null;
    }

    public List<Project> getProjectListWithPORight(String email) {
        List<Project> projectList = new ArrayList<>();
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email inserted");
        }
        for (Project project : this.projectList) {
            if (project.getProductOwner() != null && email.equals(project.getProductOwner().getEmail())) {
                projectList.add(project);
            }
        }
        if (projectList.isEmpty()) {
            throw new IllegalArgumentException("Dont recognise email");
        }
        return projectList;
    }


    /**
     * Validation Methods
     **/

    public boolean checkProjectExists(String code) {

        for (Project proj : projectList) {
            if (proj.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateAllocation(SystemUser user, double percentageOfAllocation, LocalDate startDate, LocalDate
            endDate) {
        double sum = 0;
        boolean msg = false;

        for (int i = 0; i < projectList.size(); i++) {
            for (int j = 0; j < projectList.get(i).getProjectTeam().getProjectTeamList().size(); j++) {
                if (projectList.get(i).getTeamMemberByIndex(j).getUser().equals(user) &&
                        projectList.get(i).getTeamMemberByIndex(j).checkAllocationPeriod(startDate, endDate)) {
                    sum = sum + projectList.get(i).getTeamMemberByIndex(j).getPercentageOfAllocation();
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
        if (!checkProjectExists(proj.getCode())) {
            addProject(proj);
            status = true;
        }
        return status;
    }


    public ProductBacklog getProductBacklog(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Project code is empty.");
        }
        Project project = getProjectByCode(code);
        if (project == null) {
            throw new IllegalArgumentException("Project does not exist.");
        }
        return project.getProductBacklog();
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

            for (Project project : this.projectList) { //nesta lista estao todos os objetos do tipo projeto
                if (project.hasCurrentProjectTeamMember(email) && project.getEndDate() == null ||
                        project.hasCurrentProjectTeamMember(email) && project.getEndDate().isAfter(LocalDate.now())) { //project corresponde ao "objeto" daquele ciclo
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