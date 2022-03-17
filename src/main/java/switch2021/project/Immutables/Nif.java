package switch2021.project.Immutables;

import lombok.Getter;

@Getter
public class Nif {

    /**
     * Attributes
     */

    private final long nif;
    private final int NIF_LENGTH = 9;


    public Nif(long nif) {

        checkNifRules(nif);

        this.nif = nif;
    }

    private void checkNifRules(long nif) {

        int length = nifSize(nif);

        if(length != NIF_LENGTH){

            throw new IllegalArgumentException("The NIF must have " + NIF_LENGTH + " numbers.");

        } else if (!checkLastDigit(nif)){
            throw new IllegalArgumentException("Digit control are wrong.");
        }

    }

    private int nifSize(long nif) {

        int size = 1;

        while(nif/10 != 0){
            nif /= 10;
            size++;
        }

        return size;

    }

    private boolean checkLastDigit(long nif) {

        int validDigitControl = 0;
        int typedDigitControl = (int) nif%10;
        int produt = 2;

        while(nif != 0){

            nif /= 10;
            validDigitControl += (int) nif%10 * produt;
            produt++;

        }

        validDigitControl %= 11;

        if(validDigitControl == 0 || validDigitControl == 1){
            validDigitControl = 0;
        } else {
            validDigitControl = 11 - validDigitControl;
        }

        return validDigitControl == typedDigitControl;
    }
}
