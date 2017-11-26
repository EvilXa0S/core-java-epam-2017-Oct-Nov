package com.epam.courses.jf.practice.vkostin.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.*;

/**
 * For all:
 + BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); could be used
 Scanner - The reading methods are not synchronized
 + byte numberOfStrings = input.nextByte(); shuold be used
 + List more flexible and readable then array
 + JavaDocs not provided
 + final should be used
 */
public class Solver implements ISolver {

    public final static String NOT_FOUND = "NOT FOUND";
    public final static String MAX = "max";
    public final static String MIN = "min";

    /**
     * Find the shortest and the longest among the entered N lines.
     *
     * + Why short?
     * + Java 8 Stream API looks more elegant:

     * + final Comparator<String> comp = (l1, l2) -> Integer.compare( l1.length(), l2.length());
     * + String min = in.lines().min(comp).get();
     * + String max = in.lines().max(comp).get();
     */
    @Override
    public void task1() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            final int numberOfStrings = Integer.parseInt(input.readLine());
            final Comparator<String> comp = (l1, l2) -> Integer.compare(l1.length(), l2.length());

            List<String> strings = new ArrayList<>();

            for (int i = 0; i < numberOfStrings; ++i)
                strings.add(input.readLine());

            strings.sort(comp);

            String minStr = strings.get(0);
            String maxStr = strings.get(numberOfStrings - 1);

