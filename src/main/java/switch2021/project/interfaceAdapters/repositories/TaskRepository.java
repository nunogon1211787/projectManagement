package switch2021.project.interfaceAdapters.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.ITaskRepo;
import switch2021.project.dataModel.JPA.TaskJpa;
import switch2021.project.dataModel.JPA.assembler.TaskJpaAssembler;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TaskID;
import switch2021.project.persistence.TaskJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository implements ITaskRepo {

    /*** Class Attributes **/
    @Autowired
    private TaskJpaRepository taskJpaRepo;
    @Autowired
    private TaskJpaAssembler taskJpaAssembler;

    /*** Class Methods **/
    @Override
    public Task save(Task newTask) {
        TaskJpa taskJpa = taskJpaAssembler.toData(newTask);
        TaskJpa savedTaskJpa = taskJpaRepo.saveAndFlush(taskJpa);
        return taskJpaAssembler.toDomain(savedTaskJpa);
    }

    @Override
    public List<Task> findAll() {
        List<TaskJpa> jpaTasks = taskJpaRepo.findAll();
        List<Task> tasks = new ArrayList<>();

        for (TaskJpa taskJpa : jpaTasks) {
            Task task = taskJpaAssembler.toDomain(taskJpa);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public Optional<Task> findById(Description id) {
        Optional<TaskJpa> opTaskJpa = taskJpaRepo.findById(id);

        if (opTaskJpa.isPresent()) {
            TaskJpa taskJpa = opTaskJpa.get();

            Task task = taskJpaAssembler.toDomain(taskJpa);
            return Optional.of(task);
        } else
            return Optional.empty();
    }

    @Override
    public boolean existsById(Description id) {
        return taskJpaRepo.existsById(id);
    }
}
