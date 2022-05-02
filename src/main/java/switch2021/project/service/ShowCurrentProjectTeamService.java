package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.DateDTO;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.interfaces.ResourceRepositoryInterface;
import switch2021.project.mapper.ResourceMapper;
import switch2021.project.model.Resource.ManageResourcesService;
import switch2021.project.model.Resource.ResourceReeng;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowCurrentProjectTeamService {

    @Autowired
    ProjectRepositoryInterface projRepo;
    @Autowired
    ResourceRepositoryInterface resRepo;
    @Autowired
    ResourceMapper map;
    @Autowired
    ManageResourcesService dsrv;

    public List<OutputResourceDTO> showCurrentProjectTeam(IdDTO dto, DateDTO dateDto) {

        String projectId = dto.id;

        List<OutputResourceDTO> resourcesDto = new ArrayList<>();

        if (projRepo.existById(projectId)){

            List<ResourceReeng> resources = resRepo.findByProject(projectId);

            List<ResourceReeng> projectTeam = dsrv.currentResourcesByDate(resources, LocalDate.parse(dateDto.date));

            for(ResourceReeng res : projectTeam){

                resourcesDto.add(map.model2Dto(res));

            }

        }

        return resourcesDto;
    }
}
