package switch2021.project.controller;

import switch2021.project.model.*;
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
                for (int i = 0; i < statusList.size(); i++) {
                        if (statusList.get(i).isSprintAvailable())
                                usList.add(statusList.get(i).getDescription().getDescriptionF());
                }
                return Collections.unmodifiableList(usList);
        }

        public boolean changeStatusOfUs(String code, int usId, String userST) {
                UserStoryStatus userStoryStatus = this.company.getUserStoryStatusStore().getUserStoryStatusByDescription(userST);
                ProjectStore projectStore = company.getProjectStore();
                Project project = projectStore.getProjectByCode(code);
                Sprint sprint = project.getSprints().getCurrentSprint();
                SprintBacklog sprintBacklog = sprint.getSprintBacklog();
                UserStory userStory = sprintBacklog.getUserStory(usId);
                return userStory.setUserStoryStatusBoolean(userStoryStatus);
        }

}
