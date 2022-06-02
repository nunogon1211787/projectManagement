package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.DateDTO;
import switch2021.project.dtoModel.dto.IdDTO;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.entities.factories.factoryInterfaces.IResourceFactoryReeng;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.dtoModel.mapper.ResourceMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Resource.ManageResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.Email;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.interfaceAdapters.repositories.ProjectRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private IResourceFactoryReeng iResourceFactory;


    public OutputResourceDTO createAndSaveResource(CreateResourceDTO dto){

        OutputResourceDTO response;

        if(!checkSystemUserExists(dto)) {
            throw new IllegalArgumentException(("SystemUser does not exist"));
        } else if (!checkProjectExists(dto)) {
            throw new IllegalArgumentException(("Project does not exist"));
        }
        else if (!checkDatesInsideProject(dto)) {
            throw new IllegalArgumentException(("Dates are not inside project"));
        }
        else
        if (!checkAllocation(dto)) {
            throw new IllegalArgumentException(("Is not valid to create - Allocation)"));
        }
//        else if (!checkProjectRole(dto)) {
//            throw new IllegalArgumentException(("Is not valid to create - ProjectRole"));}
            else {
            Resource newResource = iResourceFactory.createResource(dto);

            resRepo.save(newResource);
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

        if (projRepo.existsById(projectID)){

            List<Resource> resources = resRepo.findAllByProject(projectID);

            List<Resource> projectTeam = manageResourcesService.currentResourcesByDate(resources, LocalDate.parse(dateDto.date));

            for(Resource res : projectTeam){

                resourcesDto.add(map.model2Dto(res));
            }
        }
        return resourcesDto;
    }

    private boolean checkSystemUserExists(CreateResourceDTO dto){
        UserID userID = new UserID(new Email(dto.systemUserID));
        return this.userRepo.existsById(userID);
    }

    private boolean checkProjectExists(CreateResourceDTO dto){
        return this.projRepo.existsById(new ProjectID(dto.projectId));
    }

    private boolean checkDatesInsideProject(CreateResourceDTO dto) {
        ProjectID projID = new ProjectID(dto.projectId);
        Optional<Project> opProject = projRepo.findById(projID);
        boolean msg = false;

        if (opProject.isPresent()) {
            Project project = opProject.get();

            msg = project.isActiveInThisDate(LocalDate.parse(dto.startDate)) &&
                    project.isActiveInThisDate(LocalDate.parse(dto.endDate));
        }
        return msg;
    }

    private boolean checkAllocation(CreateResourceDTO dto){
        UserID sysUserId = new UserID(new Email(dto.systemUserID));
        List<Resource> resourceProjectsList = resRepo.findAllByUser(sysUserId);
        return manageResourcesService.validateAllocation(resourceProjectsList, dto);
    }

    private boolean checkProjectRole(CreateResourceDTO dto){
        ProjectID projID = new ProjectID(dto.projectId);
        List<Resource> projectTeamList = resRepo.findAllByProject(projID);
        return manageResourcesService.validateProjectRole(projectTeamList, dto);
    }
}
