package switch2021.project.factory;

import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.valueObject.Photo;

@Service
public class PhotoFactory implements IValueObjectsFactory<Photo> {
    @Override
    public Photo create(Object o) {
        return new Photo(o.toString());
    }
}
