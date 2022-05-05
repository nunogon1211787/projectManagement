package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.dto.ResourceDTOReeng;
import switch2021.project.model.Resource.ResourceReeng;

public interface ResourceFactoryInterfaceReeng {

    ResourceReeng createResource(ResourceDTOReeng resourceDTO);

}
