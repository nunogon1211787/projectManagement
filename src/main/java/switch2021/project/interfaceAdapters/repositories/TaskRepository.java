package switch2021.project.interfaceAdapters.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.ITaskRepo;
import switch2021.project.dataModel.assembler.TaskJpaAssembler;
import switch2021.project.dataModel.jpa.TaskJpa;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.persistence.TaskJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository implements ITaskRepo {

    /*** Class Attributes **/
    @Autowired
    private TaskJpaRepository jpaRepo;
    @Autowired
    private TaskJpaAssembler assembler;

    /*** Class Methods **/

    @Override
    public Optional<Task> save(Task newTask) {

        TaskJpa taskJpa = assembler.toData(newTask);

        TaskJpa savedTaskJpa = jpaRepo.save(taskJpa);

        return Optional.of(assembler.toDomain(savedTaskJpa));
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
        /*Optional<TaskJpa> task = jpaRepo.findById(new TaskIDJpa(code, new Name(code)));

        if(task.isPresent())
        return assembler.toDomain(task.get());
        else

         */
            return null;
    }

    public boolean existById(String id) {
        return true;

    }
}
