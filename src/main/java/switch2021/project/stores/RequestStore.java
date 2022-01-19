package switch2021.project.stores;

import switch2021.project.model.Request;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RequestStore {

    /**
     * Atributos da Classe
     **/

    private List<Request> requestProfileList;


    public RequestStore (){
        this.requestProfileList = new ArrayList<>();
    }

    /**
     * Creator Method
     **/
    public Request createProfileRequest (UserProfile Profile, SystemUser user ) {

        return new Request(Profile,user);
    }

    /**
     * ID Generator
     */
    public int idGenerator () {

        int id = 1;
        if (getRequestProfileList().size() !=0){
            id = this.requestProfileList.get(this.requestProfileList.size() -1).getIdRequest() +1;

        }
        return id;
    }

    /**
     * Add Method
     **/

    public boolean addProfileRequest(Request request) {

        if(validateIfProfileRequestedItsNotAlreadyAssigned(request))
        {
            throw new IllegalArgumentException("Requested profile is already assigned to the user.");
        }
        if (validateIfRequestAlreadyExists(request) && (!validateRequestStatus(request))){
            throw new IllegalArgumentException("Request already exists");
        }

        request.setIdRequest(idGenerator());

        this.requestProfileList.add(request);

        return true;
    }

    /**
     * Getter Method
     **/

    public List<Request> getRequestProfileList() {
        return this.requestProfileList;
    }

    /**
     * Remove Method
     */

    public boolean removeProfileRequest (Request request){
        this.requestProfileList.remove(request);

       return true;
    }

    /**
     * Method To Validate If Requested Profile Is Not Already Assigned To The User
     */

    private boolean validateIfProfileRequestedItsNotAlreadyAssigned (Request request) {
        return request.getUser().hasProfile(request.getProfile());
    }

    /**
     * Method to validate if a request already exists
     * */

    private boolean validateIfRequestAlreadyExists(Request request){
        return this.requestProfileList.contains(request);
    }

    /**
     * Method to validate request status
     */

    private boolean validateStatusOfARequest (Request request){
        return request.getRequestStatus();

    }

    /**
     * Validation Method
     */

    private boolean validateRequest(Request newRequest) {

        //Check if request already exist
        for (Request up : requestProfileList) {
            if (up.equals(newRequest)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Save Method
     */

    public boolean saveRequest(Request newRequest) {

        boolean result = false;

        if (validateRequest(newRequest)) {
            addProfileRequest(newRequest);
            result = true;
        }
        return result;
    }


    /**
     * Method to change request status
     * */

    public void changeRequestStatusCheck(Request request){
        request.changeRequestStatus(request);

    }

    private boolean validateRequestStatus(Request request){
        return request.getRequestStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestStore that = (RequestStore) o;
        return Objects.equals(requestProfileList, that.requestProfileList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestProfileList);
    }


}
