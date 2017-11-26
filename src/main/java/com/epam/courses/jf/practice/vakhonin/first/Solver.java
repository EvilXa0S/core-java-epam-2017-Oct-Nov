package com.epam.courses.jf.practice.vakhonin.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.util.*;

public class Solver implements ISolver {

    static final String NOT_FOUND = "NOT FOUND";

    public void task1() {
        Scanner in = new Scanner(System.in);
        int numberOfStrings = Integer.valueOf(in.nextLine());
        int minLength = Integer.MAX_VALUE;
        int maxLength = 0;
        String minString = "";
        String maxString = "";
        String[] strings = new String[numberOfStrings];
        int length;

        for (int j = 0; j < numberOfStrings; j++) {
            strings[j] = in.nextLine();
        }

        for (String str : strings) {
            length = str.length();
            if (length <= minLength) {
                minLength = length;
                minString = str;
            }
            if (length >= maxLength) {
                maxLength = length;
                maxString = str;
            }
        }

        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }   //READY!

    public void task2() {
        List<String> list = new ArrayList<>();
        Comparator<String> comparator = (s1, s2) -> {
            int length1 = s1.length();
            int length2 = s2.length();
            int result;
            if (length1 == length2) {
                result = s1.compareToIgnoreCase(s2);
            } else {
                result = (length1 - length2);
            }
            return result;
        };

        Scanner in = new Scanner(System.in);
        int numberOfStrings = Integer.valueOf(in.nextLine());

        for (int j = 0; j < numberOfStrings; j++) {
            list.add(in.nextLine());
        }

        list.sort(comparator);

        for (String s : list) {
            System.out.printf("(%d): \"%s\"%n", s.length(), s);
        }
    }   // READY!

    public void task3() {
        Scanner in = new Scanner(System.in);
        int numberOfStrings = Integer.valueOf(in.nextLine());
        int length, averageLength, sumLength = 0;
        String string;
        String[] strings = new String[numberOfStrings];

        for (int j = 0; j < numberOfStrings; j++) {
            strings[j] = in.nextLine();
            sumLength += strings[j].length();
        }

        averageLength = sumLength / numberOfStrings;
        System.out.printf("AVERAGE (%d)%n", averageLength);

        for (int j = 0; j < numberOfStrings; j++) {
            string = strings[j];
            length = string.length();
            if (length < averageLength) {
                System.out.printf("(%d): \"%s\"%n", length, string);
            }
        }
    }   // READY!

    public void task4() {
        Scanner in = new Scanner(System.in);
        int numberOfWords = Integer.valueOf(in.nextLine());
        List<String> wordList = new ArrayList<>();

        for (int j = 0; j < numberOfWords; j++) {
            wordList.add(in.next());
        }

        Set<Character> tempSet;
        int tempSize;
        int size = Integer.MAX_VALUE;
        String word = "";

        for (String s : wordList) {
            tempSet = new HashSet<>();

            for (Character ch : s.toCharArray()) {
                tempSet.add(ch);
            }

            tempSize = tempSet.size();

            if (tempSize < size) {
                size = tempSize;
                word = s;
            }
        }

        System.out.println(word);
    }   // READY!

    public void task5() {
        Scanner in = new Scanner(System.in);
        int length;
        int numberOfWords = Integer.valueOf(in.nextLine());
        List<String> wordList = new ArrayList<>();

        for (int j = 0; j < numberOfWords; j++) {
            wordList.add(in.next());
        }

        int countOfVowels, countOfWords = 0;

        for (String word : wordList) {
            length = word.length();
            if (((length % 2) == 0) && (word.matches("^[a-zA-Z]+$"))) {
                countOfVowels = 0;
                for (Character ch : word.toCharArray()) {
                    if ((ch.toString()).matches("(?i:[aeiouy])")) {
                        countOfVowels++;
                    }
                }

                if (countOfVowels * 2 == length) {
                    countOfWords++;
                }
            }
        }

        System.out.println(countOfWords);
    }   // READY!

    public void task6() {
        Scanner in = new Scanner(System.in);

        int numberOfWords = Integer.valueOf(in.next());
        int code, prevCode = -1;
        String result = NOT_FOUND;
        String[] strings = new String[numberOfWords];

        for (int j = 0; j < numberOfWords; j++) {
            strings[j] = in.next();
        }

        for (String word : strings) {
            if (word.length() == 1) {
                continue;
            }

            for (Character ch : word.toCharArray()) {
                code = (int) ch;

                if (code > prevCode) {
                    prevCode = code;
                } else {
                    prevCode = -1;
                    break;
                }
            }

            if (prevCode != -1) {
                result = word;
                break;
            }
        }

        System.out.printf("%s%n", result);
    }   // READY!