            System.out.printf("MIN (%d): \"%s\"%n", minStr.length(), minStr);
            System.out.printf("MAX (%d): \"%s\"%n", maxStr.length(), maxStr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Arrange and output the rows in ascending order of their length values.
     *
     * + Java 8 Stream API could be used
     */
    @Override
    public void task2() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            final int numberOfStrings = Integer.parseInt(input.readLine());
            List<String> strings = new ArrayList<>();

            for (int i = 0; i < numberOfStrings; ++i)
                strings.add(input.readLine());

            strings.sort(Comparator.comparingInt(String::length)
                    .thenComparing(String::compareToIgnoreCase));

            for (String str : strings)
                System.out.printf("(%d): \"%s\"%n", str.length(), str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * From the N input lines, output those lines whose length is less than the average.
     *
     * + averageLength var at the beggining sum of lengths..
     */
    @Override
    public void task3() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            int totalLength = 0;
            final int numberOfStrings = Integer.parseInt(input.readLine());

            List<String> strings = new ArrayList<>();

            for (int i = 0; i < numberOfStrings; ++i) {
                strings.add(input.readLine());
                totalLength += strings.get(i).length();
            }

            final int averageLength = totalLength / numberOfStrings;

            System.out.printf("AVERAGE (%d)%n", averageLength);
            for (String string : strings) {
                if (string.length() < averageLength) {
                    System.out.printf("(%d): \"%s\"%n", string.length(), string);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enter N words consisting of characters from the English alphabet.
     * Find a word in which the number of different characters is minimal.
     * The upper and lower case letters are considered different.
     * If there are several such words, find the first one.
     *
     * + according to name convension, we don't use upper case for vars and var should be described by logic name
     * + magic numbers..
     * + Why? if (!symbols.contains(symb))
     * + Java 8 Stream API could be used
     */
    @Override
    public void task4() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            final int numberOfWords = Integer.parseInt(input.readLine());
            final String[] allWords = input.readLine().split(" ");
            final List<String> words = new ArrayList<>();

            words.addAll(Arrays.asList(allWords).subList(0, numberOfWords));

            String wordWithMinNumberOfDiffChars = null;

            Set<Character> symbols = new HashSet<>();
            int minSymbols = Integer.MAX_VALUE;

            for (String word : words) {
                symbols.clear();

                symbols.addAll(word.chars().mapToObj(e -> (char)e).collect(Collectors.toList()));

                if (symbols.size() < minSymbols) {
                    wordWithMinNumberOfDiffChars = word;
                    minSymbols = symbols.size();
                }
            }

            System.out.println(wordWithMinNumberOfDiffChars);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enter N words. Find the number of words containing only Latin characters,
     * and among them - the number of words with an equal number of vowels and consonants.
     *
     * + countVowelsInWord = 0 set twice
     * + "y" vowel missed ?
     * + if mixed words?
     * + if upper case?
     */
    @Override
    public void task5() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            final int numberOfWords = Integer.parseInt(input.readLine());
            final String[] allWords = input.readLine().toLowerCase().split(" ");
            final List<String> words = new ArrayList<>();

            int wordsConsistingOfLatinChars = 0;
            int countVowelsInWord;

            words.addAll(Arrays.asList(allWords).subList(0, numberOfWords));

            for (int i = 0; i < numberOfWords; ++i) {
                countVowelsInWord = 0;
                if (words.get(i).matches("[a-zA-Z]+")) {
                    for (int j = 0; j < words.get(i).length(); ++j) {
                        char x = words.get(i).charAt(j);
                        if (x == 'a' || x == 'e' || x == 'i'
                                || x == 'o' || x == 'u' || x == 'y') {
                            countVowelsInWord++;
                        }
                    }

                    if (countVowelsInWord * 2 == words.get(i).length()) {
                        wordsConsistingOfLatinChars++;
                    }
                }
            }

            System.out.println(wordsConsistingOfLatinChars);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Enter N words.
     * Find the word, the characters in which go in the strict order of increasing their codes.
     * If there are several such words, find the first one.
     * Words consisting of a single character do not count.
     *
     * + Character.codePointAt() should be used    (did it differently)
     * + if only one char words input              ([condition!] Words consisting of a single character do not count.)
     * + too mush embedded if
     * + "NOT FOUND" should be declared as constant
     */
    @Override
    public void task6() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            final int numberOfWords = Integer.parseInt(input.readLine());
            final String[] allWords = input.readLine().split(" ");
            final List<String> words = new ArrayList<>();

            String resultWord = null;

            words.addAll(Arrays.asList(allWords).subList(0, numberOfWords));

            for (String word : words) {
                if (word.length() > 1) {
                    String iterWord = Stream.of(word.split(""))
                            .sorted()
                            .collect(Collectors.joining());

                    if (iterWord.equals(word)) {
                        if (null == resultWord) {
                            resultWord = word;
                        }
                    }
                }
            }

            if (null == resultWord) {
                System.out.println(NOT_FOUND);
            } else {
                System.out.println(resultWord);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enter N words.
     * Find words that consist of only different characters.
     * In case the word occurs more than once - withdraw it once.
     *
     * + Java 8 API can be used:
     * .distinct().count();
     *
     * + "NOT FOUND" should be declared as constant
     */
    @Override
    public void task7() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            final int numberOfWords = Integer.parseInt(input.readLine());
            final String[] allWords = input.readLine().split(" ");
            final List<String> words = new ArrayList<>();

            Set<String> wordsFromDifChars = new LinkedHashSet<>();

            words.addAll(Arrays.asList(allWords).subList(0, numberOfWords));

            for (String word : words) {
                long numberOfUniqueCharsInWord = word.chars().distinct().count();

                if (word.length() == numberOfUniqueCharsInWord) {
                    wordsFromDifChars.add(word);
                }
            }

            if (wordsFromDifChars.isEmpty()) {
                System.out.println(NOT_FOUND);
            } else {
                System.out.println(String.join(" ", wordsFromDifChars));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enter N words. In addition to ordinary words, integers can occur in the input sequence.
     * Among them it is necessary to find a number-palindrome (equally readable in both directions).
     * If there are more than one such number, find the second one.
     * There are no restrictions on the size of the number. One digit is a palindrome. Numbers can be large.
     *
     * + "NOT FOUND" should be declared as constant
     */
    @Override
    public void task8() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            final int numberOfWords = Integer.parseInt(input.readLine());
            final String[] allWords = input.readLine().split(" ");
            final List<String> words = new ArrayList<>();

            String wordPalindrome = null;

            words.addAll(Arrays.asList(allWords).subList(0, numberOfWords));

            for (String word : words) {
                if (word.matches("[0-9]+")) {
                    if (word.equals(new StringBuilder(word).reverse().toString())) {
                        wordPalindrome = word;
                    }
                }
            }

            if (null == wordPalindrome) {
                System.out.println(NOT_FOUND);
            } else {
                System.out.println(wordPalindrome);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write a program that prints numbers from 1 to N ^ 2
     * in the form of an NxN matrix from left to right and top to bottom.
     */
    @Override
    public void task9() {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[][] array = new int[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                array[i][j] = i * N + j + 1;
                if (j == array.length - 1) {
                    System.out.print(array[i][j]);
                } else {
                    System.out.print(array[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * The search for the roots of the quadratic equation
     *
     * + bigDecimal in var names and long in this case
     * + code repeat
     */
    @Override
    public void task10() {
        Scanner input = new Scanner(System.in);

        final int a = input.nextInt();
        final int b = input.nextInt();
        final int c = input.nextInt();
        final int d = b * b - 4 * a * c;

        if (d > 0) {
            BigDecimal root1 = findRoot(a, b, d, (byte) 1);
            BigDecimal root2 = findRoot(a, b, d, (byte) 2);
            System.out.println("Two solutions: " + root1 + ", " + root2);
        } else if (d == 0) {
            BigDecimal root = findRoot(a, b, d);;
            System.out.println("One solution: " + root);
        } else {
            System.out.println("No solution");
        }
    }

    /**
     * Calculates the roots of the quadratic equation.
     * @param a coefficient a
     * @param b coefficient b
     * @param d discriminant
     * @param rootNumber number of the root, if there are two
     * @return root of the quadratic equation
     */
    private BigDecimal findRoot(int a, int b, int d, byte ... rootNumber) {
        if (0 == d) {
            return new BigDecimal((-b / 2d / a))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        if (1 == rootNumber[0]) {
            return new BigDecimal(
                    ((-b - sqrt(d)) / 2d / a))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return new BigDecimal(
                    ((-b + sqrt(d)) / 2d / a))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * Enter the number from 1 to 12.
     * Output to the console the name of the month corresponding to the given number.
     * In the implementation, use the switch statement. [!!!]
     * Verify the correctness of the number entry.
     *
     * + Why check incorrect twice? Strange exception approach.
     * + DateFormatSymbols().getMonths()[month-1] or new SimpleDateFormat("MMMM").format(date)
     */
    @Override
    public void task11() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            final int month = Integer.parseInt(reader.readLine());

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
            }

        } catch (Exception e){
        } finally {
            System.out.println("INCORRECT INPUT DATA");
        }
    }

    /**
     * Arrange rows of a matrix of dimension N
     * in the order of increasing values of the elements of the k-th column.
     *
     * + unused vars
     * + private methods after usage
     * + strange method names
     * + commented code
     * + swapTwoRows - explain logic. why not System.arraysCopy?
     */
    @Override
    public void task12() {
        Scanner input = new Scanner(System.in);

        final int columnNumber = input.nextInt();
        final int[][] matrix = readMatrix(input);
        final int dimension = matrix.length;

        for (int i = dimension - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (matrix[j][columnNumber] > matrix[j + 1][columnNumber]) {
                    swapTwoRows(matrix[j], matrix[j + 1]);
                }
            }
        }

        System.out.println(dimension);
        printMatrix(matrix, false);
    }

    /**
     * Perform a cyclic shift of the matrix of dimension N by k positions down.
     *
     * + difficult to read, could be separeted into methods
     *      [ i can't, because the cycles are different ]
     */
    @Override
    public void task13() {
        Scanner input = new Scanner(System.in);

        final int[][] matrix = readMatrix(input);
        final int dimension = matrix.length;

        int numberOfShifts = input.nextInt();

        if ((numberOfShifts != 0)
                || (abs(numberOfShifts) % dimension != 0)) {
            if (abs(numberOfShifts) > dimension) {
                numberOfShifts = numberOfShifts % dimension;
            }
            if (numberOfShifts > 0) {
                for (int i = 0; i < numberOfShifts; ++i) {
                    for (int j = dimension - 1; j > 0; --j) {
                        if (j == dimension - 1) {
                            swapTwoRows(matrix[0], matrix[j]);
                        } else  {
                            swapTwoRows(matrix[j], matrix[j + 1]);
                        }
                    }
                }
            } else {
                for (int i = 0; i < -numberOfShifts; ++i) {
                    for (int j = 0; j < dimension - 1; ++j) {
                        if (j == 0) {
                            swapTwoRows(matrix[dimension - 1], matrix[0]);
                        } else {
                            swapTwoRows(matrix[j], matrix[j - 1]);
                        }
                    }
                }
            }
        }

        System.out.println(dimension);
        printMatrix(matrix, false);
    }

    /**
     * Find the greatest number of strictly increasing elements of a sequence that go successively.
     * A relational operator can be defined on a set that includes more than one element.
     *
     * + upper case var
     * + why short?
     * + magic numbers
     * + elems bad name
     */
    @Override
    public void task14() {
        Scanner input = new Scanner(System.in);

        final int numberOfElements = input.nextShort();

        int rows[] = new int[numberOfElements];
        int elements = 0;
        int maxSeriesOfIncreasingElements = 0;

        for (int i = 0; i < numberOfElements; ++i) {
            rows[i] = input.nextInt();
            if (i != 0) {
                if (rows[i-1] < rows[i]) {
                    elements++;
                    if (elements > maxSeriesOfIncreasingElements) {
                        maxSeriesOfIncreasingElements = elements + 1;
                    }
                } else {
                    elements = 0;
                }
            }
        }

        System.out.println(maxSeriesOfIncreasingElements);
    }

    /**
     * Find the sum of matrix elements located between the first and second positive elements of each row.
     *
     * + matrix[0].length - should be declared as var
     */
    @Override
    public void task15() {
        final int[][] matrix = readMatrix(new Scanner(System.in));
        final int numberOfColumns = matrix[0].length;

        boolean firstPositiveElem;
        int sumPositiveNumbers = 0;
        int possibleRowSum;

        for (int i = 0; i < numberOfColumns; ++i) {
            firstPositiveElem = false;
            possibleRowSum = 0;
            for (int j = 0; j < numberOfColumns; ++j) {
                if ((matrix[i][j] > 0) && (!firstPositiveElem)) {
                    firstPositiveElem = true;
                } else if ((matrix[i][j] <= 0) && firstPositiveElem) {
                    possibleRowSum += matrix[i][j];
                } else if ((matrix[i][j] > 0) && firstPositiveElem) {
                    sumPositiveNumbers += possibleRowSum;
                    break;
                }
            }
        }

        System.out.println(sumPositiveNumbers);
    }

    /**
     * Rotate the matrix 90 degrees counterclockwise.
     *
     * + matrix[0].length - should be declared as var
     */
    @Override
    public void task16() {
        final int[][] matrix = readMatrix(new Scanner(System.in));
        final int numberOfColumns = matrix[0].length;

        int[][] matrixRotated = new int[numberOfColumns][numberOfColumns];

        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfColumns; ++j) {
                matrixRotated[numberOfColumns - i - 1][j] = matrix[j][i];
            }
        }

        System.out.println(matrix.length);
        printMatrix(matrixRotated, false);
    }

    /**
     * Calculate the determinant of the matrix.
     *
     * + commented code
     * + private method calculateDeterminant before call
     * + getDeterminant - calculateDeterminant?
     * + newMatrix - should be renamed
     * + matrix[0].length - should be declared as var
     */
    @Override
    public void task17() {
        final int[][] matrix = readMatrix(new Scanner(System.in));
        System.out.println(calculateDeterminant(matrix));
    }

    /** A simple method allows recursively to calculate the determinant of the matrix.
     *
     * + matrix[0].length - should be declared as var
     *
     * @param matrix Square matrix
     * @return determinant of the matrix
     */
    private int calculateDeterminant(int[][] matrix) {
        final int numberOfColumns = matrix[0].length;
        int[][] resultMatrix = new int[numberOfColumns - 1][numberOfColumns - 1];

        if (numberOfColumns == 1) {
            return matrix[0][0];
        }

        if (numberOfColumns == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int determinant = 0;

        // pass through the zero-row columns
        for (int i = 0; i < numberOfColumns; ++i) {
            for (int j = 1; j < numberOfColumns; ++j) {
                for (int k = 0; k < numberOfColumns - 1; ++k) {
                    if (k >= i) {
                        resultMatrix[j - 1][k] = matrix[j][k + 1];
                    } else {
                        resultMatrix[j - 1][k] = matrix[j][k];
                    }
                }
            }

            if (matrix[0][i] != 0) {
                if (((i + 1) & 1) != 0) {
                    determinant += matrix[0][i] * calculateDeterminant(resultMatrix);
                } else {
                    determinant -= matrix[0][i] * calculateDeterminant(resultMatrix);
                }
            }
        }
        return determinant;
    }

    /**
     * Find the maximum element (or elements) in the matrix
     * and remove from the matrix all the rows and columns containing it.
     *
     * + List interface should be used for ArrayList.
     * + <Integer> ArrayList type - excess
     * + Set looks more effective?
     * + findExtremum - default type param should be constant
     * + aMatrix - bad name
     *          [ replaced by "row" ]
     *          [ By the way, this name (aMatrix) is set by the IDEA itself,
     *              when it recommends switching from the usual FOR to FOREACH ]
     */
    @Override
    public void task18() {
        final int[][] matrix = readMatrix(new Scanner(System.in));
        final int dimension = matrix[0].length;

        Set<Integer> rowMaxValue = new LinkedHashSet<>();
        Set<Integer> colMaxValue = new LinkedHashSet<>();

        final int maxValue = findExtremum(matrix, MAX);

        // Find indices of rows and columns witch contains maxValue
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                if (matrix[i][j] == maxValue) {
                    rowMaxValue.add(i);
                    colMaxValue.add(j);
                }
            }
        }

        printMatrix(compactTheMatrix(matrix, rowMaxValue, colMaxValue), true);
    }

    /**
     * Seal the matrix, removing the rows and columns filled with zeros from it.
     *
     * + List interface should be used for ArrayList.
     * + <Integer> ArrayList type - excess
     * + matrix[0].length - should be declared as var
     */
    @Override
    public void task19() {
        final int[][] matrix = readMatrix(new Scanner(System.in));
        final int numberOfColumns = matrix[0].length;

        List<Integer> rowContainsZero = new ArrayList<>();
        List<Integer> colContainsZero = new ArrayList<>();

        // Find indices of rows witch contains Zero
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                if (matrix[i][j] != 0) {
                    break;
                } else if (j == numberOfColumns - 1) {
                    rowContainsZero.add(i);
                }
            }
        }

        // Find indices of columns witch contains Zero
        for (int j = 0; j < numberOfColumns; ++j) {
            for (int i = 0; i < matrix.length; ++i) {
                if (matrix[i][j] != 0) {
                    break;
                } else if (i == numberOfColumns - 1) {
                    colContainsZero.add(j);
                }
            }
        }

        printMatrix(compactTheMatrix(matrix, rowContainsZero, colContainsZero), true);
    }

    /**
     * In the matrix, find the minimum element
     * and move it to the place of the specified element by rearranging rows and columns.
     * It is guaranteed that the minimum element in the matrix occurs exactly once.
     *
     * + findExtremum - default type param should be constant
     * + swap logic
     * + may be swap could be extracted into method
     */
    @Override
    public void task20() {
        Scanner input = new Scanner(System.in);

        final int resultElemRowIndex = input.nextInt();
        final int resultElemColIndex = input.nextInt();
        final int[][] matrix = readMatrix(input);
        final int numberOfRows = matrix.length;
        final int numberOfColumns = matrix[0].length;

        int minValueRowIndex = 0;
        int minValueColIndex = 0;

        int minValue = findExtremum(matrix, MIN);

        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                if (matrix[i][j] == minValue) {
                    minValueRowIndex = i;
                    minValueColIndex = j;
                }
            }
        }

        if (minValueColIndex != resultElemColIndex) {
            // swap all needed elems by columns
            for (int i = 0; i < numberOfRows; ++i) {
                matrix[i][resultElemColIndex] = getItself(matrix[i][minValueColIndex],
                        matrix[i][minValueColIndex] = matrix[i][resultElemColIndex]);
            }
        }

        if (minValueRowIndex != resultElemRowIndex) {
            // swap all needed elems by rows
            for (int j = 0; j < numberOfColumns; ++j) {
                matrix[resultElemRowIndex][j] = getItself(matrix[minValueRowIndex][j],
                        matrix[minValueRowIndex][j] = matrix[resultElemRowIndex][j]);
            }
        }

        System.out.println(numberOfRows);
        printMatrix(matrix, false);
    }

    /**
     * The method allows you to compact the matrix by deleting the specified row and column numbers.
     *
     * @param originalMatrix The matrix, which is to be sealed
     * @param limitationRow The row numbers to remove from the "originalMatrix"
     * @param limitationCol The column numbers to remove from the "originalMatrix"
     * @return compact matrix
     */
    private int[][] compactTheMatrix(
            int[][] originalMatrix,
            Collection<Integer> limitationRow,
            Collection<Integer> limitationCol) {

        final int numberOfRows = originalMatrix.length;
        final int numberOfColumns = originalMatrix[0].length;

        // Create new matrix without rows and columns witch contains Zero
        int[][] resultMatrix = new int[numberOfRows - limitationRow.size()]
                [numberOfColumns - limitationCol.size()];

        int rowShift = 0;
        int colShift;

        for (int i = 0; i < numberOfRows; ++i) {
            if (limitationRow.contains(i)) {
                rowShift++;
            } else {
                colShift = 0;
                for (int j = 0; j < numberOfColumns; ++j) {
                    if (limitationCol.contains(j)) {
                        colShift++;
                    } else {
                        resultMatrix[i - rowShift][j - colShift] = originalMatrix[i][j];
                    }
                }
            }
        }
        return resultMatrix;
    }

    /**
     * Find elem with extremum value in original matrix.
     *
     * @param matrix original matrix
     * @param type "max" or "min"
     * @return extremumValue
     */
    private int findExtremum(int[][] matrix, String type) {
        int extremumValue = matrix[0][0];

        for (int[] row : matrix) {
            for (int j = 0; j < matrix[0].length; ++j) {
                switch (type) {
                    case MAX:
                        if (row[j] > extremumValue) {
                            extremumValue = row[j];
                        }
                        break;
                    case MIN:
                        if (row[j] < extremumValue) {
                            extremumValue = row[j];
                        }
                        break;
                    default:
                        return 0;
                }
            }
        }
        return extremumValue;
    }

    /**
     * Convert the rows of the matrix in such a way that the elements equal to zero are located after all the others.
     *
     * + swap logic
     */
    @Override
    public void task21() {
        final int[][] matrix = readMatrix(new Scanner(System.in));
        final int dimension = matrix.length;

        for (int i = 0; i < dimension; ++i) {
            for (int k = 0, j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    if (k < j) {
                        matrix[i][k] = getItself(matrix[i][j],
                                matrix[i][j] = matrix[i][k]);
                    }
                    k++;
                }
            }
        }

        System.out.println(dimension);
        printMatrix(matrix, false);
    }

    /**
     * Round all the elements of the matrix to an integer.
     * Use rounding to the nearest integer - the number is rounded to the nearest integer using Math.round ();
     *
     * + readMatrixD - bad method name
     * + static import Math
     */
    @Override
    public void task22() {
        final double[][] matrix = readMatrixDouble(new Scanner(System.in));
        final int numberOfRows = matrix.length;
        final int numberOfColumns = matrix[0].length;

        int matrixRound[][] = new int[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                matrixRound[i][j] = (int) round(matrix[i][j]);
            }
        }

        System.out.println(numberOfRows);
        printMatrix(matrixRound, false);
    }

    /**
     * Find the number of all saddle points of the matrix.
     * The matrix A has a saddle point (i, j) if A [i, j] is the minimal element
     * in the i-th row and the maximal in the j-th column.
     *
     * + spaghetti code should be avoided in most of cases
     */
    @Override
    public void task23() {

        final int[][] matrix = readMatrix(new Scanner(System.in));
        final int numberOfRows = matrix.length;
        final int numberOfColumns = matrix[0].length;

        int numberOfSaddlePoints = 0;

        for (int i = 0; i < numberOfRows; ++i){
            for (int j = 0; j < numberOfColumns; ++j){
                if (!isSaddlePoint(matrix, i, j)) {
                    break;
                }
                ++numberOfSaddlePoints;
            }
        }

        System.out.println(numberOfSaddlePoints);

    }

    /**
     * Check that the point is a saddle point.
     *
     * @param matrix original matrix
     * @param row which line contains the item
     * @param column which column contains the item
     * @return true or false
     */
    private boolean isSaddlePoint(int[][] matrix, int row, int column) {
        int point = matrix[row][column];
        for (int k = 0; k < matrix.length; ++k){
            if (!(point <= matrix[row][k] && point >= matrix[k][column]))
                return false;
        }
        return true;
    }

    /**
     * Rebuild the matrix, rearranging the rows in it
     * so that the sum of the elements in the rows of the resulting matrix increases.
     *
     * + sumPre, sumPost - could have better names
     * + comment should be more formal :)
     */
    @Override
    public void task24() {
        final int[][] matrix = readMatrix(new Scanner(System.in));
        final int numberOfRows = matrix.length;
        final int numberOfColumns = matrix[0].length;

        for (int i = numberOfRows - 1; i > 0; --i) {
            for (int k = 0; k < i; ++k) {
                int sumElementsOfCurrentRow = 0;
                int sumElementsOfNextRow = 0;

                for (int j = 0; j < numberOfColumns; ++j) {
                    sumElementsOfCurrentRow += matrix[k][j];
                    sumElementsOfNextRow += matrix[k + 1][j];
                }

                if (sumElementsOfNextRow < sumElementsOfCurrentRow) {
                    swapTwoRows(matrix[k], matrix[k + 1]);
                }
            }
        }

        System.out.println(numberOfRows);
        printMatrix(matrix, false);
    }

    /**
     * Find the number of local minima.
     * Neighbors of the matrix element are elements that have a common side or angle with it.
     * An element of a matrix is called a local minimum if it is strictly less than all its neighbors.
     *
     * + Too long method
     * + Code should be refactored
     */
    @Override
    public void task25() {
        Scanner input = new Scanner(System.in);

        final int[][] matrix = readMatrix(input);
        final int dimension = matrix.length;

        int localMins = 0;

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                if (isLocal(matrix, i, j, MIN)) {
                    ++localMins;
                }
            }
        }

        System.out.println(localMins);
    }

