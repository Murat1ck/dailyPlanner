package dailyPlanner;

import dailyPlanner.Validate.Validate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Calendar {
    private static final Map<Integer, Repeatable> actualTasks = new HashMap<>();
    private static final Map<Integer, Repeatable> archivedTasks = new HashMap<>();


    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Введите название задачи: ");
            String tittle = Validate.checkString(scanner.nextLine());
            System.out.println("Введите описание задачи: ");
            String description = Validate.checkString(scanner.nextLine());
            System.out.println("Введите тип задачи: 0 - Рабочая, 1 - Личная");
            TypeTask typeTask = TypeTask.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: 0 - Однократная, 1 - Ежедневна, 2 - Еженедельная, 3 - Ежемесячная, 4 - Ежегодная");
            int occurance = scanner.nextInt();
            System.out.println("Введите дату dd.MM.yyyy HH:mm ");
            scanner.nextLine();
            createEvent(scanner, tittle, description, typeTask, occurance);
            System.out.println("Для выхода нажмите Enter\n");
            scanner.nextLine();


        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createEvent(Scanner scanner, String tittle, String description, TypeTask typeTask, int occurance) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Repeatable task = null;
            try {
                task = createTask(occurance, tittle, description, typeTask, eventDate);
                System.out.println("Создана задача " + task);
            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат dd.MM.yyyy HH:mm и повторите попытку");
            createEvent(scanner, tittle, description, typeTask, occurance);
        }
    }


    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи\n");
        printActualTasks();
        System.out.println("Для удаления введите id задачи\n");
        int id = scanner.nextInt();
        if (actualTasks.containsKey(id)) {
            actualTasks.remove(id);
        }
    }

    public static void getTasksByDay(Scanner scanner) {
        System.out.println("Введите дату dd.MM.yyyy: ");
        try {
            String date = scanner.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateFormatter);
            List<Repeatable> foundEvents = findTasksByDate(requestedDate);
            System.out.println("События на " + requestedDate + ":");
            for (Repeatable task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат даты dd.MM.yyyy и попробуйте ещё раз");
        }
        scanner.nextLine();
        System.out.println("Для выхода нажмите Enter\n");
    }

    public static void getGroupByDate(Scanner scanner) {
        Map<LocalDate, ArrayList<Repeatable>> taskMap = new HashMap<>();
        for (Map.Entry<Integer, Repeatable> entry : actualTasks.entrySet()) {
            Repeatable task = entry.getValue();
            LocalDate localDate = task.getFirstDate().toLocalDate();
            if (taskMap.containsKey(localDate)) {
                ArrayList<Repeatable> tasks = taskMap.get(localDate);
                tasks.add(task);
            } else {
                taskMap.put(localDate, new ArrayList<>(Collections.singletonList(task)));
            }
        }
        for (Map.Entry<LocalDate, ArrayList<Repeatable>> taskEntry : taskMap.entrySet()) {
            System.out.println(taskEntry.getKey() + " : " + taskEntry.getValue());
        }
    }

    private static List<Repeatable> findTasksByDate(LocalDate date) {
        List<Repeatable> tasks = new ArrayList<>();
        for (Repeatable task : actualTasks.values()) {
            if (task.checkOccurance(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }
    public static void editTask(Scanner scanner) {
        try {
            System.out.println("Редактирование задачи: введите значение id");
            printActualTasks();
            int id = scanner.nextInt();
            if (!actualTasks.containsKey(id)) {
                throw new WrongInputException("Задача не найдена");
            }

        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }


    private static Repeatable createTask(int occurance, String title, String description, TypeTask typeTask, LocalDateTime localDateTime)
            throws WrongInputException {
        return switch (occurance) {
            case 0 -> {
                OneTimeTask oneTimeTask = new OneTimeTask(title, description, typeTask, localDateTime);
                actualTasks.put(oneTimeTask.getId(), oneTimeTask);
                yield oneTimeTask;
            }
            case 1 -> {
                DailyTask task = new DailyTask(title, description, typeTask, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(title, description, typeTask, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(title, description, typeTask, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 4 -> {
                YearlyTask task = new YearlyTask(title, description, typeTask, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }

            default -> null;
        };
    }

    private static void printActualTasks() {
        for (Repeatable task : actualTasks.values()) {
            System.out.println(task);
        }
    }
}
