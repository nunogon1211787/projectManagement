package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.dto.ResourceDTOReeng;
import switch2021.project.factoryInterface.ResourceFactoryInterfaceReeng;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.interfaces.ResourceRepositoryInterface;
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
    private ResourceRepositoryInterface iRepoResource;
    @Qualifier("projectStoreReeng")
    @Autowired
    private ProjectRepositoryInterface iRepoProject;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ResourceFactoryInterfaceReeng iResourceFactory;
    @Autowired
    private ManageResourcesService manageResourcesService;



    public OutputResourceDTO createAndSaveResource(ResourceDTOReeng dto){

        OutputResourceDTO response;
        LocalDate startDate = LocalDate.of(dto.yearStartDate, dto.monthStartDate, dto.dayStartDate);
        LocalDate endDate = LocalDate.of(dto.yearEndDate, dto.monthEndDate, dto.dayEndDate);
        ProjectRoleReeng projRole = ProjectRoleReeng.valueOf(dto.projectRole);
        PercentageOfAllocation percAllo = new PercentageOfAllocation(dto.percentageOfAllocation);

        List<ResourceReeng> projectTeamList = iRepoResource.findAllByProject(dto.projectId);
        List<ResourceReeng> resourceProjectsList = iRepoResource.findAllByUser(dto.systemUserID);

        ProjectReeng project = iRepoProject.findById(dto.projectId);

        boolean systemUserExists = iRepoResource.existsById(dto.systemUserID);
        boolean projectExists = iRepoProject.existById(dto.projectId);
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
