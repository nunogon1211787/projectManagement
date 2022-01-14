package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
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

        return new Project(code, name, description, customer, typology, businessSector,
                startDate, numberOfSprints, budget);
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
            return projectList;
        }
        for (Project project : this.projectList) {
            if (project.getProductOwner() != null && email.equals(project.getProductOwner().getEmail())) {
                projectList.add(project);
            }
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

    public boolean validateProject(Project project) {
        // check if project exists
        if (checkProjectExists(project.getCode())) {
            return false;
        }

        //check if numbers in Number of Sprints and budget are valid
        if (project.getBudget() < 0 || project.getNumberOfSprints() < 0) {
            return false;
        }

        //Check empty fields on code, name and description
        return !project.getProjectName().trim().isEmpty()
                && !project.getCode().trim().isEmpty()
                && !project.getDescription().trim().isEmpty();
    }

    public boolean validateAllocation(SystemUser user, double percentageOfAllocation, LocalDate startDate, LocalDate
            endDate) {
        double sum = 0;
        boolean msg = false;

        for (int i = 0; i < projectList.size(); i++) {
            for (int j = 0; j < projectList.get(i).getProjectTeam().size(); j++) {
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

    public boolean saveProject(Project proj, String code) {
        validateProject(proj);
        overwriteProject(proj, code);
        return true;
    }

    //NAO FUNCIONA

    public void overwriteProject(Project proj, String code) {
        int x = 0;
        for (int i = 0; i < this.projectList.size(); i++) {
                if (proj.getCode().equalsIgnoreCase(code)) {
                    x = i;
                }
        }
        this.projectList.set(x, proj);
    }

    public boolean saveNewProject(Project proj) {
        validateProject(proj);
        addProject(proj);
        return true;
    }

}
