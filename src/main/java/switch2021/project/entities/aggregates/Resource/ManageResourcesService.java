package switch2021.project.entities.aggregates.Resource;

import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRoleReeng;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManageResourcesService {

    public List<Resource> currentResourcesByDate(List<Resource> resourcesInProject, LocalDate date) {

        List<Resource> currentResources = new ArrayList<>();

        for (Resource res : resourcesInProject) {

            if (res.isActiveToThisDate(date)) {
                currentResources.add(res);
            }
        }
        return currentResources;
    }


    public boolean validateAllocation(List<Resource> resourceProjectsList, CreateResourceDTO dto) {
        double sum = 0;
        boolean msg = false;

        for (Resource res : resourceProjectsList) {
            if (res.isActiveToThisDate(LocalDate.parse(dto.startDate)) && res.isActiveToThisDate(LocalDate.parse(dto.endDate))) {
                sum = sum + res.getAllocation().getPercentage();
            }
        }
        if ((sum + dto.percentageOfAllocation) <= 1) {
            msg = true;
        }
        return msg;
    }
    public boolean validateProjectRole(List<Resource> projectTeamList, CreateResourceDTO dto) {
        boolean msg = false;

        for (Resource res : projectTeamList) {
            if (!(res.isActiveToThisDate(LocalDate.parse(dto.startDate)) && res.isActiveToThisDate(LocalDate.parse(dto.endDate)))) {
                    msg = true;
                } else if (ProjectRoleReeng.valueOf(dto.projectRole).equals(ProjectRoleReeng.TeamMember)){
                msg = true;
            } else if (ProjectRoleReeng.valueOf(dto.projectRole).equals(ProjectRoleReeng.ProjectManager) ||
                    ProjectRoleReeng.valueOf(dto.projectRole).equals(ProjectRoleReeng.ProductOwner) ||
                    ProjectRoleReeng.valueOf(dto.projectRole).equals(ProjectRoleReeng.ScrumMaster)){
                msg = false;
            }
            }
            return msg;
        }

    public List<ProjectID> listProjectsOfResources(List<Resource> currentUserResources) {

        List<ProjectID> resourceProjects = new ArrayList<>();

        for (Resource res : currentUserResources){

            ProjectID projId = res.getId().getProject();

            resourceProjects.add(projId);

        }

        return resourceProjects;
    }
}