package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.dto.SearchUserDTO;
import switch2021.project.factoryInterface.IUserProfileIDFactory;
import switch2021.project.interfaces.ISystemUserRepo;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.mapper.SystemUserMapper;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchUsersByParamsService {

    @Autowired
    private ISystemUserRepo userRepo;
    @Autowired
    private IUserProfileRepo profRepo;
    @Autowired
    private SystemUserMapper map;
    @Autowired
    private IUserProfileIDFactory profFactory;


    public List<OutputUserDTO> searchUsersByParams(SearchUserDTO inDto) {

        List<SystemUser> allFounded = new ArrayList<>();

        List<SystemUser> usersFounded = new ArrayList<>();

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
