package switch2021.project.model.Resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageResourcesService {

    public List<ResourceReeng> currentProjectTeam(List<ResourceReeng> resourcesInProject) {

        List<ResourceReeng> projectTeam = new ArrayList<>();

        for(ResourceReeng res : resourcesInProject){

            if(res.isActiveToThisDate(LocalDate.now())){
                projectTeam.add(res);
            }

        }

        return projectTeam;
    }
}
