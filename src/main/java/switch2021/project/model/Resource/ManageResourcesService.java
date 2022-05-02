package switch2021.project.model.Resource;

import org.springframework.stereotype.Component;
import switch2021.project.model.valueObject.PercentageOfAllocation;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.ProjectRoleReeng;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManageResourcesService {

    public List<ResourceReeng> currentResourcesByDate(List<ResourceReeng> resourcesInProject, LocalDate date) {

        List<ResourceReeng> currentResources = new ArrayList<>();

        for (ResourceReeng res : resourcesInProject) {

            if (res.isActiveToThisDate(date)) {
                currentResources.add(res);
            }
        }
        return currentResources;
    }

    public boolean resourceCreationValidation(ProjectRoleReeng projectRole, PercentageOfAllocation allocation, LocalDate startDate, LocalDate endDate, List<ResourceReeng> projectTeam, List<ResourceReeng> resourceProjectsList) {
        boolean msg = false;
        if (validateAllocation(resourceProjectsList, allocation, startDate, endDate) && validateProjectRole(projectTeam, projectRole, startDate, endDate)){
            msg = true;
    }
    return msg;
    }

    public boolean validateAllocation(List<ResourceReeng> resourceProjectsList, PercentageOfAllocation allocation, LocalDate startDate, LocalDate endDate) {
        double sum = 0;
        boolean msg = false;

        for (ResourceReeng res : resourceProjectsList) {
            if (res.isActiveToThisDate(startDate) && res.isActiveToThisDate(endDate)) {
                sum = sum + res.getAllocation().getPercentage();
            }
        }
        if ((sum + allocation.getPercentage()) <= 1) {
            msg = true;
        }
        return msg;
    }
    public boolean validateProjectRole(List<ResourceReeng> projectTeamList, ProjectRoleReeng projectRole, LocalDate startDate, LocalDate endDate) {
        boolean msg = false;

        for (ResourceReeng res : projectTeamList) {
            if (projectRole.equals(ProjectRoleReeng.TeamMember) || (!projectRole.equals(ProjectRoleReeng.TeamMember) && res.isActiveToThisDate(startDate) && res.isActiveToThisDate(endDate))) {
                    msg = true;
                }
            }
            return msg;
        }

    public List<ProjectID> listProjectsOfResources(List<ResourceReeng> currentUserResources) {

        List<ProjectID> resourceProjects = new ArrayList<>();

        for (ResourceReeng res : currentUserResources){

            ProjectID projId = res.getId().getProject();

            resourceProjects.add(projId);

        }

        return resourceProjects;

    }
}