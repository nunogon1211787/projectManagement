//package switch2021.project.controller.old;
//
//import switch2021.project.model.*;
//import switch2021.project.model.SystemUser.User;
//import switch2021.project.model.UserProfile.UserProfile;
//import switch2021.project.repositories.UserRepository;
//import switch2021.project.repositories.UserProfileRepository;
//
//import java.util.List;
//
//public class UpdateUserProfileController {
//
//    /**
//     * Attributes
//     **/
//    private final UserProfileRepository userProfileStore;
//    private final UserRepository systemUserStore;
//    private User user;
//
//
//    /**
//     * Constructor to test (without SINGLETON)
//     **/
//    public UpdateUserProfileController(Company company) {
//        this.userProfileStore = company.getUserProfileStore();
//        this.systemUserStore = company.getSystemUserStore();
//        this.user = null;
//    }
//
//
//    /**
//     * Methods
//     **/
//    public User getUser(String email) {
//        User user = systemUserStore.findByUserID(email);
//        if (user == null) {
//            throw new IllegalArgumentException("This email doesn't exist.");
//        }
//        return user;
//    }
//
//    public List<UserProfile> getUserProfileList() {
//        return getUserProfileList();
//    }
///*
//    public boolean updateProfile(SystemUser user, UserProfile oldProfile, UserProfile newProfile) {
//        this.user = user;
//        this.user.updateProfile(oldProfile, newProfile);
//        this.systemUserStore.saveSystemUser(user);
//
//        return true;
//    }
//
// */
//}
