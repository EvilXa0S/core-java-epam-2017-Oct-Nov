package com.epam.courses.jf.practice.SigarevVL.first;

import com.epam.courses.jf.practice.common.first.ISolver;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver implements ISolver{

    /**
     * Ввести N строк, найти самую короткую и самую длинную строки.
     * Вывести найденные строки и их длину. Если строк, удовлетворяющих условию,
     * более одной - вывести последнюю из них.
     * <p>
     * N (целое число, 0 < N < 100) - количество доступных для чтения строк
     * N строк
     */

    @Override
    public void task1() {


        Scanner scanner = new Scanner(System.in);

        int stringNumber = Integer.parseInt(scanner.nextLine());

        String[] string = new String[stringNumber];
        string[0] = scanner.nextLine();

        String minString = string[0];
        String maxString = string[0];

        int minLength = minString.length();
        int maxLength = maxString.length();

        for (int i = 1; i < stringNumber; i++) {
            string[i] = scanner.nextLine();

            if (string[i].length() <= minString.length()) {
                minString = string[i];
                minLength = minString.length();
            }

            if (string[i].length() >= maxString.length()) {
                maxString = string[i];
                maxLength = maxString.length();
            }
        }

        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);

        scanner.close();
    }

    /**
     * Ввести N строк. Упорядочить и вывести строки в порядке возрастания значений их длины.
     * В случае, если длины строк совпадают - упорядочить их в лексикографическом порядке.
     */
    @Override
    public void task2() {
        Scanner scanner = new Scanner(System.in);

        int stringNumber = Integer.parseInt(scanner.nextLine());

        String strings[] = new String[stringNumber];

        for (int i = 0; i < stringNumber; i++) {
            strings[i] = scanner.nextLine();
        }

        Arrays.sort(strings, (aStr, bStr) -> {
            if ((aStr.length() - bStr.length()) == 0) {
                return aStr.compareTo(bStr);
            } else {
                return aStr.length() - bStr.length();
            }
        });

        for (String str : strings) {
            System.out.printf("(%d): \"%s\"%n", str.length(), str);
        }
        scanner.close();
    }

    /**
     * Ввести N строк. Вывести те строки, длина которых меньше средней.
     * Под 'средней' подразумевается среднеарифметическая величина длины
     * всех строк, округленная до целого в меньшую сторону.
     */
    @Override
    public void task3() {
        Scanner scanner = new Scanner(System.in);
        int stringNumber = Integer.parseInt(scanner.nextLine());
        String strings[] = new String[stringNumber];
        int averageLength = 0;

        for (int i = 0; i < stringNumber; i++) {
            strings[i] = scanner.nextLine();
            averageLength += strings[i].length();
        }

        averageLength /= stringNumber;
        System.out.printf("AVERAGE (%d)%n", averageLength);

        for (String str : strings) {
            if (str.length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", str.length(), str);
            }
        }

        scanner.close();
    }

    /**
     * Ввести N слов, состоящих из символов английского алфавита. Найти слово, в котором
     * число различных символов минимально. Символы верхнего и нижнего регистра считать
     * различными. Если таких слов несколько, найти первое из них.
     */
    @Override
    public void task4() {
        Scanner scanner = new Scanner(System.in);

        int wordNumber = Integer.parseInt(scanner.nextLine());

        String inputString = scanner.nextLine();
        String inputWords[] = inputString.split(" ");

        StringBuilder result = new StringBuilder();
        Set<Character> hashWord = new HashSet<>();
        int minSize = 0;

        for (int i = 0; i < wordNumber; i++) {

            for (int j = 0; j < inputWords[i].length(); j++) {
                hashWord.add(inputWords[i].charAt(j));
            }

            if (i == 0) {
                minSize = hashWord.size();
                result.replace(0, result.length(), inputWords[i]);
            } else if (minSize > hashWord.size()) {
                minSize = hashWord.size();
                result.replace(0, result.length(), inputWords[i]);
            }

            hashWord.clear();
        }

        System.out.println(result);

        scanner.close();
    }

    /**
     * Ввести N слов. Найти количество слов, содержащих только символы латинского алфавита,
     * а среди них – количество слов с равным числом гласных и согласных букв.
     */
    @Override
    public void task5() {
        Scanner scanner = new Scanner(System.in);

        int wordNumber = Integer.parseInt(scanner.nextLine());
        int vowel = 0;

        String string[] = new String[wordNumber];

        Pattern patternLatinWords = Pattern.compile("[A-z]+");
        Matcher matcherLatinWords = patternLatinWords.matcher(scanner.nextLine());

        Pattern patternVowel = Pattern.compile("[aeiou]");

        int result = 0;
        wordNumber = 0;

        while (matcherLatinWords.find()) {
            string[wordNumber] = matcherLatinWords.group();
            wordNumber++;
        }

        for (int i = 0; i < wordNumber; i++) {

            Matcher matcherVowel = patternVowel.matcher(string[i]);
            while (matcherVowel.find()) {
                vowel++;
            }

            if (vowel == (string[i].length() - vowel)) {
                result++;
            }
            vowel = 0;
        }

        System.out.println(result);

        scanner.close();
    }

    /**
     * Ввести N слов. Найти слово, символы в котором идут в строгом порядке возрастания их кодов.
     * Если таких слов несколько, найти первое из них.
     */
    @Override
    public void task6() {
        Scanner scanner = new Scanner(System.in);

        int wordNumber = Integer.parseInt(scanner.nextLine());
        String word[] = new String[wordNumber];

        boolean findWord = false;

        for (int i = 0; i < wordNumber; i++) {
            word[i] = scanner.next();
            int count = 1;

            while (count < word[i].length()) {

                if ((word[i].charAt(count - 1) >= word[i].charAt(count))) {
                    break;
                }
                count++;

                if (count == word[i].length()) {
                    System.out.println(word[i]);
                    findWord = true;
                    i = wordNumber - 1;
                    break;
                }
            }
        }

        if (!findWord) {
            System.out.println("NOT FOUND");
        }

        scanner.close();
    }

    /**
     * Ввести N слов. Найти слова, состоящие только из различных символов.
     * В случае, если слово встречается более одного раза - вывести его единожды.
     */
    @Override
    public void task7() {
        Scanner scanner = new Scanner(System.in);
        int wordNumber = Integer.parseInt(scanner.nextLine());

        Set<String> set = new LinkedHashSet<>();
        Set<Character> characterSet = new HashSet<>();

        StringBuffer resultString = new StringBuffer();

        for (int i = 0; i < wordNumber; i++) {
            set.add(scanner.next());
        }

        boolean countOfUniqueWords = true;

        for (String string : set) {

            for (int i = 0; i < string.length(); i++) {
                characterSet.add(string.charAt(i));
            }
            if (characterSet.size() == string.length()) {
                resultString.append(string).append(" ");
                countOfUniqueWords = false;
            }
            characterSet.clear();
        }

        if (countOfUniqueWords) {
            System.out.println("NOT FOUND");
        } else {
            System.out.println(resultString.toString().trim());
        }
        scanner.close();
    }

    /**
     * Ввести N слов. Помимо обычных слов, во входной последовательности могут встречаться
     * целые числа. Среди них необходимо найти число-палиндром (одинаково читающееся в обоих
     * направлениях). Если таких чисел больше одного, найти второе из них. Ограничения на
     * размер числа нет. Одна цифра является палиндромом.
     */
    @Override
    public void task8() {
        Scanner scanner = new Scanner(System.in);

        int wordNumber = Integer.parseInt(scanner.nextLine());
        String inputString[] = scanner.nextLine().split(" ");
        BigInteger bigInteger;

        StringBuilder palindrom = new StringBuilder("NOT FOUND");

        for (String str : inputString) {
            try {
                bigInteger = new BigInteger(str);
                String number = bigInteger.toString();
                String reverseNumber = new StringBuilder(number).reverse().toString();

                if (number.equals(reverseNumber)) {
                    palindrom.replace(0, palindrom.length(), number);
                }
            } catch (java.lang.NumberFormatException e) {
                System.err.println(e);
            }
        }

        System.out.println(palindrom);

        scanner.close();
    }

    /**
     * Написать программу, которая выводит числа от 1 до N^2 в виде матрицы NxN
     * слева направо и сверху вниз.
     */
    @Override
    public void task9() {
        Scanner scanner = new Scanner(System.in);

        int matrixDimension = scanner.nextInt();
        int count = 1;

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                if (j == (matrixDimension - 1)) {
                    System.out.print(count++);
                } else {
                    System.out.print(count++ + "\t");
                }
            }
            System.out.println();
        }
        scanner.close();
    }

    /**
     * Написать программу, позволяющую корректно находить корни квадратного уравнения.
     * Параметры уравнения должны задаваться из стандартного входа.
     */
    @Override
    public void task10() {
        Scanner scanner = new Scanner(System.in);

        double a = scanner.nextInt();
        double b = scanner.nextInt();
        double c = scanner.nextInt();

        double D = Math.pow(b, 2) - 4 * a * c;

        if (D < 0) {
            System.out.println("No solution");
        } else if (D == 0) {
            BigDecimal x = new BigDecimal(((-b) / (2 * a)));
            x = x.setScale(2, RoundingMode.HALF_UP);
            System.out.println("One solution: " + x);
        } else {

            BigDecimal x1 = new BigDecimal((-b - Math.sqrt(D)) / (2 * a));
            x1 = x1.setScale(2, RoundingMode.HALF_UP);
            BigDecimal x2 = new BigDecimal((-b + Math.sqrt(D)) / (2 * a));
            x2 = x2.setScale(2, RoundingMode.HALF_UP);
            System.out.println("Two solutions: " + x1 + ", " + x2);
        }
        scanner.close();

    }

    /**
     * Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу.
     * При реализации использовать оператор switch. Осуществить проверку корректности ввода числа.
     */
    @Override
    public void task11() {

        try(Scanner scanner = new Scanner(System.in);) {
            int month = scanner.nextInt();
            switch (month) {
                case 1:
                    System.out.println("January");
                    break;
                case 2:
                    System.out.println("February");
                    break;
                case 3:
                    System.out.println("March");
                    break;
                case 4:
                    System.out.println("April");
                    break;
                case 5:
                    System.out.println("May");
                    break;
                case 6:
                    System.out.println("June");
                    break;
                case 7:
                    System.out.println("July");
                    break;
                case 8:
                    System.out.println("August");
                    break;
                case 9:
                    System.out.println("September");
                    break;
                case 10:
                    System.out.println("October");
                    break;
                case 11:
                    System.out.println("November");
                    break;
                case 12:
                    System.out.println("December");
                    break;
                default:
                    System.out.println("INCORRECT INPUT DATA");
            }

        } catch (InputMismatchException e) {
            System.out.println("INCORRECT INPUT DATA");
        }
    }

    /**
     * Упорядочить строки матрицы размерности N в порядке возрастания
     * значений элементов k-го столбца.
     */
    @Override
    public void task12() {
        Scanner scanner = new Scanner(System.in);
        int columnNumber = scanner.nextInt();
        int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];
        int sortColumn[] = new int[matrixDimension];

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
                if (j == columnNumber) {
                    sortColumn[i] = matrix[i][j];
                }
            }
        }
        Arrays.sort(sortColumn);
        System.out.println(matrixDimension);

        for (int number : sortColumn) {
            for (int i = 0; i < matrix.length; i++) {
                if (number == matrix[i][columnNumber]) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        if (j == (matrix[i].length - 1)) {
                            System.out.print(matrix[i][j]);
                        } else {
                            System.out.print(matrix[i][j] + "\t");
                        }
                    }
                    System.out.println();
                }
            }
        }
        scanner.close();
    }

    /**
     * Выполнить циклический сдвиг матрицы размерности N на k позиций вниз.
     */
    @Override
    public void task13() {
        Scanner scanner = new Scanner(System.in);

        int shiftNumber = scanner.nextInt();
        int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];
        int matrixNew[][] = new int[matrixDimension][matrixDimension];

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        if (Math.abs(shiftNumber) > matrixDimension) {
            shiftNumber %= matrixDimension;
        }

        if (shiftNumber >= 0) {
            for (int i = 0; i < matrix.length; i++) {
                if (i < shiftNumber) {
                    matrixNew[i] = matrix[matrix.length - shiftNumber + i];
                } else {
                    matrixNew[i] = matrix[i - shiftNumber];
                }
            }
        } else {
            shiftNumber = Math.abs(shiftNumber);
            for (int i = 0; i < matrix.length; i++) {
                if ((i + shiftNumber) < matrix.length) {
                    matrixNew[i] = matrix[shiftNumber + i];
                } else {
                    matrixNew[i] = matrix[-matrix.length + i + shiftNumber];
                }
            }
        }

        System.out.println(matrixDimension);

        for (int reformMatrix[] : matrixNew) {
            for (int i = 0; i < reformMatrix.length; i++) {
                if (i == (reformMatrix.length - 1)) {
                    System.out.print(reformMatrix[i]);
                } else {
                    System.out.print(reformMatrix[i] + "\t");
                }
            }
            System.out.println();
        }
        scanner.close();
    }

    /**
     * Найти наибольшее число строго возрастающих элементов последовательности, идущих подряд.
     * Оператор отношения можно определить на множестве, включающем более одного элемента.
     */
    @Override
    public void task14() {
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int array[] = new int[arraySize];
        int count = 0;
        int sequence = 0;

        if (arraySize < 2) {
            System.out.println(0);
        } else {

            for (int i = 0; i < arraySize; i++) {
                array[i] = scanner.nextInt();

                if ((i > 0) && (array[i - 1] < array[i])) {
                    count++;

                    if (count >= sequence) {
                        sequence = count + 1;
                    }

                } else {
                    count = 0;
                }
            }
        }

        System.out.println(sequence);
        scanner.close();
    }


    /**
     * Найти сумму элементов матрицы, расположенных между первым и
     * вторым положительными элементами каждой строки.
     * <p>
     * Если в строке нет двух положительных элементов - сумма от этой строки равной нулю.
     * Сумма между двумя рядом расположенными элементами равна нулю.
     */
    @Override
    public void task15() {
        Scanner scanner = new Scanner(System.in);
        final int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int summa = 0;

        for (int i = 0; i < matrixDimension; i++) {
            boolean flag = false;
            for (int j = 0; j < matrixDimension; j++) {
                if (matrix[i][j] > 0) {
                    if (flag) {
                        break;
                    }
                    flag = true;
                } else {
                    if (flag)
                        summa += matrix[i][j];
                }
            }

        }

        System.out.println(summa);
        scanner.close();
    }

    /**
     * Повернуть матрицу на 90 градусов против часовой стрелки.
     */
    @Override
    public void task16() {
        Scanner scanner = new Scanner(System.in);

        final int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];
        int newMatrix[][] = new int[matrixDimension][matrixDimension];

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        for (int column = 0; column < matrixDimension; column++) {
            for (int line = 0; line < matrixDimension; line++) {
                newMatrix[matrixDimension - 1 - column][line] = matrix[line][column];
            }
        }

        System.out.println(matrixDimension);
        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                System.out.print(newMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        scanner.close();
    }

    @Override
    public void task17() {
        Scanner scanner = new Scanner(System.in);

        final int matrixDimension = scanner.nextInt();
        double matrix[][] = new double[matrixDimension][matrixDimension];
        double zeroCheck = 0;
        double determinant = 1;

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextDouble();
                zeroCheck += matrix[i][0];
            }
        }
        //проверка определителя на равенство 0
        if (zeroCheck != 0) {

            //если нулевой элемент равен 0, нужно прибавить к нулевой
            // строке любую строку с ненулевым начальным элементом
            if (matrix[0][0] == 0) {
                int notZeroFirstElement = 1;

                while (matrix[notZeroFirstElement][0] == 0) {
                    notZeroFirstElement++;
                }
                //прибавление ненулевой строки
                for (int j = 0; j < matrixDimension; j++) {
                    matrix[0][j] += matrix[notZeroFirstElement][j];
                }
            }
            //применение метода Гаусса

            for (int diagonal = 1; diagonal < matrixDimension - 1; diagonal++) {

                for (int i = diagonal; i < matrixDimension; i++) {

                    for (int j = matrixDimension - 1; j >= diagonal - 1; j--) {

                        matrix[i][j] = matrix[i][j] - (matrix[diagonal - 1][j]
                                * (matrix[i][diagonal - 1]
                                / matrix[diagonal - 1][diagonal - 1]));
                    }
                }
                determinant *= matrix[diagonal - 1][diagonal - 1];
            }

            if (matrixDimension == 1) {
                determinant = matrix[0][0];
            } else {
                determinant *= matrix[matrixDimension - 2][matrixDimension - 2]
                        * matrix[matrixDimension - 1][matrixDimension - 1]
                        - matrix[matrixDimension - 2][matrixDimension - 1]
                        * matrix[matrixDimension - 1][matrixDimension - 2];
            }
            BigDecimal result = new BigDecimal(determinant);
            System.out.println(result.setScale(0, RoundingMode.HALF_UP));
        } else {
            System.out.println(0);
        }

        scanner.close();
    }

    /**
     * Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы,
     * его содержащие.
     */
    @Override
    public void task18() {
        Scanner scanner = new Scanner(System.in);
        final int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        HashSet<Integer> lineSet = new HashSet<>();
        HashSet<Integer> columnSet = new HashSet<>();

        for (int i = 0; i < matrixDimension; i++) {
            lineSet.add(i);
            columnSet.add(i);
        }

        int max = matrix[0][0];

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        for (int i = matrixDimension - 1; i >= 0; i--) {
            for (int j = matrixDimension - 1; j >= 0; j--) {
                if (matrix[i][j] == max) {
                    lineSet.remove(i);
                    columnSet.remove(j);
                }
            }
        }

        System.out.println(lineSet.size());        //number of lines
        System.out.println(columnSet.size());      //number of colomns

        for (Integer line : lineSet) {
            for (Integer column : columnSet) {
                System.out.print(matrix[line][column] + "\t");
            }
            System.out.println();
        }
        scanner.close();

    }
    /**
     * Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
     */
    @Override
    public void task19() {
        Scanner scanner = new Scanner(System.in);
        final int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        HashSet<Integer> lineSet = new HashSet<>();
        HashSet<Integer> colomnSet = new HashSet<>();

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                if (matrix[i][j] != 0) {
                    lineSet.add(i);
                    colomnSet.add(j);
                }
            }
        }

        System.out.println(lineSet.size());        //number of lines
        System.out.println(colomnSet.size());      //number of colomns

        for (Integer line : lineSet) {
            for (Integer colomn : colomnSet) {
                System.out.print(matrix[line][colomn] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * В матрице найти минимальный элемент и переместить его на место заданного элемента
     * путем перестановки строк и столбцов. Гарантируется, что минимальный элемент в матрице
     * встречается ровно один раз.
     */
    @Override
    public void task20() {
        Scanner scanner = new Scanner(System.in);
        int lineNumber = scanner.nextInt();
        int columnNumber = scanner.nextInt();
        int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];
        int minElement = 0;
        int minElementPosition[] = new int[2];

        for (int line = 0; line < matrixDimension; line++) {
            for (int column = 0; column < matrixDimension; column++) {
                if (line == 0 && column == 0) {
                    minElement = matrix[0][0] = scanner.nextInt();
                    minElementPosition[0] = line;
                    minElementPosition[1] = column;
                } else {
                    matrix[line][column] = scanner.nextInt();
                    if (minElement > matrix[line][column]) {
                        minElement = matrix[line][column];
                        minElementPosition[0] = line;
                        minElementPosition[1] = column;

                    }
                }
            }
        }

        int emptyMass[] = new int[matrixDimension];

        emptyMass = matrix[minElementPosition[0]];
        matrix[minElementPosition[0]] = matrix[lineNumber];
        matrix[lineNumber] = emptyMass;

        int empty = 0;

        for (int k = 0; k < matrixDimension; k++) {
            empty = matrix[k][minElementPosition[1]];
            matrix[k][minElementPosition[1]] = matrix[k][columnNumber];
            matrix[k][columnNumber] = empty;
        }

        System.out.println(matrixDimension);
        for (int i[] : matrix) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        scanner.close();
    }

    /**
     * Преобразовать строки матрицы таким образом, чтобы элементы, равные нулю,
     * располагались после всех остальных.
     */

    public void task21() {
        Scanner scanner = new Scanner(System.in);
        final int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        for (int line = 0; line < matrixDimension; line++) {
            for (int column = matrixDimension - 1; column >= 0; column--) {
                if (matrix[line][column] == 0) {
                    int k = column;

                    while (k < matrixDimension - 1) {
                        if (matrix[line][k + 1] == 0) {
                            break;
                        } else {
                            matrix[line][k] = matrix[line][k + 1];
                            matrix[line][k + 1] = 0;
                            k++;
                        }
                    }
                }
            }
        }

        System.out.println(matrixDimension);
        for (int i[] : matrix) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        scanner.close();
    }

    /**
     * Округлить все элементы матрицы до целого числа.
     * Использовать округление к ближайшему целому — число округляется до целого с использованием Math.round();
     */

    @Override
    public void task22() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.FRANCE);

        final int matrixDimension = scanner.nextInt();
        long matrix[][] = new long[matrixDimension][matrixDimension];

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = Math.round(scanner.nextDouble());
            }
        }

        System.out.println(matrixDimension);
        for (long i[] : matrix) {
            for (long j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        scanner.close();
    }


    /**
     * Найти количество всех седловых точек матрицы. Матрица А имеет седловую точку (i, j),
     * если А[i, j] является минимальным элементом в i-й строке и максимальным в j-м столбце.
     */
    @Override
    public void task23() {
        Scanner scanner = new Scanner(System.in);
        final int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];
        int minElement;
        int maxElement;

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        boolean flagMin = true;
        int saddle = 0;

        for (int i = 0; i < matrixDimension; i++) {
            minElement = matrix[i][0];
            int minElementColumn = 0;
            for (int colomn = 1; colomn < matrixDimension; colomn++) {
                if (minElement >= matrix[i][colomn]) {
                    if (minElement == matrix[i][colomn]) {
                        flagMin = false;
                    } else {
                        minElement = matrix[i][colomn];
                        minElementColumn = colomn;
                        flagMin = true;
                    }
                }
            }

            if (flagMin) {
                boolean flagMax = true;
                maxElement = matrix[i][minElementColumn];
                for (int line = 0; line < matrixDimension; line++) {
                    if (line != i && maxElement <= matrix[line][minElementColumn]) {

                        flagMax = false;
                        break;
                    }
                }
                if (flagMax) {
                    saddle++;
                }
                flagMin = true;
            }
        }

        System.out.println(saddle);
        scanner.close();
    }



    /**
     * Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках
     * полученной матрицы возрастала.
     */
    @Override
    public void task24() {
        Scanner scanner = new Scanner(System.in);
        final int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension + 1];
        int sum = 0;

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
                sum += matrix[i][j];
            }
            matrix[i][matrixDimension] = sum;
            sum = 0;
        }

        Arrays.sort(matrix, (amass, bmass)
                -> amass[amass.length - 1] - bmass[bmass.length - 1]);

        System.out.println(matrixDimension);
        for (int i[] : matrix) {
            for (int j = 0; j < i.length - 1; j++) {
                System.out.print(i[j] + "\t");
            }
            System.out.println();
        }
        scanner.close();
    }

    /**
     * Найти число локальных минимумов. Соседями элемента матрицы назовем элементы,
     * имеющие с ним общую сторону или угол. Элемент матрицы называется локальным минимумом,
     * если он строго меньше всех своих соседей.
     */
    @Override
    public void task25() {
        Scanner scanner = new Scanner(System.in);
        int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];
        int localMin = 0;

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {

                boolean leftY = (j - 1) >= 0;
                boolean rightY = (j + 1) < matrixDimension;
                boolean toptX = (i - 1) >= 0;
                boolean bottomX = (i + 1) < matrixDimension;

                boolean s1 = true;
                boolean s2 = true;
                boolean s3 = true;
                boolean s4 = true;
                boolean s5 = true;
                boolean s6 = true;
                boolean s7 = true;
                boolean s8 = true;

                if (toptX && leftY) {
                    s1 = matrix[i][j] < matrix[i - 1][j - 1];
                }

                if (toptX) {
                    s2 = matrix[i][j] < matrix[i - 1][j];
                }

                if (toptX && rightY) {
                    s3 = matrix[i][j] < matrix[i - 1][j + 1];
                }

                if (rightY) {
                    s4 = matrix[i][j] < matrix[i][j + 1];
                }

                if (bottomX && rightY) {
                    s5 = matrix[i][j] < matrix[i + 1][j + 1];
                }

                if (bottomX) {
                    s6 = matrix[i][j] < matrix[i + 1][j];
                }

                if (bottomX && leftY) {
                    s7 = matrix[i][j] < matrix[i + 1][j - 1];
                }

                if (leftY) {
                    s8 = matrix[i][j] < matrix[i][j - 1];
                }

                if (s1 && s2 && s3 && s4 && s5 && s6 && s7 && s8) {
                    localMin++;
                }
            }
        }

        System.out.println(localMin);
        scanner.close();
    }

    /**
     * Найти наибольший среди локальных максимумов. Соседями элемента матрицы назовем элементы,
     * имеющие с ним общую сторону или угол. Элемент матрицы называется локальным максимумом,
     * если он строго больше всех своих соседей.
     */
    @Override
    public void task26() {
        Scanner scanner = new Scanner(System.in);
        int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension][matrixDimension];
        ArrayList<Integer> localMax = new ArrayList<>();

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {

                boolean leftY = (j - 1) >= 0;
                boolean rightY = (j + 1) < matrixDimension;
                boolean toptX = (i - 1) >= 0;
                boolean bottomX = (i + 1) < matrixDimension;

                boolean s1 = true;
                boolean s2 = true;
                boolean s3 = true;
                boolean s4 = true;
                boolean s5 = true;
                boolean s6 = true;
                boolean s7 = true;
                boolean s8 = true;

                if (toptX && leftY) {
                    s1 = matrix[i][j] > matrix[i - 1][j - 1];
                }

                if (toptX) {
                    s2 = matrix[i][j] > matrix[i - 1][j];
                }

                if (toptX && rightY) {
                    s3 = matrix[i][j] > matrix[i - 1][j + 1];
                }

                if (rightY) {
                    s4 = matrix[i][j] > matrix[i][j + 1];
                }

                if (bottomX && rightY) {
                    s5 = matrix[i][j] > matrix[i + 1][j + 1];
                }

                if (bottomX) {
                    s6 = matrix[i][j] > matrix[i + 1][j];
                }

                if (bottomX && leftY) {
                    s7 = matrix[i][j] > matrix[i + 1][j - 1];
                }

                if (leftY) {
                    s8 = matrix[i][j] > matrix[i][j - 1];
                }

                if (s1 && s2 && s3 && s4 && s5 && s6 && s7 && s8) {
                    localMax.add(matrix[i][j]);
                }
            }
        }

        Collections.sort(localMax);

        if (localMax.size() > 0) {
            System.out.println(localMax.get(localMax.size() - 1));
        } else {
            System.out.println("NOT FOUND");
        }
        scanner.close();
    }

    /**
     * Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы значения
     * их характеристик убывали.
     * Характеристикой столбца прямоугольной матрицы называется сумма модулей его элементов.
     * Если значения характеристики совпадают - столбцы должны следовать в том же порядке,
     * что и в исходной матрице.
     */
    @Override
    public void task27() {
        Scanner scanner = new Scanner(System.in);

        final int matrixDimension = scanner.nextInt();
        int matrix[][] = new int[matrixDimension + 1][matrixDimension];
        int characteristicsValue[] = new int[matrixDimension];
        int newMatrix[][] = new int[matrixDimension + 1][matrixDimension];

        for (int i = 0; i < matrixDimension; i++){
            for (int j = 0; j < matrixDimension; j++){
                matrix[i][j] = scanner.nextInt();
                characteristicsValue[j] += Math.abs(matrix[i][j]);
            }

        }

        System.arraycopy(characteristicsValue, 0,
                matrix[matrixDimension], 0, characteristicsValue.length);

        for (int i = 1; i < matrixDimension; i ++){
            int k = 0;
            for (int j = i; j >= 1; j -= 1){
                if (characteristicsValue[j-1] < characteristicsValue[j]){
                    k = characteristicsValue[j];
                    characteristicsValue[j] = characteristicsValue[j - 1];
                    characteristicsValue[j - 1] = k;
                }
                else {
                    break;
                }
            }
        }

        int colomnNumber = 0;
        for(int position = 0; position < matrixDimension; position++) {

            if (position > 0 && characteristicsValue[position - 1]
                    == characteristicsValue[position]) {
                colomnNumber++;
            } else {
                colomnNumber = 0;
            }

            for(int i = colomnNumber; i < matrixDimension; i++) {

                if (characteristicsValue[position] == matrix[matrixDimension][i]) {
                    for (int j = 0; j < matrixDimension; j++) {
                        newMatrix[j][position] = matrix[j][i];
                    }
                    colomnNumber = i;
                    break;
                }
            }
        }

        System.out.println(matrixDimension);
        for (int i = 0; i < matrixDimension; i++) {
            for (int j : newMatrix[i]){
                System.out.print(j + "\t");
            }
            System.out.println();
        }

        scanner.close();
    }


}

