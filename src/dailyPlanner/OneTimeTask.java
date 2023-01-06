package dailyPlanner;

import java.time.LocalDateTime;

public class OneTimeTask extends Task implements Repeatable {
    public OneTimeTask(String tittle, String description, TypeTask typeTask, LocalDateTime date)
            throws WrongInputException {
        super(tittle, description, typeTask, date);
    }

    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().toLocalDate().equals(requestedDate.toLocalDate());
    }
}
