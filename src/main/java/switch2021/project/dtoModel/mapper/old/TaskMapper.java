package switch2021.project.dtoModel.mapper.old;

public class TaskMapper {

    /**
     * Method to break a Create Task DTO data
     **/
/*    public Task toModel(CreateTaskDTO dto, Project proj){

        String name = dto.getName();
        String description = dto.getDescription();
        int effortEstimate = dto.getEffortEstimate();
        Resource responsible = proj.getProjectTeam().getResourceByName(dto.getResponsible());
        TaskTypeEnum type = dto.getTypeName();

        if(dto.getPrecedenceList() == null) {
            return new Task(name, description, effortEstimate, type, responsible);
        }
        else {
            List<String> precedenceList = dto.getPrecedenceList();
            return new Task(name, description, effortEstimate, type, responsible, precedenceList);
        }
    }

 */
}
