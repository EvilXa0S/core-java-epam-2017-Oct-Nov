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
     * To input N lines, find the shortest and longest lines.
     * Output the found lines and their length.
     * If the rows satisfying the conditions more than one
     * output the last one.
     * <p>
     * N (integer, 0 < N < 100) - count of input lines
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
     * To input N lines. To sort and output the rows
     * in ascending order of their length values.
     * If lines have same length,
     * you need to sort them in a lexicographical order.
     */
    @Override
    public void task2() {
        Scanner scanner = new Scanner(System.in);
        String strings[] = getStrings(scanner);
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
     * Recording srings from the console
     *
     * Method reads from the console int number of input strings and
     * puts all input string into array of type String.
     * @param scanner
     * @return array of input strings
     */
    private String[] getStrings(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());

        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = scanner.nextLine();
        }
        return strings;
    }

    /**
     * Enter N lines.
     * Output those lines whose length is less than the average.
     * 'Average' means the arithmetic value of the length
     * All lines, rounded down to the whole in the smaller side.
     */
    @Override
    public void task3() {

        Scanner scanner = new Scanner(System.in);

        String strings[] = getStrings(scanner);
        int averageLength = 0;

        for (String string : strings) {
            averageLength += string.length();
        }
        averageLength /= strings.length;
        System.out.printf("AVERAGE (%d)%n", averageLength);
        for (String str : strings) {
            if (str.length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", str.length(), str);
            }
        }

        scanner.close();
    }

    /**
     * Enter N words consisting of characters
     * from the English alphabet. Find the word in which
     * the number of different characters is minimal.
     * The upper and lower case letters are various.
     * If there are several such words, find the first one.
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
     * Enter N words. Find the number of words
     * containing only Latin characters,
     * among them to find the number of words
     * with an equal number of vowels and consonants.
     */
    @Override
    public void task5() {

        Scanner scanner = new Scanner(System.in);

        int wordNumber = Integer.parseInt(scanner.nextLine());
        int vowel = 0;

        String string[] = new String[wordNumber];

        Pattern patternLatinWords = Pattern.compile("[A-z]+");
        Matcher matcherLatinWords =
                patternLatinWords.matcher(scanner.nextLine());

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
     * Enter N words. Find the word which the characters stay
     * in the strict order of increasing their codes.
     * If there are several such words, to find the first one.
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
     * Enter N words.
     * Find words are consisting of only different characters.
     * In case the word occurs more than once - output it once.
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
     * Enter N words. The input sequence can occur whole numbers.
     * It is necessary to find a number-palindrome from the numbers.
     * If there are more than one such number, find the second one.
     * There are no restrictions on the size of the number.
     * One number is a palindrome.
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
                String reverseNumber = new StringBuilder(number).
                        reverse().toString();

                if (number.equals(reverseNumber)) {
                    palindrom.replace(0, palindrom.length(), number);
                }
            } catch (NumberFormatException e) {
                System.err.println(e);
            }
        }

        System.out.println(palindrom);

        scanner.close();
    }

    /**
     * This progamm outputs numbers fron 1 to N^2
     * in the matrix format from left to right and from up to bottom.
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
     * The programm allows to find the root of the quadratic equation.
     * Equation parametrs must be given from the standart input.
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
     * Enter number from 1 to 12.
     * The programm outputs to the console a name of month,
     * which correspond to input number.
     * It is necessary to use a switch operator
     * and to check the correct input.
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
     * Need to sort row of matrix with N dimension
     * in ascending order of k column element.
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
     * Perform a cyclic shift of the matrix
     * of dimension N by k positions down.
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
     * To find the biggest count of strictly increasing elements
     * of the sequence which stays in succession.
     * The many musts to have more then 1 element.
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
     * To find the sum of matrux elements, which locates from
     * the first and the second positive elements in every row.
     * If a row does not have two positive elements,
     * the sum equals to zero
     * as the sum between two neighbour elements.
     */
    @Override
    public void task15() {
        int matrix[][] = readIntMatrix();
        int sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            boolean flag = false;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] > 0) {
                    if (flag) {
                        break;
                    }
                    flag = true;
                } else {
                    if (flag)
                        sum += matrix[i][j];
                }
            }
        }

        System.out.println(sum);
    }

    /**
     * Rotates the matrix counterclockwise 90 degrees.
     */
    @Override
    public void task16() {
        int matrix[][] = readIntMatrix();
        int newMatrix[][] = new int[matrix.length][matrix.length];

        for (int column = 0; column < matrix.length; column++) {
            for (int line = 0; line < matrix.length; line++) {
                newMatrix[matrix.length - 1 - column][line] =
                        matrix[line][column];
            }
        }
        printIntMatrix(matrix);
//        System.out.println(matrix.length);
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix.length; j++) {
//                System.out.print(newMatrix[i][j] + "\t");
//            }
//            System.out.println();
//        }
    }

    /**
     * Find the determinant of the square matrix.
     */
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
     * Find the maximal elements in matrix and delete from matrix
     * all rows and columns which consists maximal element.
     */
    @Override
    public void task18() {
        int matrix[][] = readIntMatrix();

        HashSet<Integer> lineSet = new HashSet<>();
        HashSet<Integer> columnSet = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            lineSet.add(i);
            columnSet.add(i);
        }

        int max = matrix[0][0];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix.length - 1; j >= 0; j--) {
                if (matrix[i][j] == max) {
                    lineSet.remove(i);
                    columnSet.remove(j);
                }
            }
        }

        System.out.println(lineSet.size());
        System.out.println(columnSet.size());

        for (Integer line : lineSet) {
            for (Integer column : columnSet) {
                System.out.print(matrix[line][column] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Seal the matrix, removing the rows and columns
     * filled with zeros from it.
     */
    @Override
    public void task19() {
        int matrix[][] = readIntMatrix();

        HashSet<Integer> lineSet = new HashSet<>();
        HashSet<Integer> colomnSet = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    lineSet.add(i);
                    colomnSet.add(j);
                }
            }
        }

        System.out.println(lineSet.size());
        System.out.println(colomnSet.size());

        for (Integer line : lineSet) {
            for (Integer colomn : colomnSet) {
                System.out.print(matrix[line][colomn] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Find the minimal element and replace it to necessary place
     * by replacing rows and columns. The minimal element is unique.
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
        printIntMatrix(matrix);
//        System.out.println(matrixDimension);
//        for (int i[] : matrix) {
//            for (int j : i) {
//                System.out.print(j + "\t");
//            }
//            System.out.println();
//        }
        scanner.close();
    }

    /**
     * Convert the rows of the matrix in such a way that
     * the elements equal to zero are located after all the others.
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

        printIntMatrix(matrix);
//        System.out.println(matrixDimension);
//        for (int i[] : matrix) {
//            for (int j : i) {
//                System.out.print(j + "\t");
//            }
//            System.out.println();
//        }
        scanner.close();
    }

    /**
     * Round all the elements of the matrix to an integer.
     * Use rounding to the nearest integer -
     * the number is rounded to the nearest integer
     * using Math.round ().
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
     *
     * Find the number of all saddle points of the matrix.
     * The matrix A has a saddle point (i, j)
     * if A [i, j] is the minimal element in the i-th row
     * and the maximal in the j-th column.
     */
    @Override
    public void task23() {
        int matrix[][] = readIntMatrix();
        int minElement;
        int maxElement;

        boolean flagMin = true;
        int saddle = 0;

        for (int i = 0; i < matrix.length; i++) {
            minElement = matrix[i][0];
            int minElementColumn = 0;
            for (int column = 1; column < matrix.length; column++) {
                if (minElement >= matrix[i][column]) {
                    if (minElement == matrix[i][column]) {
                        flagMin = false;
                    } else {
                        minElement = matrix[i][column];
                        minElementColumn = column;
                        flagMin = true;
                    }
                }
            }

            if (flagMin) {
                boolean flagMax = true;
                maxElement = matrix[i][minElementColumn];
                for (int line = 0; line < matrix.length; line++) {
                    if (line != i && maxElement
                            <= matrix[line][minElementColumn]) {
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
    }

    /**
     * Rebuild the matrix, rearranging the rows in it so that the sum
     * of the elements in the rows of the resulting matrix increases.
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
     * Find the number of local minimal.
     * Neighbors of the matrix element are elements
     * that have a common side or angle with it.
     * An element of a matrix is called a local minimum
     * if it is strictly less than all its neighbors.
     */
    @Override
    public void task25() {

        int matrix[][] = readIntMatrix();
        int localMin = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                boolean leftY = (j - 1) >= 0;
                boolean rightY = (j + 1) < matrix.length;
                boolean toptX = (i - 1) >= 0;
                boolean bottomX = (i + 1) < matrix.length;

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
    }

    /**
     * Find the largest among the local maximal.
     * Neighbors of the matrix element are elements
     * that have a common side or angle with it.
     * An element of a matrix is called a local maximum
     * if it is strictly greater than all its neighbors.
     */
    @Override
    public void task26() {
        int matrix[][] = readIntMatrix();
        ArrayList<Integer> localMax = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                boolean leftY = (j - 1) >= 0;
                boolean rightY = (j + 1) < matrix.length;
                boolean toptX = (i - 1) >= 0;
                boolean bottomX = (i + 1) < matrix.length;

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
    }

    /**
     * Rebuild the given matrix by rearranging the columns in it so
     * that the values of their characteristics will be decrease.
     * The characteristic of a column of a rectangular matrix
     * is the sum of the modules of its elements.
     * If the characteristic values are the same,
     * the columns should follow the same order
     * as in the original matrix.
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

        int columnNumber = 0;
        for(int position = 0; position < matrixDimension; position++) {
            if (position > 0 && characteristicsValue[position - 1]
                    == characteristicsValue[position]) {
                columnNumber++;
            } else {
                columnNumber = 0;
            }

            for(int i = columnNumber; i < matrixDimension; i++) {
                if (characteristicsValue[position] ==
                        matrix[matrixDimension][i]) {

                    for (int j = 0; j < matrixDimension; j++) {
                        newMatrix[j][position] = matrix[j][i];
                    }
                    columnNumber = i;
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

    /**
     * Input an array element by element
     *
     * Input the array size from the console.
     * To create the two-dimensional array
     * and fills it with elements from the console.
     * @return array of int type
     */
    private static int[][] readIntMatrix() {
        Scanner scanner = new Scanner(System.in);
        int matrixSize = scanner.nextInt();

        int[][] matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        return matrix;
    }

    /**
     * Вывод матрицы на экран
     *
     * @param matrix матрица типа int
     */
    private static void printIntMatrix(int[][] matrix) {
        System.out.println(matrix.length);
        for (int[] i : matrix) {
            for (int j : i){
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }
}

