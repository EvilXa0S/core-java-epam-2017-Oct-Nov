package com.epam.courses.jf.practice.vplaksin.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.Set;

import static java.lang.Math.min;
import static java.lang.Math.max;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.Math.abs;

/**
 * First part of tasks
 */
public class Solver implements ISolver {

    private static final String NOT_FOUND = "NOT FOUND";

    // Reads array of strings from scanner
    private static String[] readStrings(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());

        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = scanner.nextLine();
        }

        return result;
    }

    /**
     * Описание:
     * Ввести N строк (каждая с новой строки). Найти среди них самую короткую и самую длинную. Вывести найденные строки
     * и их длину. Если строк, удовлетворяющих условию, более одной - вывести последнюю из них.
     * <p>
     * Формат входных данных:
     * N (целое число, 0 < N < 100) - количество доступных для чтения строк
     * Строка_1
     * Строка_2
     * ...
     * Строка_N
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task1 в выходной поток должны быть выведены две строки следующего вида:
     * System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
     * System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
     */
    @Override
    public void task1() {
        String[] strings = readStrings(new Scanner(System.in));

        String minString;
        String maxString;
        if (strings.length == 0) {
            return;
        } else {
            minString = strings[0];
            maxString = strings[0];
        }

        int minLength = minString.length();
        int maxLength = maxString.length();

        for (String s : strings) {
            if (s.length() <= minLength) {
                minString = s;
                minLength = s.length();
            }

            if (s.length() >= maxLength) {
                maxString = s;
                maxLength = s.length();
            }
        }

        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }

    /**
     * Описание:
     * Ввести N строк (каждая с новой строки). Упорядочить и вывести строки в порядке возрастания значений их длины. В
     * случае, если длины строк совпадают - упорядочить их в лексикографическом порядке.
     * <p>
     * Формат входных данных:
     * N (целое число, 0 < N < 100) - количество доступных для чтения строк
     * Строка_1
     * Строка_2
     * ...
     * Строка_N
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task2 в выходной поток должны быть выведены N строк следующего вида:
     * (длина строки): "строка наименьшей длины"
     * (длина строки): "строка большей длины"
     * (длина строки): "строка большей длины"
     * ...
     * (длина строки): "строка наибольшей длины"
     */
    @Override
    public void task2() {
        String[] strings = readStrings(new Scanner(System.in));

        Arrays.sort(strings, Comparator.comparingInt(String::length).thenComparing(String::compareTo));

        for (String s : strings) {
            System.out.printf("(%d): \"%s\"%n", s.length(), s);
        }
    }

    /**
     * Описание:
     * Ввести N строк. Вывести те строки, длина которых меньше средней. Под 'средней' подразумевается
     * среднеарифметическая величина длины всех строк, округленная до целого в меньшую сторону.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество доступных для чтения строк
     * N строк
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task3 в выходной поток должна быть выведена средняя длина (округленная до целого в
     * меньшую сторону) и строки, удовлетворяющие условию. Порядок появления строк в выходном потоке должен совпадать с
     * порядком их появления во входном.
     * AVERAGE (средняя длина)
     * (длина строки): "строка с длиной меньше средней"
     * ...
     * (длина строки): "строка с длиной меньше средней"
     */
    @Override
    public void task3() {
        String[] strings = readStrings(new Scanner(System.in));

        long averageLength = 0L;
        for (String s : strings) {
            averageLength += s.length();
        }
        averageLength /= strings.length;
        System.out.printf("AVERAGE (%d)%n", averageLength);

        for (String s : strings) {
            if (s.length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", s.length(), s);
            }
        }
    }

    //Read array of words from scanner
    private static String[] readWords(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        return scanner.nextLine().split("\\s+", n);
    }

    /**
     * Описание:
     * Ввести N слов, состоящих из символов английского алфавита. Найти слово, в котором число различных символов
     * минимально. Символы верхнего и нижнего регистра считать различными. Если таких слов несколько, найти первое из
     * них.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task4 в выходной поток должно быть выведено слово, содержащее наименьшее число
     * различных символов.
     */
    @Override
    public void task4() {
        String[] words = readWords(new Scanner(System.in));
        int minDifference = 2 * 26 + 1; //Number of different symbols in english alphabet + 1
        String result = "";

        for (String word : words) {
            char[] chars = word.toCharArray();
            Set<Character> set = new HashSet<>();

            for (Character c : chars) {
                set.add(c);
            }

            if (set.size() < minDifference) {
                result = word;
                minDifference = set.size();
            }
        }

        System.out.println(result);
    }


    /**
     * Описание:
     * Ввести N слов. Найти количество слов, содержащих только символы латинского алфавита, а среди них – количество
     * слов с равным числом гласных и согласных букв.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task5 в выходной поток должно быть выведено количество слов, состоящих только из
     * символов латинского алфавита и содержащих равное количество гласных и согласных букв (количество различных
     * вхождений одной буквы в слове учитывается).
     */
    @Override
    public void task5() {
        String[] words = readWords(new Scanner(System.in));

        final List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'y');
        int result = 0;

        for (String word : words) {
            if (!Pattern.matches("[a-zA-Z]+", word)) {
                continue;
            }

            char[] chars = word.toLowerCase().toCharArray();
            int vowelCount = 0;
            int consonantCount = 0;

            for (Character c : chars) {
                if (vowels.contains(c)) {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }

            if (consonantCount == vowelCount) {
                result++;
            }
        }

        System.out.println(result);
    }

    /**
     * Описание:
     * Ввести N слов. Найти слово, символы в котором идут в строгом порядке возрастания их кодов. Если таких слов
     * несколько, найти первое из них.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task6 в выходной поток должно быть выведено слово, коды символов которого следуют
     * в порядке возрастания (каждый следующий строго больше предыдущего). В случае, если такое слово не найдено, в
     * поток должно быть выведено "NOT FOUND". Слова состоящие из одного символа не учитывать.
     */
    @Override
    public void task6() {
        String[] words = readWords(new Scanner(System.in));

        loop:
        for (String word : words) {
            char[] chars = word.toCharArray();

            if (chars.length == 1) {
                continue;
            }

            for (int j = 0; j < chars.length - 1; j++) {
                if (chars[j] >= chars[j + 1]) {
                    continue loop;
                }
            }

            System.out.println(word);
            return;
        }

        System.out.println(NOT_FOUND);
    }

    /**
     * Описание:
     * Ввести N слов. Найти слова, состоящие только из различных символов. В случае, если слово встречается более одного
     * раза - вывести его единожды.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task7 в выходной поток должна быть выведена строка, содержащая найденные слова,
     * разделенные пробелами. В случае, если не найдено ни одного слова, удовлетворяющего условию - в поток должно быть
     * выведено "NOT FOUND".
     */
    @Override
    public void task7() {
        String[] words = readWords(new Scanner(System.in));
        Set<String> wordSet = new HashSet<>();
        StringBuilder builder = new StringBuilder();

        loop:
        for (String word : words) {
            char[] chars = word.toCharArray();
            Set<Character> charSet = new HashSet<>();

            for (char c : chars) {
                if (!charSet.add(c)) {
                    continue loop;
                }
            }

            if (wordSet.add(word)) {
                if (builder.length() == 0) {
                    builder.append(word);
                } else {
                    builder.append(" ");
                    builder.append(word);
                }
            }

        }

        if (builder.length() != 0) {
            System.out.println(builder.toString());
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    /**
     * Описание:
     * Ввести N слов. Помимо обычных слов, во входной последовательности могут встречаться целые числа. Среди них
     * необходимо найти число-палиндром (одинаково читающееся в обоих направлениях). Если таких чисел больше одного,
     * найти второе из них. Ограничения на размер числа нет. Одна цифра является палиндромом. Числа могут быть
     * большими.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task8 в выходной поток должны быть выведено найденное число-палиндром. В случае,
     * если не найдено ни одного такого числа - в поток должно быть выведено "NOT FOUND".
     */
    @Override
    public void task8() {
        String[] words = readWords(new Scanner(System.in));
        List<BigInteger> polyndroms = new ArrayList<>();

        for (String word : words) {
            if (!Pattern.matches("\\d+", word)) {
                continue;
            }

            BigInteger number = new BigInteger(word);

            if (word.equals(new StringBuilder(word).reverse().toString())) {
                polyndroms.add(number);
            }
        }

        if (polyndroms.size() == 1) {
            System.out.println(polyndroms.get(0));
        } else if (polyndroms.size() > 1) {
            System.out.println(polyndroms.get(1));
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    //Reads square matrix of integers from scanner
    private static int[][] readMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        int[][] matrix = new int[DIMENSION][DIMENSION];

        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }

        return matrix;
    }

    //Prints matrix of integers
    private static void printMatrixWithoutSize(int[][] input) {
        for (int[] row : input) {
            for (int i = 0; i < row.length; i++) {
                if (i != row.length - 1) {
                    System.out.print(row[i] + "\t");
                } else {
                    System.out.print(row[i] + "\n");
                }
            }
        }
    }

    //Prints square matrix of integers with its size
    private static void printMatrix(int[][] input) {
        System.out.println(input.length);
        printMatrixWithoutSize(input);
    }

    /**
     * Описание:
     * Написать программу, которая выводит числа от 1 до N^2 в виде матрицы NxN слева направо и сверху вниз.
     * <p>
     * Формат входных данных:
     * N (целое число) - размерность матрицы
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task9 в выходной поток должны быть выведены N строк, соответствующие строкам
     * матрицы. Внутри строки элементы матрицы разделяются знаком табуляции ('\t').
     */
    @Override
    public void task9() {
        Scanner scanner = new Scanner(System.in);
        final int n = Integer.parseInt(scanner.nextLine());

        int[][] result = new int[n][n];
        int k = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = k;
                k++;
            }
        }

        printMatrixWithoutSize(result);
    }

    /**
     * Описание:
     * Написать программу, позволяющую корректно находить корни квадратного уравнения. Параметры уравнения должны
     * задаваться из стандартного входа.
     * <p>
     * Формат входных данных:
     * Во входном потоке последовательно расположены три целочисленных коэффициента A, B и C, задающие уравнение в общем
     * виде.
     * <p>
     * Формат выходных данных:
     * В результате решения уравнения может быть получено три варианта ответа:
     * No solution
     * One solution: корень уравнения
     * Two solutions: первый корень уравнения, второй корень уравнения
     * В результате выполнения метода task10, в выходной поток посылается строка, соответствующая найденному решению.
     * Значения корней округляются до 2 знаков после запятой. Использовать округление BigDecimal.HALF_UP. В качестве
     * десятичного разделителя используется точка..
     */
    @Override
    public void task10() {
        Scanner scanner = new Scanner(System.in);
        final int A = scanner.nextInt();
        final int B = scanner.nextInt();
        final int C = scanner.nextInt();

        if (A == 0) {
            if (B == 0) {
                if (C == 0) {
                    System.out.println("No solution");
                } else {
                    System.out.println("Infinite count of solutions");
                }
            } else {
                BigDecimal result = new BigDecimal((double) -C / B);
                System.out.println("One solution: " + result.setScale(2, RoundingMode.HALF_UP));
            }
            return;
        }

        final double D = B * B - 4 * A * C;
        if (D < 0) {
            System.out.println("No solution");
        } else if (D == 0) {
            BigDecimal result = new BigDecimal((double) -B / (2 * A));
            System.out.println("One solution: " + result.setScale(2, RoundingMode.HALF_UP));
        } else {
            BigDecimal result1 = new BigDecimal((-B - sqrt(D)) / (2 * A));
            BigDecimal result2 = new BigDecimal((-B + sqrt(D)) / (2 * A));
            if (result1.compareTo(result2) > 0) {
                BigDecimal swap = result1;
                result1 = result2;
                result2 = swap;
            }
            System.out.println("Two solutions: " + result1.setScale(2, RoundingMode.HALF_UP)
                    + ", " + result2.setScale(2, RoundingMode.HALF_UP));
        }
    }

    /**
     * Описание:
     * Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу. При реализации
     * использовать оператор switch. Осуществить проверку корректности ввода числа.
     * <p>
     * Формат входных данных:
     * Во входном потоке расположена строка. Необходимо проверить, что она является целым числом в диапазоне 1-12.
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task11, в выходной поток посылается строка, соответствующая названию месяца (на
     * английском языке) с указанным порядковым номером (регистр букв не важен). В случае, если введенная строка не
     * удовлетворяет заданным условиям - в выходной поток посылается сообщение "INCORRECT INPUT DATA".
     */
    @Override
    public void task11() {
        final String INCORRECT_INPUT_DATA = "INCORRECT INPUT DATA";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (!Pattern.matches("\\d{1,2}", input)) {
            System.out.println(INCORRECT_INPUT_DATA);
            return;
        }

        int monthNumber = Integer.parseInt(input);

        switch (monthNumber) {
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
                System.out.println(INCORRECT_INPUT_DATA);
                break;
        }
    }

    /**
     * Описание:
     * Упорядочить строки матрицы размерности N в порядке возрастания значений элементов k-го столбца.
     * <p>
     * Формат входных данных:
     * k (целое число, 0 <= k < N) - номер столбца
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task12 в выходной поток должна быть выведена преобразованная матрица
     */
    @Override
    public void task12() {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        int[][] matrix = readMatrix(scanner);

        Arrays.sort(matrix, Comparator.comparingInt(row -> row[k]));

        printMatrix(matrix);
    }

    /**
     * Описание:
     * Выполнить циклический сдвиг матрицы размерности N на k позиций вниз.
     * <p>
     * Формат входных данных:
     * k (целое число) - количество сдвигов
     * Если k > 0 - производится циклический сдвиг матрицы вниз
     * Если k < 0 - производится циклический сдвиг матрицы вверх
     * Если k = 0 - матрица остается без изменений
     * Матрица (описание представления матриц)
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task13 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task13() {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        int[][] matrix = readMatrix(scanner);
        int[][] result = new int[matrix.length][];


        for (int i = 0; i < result.length; i++) {
            int positionInMatrix = (i - k) % result.length;
            positionInMatrix = positionInMatrix < 0 ? positionInMatrix + result.length : positionInMatrix;
            result[i] = matrix[positionInMatrix].clone();
        }

        printMatrix(result);
    }

    /**
     * Описание:
     * Найти наибольшее число строго возрастающих элементов последовательности, идущих подряд. Оператор отношения можно
     * определить на множестве, включающем более одного элемента.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество элементов последовательности
     * N целых чисел - элементы последовательности
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task14 в выходной поток должно быть выведено число, отображающее количество
     * элементов на самом большом возрастающем участке последовательности.
     */
    @Override
    public void task14() {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int maxRow = 1;
        int tempRow = 1;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[i - 1]) {
                tempRow++;
            } else {
                maxRow = max(maxRow, tempRow);
                tempRow = 1;
            }
        }
        maxRow = max(maxRow, tempRow);

        if (maxRow == 1) {
            System.out.println(0);
        } else {
            System.out.println(maxRow);
        }
    }

    /**
     * Описание:
     * Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task15 в выходной поток должна быть выведена указанная сумма. В случае, если в
     * строке нет двух положительных элементов - сумма от это строки полагается равной нулю. Сумма между двумя рядом
     * расположенными элементами также равна нулю
     */
    @Override
    public void task15() {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);

        int result = 0;
        for (int[] row : matrix) {
            int sumBetweenPositives = 0;
            boolean wasPositive = false;

            for (int element : row) {
                if (!wasPositive) {
                    if (element > 0) {
                        wasPositive = true;
                    }
                } else {
                    if (element > 0) {
                        result += sumBetweenPositives;
                        break;
                    } else {
                        sumBetweenPositives += element;
                    }
                }
            }
        }

        System.out.println(result);
    }

    /**
     * Описание:
     * Повернуть матрицу на 90 градусов против часовой стрелки.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task16 в выходной поток должна быть выведена преобразованная матриц
     */
    @Override
    public void task16() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        int[][] result = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = matrix[j][matrix.length - 1 - i];
            }
        }

        printMatrix(result);
    }

    /**
     * Описание:
     * Вычислить определитель матрицы.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task17 в выходной поток должен быть выведен определитель матрицы, представленный
     * целым числом.
     */
    @Override
    public void task17() {
        int[][] temp = readMatrix(new Scanner(System.in));
        double[][] matrix = new double[temp.length][temp.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
        double result = 1.0;

        for (int k = 0; k < matrix.length; k++) {
            if (matrix[k][k] == 0) {
                boolean isMatrixDegenerate = true;
                for (int i = k; i < matrix.length; i++) {
                    if (matrix[i][k] != 0) {
                        isMatrixDegenerate = false;
                        double[] swap = matrix[i];
                        matrix[i] = matrix[k];
                        matrix[k] = swap;
                        result *= -1;
                        break;
                    }
                }
                if (isMatrixDegenerate) {
                    System.out.println(0);
                    return;
                }
            }

            for (int i = k + 1; i < matrix.length; i++) {
                double coefficient = matrix[i][k] / matrix[k][k];
                for (int j = k; j < matrix[0].length; j++) {
                    matrix[i][j] -= coefficient * matrix[k][j];
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            result *= matrix[i][i];
        }

        System.out.println(Math.round(result));
    }

    //Prints nonsquare matrix with its size
    private void printNonSquareMatrix(int[][] input) {
        System.out.println(input.length);
        System.out.println(input[0].length);

        printMatrixWithoutSize(input);
    }

    /**
     * Описание:
     * Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task18 в выходной поток должна быть выведена преобразованная матрица. Так как
     * матрица в результате преобразования может перестать быть квадратной - несколько изменяется формат её
     * представления при выводе в выходной поток:
     * N (целое число) - количество строк
     * M (целое число) - количество столбцов
     * N * M целых чисел, являющихся элементами матрицы
     */
    @Override
    public void task18() {
        int[][] matrix = readMatrix(new Scanner(System.in));

        int maxElement = matrix[0][0];
        for (int[] row : matrix) {
            for (int element : row) {
                maxElement = max(maxElement, element);
            }
        }

        Set<Integer> rowsToDelete = new HashSet<>();
        Set<Integer> columnsToDelete = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == maxElement) {
                    rowsToDelete.add(i);
                    columnsToDelete.add(j);
                }
            }
        }

        int[][] result = new int[matrix.length - rowsToDelete.size()][matrix.length - columnsToDelete.size()];
        int rowNumber = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (rowsToDelete.contains(i)) {
                continue;
            }
            int columnNumber = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (columnsToDelete.contains(j)) {
                    continue;
                }
                result[rowNumber][columnNumber] = matrix[i][j];
                columnNumber++;
            }
            rowNumber++;
        }

        printNonSquareMatrix(result);
    }

    /**
     * Описание:
     * Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task19 в выходной поток должна быть выведена преобразованная матрица. Так как
     * матрица в результате преобразования может перестать быть квадратной - несколько изменяется формат её
     * представления при выводе в выходной поток:
     * N (целое число) - количество строк
     * M (целое число) - количество столбцов
     * N * M целых чисел, являющихся элементами матрицы
     */
    @Override
    public void task19() {
        int[][] matrix = readMatrix(new Scanner(System.in));

        List<Integer> rowsToDelete = new ArrayList<>();
        loop:
        for (int i = 0; i < matrix.length; i++) {
            for (int element : matrix[i]) {
                if (element != 0) {
                    continue loop;
                }
            }
            rowsToDelete.add(i);
        }

        List<Integer> columnsToDelete = new ArrayList<>();
        loop:
        for (int j = 0; j < matrix.length; j++) {
            for (int[] row : matrix) {
                if (row[j] != 0) {
                    continue loop;
                }
            }
            columnsToDelete.add(j);
        }

        int[][] result = new int[matrix.length - rowsToDelete.size()][matrix.length - columnsToDelete.size()];
        int rowNumber = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (rowsToDelete.contains(i)) {
                continue;
            }
            int columnNumber = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (columnsToDelete.contains(j)) {
                    continue;
                }
                result[rowNumber][columnNumber] = matrix[i][j];
                columnNumber++;
            }
            rowNumber++;
        }

        printNonSquareMatrix(result);
    }

    /**
     * Описание:
     * В матрице найти минимальный элемент и переместить его на место заданного элемента путем перестановки строк и
     * столбцов. Гарантируется, что минимальный элемент в матрице встречается ровно один раз.
     * <p>
     * Формат входных данных:
     * X (целое число, 0 <= X < N) - номер строки
     * Y (целое число, 0 <= Y < N) - номер столбца
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task20 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task20() {
        Scanner scanner = new Scanner(System.in);
        int x = Integer.parseInt(scanner.nextLine());
        int y = Integer.parseInt(scanner.nextLine());
        int[][] matrix = readMatrix(scanner);

        int minX = 0;
        int minY = 0;
        int minElement = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (minElement > matrix[i][j]) {
                    minElement = matrix[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }

        //Swap rows
        int[] swap = matrix[minX];
        matrix[minX] = matrix[x];
        matrix[x] = swap;

        //Swap columns
        swap = new int[matrix.length];
        for (int j = 0; j < matrix.length; j++) {
            swap[j] = matrix[j][minY];
            matrix[j][minY] = matrix[j][y];
            matrix[j][y] = swap[j];
        }

        printMatrix(matrix);
    }

    /**
     * Описание:
     * Преобразовать строки матрицы таким образом, чтобы элементы, равные нулю, располагались после всех остальных.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task21 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task21() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        int[][] result = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            int columnCounter = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    result[i][columnCounter] = matrix[i][j];
                    columnCounter++;
                }
            }
            for (int j = columnCounter; j < result[0].length; j++) {
                result[i][j] = 0;
            }
        }

        printMatrix(result);
    }

    //Reads matrix of doubles from scanner
    private double[][] readDoubleMatrix(Scanner scanner) {
        Scanner newScanner = scanner.useLocale(Locale.FRANCE);
        final int DIMENSION = newScanner.nextInt();
        double[][] matrix = new double[DIMENSION][DIMENSION];

        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                matrix[row][col] = newScanner.nextDouble();
            }
        }

        return matrix;
    }

    /**
     * Описание:
     * Округлить все элементы матрицы до целого числа.
     * Использовать округление к ближайшему целому — число округляется до целого с использованием Math.round();
     * <p>
     * Формат входных данных:
     * Матрица. В данном задании входная матрица в качестве элементов содержит не целые числа а вещественные (в формате
     * Double). Для их извлечения нужно воспользоваться методом scanner.nextDouble() класса Scanner.
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task22 в выходной поток должна быть выведена преобразованная матрица, в которой
     * элементы являются целыми числами. Вывод матрицы осуществляется по обычным правилам.
     */
    @Override
    public void task22() {
        double[][] matrix = readDoubleMatrix(new Scanner(System.in));
        int[][] result = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = (int) round(matrix[i][j]);
            }
        }

        printMatrix(result);
    }

    /**
     * Описание:
     * Найти количество всех седловых точек матрицы. Матрица А имеет седловую точку (i, j), если А[i, j] является
     * минимальным элементом в i-й строке и максимальным в j-м столбце.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task23 в выходной поток должна быть выведено целое число, отображающее количество
     * седловых точек в матрице.
     */
    @Override
    public void task23() {
        int[][] matrix = readMatrix(new Scanner(System.in));

        int result = 0;
        for (int[] row : matrix) {

            int minInRow = row[0];
            for (int element : row) {
                minInRow = min(minInRow, element);
            }

            loop:
            for (int j = 0; j < row.length; j++) {
                if (row[j] == minInRow) {
                    for (int[] tempRow : matrix) {
                        if (tempRow[j] > minInRow) {
                            continue loop;
                        }
                    }
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    /**
     * Описание:
     * Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках полученной матрицы
     * возрастала.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task24 в выходной поток должна быть выведена преобразованная матрица. Если сумма
     * нескольких строк совпадает - в результирующей матрицы они должны следовать в том же порядке что и в исходной.
     */
    @Override
    public void task24() {
        class Structure {
            private int[] row;
            private int sumInRow;
            private int rowIndex;

            private Structure(int[] row, int sumInRow, int rowIndex) {
                this.row = row;
                this.sumInRow = sumInRow;
                this.rowIndex = rowIndex;
            }

            private int getSumInRow() {
                return sumInRow;
            }

            private int[] getRow() {
                return row.clone();
            }

            private int getRowIndex() {
                return rowIndex;
            }
        }

        int[][] matrix = readMatrix(new Scanner(System.in));

        List<Structure> structures = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int element : matrix[i]) {
                sum += element;
            }
            structures.add(new Structure(matrix[i], sum, i));
        }

        structures.sort(Comparator.comparingInt(Structure::getSumInRow)
                .thenComparing(Structure::getRowIndex));

        int[][] result = new int[matrix.length][];
        for (int i = 0; i < result.length; i++) {
            result[i] = structures.get(i).getRow();
        }

        printMatrix(result);
    }

    /**
     * Описание:
     * Найти число локальных минимумов. Соседями элемента матрицы назовем элементы, имеющие с ним общую сторону или
     * угол. Элемент матрицы называется локальным минимумом, если он строго меньше всех своих соседей.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task25 в выходной поток должно быть выведено число, соответствующее количеству
     * локальных минимумов в матрице.
     */
    @Override
    public void task25() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        int result = 0;

        if (matrix.length == 1) {
            System.out.println(1);
            return;
        }

        int[][] tempMatrix = new int[matrix.length + 2][matrix.length + 2];
        int max = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                tempMatrix[i + 1][j + 1] = matrix[i][j];
                max = max(max, matrix[i][j]);
            }
        }

        for (int i = 0; i < tempMatrix.length; i++) {
            tempMatrix[i][0] = max;
            tempMatrix[i][tempMatrix.length - 1] = max;
            tempMatrix[0][i] = max;
            tempMatrix[tempMatrix.length - 1][i] = max;
        }


        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix.length + 1; j++) {
                if (tempMatrix[i][j] < tempMatrix[i + 1][j - 1] &&
                        tempMatrix[i][j] < tempMatrix[i + 1][j] &&
                        tempMatrix[i][j] < tempMatrix[i + 1][j + 1] &&
                        tempMatrix[i][j] < tempMatrix[i][j - 1] &&
                        tempMatrix[i][j] < tempMatrix[i][j + 1] &&
                        tempMatrix[i][j] < tempMatrix[i - 1][j - 1] &&
                        tempMatrix[i][j] < tempMatrix[i - 1][j] &&
                        tempMatrix[i][j] < tempMatrix[i - 1][j + 1]) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    /**
     * Описание:
     * Найти наибольший среди локальных максимумов. Соседями элемента матрицы назовем элементы, имеющие с ним общую
     * сторону или угол. Элемент матрицы называется локальным максимумом, если он строго больше всех своих соседей.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task26 в выходной поток должно быть выведено число, соответствующее наибольшему
     * локальному максимуму. Если локальные максимумы в матрице отсутствуют - выводится строка "NOT FOUND".
     */
    @Override
    public void task26() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        boolean isLocalMaxExist = false;
        int result = 0;

        if (matrix.length == 1) {
            System.out.println(matrix[0][0]);
            return;
        }

        int[][] tempMatrix = new int[matrix.length + 2][matrix.length + 2];
        int min = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                tempMatrix[i + 1][j + 1] = matrix[i][j];
                min = min(min, matrix[i][j]);
            }
        }

        for (int i = 0; i < tempMatrix.length; i++) {
            tempMatrix[i][0] = min;
            tempMatrix[i][tempMatrix.length - 1] = min;
            tempMatrix[0][i] = min;
            tempMatrix[tempMatrix.length - 1][i] = min;
        }

        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix.length + 1; j++) {
                if (tempMatrix[i][j] > tempMatrix[i + 1][j - 1] &&
                        tempMatrix[i][j] > tempMatrix[i + 1][j] &&
                        tempMatrix[i][j] > tempMatrix[i + 1][j + 1] &&
                        tempMatrix[i][j] > tempMatrix[i][j - 1] &&
                        tempMatrix[i][j] > tempMatrix[i][j + 1] &&
                        tempMatrix[i][j] > tempMatrix[i - 1][j - 1] &&
                        tempMatrix[i][j] > tempMatrix[i - 1][j] &&
                        tempMatrix[i][j] > tempMatrix[i - 1][j + 1]) {
                    if (!isLocalMaxExist) {
                        result = tempMatrix[i][j];
                        isLocalMaxExist = true;
                    } else {
                        result = max(result, tempMatrix[i][j]);
                    }
                }
            }
        }

        if (isLocalMaxExist) {
            System.out.println(result);
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    /**
     * Описание:
     * Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы значения их характеристик убывали.
     * Характеристикой столбца прямоугольной матрицы называется сумма модулей его элементов. Если значения
     * характеристики совпадают - столбцы должны следовать в том же порядке, что и в исходной матрице.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task27 в выходной поток должна быть выведена преобразованная матрица, в которой
     * столбцы отсортированы по убыванию их характеристики.
     */
    @Override
    public void task27() {
        class Structure {
            private int[] column;
            private int sumInColumn;
            private int columnIndex;

            private Structure(int[] column, int sumInColumn, int columnIndex) {
                this.column = column;
                this.sumInColumn = sumInColumn;
                this.columnIndex = columnIndex;
            }

            private int getSumInColumn() {
                return sumInColumn;
            }

            private int[] getColumn() {
                return column.clone();
            }

            private int getColumnIndex() {
                return columnIndex;
            }
        }

        int[][] matrix = readMatrix(new Scanner(System.in));

        List<Structure> structures = new ArrayList<>();

        for (int j = 0; j < matrix[0].length; j++) {
            int sum = 0;
            int[] column = new int[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                column[i] = matrix[i][j];
                sum += abs(matrix[i][j]);
            }
            structures.add(new Structure(column, sum, j));
        }

        structures.sort(Comparator.comparingInt(Structure::getSumInColumn).reversed()
                .thenComparing(Structure::getColumnIndex));

        int[][] result = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < result[0].length; j++) {
            int[] column = structures.get(j).getColumn();
            for (int i = 0; i < result.length; i++) {
                result[i][j] = column[i];
            }
        }

        printMatrix(result);
    }

}
