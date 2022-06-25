package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskEffortDTO;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.valueObjects.vos.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public OutputTaskDTO toDto(Task task) {
        OutputTaskDTO outputTaskDTO = new OutputTaskDTO();
        outputTaskDTO.taskID = task.getTaskID().toString();
        outputTaskDTO.taskTitle = task.getTaskID().getTaskTitle().getText();

        TaskContainerID sprintOrUsID = task.getTaskID().getTaskContainerID();
        ProjectID projectID;
        UsTitle usTitle;
        Description sprintName;
        if (sprintOrUsID instanceof UserStoryID) {
            projectID = ((UserStoryID) sprintOrUsID).getProjectID();
            usTitle = ((UserStoryID) sprintOrUsID).getUsTitle();
            outputTaskDTO.sprintNameOrUsTitle = usTitle.getTitleUs();
            outputTaskDTO.projectID = projectID.getCode();
        }
        if (sprintOrUsID instanceof SprintID) {
            projectID = ((SprintID) sprintOrUsID).getProjectID();
            sprintName = ((SprintID) sprintOrUsID).getSprintName();
            outputTaskDTO.sprintNameOrUsTitle = sprintName.getText();
            outputTaskDTO.projectID = projectID.getCode();
        }
        outputTaskDTO.resourceID = task.getResponsible().toString();
        outputTaskDTO.userID = task.getResponsible().getUser().getEmail().getEmailText();
        outputTaskDTO.resourceStartDate = task.getResponsible().getStartDate().toString();
        outputTaskDTO.description = task.getDescription().getText();
        outputTaskDTO.type = task.getType().toString();
        outputTaskDTO.status = task.getStatus().toString();
        outputTaskDTO.effortEstimate = task.getEffortEstimate().getEffortHours();
        if (task.getStartDate() != null) {
            outputTaskDTO.taskStartDate = task.getStartDate().toString();
        }
        if (task.getEndDate() != null) {
            outputTaskDTO.taskEndDate = task.getEndDate().toString();
        }
        outputTaskDTO.registeredEfforts = effortsToString(task);
        outputTaskDTO.hoursSpent = Math.round(getHoursSpent(task.getRegisteredEfforts()) * 100.0) / 100.0;
        outputTaskDTO.percentageOfExecution =
                Math.round(((outputTaskDTO.hoursSpent / outputTaskDTO.effortEstimate) * 100) * 10.0) / 10.0;

        return outputTaskDTO;
    }

    private List<TaskEffortDTO> effortsToString(Task task) {
        return task.getRegisteredEfforts().stream()
                .map(this::effortToDTO)
                .collect(Collectors.toList());

    }

    public TaskEffortDTO effortToDTO(TaskEffort taskEffort) {
        TaskEffortDTO dto = new TaskEffortDTO();
        dto.effortHours = taskEffort.getEffortHours().getEffortHours();
        dto.effortMinutes = taskEffort.getEffortMinutes().getEffortMinutes();
        dto.effortDate = taskEffort.getEffortDate().getEffortDate().toString();
        dto.comment = taskEffort.getComment().getText();
        dto.attachment = taskEffort.getAttachment().getExtension();

        return dto;
    }

    private double getHoursSpent(List<TaskEffort> taskEffortList) {
        double x = 0;
        for (TaskEffort i : taskEffortList) {
            x = x + effortInHours(i);
        }
        return x;
    }

    private double effortInHours(TaskEffort effort) {
        return (double) effort.getEffortHours().getEffortHours()
                + ((double) effort.getEffortMinutes().getEffortMinutes() / 60);
    }

    public CollectionModel<OutputTaskDTO> toCollectionDto(List<Task> tasks) {

        CollectionModel<OutputTaskDTO> result = CollectionModel.of(tasks.stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
        return result;
    }
}
