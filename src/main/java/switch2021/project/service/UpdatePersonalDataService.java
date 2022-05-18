package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.dto.UpdateDataDTO;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.mapper.UserMapper;
import switch2021.project.model.SystemUser.User;

import java.util.Optional;


@Service
public class UpdatePersonalDataService {

    @Autowired
    private IUserRepo iUserRepo;
    @Autowired
    private UserMapper userMapper;


    public OutputUserDTO updatePersonalData(IdDTO idDTO, UpdateDataDTO updateDataDTO) {

        User user = iUserRepo.findByUserID(idDTO.id);

        if (updateDataDTO.newPassword == null && updateDataDTO.oldPassword == null) {
            user.editPersonalData(updateDataDTO.userName, updateDataDTO.function, updateDataDTO.photo);
        }
        if (updateDataDTO.userName == null && updateDataDTO.function == null) {
            user.updatePassword(updateDataDTO.oldPassword, updateDataDTO.newPassword);
        }
        return userMapper.toDto(user);
    }
}
