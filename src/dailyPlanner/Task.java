package dailyPlanner;
import dailyPlanner.Validate.Validate;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private String tittle;
    private String description;
    private TypeTask typeTask;
    private LocalDateTime firstDate;
    private boolean archived;
    private static Integer counter = 1;
    private final Integer id;

    public Task(String tittle, String description, TypeTask typeTask, LocalDateTime localDateTime)
            throws WrongInputException {
        this.tittle = Validate.checkString(tittle);
        this.description = Validate.checkString(description);
        this.typeTask = typeTask;
        this.firstDate = localDateTime;
        this.archived = false;
        id = counter++;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public static Integer getCounter() {
        return counter;
    }

    public static void setCounter(Integer counter) {
        Task.counter = counter;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return archived == task.archived && Objects.equals(tittle, task.tittle) && Objects.equals(description, task.description) && typeTask == task.typeTask && Objects.equals(firstDate, task.firstDate) && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tittle, description, typeTask, firstDate, archived, id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "tittle='" + tittle + '\'' +
                ", description='" + description + '\'' +
                ", typeTask=" + typeTask +
                ", firstDate=" + firstDate +
                ", archived=" + archived +
                ", id=" + id +
                '}';
    }
}
