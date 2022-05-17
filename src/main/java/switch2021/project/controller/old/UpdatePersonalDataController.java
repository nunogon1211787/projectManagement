//package switch2021.project.controller.old;
//
//import switch2021.project.model.Company;
//import switch2021.project.model.SystemUser.User;
//
//public class UpdatePersonalDataController {
//
//    /**
//     * Attributes
//     **/
//
//    private final Company company;
//    private User user;
//
//    /**
//     * Constructor to test (without SINGLETON)
//     **/
//
//    public UpdatePersonalDataController(Company company) { this.company = company; }
//
//    /**
//     * Methods
//     **/
//
//    public User getUser(String email) {
//        this.user = this.company.getSystemUserStore().findByUserID(email);
//        return this.user; }
//}
