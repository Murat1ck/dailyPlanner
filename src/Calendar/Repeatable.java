package Calendar;

import java.time.LocalDateTime;

public interface Repeatable {
    boolean checkOccurance(LocalDateTime localDateTime);



    LocalDateTime getFirstDate();


}
