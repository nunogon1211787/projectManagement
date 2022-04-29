package switch2021.project.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.mapper.ProjectMapper;

@ExtendWith(SpringExtension.class)
class ProjectServiceTest {

    @InjectMocks
    ProjectService projectService;

    private ProjectRepositoryInterface projectRepositoryInterfaceTrue;;

    @Mock
    private  ProjectFactoryInterface projectFactoryInterface;
    @Mock
    private ProjectMapper projectsMapper;
    @Mock
    private ProjectRepositoryInterface projectRepositoryInterface;;

    /*@Test
    public void projectServiceTest() {

        //Arrange
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.code = "Project_2022_1";
        projectDTO.projectName = "Project";
        projectDTO.description = "Description";
        projectDTO.businessSector = "BusinessSector";
        projectDTO.startDate = "2025-12-05";
        projectDTO.numberOfSprints = "12";
        projectDTO.budget = "1200";

        OutputProjectDTO outputProjectDTO = new OutputProjectDTO();
        outputProjectDTO.code;
        outputProjectDTO.name;
        outputProjectDTO.desc;
        outputProjectDTO.startDate
    }*/


}