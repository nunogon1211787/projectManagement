package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.LoginDto;
import switch2021.project.dtoModel.dto.OutputLoginDTO;
import switch2021.project.dtoModel.mapper.LoginMapper;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.Password;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    IUserRepo repo;

    @Autowired
    IUserIDFactory idFactory;

    @Autowired
    LoginMapper map;

    public OutputLoginDTO authentication(LoginDto login) throws Exception {

        User userLogged = repo.findByUserId(idFactory.createUserID(login.email));

            if(userLogged.checkPassword(new Password(login.password))){

                OutputLoginDTO result = map.toDto(userLogged);

                return result;
            };
        throw new Exception("Username or Password invalid");
    }
}
