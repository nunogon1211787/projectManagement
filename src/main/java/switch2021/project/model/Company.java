package switch2021.project.model;

import lombok.Getter;
import switch2021.project.stores.*;

import java.util.Objects;

@Getter
public class Company {

    /**
     * Company Atributes
     * The company atributes are composed by all the store lists of a xiven project
     **/
    private final SystemUserStore systemUserStore;
    private final ProjectStore projectStore;
    private final UserProfileStore userProfileStore;
    private final ProjectRoleStore projectRoleStore;
    private final TypologyStore typologyStore;
    private final CustomerStore customerStore;
    private final BusinessSectorStore businessSectorStore;
    private final ProjectStatusStore projectStatusStore;
    private final UserStoryStatusStore userStoryStatusStore;
    private final RequestStore requestStore;
    private final TaskStatusStore taskStatusStore;
    private final TaskTypeStore taskTypeStore;


    /**
     * Company Constructor
     * The company constructor initializes and populates all the store lists.
     **/
    public Company() {
        this.systemUserStore = new SystemUserStore();
        this.projectStore = new ProjectStore();
        this.userProfileStore = new UserProfileStore();
        this.projectRoleStore = new ProjectRoleStore();
        this.typologyStore = new TypologyStore();
        this.customerStore = new CustomerStore();
        this.businessSectorStore = new BusinessSectorStore();
        this.projectStatusStore = new ProjectStatusStore();
        this.userStoryStatusStore = new UserStoryStatusStore();
        this.requestStore = new RequestStore();
        this.taskStatusStore = new TaskStatusStore();
        this.taskTypeStore = new TaskTypeStore();

        this.userProfileStore.populateDefault();
        this.projectRoleStore.populateDefault();
        this.typologyStore.populateDefault();
        this.projectStatusStore.populateDefault();
        this.userStoryStatusStore.populateDefault();
        this.taskStatusStore.populateDefault();
        this.taskTypeStore.populateDefault();
    }


    /** Override **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company that = (Company) o;
        return this.systemUserStore.equals(that.systemUserStore) &&
                this.projectStore.equals(that.projectStore) &&
                this.userProfileStore.equals(that.userProfileStore) &&
                this.projectRoleStore.equals(that.projectRoleStore) &&
                this.typologyStore.equals(that.typologyStore) &&
                this.customerStore.equals(that.customerStore) &&
                this.businessSectorStore.equals(that.businessSectorStore) &&
                this.projectStatusStore.equals(that.projectStatusStore) &&
                this.userStoryStatusStore.equals(that.userStoryStatusStore) &&
                this.requestStore.equals(that.requestStore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemUserStore, projectStore, userProfileStore,
                            projectRoleStore, typologyStore, customerStore,
                            businessSectorStore, projectStatusStore,
                            userStoryStatusStore, requestStore);
    }

}
