package Calendar;

import java.time.LocalDateTime;

public class YearlyTask extends Task implements Repeatable {
    public YearlyTask(String tittle, String description, TypeTask typeTask, LocalDateTime date)
            throws WrongInputException {
        super(tittle, description, typeTask, date);
    }

    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().getYear() == requestedDate.getYear();    }
}
