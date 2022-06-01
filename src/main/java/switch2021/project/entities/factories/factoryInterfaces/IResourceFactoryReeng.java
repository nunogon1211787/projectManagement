package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.entities.aggregates.Resource.Resource;

public interface IResourceFactoryReeng {

    Resource createResource(CreateResourceDTO resourceDTO);

}
