package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.valueObject.ProjectID;

@Component
public class ProjectIDFactory implements IValueObjectsFactory<ProjectID> {

    @Override
    public ProjectID create(Object o) {
        return new ProjectID(o.toString());
    }

}
<<<<<<< HEAD

=======
>>>>>>> origin/master
