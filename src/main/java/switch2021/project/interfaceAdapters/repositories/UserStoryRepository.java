package switch2021.project.interfaceAdapters.repositories;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.dataModel.jpa.UserStoryJpa;
import switch2021.project.dataModel.assembler.UserStoryJpaAssembler;
import switch2021.project.applicationServices.iRepositories.IUserStoryRepo;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.valueObjects.vos.UserStoryID;
import switch2021.project.persistence.UserStoryJpaRepository;

import java.util.*;

@Getter
@Repository
public class UserStoryRepository implements IUserStoryRepo {

    @Autowired
    private UserStoryJpaRepository jpaRepository;
    @Autowired
    private UserStoryJpaAssembler assembler;


    @Override
    public Optional<UserStory> findByUserStoryId(UserStoryID userStoryID) {
        Optional<UserStoryJpa> opUsJpa = jpaRepository.findById(userStoryID);

        if(opUsJpa.isPresent()) {
            UserStoryJpa usJpa = opUsJpa.get();
            UserStory userStory = assembler.toDomain(usJpa);

            return Optional.of(userStory);
        }
        return Optional.empty();
    }

    @Override
    public List<UserStory> findAll() {
        return assembler.toDomain(jpaRepository.findAll());
    }

    @Override
    public List<UserStory> findProductBacklog(String projectId) {
        List<UserStory> userStories = findAll();
        List<UserStory> produckBacklog = new ArrayList<>();

        for (UserStory us : userStories) {
            if (us.hasProjectId(projectId)) {
                produckBacklog.add(us);
            }
        }
        Comparator<UserStory> compareByPriority = Comparator.comparing(UserStory::getPriority);
        produckBacklog.sort(compareByPriority);

        return produckBacklog;
    }

    @Override
    public boolean existsUserStoryByID(UserStoryID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public UserStory save(UserStory newUserStory) {
        UserStoryJpa usJpa = assembler.toData(newUserStory);

        UserStoryJpa usJpaSaved = jpaRepository.saveAndFlush(usJpa);

        return assembler.toDomain(usJpaSaved);
    }

    @Override
    public void deleteByUserStoryId(UserStoryID usId) throws NullPointerException {

        if (!jpaRepository.existsById(usId)) {
            throw new NullPointerException("User Story does not exist");
        }
        jpaRepository.deleteById(usId);
    }
}