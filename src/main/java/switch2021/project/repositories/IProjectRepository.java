package switch2021.project.repositories;

import lombok.Getter;
import org.springframework.stereotype.Component;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.model.Project.ProjectReeng;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class IProjectRepository implements IProjectRepo {

    /**
     * Class Attributes
     **/
    private final List<ProjectReeng> projectList;


    /**
     * Constructors with data
     **/
    public IProjectRepository() {
        this.projectList = new ArrayList<>();
    }


    public ProjectReeng saveProject(ProjectReeng newProject) {
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

