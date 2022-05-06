package switch2021.project.model.Resource;

import org.junit.jupiter.api.Test;
import switch2021.project.model.valueObject.ProjectID;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ManageResourcesServiceTest {



    //Unit Tests

    @Test
    void returnAllResourcesTrue(){

        //Arrange
        ManageResourcesService dsrv = new ManageResourcesService();
        List<ResourceReeng> initial = new ArrayList<>();
        ResourceReeng res1 = mock(ResourceReeng.class);
        ResourceReeng res2 = mock(ResourceReeng.class);
        ResourceReeng res3 = mock(ResourceReeng.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);

        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        when(res1.isActiveToThisDate(date)).thenReturn(true);
        when(res2.isActiveToThisDate(date)).thenReturn(true);
        when(res3.isActiveToThisDate(date)).thenReturn(true);
        List<ResourceReeng> result = dsrv.currentResourcesByDate(initial, date);

        //Assert
        List<ResourceReeng> expected = new ArrayList<>(List.of(res1, res2, res3));
        assertEquals(expected, result);

    }

    @Test
    void returnAllResourcesFalse(){

        //Arrange
        ManageResourcesService dsrv = new ManageResourcesService();
        List<ResourceReeng> initial = new ArrayList<>();
        ResourceReeng res1 = mock(ResourceReeng.class);
        ResourceReeng res2 = mock(ResourceReeng.class);
        ResourceReeng res3 = mock(ResourceReeng.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);

        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        when(res1.isActiveToThisDate(date)).thenReturn(false);
        when(res2.isActiveToThisDate(date)).thenReturn(false);
        when(res3.isActiveToThisDate(date)).thenReturn(false);
        List<ResourceReeng> result = dsrv.currentResourcesByDate(initial, date);

        //Assert
        List<ResourceReeng> expected = new ArrayList<>();
        assertEquals(expected, result);

    }

    @Test
    void returnResourcesTrueAndFalse(){

        //Arrange
        ManageResourcesService dsrv = new ManageResourcesService();
        List<ResourceReeng> initial = new ArrayList<>();
        ResourceReeng res1 = mock(ResourceReeng.class);
        ResourceReeng res2 = mock(ResourceReeng.class);
        ResourceReeng res3 = mock(ResourceReeng.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);

        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        when(res1.isActiveToThisDate(date)).thenReturn(false);
        when(res2.isActiveToThisDate(date)).thenReturn(true);
        when(res3.isActiveToThisDate(date)).thenReturn(false);
        List<ResourceReeng> result = dsrv.currentResourcesByDate(initial, date);

        //Assert
        List<ResourceReeng> expected = new ArrayList<>(List.of(res2));
        assertEquals(expected, result);

    }

    @Test
    void receivingEmptyList(){

        //Arrange
        ManageResourcesService dsrv = new ManageResourcesService();
        List<ResourceReeng> initial = new ArrayList<>();

        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        List<ResourceReeng> result = dsrv.currentResourcesByDate(initial, date);

        //Assert
        List<ResourceReeng> expected = new ArrayList<>();
        assertEquals(expected, result);

    }

    @Test
    void returnProjectsNotEmpty(){

        //Arrange
        ManageResourcesService dsrv = new ManageResourcesService();
        List<ResourceReeng> initial = new ArrayList<>();
        ResourceReeng res1 = mock(ResourceReeng.class);
        ResourceReeng res2 = mock(ResourceReeng.class);
        ResourceReeng res3 = mock(ResourceReeng.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);

        //Act
        ResourceIDReeng id = mock(ResourceIDReeng.class);
        ProjectID projId = mock(ProjectID.class);
        when(res1.getId()).thenReturn(id);
        when(res2.getId()).thenReturn(id);
        when(res3.getId()).thenReturn(id);
        when(id.getProject()).thenReturn(projId);
        List<ProjectID> result = dsrv.listProjectsOfResources(initial);

        //Assert
        List<ProjectID> expected = new ArrayList<>(List.of(projId, projId, projId));
        assertEquals(expected, result);

    }

    @Test
    void returnProjectsEmpty(){

        //Arrange
        ManageResourcesService dsrv = new ManageResourcesService();
        List<ResourceReeng> initial = new ArrayList<>();

        //Act
        List<ProjectID> result = dsrv.listProjectsOfResources(initial);

        //Assert
        List<ProjectID> expected = new ArrayList<>();
        assertEquals(expected, result);

    }




}