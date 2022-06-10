package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.entities.factories.factoryInterfaces.IResourceFactoryReeng;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.dtoModel.mapper.ResourceMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Resource.ManageResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.*;
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
    @Autowired
    private IResourceIDFactory iResourceIDFactory;
    @Autowired
    private IProjectIDFactory iProjIDFactory;
    @Autowired
    private IUserIDFactory iUserIDFactory;


    public OutputResourceDTO createAndSaveResource(CreateResourceDTO dto) {

        OutputResourceDTO response;

        if (!checkSystemUserExists(dto.getSystemUserID())) {
            throw new IllegalArgumentException(("SystemUser does not exist"));
        } else if (!checkProjectExists(dto.projectId)) {
            throw new IllegalArgumentException(("Project does not exist"));
        } else if (!checkDatesInsideProject(dto)) {
            throw new IllegalArgumentException(("Dates are not inside project"));
        } else if (!checkAllocation(dto)) {
            throw new IllegalArgumentException(("Is not valid to create - Allocation)"));
        } else if (!checkProjectRole(dto)) {
            throw new IllegalArgumentException(("Is not valid to create - ProjectRole"));
        } else {
            Resource newResource = iResourceFactory.createResource(dto);

            resRepo.save(newResource);
            response = map.model2Dto(newResource);
        }

        return response;
    }

    public List<OutputResourceDTO> showCurrentProjectTeam(String projectId) {

//        String projectId = dto.id;

        List<OutputResourceDTO> resourcesDto = new ArrayList<>();

//        // ------------- new ------------
        ProjectID projectID = iProjIDFactory.create(projectId);
//        // ------------- new ------------

        if (projRepo.existsById(projectID)) {

            List<Resource> resources = resRepo.findAllByProject(projectID);

            List<Resource> projectTeam = manageResourcesService.currentResourcesByDate(resources);

            for (Resource res : projectTeam) {

                resourcesDto.add(map.model2Dto(res));
            }
        }
        return resourcesDto;
    }

    public OutputResourceDTO showResource(String id) throws Exception {
        ResourceIDReeng resID = createResourceIdByStringInputFromController(id);

        if (!projRepo.existsById(resID.getProject())) {
            throw new Exception("Project does not exist!");
        }
        if (!userRepo.existsById(resID.getUser())) {
            throw new Exception("System User does not exist!");
        }
        Optional<Resource> foundResource = resRepo.findById(resID);
        return foundResource.map(resource -> map.model2Dto(resource)).orElse(null);
    }

    /**
     * Consult a Project Team of a Project (US028)
     */
    public List<OutputResourceDTO> showProjectTeam(String projectId) throws NullPointerException {
        ProjectID projID = iProjIDFactory.create(projectId);
        List<OutputResourceDTO> resourcesDto = new ArrayList<>();

        if (!projRepo.existsById(projID)) {
            throw new NullPointerException("Project does not exist");
        }

        List<Resource> projectTeam = resRepo.findAllByProject(projID);
        for (Resource res : projectTeam) {

            resourcesDto.add(map.model2Dto(res));
        }
        return resourcesDto;
    }

    public List<OutputResourceDTO> showAllResources() {
        List<Resource> allResources = resRepo.findAll();
        List<OutputResourceDTO> allResDto = new ArrayList<>();

        for (Resource res : allResources) {

            allResDto.add(map.model2Dto(res));
        }
        return allResDto;
    }

    public void deleteResourceRequest(String id) throws Exception {
        ResourceIDReeng resId = createResourceIdByStringInputFromController(id);

        if (!resRepo.existsById(resId)) {
            throw new Exception("Resource does not exist");
        } else {
            resRepo.deleteByResourceID(resId);
        }
    }

    private boolean checkSystemUserExists(String userID) {
        UserID userId = iUserIDFactory.createUserID(userID);
        return this.userRepo.existsById(userId);
    }

    private boolean checkProjectExists(String projectId) {
        ProjectID projID = iProjIDFactory.create(projectId);
        return this.projRepo.existsById(projID);
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

    private boolean checkAllocation(CreateResourceDTO dto) {
        UserID sysUserId = new UserID(new Email(dto.systemUserID));
        List<Resource> resourceProjectsList = resRepo.findAllByUser(sysUserId);
        return manageResourcesService.validateAllocation(resourceProjectsList, dto);
    }

    private boolean checkProjectRole(CreateResourceDTO dto) {
        ProjectID projID = new ProjectID(dto.projectId);
        List<Resource> projectTeamList = resRepo.findAllByProject(projID);
        return manageResourcesService.validateProjectRole(projectTeamList, dto);
    }

    /**
     * Create User Story ID method
     */
    private ResourceIDReeng createResourceIdByStringInputFromController(String id) {
        String[] x = id.split("&");
        String userId = x[0];
        String projectId = x[1];
        String startDate = x[2];
        return iResourceIDFactory.create(userId, projectId, startDate);
    }

}