    public void task7() {
        Scanner in = new Scanner(System.in);
        int numberOfWords = Integer.valueOf(in.nextLine());
        int wordsSize;
        String[] strings = new String[numberOfWords];

        for (int j = 0; j < numberOfWords; j++) {
            strings[j] = in.next();
        }

        int length;
        Set<Character> charSet;
        StringBuilder result;
        result = new StringBuilder("");
        Set<String> wordsSet = new LinkedHashSet<>();

        for (String word : strings) {
            charSet = new LinkedHashSet();
            length = word.length();

            for (Character ch : word.toCharArray()) {
                charSet.add(ch);
            }

            if (length == charSet.size()) {
                wordsSet.add(word);
            }
        }

        wordsSize = wordsSet.size();

        if (wordsSize == 0) {
            result.append(NOT_FOUND);
        } else {
            for (String s : wordsSet) {
                result.append(s);
                result.append(" ");
            }

            result.deleteCharAt(result.length() - 1);
        }

        System.out.println(result.toString());
    }   // READY!

    public void task8() {
        Scanner in = new Scanner(System.in);
        int n = Integer.valueOf(in.nextLine());
        List<String> stringList = new ArrayList<>();

        for (int j = 0; j < n; j++) {
            stringList.add(in.next());
        }

        int counter = 0;
        String result = NOT_FOUND;
        StringBuffer strBuf;

        for (String str : stringList) {
            if (str.matches("^\\d+$")) {
                strBuf = new StringBuffer(str);
                strBuf.reverse();

                if (strBuf.toString().equals(str)) {
                    result = str;
                    counter++;

                    if (counter == 2) {
                        break;
                    }
                }
            }
        }

        System.out.printf("%s%n", result);
    }   // READY!

