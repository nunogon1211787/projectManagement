package switch2021.project.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Sprint {

    /**
     * Atributos da classe Sprint
     **/
    private int number;
    private int duration;
    private Project proj;

    private LocalDate startDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/
    private LocalDate endDate; /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/


    /**
     * Construtor de Sprint
     **/

    public Sprint(Project x,int number, int duration, LocalDate startDate, LocalDate endDate){

        this.proj = x;
        this.number = number;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;

    }



}
