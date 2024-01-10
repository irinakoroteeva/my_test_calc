import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String result = Main.calc(s);
        System.out.println(result);

    }
    private static List<String> romanNumerals = List.of("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
            "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV",
            "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII",
            "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII",
            "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII",
            "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII",
            "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C");

    public static String calc(String input) {
        String[] stringArray = input.split(" ");
        checkArithmetic(stringArray);
        String first = stringArray[0];
        String second = stringArray[2];
        boolean isRoman = checkRoman(first, second);
        int firstValue;
        int secondValue;
        if (isRoman) {
            firstValue = romanToInt(first);
            secondValue = romanToInt(second);
        } else {
            checkArab(first);
            checkArab(second);
            firstValue = Integer.parseInt(stringArray[0]);
            secondValue = Integer.parseInt(stringArray[2]);
            checkArab(firstValue, secondValue);
        }

        String mathSymbol = stringArray[1];

        String result = "";

        switch (mathSymbol) {
            case ("+"):
                result = String.valueOf(sum(firstValue, secondValue));
                break;
            case ("-"):
                result = String.valueOf(minus(firstValue, secondValue));
                break;
            case ("*"):
                result = String.valueOf(multiply(firstValue, secondValue));
                break;
            case ("/"):
                result = String.valueOf(division(firstValue, secondValue));
                break;
            default:
                throw new IllegalArgumentException("Не соответствует одной из арифметических операций: +, -, *, /");
        }

        if (isRoman) {
            int rsl = Integer.parseInt(result);
            result = intToRoman(rsl);
        }
        return result;
    }



    private static String intToRoman(int num) {
        if (num < 1) {
            throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
        }

        return romanNumerals.get(num);
    }

    private static int romanToInt(String value) {
        return romanNumerals.indexOf(value);
    }

    private static void checkArab(int firstValue, int secondValue) {
        if (1 > firstValue || firstValue > 10 || 1 > secondValue || secondValue > 10) {
            throw new IllegalArgumentException("Числа должны быть в диапазоне от 1 до 10");
        }

    }
    private static void checkArab(String value) {
        char[] array = value.toCharArray();
        if (!Character.isDigit(array[0])) {
            throw new IllegalArgumentException("Используются одновременно разные системы счисления");
        }
    }

    private static boolean checkRoman(String firstValue, String secondValue) {
        if (!romanNumerals.contains(firstValue) || !romanNumerals.contains(secondValue)) {
            return false;
        }

        int first = romanToInt(firstValue);
        int second = romanToInt(secondValue);

        checkArab(first, second);
        return true;

    }

    private static void checkArithmetic(String[] array) {

        if (array.length < 3 || array[0].trim().isEmpty() || array[1].trim().isEmpty() || array[2].trim().isEmpty()) {
            throw new IllegalArgumentException("Cтрока не является математической операцией");
        }
        if (array.length != 3 || array[0].trim().isEmpty() || array[1].trim().isEmpty() || array[2].trim().isEmpty()) {
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

    }

    private static int sum(int firstValue, int secondValue) {
        return firstValue + secondValue;
    }

    private static int minus(int firstValue, int secondValue) {
        return firstValue - secondValue;
    }

    private static int multiply(int firstValue, int secondValue) {
        return firstValue * secondValue;
    }

    private static int division(int firstValue, int secondValue) {
        return firstValue / secondValue;
    }


}