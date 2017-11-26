package com.epam.courses.jf.practice.vakhonin.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.util.*;

public class Solver implements ISolver {

    static final String NOT_FOUND = "NOT FOUND";


    public void task1() {
        Scanner in= new Scanner(System.in);
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

        for (String str: strings) {
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
                result =  s1.compareToIgnoreCase(s2);
            }
            else {
                result =  (length1 - length2);
            }
            return result;
        };

        Scanner in = new Scanner(System.in);
        int numberOfStrings = Integer.valueOf(in.nextLine());

        for (int j = 0; j < numberOfStrings; j++) {
            list.add(in.nextLine());
        }

        list.sort(comparator);

        for (String s: list) {
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
        int code, previousCode = -1;
        String result = NOT_FOUND;
        String[] words = new String[numberOfWords];

        for (int j = 0; j < numberOfWords; j++) {
            words[j] = in.next();
        }

        for (String word: words) {
            if (word.length() == 1) {
                result = word;
                break;
            }

            for (char ch: word.toCharArray()) {
                code = (int) ch;

                if (code > previousCode) {
                    previousCode = code;
                }
                else {
                    previousCode = -1;
                    break;
                }
            }

            if (previousCode != -1) {
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
        numberOfWords = Integer.valueOf(in.nextLine());

        String[] strings = new String[numberOfWords];
        for (int j = 0; j < numberOfWords; j++) {
            strings[j] = in.next();
        }

        int length;
        Set<Character> charSet;

        StringBuilder result;
        result = new StringBuilder("");
//        Set<String> words = new LinkedHashSet<>();
        Set<String> wordsSet = new HashSet<>();

        for (String word: strings) {
//            charSet = new LinkedHashSet();
            charSet = new HashSet();
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
        }
        else {
            for (String s: wordsSet) {
                result.append(s);
                result.append(" ");
            }

            result.deleteCharAt(result.length() - 1);
        }

        System.out.println(result.toString());
    }   // DOING!
    
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

        for (String str: stringList) {
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
    }   // DOING!
//
    public void task9() {
        Scanner in;
        int n, length;
        int[][] matrix;
        StringBuilder stringMatrix;

        in = new Scanner(System.in);
        n = in.nextInt();
        matrix = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                matrix[j][k] = j * n + k + 1;
            }
        }

        stringMatrix = new StringBuilder();
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                stringMatrix.append(matrix[j][k] + "\t");
            }
            length = stringMatrix.length();
            stringMatrix.setLength(length - 1);
            stringMatrix.append("\n");
        }

        length = stringMatrix.length();
        stringMatrix.setLength(length - 1);

        System.out.println(stringMatrix);
    }   // DOING!
//
    public void task10() {
        Scanner in;
        String result;
        double a, b, c, d;
        BigDecimal x1, x2;

        in = new Scanner(System.in);
        a = in.nextDouble();
        b = in.nextDouble();
        c = in.nextDouble();

        result = new String();

        if (a == 0) {
            if (b == 0) {
                result = "No solution";
            } else {
                x1 = (new BigDecimal(-c / b)).setScale(2, BigDecimal.ROUND_HALF_UP);
                result = "One solution: " + x1;
            }
        } else {
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
    }   // DOING!
//
    public void task11() {
        Scanner in;
        String month, data, result;
        int numberOfMonth;

        in = new Scanner(System.in);
        data = in.nextLine();
        result = "";
        month = "";

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
        } else {
            result += "INCORRECT INPUT DATA";
        }
        System.out.println(result);
    }   // DOING!
//
    public void task12() {
        Scanner in = new Scanner(System.in);
        int numberOfColumn = in.nextInt();
        Integer[][] matrix = enterMatrixInteger(in);
        Arrays.sort(matrix, new Comparator<Integer[]>() {
            public int compare(Integer[] row1, Integer[] row2) {
                return row1[numberOfColumn] - row2[numberOfColumn];
            }
        });
        System.out.println(matrix.length);

        printMatrixInteger(matrix);
    }   // DOING!
