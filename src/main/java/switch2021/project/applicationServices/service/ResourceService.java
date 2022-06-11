package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.factories.factoryInterfaces.IResourceFactoryReeng;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.dtoModel.mapper.ResourceMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Resource.ManagementResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRoleReeng;
import switch2021.project.interfaceAdapters.repositories.ProjectRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class ResourceService {

    /**
     * Attributes
     */
    @Autowired
    private ProjectRepository projRepo;
    @Autowired
    private IResourceRepo resRepo;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private ResourceMapper mapper;
    @Autowired
    private ManagementResourcesService managementService;
    @Autowired
    private IResourceFactoryReeng iResourceFactory;
    @Autowired
    private IResourceIDFactory iResourceIDFactory;
    @Autowired
    private IProjectIDFactory iProjIDFactory;
    @Autowired
    private IUserIDFactory iUserIDFactory;


    /**
     * Create a Resource (US007)
     */
    public OutputResourceDTO createAndSaveResource(CreateResourceDTO dto) {
        checkAllInputToCreateResource(dto);

        Resource newResource = iResourceFactory.createResource(dto);
        checkIfUserIsPartOfProjectTeam(newResource);

        if (resRepo.existsById(newResource.getId())) {
            throw new IllegalArgumentException("This resource already exist!");
        }
        Resource saved = resRepo.save(newResource);
        return mapper.toDto(saved);
    }


    /**
     * Show a requested resource
     */
    public OutputResourceDTO showResourceRequested(String id) throws Exception {
        ResourceID resID = createResourceIdByStringInputFromController(id);

        if (!projRepo.existsById(resID.getProject())) {
            throw new Exception("Project does not exist!");
        }
        if (!userRepo.existsById(resID.getUser())) {
            throw new Exception("This User does not exist!");
        }
        Optional<Resource> foundResource = resRepo.findById(resID);
        Resource resource = foundResource.flatMap(res -> foundResource).orElse(null);

        if (resource == null) {
            throw new NullPointerException("This User is not part of the project team!");
        }

        return mapper.toDto(resource);
    }


    /**
     * Consult a Project Team of a Project (US028)
     */
    public CollectionModel<OutputResourceDTO> showCurrentProjectTeam(String projectId) throws NullPointerException {
        checkProjectExists(projectId);
        ProjectID projectID = iProjIDFactory.create(projectId);

        List<Resource> resources = resRepo.findAllByProject(projectID);
        List<Resource> projectTeam = managementService.currentResourcesByDate(resources);

        if (projectTeam.isEmpty()) {
            throw new NullPointerException("There are no resources in this project!");
        }
        return mapper.toCollectionDto(projectTeam);
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

            resourcesDto.add(mapper.toDto(res));
        }
        return resourcesDto;
    }


    /**
     * Find all Resources
     */
    public List<OutputResourceDTO> showAllResources() {
        List<Resource> allResources = resRepo.findAll();
        List<OutputResourceDTO> allResDto = new ArrayList<>();

        for (Resource res : allResources) {
            allResDto.add(mapper.toDto(res));
        }
        return allResDto;
    }


    /**
     * Define the requested project role of a resource (US014 and US027)
     */
    public OutputResourceDTO defineProjectRole(String id, DefineRoleOfResourceDTO dto) throws NullPointerException {
        ResourceID resID = createResourceIdByStringInputFromController(id);
        List<Resource> resourcesByProject = resRepo.findAllByProject(resID.getProject());

        Optional<Resource> opFoundResource = resRepo.findById(resID);
        Optional<Resource> opFoundByRole = showCurrentResourceByProjectRole(resourcesByProject,
                dto.getRole(), LocalDate.parse(dto.getStartDate()));

        Resource foundResource = opFoundResource.flatMap(res -> opFoundResource).orElse(null);
        Resource foundByRole = opFoundByRole.flatMap(res -> opFoundByRole).orElse(null);

        if (foundResource == null) {
            throw new NullPointerException("This User is not part of the project team!");
        }
        if(foundByRole != null) {
            DefineRoleOfResourceDTO roleOfOldResourceDto = new DefineRoleOfResourceDTO("TeamMember",
                    dto.getStartDate(), foundByRole.getEndDate().toString(),
                    foundByRole.getCost().getCost(),foundByRole.getAllocation().getPercentage());

            updateProjectRoleOfAResource(foundByRole, roleOfOldResourceDto);
        }
        return mapper.toDto(updateProjectRoleOfAResource(foundResource,dto));
    }

    private Optional<Resource> showCurrentResourceByProjectRole (List<Resource> resources, String role, LocalDate date) {

        for(Resource res : resources) {
            if(res.hasProjectRole(role) && res.isActiveToThisDate(date)) {
             return Optional.of(res);
            }
        }
        return Optional.empty();
    }

    private Resource updateProjectRoleOfAResource (Resource oldResource, DefineRoleOfResourceDTO dto) {
        Resource newResourceByRole = iResourceFactory.createResourceByAnotherResource(oldResource.getId(), dto);

        checkDatesInsideProject(newResourceByRole.getId().getProject().getCode(),
                newResourceByRole.getId().getStartDate().toString(),
                newResourceByRole.getEndDate().toString());
        checkAllocation(newResourceByRole.getId().getUser().getEmail().getEmailText(),
                newResourceByRole.getId().getStartDate().toString(),
                newResourceByRole.getEndDate().toString(),
                newResourceByRole.getAllocation().getPercentage());

        oldResource.setEndDate(LocalDate.parse(dto.getStartDate()).minusDays(1));
        resRepo.save(oldResource);

        return resRepo.save(newResourceByRole);
    }

    /**
     * Delete a Resource
     */
    public void deleteResourceRequest(String id) throws NullPointerException {
        ResourceID resId = createResourceIdByStringInputFromController(id);

        if (!resRepo.existsById(resId)) {
            throw new NullPointerException("This User is not part of the project team!");
        } else {
            resRepo.deleteByResourceID(resId);
        }
    }


    /**
     * Validation Methods
     */
    private void checkIfUserIsPartOfProjectTeam(Resource resource) {
        List<Resource> resources = resRepo.findAllByProject(resource.getId().getProject());

        for (Resource res : resources) {
            if (res.getId().getUser().equals(resource.getId().getUser())) {

                if (resource.getId().getStartDate().isAfter(res.getId().getStartDate())
                        && resource.getId().getStartDate().isBefore(res.getEndDate())) {
                    throw new IllegalArgumentException("This User already is part of this project team!");
                } else
                if (resource.getId().getStartDate().isBefore(res.getId().getStartDate())
                        && resource.getEndDate().isAfter(res.getId().getStartDate())) {
                    throw new IllegalArgumentException("This User already is part of this project team!");
                }
            }
        }
    }

    private void checkAllInputToCreateResource(CreateResourceDTO dto) {
        checkSystemUserExists(dto.systemUserID);
        checkProjectExists(dto.projectId);
        checkProjectRole(dto);
        checkDatesInsideProject(dto.projectId, dto.startDate, dto.endDate);
        checkAllocation(dto.systemUserID, dto.startDate, dto.endDate, dto.percentageOfAllocation);
    }

    private void checkSystemUserExists(String userID) throws IllegalArgumentException {
        UserID userId = iUserIDFactory.createUserID(userID);
        if (!userRepo.existsById(userId)) {
            throw new IllegalArgumentException(("This User does not exist!"));
        }
    }

    private void checkProjectExists(String projectId) throws IllegalArgumentException {
        ProjectID projID = iProjIDFactory.create(projectId);

        if (!projRepo.existsById(projID)) {
            throw new IllegalArgumentException(("Project does not exist!"));
        }
    }

    private void checkDatesInsideProject(String id, String startDate, String endDate) throws IllegalArgumentException {
        ProjectID projID = iProjIDFactory.create(id);
        Optional<Project> opProject = projRepo.findById(projID);

        Project project = opProject.flatMap(pro -> opProject).orElse(null);

        if (project == null) {
            throw new IllegalArgumentException(("Project does not exist!"));
        }
        if (!project.isActiveInThisDate(LocalDate.parse(startDate)) ||
                !project.isActiveInThisDate(LocalDate.parse(endDate))) {
            throw new IllegalArgumentException(("Dates are not inside project!"));
        }
    }

    private void checkAllocation(String email, String startDate,
                                 String endDate, double percentageOfAllocation) throws IllegalArgumentException {
        UserID sysUserId = new UserID(new Email(email));
        List<Resource> resourceAllocatedProjects = resRepo.findAllByUser(sysUserId);

        if (!managementService.validateAllocation(resourceAllocatedProjects, startDate,
                endDate, percentageOfAllocation)) {
            throw new IllegalArgumentException(("Is not valid to create - Allocation)"));
        }
    }

    private void checkProjectRole(CreateResourceDTO dto) throws IllegalArgumentException {
        ProjectID projID = new ProjectID(dto.projectId);
        List<Resource> projectTeamList = resRepo.findAllByProject(projID);

        if (!managementService.validateProjectRole(projectTeamList, dto.startDate, dto.endDate, dto.projectRole)) {
            throw new IllegalArgumentException(("Is not valid to create - ProjectRole"));
        }
    }


    /**
     * Create User Story ID method
     */
    private ResourceID createResourceIdByStringInputFromController(String id) {
        String[] x = id.split("&");
        String userId = x[0];
        String projectId = x[1];
        String startDate = x[2];
        return iResourceIDFactory.create(userId, projectId, startDate);
    }

}
