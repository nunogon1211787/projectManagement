package switch2021.project.factoryInterface;


import switch2021.project.model.valueObject.ProjectID;


public interface IProjectIDFactory {
    ProjectID create(String projectID);
}
