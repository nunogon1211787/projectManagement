package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.repositories.ProjectStore;
import switch2021.project.repositories.ProjectTeam;
import switch2021.project.model.valueObject.*;

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

        store.getProjectList().add(proj1);
        store.getProjectList().add(proj2);

        // Act
        List<Project> projectList = store.getProjectsByUserEmail("cris@ipp.pt");

        // Assert
        assertEquals(2, projectList.size());
    }

    @Test
    public void getProjectListByUserEmailBlank() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            ProjectStore store = new ProjectStore();
            Project proj1 = mock(Project.class);
            Project proj2 = mock(Project.class);

            when(proj1.hasProjectTeamMember(any())).thenReturn(false);
            when(proj2.hasProjectTeamMember(any())).thenReturn(false);

            // Act
            store.getProjectsByUserEmail("cris@ipp.pt");
        });
    }

    @Test
    public void getProjectByCodeSuccess() {
        // Arrange
        ProjectStore projectStore = new ProjectStore();

        Project proj = mock(Project.class);
        when(proj.hasCode(anyString())).thenReturn(true);

        projectStore.getProjectList().add(proj);

        // Act
        Project project1 = projectStore.findById("Project_2022_1");

        // Assert
        assertEquals(proj, project1);
    }

    @Test
    public void getProjectByCodeFail() {
        // Arrange
        ProjectStore projectStore = new ProjectStore();

        Project proj = mock(Project.class);
        when(proj.hasCode(anyString())).thenReturn(false);

        // Act
        Project project1 = projectStore.findById("Project_2022_1");

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

        projectStore.getProjectList().add(proj);
        projectStore.getProjectList().add(proj2);

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

        // Act
        List<Project> projectList = projectStore.getCurrentProjectsByUserEmail("manuelmartins@beaver.com");
        // Assert
        assertTrue(projectList.isEmpty());
    }

    @Test
    public void getProjectListSuccessEmpty() {
        //Arrange
        ProjectStore projectStore = new ProjectStore();
        List<Project> projectList = projectStore.findAll();
        // Assert
        assertTrue(projectList.isEmpty());
    }

    @Test
    public void getProjectListSuccessWith2Projects() {
        //Arrange
        ProjectStore projectStore = new ProjectStore();

        Project proj = mock(Project.class);
        Project proj2 = mock(Project.class);

        projectStore.getProjectList().add(proj);
        projectStore.getProjectList().add(proj2);

        List<Project> projectList = projectStore.findAll();

        // Assert
        assertEquals(2, projectList.size());
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
        PercentageOfAllocation percOfAllo = mock(PercentageOfAllocation.class);

        projectTeam.saveResource(resource);

        when(proj.getTeamMemberByIndex(anyInt())).thenReturn(resource);
        when(proj.getProjectTeam()).thenReturn(projectTeam);

        when(resource.getUser()).thenReturn(user);
        when(resource.checkAllocationPeriod(any(),any())).thenReturn(true);
        when(resource.getPercentageOfAllocation()).thenReturn(percOfAllo);
        when(percOfAllo.getPercentage()).thenReturn(0.5);

        //Assert
        assertTrue(projectStore.validateAllocation(user, 0.5, startDate, endDate));
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        Company company = new Company();
        //Project
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //list1 and list2 are equals
        Project project1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2022, 1, 31));
        ProjectStore list1 = new ProjectStore();
        ProjectStore list2 = new ProjectStore();
        list1.getProjectList().add(project1);
        list2.getProjectList().add(project1);
        Project project3 = company.getProjectStore().createAndSaveProject("prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
        project3.setEndDate(LocalDate.of(2022, 1, 31));
        ProjectStore list3 = new ProjectStore();
        list3.getProjectList().add(project3);
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }
}
