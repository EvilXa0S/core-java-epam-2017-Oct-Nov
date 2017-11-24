package com.epam.courses.jf.practice.asgalantsev.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.util.*;

public class Solver implements ISolver {

    public static void main(String[] args) {
        (new Solver()).task26();
    }

    public void task1() {
        Scanner input = new Scanner(System.in);
        String minString = "";
        String maxString = "";

        int number = input.nextInt();
        input.nextLine();

        for (int i = 0; i < number; i++) {
            String s = input.nextLine();
            if(i == 0)
                minString = s;
            if(s.length() >= maxString.length())
                maxString = s;
            if(s.length() <= minString.length())
                minString = s;
        }

        System.out.printf("MIN (%d): \"%s\"%n", minString.length(), minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxString.length(), maxString);
    }

    public void task2() {
        Scanner input = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        int number = input.nextInt();
        input.nextLine();
        for (int i = 0; i < number; i++) {
            String s = input.nextLine();
            list.add(s);
        }

        Collections.sort(list, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                String s1 = (String)o1;
                String s2 = (String)o2;
                if(s1.length() < s2.length())
                    return -1;
                else if(s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                }
                else
                    return 1;
            }
        });

        for(String s: list)
            System.out.printf("(%d): \"%s\"%n", s.length(), s);
    }

    public void task3() {
        Scanner input = new Scanner(System.in);
        int mean = 0;
        int sum = 0;
        ArrayList<String> list = new ArrayList<>();

        int number = input.nextInt();
        input.nextLine();
        for (int i = 0; i < number; i++) {
            String s = input.nextLine();
            list.add(s);
            sum += s.length();
        }

        mean = (int)(sum / list.size());
        System.out.printf("AVERAGE (%d)%n", mean);

        for(String s: list)
            if(s.length() < mean)
                System.out.printf("(%d): \"%s\"%n", s.length(), s);
    }

    public void task4() {
        Scanner input = new Scanner(System.in);
        String[] sentence;
        int minNumberOfChars;
        String minWord;

        int number = input.nextInt();
        input.nextLine();
        String s = input.nextLine();

        sentence = s.split(" ");
        minNumberOfChars = sentence[0].length();
        minWord = sentence[0];
        for(String word: sentence) {
            String noDuplicates = noDuplicatedChars(word);
            if(noDuplicates.length() < minNumberOfChars) {
                minNumberOfChars = noDuplicates.length();
                minWord = word;
            }
        }
        System.out.println(minWord);
    }

    public void task5() {
        Scanner input = new Scanner(System.in);
        String[] sentence;
        int counterOfEquilibrium = 0;

        int number = input.nextInt();
        input.nextLine();
        String s = input.nextLine();

        sentence = s.split(" ");

        for (String word: sentence) {
            if (word.matches("^[a-z||A-Z]+")) {
                int vowels = 0;
                int consonants = 0;
                for (char c : word.toCharArray())
                    if (("" + c).matches("[aoiyueAOUIYE]"))
                        vowels += 1;
                    else
                        consonants += 1;
                if(vowels == consonants)
                    counterOfEquilibrium += 1;

            }
        }
        System.out.println(counterOfEquilibrium);
    }

    public void task6() {
        Scanner input = new Scanner(System.in);
        String[] sentence;
        int counter = 0;
        boolean noAscendingWords = true;

        input.nextInt();
        input.nextLine();
        String s = input.nextLine();

        sentence = s.split(" ");

        for(String word: sentence) {
            if(hasAscendingChars(word)) {
                noAscendingWords = false;
                System.out.println(word);
                break;
            }
        }
         if(noAscendingWords == true)
             System.out.println("NOT FOUND");
    }

    public void task7() {
        Scanner input;
        String[] sentence;
        String s;
        Set<String> resultingString;

        input = new Scanner(System.in);
        input.nextInt();
        input.nextLine();
        s = input.nextLine();

        sentence = s.split(" ");
        resultingString = new LinkedHashSet<>();

        for(String word: sentence) {
            String newWord = word.toLowerCase();
            String noDuplicates = new String(noDuplicatedChars(newWord));
            if(newWord.equals(noDuplicates))
                resultingString.add(word);
        }

        if(resultingString.isEmpty())
            System.out.println("NOT FOUND");
        else
            for(String word: resultingString)
                System.out.println(word);

    }

    public void task8() {
        Scanner input = new Scanner(System.in);
        String[] sentence;
        String resultNumber = "";
        int counter = 0;

        input.nextInt();
        input.nextLine();
        String s = input.nextLine();

        sentence = s.split(" ");

        for(String word: sentence) {
            if(word.matches("\\D*"))
                continue;

            String reversedNumber = new String((new StringBuffer(word)).reverse());

            if(word.equals(reversedNumber)) {
                counter += 1;
                resultNumber = word;
            }
        }

        if(resultNumber.equals(""))
            System.out.println("NOT FOUND");
        else
            System.out.println(resultNumber);
    }

    public void task9() {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= number*number; i++) {
            if(i%number == 0)
                System.out.print(i);
            else
                System.out.print(i + "\t");

            if(i%number == 0)
                System.out.println();
        }
    }

    public void task10() {
        Scanner input = new Scanner(System.in);
        int[] numbers = new int[3];
        String[] equation;

        String s = input.nextLine();
        equation = s.split(" ");
        for (int i=0; i < equation.length; i++)
            numbers[i] = Integer.parseInt(equation[i]);

        int a = numbers[0];
        int b = numbers[1];
        int c = numbers[2];

        int discriminant = b * b - 4 * a * c;
        if(discriminant < 0)
            System.out.println("No solution");
        else if (discriminant == 0) {
            BigDecimal root = (new BigDecimal((double)((-b - Math.sqrt(discriminant)) / (2 * a))));
            System.out.println("One solution: " + root.setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            BigDecimal root1 = (new BigDecimal((double)((-b - Math.sqrt(discriminant)) / (2 * a))));
            BigDecimal root2 = (new BigDecimal((double)((-b + Math.sqrt(discriminant)) / (2 * a))));
            System.out.println("Two solutions: " +  root1.setScale(2, BigDecimal.ROUND_HALF_UP) + ", " + root2.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }

    public void task11() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        String result = "";
        int monthNumber = 0;

        try{
            monthNumber = Integer.parseInt(s);
        } catch(NumberFormatException e) { }

        switch(monthNumber) {
            case 1: result += "January";
                break;
            case 2: result += "February";
                break;
            case 3: result += "March";
                break;
            case 4: result += "April";
                break;
            case 5: result += "May";
                break;
            case 6: result += "June";
                break;
            case 7: result += "July";
                break;
            case 8: result += "August";
                break;
            case 9: result += "September";
                break;
            case 10: result += "October";
                break;
            case 11: result += "November";
                break;
            case 12: result += "December";
                break;
            default: result += "INCORRECT INPUT DATA";
        }

        System.out.println(result);
    }

    public void task12() {
        Scanner input = new Scanner(System.in);
        Map<Integer, int[]> map = new TreeMap<>();
        int[][] matrix;

        int col = input.nextInt();
        matrix = readMatrix(input);

        for(int[] row: matrix)
            map.put(new Integer(row[col]), row);

        System.out.println(matrix.length);

        for(int[] row: map.values()) {
            int counter = 0;
            for (int elem : row) {
                System.out.print(elem);
                if (++counter != row.length)
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void task13() {
        Scanner input = new Scanner(System.in);
        int[][] matrix;
        int[][] resultingMatrix;

        int shift = input.nextInt();
        matrix = readMatrix(input);
        resultingMatrix = new int[matrix.length][matrix.length];

        for(int i=0; i < matrix.length; i++) {
            int sign = 1;
            int len = matrix.length;
            int index = 0;

            try{
                sign = (int)((i + shift) / Math.abs(i + shift));
            } catch (ArithmeticException e ){ }

            if(shift >= 0)
                index = i + shift  - (len * (int)((i + shift) / len));
            else
                index = i + shift + (int)(0.5 * len * (1 - sign));

            resultingMatrix[index] = matrix[i];
        }

        System.out.println(resultingMatrix.length);
        for(int[] row: resultingMatrix) {
            int counter = 0;
            for (int elem : row) {
                System.out.print(elem);
                if (++counter != row.length)
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void task14() {
        Scanner input = new Scanner(System.in);
        int[] numbers;
        int maxCounter = 0;
        int counter = 1;

        int n = input.nextInt();
        input.nextLine();

        numbers = new int[n];
        for(int i=0; i < n; i++)
            numbers[i] = input.nextInt();

        for(int i=0; i < numbers.length-1; i++) {
            if(numbers[i] >= numbers[i+1]) {
                counter = 1;
                continue;
            } else
                counter++;
            if(counter > maxCounter)
                maxCounter = counter;
        }

        System.out.println(maxCounter);
    }

    public void task15() {
        Scanner input = new Scanner(System.in);
        int[][] matrix = readMatrix(input);

        int totalSum = 0;
        for (int[] row : matrix) {
            int sum = 0;
            int first = Integer.MAX_VALUE;
            int second = Integer.MAX_VALUE;
            for(int i = 0; i < row.length - 1; i++){
                if(row[i] > 0)
                    if(first == Integer.MAX_VALUE)
                        first = row[i];
                    else
                        second = row[i];
                else if(first != Integer.MAX_VALUE && second == Integer.MAX_VALUE)
                    sum += row[i];
            }
            totalSum += sum;
        }

        System.out.println(totalSum);
    }

    public void task16() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        int[][] tempMatrix = new int[matrix.length][matrix.length];
        int[][] newMatrix = new int[matrix.length][matrix.length];

        for(int i=0; i < matrix.length; i++)
            for(int j=0; j < matrix.length; j++)
                tempMatrix[i][j] = matrix[j][i];

        for(int i=0; i < matrix.length; i++)
            newMatrix[i] = tempMatrix[tempMatrix.length - i - 1];

        System.out.println(newMatrix.length);

        for(int[] row: newMatrix) {
            int counter = 0;
            for (int elem : row) {
                System.out.print(elem);
                if (++counter != row.length)
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void task17() {
        int[][] matrix = readMatrix(new Scanner(System.in));

        System.out.println(determinant(matrix));
    }

    public void task18() {
        int[][] result;

        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        int[][] matrix = readMatrix(input);

        int max = matrix[0][0];
        for(int i=0; i < matrix.length; i++)
            for(int j=0; j < matrix.length; j++) {
                if(max < matrix[i][j])
                    max = matrix[i][j];
            }

        for(int i=0; i < matrix.length; i++)
            for(int j=0; j < matrix.length; j++) {
                if(max == matrix[i][j]) {
                    rows.add(i);
                    cols.add(j);
                }
            }

        result = new int[matrix.length - (new TreeSet<>(rows)).size()][matrix.length - (new TreeSet<>(cols)).size()];
        int row = 0;
        int col = 0;

        for(int i=0; i < matrix.length; i++) {
            if (rows.contains(i))
                continue;
            col = 0;
            for (int j = 0; j < matrix.length; j++) {
                if(cols.contains(j))
                    continue;
                result[row][col] = matrix[i][j];
                col++;
            }
            row++;
        }

        System.out.println(result.length);
        System.out.println(result[0].length);

        for(int[] r: result) {
            int counter = 0;
            for (int elem : r) {
                if(++counter == r.length)
                    System.out.print(elem);
                else
                    System.out.print(elem + "\t");
            }
            System.out.println();
        }
    }

    public void task19() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();

        int[][] newMatrix;

        for(int i=0; i < matrix.length; i++) {
            int counter = 0;
            for(int j=0; j < matrix.length; j++) {
                if (matrix[i][j] == 0)
                    counter++;
                else
                    break;
            }
            if(counter == matrix.length)
                rows.add(i);
        }

        for(int i=0; i < matrix.length; i++) {
            int counter = 0;
            for(int j=0; j < matrix.length; j++) {
                if (matrix[j][i] == 0)
                    counter++;
                else
                    break;
            }
            if(counter == matrix.length)
                cols.add(i);
        }

        newMatrix = new int[matrix.length - (new TreeSet<>(rows)).size()][matrix.length - (new TreeSet<>(cols)).size()];
        int row = 0;
        int col = 0;

        for(int i=0; i < matrix.length; i++) {
            if (rows.contains(i))
                continue;
            col = 0;
            for (int j = 0; j < matrix.length; j++) {
                if(cols.contains(j))
                    continue;
                newMatrix[row][col] = matrix[i][j];
                col++;
            }
            row++;
        }

        System.out.println(newMatrix.length);
        System.out.println(newMatrix[0].length);

        for(int[] r: newMatrix) {
            int counter = 0;
            for (int elem : r) {
                System.out.print(elem);
                if (++counter != r.length)
                    System.out.print("\t");
            }
            System.out.println();
        }

    }

    public void task20() {
        Scanner input = new Scanner(System.in);
        int minRow;
        int minCol;
        int min;
        int[][] newMatrix;

        int row = input.nextInt();
        int col = input.nextInt();
        int[][] matrix = readMatrix(input);

        newMatrix = new int[matrix.length][matrix[0].length];
        min = matrix[0][0];
        minRow = 0;
        minCol = 0;

        for (int i = 0; i < matrix.length; i++)
            for (int j=0; j < matrix[0].length; j++)
                if(matrix[i][j] < min) {
                    min = matrix[i][j];
                    minRow = i;
                    minCol = j;
                }

        for (int i = 0; i < matrix.length; i++)
            for (int j=0; j < matrix[0].length; j++)
                newMatrix[i][j] = matrix[i][j];

        newMatrix[row] = matrix[minRow];
        newMatrix[minRow] = matrix[row];
        for(int i=0; i < newMatrix[0].length; i++) {
            int temp = newMatrix[i][col];
            newMatrix[i][col] = newMatrix[i][minCol];
            newMatrix[i][minCol] = temp;
        }

        System.out.println(newMatrix.length);
        for(int[] r: newMatrix) {
            int counter = 0;
            for (int elem : r) {
                System.out.print(elem);
                if (++counter != r.length)
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void task21() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        int[][] newMatrix = new int[matrix.length][matrix[0].length];

        for(int row=0 ; row < matrix.length; row++) {
            int[] newRow = new int[matrix[row].length];
            int zeroIndex = matrix[row].length-1;
            int index = 0;
            for (int i = 0; i < matrix[row].length; i++) {
                if(matrix[row][i] != 0)
                    newRow[index++] = matrix[row][i];
                else
                    newRow[zeroIndex--] = matrix[row][i];
            }
            newMatrix[row] = newRow;
        }

        System.out.println(newMatrix.length);
        for(int[] r: newMatrix) {
            int counter = 0;
            for (int elem : r) {
                System.out.print(elem);
                if (++counter != r.length)
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void task22() {
        double[][] doubleMatrix = readDoubleMatrix(new Scanner(System.in));
        int[][] longMatrix = new int[doubleMatrix.length][doubleMatrix[0].length];

        for(int i=0; i < doubleMatrix.length; i++)
            for(int j=0; j < doubleMatrix[0].length; j++) {
                longMatrix[i][j] = (int)Math.round(doubleMatrix[i][j]);
            }

        System.out.println(longMatrix.length);
        for(int[] r: longMatrix) {
            int counter = 0;
            for (int elem : r) {
                System.out.print(elem);
                if (++counter != r.length)
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void task23() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        List<Integer> rows = new ArrayList<>(matrix.length);
        List<Integer> cols = new ArrayList<>(matrix.length);

        List<Integer> result;
        int max;
        int min;

        for(int i=0; i < matrix.length; i++) {
            min = matrix[i][0];
            max = matrix[0][i];
            for(int j=0; j < matrix.length; j++) {
                if(matrix[i][j] < min)
                    min = matrix[i][j];
                if(matrix[j][i] > max)
                    max = matrix[j][i];
            }
            rows.add(min);
            cols.add(max);
        }

        result = new ArrayList<>(rows);
        result.retainAll(cols);

        System.out.println(result.size());
    }

    public void task24() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        ArrayList<int[]> matrixList = new ArrayList<>();

        for(int[] row: matrix)
            matrixList.add(row);

        Collections.sort(matrixList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int[] arr1 = (int[]) o1;
                int[] arr2 = (int[]) o2;
                int sum1 = 0;
                int sum2 = 0;
                for (int i = 0; i < arr1.length; i++) {
                    sum1 += arr1[i];
                    sum2 += arr2[i];
                }
                if (sum1 > sum2)
                    return 1;
                else if (sum1 < sum2)
                    return -1;
                else {
                    int idx1 = matrixList.indexOf(arr1);
                    int idx2 = matrixList.indexOf(arr2);
                    if(idx1 > idx2)
                        return 1;
                    else
                        return -1;
                }
            }
        });


        System.out.println(matrixList.size());
        for(int[] r: matrixList) {
            int counter = 0;
            for (int elem : r) {
                System.out.print(elem);
                if (++counter != r.length)
                    System.out.print("\t");
            }
            System.out.println();
        }
    }
    
    public void task25() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        int counter = 0;
        boolean flag = true;

        if(matrix.length == 1) {
            System.out.println(matrix[0][0]);
            return;
        }

        for(int i=0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int new_i;
                int new_j;
                int numberOfNeighbours;
                int n;

                n = matrix.length - 1;
                numberOfNeighbours = neighbours(matrix, i, j);
                flag = true;
                for(int k=0; k < numberOfNeighbours; k++) {
                    if(i==0 && j==0 || i==n && j==n || i==n && j==0 || i==0 && j==n) {
                        new_i = i - ((2 * i / n) - 1)*(k % 2) - ((int)(k - k%2) / 2)*((2 * i / n) - 1);
                        new_j = j - ((2 * j / n) - 1)*((k+1) % 2);
                    } else if(i==n || i==0 ) {
                        if(k > 2) {
                            new_i = i - ((2 * i / n) - 1);
                            new_j = j + (2 * (k - 4) + 1);
                        } else {
                            new_i = i - ((2 * i / n) - 1) * (k % 2);
                            new_j = j + (k - 1) * ((k + 1) % 2);
                        }
                    } else if(j==n || j==0) {
                        if(k > 2) {
                            new_j = j - ((2 * j / n) - 1);
                            new_i = i + (2 * (k - 4) + 1);
                        } else {
                            new_i = i + (k - 1) * ((k + 1) % 2);
                            new_j = j - ((2 * j / n) - 1) * (k % 2);
                        }
                    } else {
                        if(k > 3) {
                            new_i = i + (k-6) * (k % 2);
                            new_j = j + (k-5) * ((k+1) % 2);
                        } else {
                            new_i = i + (k - 2) * (k % 2);
                            new_j = j + (k - 1) * ((k + 1) % 2);
                        }
                    }

                    if(matrix[new_i][new_j] <= matrix[i][j]) {
                        flag = false;
                        break;
                    }
                }
                if(flag == true)
                    counter++;
            }
        }
        System.out.println(counter);
    }

    public void task26() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        List<Integer> list = new ArrayList<>();
        int counter = 0;
        boolean flag = true;

        if(matrix.length == 1) {
            System.out.println(matrix[0][0]);
            return;
        }

        for(int i=0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int new_i;
                int new_j;
                int numberOfNeighbours;
                int n;

                n = matrix.length - 1;
                numberOfNeighbours = neighbours(matrix, i, j);
                flag = true;

                for(int k=0; k < numberOfNeighbours; k++) {
                    if(i==0 && j==0 || i==n && j==n || i==n && j==0 || i==0 && j==n) {
                        new_i = i - ((2 * i / n) - 1)*(k % 2) - ((int)(k - k%2) / 2)*((2 * i / n) - 1);
                        new_j = j - ((2 * j / n) - 1)*((k+1) % 2);
                    } else if(i==n || i==0 ) {
                        if(k > 2) {
                            new_i = i - ((2 * i / n) - 1);
                            new_j = j + (2 * (k - 4) + 1);
                        } else {
                            new_i = i - ((2 * i / n) - 1) * (k % 2);
                            new_j = j + (k - 1) * ((k + 1) % 2);
                        }
                    } else if(j==n || j==0) {
                        if(k > 2) {
                            new_j = j - ((2 * j / n) - 1);
                            new_i = i + (2 * (k - 4) + 1);
                        } else {
                            new_i = i + (k - 1) * ((k + 1) % 2);
                            new_j = j - ((2 * j / n) - 1) * (k % 2);
                        }
                    } else {
                        if(k > 3) {
                            new_i = i + (k-6) * (k % 2);
                            new_j = j + (k-5) * ((k+1) % 2);
                        } else {
                            new_i = i + (k - 2) * (k % 2);
                            new_j = j + (k - 1) * ((k + 1) % 2);
                        }
                    }

                    if(matrix[new_i][new_j] >= matrix[i][j]) {
                        flag = false;
                        break;
                    }
                }
                if(flag == true)
                    list.add(matrix[i][j]);
            }
        }
        if(list.size() == 0)
            System.out.println("NOT FOUND");
        else
            System.out.println(Collections.max(list));
    }

    public void task27() {
        int[][] matrix = readMatrix(new Scanner(System.in));
        List<int[]> colList = new ArrayList<>();

        for(int i=0; i < matrix.length; i++) {
            int[] col = new int[matrix[i].length];
            for(int j=0; j < matrix[i].length; j++)
                col[j] = matrix[j][i];
            colList.add(col);
        }

        Collections.sort(colList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int[] arr1 = (int[]) o1;
                int[] arr2 = (int[]) o2;
                int sum1 = 0;
                int sum2 = 0;
                for (int i = 0; i < arr1.length; i++) {
                    sum1 += Math.abs(arr1[i]);
                    sum2 += Math.abs(arr2[i]);
                }
                if (sum1 < sum2)
                    return 1;
                else if (sum1 > sum2)
                    return -1;
                else {
                    int idx1 = colList.indexOf(arr1);
                    int idx2 = colList.indexOf(arr2);
                    if(idx1 > idx2)
                        return 1;
                    else
                        return -1;
                }
            }
        });

        System.out.println(colList.size());
        for (int i = 0; i < colList.size(); i++) {
            int counter = 0;
            for (int j = 0; j < colList.size(); j++) {
                System.out.print(colList.get(j)[i]);
                if (++counter != colList.size())
                    System.out.print("\t");
            }
            System.out.println();
        }
    }


    private String noDuplicatedChars(String word) {
        String result = "";
        for(int i = 0; i < word.length(); i++)
            if(!result.contains("" + word.charAt(i)))
                result += word.charAt(i);
        return result;
    }

    private boolean hasAscendingChars(String word) {
        if(word.length() <= 1)
            return false;
        for(int i=0; i < word.length() - 1; i++) {
            if(word.charAt(i) >= word.charAt(i+1))
                return false;
        }
        return true;
    }

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

    private double[][] readDoubleMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        double[][] matrix = new double[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    private int determinant(int[][] M) {
        if(M.length <= 0)
            return 0;
        else if(M.length == 1)
            return M[0][0];
        else if(M.length == 2)
            return M[0][0] * M[1][1] - M[0][1] * M[1][0];
        else {
            int len = M.length;
            int res = 0;
            for(int i=len; i >= 1; i--) {
                int sign = (len - i)%2 == 0 ? 1 : -1;

                int[][] newMatrix = new int[len-1][len-1];
                int row = 0;
                for(int j=0; j < newMatrix.length; j++) {
                    if(row == i - 1)
                        row++;
                    for (int k = 0; k < newMatrix.length; k++)
                        newMatrix[j][k] = M[row][k];
                    row++;
                }
                res += sign * M[i-1][len-1] * determinant(newMatrix);
            }
            return res;
        }
    }

    private int neighbours(int[][] M, int i, int j) {
        int len = M.length - 1;
        if(i%len == 0 && j%len == 0)
            return 3;
        else if(i%len == 0 || j%len == 0)
            return 5;
        else
            return 8;
    }
}
