package switch2021.project.factoryInterface;

import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.model.SystemUser.User;

public interface IUserFactory {
    User createUser(NewUserInfoDTO infoDTO);
}