    /**
     * Find the largest among the local maxima.
     * Neighbors of the matrix element are elements that have a common side or angle with it.
     * An element of a matrix is called a local maximum if it is strictly greater than all its neighbors.
     *
     * - think about max optimization
     * + "NOT FOUND" should be declared as constant
     */
    @Override
    public void task26() {
        Scanner input = new Scanner(System.in);

        final int[][] matrix = readMatrix(input);
        final int dimension = matrix.length;

        List<Integer> localMaxes = new ArrayList<>();
        int maxLocalMax;

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                if (isLocal(matrix, i, j, MAX)) {
                    localMaxes.add(matrix[i][j]);
                }
            }
        }

        maxLocalMax = Collections.max(localMaxes);

        if (0 == maxLocalMax) {
            System.out.println(NOT_FOUND);
        } else {
            System.out.println(maxLocalMax);
        }
    }

    /**
     * Checking whether this element is a local maximum or minimum of the matrix.
     *
     * @param matrix source matrix
     * @param x index of cols
     * @param y index of rows
     * @param type type of extremum
     * @return true if elem is local maximum/minimum, false else
     */
    private static boolean isLocal(int[][] matrix, int x, int y, String type) {

        for (int i = y - 1; i <= y + 1; ++i) {
            for (int j = x - 1; j <= x + 1; ++j) {
                if ((i >= 0) && (j >= 0)
                        && (i < matrix.length) && (j < matrix.length)
                        && ((j != x) || (i != y))) {

                    if (Objects.equals(MAX, type)) {
                        if (matrix[i][j] >= matrix[y][x]) {
                            return false;
                        }
                    } else {
                        if (matrix[i][j] <= matrix[y][x]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Rebuild the given matrix by rearranging the columns in it
     * so that the values of their characteristics decrease.
     * The characteristic of a column of a rectangular matrix is the sum of the modules of its elements.
     * If the characteristic values are the same,
     * the columns should follow the same order as in the original matrix.
     *
     * - var names could be better
     */
    @Override
    public void task27() {
        final int[][] matrix = readMatrix(new Scanner(System.in));
        final int dimension = matrix.length;

        for (int i = matrix[0].length - 1; i > 0; --i) {
            for (int k = 0; k < i; ++k) {
                int sumElementsOfCurrentRow = 0;
                int sumElementsOfNextRow = 0;

                for (int[] row : matrix) {
                    sumElementsOfCurrentRow += abs(row[k]);
                    sumElementsOfNextRow += abs(row[k + 1]);
                }
                if (sumElementsOfNextRow > sumElementsOfCurrentRow) {
                    for (int j2 = 0; j2 < dimension; ++j2) {
                        matrix[j2][k] = getItself(matrix[j2][k + 1],
                                matrix[j2][k + 1] = matrix[j2][k]);
                    }
                }
            }
        }

        System.out.println(dimension);
        printMatrix(matrix, false);
    }

    /**
     * Swap elements of two different rows.
     *
     * @param row1 first row
     * @param row2 second row
     */
    private void swapTwoRows(int[] row1, int[] row2) {
        for (int i = 0; i < row1.length; ++i) {
            row1[i] = getItself(row2[i], row2[i] = row1[i]);
        }
    }

    /**
     * Return itself
     *
     * @param itself element
     * @param dummy just dummy
     * @return itself
     */
    private int getItself(int itself, int dummy) {
        return itself;
    }

    /**
     * A simple method that allows you to read the matrix from the console.
     *
     * @param scanner Scanner class
     * @return square matrix
     */
    private int[][] readMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        int[][] matrix = new int[DIMENSION][DIMENSION];

        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextInt();
            }
        }

        return matrix;
    }

    /**
     * A simple method that allows you to read the double matrix from the console.
     *
     * @param scanner Scanner class
     * @return square double matrix
     */
    private double[][] readMatrixDouble(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        double[][] matrix = new double[DIMENSION][DIMENSION];

        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextDouble();
            }
        }

        return matrix;
    }

    /**
     * A simple method that allows you to display the matrix on the console.
     *
     * @param matrix Square matrix
     */
    private void printMatrix(int matrix[][], boolean printDimension) {
        if (printDimension) {
            System.out.println(matrix.length);
            System.out.println(matrix[0].length);
        }

        for (int[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; ++j) {
                System.out.print(aMatrix[j] + " ");
            }
            System.out.println();
        }
    }

}
