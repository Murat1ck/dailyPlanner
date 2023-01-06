package dailyPlanner.Validate;

import dailyPlanner.WrongInputException;

public class Validate {
    public static String checkString(String str) throws WrongInputException {
        if (str == null || str.isEmpty() || str.isBlank()) {
            throw new WrongInputException("Некорректный ввод");
        } else {
            return str;
        }
    }
}

