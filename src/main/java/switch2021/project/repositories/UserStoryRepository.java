package switch2021.project.repositories;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.UserStoryJpa;
import switch2021.project.datamodel.assembler.UserStoryJpaAssembler;
import switch2021.project.interfaces.IUserStoryRepo;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.UserStoryID;
import switch2021.project.repositories.jpa.UserStoryJpaRepository;

import java.util.*;

@Getter
@Setter
@Repository
public class UserStoryRepository implements IUserStoryRepo {

    @Autowired
    private UserStoryJpaRepository jpaRepository;

    @Autowired
    private UserStoryJpaAssembler assembler;


    @Override
    public Optional<UserStory> save(UserStory newUserStory) {

        UserStoryJpa usJpa = assembler.toData(newUserStory);
        Optional<UserStory> userStory = Optional.empty();

        if(!jpaRepository.existsById(usJpa.getId())) {
            UserStoryJpa usJpaSaved = jpaRepository.save(usJpa);
            userStory = Optional.of(assembler.toDomain(usJpaSaved));
        }

        return userStory;
    }

    @Override
    public boolean deleteByUserStoryId(UserStoryID usId) {

        if (jpaRepository.existsById(usId)) {
            jpaRepository.deleteById(usId);
            return true;
        }

        return false;
    }

    @Override
    public Optional<UserStory> findByUserStoryId(UserStoryID userStoryID) {

        Optional<UserStoryJpa> usJpa = jpaRepository.findById(userStoryID);

        Optional<UserStory> result = Optional.empty();

        if(usJpa.isPresent()){
            result = Optional.of(assembler.toDomain(usJpa.get()));
        }

        return result;
    }

    /**
     * Finds all user stories.
     */

    @Override
    public List<UserStory> findAll() {

        List<UserStoryJpa> jpaList = jpaRepository.findAll();

        List<UserStory> modelList = new ArrayList<>();

        jpaList.forEach(usJpa -> modelList.add(assembler.toDomain(usJpa)));

        return modelList;
    }


}