package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Project.Sprint;
import switch2021.project.model.Project.SprintBacklog;
import switch2021.project.model.Project.UserStory;
import switch2021.project.model.UserStoryStatus.UserStoryStatus;
import switch2021.project.stores.ProjectStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpdateStatusUsController {

        /**
         * Attributes
         **/

        private final Company company;

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
                List<UserStoryStatus> statusList = this.company.getUserStoryStatusStore().getUserStoryStatusList();
                List<String> usList = new ArrayList<>();
                for (UserStoryStatus userStoryStatus : statusList) {
                        if (userStoryStatus.isSprintAvailable())
                                usList.add(userStoryStatus.getDescription().getText());
                }
                return Collections.unmodifiableList(usList);
        }

        public boolean changeStatusOfUs(String code, int usId, String userST) {
                UserStoryStatus userStoryStatus = this.company.getUserStoryStatusStore().getUserStoryStatusByDescription(userST);
                ProjectStore projectStore = company.getProjectStore();
                Project project = projectStore.getProjectByCode(code);
                Sprint sprint = project.getSprintList().getCurrentSprint();
                SprintBacklog sprintBacklog = sprint.getSprintBacklog();
                UserStory userStory = sprintBacklog.getUserStory(usId);
                return userStory.setUserStoryStatusBoolean(userStoryStatus);
        }

}
