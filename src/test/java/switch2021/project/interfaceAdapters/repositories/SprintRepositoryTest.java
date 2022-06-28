package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.JPA.SprintJpa;
import switch2021.project.dataModel.JPA.assembler.SprintJpaAssembler;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.persistence.SprintJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SprintRepositoryTest {

    @MockBean
    SprintJpaRepository sprintJpaRepository;

    @MockBean
    SprintJpaAssembler sprintJpaAssembler;

    @InjectMocks
    SprintRepository sprintRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Save Sprint, with success")
    public void saveSprintSuccess() {
        //Arrange
        SprintJpa sprintJpa = mock(SprintJpa.class);
        SprintJpa savedSprint = mock(SprintJpa.class);
        Sprint sprint = mock(Sprint.class);
        when(sprintJpaAssembler.toData(sprint)).thenReturn(sprintJpa);
        when(sprintJpaRepository.save(sprintJpa)).thenReturn(savedSprint);
        when(sprintJpaAssembler.toDomain(savedSprint)).thenReturn(sprint);
        //Act
        Sprint result = sprintRepository.save(sprint);
        //Assert
        assertEquals(result, sprint);

    }


    @Test
    @DisplayName("Find Sprint, by ID")
    public void findByID() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        SprintJpa sprintJpa = mock(SprintJpa.class);
        Sprint sprint = mock(Sprint.class);
        when(sprintJpaRepository.findById(sprintID)).thenReturn(Optional.of(sprintJpa));
        when(sprintJpaAssembler.toDomain(sprintJpa)).thenReturn(sprint);
        //Act
        Optional<Sprint> result = sprintRepository.findBySprintID(sprintID);
        //Assert
        assertEquals(result, Optional.of(sprint));
    }


    @Test
    @DisplayName("Find All Sprints")
    public void findAll_Success() {
        //Arrange
        SprintJpa sprintJpa1 = mock(SprintJpa.class);
        SprintJpa sprintJpa2 = mock(SprintJpa.class);
        SprintJpa sprintJpa3 = mock(SprintJpa.class);

        List<SprintJpa> sprintJPAList = new ArrayList<>();
        sprintJPAList.add(sprintJpa1);
        sprintJPAList.add(sprintJpa2);
        sprintJPAList.add(sprintJpa3);

        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);
        Sprint sprint3 = mock(Sprint.class);
        List<Sprint> sprintList = new ArrayList<>();
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        when(sprintJpaRepository.findAll()).thenReturn(sprintJPAList);
        when(sprintJpaAssembler.toDomain(sprintJpa1)).thenReturn(sprint1);
        when(sprintJpaAssembler.toDomain(sprintJpa2)).thenReturn(sprint2);
        when(sprintJpaAssembler.toDomain(sprintJpa3)).thenReturn(sprint3);
        //Act
        List<Sprint> result = sprintRepository.findAllSprints();
        //Assert
        assertEquals(result, sprintList);
    }



    @Test
    @DisplayName("Find All Sprints")
    public void findAll_Fail() {
        //Arrange
        SprintJpa sprintJpa1 = mock(SprintJpa.class);
        SprintJpa sprintJpa2 = mock(SprintJpa.class);
        SprintJpa sprintJpa3 = mock(SprintJpa.class);

        List<SprintJpa> sprintJPAList = new ArrayList<>();
        sprintJPAList.add(sprintJpa1);
        sprintJPAList.add(sprintJpa2);
        sprintJPAList.add(sprintJpa3);

        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);
        Sprint sprint3 = mock(Sprint.class);
        Sprint sprint4 = mock(Sprint.class);
        List<Sprint> sprintList = new ArrayList<>();
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);
        sprintList.add(sprint4);

        when(sprintJpaRepository.findAll()).thenReturn(sprintJPAList);
        when(sprintJpaAssembler.toDomain(sprintJpa1)).thenReturn(sprint1);
        when(sprintJpaAssembler.toDomain(sprintJpa2)).thenReturn(sprint2);
        when(sprintJpaAssembler.toDomain(sprintJpa3)).thenReturn(sprint3);
        //Act
        List<Sprint> result = sprintRepository.findAllSprints();
        //Assert
        assertNotEquals(result, sprintList);
    }

    @Test
    @DisplayName("Delete Sprint")
    public void deleteSprint_Success() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        when(sprintJpaRepository.existsById(sprintID)).thenReturn(true);
        //Act
        boolean deleted = sprintRepository.deleteSprint(sprintID);
        //Assert
        assertTrue(deleted);
    }


    @Test
    @DisplayName("Delete Sprint")
    public void deleteSprint_Fail() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        when(sprintJpaRepository.existsById(sprintID)).thenReturn(false);
        //Act
        boolean deleted = sprintRepository.deleteSprint(sprintID);
        //Assert
        assertFalse(deleted);
    }


    @Test
    @DisplayName("Exists Sprint By ID")
    public void existsSprintByID_Fail() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        when(sprintJpaRepository.existsById(sprintID)).thenReturn(false);
        //Act
        boolean exists = sprintRepository.existsBySprintID("sprintID");
        //Assert
        assertFalse(exists);
    }

}

