package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.DateDTO;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.repositories.ProjectRepository;
import switch2021.project.repositories.jpa.ProjectJpaRepository;
import switch2021.project.interfaces.IResourceRepo;
import switch2021.project.mapper.ResourceMapper;
import switch2021.project.model.Resource.ManageResourcesService;
import switch2021.project.model.Resource.ResourceReeng;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowCurrentProjectTeamService {

    @Autowired
    ProjectRepository projRepo;
    @Autowired
    IResourceRepo resRepo;
    @Autowired
    ResourceMapper map;
    @Autowired
    ManageResourcesService dsrv;

    public List<OutputResourceDTO> showCurrentProjectTeam(IdDTO dto, DateDTO dateDto) {

        String projectId = dto.id;

        List<OutputResourceDTO> resourcesDto = new ArrayList<>();

        // ------------- new ------------
        ProjectID projectID = new ProjectID(projectId);
        // ------------- new ------------

        if (projRepo.existsById(projectID)){

            List<ResourceReeng> resources = resRepo.findAllByProject(projectId);

            List<ResourceReeng> projectTeam = dsrv.currentResourcesByDate(resources, LocalDate.parse(dateDto.date));

            for(ResourceReeng res : projectTeam){

                resourcesDto.add(map.model2Dto(res));

            }

        }

        return resourcesDto;
    }
}
