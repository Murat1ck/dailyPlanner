package dailyPlanner;

public enum TypeTask {
    WORK(0),
    PRIVATE(1);
    public final int value;

    TypeTask( final int value) {
        this.value = value;
    }
}
