package switch2021.project.interfaces;

import org.springframework.stereotype.Component;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.Resource.old.Resource;

import java.util.List;

@Component
public interface ResourceRepositoryInterface/* extends CrudRepository<Object, Long> */{

    /**
     * The repository should be able to find a object using given Identity.
     */
    //Optional<Resource> findByResourceId(ResourceId resId);

    /**
     * Finds all objects from this repository with any parameter.
     */

    List<ResourceReeng> findByProject(String projectId);

    //List<Resource> findAllBySystemUserId(SystemUserId userId);


    ResourceReeng findById (String resourceId);

    /**
     * Save a object in the list of the repository.
     */
    boolean saveResource(Resource resource);
}
