package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.interfaces.ResourceRepositoryInterface;
import switch2021.project.mapper.ResourceMapper;
import switch2021.project.model.Resource.ManageResourcesService;
import switch2021.project.model.Resource.ResourceReeng;

import java.util.ArrayList;
import java.util.List;

public class ShowCurrentProjectTeamService {

    @Autowired
    ProjectRepositoryInterface projRepo;
    @Autowired
    ResourceRepositoryInterface resRepo;
    @Autowired
    ResourceMapper map;
    @Autowired
    ManageResourcesService dsrv;

    public List<OutputResourceDTO> showCurrentProjectTeam(IdDTO dto) {

        String projectId = dto.id;

        List<OutputResourceDTO> resourcesDto = new ArrayList<>();

        if (projRepo.existById(projectId)){

            List<ResourceReeng> resources = resRepo.findByProject(projectId);

            List<ResourceReeng> projectTeam = dsrv.currentProjectTeam(resources);

            for(ResourceReeng res : projectTeam){

                resourcesDto.add(map.model2Dto(res));

            }

        }

        return resourcesDto;
    }
}
