package com.epam.courses.jf.practice.akriukov.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class Solver implements ISolver{
    

    @Override
    public void task1() {
            int numberOfLines = Integer.parseInt(readLineFromConsole());

            int minLength = Integer.MAX_VALUE;
            int maxLength = Integer.MIN_VALUE;
            String minString = "";
            String maxString = "";

            for (int i = 0; i < numberOfLines; i++) {
                String s = readLineFromConsole();
                if (minLength >= s.length()) {
                    minLength = s.length();
                    minString = s;
                }
                if (maxLength <= s.length()) {
                    maxLength = s.length();
                    maxString = s;
                }

            }
            System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
            System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }

    @Override
    public void task2() {
        String[] lines = readLinesToStringArray();
        Arrays.sort(lines, new compByLength());
        for (String s: lines) {
            System.out.printf("(%d): \"%s\"%n", s.length(), s);
        }
    }

    @Override
    public void task3() {
        int averageLength =0;
        String[] lines = readLinesToStringArray();

        for (String string: lines) {
            averageLength += string.length();
        }
        averageLength /= lines.length;
        System.out.printf("AVERAGE (%d)%n", averageLength);

        for (String string: lines) {
            if (averageLength > string.length()) {
                System.out.printf("(%d): \"%s\"%n", string.length(), string);
            }
        }
    }

    @Override
    public void task4() {
        int numberOfWords = Integer.parseInt(readLineFromConsole());
        int minNumberOfLetters = Integer.MAX_VALUE;
        String minNumberOfLetterString = "";
        String[] strings = readLineFromConsole().split(" ");
        for (String s : strings) {
            Set<Character> word = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                word.add(s.charAt(i));
            }
            if (minNumberOfLetters > word.size()) {
                minNumberOfLetters = word.size();
                minNumberOfLetterString = s;
            }
        }
        System.out.println(minNumberOfLetterString);
    }

    @Override
    public void task5() {
        int numberOfWords = Integer.parseInt(readLineFromConsole());
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        String[] strings = readLineFromConsole().split(" ");
        int wordsWithEqualVowelsNConsonants = 0;

        for (String str : strings) {
            int vowelCountInWord = 0;
            boolean latinFlag = true; //false if nonLatin letter detected
            for (int i = 0; i < str.length(); i++) {
                char currentSymbol = Character.toLowerCase(str.charAt(i)); //using lowercase to minimize check
                int charIntValue = (int) currentSymbol;
                if ((charIntValue > 96) && //character must be latin
                        (charIntValue < 123)) {
                    if (vowels.contains(currentSymbol)) {
                        vowelCountInWord++;
                    }
                } else {
                    latinFlag = false; //nonLatin letter detected
                    break;
                }
            }
            //pass with no actions if nonLatin letter detected
            if (((str.length() - vowelCountInWord) == vowelCountInWord) &&
                    latinFlag){
                wordsWithEqualVowelsNConsonants++;
            }
        }
        System.out.println(wordsWithEqualVowelsNConsonants);
    }

    @Override
    public void task6() {
        int numberOfWords = Integer.parseInt(readLineFromConsole());
        String[] strings = readLineFromConsole().split(" ");
        for (String str : strings) {
            int charIntValueToCompare = 0;
            boolean wordFound = false;
            if (str.length() < 2) {
                continue;
            }
            for (int i = 0; i < str.length(); i++) {
                char currentSymbol = str.charAt(i);
                int charIntValue = (int) currentSymbol;
                if (charIntValue > charIntValueToCompare) { //use >= for same neighboring letters
                    charIntValueToCompare = charIntValue;
                    if (i == (str.length() - 1)) {
                        wordFound = true;
                    }
                } else {
                    break;
                }
            }
            if (wordFound) {
                System.out.println(str);
                return;
            }
        }
        System.out.println("NOT FOUND");
    }

    @Override
    public void task7() {
        int numberOfWords = Integer.parseInt(readLineFromConsole());
        String[] strings = readLineFromConsole().split(" ");
        Set<String> printedWords = new HashSet<>();
        for (String str : strings) {
            if (printedWords.contains(str)) {
                continue;
            } else {
                Set<Character> word = new HashSet<>();
                for (int i = 0; i < str.length(); i++) {
                    word.add(str.charAt(i)); //if non-register sensitive algorithm is needed Use Character.toLowerCase(str.charAt(i))
                }
                if (str.length() == word.size()) {
                    System.out.print(str + " "); //" " in the end of line may be a problem
                    printedWords.add(str);
                }
            }
        }
        if (printedWords.size() == 0) {
            System.out.println("NOT FOUND");
        }
    }

    @Override
    public void task8() {
        int numberOfWords = Integer.parseInt(readLineFromConsole());
        HashSet<Character> numbers = new HashSet<>();
        numbers.add('0');
        numbers.add('1');
        numbers.add('2');
        numbers.add('3');
        numbers.add('4');
        numbers.add('5');
        numbers.add('6');
        numbers.add('7');
        numbers.add('8');
        numbers.add('9');
        String[] strings = readLineFromConsole().split(" ");
        String[] palindromes = new String[2];
        int palindromeNumbersFound = 0;

        for (String str : strings) {
            int numberCountInWord = 0;
            for (int i = 0; i < str.length(); i++) {
                char currentSymbol = str.charAt(i);
                if (numbers.contains(currentSymbol)) {
                    numberCountInWord++;
                } else {
                    break;
                }
            }
            if ((str.length() - numberCountInWord) == 0) { //next actions only if no letters in word (it is number)
                if (str.equals(new StringBuilder(str).reverse().toString())) { //check for palindrome
                    palindromes[palindromeNumbersFound] = str;
                    palindromeNumbersFound++;
//                    System.out.println(str);
                }
            }
            if (palindromeNumbersFound == 2) {
                break;
            }
        }
        switch (palindromeNumbersFound) {
            case 0: System.out.println("NOT FOUND");
                break;
            case 1: System.out.println(palindromes[0]);
                break;
            case 2: System.out.println(palindromes[1]);
                break;
        }
    }

    @Override
    public void task9() {
        int matrixDim = Integer.parseInt(readLineFromConsole());
        int counter = 1;
        for (int i = 0; i < matrixDim; i++) {
            for (int j = 0; j <matrixDim; j++) {
                System.out.print(counter + "\t");
                counter++;
            }
            System.out.println();
        }
    }

    /*@Override
    public void task10() {
        //TODO: decide use double or BigDecimal
        String[] coefficients = readLineFromConsole().split(" ");
        BigDecimal a = new BigDecimal(coefficients[0]);
        BigDecimal b = new BigDecimal(coefficients[1]);
        BigDecimal c = new BigDecimal(coefficients[2]);
        BigDecimal d = b.multiply(b).add(a.multiply(c).multiply(new BigDecimal("4")).negate()); //d = b * b - 4 * a * c
        BigDecimal x1 = d.sqrt(MathContext.DECIMAL64);
    }*/

    @Override
    public void task11() {
        try {
            int monthNumber = Integer.parseInt(readLineFromConsole());

            /*if (monthNumber < 1 || monthNumber > 12) { //easy mode
                System.out.println("INCORRECT INPUT DATA");
            } else {
                System.out.println(java.time.Month.of(monthNumber));
            }*/

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
                    System.out.println("INCORRECT INPUT DATA");
            }
        } catch (Exception e) {
            System.out.println("INCORRECT INPUT DATA");
        }

    }

    @Override
    public void task12() {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt(); //column to sort
        int[][] matrix = matrixInput();

        Arrays.sort(matrix, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[k] - o2[k];
            }
        });

        matrixOutput(matrix);
    }

    @Override
    public void task13() {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt(); //move position
        int[][] inMatrix = matrixInput();
        int[][] outMatrix = moveRows(inMatrix, k);
        matrixOutput(outMatrix);

    }

    @Override
    public void task14() {
        Scanner scanner = new Scanner(System.in);
        int elementsCount = scanner.nextInt();
        int previous = Integer.MAX_VALUE;
        int currentLength = 1;
        int maxLength = 1;
        for (int i = 0; i < elementsCount; i++) {
            int current = scanner.nextInt();
            if (current > previous) {
                currentLength++;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
            } else {
                currentLength = 1;
            }
            previous = current;
        }
        System.out.println(maxLength);

    }

    /* TODO: do later
    @Override
    public void task15() {

    }*/

    @Override
    public void task16() {
        int[][] inMatrix = matrixInput();
        int dim = inMatrix.length;
        int[][] outMatrix = new int[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                outMatrix[i][j] = inMatrix[j][dim - i - 1];
            }
        }
        matrixOutput(outMatrix);
    }

    @Override
    public void task17() {
        int[][] matrix = matrixInput();
        int dim = matrix.length;
        int res = determinant(dim, matrix);
        System.out.println(res);
    }

    public static int determinant(int dim, int[][] matrix) {
        int sum = 0;
        if (dim == 1) {
            return matrix[0][0];
        } else { //is else needed when using return?
            if (dim == 2) {
                return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            } else {
                for (int i = 0; i < dim; i++) {
                    sum += (int) Math.pow(-1, i) * matrix[0][i] * determinant(dim - 1, getMinor(matrix, i));
                }
            }
        }
        return sum;
    }

    public static int[][] getMinor(int[][] matrix, int column) {
        int minorLength = matrix.length - 1;
        int[][] minor = new int[minorLength][minorLength];
        for (int i = 1; i <= minorLength; i++) {
            int m = 0;
            for (int j = 0; j <= minorLength; j++) {
                if (j == column) {
                    m = 1;
                } else {
                    minor[i - 1][j - m] = matrix[i][j];
                }
            }
        }
        return minor;
    }

    @Override
    public void task18() {
        int inMatrix[][] = matrixInput();
        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < inMatrix.length; i++) { //search max element in matrix
            for (int j = 0; j < inMatrix.length; j++) {
                if (maxElement < inMatrix[i][j]) {
                    maxElement = inMatrix[i][j];
                }
            }
        }
        Set<Integer> linesToDelete = new HashSet<>();
        Set<Integer> columnsToDelete = new HashSet<>();
        for (int i = 0; i < inMatrix.length; i++) { //search rows and columns with max element
            for (int j = 0; j < inMatrix.length; j++) {
                if (maxElement == inMatrix[i][j]) {
                    linesToDelete.add(i);
                    columnsToDelete.add(j);
                }
            }
        }
        int outMatrix[][] = new int[inMatrix.length - linesToDelete.size()][inMatrix[0].length - columnsToDelete.size()];
        int outMatrixRows = 0;
        int outMatrixColumns = 0;
        for (int i = 0; i < inMatrix.length; i++) { //copy non-deleted elements to new matrix
            if (linesToDelete.contains(i)) {
                continue;
            }
            outMatrixColumns = 0;
            for (int j = 0; j < inMatrix.length; j++) {
                if (columnsToDelete.contains(j)) {
                    continue;
                }
                outMatrix[outMatrixRows][outMatrixColumns] = inMatrix[i][j];
                outMatrixColumns++;
            }
            outMatrixRows++;
        }
        System.out.println(outMatrixRows);
        System.out.println(outMatrixColumns);
        matrixOutputNonSquare(outMatrix);
    }

    @Override
    public void task19() {
        int inMatrix[][] = matrixInput();
        Set<Integer> linesToDelete = new HashSet<>();
        for (int i = 0; i < inMatrix.length; i++) { //search zero rows
            boolean zeroRow = true;
            for (int j = 0; j < inMatrix.length; j++) {
                if (inMatrix[i][j] != 0) {
                    zeroRow = false;
                    break;
                }
            }
            if (zeroRow) {
                linesToDelete.add(i);
            }
        }
        Set<Integer> columnsToDelete = new HashSet<>();
        for (int j = 0; j < inMatrix.length; j++) { //search zero columns
            boolean zeroColumn = true;
            for (int i = 0; i < inMatrix.length; i++) {
                if (inMatrix[i][j] != 0) {
                    zeroColumn = false;
                    break;
                }
            }
            if (zeroColumn) {
                columnsToDelete.add(j);
            }
        }

        int outMatrix[][] = new int[inMatrix.length - linesToDelete.size()][inMatrix[0].length - columnsToDelete.size()];
        int outMatrixRows = 0;
        int outMatrixColumns = 0;
        for (int i = 0; i < inMatrix.length; i++) { //copy non-deleted elements to new matrix
            if (linesToDelete.contains(i)) {
                continue;
            }
            outMatrixColumns = 0;
            for (int j = 0; j < inMatrix.length; j++) {
                if (columnsToDelete.contains(j)) {
                    continue;
                }
                outMatrix[outMatrixRows][outMatrixColumns] = inMatrix[i][j];
                outMatrixColumns++;
            }
            outMatrixRows++;
        }

        System.out.println(outMatrixRows);
        System.out.println(outMatrixColumns);
        matrixOutputNonSquare(outMatrix);
    }

    @Override
    public void task20() {
        int moveRowPosition = Integer.parseInt(readLineFromConsole()); //throws exception if copy-paste input data in console, but with typing works good
        int moveColumnPosition = Integer.parseInt(readLineFromConsole()); //use scanner for copy-paste
        int matrix[][] = matrixInput();
        int minElement = Integer.MAX_VALUE;
        int dim = matrix.length;
        int minElementRowPosition = 0;
        int minElementColumnPosition = 0;

        for (int i = 0; i < dim; i++) { //search min element in matrix
            for (int j = 0; j < dim; j++) {
                if (minElement > matrix[i][j]) {
                    minElement = matrix[i][j];
                    minElementRowPosition = i;
                    minElementColumnPosition = j;
                }
            }
        }

        for (int j = 0; j < dim; j++) { //moving rows
            int[] buffer = new int[dim];
            buffer[j] = matrix[moveRowPosition][j];
            matrix[moveRowPosition][j] = matrix[minElementRowPosition][j];
            matrix[minElementRowPosition][j] = buffer[j];
        }

        for (int i = 0; i < dim; i++) { //moving columns
            int[] buffer = new int[dim];
            buffer[i] = matrix[i][moveColumnPosition];
            matrix[i][moveColumnPosition] = matrix[i][minElementColumnPosition];
            matrix[i][minElementColumnPosition] = buffer[i];
        }

        matrixOutput(matrix);
    }

    @Override
    public void task21() {
        int[][] matrix = matrixInput();
        int dim = matrix.length;

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim - 1; j++) {
                if (matrix[i][j] == 0) {
                    if (matrix[i][j + 1] == 0) {
                        for (int k = j + 2; k < dim; k++) {
                            if (matrix[i][k] != 0) {
                                matrix[i][j] = matrix[i][k];
                                matrix[i][k] = 0;
                            }
                        }
                    } else {
                        matrix[i][j] = matrix[i][j + 1];
                        matrix[i][j + 1] = 0;
                    }
                }
            }
        }

        matrixOutput(matrix);
    }

    @Override
    public void task22() {
        Scanner scanner = new Scanner(System.in);
        int matrixDim = scanner.nextInt(); //matrix dimension

        int[][] matrix = new int[matrixDim][matrixDim];
        for (int row = 0; row < matrixDim; ++row) {
            for (int col = 0; col < matrixDim; ++col) {
                matrix[row][col] = (int) Math.round(scanner.nextDouble());
            }
        }

        matrixOutput(matrix);
    }

    /**
     * Custom comparator for task2()
     * Firstly compares string length. If length is the same, then compare by symbol codes
     */
    static class compByLength implements Comparator<String> {
        public int compare(String o1, String o2) {
            if (o1.length() != o2.length()) {
                return Integer.compare(o1.length(), o2.length()); //compare length
            } else {
                return String.CASE_INSENSITIVE_ORDER.compare(o1, o2); //compare symbols
            }
        }
    }

    /**
     * Reads lines from console
     * Input number of lines first
     * Then input lines
     * @return String[]
     */
    public static String[] readLinesToStringArray() {
            int numberOfLines = Integer.parseInt(readLineFromConsole());
            String[] lines = new String[numberOfLines];

            for (int i = 0; i < numberOfLines; i++) {
                lines[i] = readLineFromConsole();
            }
            return lines;
    }

    /**
     * Reads one line from console
     * @return entered in console line or null
     */
    public static String readLineFromConsole() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads square matrix from console
     * @return matrix
     */
    public static int[][] matrixInput() {
        Scanner scanner = new Scanner(System.in);
        int matrixDim = scanner.nextInt(); //matrix dimension

        int[][] matrix = new int[matrixDim][matrixDim];
        for (int row = 0; row < matrixDim; ++row) {
            for (int col = 0; col < matrixDim; ++col) {
                matrix[row][col] = scanner.nextInt();
            }
        }
        return matrix;
    }

    /**
     * Prints square matrix to console
     * @param matrix
     */
    public static void matrixOutput(int[][] matrix) {
        int matrixDim = matrix.length;
        System.out.println(matrixDim);
        for (int i = 0; i < matrixDim; i++) {
            for (int j = 0; j < matrixDim; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Prints matrix to console
     * @param matrix
     */
    public static void matrixOutputNonSquare(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Cyclic move rows in matrix on movePosition times
     * If movePosition > 0 then mowing down
     * If movePosition < 0 then mowing up
     * If movePosition == 0 then matrix stand still
     * @param inMatrix
     * @param movePosition
     * @return matrix with moved rows
     */
    public static int[][] moveRows(int[][] inMatrix, int movePosition) {
        int dim = inMatrix.length;
        int[][] outMatrix = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (movePosition >= 0) {
                    outMatrix[(i + movePosition) % dim][j] = inMatrix[i][j]; //move rows down
                } else {
                    outMatrix[i][j] = inMatrix[(i - movePosition) % dim][j]; //move rows up
                }
            }
        }
        return outMatrix;
    }

    /**
     * Cyclic move columns in matrix on movePosition times
     * If movePosition > 0 then mowing right
     * If movePosition < 0 then mowing left
     * If movePosition == 0 then matrix stand still
     * @param inMatrix
     * @param movePosition
     * @return matrix with moved rows
     */
    public static int[][] moveColumns(int[][] inMatrix, int movePosition) {
        int dim = inMatrix.length;
        int[][] outMatrix = new int[dim][dim];
        for (int j = 0; j < dim; j++) {
            for (int i = 0; i < dim; i++) {
                if (movePosition >= 0) {
                    outMatrix[i][(j + movePosition) % dim] = inMatrix[i][j]; //move rows right
                } else {
                    outMatrix[i][j] = inMatrix[i][(j - movePosition) % dim]; //move rows left
                }
            }
        }
        return outMatrix;
    }

}
