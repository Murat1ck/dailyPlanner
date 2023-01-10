package Calendar;

import java.util.Calendar;
import java.util.Scanner;

import static Calendar.Calendar.*;
import static java.util.Calendar.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.print("Выберите пункт меню: " + " ");
                printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {

                        case 1:
                            addTask(scanner);
                            break;
                        case 2:
                            deleteTask(scanner);
                            break;
                        case 3:
                            getTasksByDay(scanner);
                            break;
                        case 4:
                            getGroupByDate(scanner);
                            break;
                        case 5:
                            editTask(scanner);
                            break;
                        case 0:
                            break label;

                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        // todo
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                        """
        );
    }
}



    