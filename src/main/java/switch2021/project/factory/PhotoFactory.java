package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IPhotoFactory;
import switch2021.project.model.valueObject.Photo;

@Component
public class PhotoFactory implements IPhotoFactory {
    @Override
    public Photo createPhoto(String photo) {
        return new Photo(photo);
    }
}
