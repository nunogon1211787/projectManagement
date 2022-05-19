package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.assembler.ProjectJpaAssembler;
import switch2021.project.dto.CreateResourceDTO;
import switch2021.project.dto.DateDTO;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.factoryInterface.ResourceFactoryInterfaceReeng;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.valueObject.PercentageOfAllocation;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.ProjectRoleReeng;
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
public class ResourceService {

    @Autowired
    ProjectRepository projRepo;
    @Autowired
    IResourceRepo resRepo;
    @Autowired
    IUserRepo userRepo;
    @Autowired
    ResourceMapper map;
    @Autowired
    ManageResourcesService manageResourcesService;
    @Autowired
    private ResourceFactoryInterfaceReeng iResourceFactory;
    @Autowired
    private ProjectJpaAssembler assembler;

    public OutputResourceDTO createAndSaveResource(CreateResourceDTO dto){

        OutputResourceDTO response;
        LocalDate startDate = LocalDate.of(dto.yearStartDate, dto.monthStartDate, dto.dayStartDate);
        LocalDate endDate = LocalDate.of(dto.yearEndDate, dto.monthEndDate, dto.dayEndDate);
        ProjectRoleReeng projRole = ProjectRoleReeng.valueOf(dto.projectRole);
        PercentageOfAllocation percAllo = new PercentageOfAllocation(dto.percentageOfAllocation);

        List<ResourceReeng> projectTeamList = resRepo.findAllByProject(dto.projectId);
        List<ResourceReeng> resourceProjectsList = resRepo.findAllByUser(dto.systemUserID);

        ProjectReeng project = projRepo.findById(dto.projectId).get();

        boolean systemUserExists = userRepo.existsByEmail(dto.systemUserID);
        // ------------- new
        ProjectID projID = new ProjectID(dto.projectId);
        // ------------- new
        boolean projectExists = projRepo.existsById(projID);
        boolean isValidToProject = project.isActiveInThisDate(startDate) && project.isActiveInThisDate(endDate);
        boolean isValidToCreate = manageResourcesService.resourceCreationValidation(projRole, percAllo, startDate, endDate, projectTeamList, resourceProjectsList);

        if(systemUserExists && projectExists && isValidToProject && isValidToCreate){

            ResourceReeng newResource = iResourceFactory.createResource(dto);

            resRepo.saveResource(newResource);
            response = map.model2Dto(newResource);
        } else {
            throw new IllegalArgumentException(("ID inválido"));
        }
        return response;
    }

    public List<OutputResourceDTO> showCurrentProjectTeam(IdDTO dto, DateDTO dateDto) {

        String projectId = dto.id;

        List<OutputResourceDTO> resourcesDto = new ArrayList<>();

//        // ------------- new ------------
        ProjectID projectID = new ProjectID(projectId);
//        // ------------- new ------------

        if (projRepo.existsById(projectID)){

            List<ResourceReeng> resources = resRepo.findAllByProject(projectId);

            List<ResourceReeng> projectTeam = manageResourcesService.currentResourcesByDate(resources, LocalDate.parse(dateDto.date));

            for(ResourceReeng res : projectTeam){

                resourcesDto.add(map.model2Dto(res));
            }
        }
        return resourcesDto;
    }
}
