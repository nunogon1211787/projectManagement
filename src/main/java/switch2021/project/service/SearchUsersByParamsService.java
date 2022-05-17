package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.dto.SearchUserDTO;
import switch2021.project.factoryInterface.IUserProfileIDFactory;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.mapper.UserMapper;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchUsersByParamsService {

    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IUserProfileRepo profRepo;
    @Autowired
    private UserMapper map;
    @Autowired
    private IUserProfileIDFactory profFactory;


    public List<OutputUserDTO> searchUsersByParams(SearchUserDTO inDto) {

        List<User> allFounded = new ArrayList<>();

        List<User> usersFounded = new ArrayList<>();

        List<OutputUserDTO> usersFoundedDto = new ArrayList<>();

        if(!inDto.id.isEmpty() || !inDto.id.isBlank())
            allFounded.addAll(userRepo.findAllBySystemUserIdContains(inDto.id));

        if(!inDto.name.isEmpty() || !inDto.name.isBlank())
            allFounded.addAll(userRepo.findAllByNameContains(inDto.name));

        if(!inDto.function.isEmpty() || !inDto.function.isBlank())
            allFounded.addAll(userRepo.findAllByFunctionContains(inDto.function));

        if (!inDto.profile.isEmpty() || !inDto.profile.isBlank()) {
            UserProfileID profile = profFactory.createUserProfileID(inDto.profile);

            if (profRepo.existsByUserProfileId(profile)) {

                allFounded.addAll(userRepo.findAllByUserProfileId(profile));

            }
        }

        allFounded.forEach(user -> {
            if(!usersFounded.contains(user)){
                usersFounded.add(user);
            }
        });

        usersFounded.forEach(user -> usersFoundedDto.add(map.toDto(user)));

        return usersFoundedDto;
    }

}
