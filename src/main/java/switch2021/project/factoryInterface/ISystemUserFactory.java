package switch2021.project.factoryInterface;

import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.model.SystemUser.SystemUser;

public interface ISystemUserFactory {
    SystemUser createSystemUser(NewUserInfoDTO infoDTO);
}
