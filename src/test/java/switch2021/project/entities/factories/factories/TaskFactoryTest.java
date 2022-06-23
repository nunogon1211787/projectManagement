package switch2021.project.entities.factories.factories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.applicationServices.iRepositories.ITaskRepo;
import switch2021.project.applicationServices.service.TaskService;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ISprintIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITaskIDFactory;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.persistence.TaskJpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskFactoryTest {

/*

    @MockBean
    ITaskIDFactory taskIDFactory;

    @MockBean
    Name name;

    @MockBean
    TaskID taskID;

    @MockBean
    Description description;

    @MockBean
    EffortEstimate effort;

    @MockBean
    ResourceID resID;

    @MockBean
    UserStoryID usID;

    @MockBean
    SprintID sprintID;

    @InjectMocks
    private TaskFactory taskFactory;


    @BeforeEach
    void TestConfiguration(){
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    private TaskFactory taskFactory;
    @Autowired
    private ITaskRepo taskRepo;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ITaskIDFactory taskIDFactory;

    @Test
    @DisplayName("createTask - with success")
    void createTaskWithSuccess() {
        //Arrange
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.projectId="Project_2022_3";
        taskDTO.sprintName="9";
        taskDTO.usTitle=null;
        taskDTO.systemUserID="tdc@mymail.com";
        taskDTO.resourceStartDate="2022-03-10";
        taskDTO.taskName="name";
        taskDTO.taskDescription="description";
        taskDTO.taskEffortEstimate=20.0;
        taskDTO.taskType="TESTING";

        Task newTask = taskFactory.createTask(taskDTO);
        //Act
        Task savedTask = taskRepo.save(newTask);
        //Assert
        assertNotNull(savedTask);
        assertEquals("TESTING", savedTask.getType().toString());
    }

    @Test
    @DisplayName("createTask - with success")
    void serviceCreateTaskWithSuccess() {
        //Arrange
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.projectId="Project_2022_3";
        taskDTO.sprintName="9";
        taskDTO.usTitle=null;
        taskDTO.systemUserID="tdc@mymail.com";
        taskDTO.resourceStartDate="2022-03-10";
        taskDTO.taskName="name2";
        taskDTO.taskDescription="description";
        taskDTO.taskEffortEstimate=20.0;
        taskDTO.taskType="TESTING";
        //Act
        OutputTaskDTO outputTaskDTO = taskService.createAndSaveTask(taskDTO);
        //Assert
        assertNotNull(outputTaskDTO);
        assertEquals("TESTING", outputTaskDTO.type);
    }*/
}