package switch2021.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;

import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static switch2021.project.model.Project.ProjectStatusEnum.CLOSED;


public class ProjectMapperTest {

    @Test
    @DisplayName("Transform newProject into DTO")
    public void ToDtoNewProject() {
        //Arrange
        ProjectMapper projectMapper = new ProjectMapper();
        ProjectReeng project = mock(ProjectReeng.class);
        ProjectID projectID = mock(ProjectID.class);
        Description name = mock(Description.class);
        Description description = mock(Description.class);
        BusinessSector businessSector = mock(BusinessSector.class);
        Description bs = mock(Description.class);
        NumberOfSprints numberOfSprints = mock(NumberOfSprints.class);
        Budget budget = mock(Budget.class);

        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2021_2");
        when(project.getProjectName()).thenReturn(name);
        when(name.getText()).thenReturn("Make test");
        when(project.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("make DS");
        when(project.getBusinessSector()).thenReturn(businessSector);
        when(businessSector.getDescription()).thenReturn(bs);
        when(bs.getText()).thenReturn("Medical");
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(numberOfSprints.getNumberOfSprintsVO()).thenReturn(1);
        when(project.getProjectStatus()).thenReturn(CLOSED);
        when(project.getBudget()).thenReturn(budget);
        when(budget.getBudgetVO()).thenReturn(5.0);
        when(project.getStartDate()).thenReturn(LocalDate.now());

        //Act
        OutputProjectDTO outputProjectDTO = projectMapper.model2Dto(project);

        //Assert
        assertEquals(project.getProjectCode().getCode(), outputProjectDTO.code);
        assertEquals(project.getProjectName().getText(), outputProjectDTO.projectName);
        assertEquals(project.getBusinessSector().getDescription().getText(), outputProjectDTO.businessSector);
        assertEquals(project.getDescription().getText(), outputProjectDTO.description);
    }

    @Test
    @DisplayName("Validate data from outputDTO")
    public void getInfoFromOutPutProjectDTO() {
        //Arrange
        ProjectMapper projectMapper = new ProjectMapper();
        ProjectReeng project = mock(ProjectReeng.class);
        ProjectID projectID = mock(ProjectID.class);
        Description name = mock(Description.class);
        Description description = mock(Description.class);
        BusinessSector businessSector = mock(BusinessSector.class);
        Description bs = mock(Description.class);
        NumberOfSprints numberOfSprints = mock(NumberOfSprints.class);
        Budget budget = mock(Budget.class);

        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2021_2");
        when(project.getProjectName()).thenReturn(name);
        when(name.getText()).thenReturn("Make test");
        when(project.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("make DS");
        when(project.getBusinessSector()).thenReturn(businessSector);
        when(businessSector.getDescription()).thenReturn(bs);
        when(bs.getText()).thenReturn("Medical");
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(numberOfSprints.getNumberOfSprintsVO()).thenReturn(1);
        when(project.getProjectStatus()).thenReturn(CLOSED);
        when(project.getBudget()).thenReturn(budget);
        when(budget.getBudgetVO()).thenReturn(5.0);
        when(project.getStartDate()).thenReturn(LocalDate.now());

        //Act
        OutputProjectDTO outputProjectDTO = projectMapper.model2Dto(project);

        //Assert
        assertEquals(outputProjectDTO.getProjectName(), "Make test");
        assertEquals(outputProjectDTO.getCode(), "Project_2021_2");
        assertEquals(outputProjectDTO.getDescription(), "make DS");
        assertEquals(outputProjectDTO.getBusinessSector(), "Medical");
    }

}
