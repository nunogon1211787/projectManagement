package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    private UserJpaAssembler userJpaAssembler;

    @Override
    public Optional<User> findByUserId(UserID userID) {
        Optional<UserJpa> foundUserJpa = userJpaRepository.findById(userID);

        if (foundUserJpa.isPresent()) {
            User user = userJpaAssembler.toDomain(foundUserJpa.get());
            return Optional.of(user);
        } else
            return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<UserJpa> userJpaList = userJpaRepository.findAll();
        List<User> userList = new ArrayList<>();

        userJpaList.forEach(userJpa -> userList.add(userJpaAssembler.toDomain(userJpa)));

        return userList;
    }

    @Override
    public List<User> findAllByNameContains(String name) {
        List<UserJpa> usersJpa = userJpaRepository.findAll();
        List<User> users = userJpaAssembler.toDomain(usersJpa);

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
        List<UserJpa> usersJpa = userJpaRepository.findAll();
        List<User> users = userJpaAssembler.toDomain(usersJpa);

        List<User> usersByFunction = new ArrayList<>();

        for (User user : users) {
            if (user.hasFunction(function)) {
                usersByFunction.add(user);
            }
        }
        return usersByFunction;
    }

    @Override
    public List<User> findAllByUserProfileId(UserProfileID profile) {
        List<UserJpa> usersJpa = userJpaRepository.findAll();
        List<User> users = userJpaAssembler.toDomain(usersJpa);

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
    public Optional<User> update(User user) {
        UserJpa userJpa = userJpaAssembler.toData(user);

        UserJpa savedUserJpa = userJpaRepository.saveAndFlush(userJpa);
        userJpaRepository.flush();

        return Optional.of(userJpaAssembler.toDomain(savedUserJpa));
    }

    @Override
    public Optional<User> save(User newUser) {

        UserJpa userJpa = userJpaAssembler.toData(newUser);
        Optional<User> user = Optional.empty();

        if (!userJpaRepository.existsById(userJpa.getEmail())) {
            UserJpa savedUserJpa = userJpaRepository.saveAndFlush(userJpa);
            user = Optional.of(userJpaAssembler.toDomain(savedUserJpa));
        }
        //userJpaRepository.sav();
        return user;
    }

    @Override
    public boolean delete(UserID userID) {

        if (userJpaRepository.existsById(userID)) {
            userJpaRepository.deleteById(userID);
            return true;
        }
        return false;
    }
}