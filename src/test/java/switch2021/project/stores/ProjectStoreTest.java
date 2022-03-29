package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.valueObject.Resource.Resource;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectStoreTest {



    @Test
    public void getProjectListByUserEmail() {
        //Arrange
        ProjectStore store = new ProjectStore();
        Project proj1 = mock(Project.class);
        Project proj2 = mock(Project.class);

        when(proj1.hasProjectTeamMember(any())).thenReturn(true);
        when(proj2.hasProjectTeamMember(any())).thenReturn(true);

        store.saveNewProject(proj1);
        store.saveNewProject(proj2);

        // Act
        List<Project> projectList = store.getProjectsByUserEmail("cris@ipp.pt");

        // Assert
        assertEquals(2, projectList.size());
    }

    @Test
    public void getProjectListByUserEmailBlank() {
        //Arrange
        ProjectStore store = new ProjectStore();
        Project proj1 = mock(Project.class);
        Project proj2 = mock(Project.class);

        when(proj1.hasProjectTeamMember(any())).thenReturn(false);
        when(proj2.hasProjectTeamMember(any())).thenReturn(false);

        store.saveNewProject(proj1);
        store.saveNewProject(proj2);

        // Act
        assertThrows(IllegalArgumentException.class, () -> {
            store.getProjectsByUserEmail("cris@ipp.pt");
        });

    }

    @Test
    public void getProjectByCodeSuccess() {
        // Arrange
        ProjectStore projectStore = new ProjectStore();

        Project proj = mock(Project.class);
        when(proj.hasCode(anyString())).thenReturn(true);

        projectStore.saveNewProject(proj);

        // Act
        Project project1 = projectStore.getProjectByCode("Project_2022_1");

        // Assert
        assertEquals(proj, project1);
    }

    @Test
    public void getProjectByCodeFail() {
        // Arrange
        ProjectStore projectStore = new ProjectStore();

        Project proj = mock(Project.class);
        when(proj.hasCode(anyString())).thenReturn(false);

        projectStore.saveNewProject(proj);

        // Act
        Project project1 = projectStore.getProjectByCode("Project_2022_1");

        // Assert
        assertNull(project1);
    }

    @Test
    public void getCurrentProjectListByUserEmailSuccess() {
        //Arrange
        ProjectStore projectStore = new ProjectStore();
        LocalDate endDate = LocalDate.now().plusWeeks(2);

        Project proj = mock(Project.class);
        when(proj.hasCurrentProjectTeamMember(anyString())).thenReturn(true);
        when(proj.getEndDate()).thenReturn(endDate);

        Project proj2 = mock(Project.class);
        when(proj2.hasCurrentProjectTeamMember(anyString())).thenReturn(true);
        when(proj2.getEndDate()).thenReturn(endDate);

        projectStore.saveNewProject(proj);
        projectStore.saveNewProject(proj2);

        // Act
        List<Project> projectList = projectStore.getCurrentProjectsByUserEmail("manuelmartins@beaver.com");

        // Assert
        assertEquals(2, projectList.size());
    }

    @Test
    public void getCurrentProjectListByUserEmailFailResourceNotPresent() {
        //Arrange
        ProjectStore projectStore = new ProjectStore();
        LocalDate endDate = LocalDate.now().plusWeeks(2);

        Project proj = mock(Project.class);
        when(proj.hasCurrentProjectTeamMember(anyString())).thenReturn(false);
        when(proj.getEndDate()).thenReturn(endDate);

        Project proj2 = mock(Project.class);
        when(proj2.hasCurrentProjectTeamMember(anyString())).thenReturn(false);
        when(proj2.getEndDate()).thenReturn(endDate);

        projectStore.saveNewProject(proj);
        projectStore.saveNewProject(proj2);

        // Act
        List<Project> projectList = projectStore.getCurrentProjectsByUserEmail("manuelmartins@beaver.com");

        // Assert
        assertTrue(projectList.isEmpty());
    }

    @Test
    public void getCurrentProjectListByUserEmailFailResourceNotCurrent() {
        //Arrange
        ProjectStore projectStore = new ProjectStore();
        LocalDate endDate = LocalDate.now().minusDays(2);

        Project proj = mock(Project.class);
        when(proj.hasCurrentProjectTeamMember(anyString())).thenReturn(true);
        when(proj.getEndDate()).thenReturn(endDate);

        Project proj2 = mock(Project.class);
        when(proj2.hasCurrentProjectTeamMember(anyString())).thenReturn(true);
        when(proj2.getEndDate()).thenReturn(endDate);

        projectStore.saveNewProject(proj);
        projectStore.saveNewProject(proj2);

        // Act
        List<Project> projectList = projectStore.getCurrentProjectsByUserEmail("manuelmartins@beaver.com");
        // Assert
        assertTrue(projectList.isEmpty());
    }

    @Test
    public void getProjectListSuccessEmpty() {
        //Arrange
        ProjectStore projectStore = new ProjectStore();
        List<Project> projectList = projectStore.getProjects();
        // Assert
        assertTrue(projectList.isEmpty());
    }

    @Test
    public void getProjectListSuccessWith2Projects() {
        //Arrange
        ProjectStore projectStore = new ProjectStore();

        Project proj = mock(Project.class);
        Project proj2 = mock(Project.class);

        projectStore.saveNewProject(proj);
        projectStore.saveNewProject(proj2);

        List<Project> projectList = projectStore.getProjects();

        // Assert
        assertEquals(2, projectList.size());
    }

    @Test
    public void checkProjectExists() {
        //Arrange
        ProjectStore projectStore = new ProjectStore();

        Project proj = mock(Project.class);
        Project proj2 = mock(Project.class);
        Project proj3 = mock(Project.class);

        projectStore.saveNewProject(proj);
        projectStore.saveNewProject(proj2);

        // Assert
        assertTrue(projectStore.checkProjectExists(proj));
        assertFalse(projectStore.checkProjectExists(proj3));
    }

    @Test
    public void validateAllocation() {
        //Arrange
        ProjectStore projectStore = new ProjectStore();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusWeeks(2);

        Project proj = mock(Project.class);
        SystemUser user = mock(SystemUser.class);
        Resource resource = mock(Resource.class);
        ProjectTeam projectTeam = mock(ProjectTeam.class);

        projectTeam.saveResource(resource);

        when(proj.getTeamMemberByIndex(anyInt())).thenReturn(resource);
        when(proj.getProjectTeam()).thenReturn(projectTeam);

        when(resource.getUser()).thenReturn(user);
        when(resource.checkAllocationPeriod(any(),any())).thenReturn(true);
        when(resource.getPercentageOfAllocation()).thenReturn(0.4);

        projectStore.saveNewProject(proj);

        //Assert
        assertTrue(projectStore.validateAllocation(user, 0.5, startDate, endDate));
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        Company company = new Company();
        //Project
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //list1 and list2 are equals
        Project project1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2022, 1, 31));
        ProjectStore list1 = new ProjectStore();
        list1.saveNewProject(project1);
        ProjectStore list2 = new ProjectStore();
        list2.saveNewProject(project1);
        Project project3 = company.getProjectStore().createProject("prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
        project3.setEndDate(LocalDate.of(2022, 1, 31));
        ProjectStore list3 = new ProjectStore();
        list3.saveNewProject(project3);
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }
}
