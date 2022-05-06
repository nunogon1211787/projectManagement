package switch2021.project.factory;

import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IPhotoFactory;
import switch2021.project.model.valueObject.Photo;

@Service
public class PhotoFactory implements IPhotoFactory {
    @Override
    public Photo createPhoto(String photo) {
        return new Photo(photo);
    }
}