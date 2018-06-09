package com.epam.courses.jf.practice.klimenko.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.lang.reflect.Array;
import java.util.*;

public class SolverImpl implements ISolver {
    /**
     * Поиск самой короткой и длинной строки.
     */
    @Override
    public void task1() {
        List<String> list = readLines(new Scanner(System.in));
        String minString = list.get(0), maxString = list.get(0);
        int minLength = minString.length(), maxLength = maxString.length();

        for (String currentString : list) {
            if (currentString.length() <= minLength) {
                minLength = currentString.length();
                minString = currentString;
            }
            if (currentString.length() >= maxLength) {
                maxLength = currentString.length();
                maxString = currentString;
            }
        }

        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }

    /**
     * Упорядочивание строк по длине.
     */
    @Override
    public void task2() {
        List<String> list = readLines(new Scanner(System.in));

        list.sort(Comparator
                .comparing(String::length)
                .thenComparing(str -> str)
        );

        for (String string : list) {
            System.out.printf("(%d): \"%s\"%n", string.length(), string);
        }
    }

    /**
     * Поиск строк меньше средней.
     */
    @Override
    public void task3() {
        List<String> list = readLines(new Scanner(System.in));
        int sumOfLengths = 0;
        int averageLength;

        for (String string : list) {
            sumOfLengths += string.length();
        }
        averageLength = sumOfLengths / list.size();

        System.out.printf("AVERAGE (%d)%n", averageLength);
        for (String string : list) {
            if (string.length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", string.length(), string);
            }
        }
    }

    /**
     * Поиск слова с наименьшим числом различных символов.
     */
    @Override
    public void task4() {
        List<String> words = readWords(new Scanner(System.in));
        String minWord = "???";
        int minCharset = 65536;

        for (String word : words) {
            Set<Character> charset = new HashSet<>();
            for (int i = 0; i < word.length(); ++i) {
                charset.add(word.charAt(i));
            }
            if (charset.size() < minCharset) {
                minCharset = charset.size();
                minWord = word;
            }
        }

        System.out.println(minWord);
    }

    /**
     * Поиск слов с равным числом гласных и согласных.
     */
    @Override
    public void task5() {
        List<String> words = readWords(new Scanner(System.in));
        int matchedWordsCount = 0;
        String vowels = "aeiouy";

        for (String word : words) {
            boolean alphaOnly = true;
            int vowelsCount = 0;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (!Character.isAlphabetic(c)) {
                    alphaOnly = false;
                    break;
                }
                if (vowels.indexOf(Character.toLowerCase(c)) >= 0) {
                    ++vowelsCount;
                }
            }
            if (alphaOnly && vowelsCount == word.length() - vowelsCount) {
                ++matchedWordsCount;
            }
        }

