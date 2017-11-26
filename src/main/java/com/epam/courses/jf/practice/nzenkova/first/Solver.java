package com.epam.courses.jf.practice.nzenkova.first;

import com.epam.courses.jf.practice.common.first.ISolver;
import com.epam.courses.jf.practice.common.second.ITaskStorage;

import java.math.BigDecimal;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Solver implements ISolver {
    private static final String NOT_FOUND = "NOT FOUND";
    /**
     * Searching the smallest and the biggest row.
     * Input: a number of strings N and N strings.
     * Output: the rows found and their length.
     * If the rows fully satisfying the conditions are more than one, output the last row.
     */
    public void task1() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());//a number of lines
        int maxLength, minLength;
        String maxString, minString;
        String str = scanner.nextLine();
        maxLength = str.length();
        minLength = str.length();
        maxString = str;
        minString = str;
        for (int i = 0; i < number - 1; ++i) {
            str = scanner.nextLine();
            if (str.length() >= maxLength) {
                maxLength = str.length();
                maxString = str;
            }
            if (str.length() <= minLength) {
                minLength = str.length();
                minString = str;
            }
        }
        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }

    /**
     * Sort strings in ascending order of their length.
     * Input: a number of strings N and N strings.
     * Output: sorted rows and their lengths.
     * If the lengths of the strings are the same, sort strings in lexicographical order.
     */
    public void task2() {
        Scanner in = new Scanner(System.in);
        int number = Integer.valueOf(in.nextLine());
        String[] strings = new String[number];
        for (int i = 0; i < number; ++i) {
            strings[i] = in.nextLine();
        }
        Arrays.sort(strings, (o1, o2) -> o1.length() > o2.length() ? 1 : o1.length() < o2.length() ? -1 : o1.compareTo(o2));
        for (String str : strings) {
            System.out.printf("(%d): \"%s\"%n", str.length(), str);
        }
    }

    /**
     * Output strings which length < average length of all strings.
     * Average length is the arithmetic mean of the lengths of all rows, rounded down to the nearest whole number.
     * Input: a number of strings N and N strings.
     * Output: AVERAGE -- average mean, the lengths of strings and the strings.
     * The order of output and input strings is the same.
     */

    public void task3() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        String[] strings = new String[number];
        int sumLength = 0;
        for (int i = 0; i < number; ++i) {
            strings[i] = scanner.nextLine();
            sumLength += strings[i].length();
        }
        int averageLength = sumLength / number;

        System.out.printf("AVERAGE (%d)%n", averageLength);
        for (String str : strings) {
            if (str.length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", str.length(), str);
            }
        }
    }

    /**
     * Find a word, which contains the minimum number of different characters.
     * Consider the uppercase and lowercase letters are different.
     * Input: a number of words N and the string of N words consisting of English letters.
     * Output: a word satisfying conditions.
     * If there are several appropriate words, it finds the first one.
     */
    public void task4() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());
        String result = "";
        int minUniqueElements;
        String word = scanner.next();
        int countUniqueElements = (int) word.chars().distinct().count();
        minUniqueElements = countUniqueElements;
        result = word;
        for (int i = 0; i < number - 1; ++i) {
            word = scanner.next();
            countUniqueElements = (int) word.chars().distinct().count();
            if (countUniqueElements < minUniqueElements) {
                minUniqueElements = countUniqueElements;
                result = word;
            }
        }
        System.out.printf("%s%n", result);
    }

    /**
     * Find amount of words which contain only Latin characters.
     * Then find amount of words with an equal number of vowels and consonants among the first group.
     * Input: a number of words N and the string of N words separated by spaces.
     * Output: a number of such words.
     */
    public void task5() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());
        int counter = 0;
        for (int i = 0; i < number; ++i) {
            String word = scanner.next();
            final String latinAlphabet = "^[a-zA-Z]+";
            final String vowels = "[AEIOUYaeiouy]";
            if (word.matches(latinAlphabet)) {
                if (2 * Arrays.stream(word.split(vowels)).collect(Collectors.joining("")).length() == word.length()) {
                    ++counter;
                }
            }
        }
        System.out.printf("%d%n", counter);
    }

    /**
     * Find such a word that its characters are following in increasing order.
     * Input: a number of words N and the string of N words separated by spaces.
     * Output: a word satisfying conditions or "NOT FOUND".
     * If there are several such word, find the first such word.
     */
    public void task6() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < number; ++i) {
            char[] word = scanner.next().toCharArray();
            for (int j = 0; j < word.length - 1; ++j) {
                if (word[j] >= word[j + 1]) break;
                if (word.length - 2 == j) {
                    System.out.printf("%s%n", new String(word));
                    return;
                }
            }
        }
        System.out.printf("%s%n", NOT_FOUND);
    }

    /**
     * Find words which contain of only different characters.
     * Input: a number of words N and the string of N words separated by spaces.
     * Output: a string which contains of words satisfying conditions or "NOT FOUND".
     * If the word is not unique, output only once.
     */
    public void task7() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());
        String result = "", word = "";
        int countUniqueElements;
        Set<String> necessaryWords = new LinkedHashSet<String>();
        for (int i = 0; i < number; ++i) {
            word = scanner.next();
            countUniqueElements = (int) word.chars().distinct().count();
            if (countUniqueElements == word.length()) {
                necessaryWords.add(word);
            }
        }
        if(necessaryWords.isEmpty()) System.out.printf("%s%n", NOT_FOUND);
        else{
            for (String str : necessaryWords) {
                System.out.println(str);
            }
        }
    }

    /**
     * Find a palindromic number.
     * Input: a number of words N and the string of N words or numbers separated by spaces.
     * Output: a palindromic number or NOT FOUND.
     * If there is a bunch of appropriate numbers, we are to find the second one.
     * No restrictions on numbers size provided.
     * The number can be rather big.
     */
    public void task8() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());
        final String numbers = "[0-9]+";
        int counter = 0;
        String palindrome = "";
        for (int i = 0; i < number; ++i) {
            String word = scanner.next();
            if (word.matches(numbers)) {
                if (word.equals(new StringBuilder(word).reverse().toString())) {
                    if (counter == 1) {
                        palindrome = word;
                        break;
                    } else {
                        ++counter;
                        palindrome = word;
                    }
                }
            }
        }
        if (counter != 0) {
            System.out.printf("%s%n", palindrome);
        } else System.out.printf("%s%n", NOT_FOUND);
    }

    /**
     * Input: N -- dimension of matrix.
     * Output: numbers from 1 to N^2 as a NxN matrix.
     */
    public void task9(){
        Scanner scanner = new Scanner(System.in);
        int dimension = Integer.valueOf(scanner.nextLine());
        int counter = 0;
        int[][] matrix = new int[dimension][dimension];
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                matrix[i][j] = ++counter;
            }
        }
        Matrix.printMatrix(matrix);
    }

    /**
     * Find the real roots of a quadratic equation.
     * Input: three real coefficients which define an equation.
     * Output: No solution
     *         One solution: a root of the quadratic equation
     *         Two solution: a first root of the quadratic equation, a second root of the equation.
     * Use BigDecimal.HALF_UP.
     * The decimal separator is a point.
     */

    public void task10(){
        Scanner scanner = new Scanner(System.in);
        final String NO_SOLUTION = "No solution";
        final String ONE_SOLUTION = "One solution";
        final String TWO_SOLUTION =  "Two solutions";
        double A = scanner.nextDouble();
        double B = scanner.nextDouble();
        double C = scanner.nextDouble();
        double D = Math.pow(B, 2) - 4 * A * C;
        if(D < 0) System.out.println(NO_SOLUTION);
        else if(D == 0){
            BigDecimal x = (new BigDecimal(-B/(2 * A))).setScale(2,BigDecimal.ROUND_HALF_UP);
            System.out.println(ONE_SOLUTION + ": " + x);
        }
        else{
            BigDecimal x1 = (new BigDecimal((- B - Math.sqrt(D))/(2 * A))).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal x2 = (new BigDecimal((- B + Math.sqrt(D))/(2 * A))).setScale(2,BigDecimal.ROUND_HALF_UP);
            System.out.println(TWO_SOLUTION + ": " + x1 + ", " + x2);
        }
    }

    /**
     * Input: a number from 1 to 12.
     * Output: a name of the month or "INCORRECT INPUT DATA".
     * Use switch.
     * Check correctness of input.
     */
    public void task11(){
        Scanner scanner = new Scanner(System.in);
        final String INCORRECT_INPUT_DATA = "INCORRECT INPUT DATA";
        try{
            int numberMonth = Integer.valueOf(scanner.nextLine());
            switch (numberMonth){
                case 1: System.out.println(Month.of(numberMonth)); break;
                case 2: System.out.println(Month.of(numberMonth)); break;
                case 3: System.out.println(Month.of(numberMonth)); break;
                case 4: System.out.println(Month.of(numberMonth)); break;
                case 5: System.out.println(Month.of(numberMonth)); break;
                case 6: System.out.println(Month.of(numberMonth)); break;
                case 7: System.out.println(Month.of(numberMonth)); break;
                case 8: System.out.println(Month.of(numberMonth)); break;
                case 9: System.out.println(Month.of(numberMonth)); break;
                case 10: System.out.println(Month.of(numberMonth)); break;
                case 11: System.out.println(Month.of(numberMonth)); break;
                case 12: System.out.println(Month.of(numberMonth)); break;
                default: System.out.println(INCORRECT_INPUT_DATA); break;
            }
        } catch (NumberFormatException ex) {
            System.out.println(INCORRECT_INPUT_DATA);
        }
    }


    /**
     * Sort rows of the matrix in ascending order of elements in k-th column.
     * Input: a number of a column k (0 <= k < N), dimension of a matrix and a matrix NxN.
     * Output: dimension of the matrix and the transformed matrix.
     */
    public void task12(){
        Scanner scanner = new Scanner(System.in);
        int ncol = scanner.nextInt();
        int[][] matrix = Matrix.readMatrix(scanner);
        Arrays.sort(matrix, (o1, o2) -> o1[ncol] > o2[ncol] ? 1 : o1[ncol] < o2[ncol] ? -1 : Integer.compare(o1[ncol], o2[ncol]));
        System.out.println(matrix.length);
        Matrix.printMatrix(matrix);
    }

    /**
     * Perform a cyclic shift of a matrix by k positions downwards.
     * Input: a number of shift k, dimension of a mtrix and a matrix.
     * k > 0 -- cyclic shift of the matrix downwards.
     * k < 0 -- cyclic shift of the matrix upward.
     * k = 0 -- stays unchanged.
     * Output: dimension of the matrix and the transformed matrix.
     */
    public void task13(){
        Scanner scanner = new Scanner(System.in);
        int counter = scanner.nextInt();
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        if((counter != 0) && (Math.abs(counter) % dimension != 0)){
            if(counter > 0){
                for(int i = 0; i < counter; ++i){
                    for(int j = dimension - 1; j > 0; --j){
                        Matrix.swapRows(matrix[j], matrix[j - 1]);
                    }
                }
            }
            else{
                for(int i = 0; i < -counter; ++i){
                    for(int j = 0; j < dimension - 1; ++j){
                        if(j == dimension - 1) Matrix.swapRows(matrix[0], matrix[j]);
                        Matrix.swapRows(matrix[j], matrix[j + 1]);
                    }
                }
            }
        }
        System.out.println(matrix.length);
        Matrix.printMatrix(matrix);
    }

    /**
     * Find the maximum amount of the strictly increasing numbers in the sequence, which go successively.
     * The relational operator can be defined on the set, which consists of more than one element.
     * Input: N (integer) -- the amount of the sequence and N integer numbers, which are the elements of the sequence.
     * Output: The maximum amount of the strictly increasing numbers in the sequence.
     */
    public void task14(){
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());
        int numberMax = 0, counter = 1;
        int number1 = 0, number2 = 0;
        number1 = scanner.nextInt();
        for(int i = 0; i < number - 1; ++i){
            number2 = scanner.nextInt();
            if(number2 > number1){
                ++counter; number1 = number2;
            }
            else{
                if(numberMax < counter) numberMax = counter;
                counter = 1;
                number1 = number2;
            }
        }
        if(numberMax < counter) numberMax = counter;
        if(numberMax <= 1) numberMax = 0;
        System.out.println(numberMax);
    }

    /**
     * Find the sum of the matrix elements,
     *      which are placed between the first and the second positive elements on each row.
     * Input: The matrix.
     * Output: The sum.
     * If there isn't the elements, which are placed between the first and the second positive elements on the row,
     *  so the sum of this row = 0.
     *  The sum of the neighboring elements = 0.
     */
    public void task15(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        int counter = 0, sum = 0;
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(matrix[i][j] > 0) ++counter;
                if((counter == 1) && (matrix[i][j] <= 0)) sum += matrix[i][j];
                if(counter == 2) break;
            }
            counter = 0;
        }
        System.out.println(sum);
    }


    /**
     * Rotate a matrix by 90 degree anticlockwise.
     * Input: The matrix.
     * Output: The resultant matrix.
     */
    public void task16(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        int[][] resultMatrix = new int[dimension][dimension];
        for(int i  = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j) {
                resultMatrix[i][j] = matrix[j][dimension - 1 - i];
            }
        }
        System.out.println(resultMatrix.length);
        Matrix.printMatrix(resultMatrix);

    }

    /**
     * Find the determinant of the matrix.
     * Input: The matrix.
     * Output: The determinant is the integer number.
     */
    public void task17(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        double[][] matr = Matrix.upperTriangularMatrix(matrix);
        System.out.println(Matrix.getDet(matr));
    }


    /**
     * Find the maximum element in the matrix and delete the rows and the columns, which contain this element.
     * Input: The matrix.
     * Output: The resultant matrix.
     * N -- a number of the rows.
     * M -- a number of the columns.
     * N * M -- a dimension of the matrix.
     */
    public  void task18(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        int maxElement = 0;

        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(matrix[i][j] > maxElement) maxElement = matrix[i][j];
            }
        }

        Set<Integer> deleteRows = new HashSet<Integer>();
        Set<Integer> deleteColumns = new HashSet<Integer>();

        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(maxElement == matrix[i][j]){
                    deleteColumns.add(j);
                    deleteRows.add(i);
                }
            }
        }

        int resultColumns = dimension - deleteColumns.size();
        int resultRows = dimension - deleteRows.size();
        int[][] resultMatrix = new int[resultRows][resultColumns];

        int rowsResultMatrix = 0, columnsResultMatrix = 0;
        for(int i = 0; i < dimension; ++i){
            if(!deleteRows.contains(i)){
                for(int j = 0; j < dimension; ++j){
                    if(!deleteColumns.contains(j)){
                        resultMatrix[rowsResultMatrix][columnsResultMatrix] = matrix[i][j];
                        ++columnsResultMatrix;
                    }
                }
                ++rowsResultMatrix;
                columnsResultMatrix = 0;
            }
        }

        System.out.println(resultRows);
        System.out.println(resultColumns);

       /* for(int i = 0; i < resultColumns; ++i){
            for(int j = 0; j < resultColumns; ++j){
                System.out.print(resultMatrix[i][j]);
            }
            System.out.printf("%n");
        }*/

        Matrix.printMatrix(resultMatrix);
    }


    /**
     * Compact the matrix (delete the rows and the columns, which are zero).
     * Input: The matrix.
     * Output: The resultant matrix.
     * N -- a number of the rows.
     * M -- a number of the columns.
     * N * M -- a dimension of the matrix.
     */
    public void task19(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;

        Set<Integer> deleteRows = new HashSet<Integer>();
        Set<Integer> deleteColumns = new HashSet<Integer>();

        int counterNullInRows = 0;
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j  < dimension; ++j){
                if(matrix[i][j] == 0) ++counterNullInRows;
            }
            if(counterNullInRows == dimension) deleteRows.add(i);
            counterNullInRows = 0;
        }

        int counterNullInColumns = 0;
        for(int j = 0; j < dimension; ++j){
            for(int i = 0; i  < dimension; ++i){
                if(matrix[i][j] == 0) ++counterNullInColumns;
            }
            if(counterNullInColumns == dimension) deleteColumns.add(j);
            counterNullInColumns = 0;
        }

        int resultColumns = dimension - deleteColumns.size();
        int resultRows = dimension - deleteRows.size();
        int[][] resultMatrix = new int[resultRows][resultColumns];

        int rowsResultMatrix = 0, columnsResultMatrix = 0;
        for(int i = 0; i < dimension; ++i){
            if(!deleteRows.contains(i)){
                for(int j = 0; j < dimension; ++j){
                    if(!deleteColumns.contains(j)){
                        resultMatrix[rowsResultMatrix][columnsResultMatrix] = matrix[i][j];
                        ++columnsResultMatrix;
                    }
                }
                ++rowsResultMatrix;
                columnsResultMatrix = 0;
            }
        }

        System.out.println(resultRows);
        System.out.println(resultColumns);
        Matrix.printMatrix(resultMatrix);
    }


    /**
     * Find the minimum element and relocate to position of the specified element by rearrangement lines and columns.
     * The minimum is unique in the matrix.
     * Input: X (0 <= X < N) -- a number of the row,
     *        Y (0 <= Y < N) -- a number of the column,
     *        A matrix.
     * Output: The resultant matrix.
     */
    public void task20(){
        Scanner scanner = new Scanner(System.in);
        int numberRow = scanner.nextInt();
        int numberColumn = scanner.nextInt();
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        int rowMin = 0, columnMin = 0;
        int min = 0;
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(matrix[i][j] < min){
                    min = matrix[i][j];
                    rowMin = i;
                    columnMin = j;
                }
            }
        }
        int tmp = 0;
        for(int i = 0; i < dimension; ++i){
            tmp = matrix[numberRow][i];
            matrix[numberRow][i] = matrix[rowMin][i];
            matrix[rowMin][i] = tmp;
        }
        for(int i = 0; i < dimension; ++i){
            tmp = matrix[i][numberColumn];
            matrix[i][numberColumn] = matrix[i][columnMin];
            matrix[i][columnMin] = tmp;
        }
        System.out.println(dimension);
        Matrix.printMatrix(matrix);
    }

    /**
     * Change the matrix: the zero elements are after other elements.
     * Input: A matrix.
     * Output: The resultant matrix.
     */
    public void  task21(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        for(int i = 0; i < dimension; ++i){
            int flag = 0, j = 0;
            while(j < dimension - flag){
                if(matrix[i][j] == 0){
                    for(int  k = j  + 1; k < dimension; ++k){
                        int tmp = matrix[i][k];
                        matrix[i][k] = matrix[i][k - 1];
                        matrix[i][k - 1] = tmp;
                    }
                    ++flag;
                    --j;
                }
                ++j;
            }
        }
        System.out.println(dimension);
        Matrix.printMatrix(matrix);
    }

    /**
     * Round all the elements of the matrix to an integer numbers.
     * Round to the nearest integer.
     * Input: A double matrix (use scanner.nextDouble()).
     * Output: The integer matrix.
     */

    public void task22(){
        Scanner scanner = new Scanner(System.in);
        Double[][] matrix = Matrix.readDoubleMatrix(scanner);
        int dimension = matrix.length;
        int[][] resultMatrix = new int[dimension][dimension];
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                resultMatrix[i][j] = (int)(Math.round(matrix[i][j]));
            }
        }
        System.out.println(dimension);
        Matrix.printMatrix(resultMatrix);
    }

    /**
     * Find the amount of the saddle points.
     * Input: A matrix.
     * Output: The integer number, which is the amount of the saddle points in the matrix.
     */
    public void task23(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        int result = 0;
        //Set<Integer> inRows = new HashSet<Integer>();
        //Set<Integer> inColumns = new HashSet<Integer>();

        for(int[] aMatrix : matrix){
            int min = aMatrix[0];
            int jmin = 0;
            for(int j = 0; j < dimension; ++j){
                if(aMatrix[j] < min){
                    min = aMatrix[j];
                    jmin = j;
                }
            }
            int max = matrix[0][jmin];
            for(int[] aMatrix1 : matrix){
                if(aMatrix1[jmin] > max){
                    max = aMatrix1[jmin];
                }

            }
            if(min == max) ++result;
        }
        System.out.println(result);
    }

    /**
     * Change matrix: the sum of the elements on each row will be increasing.
     * Input: A matrix.
     * Output: The resultant matrix.
     * If the sums of the elements on the rows is the same, this rows won't be changed.
     */
    public void task24(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        Arrays.sort(matrix, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                int sum1 = 0, sum2 = 0;
                for(int i = 0; i < dimension; ++i){
                    sum1 += o1[i];
                    sum2 += o2[i];
                }
                return (sum1 > sum2 ? 1 : sum1 < sum2 ? -1 : 0);
            }
        });

        System.out.println(dimension);
        Matrix.printMatrix(matrix);
    }

    /**
     * Find the amount of the local minimum.
     * Input: A matrix.
     * Output: The amount of the local minimum.
     */

    public void task25(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        int result = 0;

        if(dimension <= 1) result = 1;
        else{
            for(int  i = 0; i < dimension; ++i){
                for(int j = 0; j < dimension; ++j){
                    if(Matrix.testLocalMin(matrix, i , j))
                        ++result;
                }
            }
        }
        System.out.println(result);
    }

    /**
     * Find the maximum of the local maximum.
     * Input: A matrix.
     * Output: This maximum or NOT FOUND.
     */
    public void task26(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        Set<Integer> localMax = new HashSet<>();

        if(dimension <= 1) System.out.println(matrix[0][0]);
        else {
            for (int i = 0; i < dimension; ++i) {
                for (int j = 0; j < dimension; ++j) {
                    if (Matrix.testLocalMax(matrix, i, j)) {
                      //  if (localMax.contains(matrix[i][j]))
                          //  localMax.remove(matrix[i][j]);
                       // else localMax.add(matrix[i][j]);
                        localMax.add(matrix[i][j]);
                    }


                }
            }

            if (localMax.isEmpty() || (Collections.max(localMax) == 0)) System.out.println(NOT_FOUND);
            else System.out.println(Collections.max(localMax));
        }
    }


    /**
     * To reorganize the given matrix by rearrangement its columns with respect to characteristics decreasing.
     * Input: A matrix.
     * Output: A resultant matrix.
     */

    public void task27(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = Matrix.readMatrix(scanner);
        int dimension = matrix.length;
        int[][] tempMatrix = Matrix.transposedMatrix(matrix);

        Arrays.sort(tempMatrix, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                int sum1 = 0, sum2 = 0;
                for(int i = 0; i < dimension; ++i){
                    sum1 += Math.abs(o1[i]);
                    sum2 += Math.abs(o2[i]);
                }
                return (sum1 > sum2 ? -1 : sum1 < sum2 ? 1 : 0);
            }
        });

        matrix = Matrix.transposedMatrix(tempMatrix);

        System.out.println(dimension);
        Matrix.printMatrix(matrix);
    }
}
