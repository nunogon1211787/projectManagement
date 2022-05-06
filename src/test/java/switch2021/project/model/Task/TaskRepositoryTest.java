package switch2021.project.model.Task;

import org.junit.jupiter.api.Test;
import switch2021.project.interfaces.TaskContainerID;
//import switch2021.project.model.Resource.ResourceId;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryTest {

/*    @Test
    void createTaskUSTest() {

        UserProfile profile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());

        ResourceReeng resource = new ResourceReeng(new ResourceId(user.getSystemUserId(), new ProjectID("Project_2022_1")));


        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);

        TaskContainerID taskContainerID = userStory.getUserStoryID();

        TaskID taskID = new TaskID(taskContainerID, (new Name ("Custodio")));

        TaskReeng task = new TaskReeng(taskID);
        task.setName(new Name("Oscar"));
        task.setDescription(new Description("Isto é uma descrição"));
        task.setEffortEstimate(new EffortEstimate(12));
        task.setType(TaskTypeEnum.Design);
        task.setResponsible(resource.getId());
        task.setTaskContainerID(taskContainerID);

        TaskRepository x = new TaskRepository();
        x.save(task);

        assertEquals("Project_2022_1_As a PO, i want to test this string", task.getTaskContainerID().toString());

    }

 */

    @Test
    void createTaskSprint() {

        ResourceIDReeng resourceIDReeng = new ResourceIDReeng(new SystemUserID(new Email("manuelbras@beaver.com")), new ProjectID("Project_2022_1"), LocalDate.of(2022, 1, 1) );

        TaskContainerID taskContainerID = new SprintID("Project_2022_1_Sprint 1");

        TaskID taskID = new TaskID(taskContainerID, (new Name ("Custodio")));

        TaskReeng task = new TaskReeng(taskID);
        task.setDescription(new Description("Isto é uma descrição"));
        task.setEffortEstimate(new EffortEstimate(12));
        task.setType(TaskTypeEnum.Design);
        task.setResponsible(resourceIDReeng);
        task.setTaskContainerID(taskContainerID);

        TaskRepository x = new TaskRepository();
        x.save(task);

        List<TaskReeng> taskL =  new ArrayList<>();
        taskL.add(task);

        List<TaskReeng> taskLe =  x.findAll();

        assertEquals("Project_2022_1_Sprint 1", task.getTaskContainerID().toString());
        assertEquals(taskL, x.getTaskList());
        assertEquals(taskLe, x.getTaskList());

    }
}