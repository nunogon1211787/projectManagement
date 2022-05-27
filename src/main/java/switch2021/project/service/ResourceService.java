package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.assembler.ProjectJpaAssembler;
import switch2021.project.dto.CreateResourceDTO;
import switch2021.project.dto.DateDTO;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.factoryInterface.ResourceFactoryInterfaceReeng;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.interfaces.IResourceRepo;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.mapper.ResourceMapper;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Resource.ManageResourcesService;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.ProjectRepository;
import switch2021.project.repositories.jpa.SystemUserRepositoryInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ProjectRepository projRepo;
    @Autowired
    private IResourceRepo resRepo;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    ResourceMapper map;
    @Autowired
    ManageResourcesService manageResourcesService;
    @Autowired
    private ResourceFactoryInterfaceReeng iResourceFactory;


    public OutputResourceDTO createAndSaveResource(CreateResourceDTO dto){

        OutputResourceDTO response;

        if(!checkSystemUserExists(userRepo, dto)) {
            throw new IllegalArgumentException(("SystemUser does not exist"));
        } else if (!checkProjectExists(projRepo, dto)) {
            throw new IllegalArgumentException(("Project does not exist"));
        } else if (!checkDatesInsideProject(projRepo,dto)) {
            throw new IllegalArgumentException(("Dates are not inside project"));
        } else if (!checkAllocation(resRepo, dto)) {
            throw new IllegalArgumentException(("Is not valid to create - Allocation)"));
        } else if (!checkProjectRole(resRepo, dto)) {
            throw new IllegalArgumentException(("Is not valid to create - ProjectRole"));
        } else {
            ResourceReeng newResource = iResourceFactory.createResource(dto);

            resRepo.saveResource(newResource);
            response = map.model2Dto(newResource);
        }

        return response;
    }
    public List<OutputResourceDTO> showCurrentProjectTeam(IdDTO dto, DateDTO dateDto) {

        String projectId = dto.id;

        List<OutputResourceDTO> resourcesDto = new ArrayList<>();

//        // ------------- new ------------
        ProjectID projectID = new ProjectID(projectId);
//        // ------------- new ------------

        if (projRepo.existsById(projectID.getCode())){

            List<ResourceReeng> resources = resRepo.findAllByProject(projectId);

            List<ResourceReeng> projectTeam = manageResourcesService.currentResourcesByDate(resources, LocalDate.parse(dateDto.date));

            for(ResourceReeng res : projectTeam){

                resourcesDto.add(map.model2Dto(res));
            }
        }
        return resourcesDto;
    }

    private boolean checkSystemUserExists(IUserRepo userRepository, CreateResourceDTO dto){
        SystemUserID systemUserID = new SystemUserID(new Email(dto.systemUserID));
        return userRepository.existsById(systemUserID);
    }

    private boolean checkProjectExists(IProjectRepo projectRepo, CreateResourceDTO dto){
        return projectRepo.existsById(dto.projectId);
    }

    private boolean checkDatesInsideProject(IProjectRepo projRepo, CreateResourceDTO dto){
        ProjectReeng project = projRepo.findById(dto.projectId).get();
        return project.isActiveInThisDate(LocalDate.parse(dto.startDate)) && project.isActiveInThisDate(LocalDate.parse(dto.endDate));
    }

    private boolean checkAllocation(IResourceRepo resRepo, CreateResourceDTO dto){
        List<ResourceReeng> resourceProjectsList = resRepo.findAllByUser(dto.systemUserID);
        return manageResourcesService.validateAllocation(resourceProjectsList, dto);
    }

    private boolean checkProjectRole(IResourceRepo resRepo, CreateResourceDTO dto){
        List<ResourceReeng> projectTeamList = resRepo.findAllByProject(dto.projectId);
        return manageResourcesService.validateProjectRole(projectTeamList, dto);
    }
}
