package switch2021.project.interfaceAdapters.repositories;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.applicationServices.iRepositories.TaskRepositoryInterface;
import switch2021.project.dataModel.Task.TaskIDJpa;
import switch2021.project.dataModel.Task.TaskJpa;
import switch2021.project.dataModel.assembler.TaskJpaAssembler;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.valueObjects.vos.Name;
import switch2021.project.persistence.TaskJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Component
public class TaskRepository implements TaskRepositoryInterface {

    /*** Class Attributes **/


    @Autowired
    private TaskJpaRepository jpaRepo;

    @Autowired
    private TaskJpaAssembler assembler;

    /*** Class Methods **/

    public Task save(Task newTask) {

        TaskJpa toSaveTask = assembler.toData2(newTask);

        TaskJpa savedTask= jpaRepo.save(toSaveTask);

        Task finalTask = assembler.toDomain(savedTask);

        return finalTask;
    }

    public List<Task> findAll() {
        List <TaskJpa> allTasks = jpaRepo.findAll();

        List <Task> finalListTask = new ArrayList<>();

        for(TaskJpa task: allTasks){
            finalListTask.add(assembler.toDomain(task));
        }

        return finalListTask;
    }

    public Task findById(String code) {
        Optional<TaskJpa> task = jpaRepo.findById(new TaskIDJpa(code, new Name(code)));

        if(task.isPresent())
        return assembler.toDomain(task.get());
        else
            return null;
    }

    public boolean existById(String id) {
        return true;

    }
}
