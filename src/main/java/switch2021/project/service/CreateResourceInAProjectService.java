package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.assembler.ProjectJpaAssembler;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.dto.ResourceDTOReeng;
import switch2021.project.factoryInterface.ResourceFactoryInterfaceReeng;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.repositories.ProjectRepository;
import switch2021.project.interfaces.IResourceRepo;
import switch2021.project.mapper.ResourceMapper;

import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Resource.ManageResourcesService;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.PercentageOfAllocation;
import switch2021.project.model.valueObject.ProjectRoleReeng;


import java.time.LocalDate;
import java.util.List;

@Service
public class CreateResourceInAProjectService {
    @Autowired
    private IResourceRepo iRepoResource;
//    @Qualifier("projectStoreReeng")
    @Autowired
    private ProjectRepository iRepoProject;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ResourceFactoryInterfaceReeng iResourceFactory;
    @Autowired
    private ManageResourcesService manageResourcesService;
    @Autowired
    private ProjectJpaAssembler assembler;


    public OutputResourceDTO createAndSaveResource(ResourceDTOReeng dto){

        OutputResourceDTO response;
        LocalDate startDate = LocalDate.of(dto.yearStartDate, dto.monthStartDate, dto.dayStartDate);
        LocalDate endDate = LocalDate.of(dto.yearEndDate, dto.monthEndDate, dto.dayEndDate);
        ProjectRoleReeng projRole = ProjectRoleReeng.valueOf(dto.projectRole);
        PercentageOfAllocation percAllo = new PercentageOfAllocation(dto.percentageOfAllocation);

        List<ResourceReeng> projectTeamList = iRepoResource.findAllByProject(dto.projectId);
        List<ResourceReeng> resourceProjectsList = iRepoResource.findAllByUser(dto.systemUserID);

        ProjectReeng project = iRepoProject.findById(dto.projectId).get();

        boolean systemUserExists = iRepoResource.existsById(dto.systemUserID);
        // ------------- new
        ProjectID projectID = new ProjectID(dto.projectId);
        // ------------- new
        boolean projectExists = iRepoProject.existsById(projectID);
        boolean isValidToProject = project.isActiveInThisDate(startDate) && project.isActiveInThisDate(endDate);
        boolean isValidToCreate = manageResourcesService.resourceCreationValidation(projRole, percAllo, startDate, endDate, projectTeamList, resourceProjectsList);

        if(systemUserExists && projectExists && isValidToProject && isValidToCreate){

        ResourceReeng newResource = iResourceFactory.createResource(dto);

        iRepoResource.saveResource(newResource);
        response = resourceMapper.model2Dto(newResource);
        } else {
            throw new IllegalArgumentException(("ID inválido"));
        }
        return response;
    }
}