    public void task9() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                matrix[j][k] = j * n + k + 1;
            }
        }

        printMatrix(matrix);
    }   // READY!

    public void task10() {
        Scanner in = new Scanner(System.in);
        double d;
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        BigDecimal x1, x2;
        String result = "";

        if (a == 0) {
            if (b == 0) {
                result = "No solution";
            }
            else {
                x1 = (new BigDecimal(-c / b)).setScale(2, BigDecimal.ROUND_HALF_UP);
                result = "One solution: " + x1;
            }
        }
        else {
            d = b * b - 4 * a * c;
            if (d > 0) {
                x1 = (new BigDecimal((-b + Math.sqrt(d)) / (2 * a))).setScale(2, BigDecimal.ROUND_HALF_UP);
                x2 = (new BigDecimal((-b - Math.sqrt(d)) / (2 * a))).setScale(2, BigDecimal.ROUND_HALF_UP);
                result = "Two solutions: " + x1 + ", " + x2;
            }
            if (d == 0) {
                x1 = (new BigDecimal(-b / (2 * a))).setScale(2, BigDecimal.ROUND_HALF_UP);
                result = "One solution: " + x1;
            }
            if (d < 0) {
                result = "No solution";
            }
        }

        System.out.println(result);
    }   // READY!

    public void task11() {
        Scanner in = new Scanner(System.in);
        String month = "";
        String result = "";
        String data = in.nextLine();
        int numberOfMonth;

        if (data.matches("([1][0-2])|([1-9])")) {
            numberOfMonth = Integer.valueOf(data);
            switch (numberOfMonth) {
                case 1:
                    month = "January";
                    break;
                case 2:
                    month = "February";
                    break;
                case 3:
                    month = "March";
                    break;
                case 4:
                    month = "April";
                    break;
                case 5:
                    month = "May";
                    break;
                case 6:
                    month = "June";
                    break;
                case 7:
                    month = "July";
                    break;
                case 8:
                    month = "August";
                    break;
                case 9:
                    month = "September";
                    break;
                case 10:
                    month = "October";
                    break;
                case 11:
                    month = "November";
                    break;
                case 12:
                    month = "December";
                    break;
                default:
                    break;
            }
            result += month;
        }
        else {
            result += "INCORRECT INPUT DATA";
        }

        System.out.println(result);
    }   // READY!

    public void task12() {
        Scanner in = new Scanner(System.in);
        int numberOfColumn = in.nextInt();
        int[][] matrix = enterMatrix(in);
        Arrays.sort(matrix, Comparator.comparingInt(row -> row[numberOfColumn]));
        System.out.println(matrix.length);
        printMatrix(matrix);
    }   // READY!

    public void task13() {
        Scanner in = new Scanner(System.in);
        int k, shift;
        int[][] matrix;
        k = in.nextInt();
        matrix = enterMatrix(in);
        int size = matrix.length;
        int[][] newMatrix = new int[size][size];
        shift = -(size + k % size) % size + size;

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                newMatrix[j][q] = matrix[(j + shift) % size][q];
            }
        }

        System.out.println(size);
        printMatrix(newMatrix);
    }   // READY!

    public void task14() {
        Scanner in = new Scanner(System.in);
        int n = Integer.valueOf(in.nextLine());
        int[] seq = new int[n];
        int count = 1;
        int max = 1;
        int result;

        for (int j = 0; j < n; j++) {
            seq[j] = in.nextInt();
        }

        for (int j = 1; j < n; j++) {
            if (seq[j] > seq[j - 1]) {
                count++;
            }
            else {
                max = count;
                count = 1;
            }
        }

        if (count > max) {
            max = count;
        }

        if (max == 1) {
            result = 0;
        }
        else {
            result = max;
        }

        System.out.println(result);
    }   // READY!

    public void task15() {
        Scanner in = new Scanner(System.in);
        int sumOfRow;
        int totalSum = 0;
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;


        for (int[] row: matrix) {
            int counter = 0;
            int index1 = -1;
            int index2 = -1;

            for (int j = 0; !((counter == 2) || (j == size)); j++) {
                if (row[j] > 0) {
                    counter++;

                    if (index1 > -1) {
                        index2 = j;
                    } else {
                        index1 = j;
                    }
                }
            }

            sumOfRow = 0;

            if ((counter == 2) && ((index2 - index1) > 1)) {
                for (int j = index1 + 1; j < index2; j++) {
                    sumOfRow += row[j];
                }
            }

            totalSum += sumOfRow;
        }
        System.out.println(totalSum);
    }   // READY!

    public void task16() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        int[][] matrixNew = new int[size][size];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                matrixNew[size - q - 1][j] = matrix[j][q];
            }
        }

        System.out.println(size);
        printMatrix(matrixNew);
    }   // READY!

    public void task17() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        int el;
        int det = 1;

        for (int j = 0; j < size; j++) {
            for (int k = j; k < size; k++) {
                el = matrix[j][k];

                if (el != 0) {
                    swapRows(matrix, j, k);
                    break;
                }

                det = 0;
                break;
            }

            if (det != 0) {
                for (int k = j + 1; k < size; k++) {
                    for (int i = size - 1; i >= j; i--) {
                        matrix[k][i] = matrix[j][j] * matrix[k][i] - matrix[k][j] * matrix[j][i];
                    }
                }
            }
        }

        if (det != 0) {
            det = matrix[size - 1][size - 1];

            for (int j = 0; j < size; j++) {
                for (int k = 0; k < (size - j - 2); k++) {
                    det /= matrix[j][j];
                }
            }
        }

        System.out.println(det);
    }   // READY!

    public void task18() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        Set<Integer> rows = new HashSet();
        Set<Integer> columns = new HashSet();
        int max = matrix[0][0];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                if (matrix[j][q] >= max) {
                    if (matrix[j][q] != max) {
                        rows = new HashSet();
                        columns = new HashSet();
                        max = matrix[j][q];
                    }

                    rows.add(j);
                    columns.add(q);
                }
            }
        }

        int[][] matrixNew = new int[size - rows.size()][size - columns.size()];
        int i, k = 0;

        for (int j = 0; j < size; j++) {
            if (!rows.contains(j)) {
                i = 0;

                for (int q = 0; q < size; q++) {
                    if (!columns.contains(q)) {
                        matrixNew[k][i] = matrix[j][q];
                        i++;
                    }
                }

                k++;
            }
        }

        System.out.println(matrixNew.length);
        System.out.println(matrixNew[0].length);
        printMatrix(matrixNew);
    }   // READY!

    public void task19() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int[][] tMatrix = transposeMatrix(matrixWithoutZeroRows(matrix));
        int[][] resultMatrix = transposeMatrix(matrixWithoutZeroRows(tMatrix));
        System.out.println(resultMatrix.length);
        System.out.println(resultMatrix[0].length);
        printMatrix(resultMatrix);
    }   // READY!

    public void task20() {
        Scanner in = new Scanner(System.in);
        int targetRow = in.nextInt();
        int targetColumn = in.nextInt();
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        int row = 0;
        int column = 0;
        int min = matrix[0][0];
        int[] temp;

        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                if (matrix[j][k] <= min) {
                    min = matrix[j][k];
                    row = j;
                    column = k;
                }
            }
        }

        swapRows(matrix, targetRow, row);
        int[][] matrixT = transposeMatrix(matrix);
        swapRows(matrixT, targetColumn, column);
        int[][] resultMatrix = transposeMatrix(matrixT);
        System.out.println(size);
        printMatrix(resultMatrix);
    }   // READY!

    public void task21() {
        Scanner in = new Scanner(System.in);
        Integer[][] matrix = enterMatrixInteger(in);
        int size = matrix.length;

        Comparator<Integer> comparator = (r1, r2) -> {
            int result = 0;

            if ((r1 == 0) &&  (r2 != 0)) {
                result = 1;
            }

            if ((r1 != 0) && (r2 == 0)) {
                result = -1;
            }

            return result;
        };

        for (Integer[] arr : matrix) {
            Arrays.sort(arr, comparator);
        }

        System.out.println(size);
        printMatrix(matrix);
    }   // READY!

    public void task22() {
        Scanner in = new Scanner(System.in);
        double[][] matrix = enterMatrixDouble(in);
        int size = matrix.length;
        int[][] matrixNew;
        matrixNew = new int[size][size];
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                matrixNew[j][k] = (int) Math.round(matrix[j][k]);
            }
        }

        System.out.println(size);
        printMatrix(matrixNew);
    }   // READY!

    public void task23() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int[][] matrixT = transposeMatrix(matrix.clone());
        int size = matrix.length;
        int number = 0;
        int min, max, index;
        Map<String, Integer> map;

        for (int j = 0; j < size; j++) {
            map = minElementOfArrayWithIndex(matrix[j]);
            min = map.get("minElement");
            index = map.get("index");
            max = maxElementOfArray(matrixT[index]);

            if (min == max) {
                number++;
            }
        }

        System.out.println(number);
    }   // READY!

    public void task24() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;

        Comparator<int[]> comparator = (arr1, arr2) -> {
            int result = 0;
            int sum1, sum2;
            sum1 = sum(arr1);
            sum2 = sum(arr2);

            if (sum2 > sum1) {
                result = -1;
            }

            if (sum2 < sum1) {
                result = 1;
            }

            return result;
        };

        Arrays.sort(matrix, comparator);
        System.out.println(size);
        printMatrix(matrix);
    }   // READY!

    public void task25() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        int[][] matrixExpanded = new int[size + 2][size + 2];
        int numberOfMinimum = 0;
        int el;

        for (int j = 0; j < (size + 2); j++) {
            matrixExpanded[j][0] = Integer.MAX_VALUE;
            matrixExpanded[j][size + 1] = Integer.MAX_VALUE;
            matrixExpanded[0][j] = Integer.MAX_VALUE;
            matrixExpanded[size + 1][j] = Integer.MAX_VALUE;
        }

        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                matrixExpanded[j + 1][k + 1] = matrix[j][k];
            }
        }

        for (int j = 1; j < size + 1; j++) {
            for (int k = 1; k < size + 1; k++) {
                el = matrixExpanded[j][k];
                if (((el < matrixExpanded[j - 1][k]) && (el < matrixExpanded[j - 1][k - 1]) && (el < matrixExpanded[j][k - 1]) && (el < matrixExpanded[j + 1][k - 1]) && (el < matrixExpanded[j + 1][k + 1]) && (el < matrixExpanded[j + 1][k]) && (el < matrixExpanded[j][k + 1]) && (el < matrixExpanded[j - 1][k + 1]))) {
                    numberOfMinimum++;
                }
            }
        }

        System.out.println(numberOfMinimum);
    }   // READY!

    public void task26() {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[][] matrix = new int[size + 2][size + 2];
        int maximum = Integer.MIN_VALUE;
        String result;

        for (int q = 1; q < size + 1; q++) {
            for (int j = 1; j < size + 1; j++) {
                matrix[j][q] = in.nextInt();
            }
        }

        for (int j = 0; j < size + 2; j++) {
            matrix[j][0] = Integer.MIN_VALUE;
            matrix[j][size + 1] = Integer.MIN_VALUE;
            matrix[0][j] = Integer.MIN_VALUE;
            matrix[size + 1][j] = Integer.MIN_VALUE;
        }

        for (int j = 1; j < size + 1; j++) {
            for (int k = 1; k < size + 1; k++) {
                if ((matrix[j][k] > maximum) && ((matrix[j][k] > matrix[j - 1][k]) && (matrix[j][k] > matrix[j - 1][k - 1]) && (matrix[j][k] > matrix[j][k - 1]) && (matrix[j][k] > matrix[j + 1][k - 1]) && (matrix[j][k] > matrix[j + 1][k + 1]) && (matrix[j][k] > matrix[j + 1][k]) && (matrix[j][k] > matrix[j][k + 1]) && (matrix[j][k] > matrix[j - 1][k + 1]))) {
                    maximum = matrix[j][k];
                }
            }
        }

        if (maximum == Integer.MIN_VALUE) {
            result = NOT_FOUND;
        } else {
            result = "" + maximum;
        }

        System.out.println(result);
    }   // READY!

    public void task27() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = transposeMatrix(enterMatrix(in));
        int size = matrix.length;
        Comparator<int[]> comparator = (line1, line2) -> characteristic(line2) - characteristic(line1);
        Arrays.sort(matrix, comparator);
        int[][] resultMatrix = transposeMatrix(matrix);
        System.out.println(size);
        printMatrix(resultMatrix);
    }   // READY!

    int characteristic(int[] line) {
        int result = 0;

        for (int j : line) {
            result += Math.abs(j);
        }

        return result;
    }

    int sum(int[] arr) {
        int sum = 0;

        for (int j : arr) {
            sum += j;
        }

        return sum;
    }

    void swapRows(int[][] matrix, int j, int k) {
        int[] temp = matrix[j];
        matrix[j] = matrix[k];
        matrix[k] = temp;
    }

    Integer[][] enterMatrixInteger(Scanner in) {
        int size = in.nextInt();
        Integer[][] matrix = new Integer[size][size];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                matrix[j][q] = in.nextInt();
            }
        }

        return matrix;
    }

    int[][] enterMatrix(Scanner in) {
        int size = in.nextInt();
        int[][] matrix = new int[size][size];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                matrix[j][q] = in.nextInt();
            }
        }

        return matrix;
    }

    double[][] enterMatrixDouble(Scanner in) {
        int size = in.nextInt();
        double[][] matrix = new double[size][size];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                matrix[j][q] = in.nextDouble();
            }
        }

        return matrix;
    }

    void printMatrix(Integer[][] matrix) {
        StringBuilder stringMatrix = new StringBuilder();
        int height = matrix.length;
        int width = matrix[0].length;
        int length;

        for (int j = 0; j < height; j++) {
            for (int q = 0; q < width; q++) {
                stringMatrix.append(matrix[j][q] + "\t");
            }

            length = stringMatrix.length();
            stringMatrix.setLength(length - 1);
            stringMatrix.append("\n");
        }

        length = stringMatrix.length();
        stringMatrix.setLength(length - 1);
        System.out.println(stringMatrix);
    }

    void printMatrix(int[][] matrix) {
        StringBuilder stringMatrix = new StringBuilder();
        int height = matrix.length;
        int width = matrix[0].length;
        int length;

        for (int j = 0; j < height; j++) {
            for (int q = 0; q < width; q++) {
                stringMatrix.append(matrix[j][q] + "\t");
            }

            length = stringMatrix.length();
            stringMatrix.setLength(length - 1);
            stringMatrix.append("\n");
        }

        length = stringMatrix.length();
        stringMatrix.setLength(length - 1);
        System.out.println(stringMatrix);
    }

    int[][] transposeMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] tMatrix = new int[width][height];

        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                tMatrix[k][j] = matrix[j][k];
            }
        }

        return tMatrix;
    }

    Map<String, Integer> minElementOfArrayWithIndex(int[] arr) {
        Map<String, Integer> map = new HashMap<>();
        Integer min = Integer.MAX_VALUE;
        Integer index = 0;
        Integer el;
        int n = arr.length;

        for (int j = 0; j < n; j++) {
            el = arr[j];

            if (el < min) {
                min = el;
                index = j;
            }
        }

        map.put("index", index);
        map.put("minElement", min);

        return map;
    }

    int maxElementOfArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (Integer el : arr) {
            if (el > max) {
                max = el;
            }
        }

        return max;
    }

    int[][] matrixWithoutZeroRows(int[][] matrix){
        List<int[]> listOfRows = new ArrayList<>();

        for (int[] row: matrix) {
            for (int x: row) {
                if (x != 0) {
                    listOfRows.add(row);
                    break;
                }
            }
        }

        int[][] newMatrix = new int[listOfRows.size()][matrix.length];

        for (int j = 0; j < listOfRows.size(); j++) {
            newMatrix[j] = listOfRows.get(j);
        }

        return newMatrix;
    }
}
