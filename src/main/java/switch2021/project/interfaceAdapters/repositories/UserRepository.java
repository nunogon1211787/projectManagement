package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.dataModel.jpa.UserJpa;
import switch2021.project.dataModel.assembler.UserJpaAssembler;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import switch2021.project.persistence.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepo {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserJpaAssembler assembler;

    @Override
    public Optional<User> findByUserId(UserID userID) {
        Optional<UserJpa> foundUserJpa = userJpaRepository.findById(userID);

        if (foundUserJpa.isPresent()) {
            UserJpa userJpa = foundUserJpa.get();
            User user = assembler.toDomain(userJpa);

            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        List<UserJpa> userJpaList = userJpaRepository.findAll();
        List<User> userList = new ArrayList<>();

        if (userJpaList.isEmpty()) {
            throw new NullPointerException("Was not found any user!");
        }
        userJpaList.forEach(userJpa -> userList
                .add(assembler.toDomain(userJpa)));

        return userList;
    }

    @Override
    public List<User> findAllByNameContains(String name) {
        List<User> users = findAll();
        List<User> usersByName = new ArrayList<>();

        for (User user : users) {
            if (user.hasName(name)) {
                usersByName.add(user);
            }
        }
        return usersByName;
    }

    @Override
    public List<User> findAllByFunctionContains(String function) {
        List<User> users = findAll();
        List<User> usersByFunction = new ArrayList<>();

        for (User user : users) {
            if (user.hasFunction(function)) {
                usersByFunction.add(user);
            }
        }
        return usersByFunction;
    }

    @Override
    public List<User> findAllByUserProfileContains(UserProfileID profile) {
        List<User> users = findAll();
        List<User> usersByProfile = new ArrayList<>();

        for (User user : users) {
            if (user.hasProfile(profile)) {
                usersByProfile.add(user);
            }
        }
        return usersByProfile;
    }

    @Override
    public boolean existsById(UserID id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public User save(User newUser) {
        UserJpa userJpa = assembler.toData(newUser);

        UserJpa savedUserJpa = userJpaRepository.saveAndFlush(userJpa);

        return assembler.toDomain(savedUserJpa);
    }

    @Override
    public void delete(UserID userID) {
        if (!userJpaRepository.existsById(userID)) {
            throw new NullPointerException("User does not exists!");
        } else {
            userJpaRepository.deleteById(userID);
        }
    }
}