import java.util.Objects;
import java.util.Scanner;

public class Main {
    static boolean isNumberRoman = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String conversion = sc.nextLine().replaceAll(" ","").toUpperCase();

        System.out.println(calc(conversion));
    }

    //Принимает строку с арифметическим выражением между двумя числами и возвращает строку с результатом их выполнения
    public static String calc(String input) {
        // разделение и помещение слагаемых в массив
        String[] tokens= input.split("\\W"); //буквы цифры и _

        // проверка на заданные условия работы с калькулятором
        if (tokens.length < 2 || input.length() < 3) {
            throw new ArrayIndexOutOfBoundsException("Строка не является математической операцией");
        } else if (tokens.length > 2) {
            throw new ArrayIndexOutOfBoundsException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        // извлечение оператора
        String[] operators = input.split("\\w"); //не буквы не цифры и не _
        String operator = operators[operators.length - 1];

        // приведение строки в int
        int firstNumber = getNumber(tokens[0]);
        boolean isFirstNumberRoman = isNumberRoman; // запоминаем СС для 1 числа
        int secondNumber = getNumber(tokens[1]);

        if (firstNumber > 10 || secondNumber > 10) {
            throw new RuntimeException("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
        }
        if (!isFirstNumberRoman && !isNumberRoman) {
            return Integer.toString(calculated(firstNumber, secondNumber, operator));
        }
        if (isFirstNumberRoman && isNumberRoman) {
            return convertNumToRoman(calculated(firstNumber, secondNumber, operator));
        } else throw new RuntimeException("Используются одновременно разные системы счисления");
    }

    // Метод калькуляции выражения
    public static int calculated(int num1, int num2, String operator) {
        if(Objects.equals(operator, "/") && num2==0)
        {
            throw new ArithmeticException("Деление на 0!");
        }

        int result;
        result = switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException("Неверный знак операции");
        };

        return result;
    }

    // метод преобразования числа в строке в int
    private static int getNumber(String number) {
        try {
            switch (number) {
                case "I" -> {
                    isNumberRoman = true;
                    return 1;
                }
                case "II" -> {
                    isNumberRoman = true;
                    return 2;
                }
                case "III" -> {
                    isNumberRoman = true;
                    return 3;
                }
                case "IV" -> {
                    isNumberRoman = true;
                    return 4;
                }
                case "V" -> {
                    isNumberRoman = true;
                    return 5;
                }
                case "VI" -> {
                    isNumberRoman = true;
                    return 6;
                }
                case "VII" -> {
                    isNumberRoman = true;
                    return 7;
                }
                case "VIII" -> {
                    isNumberRoman = true;
                    return 8;
                }
                case "IX" -> {
                    isNumberRoman = true;
                    return 9;
                }
                case "X" -> {
                    isNumberRoman = true;
                    return 10;
                }
                default -> {
                    isNumberRoman = false;
                    return Integer.parseInt(number);
                }
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неверный формат данных");
        }
    }

    // Метод преобразования арабских чисел в римские
    private static String convertNumToRoman(int numArabian) {
        if(numArabian < 1) {
            throw new RuntimeException("В римской системе счисления нет чисел меньше I (1)");
        }

        if(numArabian > 100) {
            throw new RuntimeException("По условию задачи Римское число не может быть больше С (100)");
        }

        String[] roman = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII",
                "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX",
                "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        return roman[numArabian];
    }
}