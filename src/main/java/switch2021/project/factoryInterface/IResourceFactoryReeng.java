package switch2021.project.factoryInterface;

import switch2021.project.dto.CreateResourceDTO;
import switch2021.project.model.Resource.ResourceReeng;

public interface IResourceFactoryReeng {

    ResourceReeng createResource(CreateResourceDTO resourceDTO);

}