        System.out.println(matchedWordsCount);
    }

    /**
     * Поиск слова с возрастающими буквами.
     */
    @Override
    public void task6() {
        List<String> words = readWords(new Scanner(System.in));
        String matchedWord = "NOT FOUND";

        for (String word : words) {
            boolean sorted = word.length() > 1;
            for (int i = 1; i < word.length(); ++i) {
                if (word.codePointAt(i - 1) >= word.codePointAt(i)) {
                    sorted = false;
                    break;
                }
            }
            if (sorted) {
                matchedWord = word;
                break;
            }
        }

        System.out.println(matchedWord);
    }

    /**
     * Поиск слова, состоящего из различных символов.
     */
    @Override
    public void task7() {
        List<String> words = readWords(new Scanner(System.in));
        Set<String> matchedWords = new HashSet<>();

        for (String word : words) {
            Set<Character> charset = new HashSet<>();
            boolean matched = true;

            for (int i = 0; i < word.length(); ++i) {
                Character c = Character.toLowerCase(word.charAt(i));
                if (!charset.add(c)) {
                    matched = false;
                    break;
                }
            }

            if (matched) {
                if (matchedWords.add(word)) {
                    if (matchedWords.size() > 1) {
                        System.out.print(" ");
                    }
                    System.out.printf("%s", word);
                }
            }
        }

        if (matchedWords.isEmpty()) {
            System.out.print("NOT FOUND");
        }
        System.out.println();
    }

    /**
     * Поиск числа-палиндрома.
     */
    @Override
    public void task8() {
        List<String> words = readWords(new Scanner(System.in));
        String firstMatch = null, secondMatch = null;

        for (String word : words) {
            if (word.matches("[0-9]+")
                    && new StringBuilder(word).reverse().toString().equals(word)) {
                if (firstMatch == null) {
                    firstMatch = word;
                } else {
                    secondMatch = word;
                    break;
                }
            }
        }

        System.out.println(
                secondMatch != null ? secondMatch :
                        firstMatch != null ? firstMatch :
                                "NOT FOUND");
    }

    /**
     * Вывод матрицы заданной размерности.
     */
    @Override
    public void task9() {
        int size = new Scanner(System.in).nextInt();

        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                int element = row * size + col + 1;
                System.out.printf("%d", element);
                if (col < size - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * Поиск корней квадратного уравнения.
     */
    @Override
    public void task10() {
        Scanner scanner = new Scanner(System.in);
        Locale loc = Locale.US;
        int coefA = scanner.nextInt();
        int coefB = scanner.nextInt();
        int coefC = scanner.nextInt();
        double det = coefB * coefB - 4 * coefA * coefC;

        if (det < 0) {
            System.out.println("No solution");
            return;
        }

        double sqrtDet = Math.sqrt(det);

        if (det == 0) {
            double solution = (sqrtDet - coefB) / (2 * coefA);
            System.out.printf(loc, "One solution: %.2f\n", solution);
            return;
        }

        double solutionA = (-coefB - sqrtDet) / (2 * coefA);
        double solutionB = (-coefB + sqrtDet) / (2 * coefA);
        System.out.printf(loc, "Two solutions: %.2f, %.2f\n", solutionA, solutionB);
    }

    /**
     * Вывод названия месяца.
     */
    @Override
    public void task11() {
        String monthName = "INCORRECT INPUT DATA";

        try {
            int monthIndex = new Scanner(System.in).nextInt();

            switch (monthIndex) {
                case 1:
                    monthName = "January";
                    break;
                case 2:
                    monthName = "February";
                    break;
                case 3:
                    monthName = "March";
                    break;
                case 4:
                    monthName = "April";
                    break;
                case 5:
                    monthName = "May";
                    break;
                case 6:
                    monthName = "June";
                    break;
                case 7:
                    monthName = "July";
                    break;
                case 8:
                    monthName = "August";
                    break;
                case 9:
                    monthName = "September";
                    break;
                case 10:
                    monthName = "October";
                    break;
                case 11:
                    monthName = "November";
                    break;
                case 12:
                    monthName = "December";
                    break;
                default:
                    // Do nothing
                    break;
            }
        } catch (InputMismatchException e) {
            // Ignore exception
        }

        System.out.println(monthName);
    }

    /**
     * Упорядочивание строк матрицы.
     */
    @Override
    public void task12() {
        Scanner scanner = new Scanner(System.in);
        int column = scanner.nextInt();
        Integer[][] matrix = readSquareMatrix(scanner, Integer.class);

        Arrays.asList(matrix).sort(Comparator.comparing((Integer[] row) -> row[column]));

        printIntegerMatrix(matrix, true);
    }

    /**
     * Циклический сдвиг матрицы.
     */
    @Override
    public void task13() {
        Scanner scanner = new Scanner(System.in);
        int offset = scanner.nextInt();
        Integer[][] matrix = readSquareMatrix(scanner, Integer.class);
        int matrixSize = matrix.length;
        Integer[][] newMatrix = new Integer[matrixSize][];

        offset = (offset % matrixSize + matrixSize) % matrixSize;
        for (int i = 0; i < matrixSize; ++i) {
            int shifted = (i + offset) % matrixSize;
            newMatrix[shifted] = matrix[i];
        }
        matrix = newMatrix;

        printIntegerMatrix(matrix, true);
    }

    /**
     * Поиск возрастающей подпоследовательности.
     */
    @Override
    public void task14() {
        Scanner scanner = new Scanner(System.in);
        int prevValue;
        int count = scanner.nextInt();
        int currentValue = scanner.nextInt();
        int currentLength = 0, maxLength = 0;

        for (int i = 1; i < count; ++i) {
            prevValue = currentValue;
            currentValue = scanner.nextInt();
            if (currentValue > prevValue) {
                currentLength = currentLength == 0 ? 2 : currentLength + 1;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 0;
            }
        }
        maxLength = Math.max(maxLength, currentLength);

        System.out.println(maxLength);
    }

    /**
     * Поиск суммы элементов матрицы в строках.
     */
    @Override
    public void task15() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);
        int matrixSum = 0;

        for (Integer[] row : matrix) {
            List<Integer> positiveIndex = new ArrayList<>();
            for (int i = 0; i < matrix.length; i++) {
                if (row[i] > 0) {
                    positiveIndex.add(i);
                }
            }
            int rowSum = 0;
            if (positiveIndex.size() > 1) {
                for (int i = positiveIndex.get(0) + 1; i < positiveIndex.get(1); ++i) {
                    rowSum += row[i];
                }
            }
            matrixSum += rowSum;
        }

        System.out.println(matrixSum);
    }

    /**
     * Поворот матрицы против часовой стрелки.
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public void task16() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);
        int matrixSize = matrix.length;
        Integer[][] newMatrix = new Integer[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                int i1 = matrixSize - j - 1;
                int j1 = i;
                newMatrix[i1][j1] = matrix[i][j];
            }
        }
        matrix = newMatrix;

        printIntegerMatrix(matrix, true);
    }

    /**
     * Вычисление определителя матрицы.
     */
    @Override
    public void task17() {
        Double[][] matrix = readSquareMatrix(new Scanner(System.in), Double.class);
        int matrixSize = matrix.length;
        double determinant = 1;

        for (int rowIndex = 0; rowIndex < matrixSize; ++rowIndex) {
            int lead = -1;
            for (int i = rowIndex; i < matrixSize; ++i) {
                if (matrix[i][rowIndex] != 0
                        && (lead == -1
                        || Math.abs(matrix[i][rowIndex]) > Math.abs(matrix[lead][rowIndex]))
                        ) {
                    lead = i;
                }
            }
            if (lead == -1) {
                determinant = 0;
                break;
            }

            Double[] tmp = matrix[rowIndex];
            matrix[rowIndex] = matrix[lead];
            matrix[lead] = tmp;

            if ((lead - rowIndex) % 2 == 1) {
                determinant *= -1;
            }
            determinant *= matrix[rowIndex][rowIndex];

            for (int i = rowIndex + 1; i < matrixSize; ++i) {
                double multiplier = matrix[i][rowIndex] / matrix[rowIndex][rowIndex];
                for (int j = rowIndex; j < matrixSize; ++j) {
                    matrix[i][j] -= matrix[rowIndex][j] * multiplier;
                }
            }
        }

        System.out.printf("%.0f%n", determinant);
    }

    /**
     * Удаление максимального элемента матрицы.
     */
    @Override
    public void task18() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);
        int matrixSize = matrix.length;
        Set<Integer> rowsToDelete = new HashSet<>();
        Set<Integer> columnsToDelete = new HashSet<>();
        int maxValue = matrix[0][0];

        for (Integer[] row : matrix) {
            for (int val : row) {
                maxValue = Math.max(maxValue, val);
            }
        }

        for (int rowIndex = 0; rowIndex < matrixSize; ++rowIndex) {
            for (int colIndex = 0; colIndex < matrixSize; ++colIndex) {
                if (matrix[rowIndex][colIndex] == maxValue) {
                    rowsToDelete.add(rowIndex);
                    columnsToDelete.add(colIndex);
                }
            }
        }

        matrix = filterMatrix(matrix, rowsToDelete, columnsToDelete);

        printIntegerMatrix(matrix, false);
    }

    /**
     * Уплотнение матрицы.
     */
    @Override
    public void task19() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);
        int matrixSize = matrix.length;
        Set<Integer> rowsToDelete = new HashSet<>();
        Set<Integer> columnsToDelete = new HashSet<>();

        for (int i = 0; i < matrixSize; ++i) {
            boolean allZeroes = true;
            for (int j = 0; j < matrixSize; ++j) {
                allZeroes &= matrix[i][j] == 0;
            }
            if (allZeroes) {
                rowsToDelete.add(i);
            }
        }

        for (int j = 0; j < matrixSize; ++j) {
            boolean allZeroes = true;
            for (Integer[] aMatrix : matrix) {
                allZeroes &= aMatrix[j] == 0;
            }
            if (allZeroes) {
                columnsToDelete.add(j);
            }
        }

        matrix = filterMatrix(matrix, rowsToDelete, columnsToDelete);

        printIntegerMatrix(matrix, false);
    }

    /**
     * Перемещение минимального элемента матрицы.
     */
    @Override
    public void task20() {
        Scanner scanner = new Scanner(System.in);
        int y = scanner.nextInt();
        int x = scanner.nextInt();
        Integer[][] matrix = readSquareMatrix(scanner, Integer.class);
        int matrixSize = matrix.length;
        int imin = 0, jmin = 0, minValue = matrix[0][0];

        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                if (matrix[i][j] < minValue) {
                    minValue = matrix[i][j];
                    imin = i;
                    jmin = j;
                }
            }
        }

        {
            Integer[] tmp = matrix[y];
            matrix[y] = matrix[imin];
            matrix[imin] = tmp;
        }

        for (int i = 0; i < matrixSize; ++i) {
            int tmp = matrix[i][x];
            matrix[i][x] = matrix[i][jmin];
            matrix[i][jmin] = tmp;
        }

        printIntegerMatrix(matrix, true);
    }

    /**
     * Перемещение нулей в строках матрицы.
     */
    @Override
    public void task21() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);

        for (Integer[] row : matrix) {
            Arrays.sort(row, (Integer a, Integer b) -> {
                if (b == 0) {
                    return b - a;
                }
                return 0;
            });
        }

        printIntegerMatrix(matrix, true);
    }

    /**
     * Округление элементов матрицы.
     */
    @Override
    public void task22() {
        Double[][] matrix = readSquareMatrix(new Scanner(System.in), Double.class);
        int matrixSize = matrix.length;

        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                matrix[i][j] = (double) Math.round(matrix[i][j]);
            }
        }

        printDoubleSquareMatrix(matrix, "%.0f");
    }

    /**
     * Поиск седловых точек матрицы.
     */
    @Override
    public void task23() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);
        int matrixSize = matrix.length;
        Integer[] maxColValue = new Integer[matrixSize];
        Integer[] minRowValue = new Integer[matrixSize];
        int count = 0;

        for (int index = 0; index < matrixSize; ++index) {
            maxColValue[index] = matrix[0][index];
            minRowValue[index] = matrix[index][0];
            for (int i = 0; i < matrixSize; ++i) {
                maxColValue[index] = Math.max(maxColValue[index], matrix[i][index]);
                minRowValue[index] = Math.min(minRowValue[index], matrix[index][i]);
            }
        }

        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                if (matrix[i][j].equals(minRowValue[i]) && matrix[i][j].equals(maxColValue[j])) {
                    ++count;
                }
            }
        }

        System.out.println(count);
    }

    /**
     * Сортировка строк матрицы.
     */
    @Override
    public void task24() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);
        int matrixSize = matrix.length;

        Arrays.sort(matrix, (Integer[] a, Integer[] b) -> {
            Integer asum = 0, bsum = 0;
            for (int i = 0; i < matrixSize; ++i) {
                asum += a[i];
                bsum += b[i];
            }
            return asum - bsum;
        });

        printIntegerMatrix(matrix, true);
    }

    /**
     * Поиск числа локальных минимумов матрицы.
     */
    @Override
    public void task25() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);
        int matrixSize = matrix.length;
        int count = 0;

        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                Integer val = matrix[i][j];
                boolean minimum = true;

                for (int direction = 0; direction < 8; ++direction) {
                    int i1 = i + yOffset8[direction];
                    int j1 = j + xOffset8[direction];
                    if (validIndexes(i1, j1, matrixSize) && val >= matrix[i1][j1]) {
                        minimum = false;
                        break;
                    }
                }

                if (minimum) {
                    ++count;
                }
            }
        }

        System.out.println(count);
    }

    /**
     * Поиск наибольшего локального максимума матрицы.
     */
    @Override
    public void task26() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);
        int matrixSize = matrix.length;
        boolean found = false;
        int maxMax = -1;

        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                boolean maximum = true;
                Integer val = matrix[i][j];

                for (int direction = 0; direction < 8; ++direction) {
                    int i1 = i + yOffset8[direction];
                    int j1 = j + xOffset8[direction];
                    if (validIndexes(i1, j1, matrixSize) && val <= matrix[i1][j1]) {
                        maximum = false;
                        break;
                    }
                }

                if (maximum && (!found || val > maxMax)) {
                    maxMax = val;
                    found = true;
                }
            }
        }

        if (found) {
            System.out.println(maxMax);
        } else {
            System.out.println("NOT FOUND");
        }
    }

    /**
     * Сортировка столбцов матрицы.
     */
    @Override
    public void task27() {
        Integer[][] matrix = readSquareMatrix(new Scanner(System.in), Integer.class);
        int matrixSize = matrix.length;
        Integer[] colIndex = new Integer[matrixSize];

        for (int i = 0; i < matrixSize; ++i) {
            colIndex[i] = i;
        }

        Arrays.sort(colIndex, (Integer a, Integer b) -> {
            int charA = 0, charB = 0;
            for (Integer[] aMatrix : matrix) {
                charA += Math.abs(aMatrix[a]);
                charB += Math.abs(aMatrix[b]);
            }
            return charB - charA;
        });

        for (int i = 0; i < matrixSize; ++i) {
            Integer[] newRow = new Integer[matrixSize];
            for (int j = 0; j < matrixSize; ++j) {
                newRow[j] = matrix[i][colIndex[j]];
            }
            matrix[i] = newRow;
        }

        printIntegerMatrix(matrix, true);
    }

    private static final int[] xOffset8 = {1, 1, 0, -1, -1, -1, 0, 1};
    private static final int[] yOffset8 = {0, -1, -1, -1, 0, 1, 1, 1};

    /**
     * @param i Номер строки.
     * @param j Номер столбца.
     * @param size Размер матрицы.
     * @return Находится ли указанная позиция внутри матрицы заданного размера.
     */
    private static boolean validIndexes(int i, int j, int size) {
        return i >= 0 && i < size && j >= 0 && j < size;
    }

    /**
     * Чтение из входного потока построчно.
     * @param scanner Входной поток.
     * @return Список прочитанных строк.
     */
    private static List<String> readLines(Scanner scanner) {
        List<String> ret = new ArrayList<>();
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; ++i) {
            ret.add(scanner.nextLine());
        }

        return ret;
    }

    /**
     * Чтение из входного потока слов, разделенных пробелами.
     * @param scanner Входной поток.
     * @return Список прочитанных строк.
     */
    private static List<String> readWords(Scanner scanner) {
        int count = scanner.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            list.add(scanner.next());
        }
        return list;
    }

    /**
     * Чтение матрицы.
     * @param scanner Входной поток.
     * @param clazz Тип элементов матрицы.
     * @return Двумерный массив clazz[][].
     */
    @SuppressWarnings("unchecked")
    private static <T extends Number> T[][] readSquareMatrix(Scanner scanner, Class<T> clazz) {
        int size = scanner.nextInt();
        T[][] ret = (T[][]) Array.newInstance(clazz, size, size);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (clazz == Integer.class) {
                    ret[i][j] = (T) (Integer) scanner.nextInt();
                } else if (clazz == Double.class) {
                    ret[i][j] = (T) (Double) scanner.nextDouble();
                } else {
                    throw new RuntimeException();
                }
            }
        }
        return ret;
    }

    /**
     * Печать целочисленной матрицы в стандартный вывод.
     * @param matrix Матрица.
     * @param square Формат вывода размера матрицы. Для квадратных печатается только одно измерение.
     */
    private static void printIntegerMatrix(Integer[][] matrix, boolean square) {
        System.out.print(matrix.length);
        if (!square) {
            System.out.printf(" %d", matrix[0].length);
        }
        System.out.println();

        for (Integer[] row : matrix) {
            boolean first = true;
            for (Integer val : row) {
                if (!first) {
                    System.out.print(" ");
                }
                System.out.print(val);
                first = false;
            }
            System.out.println();
        }
    }

    /**
     * Печать матрицы вещественных чисел в стандартный вывод.
     * @param matrix Матрица.
     * @param fmt Формат вывода вещественного числа.
     */
    private static void printDoubleSquareMatrix(Double[][] matrix, String fmt) {
        System.out.println(matrix.length);
        for (Double[] row : matrix) {
            boolean first = true;
            for (Double val : row) {
                if (!first) {
                    System.out.print(" ");
                }
                System.out.printf(fmt, val);
                first = false;
            }
            System.out.println();
        }
    }

    /**
     * Удаление указанных строк и столбцов из матрицы.
     * @param matrix Исходная матрица.
     * @param rowsToDelete Номера строк для удаления.
     * @param columnsToDelete Номера столбцов для удаления.
     * @return Новая матрица.
     */
    private static Integer[][] filterMatrix(Integer[][] matrix, Set<Integer> rowsToDelete, Set<Integer> columnsToDelete) {
        int matrixSize = matrix.length;
        int newWidth = matrixSize - columnsToDelete.size();
        int newHeight = matrixSize - rowsToDelete.size();
        Integer[][] newMatrix = new Integer[newHeight][newWidth];
        int i1 = 0;

        for (int i = 0; i < matrixSize; ++i) {
            if (!rowsToDelete.contains(i)) {
                int j1 = 0;
                for (int j = 0; j < matrixSize; ++j) {
                    if (!columnsToDelete.contains(j)) {
                        newMatrix[i1][j1] = matrix[i][j];
                        ++j1;
                    }
                }
                ++i1;
            }
        }

        return newMatrix;
    }
}
