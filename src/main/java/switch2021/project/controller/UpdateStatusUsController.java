package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.util.List;

public class UpdateStatusUsController {

        private final Company company;
        private ProjectStore projectStore;
        private Project project;
        private SprintBacklog sprintBacklog;
        private UserStory userStory;
        List<Project> arrayProject;
        List<UserStory> userStoryList;

//    /**
//     * Constructor to UI (with SINGLETON).
//     */
//    public ChangePriorityUSController(){
//        this.company = App.getInstance().getCompany();
//    }

        /**
         * Constructor (without SINGLETON).
         */
        public UpdateStatusUsController(Company company){
                this.company = company;
        }



        public ProjectStore getProjectStore (){
                this.projectStore = company.getProjectStore();
                return this.projectStore;
        }

        public Project getProjectByCode (String code){
                this.project = company.getProjectStore().getProjectByCode(code);
                return this.project;
        }

        public SprintBacklog getSprintBacklog (){
                this.sprintBacklog = this.project.getCurrentSprint().getSprintBacklog();
                return this.sprintBacklog;
        }

        public UserStory getUserStory(int usId){
                this.userStory = this.sprintBacklog.getUserStory(usId);
                return this.userStory;
        }









}
