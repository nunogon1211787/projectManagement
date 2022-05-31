package switch2021.project.dataModel.assembler;


import org.springframework.stereotype.Component;

import switch2021.project.dataModel.Task.EffortJpa;
import switch2021.project.dataModel.Task.TaskIDJpa;
import switch2021.project.dataModel.Task.TaskJpa;
import switch2021.project.dataModel.jpa.ResourceIDJpa;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

import switch2021.project.entities.aggregates.Task.TaskReeng;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskJpaAssembler {

//    public TaskJpa toData (TaskReeng task){
//
//        TaskID id = task.getIdTask();
//        String description = task.getDescription().getText();
//        int effortEstimate = (int) Math.round(task.getEffortEstimate().getEffortHours());
//        String type = task.getType().toString();
//        ResourceIDReeng responsible = task.getResponsible();
//
//        ResourceIDJpa resourceIDJpa = new ResourceIDJpa(responsible.getUser(), responsible.getProject(), responsible.getStartDate().toString());
//
//        String startDate = null;
//        String endDate = null;
//        List<TaskID> precedenceList = new ArrayList<>();
//
//        List<TaskEffort> taskEfList = task.getTaskEffortList();
//        List<EffortJpa> effortJpas = new ArrayList<>();
//
//        if (!(task.getStartDate() == null)) {
//            startDate = task.getStartDate().toString();
//        }
//        if(!(task.getEndDate() == null)) {
//            endDate = task.getEndDate().toString();
//        }
//        if(!(task.getTaskEffortList() == null)) {
//            for(TaskEffort effort: taskEfList ){
//                EffortJpa effortJpa = new EffortJpa(task.getIdTask(), effort.getEffortHours().getEffortHours(), effort.getEffortMinutes().getEffortMinutes(), effort.getEffortDate().toString(), effort.getComment().toString(), effort.getAttachment().toString());
//                effortJpas.add(effortJpa);
//            }
//        }
//        if(!(task.getPrecedenceList() == null)) {
//            precedenceList = task.getPrecedenceList();
//        }
//
//        return new TaskJpa(id, description, type, effortEstimate, startDate, endDate, resourceIDJpa, effortJpas, precedenceList);
//    }

    public TaskJpa toData2 (TaskReeng task){

        TaskID id = task.getIdTask();
        String description = task.getDescription().getText();
        int effortEstimate = (int) Math.round(task.getEffortEstimate().getEffortHours());
        String type = task.getType().toString();
        ResourceIDReeng responsible = task.getResponsible();

        ResourceIDJpa resourceIDJpa = new ResourceIDJpa(responsible.getUser(), responsible.getProject(), responsible.getStartDate().toString());

        String startDate = null;
        String endDate = null;
        List<TaskID> precedenceList = new ArrayList<>();
        List<TaskEffort> taskEfList = task.getTaskEffortList();

        if (!(task.getStartDate() == null)) {
            startDate = task.getStartDate().toString();
        }
        if(!(task.getEndDate() == null)) {
            endDate = task.getEndDate().toString();
        }

        if(!(task.getPrecedenceList() == null)) {
            precedenceList = task.getPrecedenceList();
        }

        TaskJpa x = new TaskJpa(id, description, type, effortEstimate, startDate, endDate, resourceIDJpa, precedenceList);

        if(!(task.getTaskEffortList() == null)) {
            for(TaskEffort effort: taskEfList ){
                EffortJpa effortJpa = new EffortJpa(effort.getEffortHours().getEffortHours(), effort.getEffortMinutes().getEffortMinutes(), effort.getEffortDate().toString(), effort.getComment(), effort.getAttachment().toString());
                x.getTaskEffortList().add(effortJpa);
            }
        }
        return x;
    }

    public TaskReeng toDomain (TaskJpa task){

        TaskIDJpa id = task.getId();
        Description description = new Description(task.getDescription());
        EffortEstimate effortEstimate = new EffortEstimate(task.getEffortEstimate());
        TaskTypeEnum type = TaskTypeEnum.valueOf(task.getType());
        ResourceIDReeng responsible = new ResourceIDReeng(task.getResponsible().getUser(), task.getResponsible().getProject(), LocalDate.parse(task.getResponsible().getStartDate()));

        TaskID idFinal = new TaskID( new SprintID(), id.getTaskName());
        LocalDate startDate = null;
        LocalDate endDate = null;
        List<TaskEffort> taskEffortList = new ArrayList<>();
        List<TaskID> precedenceList = new ArrayList<>();

        List<EffortJpa> effortJpas = task.getTaskEffortList();

        if (!(task.getStartDate2() == null)) {
            startDate = LocalDate.parse(task.getStartDate2());
        }
        if(!(task.getEndDate() == null)) {
            endDate = LocalDate.parse(task.getEndDate());
        }
        if(!(task.getTaskEffortList() == null)) {
            for (EffortJpa effort : effortJpas) {
                taskEffortList.add(new TaskEffort(effort.getEffortHours(), effort.getEffortMinutes(), new Date(LocalDate.parse(effort.getEffortDate())), effort.getComment().toString(), effort.getAttachment().toString()));
            }
        }
        if(!(task.getPrecedenceList() == null)) {
            precedenceList = task.getPrecedenceList();
        }

        return new TaskReeng(idFinal, description, type, effortEstimate, startDate, endDate, responsible, taskEffortList, precedenceList);
    }

}
