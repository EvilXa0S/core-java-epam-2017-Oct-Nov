package com.epam.courses.jf.practice.mallayarov.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class SolverImpl implements ISolver {

    public static final String MESSAGE_NOT_FOUND = "NOT FOUND";
    public static final String MESSAGE_INCORRECT_INPUT_DATA = "INCORRECT INPUT DATA";

     private void pushElementToTheEnd(int[][] matrix, int positionFrom, int row) {
        for (int j = positionFrom; j < matrix.length - 1; ++j) {
            int temp = matrix[row][j];
            matrix[row][j] = matrix[row][j + 1];
            matrix[row][j + 1] = temp;
        }
    }

    // Элемент матрицы называется локальным минимумом, если он строго меньше всех своих соседей.
     private boolean isLocalMin(int[][] matrix, int row, int col) {
        int current = matrix[row][col];

        int startPosX = row - 1 < 0 ? row : row - 1;
        int endPosX = row + 1 > matrix.length - 1 ? row : row + 1;
        int startPosY = col - 1 < 0 ? col : col - 1;
        int endPosY = col + 1 > matrix.length - 1 ? col : col + 1;

        for (int i = startPosX; i <= endPosX; ++i) {
            for (int j = startPosY; j <= endPosY; ++j) {
                if (i == row && j == col) {
                    continue;
                }

                if (matrix[i][j] <= current) {
                    return false;
                }
            }
        }

        return true;
    }

    // Элемент матрицы называется локальным максимумом, если он строго больше всех своих соседей
     private boolean isLocalMax(int[][] matrix, int row, int col) {
        int current = matrix[row][col];

        int startPosX = row - 1 < 0 ? row : row - 1;
        int endPosX = row + 1 > matrix.length - 1 ? row : row + 1;
        int startPosY = col - 1 < 0 ? col : col - 1;
        int endPosY = col + 1 > matrix.length - 1 ? col : col + 1;

        for (int i = startPosX; i <= endPosX; ++i) {
            for (int j = startPosY; j <= endPosY; ++j) {
                if (i == row && j == col) {
                    continue;
                }

                if (matrix[i][j] >= current) {
                    return false;
                }
            }
        }

        return true;
    }

     private int[][] removeRowsAndColumns(int[][] matrix, HashSet<Integer> removeRowIndices, HashSet<Integer> removeColumnIndices) {
        int k = 0; // index of row in new matrix
        int[][] processedArray = new int[matrix.length - removeRowIndices.size()][matrix.length - removeColumnIndices.size()];

        for (int i = 0; i < matrix.length; ++i) {
            // ignore row we should delete (no increasing of row index)
            if (removeRowIndices.contains(i)) {
                continue;
            }

            int l = 0; // index of column in new matrix
            for (int j = 0; j < matrix.length; ++j) {
                // ignore the column we should delete (no increasing of column index)
                if (removeColumnIndices.contains(j)) {
                    continue;
                }

                processedArray[k][l] = matrix[i][j];
                ++l;
            }

            ++k;
        }

        return processedArray;
    }

    // https://ru.wikipedia.org/wiki/%D0%9E%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C#Значение_определителя_матрицы
    // https://ru.wikipedia.org/wiki/%D0%94%D0%BE%D0%BF%D0%BE%D0%BB%D0%BD%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%BC%D0%B8%D0%BD%D0%BE%D1%80
     private int countDeterminant(int[][] matrix) {
        int result = 0;

        if (matrix.length == 1) {
            return matrix[0][0];
        }

        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        for (int i = 0; i < matrix[0].length; i++) {
            int[][] temporary = new int[matrix.length - 1][matrix[0].length - 1];
            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        temporary[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temporary[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }

            result += matrix[0][i] * Math.pow(-1, (double) i) * countDeterminant(temporary);
        }

        return result;
    }

    static private int countSumBetweenPositions(int[] arr, int from, int to) {
        if (from == -1 || to == -1) {
            return 0;
        }

        int sum = 0;
        for (int i = from + 1; i < to; ++i) {
            sum += arr[i];
        }

        return sum;
    }

     private void swapMatrixLines(int[][] matrix, int firstLine, int secondLine) {
        for (int i = 0; i < matrix.length; ++i) {
            int temp = matrix[firstLine][i];
            matrix[firstLine][i] = matrix[secondLine][i];
            matrix[secondLine][i] = temp;
        }
    }

    static private int[][] transposeMatrix(int[][] m) {
        int[][] temp = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                temp[j][i] = m[i][j];
            }
        }

        return temp;
    }

    static private void reverse(int[] arr, int start, int end) {
        for (int i = start; i <= (start + end) / 2; ++i) {
            int temp = arr[start + end - i];
            arr[start + end - i] = arr[i];
            arr[i] = temp;
        }
    }

    static private int[][] readMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        int[][] matrix = new int[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextInt();
            }
        }

        return matrix;
    }

    static private void showMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; ++row) {
            String separator = "";
            for (int col = 0; col < matrix[0].length; ++col) {
                System.out.print(separator + matrix[row][col]);
                separator = "\t";
            }
            System.out.println();
        }
    }

    /**
     * Ввести N строк, найти самую короткую и самую длинную строки.
     * Вывести найденные строки и их длину. Если строк, удовлетворяющих условию, более одной - вывести последнюю из них.
     */
    @Override
    public void task1() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        String maxString = sc.nextLine();
        String minString = maxString;

        for (int i = 1; i < n; ++i) {
            String current = sc.nextLine();

            if (current.length() >= maxString.length()) {
                maxString = current;
            }

            if (current.length() <= minString.length()) {
                minString = current;
            }
        }

        System.out.printf("MIN (%d): \"%s\"%n", minString.length(), minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxString.length(), maxString);

        sc.close();
    }

    /**
     * Ввести N строк. Упорядочить и вывести строки в порядке возрастания значений их длины.
     * В случае, если длины строк совпадают - упорядочить их в лексикографическом порядке.
     */
    @Override
    public void task2() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<String> input = new ArrayList<>(n);

        for (int i = 0; i < n; ++i) {
            input.add(sc.nextLine());
        }

        // sort by length then lexicographically
        input.sort((o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return o1.compareTo(o2);
        });

        for (String string : input) {
            System.out.printf("(%d): \"%s\"%n", string.length(), string);
        }

        sc.close();
    }

    /**
     * Ввести N строк. Вывести те строки, длина которых меньше средней.
     * Под 'средней' подразумевается среднеарифметическая величина длины всех строк, округленная до целого в меньшую сторону.
     */
    @Override
    public void task3() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        int averageLength = 0;
        List<String> input = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            String currentString = sc.nextLine();
            input.add(currentString);
            averageLength += currentString.length();
        }

        averageLength = StrictMath.floorDiv(averageLength, n);
        System.out.printf("AVERAGE (%d)%n", averageLength);

        for (String string : input) {
            if (string.length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", string.length(), string);
            }
        }

        sc.close();
    }

    /**
     * Ввести N слов, состоящих из символов английского алфавита.
     * Найти слово, в котором число различных символов минимально.
     * Символы верхнего и нижнего регистра считать различными. Если таких слов несколько, найти первое из них.
     */
    @Override
    public void task4() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        String[] input = new String[n];
        int[] unique = new int[n];

        for (int i = 0; i < n; ++i) {
            input[i] = sc.next();
            char[] word = input[i].toCharArray();

            for (int j = 0; j < input[i].length() - 1; ++j) {
                for (int k = 1; k < input[i].length(); ++k) {
                    if (word[j] != word[k]) {
                        ++unique[i];
                    }
                }
            }
        }

        // search index of shortest word
        int position = 0;
        for (int i = 0; i < unique.length; ++i) {
            if (unique[position] > unique[i]) {
                position = i;
            }
        }

        System.out.println(input[position]);

        sc.close();
    }

    /**
     * Ввести N слов.
     * Найти количество слов, содержащих только символы латинского алфавита,
     * а среди них – количество слов с равным числом гласных и согласных букв.
     */
    @Override
    public void task5() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<String> englishInput = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            String current = sc.next();
            if (current.matches("^[A-z]+$")) {
                englishInput.add(current);
            }
        }

        int count = 0;
        for (String string : englishInput) {
            char[] word = string.toCharArray();
            int consonant = 0;
            int vowel = 0;
            for (char aWord : word) {
                if ("AEIOUaeiou".indexOf(aWord) != -1) {
                    ++vowel;
                } else {
                    ++consonant;
                }
            }

            if (consonant == vowel) {
                ++count;
            }
        }

        System.out.println(count);

        sc.close();
    }

    /**
     * Ввести N слов.
     * Найти слово, символы в котором идут в строгом порядке возрастания их кодов. Если таких слов несколько, найти первое из них.
     */
    @Override
    public void task6() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        boolean isEndReached = false;

        for (int i = 0; i < n; ++i) {

            char[] word = sc.next().toCharArray();

            // one character word is not included
            if (word.length == 1) {
                continue;
            }

            // check char by char
            // break cycle if the sequence is not strictly increasing
            char currentChar = word[0];
            for (int j = 1; j < word.length; ++j) {
                isEndReached = false;

                if (currentChar >= word[j]) {
                    break;
                }

                currentChar = word[j];
                isEndReached = true;
            }

            // if previous cycle reached its end
            if (isEndReached) {
                System.out.println(word);
                return;
            }
        }

        // if we didn't find any solution
        System.out.println(MESSAGE_NOT_FOUND);

        sc.close();
    }

    /**
     * Ввести N слов. Найти слова, состоящие только из различных символов.
     * В случае, если слово встречается более одного раза - вывести его единожды.
     */
    @Override
    public void task7() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        HashSet<String> result = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            String word = sc.next();
            int checker = 0;

            // see "Cracking the Coding interview - Chapter 1. Arrays and Strings, ex. 1.1"
            for (int j = 0; j < word.length(); ++j) {
                int val = word.charAt(j) - 'a'; // char -> index (like 'a' -> 0, 'b' -> 1 and etc.)

                // (1 << val) - number with one bit in val position
                if ((checker & (1 << val)) > 0) {
                    checker = -1; // mark as *not* unique
                    break;
                }

                // set one bit in val position
                checker |= (1 << val);
            }

            if (checker != -1) {
                result.add(word);
            }
        }

        if (result.size() != 0) {
            StringBuilder sb = new StringBuilder();
            String separator = ""; // to have "a b" instead of "a b "
            for (String string : result) {
                sb.append(separator);
                sb.append(string);
                separator = " ";
            }

            System.out.println(sb);
        } else {
            System.out.println(MESSAGE_NOT_FOUND);
        }

        sc.close();
    }

    /**
     * Ввести N слов. Помимо обычных слов, во входной последовательности могут встречаться целые числа.
     * Среди них необходимо найти число-палиндром (одинаково читающееся в обоих направлениях).
     * Если таких чисел больше одного, найти второе из них. Ограничения на размер числа нет.
     * Одна цифра является палиндромом.
     */
    @Override
    public void task8() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        int count = 0;
        String palindrome = null;

        for (int i = 0; i < n; ++i) {
            StringBuilder input = new StringBuilder(sc.next());
            // if string contains only numbers and reversed string is equals to string
            if ((input.toString()).matches("^[0-9]+")
                    && ((input.toString()).equals(input.reverse().toString()))) {
                palindrome = input.toString();
                ++count;

                if (count == 2) { // show up the second palindrome
                    System.out.println(palindrome);
                    break;
                }
            }
        }

        if (count == 0) {
            System.out.println(MESSAGE_NOT_FOUND);
        } else if (count < 2) {
            System.out.println(palindrome);
        }

        sc.close();
    }

    /**
     * Написать программу, которая выводит числа от 1 до N^2 в виде матрицы NxN слева направо и сверху вниз.
     */
    @Override
    public void task9() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; ++i) {
            String separator = "";
            for (int j = 0; j < n; ++j) {
                System.out.print(separator + (n * i + j + 1));
                separator = "\t";
            }
            System.out.println();
        }

        sc.close();
    }

    /**
     * Написать программу, позволяющую корректно находить корни квадратного уравнения.
     * Параметры уравнения должны задаваться с командной строки.
     */
    @Override
    public void task10() {
        Scanner sc = new Scanner(System.in);
        int a = Integer.parseInt(sc.next());
        int b = Integer.parseInt(sc.next());
        int c = Integer.parseInt(sc.next());

        int D = b * b - 4 * a * c;

        if (D > 0) {
            BigDecimal x1 = new BigDecimal((-b + StrictMath.sqrt(D)) / (2 * a));
            BigDecimal x2 = new BigDecimal((-b - StrictMath.sqrt(D)) / (2 * a));
            x1 = x1.setScale(2, RoundingMode.HALF_UP);
            x2 = x2.setScale(2, RoundingMode.HALF_UP);
            System.out.println("Two solutions: " + x2 + ", " + x1); // to pass the test
        } else if (D == 0) {
            BigDecimal x = new BigDecimal((-b + StrictMath.sqrt(D)) / (2 * a));
            x = x.setScale(2, RoundingMode.HALF_UP);
            System.out.println("One solution: " + x);
        } else {
            System.out.println("No solution");
        }

        sc.close();
    }

    /**
     * Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу.
     * При реализации использовать оператор switch. Осуществить проверку корректности ввода числа.
     */
    @Override
    public void task11() {
        Scanner sc = new Scanner(System.in);

        String answer;
        if (sc.hasNextInt()) {
            int n = Integer.parseInt(sc.nextLine());

            // also we can make every month a constant
            switch (n) {
                case 1:
                    answer = "January";
                    break;
                case 2:
                    answer = "February";
                    break;
                case 3:
                    answer = "March";
                    break;
                case 4:
                    answer = "April";
                    break;
                case 5:
                    answer = "May";
                    break;
                case 6:
                    answer = "June";
                    break;
                case 7:
                    answer = "July";
                    break;
                case 8:
                    answer = "August";
                    break;
                case 9:
                    answer = "September";
                    break;
                case 10:
                    answer = "October";
                    break;
                case 11:
                    answer = "November";
                    break;
                case 12:
                    answer = "December";
                    break;
                default:
                    answer = MESSAGE_INCORRECT_INPUT_DATA;
            }
        } else {
            answer = MESSAGE_INCORRECT_INPUT_DATA;
        }

        System.out.println(answer);

        sc.close();
    }

    /**
     * Упорядочить строки матрицы размерности N в порядке возрастания значений элементов k-го столбца.
     */
    @Override
    public void task12() {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine());
        int[][] matrix = readMatrix(sc);

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 1; j < matrix.length - i; ++j) {
                if (matrix[j - 1][k] > matrix[j][k]) {
                    swapMatrixLines(matrix, j - 1, j);
                }
            }
        }

        System.out.println(matrix.length);
        showMatrix(matrix);

        sc.close();
    }

    /**
     * Выполнить циклический сдвиг матрицы размерности N на k позиций вниз.
     * Если k > 0 - производится циклический сдвиг матрицы вниз
     * Если k < 0 - производится циклический сдвиг матрицы вверх
     * Если k = 0 - матрица остается без изменений
     */
    @Override
    public void task13() {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine());
        int[][] matrix = readMatrix(sc);

        matrix = transposeMatrix(matrix); // transpose matrix for convenience`s sake

        // exclude blank shifts
        if ((k % matrix.length) != 0) {
            for (int[] row : matrix) {
                int to = (Math.abs(k) % matrix.length);

                // to shift a column up we should shift the transposed row to left
                if (k < 0) {
                    reverse(row, 0, matrix.length - 1);
                }

                reverse(row, 0, to);
                reverse(row, to + 1, matrix.length - 1);

                // to shift a column down... we should do the opposite
                if (k > 0) {
                    reverse(row, 0, matrix.length - 1);
                }
            }
        }

        matrix = transposeMatrix(matrix);
        System.out.println(matrix.length);
        showMatrix(matrix);

        sc.close();
    }

    /**
     * Найти наибольшее число строго возрастающих элементов последовательности, идущих подряд.
     * Оператор отношения можно определить на множестве, включающем более одного элемента.
     */
    @Override
    public void task14() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int previous = Integer.parseInt(sc.next());

        int maxSequenceLength = 0;
        int currentCounter = 1;

        for (int i = 1; i < n; ++i) {
            int temp = Integer.parseInt(sc.next());

            if (temp > previous) {
                maxSequenceLength = StrictMath.max(maxSequenceLength, ++currentCounter);
            } else {
                currentCounter = 1;
            }

            previous = temp;
        }

        System.out.println(maxSequenceLength);

        sc.close();
    }

    /**
     * Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
     */
    @Override
    public void task15() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        int sum = 0;

        for (int[] row : matrix) {
            // indices of elements
            int firstPositive = -1;
            int secondPositive = -1;

            for (int j = 0; j < matrix.length; ++j) {
                if (row[j] > 0) {
                    if (firstPositive == -1) {
                        firstPositive = j;
                    } else {
                        secondPositive = j;
                        break;
                    }
                }
            }

            sum += countSumBetweenPositions(row, firstPositive, secondPositive);
        }

        System.out.println(sum);

        sc.close();
    }

    /**
     * Повернуть матрицу на 90 градусов против часовой стрелки.
     */
    @Override
    public void task16() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        /*
        any NxN matrix will have N/2 square cycles
        The idea is for each square cycle,
        we swap the elements involved with the corresponding cell in the matrix in anti-clockwise direction
        i.e. from top to left, left to bottom, bottom to right and from right to top one at a time
         */
        for (int x = 0; x < matrix.length / 2; ++x) {
            for (int y = x; y < matrix.length - x - 1; ++y) {
                int temp = matrix[x][y];

                matrix[x][y] = matrix[y][matrix.length - x - 1];
                matrix[y][matrix.length - 1 - x] = matrix[matrix.length - x - 1][matrix.length - y - 1];
                matrix[matrix.length - x - 1][matrix.length - y - 1] = matrix[matrix.length - y - 1][x];
                matrix[matrix.length - y - 1][x] = temp;
            }
        }

        System.out.println(matrix.length);
        showMatrix(matrix);

        sc.close();
    }

    /**
     * Вычислить определитель матрицы.
     */
    @Override
    public void task17() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        int answer = countDeterminant(matrix);
        System.out.println(answer);

        sc.close();
    }

    /**
     * Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
     */
    @Override
    public void task18() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        int max = Integer.MIN_VALUE;
        HashSet<Integer> rowsToRemove = new HashSet<>();
        HashSet<Integer> columnsToRemove = new HashSet<>();

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                max = StrictMath.max(max, matrix[i][j]);
            }
        }

        // if row or column contains max element then we add its index to hashset
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] == max) {
                    rowsToRemove.add(i);
                    columnsToRemove.add(j);
                }
            }
        }

        matrix = removeRowsAndColumns(matrix, rowsToRemove, columnsToRemove);

        System.out.println(matrix.length + "\n" + matrix[0].length);
        showMatrix(matrix);

        sc.close();
    }

    /**
     * Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
     */
    @Override
    public void task19() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        HashSet<Integer> rowsToRemove = new HashSet<>();
        HashSet<Integer> columnsToRemove = new HashSet<>();

        /*
        Мы можем найти все нулевые строки и столбцы за один проход с помощью массивов boolean для строк и столбцов.
        Если встретили ненулевой, то по соответствующему индексу false (или true, чтобы отдельно не нинициализировать массивы)

        boolean[] rowsContainSomethingButZero = new boolean[matrix.length];
        boolean[] columnsContainSomethingButZero = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] != 0) {
                   rowsContainSomethingButZero[i] = true;
                   columnsContainSomethingButZero[j] = true;
                }
            }
        }
        */

        // check if a row is "nullable"
        boolean isZero;
        for (int i = 0; i < matrix.length; ++i) {
            isZero = true;

            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] != 0) {
                    isZero = false;
                    break;
                }
            }

            if (isZero) {
                rowsToRemove.add(i);
            }
        }

        // check if row is "nullable"
        for (int j = 0; j < matrix[0].length; ++j) {
            isZero = true;

            for (int i = 0; i < matrix.length; ++i) {
                if (matrix[i][j] != 0) {
                    isZero = false;
                    break;
                }
            }

            if (isZero) {
                columnsToRemove.add(j);
            }
        }

        matrix = removeRowsAndColumns(matrix, rowsToRemove, columnsToRemove);
        System.out.println(matrix.length + "\n" + matrix[0].length);
        showMatrix(matrix);

        sc.close();
    }

    /**
     * В матрице найти минимальный элемент и переместить его на место заданного элемента путем перестановки строк и столбцов.
     * Гарантируется, что минимальный элемент в матрице встречается ровно один раз.
     */
    @Override
    public void task20() {
        Scanner sc = new Scanner(System.in);

        int x = Integer.parseInt(sc.nextLine());
        int y = Integer.parseInt(sc.nextLine());
        int[][] matrix = readMatrix(sc);
        int min = Integer.MAX_VALUE;
        int minX = 0;
        int minY = 0;

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (min > matrix[i][j]) {
                    min = matrix[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }

        // swap rows
        if (minX != x) {
            for (int j = 0; j < matrix[0].length; ++j) {
                int temp = matrix[x][j];
                matrix[x][j] = matrix[minX][j];
                matrix[minX][j] = temp;
            }
        }

        // swap columns
        if (minY != y) {
            for (int i = 0; i < matrix.length; ++i) {
                int temp = matrix[i][y];
                matrix[i][y] = matrix[i][minY];
                matrix[i][minY] = temp;
            }
        }

        System.out.println(matrix.length);
        showMatrix(matrix);

        sc.close();
    }

    /**
     * Преобразовать строки матрицы таким образом, чтобы элементы, равные нулю, располагались после всех остальных.
     */
    @Override
    public void task21() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = matrix[0].length - 1; j >= 0; --j) { // analyze from the end of a row
                if (matrix[i][j] == 0) {
                    pushElementToTheEnd(matrix, j, i); // we push all zeroes to the end so non zero elements will float to the beginning
                }
            }
        }

        System.out.println(matrix.length);
        showMatrix(matrix);

        sc.close();
    }

    /**
     * Округлить все элементы матрицы до целого числа
     * Использовать округление к ближайшему целому — число округляется до целого, модуль разности с которым у этого числа минимален
     */
    @Override
    public void task22() {
        Scanner sc = new Scanner(System.in);
        final int DIMENSION = sc.nextInt();
        double[][] matrix = new double[DIMENSION][DIMENSION];

        System.out.println(DIMENSION);

        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = sc.nextDouble();
                System.out.print(StrictMath.round(matrix[row][col]) + "\t"); // meet StrictMath.round()!
            }
            System.out.println();
        }

        sc.close();
    }

    /**
     * Найти количество всех седловых точек матрицы.
     * Матрица А имеет седловую точку (i, j), если А[i, j] является минимальным элементом в i-й строке и максимальным в j-м столбце.
     */
    @Override
    public void task23() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        Map.Entry<Integer, Integer>[] minInRow = new Map.Entry[matrix.length]; // Array of pairs column - value, index of array = index of row
        int[] maxInColumn = new int[matrix.length]; // array of max values of matrix column

        // initialize
        for (int i = 0; i < maxInColumn.length; ++i) {
            maxInColumn[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < matrix.length; ++i) {
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < matrix.length; ++j) {
                if (min > matrix[i][j]) {
                    min = matrix[i][j];
                    minInRow[i] = new AbstractMap.SimpleEntry<>(j, min);
                }
                maxInColumn[j] = StrictMath.max(maxInColumn[j], matrix[i][j]);
            }
        }

        int result = 0;
        for (int i = 0; i < matrix.length; ++i) {
            // is saddle point?
            if (maxInColumn[minInRow[i].getKey()] == minInRow[i].getValue()) {
                ++result;
            }
        }

        System.out.println(result);

        sc.close();
    }

    /**
     * Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках полученной матрицы возрастала.
     */
    @Override
    public void task24() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        // key - row, value - max, compare by value
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (int i = 0; i < matrix.length; ++i) {
            int sum = 0;
            for (int j = 0; j < matrix.length; ++j) {
                sum += matrix[i][j];
            }

            priorityQueue.add(new AbstractMap.SimpleEntry<>(i, sum));
        }

        int[][] newMatrix = new int[matrix.length][matrix.length];

        while (!priorityQueue.isEmpty()) {
            Map.Entry<Integer, Integer> map = priorityQueue.poll();
            // fill new matrix from 0 0
            for (int j = 0; j < newMatrix.length; ++j) {
                newMatrix[matrix.length - priorityQueue.size() - 1][j] = matrix[map.getKey()][j];
            }
        }

        matrix = newMatrix;
        System.out.println(matrix.length);
        showMatrix(matrix);

        sc.close();
    }

    /**
     * Найти число локальных минимумов.
     * Соседями элемента матрицы назовем элементы, имеющие с ним общую сторону или угол.
     * Элемент матрицы называется локальным минимумом, если он строго меньше всех своих соседей.
     */
    @Override
    public void task25() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        int count = 0;

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                if (isLocalMin(matrix, i, j)) {
                    ++count;
                }
            }
        }

        System.out.println(count);

        sc.close();
    }

    /**
     * Найти наибольший среди локальных максимумов.
     * Соседями элемента матрицы назовем элементы, имеющие с ним общую сторону или угол.
     * Элемент матрицы называется локальным максимумом, если он строго больше всех своих соседей.
     */
    @Override
    public void task26() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        int count = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                if (isLocalMax(matrix, i, j)) {
                    ++count;
                    max = StrictMath.max(max, matrix[i][j]);
                }
            }
        }

        if (count == 0) {
            System.out.println(MESSAGE_NOT_FOUND);
        } else {
            System.out.println(max);
        }

        sc.close();
    }

    /**
     * Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы значения их характеристик убывали.
     * Характеристикой столбца прямоугольной матрицы называется сумма модулей его элементов.
     * Если значения характеристики совпадают - столбцы должны следовать в том же порядке, что и в исходной матрице.
     */
    @Override
    public void task27() {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = readMatrix(sc);

        // key - column, value - sum, compare by decreasing the value of sum
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());

        for (int j = 0; j < matrix.length; ++j) {
            int sum = 0;
            for (int i = 0; i < matrix.length; ++i) {
                sum += Math.abs(matrix[i][j]);
            }

            priorityQueue.add(new AbstractMap.SimpleEntry<>(j, sum));
        }

        int[][] newMatrix = new int[matrix.length][matrix.length];

        while (!priorityQueue.isEmpty()) {
            Map.Entry<Integer, Integer> map = priorityQueue.poll();
            // fill new matrix from 0 0
            for (int i = 0; i < newMatrix.length; ++i) {
                newMatrix[i][matrix.length - priorityQueue.size() - 1] = matrix[i][map.getKey()];
            }
        }

        matrix = newMatrix;
        System.out.println(matrix.length);
        showMatrix(matrix);

        sc.close();
    }
}