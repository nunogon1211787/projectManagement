package switch2021.project.model;

import java.sql.Timestamp;

public class Sprint {

    /**
     * Atributos da classe Resource
     **/
    private int number;
    private int duration;

    private CharSequence startDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/
    private CharSequence endDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/


    /**
     * Construtor de Sprint
     **/

    public Sprint(int number, int duration, CharSequence startDate, CharSequence endDate){

        this.number = number;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;

    }



}
