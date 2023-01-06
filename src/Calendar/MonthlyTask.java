package Calendar;

import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Repeatable{
    public MonthlyTask(String tittle, String description, TypeTask typeTask, LocalDateTime date)
            throws WrongInputException {
        super(tittle, description, typeTask, date);
    }

    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
         return getFirstDate().getMonth().equals(requestedDate.getMonth());   }
}
