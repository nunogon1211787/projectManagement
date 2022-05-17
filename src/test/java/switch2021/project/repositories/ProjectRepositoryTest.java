package switch2021.project.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProjectRepositoryTest {

    @Mock
    ProjectReeng project;
    @Mock
    ProjectID projectID;
    @Mock
    Description description;
    @Mock
    Description name;
    @Mock
    BusinessSector businessSector;
    @Mock
    NumberOfSprints numberOfSprints;
    @Mock
    SprintDuration sprintDuration;
    @Mock
    Budget budget;
    @InjectMocks
    ProjectRepository projectRepository;

    @Test
    @DisplayName("saveProject(ProjectReeng newProject) - with success")
    void saveProjectSuccess() {
        //Arrange
        when(project.getProjectCode()).thenReturn(projectID);
        when(project.getProjectName()).thenReturn(name);
        when(project.getDescription()).thenReturn(description);
        when(project.getBusinessSector()).thenReturn(businessSector);
        project.setStartDate(LocalDate.now());
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(project.getSprintDuration()).thenReturn(sprintDuration);
        when(project.getBudget()).thenReturn(budget);
        //Act
        projectRepository.save(project);
        //Assert
        assertEquals(1, projectRepository.getProjectList().size());
    }

    @Test
    @DisplayName("saveProject(ProjectReeng newProject) - Fail \"Error: Project already exists!\"")
    void saveProjectFailAlreadyExists() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            when(project.getProjectCode()).thenReturn(projectID);
            when(projectID.getCode()).thenReturn("Test1");
            when(project.getProjectName()).thenReturn(name);
            when(project.getDescription()).thenReturn(description);
            when(project.getBusinessSector()).thenReturn(businessSector);
            project.setStartDate(LocalDate.now());
            when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
            when(project.getSprintDuration()).thenReturn(sprintDuration);
            when(project.getBudget()).thenReturn(budget);

            //Act
            projectRepository.save(project);
            projectRepository.save(project);
        });
    }

    @Test
    @DisplayName("saveProject(ProjectReeng newProject) - \"Error: Project is null!\"")
    void saveProjectFailNull() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            project = null;
            //Act
            projectRepository.save(project);

        });
    }

    @Test
    @DisplayName("findAll()")
    void findAll() {
        //Arrange
        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Test");
        when(project.getProjectName()).thenReturn(name);
        when(project.getDescription()).thenReturn(description);
        when(project.getBusinessSector()).thenReturn(businessSector);
        project.setStartDate(LocalDate.now());
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(project.getSprintDuration()).thenReturn(sprintDuration);
        when(project.getBudget()).thenReturn(budget);
        projectRepository.save(project);

        ProjectReeng project2 = mock(ProjectReeng.class);
        ProjectID projectID2 = mock(ProjectID.class);
        when(project2.getProjectCode()).thenReturn(projectID2);
        when(projectID2.getCode()).thenReturn("Test2");
        when(project2.getProjectName()).thenReturn(name);
        when(project2.getDescription()).thenReturn(description);
        when(project2.getBusinessSector()).thenReturn(businessSector);
        project2.setStartDate(LocalDate.now());
        when(project2.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(project2.getSprintDuration()).thenReturn(sprintDuration);
        when(project2.getBudget()).thenReturn(budget);
        projectRepository.save(project2);

        //Act
        List<ProjectReeng>  projectReengList = projectRepository.findAll();
        //Assert
        assertEquals(2, projectReengList.size());
    }


    @Test
    @DisplayName("findById(String code)")
    void findById() {
        //Arrange
        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Test");
        when(project.getProjectName()).thenReturn(name);
        when(project.getDescription()).thenReturn(description);
        when(project.getBusinessSector()).thenReturn(businessSector);
        project.setStartDate(LocalDate.now());
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(project.getSprintDuration()).thenReturn(sprintDuration);
        when(project.getBudget()).thenReturn(budget);
        projectRepository.save(project);

        ProjectReeng project2 = mock(ProjectReeng.class);
        ProjectID projectID2 = mock(ProjectID.class);
        when(project2.getProjectCode()).thenReturn(projectID2);
        when(projectID2.getCode()).thenReturn("Test2");
        when(project2.getProjectName()).thenReturn(name);
        when(project2.getDescription()).thenReturn(description);
        when(project2.getBusinessSector()).thenReturn(businessSector);
        project2.setStartDate(LocalDate.now());
        when(project2.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(project2.getSprintDuration()).thenReturn(sprintDuration);
        when(project2.getBudget()).thenReturn(budget);
        projectRepository.save(project2);
        //Act
        project = projectRepository.findById("Test");
        //Assert
        assertEquals("Test", project.getProjectCode().getCode());
    }

    @Test
    @DisplayName("existById(String id)")
    void existByIdWithSuccess() {
        //Arrange
        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Test");
        when(project.getProjectName()).thenReturn(name);
        when(project.getDescription()).thenReturn(description);
        when(project.getBusinessSector()).thenReturn(businessSector);
        project.setStartDate(LocalDate.now());
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(project.getSprintDuration()).thenReturn(sprintDuration);
        when(project.getBudget()).thenReturn(budget);
        projectRepository.save(project);
        //Act
        boolean expected = projectRepository.existById("Test");
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("existById(String id)")
    void existByIdFail() {
        //Arrange
        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Test");
        when(project.getProjectName()).thenReturn(name);
        when(project.getDescription()).thenReturn(description);
        when(project.getBusinessSector()).thenReturn(businessSector);
        project.setStartDate(LocalDate.now());
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(project.getSprintDuration()).thenReturn(sprintDuration);
        when(project.getBudget()).thenReturn(budget);
        projectRepository.save(project);
        //Act
        boolean expected = projectRepository.existById("");
        //Assert
        assertFalse(expected);
    }

    @Test
    @DisplayName("existByName(String id)")
    void existByNameWithSuccess() {
        //Arrange
        when(project.getProjectCode()).thenReturn(projectID);
        when(project.getProjectName()).thenReturn(name);
        when(name.getText()).thenReturn("Test");
        when(project.getDescription()).thenReturn(description);
        when(project.getBusinessSector()).thenReturn(businessSector);
        project.setStartDate(LocalDate.now());
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(project.getSprintDuration()).thenReturn(sprintDuration);
        when(project.getBudget()).thenReturn(budget);
        projectRepository.save(project);
        //Act
        boolean expected = projectRepository.existByName("Test");
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("existByName(String id)")
    void existByNameFail() {
        //Arrange
        when(project.getProjectCode()).thenReturn(projectID);
        when(project.getProjectName()).thenReturn(name);
        when(name.getText()).thenReturn("Test");
        when(project.getDescription()).thenReturn(description);
        when(project.getBusinessSector()).thenReturn(businessSector);
        project.setStartDate(LocalDate.now());
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(project.getSprintDuration()).thenReturn(sprintDuration);
        when(project.getBudget()).thenReturn(budget);
        projectRepository.save(project);
        //Act
        boolean expected = projectRepository.existByName("");
        //Assert
        assertFalse(expected);
    }

}