//
    public void task13() {
        Scanner in;
        int n, k, length, shift;
        Integer[][] matrix;
        StringBuilder stringMatrix;

        in = new Scanner(System.in);


        //        System.out.println("Enter displacement quantity:");
        k = in.nextInt();


        matrix = enterMatrixInteger(in);

        n = matrix.length;

        Integer[][] newMatrix = new Integer[n][n];

        shift = -(n + k % n) % n + n;

//        stringMatrix = new StringBuilder();

        for (int j = 0; j < n; j++) {
            for (int q = 0; q < n; q++) {
                newMatrix[j][q] = matrix[(j + shift) % n][q];
//                stringMatrix.append(matrix[(j + shift) % n][q] + "\t");
            }
        }

        System.out.println(n);

        printMatrixInteger(newMatrix);
    }   // DOING!

//
    public void task14() {
        Scanner in;
        int n, count, max, result;
        int[] seq;

        in = new Scanner(System.in);
        n = Integer.valueOf(in.nextLine());
        seq = new int[n];
        for (int j = 0; j < n; j++) {
            seq[j] = in.nextInt();
        }

        count = 1;
        max = 1;

        for (int j = 1; j < n; j++) {
            if (seq[j] > seq[j - 1]) {
                count++;
            } else {
                max = count;
                count = 1;
            }
        }

        if (count > max) {
            max = count;
        }
        if (max == 1){
            result = 0;
        }
        else{
            result = max;
        }

        System.out.println(result);
    }   // DOING!
//
    public void task15() {
        Scanner in;
        int n, sumIn, sumOut;
        Integer[][] matrix;

        in = new Scanner(System.in);
//        System.out.println("Enter matrix dimension:");
//        n = Integer.valueOf(in.nextLine());
//        matrix = new Integer[n][n];

        matrix = enterMatrixInteger(in);
        n = matrix.length;
        sumOut = 0;

        for (int k = 0; k < n; k++) {

            int counter = 0;
            int index1 = -1;
            int index2 = -1;
            for (int j = 0; !((counter == 2) || (j == n)); j++) {
                if (matrix[k][j] > 0) {
                    counter++;
                    if (index1 > -1) {
                        index2 = j;
                    } else {
                        index1 = j;
                    }
                }
            }

            sumIn = 0;

            if ((counter == 2) && ((index2 - index1) > 1)) {
                for (int j = index1 + 1; j < index2; j++) {
                    sumIn += matrix[k][j];
                }
            }
            sumOut += sumIn;
        }
        System.out.println(sumOut);
    }   // DOING!
//
//    //TODO: где-то нужно еще какие то размерности выводить...
//    //TODO: compareToIgnoreCase
//    //TODO: корни уравнения, разобрать все случаи
//    //TODO: седловые точки в 23. что делать, если минимальный элемент в нескольких экзамеплярах ?
    public void task16() {
        Scanner in;
        int n, length;
        Integer[][] matrix, matrixNew;
//        StringBuilder stringMatrix;

        in = new Scanner(System.in);
//        System.out.println("Enter matrix dimension:");
//        n = in.nextInt();
        matrix = enterMatrixInteger(in);
        n = matrix.length;
        matrixNew = new Integer[n][n];
//        System.out.println("Enter matrix of " + n + "x" + n + ":");
        for (int j = 0; j < n; j++) {
            for (int q = 0; q < n; q++) {
//                matrix[j][q] = in.nextInt();
                matrixNew[n - q - 1][j] = matrix[j][q];
            }
        }
        System.out.println(n);
        printMatrixInteger(matrixNew);
    }   // DOING!
