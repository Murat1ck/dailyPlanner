package dailyPlanner;

import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatable{
    public WeeklyTask(String tittle, String description, TypeTask typeTask, LocalDateTime date)
            throws WrongInputException {
        super(tittle, description, typeTask, date);
    }

    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfWeek().equals(requestedDate.getDayOfWeek());    }
}
