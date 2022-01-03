package switch2021.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestTest {

    @Test
    public void createRequest () {

        //input
        Request req = new Request("02-01-2022", "20:30","test@test.pt",7);

        //expected
        CharSequence requestDate = req.getRequestDate();
        CharSequence requesDate = "02-01-2022";

        CharSequence requestTime = req.getRequestTime();
        CharSequence requesTime = "20:30";

        String email = req.getEmail();
        String emailtest = "test@test.pt";

        Integer id = req.getId();
        Integer idtest = 7;

        //result

        assertEquals(requesDate,requestDate);
        assertEquals(requesTime,requestTime);
        assertEquals(email,emailtest);
        assertEquals(id,idtest);
    }

}
