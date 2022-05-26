package switch2021.project.datamodel.assembler;


import org.springframework.stereotype.Component;
import switch2021.project.datamodel.Task.EffortJpa;
import switch2021.project.datamodel.Task.TaskJpa;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Task.TaskEffort;
import switch2021.project.model.Task.TaskID;
import switch2021.project.model.Task.TaskReeng;
import switch2021.project.model.Task.TaskTypeEnum;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.EffortEstimate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskJpaAssembler {

    public TaskJpa toData (TaskReeng task){

        TaskID id = task.getIdTask();
        String description = task.getDescription().getText();
        int effortEstimate = (int) Math.round(task.getEffortEstimate().getEffortHours());
        String type = task.getType().toString();
        ResourceIDReeng responsible = task.getResponsible();

        String startDate = null;
        String endDate = null;
        List<TaskID> precedenceList = new ArrayList<>();

        List<TaskEffort> taskEfList = task.getTaskEffortList();
        List<EffortJpa> effortJpas = new ArrayList<>();

        if (!(task.getStartDate() == null)) {
            startDate = task.getStartDate().toString();
        }
        if(!(task.getEndDate() == null)) {
            endDate = task.getEndDate().toString();
        }
        if(!(task.getTaskEffortList() == null)) {
            for(TaskEffort effort: taskEfList ){
                EffortJpa effortJpa = new EffortJpa(effort.getEffortHours(), effort.getEffortMinutes(), effort.getEffortDate(), effort.getComment(), effort.getAttachment());
                effortJpas.add(effortJpa);
            }
        }
        if(!(task.getPrecedenceList() == null)) {
            precedenceList = task.getPrecedenceList();
        }

        return new TaskJpa(id, description, type, effortEstimate, startDate, endDate, responsible, effortJpas, precedenceList);
    }

    public TaskReeng toDomain (TaskJpa task){

        TaskID id = task.getId();
        Description description = new Description(task.getDescription());
        EffortEstimate effortEstimate = new EffortEstimate(task.getEffortEstimate());
        TaskTypeEnum type = TaskTypeEnum.valueOf(task.getType());
        ResourceIDReeng responsible = task.getResponsible();

        LocalDate startDate = null;
        LocalDate endDate = null;
        List<TaskEffort> taskEffortList = new ArrayList<>();
        List<TaskID> precedenceList = new ArrayList<>();

        List<EffortJpa> effortJpas = task.getTaskEffortList();

        if (!(task.getStartDate() == null)) {
            startDate = LocalDate.parse(task.getStartDate());
        }
        if(!(task.getEndDate() == null)) {
            endDate = LocalDate.parse(task.getEndDate());
        }
        if(!(task.getTaskEffortList() == null)) {
            for (EffortJpa effort : effortJpas) {
                taskEffortList.add(new TaskEffort(effort.getEffortHours().getEffortHours(), effort.getEffortMinutes().getEffortMinutes(), effort.getEffortDate(), effort.getComment().toString(), effort.getAttachment().toString()));
            }
        }
        if(!(task.getPrecedenceList() == null)) {
            precedenceList = task.getPrecedenceList();
        }

        return new TaskReeng(id, description, type, effortEstimate, startDate, endDate, responsible, taskEffortList, precedenceList);
    }

}
