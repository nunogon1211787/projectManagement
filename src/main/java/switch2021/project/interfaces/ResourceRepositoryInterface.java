package switch2021.project.interfaces;

import org.springframework.stereotype.Component;
import switch2021.project.model.Resource.ResourceReeng;


import java.util.List;

public interface ResourceRepositoryInterface/* extends CrudRepository<Object, Long> */{

    /**
     * The repository should be able to find a object using given Identity.
     */
    //Optional<Resource> findByResourceId(ResourceId resId);

    ResourceReeng findById (String resourceId);

    boolean existsById(String resourceId);

    /**
     * Finds all objects from this repository with any parameter.
     */

//    List<ResourceReeng> findByProject(String projectId);

    List<ResourceReeng> findAllByProject(String projectId);

    List<ResourceReeng> findAllByUser(String systemUserId);


    /**
     * Save a object in the list of the repository.
     */
    boolean saveResource(ResourceReeng resource);

}
