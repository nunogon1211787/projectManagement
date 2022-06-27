package switch2021.project.applicationServices.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.ISprintRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryOfSprintRepo;
import switch2021.project.dtoModel.dto.OutputSprintDTO;
import switch2021.project.dtoModel.dto.StartSprintDTO;
import switch2021.project.dtoModel.dto.UserStoryOfSprintDTO;
import switch2021.project.dtoModel.mapper.SprintMapper;
import switch2021.project.dtoModel.mapper.UserStoryOfSprintMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.valueObjects.vos.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SprintServiceTest {

    @InjectMocks
    SprintService sprintService;
    @Mock
    private ISprintRepo sprintRepo;
    @Mock
    private SprintMapper sprintMapper;
    @Mock
    private StartSprintDTO startSprintDTO;
    @Mock
    private IProjectRepo projRepo;
    @Mock
    private IUserStoryOfSprintRepo userStoryOfSprintRepo;
    @Mock
    private UserStoryOfSprintMapper userStoryOfSprintMapper;
    @Mock
    private Sprint sprint;
    @Mock
    private OutputSprintDTO outputSprintDTO;
    @Mock
    private Project project;
    @Mock
    private SprintDuration sprintDurationClass;
    @Mock
    private UserStoryOfSprint userStoryOfSprint;
    @Mock
    private UserStoryOfSprintDTO userStoryOfSprintDTO;

    @Test
    @DisplayName("Show all sprints")
    void showAllSprints() {
        //Arrange
        List<Sprint> expected = new ArrayList<>();
        CollectionModel<OutputSprintDTO> expectedDto = CollectionModel.empty();
        when(sprintRepo.findAllSprints()).thenReturn(expected);
        when(sprintMapper.toCollectionDto(expected)).thenReturn(expectedDto);

        //Act
        CollectionModel<OutputSprintDTO> result = sprintService.showAllSprints();

        //Assert
        assertEquals(expectedDto, result);

    }

    @Test
    @SneakyThrows
    @DisplayName("DeleteSprint success")
    void deleteSprints_success() {
        //Arrange
        when(sprintRepo.deleteSprint(any())).thenReturn(true);
        SprintID id = mock(SprintID.class);

        //Act
        boolean result = sprintService.deleteSprint(id);

        //Assert
        assertTrue(result);
    }

    @Test
    @SneakyThrows
    @DisplayName("DeleteSprint fail")
    void deleteSprints_fail() {
        assertThrows(Exception.class, () -> {
            //Arrange
            when(sprintRepo.deleteSprint(any())).thenReturn(false);
            SprintID id = mock(SprintID.class);
            //Act
            sprintService.deleteSprint(id);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all Sprints by ProjectId")
    void showAllSprintsByProjectId_success() {
        //Arrange
        List<Sprint> expected = new ArrayList<>();
        when(projRepo.existsById(any())).thenReturn(true);
        CollectionModel<OutputSprintDTO> expectedDto = CollectionModel.empty();
        when(sprintRepo.findAllSprintsByProjectID(any())).thenReturn(expected);
        when(sprintMapper.toCollectionDto(expected)).thenReturn(expectedDto);

        //Act
        CollectionModel<OutputSprintDTO> result = sprintService.showSprintsOfAProject("id");

        //Assert
        assertEquals(expectedDto, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all sprints fail")
    void showAllSprintsByProjectId_fail() {
        assertThrows(Exception.class, () -> {
            //Arrange
            List<Sprint> expected = new ArrayList<>();
            when(projRepo.existsById(any())).thenReturn(false);

            //Act
            sprintService.showSprintsOfAProject("id");
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all Sprints by ProjectId")
    void showScrumBoard_success() {
        //Arrange
        SprintID id = mock(SprintID.class);
        String string_id = "Project_2022_1_1";
        List<UserStoryOfSprint> usList = new ArrayList<>();
        CollectionModel<UserStoryOfSprintDTO> expectedDto = CollectionModel.empty();
        when(userStoryOfSprintRepo.findAllUserStoriesBySprintID(id)).thenReturn(usList);
        when(userStoryOfSprintMapper.toCollectionDTO(any())).thenReturn(expectedDto);

        //Act
        CollectionModel<UserStoryOfSprintDTO> result = sprintService.showScrumBoard(string_id);

        //Arrange
        assertEquals(expectedDto, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Validate dates success")
    void validateDates_success() {
        //Arrange
        String sprintId = "Project_2020_1_1";
        String date = "2012-01-01";
        LocalDate startDate = LocalDate.parse("2010-01-01");
        LocalDate endDate = LocalDate.parse("2030-01-01");
        Optional<Project> optional = Optional.of(project);
        long expected = 11;

        when(projRepo.findById(any())).thenReturn(optional);
        when(project.getSprintDuration()).thenReturn(sprintDurationClass);
        when(sprintDurationClass.getSprintDurationDays()).thenReturn(expected);
        when(project.getStartDate()).thenReturn(startDate);
        when(project.getEndDate()).thenReturn(endDate);

        //Act
        long result = sprintService.validateSprintStartDate(sprintId, date);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Validate dates project not found")
    void validateDates_fail_project_not_found() {
        assertThrows(Exception.class, () -> {
            //Arrange
            String sprintId = "Project_2020_1_1";
            String date = "2022-01-01";
            LocalDate startDate = LocalDate.parse("2010-01-01");
            LocalDate endDate = LocalDate.parse("2030-01-01");
            Optional<Project> optional = Optional.empty();
            long expected = 11;

            when(projRepo.findById(any())).thenReturn(optional);
            when(project.getSprintDuration()).thenReturn(sprintDurationClass);
            when(sprintDurationClass.getSprintDurationDays()).thenReturn(expected);
            when(project.getStartDate()).thenReturn(startDate);
            when(project.getEndDate()).thenReturn(endDate);

            //Act
            sprintService.validateSprintStartDate(sprintId, date);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Validate end date fail")
    void validateDates_endDate_fail() {
        assertThrows(Exception.class, () -> {
            //Arrange
            String sprintId = "Project_2020_1_1";
            String date = "2031-01-01";
            LocalDate startDate = LocalDate.parse("2010-01-01");
            LocalDate endDate = LocalDate.parse("2030-01-01");
            Optional<Project> optional = Optional.of(project);
            long expected = 11;

            when(projRepo.findById(any())).thenReturn(optional);
            when(project.getSprintDuration()).thenReturn(sprintDurationClass);
            when(sprintDurationClass.getSprintDurationDays()).thenReturn(expected);
            when(project.getStartDate()).thenReturn(startDate);
            when(project.getEndDate()).thenReturn(endDate);

            //Act
            sprintService.validateSprintStartDate(sprintId, date);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Validate start date fail")
    void validateDates_startDate_fail() {
        assertThrows(Exception.class, () -> {
            //Arrange
            String sprintId = "Project_2020_1_1";
            String date = "2009-01-01";
            LocalDate startDate = LocalDate.parse("2010-01-01");
            LocalDate endDate = LocalDate.parse("2030-01-01");
            Optional<Project> optional = Optional.of(project);
            long expected = 11;

            when(projRepo.findById(any())).thenReturn(optional);
            when(project.getSprintDuration()).thenReturn(sprintDurationClass);
            when(sprintDurationClass.getSprintDurationDays()).thenReturn(expected);
            when(project.getStartDate()).thenReturn(startDate);
            when(project.getEndDate()).thenReturn(endDate);

            //Act
            sprintService.validateSprintStartDate(sprintId, date);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Start a Sprint")
    void startASprint() {
        //Arrange
        String sprintId = "Project_2020_1_1";
        String date = "2012-01-01";
        LocalDate startDate = LocalDate.parse("2010-01-01");
        LocalDate endDate = LocalDate.parse("2030-01-01");
        Optional<Project> optional = Optional.of(project);
        Optional<Sprint> optionalSprint = Optional.of(sprint);
        long sprintDuration = 11;

        when(startSprintDTO.getStartDate()).thenReturn(date);
        when(projRepo.findById(any())).thenReturn(optional);
        when(project.getSprintDuration()).thenReturn(sprintDurationClass);
        when(sprintDurationClass.getSprintDurationDays()).thenReturn(sprintDuration);
        when(project.getStartDate()).thenReturn(startDate);
        when(project.getEndDate()).thenReturn(endDate);
        when(sprintRepo.findBySprintID(any())).thenReturn(optionalSprint);
        doNothing().when(sprint).setStartDate(any());
        doNothing().when(sprint).setEndDate(any());
        when(sprintRepo.save(sprint)).thenReturn(sprint);
        when(sprintMapper.toDTO(sprint)).thenReturn(outputSprintDTO);

        //Act
        OutputSprintDTO result = sprintService.startSprint(sprintId, startSprintDTO);

        //Assert
        assertEquals(outputSprintDTO, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Start a Sprint fail")
    void startASprint_fail() {
        //Arrange
        assertThrows(Exception.class, () -> {
            String sprintId = "Project_2020_1_1";
            String date = "2012-01-01";
            LocalDate startDate = LocalDate.parse("2010-01-01");
            LocalDate endDate = LocalDate.parse("2030-01-01");
            Optional<Project> optional = Optional.of(project);
            Optional<Sprint> optionalSprint = Optional.empty();
            long sprintDuration = 11;

            when(startSprintDTO.getStartDate()).thenReturn(date);
            when(projRepo.findById(any())).thenReturn(optional);
            when(project.getSprintDuration()).thenReturn(sprintDurationClass);
            when(sprintDurationClass.getSprintDurationDays()).thenReturn(sprintDuration);
            when(project.getStartDate()).thenReturn(startDate);
            when(project.getEndDate()).thenReturn(endDate);
            when(sprintRepo.findBySprintID(any())).thenReturn(optionalSprint);


            //Act
            sprintService.startSprint(sprintId, startSprintDTO);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Change scrum board category success")
    void changeScrumBoardCategory_success() {
        //Arrange
        String sprintId = "Project_2020_1_1";
        String proj = "Project_2022_1";
        List<UserStoryOfSprint> userStoryOfSprintList = new ArrayList<>();
        userStoryOfSprintList.add(userStoryOfSprint);
        UserStoryID userStoryID = mock(UserStoryID.class);
        ProjectID projectID = mock(ProjectID.class);
        UserStoryOfSprintDTO dto = mock(UserStoryOfSprintDTO.class);
        UsTitle title = mock(UsTitle.class);

        when(userStoryOfSprintRepo.findAllUserStoriesBySprintID(any())).thenReturn(userStoryOfSprintList);
        when(userStoryOfSprint.getUserStoryId()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn(proj);
        when(dto.getProjectId()).thenReturn(proj);
        when(userStoryID.getUsTitle()).thenReturn(title);
        when(title.getTitleUs()).thenReturn("title");
        when(dto.getUsTitle()).thenReturn("title");
        when(userStoryOfSprint.getSprintName()).thenReturn("1");
        when(dto.getStatus()).thenReturn("Todo");
        doNothing().when(userStoryOfSprint).setUserStoryOfSprintStatus(any());
        when(userStoryOfSprintRepo.save(any())).thenReturn(userStoryOfSprint);
        when(userStoryOfSprintMapper.toDTO(userStoryOfSprint)).thenReturn(userStoryOfSprintDTO);

        //Act
        UserStoryOfSprintDTO result = sprintService.changeStatusScrumBoard(sprintId, dto);

        //Assert
        assertEquals(userStoryOfSprintDTO, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Change scrum board category fail no user Story")
    void changeScrumBoardCategory_fail() {
        assertThrows(Exception.class, () -> {
            //Arrange
            String sprintId = "Project_2020_1_1";
            String proj = "Project_2022_1";
            List<UserStoryOfSprint> userStoryOfSprintList = new ArrayList<>();
            userStoryOfSprintList.add(userStoryOfSprint);
            UserStoryID userStoryID = mock(UserStoryID.class);
            ProjectID projectID = mock(ProjectID.class);
            UserStoryOfSprintDTO dto = mock(UserStoryOfSprintDTO.class);
            UsTitle title = mock(UsTitle.class);

            when(userStoryOfSprintRepo.findAllUserStoriesBySprintID(any())).thenReturn(userStoryOfSprintList);
            when(userStoryOfSprint.getUserStoryId()).thenReturn(userStoryID);
            when(userStoryID.getProjectID()).thenReturn(projectID);
            when(projectID.getCode()).thenReturn(proj);
            when(dto.getProjectId()).thenReturn("not");

            //Act
            sprintService.changeStatusScrumBoard(sprintId, dto);
        });
    }
}



