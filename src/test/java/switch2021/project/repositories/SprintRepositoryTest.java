package switch2021.project.repositories;

import static org.mockito.Mockito.mock;

public class SprintRepositoryTest {
/*
    @Test
    @DisplayName("Save Sprint, with success")
    public void saveSprintSuccess() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectID projectID = mock(ProjectID.class);
        Description description = mock(Description.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        boolean saveSuccess = sprintRepository.save(sprint);
        //Assert
        assertTrue(saveSuccess);
    }


    @Test
    @DisplayName("Save Sprint, with failure")
    public void saveSprintFailure(){
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprintA = mock(Sprint.class);
        Sprint sprintB = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectID projectID = mock(ProjectID.class);
        Description description = mock(Description.class);
        //Act
        when(sprintA.getSprintID()).thenReturn(sprintID);
        when(sprintB.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        sprintRepository.save(sprintA);
        when(sprintA.hasSprintID(sprintID.toString())).thenReturn(true);
        boolean saveFailure = sprintRepository.save(sprintB);
        //Assert
        assertFalse(saveFailure);
    }


    @Test
    @DisplayName("Test to search sprint by sprint id, with success")
    public void findSprintByID_Success() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectID projectID = mock(ProjectID.class);
        Description description = mock(Description.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprint.hasSprintID("Project_2022_1_Sprint Name")).thenReturn(true);
        sprintRepository.save(sprint);
        //Assert
        assertEquals(sprint, sprintRepository.findBySprintID("Project_2022_1_Sprint Name"));
    }

    @Test
    @DisplayName("Test to search sprint by sprint id, with failure")
    public void findSprintByID_Failure() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectID projectID = mock(ProjectID.class);
        Description description = mock(Description.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprint.hasSprintID("Project_2022_1_Sprint Name")).thenReturn(true);
        sprintRepository.save(sprint);
        //Assert
        assertNotEquals(sprint, sprintRepository.findBySprintID("Project_2022_1_Sprint XPTO"));
    }

    @Test
    @DisplayName("Test to search sprint by sprint ID, null ID")
    public void findSprintByID_Null() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        //Act
        Sprint sprint = sprintRepository.findBySprintID("Project_2022_1_Sprint Name");
        //Assert
        assertNull(sprint);
    }

    @Test
    @DisplayName("Test to search sprint list - find all sprints")
    public void findAllSprints() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);
        Sprint sprint3 = mock(Sprint.class);
        SprintID sprintID1 = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        SprintID sprintID3 = mock(SprintID.class);
        ProjectID projectID1 = mock(ProjectID.class);
        ProjectID projectID2 = mock(ProjectID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        Description description3 = mock(Description.class);
        //Act
        when(sprint1.getSprintID()).thenReturn(sprintID1);
        when(sprintID1.getSprintName()).thenReturn(description1);
        when(description1.getText()).thenReturn("Sprint One");
        when(sprintID1.getProjectID()).thenReturn(projectID1);
        when(projectID1.getCode()).thenReturn("Project_2022_1");
        sprintRepository.save(sprint1);

        when(sprint2.getSprintID()).thenReturn(sprintID2);
        when(sprintID2.getSprintName()).thenReturn(description2);
        when(description2.getText()).thenReturn("Sprint Two");
        when(sprintID2.getProjectID()).thenReturn(projectID2);
        when(projectID2.getCode()).thenReturn("Project_2022_2");
        sprintRepository.save(sprint2);

        when(sprint3.getSprintID()).thenReturn(sprintID3);
        when(sprintID3.getSprintName()).thenReturn(description3);
        when(description3.getText()).thenReturn("Sprint Three");
        when(sprintID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2022_3");
        sprintRepository.save(sprint3);
        //Assert
        assertEquals(3, sprintRepository.findAllSprints().size());
    }

    @Test
    @DisplayName("Test to delete a Sprint")
    public void deleteSprint() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);
        Sprint sprint3 = mock(Sprint.class);
        SprintID sprintID1 = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        SprintID sprintID3 = mock(SprintID.class);
        ProjectID projectID1 = mock(ProjectID.class);
        ProjectID projectID2 = mock(ProjectID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        Description description3 = mock(Description.class);

        when(sprint1.getSprintID()).thenReturn(sprintID1);
        when(sprintID1.getSprintName()).thenReturn(description1);
        when(description1.getText()).thenReturn("Sprint One");
        when(sprintID1.getProjectID()).thenReturn(projectID1);
        when(projectID1.getCode()).thenReturn("Project_2022_1");
        sprintRepository.save(sprint1);

        when(sprint2.getSprintID()).thenReturn(sprintID2);
        when(sprintID2.getSprintName()).thenReturn(description2);
        when(description2.getText()).thenReturn("Sprint Two");
        when(sprintID2.getProjectID()).thenReturn(projectID2);
        when(projectID2.getCode()).thenReturn("Project_2022_2");
        sprintRepository.save(sprint2);

        when(sprint3.getSprintID()).thenReturn(sprintID3);
        when(sprintID3.getSprintName()).thenReturn(description3);
        when(description3.getText()).thenReturn("Sprint Three");
        when(sprintID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2022_3");
        sprintRepository.save(sprint3);
        //Act
        when(sprint1.hasSprintID("Project_2022_1_Sprint One")).thenReturn(true);
        when(sprint2.hasSprintID("Project_2022_2_Sprint Two")).thenReturn(true);
        when(sprint3.hasSprintID("Project_2022_3_Sprint Three")).thenReturn(true);
        sprintRepository.deleteSprint(sprint3);
        //Assert
        assertEquals(2, sprintRepository.getSprints().size());
    }

    @Test
    @DisplayName("Test to search sprint list, by Project ID")
    public void findAllSprintsByProjectID() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);
        Sprint sprint3 = mock(Sprint.class);
        SprintID sprintID1 = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        SprintID sprintID3 = mock(SprintID.class);
        ProjectID projectID1 = mock(ProjectID.class);
        ProjectID projectID2 = mock(ProjectID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        Description description3 = mock(Description.class);
        //Act
        when(sprint1.getSprintID()).thenReturn(sprintID1);
        when(sprintID1.getSprintName()).thenReturn(description1);
        when(description1.getText()).thenReturn("Sprint One");
        when(sprintID1.getProjectID()).thenReturn(projectID1);
        when(projectID1.getCode()).thenReturn("Project_2022_1");
        sprintRepository.save(sprint1);

        when(sprint2.getSprintID()).thenReturn(sprintID2);
        when(sprintID2.getSprintName()).thenReturn(description2);
        when(description2.getText()).thenReturn("Sprint Two");
        when(sprintID2.getProjectID()).thenReturn(projectID2);
        when(projectID2.getCode()).thenReturn("Project_2022_1");
        sprintRepository.save(sprint2);

        when(sprint3.getSprintID()).thenReturn(sprintID3);
        when(sprintID3.getSprintName()).thenReturn(description3);
        when(description3.getText()).thenReturn("Sprint Three");
        when(sprintID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2022_2");
        sprintRepository.save(sprint3);
        //Assert
        assertEquals(2, sprintRepository.findAllSprintsByProjectID("Project_2022_1").size());
    }

    @Test
    @DisplayName("Override Test")
    public void overrideTest() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);
        Sprint sprint3 = mock(Sprint.class);
        SprintID sprintID1 = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        SprintID sprintID3 = mock(SprintID.class);
        ProjectID projectID1 = mock(ProjectID.class);
        ProjectID projectID2 = mock(ProjectID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        Description description3 = mock(Description.class);
        //Act
        when(sprint1.getSprintID()).thenReturn(sprintID1);
        when(sprintID1.getSprintName()).thenReturn(description1);
        when(description1.getText()).thenReturn("Sprint One");
        when(sprintID1.getProjectID()).thenReturn(projectID1);
        when(projectID1.getCode()).thenReturn("Project_2022_1");
        sprintRepository.save(sprint1);

        when(sprint2.getSprintID()).thenReturn(sprintID2);
        when(sprintID2.getSprintName()).thenReturn(description2);
        when(description2.getText()).thenReturn("Sprint Two");
        when(sprintID2.getProjectID()).thenReturn(projectID2);
        when(projectID2.getCode()).thenReturn("Project_2022_1");
        sprintRepository.save(sprint2);

        when(sprint3.getSprintID()).thenReturn(sprintID3);
        when(sprintID3.getSprintName()).thenReturn(description3);
        when(description3.getText()).thenReturn("Sprint Three");
        when(sprintID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2022_2");
        sprintRepository.save(sprint3);

        //Assert
        assertEquals(sprint1, sprint1);
        assertEquals(3, sprintRepository.getSprints().size());
    }

    @Test
    @DisplayName("HashCode Test")
    public void hashCodeTest() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);
        Sprint sprint3 = mock(Sprint.class);
        SprintID sprintID1 = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        SprintID sprintID3 = mock(SprintID.class);
        ProjectID projectID1 = mock(ProjectID.class);
        ProjectID projectID2 = mock(ProjectID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        Description description3 = mock(Description.class);
        //Act
        when(sprint1.getSprintID()).thenReturn(sprintID1);
        when(sprintID1.getSprintName()).thenReturn(description1);
        when(description1.getText()).thenReturn("Sprint One");
        when(sprintID1.getProjectID()).thenReturn(projectID1);
        when(projectID1.getCode()).thenReturn("Project_2022_1");
        sprintRepository.save(sprint1);

        when(sprint2.getSprintID()).thenReturn(sprintID2);
        when(sprintID2.getSprintName()).thenReturn(description2);
        when(description2.getText()).thenReturn("Sprint Two");
        when(sprintID2.getProjectID()).thenReturn(projectID2);
        when(projectID2.getCode()).thenReturn("Project_2022_1");
        sprintRepository.save(sprint2);

        when(sprint3.getSprintID()).thenReturn(sprintID3);
        when(sprintID3.getSprintName()).thenReturn(description3);
        when(description3.getText()).thenReturn("Sprint Three");
        when(sprintID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2022_2");
        sprintRepository.save(sprint3);

        List<Sprint> list = sprintRepository.findAllSprints();

        //Assert
        assertEquals(sprint1.hashCode(), sprint1.hashCode());
        assertNotEquals(sprint2.hashCode(), sprint3.hashCode());
        assertEquals(list.hashCode(), list.hashCode());
    }

 */
}

