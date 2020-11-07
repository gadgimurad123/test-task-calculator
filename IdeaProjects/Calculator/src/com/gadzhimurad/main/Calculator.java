package com.gadzhimurad.main;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        // Читаем строку
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        scanner.close();

        // Делим строку на части
        String[] partsOfInputLine = inputLine.split(" ");

        // Отправляем на дальнейшую проверку и вычисление
        Calculator.checkCorrectInput(partsOfInputLine);
    }

    // Метод для проверки корректности введенной строки
    static void checkCorrectInput (String[] partsOfInputLine) {
        int a = 0;
        int b = 0;
        String sign = partsOfInputLine[1];
        int catchCount = 0; // Счетчик количества не арабских цифр

        try {
            a = Integer.parseInt(partsOfInputLine[0]);
        } catch (NumberFormatException e) {
            catchCount++;
        }

        try {
            b = Integer.parseInt(partsOfInputLine[2]);
        } catch (NumberFormatException e) {
            catchCount++;
        }

        if (catchCount == 1) { // одно не арабское число (Ошибка)
            throw new NumberFormatException();

        } else if (catchCount == 2) { // два не арабских числа (Могут быт)
            final String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            String aStr = partsOfInputLine[0];
            String bStr = partsOfInputLine[2];
            int flagRomNum = 0;

            // Посчитаем сколько цифр относятся к диапазону римских
            for (int i = 0; i < roman.length; i++) {
                if (aStr.equals(roman[i]))
                    flagRomNum++;
                if (bStr.equals(roman[i]))
                    flagRomNum++;
            }

            // Если в выражении 2 римские цифры, то переведем их в арабские (для расчета)
            if (flagRomNum == 2) {
                a = Converter.romanToArab(aStr);
                b = Converter.romanToArab(bStr);
            } else
                System.out.println("Wrong numbers. Try again. ERROR 2");

            if ((a >= 1 && a <= 10) && (b >= 1 && b <= 10))
                calculate(a, b, sign, 2);

        } else { // два арабских числа
            a = Integer.parseInt(partsOfInputLine[0]);
            b = Integer.parseInt(partsOfInputLine[2]);

            if ((a >= 1 && a <= 10) && (b >= 1 && b <= 10))
                calculate(a, b, sign, 1);
            else
                System.out.println("Wrong numbers. Try again. ERROR 1");

        }
    }

    // Выполняем математическую операцию
    static void calculate (int a, int b, String sign, int mode) {
        int result = 0;

        if (sign.equals("+")) {
            result = a + b;
        } else if (sign.equals("-")) {
            result = a - b;
        } else if (sign.equals("*")) {
            result = a * b;
        } else if (sign.equals("/")) {
            result = a / b;
        } else {
            System.out.println("Invalid sign. Try again. ERROR 3");
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        // Если на вход поступали: арабские цифры (mode = 1), иначе римские (mode = 2)
        if (mode == 1) {
            System.out.println(result);
        } else if (mode == 2) {
            System.out.println(Converter.arabToRoman(result));
        }
    }
}
