package switch2021.project.model.Resource;

import org.springframework.stereotype.Component;
import switch2021.project.dto.CreateResourceDTO;
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


    public boolean validateAllocation(List<ResourceReeng> resourceProjectsList, CreateResourceDTO dto) {
        double sum = 0;
        boolean msg = false;

        for (ResourceReeng res : resourceProjectsList) {
            if (res.isActiveToThisDate(LocalDate.parse(dto.startDate)) && res.isActiveToThisDate(LocalDate.parse(dto.endDate))) {
                sum = sum + res.getAllocation().getPercentage();
            }
        }
        if ((sum + dto.percentageOfAllocation) <= 1) {
            msg = true;
        }
        return msg;
    }
    public boolean validateProjectRole(List<ResourceReeng> projectTeamList, CreateResourceDTO dto) {
        boolean msg = false;

        for (ResourceReeng res : projectTeamList) {
            if (ProjectRoleReeng.valueOf(dto.projectRole).equals(ProjectRoleReeng.TeamMember) ||
                    !(res.isActiveToThisDate(LocalDate.parse(dto.startDate)) && res.isActiveToThisDate(LocalDate.parse(dto.endDate)))) {
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