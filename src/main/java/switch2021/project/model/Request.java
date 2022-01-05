package switch2021.project.model;

import java.time.LocalDate;

public class Request {

    /**
     * atributos da classe "Request"
     **/

    private CharSequence requestDate;
    private CharSequence requestTime;
    private String email;
    private Integer id;

    /**
     * construtor da classe "Request"
     **/

    public Request(CharSequence requestDate, CharSequence requestTime, String email, int id) {
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.email = email;
        this.id = id;
    }

    /**
     * getters da classe "Request"
     **/

    public CharSequence getRequestDate() {
        return requestDate;
    }

    public CharSequence getRequestTime() {
        return requestTime;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }





    }





