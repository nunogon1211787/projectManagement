package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.UserStoryStatusStore;

import java.util.ArrayList;
import java.util.List;

public class UpdateStatusUsController {

        /**
         * Attributes
         **/

        private final Company company;
        private UserStoryStatusStore usStore = new UserStoryStatusStore();
        private ProjectStore projectStore;
        private Project project;
        private Sprint sprint;
        private SprintBacklog sprintBacklog;
        private UserStory userStory;
        private List <String> usList;

//    /**
//     * Constructor to UI (with SINGLETON)
//     **/

//    public ChangePriorityUSController(){
//        this.company = App.getInstance().getCompany();
//    }

        /**
         * Constructor (without SINGLETON)
         **/

        public UpdateStatusUsController(Company company) {
                this.company = company;
        }

        /**
         * Methods
         **/

        public List<String> getUsAvailableStatusList() {
                List<UserStoryStatus> statusList= this.company.getUserStoryStatusStore().getUserStoryStatusList();
                this.usList = new ArrayList<>();
                for (int i = 0; i < statusList.size(); i++) {
                        if(statusList.get(i).isSprintAvailable() )
                        this.usList.add(statusList.get(i).getDescription());
                }
                return this.usList;
        }

        public boolean changeStatusOfUs(String code, int usId, UserStoryStatus userST) {
                this.projectStore = company.getProjectStore();
                this.project = this.projectStore.getProjectByCode(code);
                this.sprint = this.project.getSprints().getCurrentSprint();
                this.sprintBacklog = this.sprint.getSprintBacklog();
                this.userStory = this.sprintBacklog.getUserStory(usId);
                return this.userStory.setUserStoryStatusBoolean(userST);
        }

}
