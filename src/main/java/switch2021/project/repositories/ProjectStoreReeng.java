package switch2021.project.repositories;

import lombok.Getter;
import org.springframework.stereotype.Component;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.valueObject.ProjectID;

import java.util.ArrayList;
import java.util.List;

@Getter
//@Component
public class ProjectStoreReeng implements ProjectRepositoryInterface {

    /**
     * Class Attributes
     **/
    private final List<ProjectReeng> projectList;


    /**
     * Constructors with data
     **/
    public ProjectStoreReeng() {
        this.projectList = new ArrayList<>();
    }


    public ProjectReeng save(ProjectReeng newProject) {
        if(newProject == null) {
            throw new IllegalArgumentException("Error: Project is null!");
        }

        if (existById(newProject.getProjectCode().getCode())) {
            throw new IllegalArgumentException("Error: Project already exists!");
        }

        projectList.add(newProject);

        return newProject;
    }


    public List<ProjectReeng> findAll() {
        return new ArrayList<>(this.projectList);
    }


    public ProjectReeng findById(String code) {
        ProjectReeng projectReeng = null;
        for (ProjectReeng proj : projectList) {
            if(proj.getProjectCode().getCode().equals(code)){
               projectReeng = proj;
               break;
            }
        }
        return projectReeng;
    }


    public boolean existById(String id) {
        for (ProjectReeng proj : projectList) {
            if (proj.getProjectCode().getCode().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean existByName(String id) {
        for (ProjectReeng proj : projectList) {
            if (proj.getProjectName().getText().equals(id)) {
                return true;
            }
        }
        return false;
    }
}

