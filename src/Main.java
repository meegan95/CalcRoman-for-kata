import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    static char operation;
    static int number1, number2;
    static int result;
    public static void main(String[] args) {

        Scanner expression = new Scanner(System.in);
        System.out.println("Введите выражение [2+2] от 1 до 10 или два римских числа от I до X:[V+V] + Enter ");
        String input = expression.nextLine();
        String[] operands0 = input.split("[+\\-*/]");
        if (operands0.length != 2) {throw new InputMismatchException("Должен быть только 1 знак операции ");}
        System.out.println(calc(input));
    }
    public static String calc(String input) {
        char[] massiv = new char[10];
        for (int i = 0; i < input.length(); i++) {
            massiv[i] = input.charAt(i);
            if (massiv[i] == '+') {
                operation = '+';
            }
            if (massiv[i] == '-') {
                operation = '-';
            }
            if (massiv[i] == '*') {
                operation = '*';
            }
            if (massiv[i] == '/') {
                operation = '/';
            }
        }
        String massivString = String.valueOf(massiv);
        int index1 = massivString.indexOf(',');
        int index2 = massivString.indexOf('.');
        if (index1 > 0 || index2 > 0) {
            throw new InputMismatchException("Калькулятор принимает только целые числа");
        }
        String[] stroka = massivString.split("[+-/*]");
        String part1 = stroka[0];
        String part2 = stroka[1];
        String string01 = part1.trim();
        String string02 = part2.trim();
        int check = 0;
        if ((string01.codePointAt(0) - (string02.codePointAt(0)) > 15) || (string02.codePointAt(0) - (string01.codePointAt(0)) > 15)) {
            throw new InputMismatchException("Ввод римфских и арабских цифр одновременно запрещен");
        }
        try {
            number1 = Integer.parseInt(string01);
            number2 = Integer.parseInt(string02);
            if (number1 > 10 || number2 > 10 || number1 == 0 || number2 == 0) {
                check = 1;
                throw new NumberFormatException("Введите число от 1 до 10");
            }
            result = calc1(number1, number2, operation);
            String result2 = Integer.toString(result);
            return (result2);
        } catch (NumberFormatException e) {
            number1 = romanToNumber(string01);
            number2 = romanToNumber(string02);
            result = calc1(number1, number2, operation);
            if (check == 1) {
                throw new NumberFormatException("Введите число от 1 до 10");
            }
            if (number1 > 10 || number2 > 10 || number1 == 0 || number2 == 0) {
                throw new NumberFormatException("Введите число от I до X");
            }
            String resultRoman = convertNumToRoman(result);
            if (result <= 0) {
                throw new NumberFormatException("В римской системе нет 0 и отрицательных чисел");
            }
            return (resultRoman);
        }
    }
    static String convertNumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }
    static int romanToNumber (String roman) {
        try {
            if (roman.equals("I")) {
                return 1;
            }
            else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }


        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }
    static int calc1 (int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}