//
    public void task17() {
        Scanner in = new Scanner(System.in);

        Integer[][] matrix;
        matrix = enterMatrixInteger(in);

        Integer element;
        int det = 1;
        int length = matrix.length;
        for (int j = 0; j < length; j++) {

            for (int k = j; k < length; k++) {
                element = matrix[j][k];
                if (!element.equals(0)) {
                    swapRows(matrix, j, k);
                    break;
                }
                det = 0;
                break;
            }

            if (det != 0) {
                for (int k = j + 1; k < length; k++) {
                    for (int i = length - 1; i >= j; i--) {
                        matrix[k][i] = matrix[j][j] * matrix[k][i] - matrix[k][j] * matrix[j][i];
                    }
                }
            }
        }


        if (det != 0) {
//            printMatrixInteger(matrix);
            det = matrix[length - 1][length - 1];
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < (length - j - 2); k++) {
                    det /= matrix[j][j];
                }
            }
        }
        System.out.println(det);
    }   // DOING!
//
    public void task18() {
        int n, max, k, i;
        Integer[][] matrix, matrixNew;
        Set<Integer> rows, columns;
        Scanner in = new Scanner(System.in);
        matrix = enterMatrixInteger(in);
        n = matrix.length;

        rows = new HashSet();
        columns = new HashSet();

        max = matrix[0][0];

        for (int j = 0; j < n; j++) {
            for (int q = 0; q < n; q++) {
                if (matrix[j][q] >= max) {
                    if (matrix[j][q] == max) {
                        rows.add(j);
                        columns.add(q);
                    } else {
                        rows = new HashSet();
                        columns = new HashSet();
                        rows.add(j);
                        columns.add(q);
                        max = matrix[j][q];
                    }
                }

            }
        }

        matrixNew = new Integer[n - rows.size()][n - columns.size()];

        k = 0;
        for (int j = 0; j < n; j++) {
            if (!rows.contains(j)) {
                i = 0;
                for (int q = 0; q < n; q++) {
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

        printMatrixInteger(matrixNew);
    }   // DOING!
//
    public void task19() {
        int n, i, k, numberOfZeros;
        Integer[][] matrix, matrixNew;
        Set<Integer> rows, columns;
        Scanner in = new Scanner(System.in);
        matrix = enterMatrixInteger(in);
        n = matrix.length;

        rows = new HashSet();
        columns = new HashSet();

        for (int j = 0; j < n; j++) {
            numberOfZeros = 0;
            for (int q = 0; q < n; q++) {
                if (matrix[j][q] != 0) {
                    break;
                }
                numberOfZeros++;
            }
            if (numberOfZeros == n) {
                rows.add(j);
            }
        }

        for (int j = 0; j < n; j++) {
            numberOfZeros = 0;
            for (int q = 0; q < n; q++) {
                if (matrix[q][j] != 0) {
                    break;
                }
                numberOfZeros++;
            }
            if (numberOfZeros == n) {
                columns.add(j);
            }
        }

        matrixNew = new Integer[n - rows.size()][n - columns.size()];

        k = 0;
        for (int j = 0; j < n; j++) {
            if (!rows.contains(j)) {
                i = 0;
                for (int q = 0; q < n; q++) {
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

        printMatrixInteger(matrixNew);
    }   // DOING!
//
    public void task20() {

        Scanner in;

        int n, min, row, column, targetRow, targetColumn;
        Integer[][] matrix, matrixT, matrixNew;
        Integer[] temp;

        in = new Scanner(System.in);

//        System.out.println("Enter target row: ");
        targetRow = in.nextInt();
//        System.out.println("Enter target column: ");
        targetColumn = in.nextInt();

        matrix = enterMatrixInteger(in);
        n = matrix.length;
//        matrixNew = new Integer[n][n];
        row = 0;
        column = 0;
        min = matrix[0][0];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (matrix[j][k] <= min) {
                    min = matrix[j][k];
                    row = j;
                    column = k;
                }
            }
        }

        temp = matrix[targetRow].clone();
        matrix[targetRow] = matrix[row].clone();
        matrix[row] = temp.clone();

        matrixT = transposeMatrix(matrix);

        temp = matrixT[targetColumn].clone();
        matrixT[targetColumn] = matrixT[column].clone();
        matrixT[column] = temp.clone();

        matrix = transposeMatrix(matrixT);

        System.out.println(n);
        printMatrixInteger(matrix);
    }   // DOING!
//
//
//    //    TODO: to do universe method: enterMatrix
    public void task21() {

//        int length;
        Scanner in = new Scanner(System.in);
        Integer[][] matrix = enterMatrixInteger(in);
//        length = matrix.length;
        int n = matrix.length;

        for (Integer[] arr : matrix) {
            Arrays.sort(arr, new Comparator<Integer>() {
                public int compare(Integer r1, Integer r2) {
                    int result = 0;

                    if (r1 == 0 && r2 != 0) {
                        result = 1;
                    }
                    if (r1 != 0 && r2 == 0) {
                        result = -1;
                    }
                    //TODO: это можно убрать, наверное, так как резалт уже задан как 0
                    if (r1 == r2) {
                        result = 0;
                    }
                    return result;
                }
            });
        }
        System.out.println(n);
        printMatrixInteger(matrix);
    }   // DOING!
//
    public void task22() {

        Scanner in = new Scanner(System.in);
        Double[][] matrix = enterMatrixDouble(in);
        Integer[][] matrixNew;

        int length = matrix.length;
        matrixNew = new Integer[length][length];
        for (int j = 0; j < length; j++) {
            for (int k = 0; k < length; k++) {
                matrixNew[j][k] = (int) Math.round(matrix[j][k]);
            }

        }

        System.out.println(length);
        printMatrixInteger(matrixNew);
    }   // DOING!

    public void task23() {
        Scanner in = new Scanner(System.in);
        Integer[][] matrix = enterMatrixInteger(in);
        int n = matrix.length;
        Integer[][] matrixT = transposeMatrix(matrix.clone());
        int number = 0;
        Integer min;
        int index;
        for (int j = 0; j<n; j++){
            Integer[] minElementWithIndex = minElementInArray(matrix[j]);
            min = minElementWithIndex[0];
            index = minElementWithIndex[1];
            if(min.equals(maxElementInArray(matrixT[index]))){
                number++;
            }
        }
        System.out.println(number);
    }

    public void task24() {


        Scanner in = new Scanner(System.in);
        Integer[][] matrix = enterMatrixInteger(in);
        int n = matrix.length;
        Arrays.sort(matrix, new Comparator<Integer[]>() {
            public int compare(Integer[] arr1, Integer[] arr2) {

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
            }
        });

        System.out.println(n);
        printMatrixInteger(matrix);
    }   // DOING!
//
    public void task25() {

        Scanner in = new Scanner(System.in);
        Integer[][] matrix = enterMatrixInteger(in);
        int n = matrix.length;
        Integer[][] matrixExpanded = new Integer[n+2][n+2];
        for(int j = 0; j<(n+2); j++){
            matrixExpanded[j][0] = Integer.MAX_VALUE;
            matrixExpanded[j][n+1] = Integer.MAX_VALUE;
            matrixExpanded[0][j] = Integer.MAX_VALUE;
            matrixExpanded[n+1][j] = Integer.MAX_VALUE;
        }
        for(int j = 0; j < n; j++){
            for(int k = 0; k<n; k++){
                matrixExpanded[j+1][k+1] = matrix[j][k];
            }
        }
        int result;
        int number = 0;
        for(int j = 1; j < n+1; j++){
            for(int k = 1; k < n+1; k++){
                Integer el = matrixExpanded[j][k];
                if (((el < matrixExpanded[j - 1][k]) && (el < matrixExpanded[j - 1][k - 1]) && (el < matrixExpanded[j][k - 1]) && (el < matrixExpanded[j + 1][k - 1]) && (el < matrixExpanded[j + 1][k + 1]) && (el < matrixExpanded[j + 1][k]) && (el < matrixExpanded[j][k + 1]) && (el < matrixExpanded[j - 1][k + 1]))) {
                    number++;
                }
            }
        }

        System.out.println(number);
    }   // DOING!
//
    public void task26() { //TODO: оформить код!!!!

        Scanner in;
        int n;
        Integer[][] matrix;

        in = new Scanner(System.in);
//        System.out.println("Enter matrix dimension:");
        n = in.nextInt();
        matrix = new Integer[n + 2][n + 2];
//        System.out.println("Enter matrix of " + n + "x" + n + ":");


        for (int q = 1; q < n + 1; q++) {
            for (int j = 1; j < n + 1; j++) {
                matrix[j][q] = in.nextInt();
            }
        }
        for (int j = 0; j < n + 2; j++) {
            matrix[j][0] = Integer.MIN_VALUE;
            matrix[j][n + 1] = Integer.MIN_VALUE;
            matrix[0][j] = Integer.MIN_VALUE;
            matrix[n + 1][j] = Integer.MIN_VALUE;

        }


        int length = matrix.length;
        Integer maximum = Integer.MIN_VALUE;

        for (int j = 1; j < n + 1; j++) {
            for (int k = 1; k < n + 1; k++) {
                if ((matrix[j][k] > maximum) && ((matrix[j][k] > matrix[j - 1][k]) && (matrix[j][k] > matrix[j - 1][k - 1]) && (matrix[j][k] > matrix[j][k - 1]) && (matrix[j][k] > matrix[j + 1][k - 1]) && (matrix[j][k] > matrix[j + 1][k + 1]) && (matrix[j][k] > matrix[j + 1][k]) && (matrix[j][k] > matrix[j][k + 1]) && (matrix[j][k] > matrix[j - 1][k + 1]))) {
                    maximum = matrix[j][k];
                }
            }
        }
        String result;

        if (maximum.equals(Integer.MIN_VALUE)) {
            result = "NOT FOUND";
        } else {
            result = "" + maximum;
        }

        System.out.println(result);
    }   // DOING!
//
    public void task27() {

        Scanner in = new Scanner(System.in);
        Integer[][] matrix = transposeMatrix(enterMatrixInteger(in));
        int n = matrix.length;
        Arrays.sort(matrix, new Comparator<Integer[]>() {
            public int compare(Integer[] line1, Integer[] line2) {
                return characteristic(line2) - characteristic(line1);
            }
        });

        Integer[][] matrixNew = transposeMatrix(matrix);
        System.out.println(n);
        printMatrixInteger(matrixNew);
    }   // DOING!

//

//
//
//    String emulate(ArrayList<String> peoples){
//
//        int size = peoples.size();
//        int j = 0;
//        int nextIndex = 0;
//        while(size!=1){
//            peoples.remove(nextIndex % size);
//            j++;
//            nextIndex = j % size;
//            size--;
//        }
//
//        return peoples.get(0);
//    }
//
//    String emulate(LinkedList<String> peoples){
//
//        int size = peoples.size();
//        int j = 0;
//        int nextIndex = 0;
//        while(size!=1){
//            peoples.remove(nextIndex % size);
//            j++;
//            nextIndex = j % size;
//            size--;
//        }
//        return peoples.get(0);
//    }
//
//
//    List<Integer> transform(List<Integer> integers, int value){
//        for(int j = integers.size()-1; j > 0; j--) {
//            if(integers.get(j) <= value) {
//                integers.set(j, integers.get(j) ^ integers.get(0));
//                integers.set(0, integers.get(j) ^ integers.get(0));
//                integers.set(j, integers.get(j) ^ integers.get(0));
//            }
//        }
//
////        integers.sort(Comparator.naturalOrder()); // Maybe this method (in one line) also fits to do this task... :-)
//
//        return integers;
//    }
//
//    HashMap<String, Integer> countNumberWords(String[] strArr){
//        HashMap<String, Integer> result = new HashMap<>();
//        Integer countNow;
//        for(String s: strArr){
//            s.toLowerCase();
//            if(result.containsKey(s)){
//                countNow = result.get(s);
//                result.put(s, ++countNow);
//            }
//            else{
//                result.put(s, 1);
//            }
//        }
//
//        return result;
//    } //    TODO: to do File as argument






//
//
    public Integer characteristic(Integer[] line) {
        int length = line.length;
        Integer result;
        result = 0;
        for (int j : line) {
            result += Math.abs(j);
        }
        return result;
    }
//
    public int sum(Integer[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        return sum;
    }
//
//    public int[][] enterMatrix() {
//        Scanner in;
//        int n;
//        int[][] matrix;
//
//        in = new Scanner(System.in);
//        System.out.println("Enter matrix dimension:");
//        n = in.nextInt();
//        matrix = new int[n][n];
//        System.out.println("Enter matrix of " + n + "x" + n + ":");
//        for (int j = 0; j < n; j++) {
//            for (int q = 0; q < n; q++) {
//                matrix[j][q] = in.nextInt();
//            }
//        }
//        return matrix;
//    }
//
    public void swapRows(Integer[][] matrix, int j, int k) {
        Integer[] temp;
        temp = matrix[j];
        matrix[j] = matrix[k];
        matrix[k] = temp;
    }
//
    public Integer[][] enterMatrixInteger(Scanner in) {

        int n;
        Integer[][] matrix;

        n = in.nextInt();
        matrix = new Integer[n][n];


        for (int j = 0; j < n; j++) {
            for (int q = 0; q < n; q++) {
                matrix[j][q] = in.nextInt();
            }
        }
        return matrix;
    }
//
    public Double[][] enterMatrixDouble(Scanner in) {

        int n;
        Double[][] matrix;


//        System.out.println("Enter matrix dimension:");
        n = in.nextInt();
        matrix = new Double[n][n];
//        System.out.println("Enter matrix of " + n + "x" + n + ":");
        for (int j = 0; j < n; j++) {
            for (int q = 0; q < n; q++) {
                matrix[j][q] = in.nextDouble();
            }
        }
        return matrix;
    }
//
//
//    public void printMatrix(int[][] matrix) {
//
//        StringBuilder stringMatrix = new StringBuilder();
//        int length, n, m;
//
//        n = matrix.length;
//        m = matrix[0].length;
//
//        for (int j = 0; j < n; j++) {
//            for (int q = 0; q < m; q++) {
//                stringMatrix.append(matrix[j][q] + "\t");
//            }
//            length = stringMatrix.length();
//            stringMatrix.setLength(length - 1);
//            stringMatrix.append("\n");
//        }
//
//        length = stringMatrix.length();
//        stringMatrix.setLength(length - 1);
//        System.out.println(stringMatrix);
//    }
//
    private void printMatrixInteger(Integer[][] matrix){

        StringBuilder stringMatrix = new StringBuilder();
        int length, n, m;

        n = matrix.length;
        m = matrix[0].length;

        for (int j = 0; j < n; j++) {
            for (int q = 0; q < m; q++) {
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
//
    public Integer[][] transposeMatrix(Integer[][] matrix) {
        int n, m;
        Integer[][] matrixNew;
        n = matrix.length;
        m = matrix[0].length;

        matrixNew = new Integer[m][n];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < m; k++) {
                matrixNew[k][j] = matrix[j][k];
            }
        }

        return matrixNew;
    }


    Integer[] minElementInArray(Integer[] arr){
        Integer min = Integer.MAX_VALUE;
        Integer index = 0;
        Integer el;
        int n = arr.length;

        for(int j = 0; j<n; j++){
            el = arr[j];
            if(el < min){
                min = el;
                index = j;
            }
        }
        Integer[] result = {min, index};
        return result;
    }

    Integer maxElementInArray(Integer[] arr){
        Integer max = Integer.MIN_VALUE;
        for(Integer el: arr){
            if(el > max){
                max = el;
            }
        }
        return max;
    }

//
//    public Integer[][] transposeMatrixInteger(Integer[][] matrix) {
//        int n, m;
//        Integer[][] matrixNew;
//        n = matrix.length;
//        m = matrix[0].length;
//
//        matrixNew = new Integer[m][n];
//
//        for (int j = 0; j < n; j++) {
//            for (int k = 0; k < m; k++) {
//                matrixNew[k][j] = matrix[j][k];
//            }
//        }
//
//        return matrixNew;
//    }
}
