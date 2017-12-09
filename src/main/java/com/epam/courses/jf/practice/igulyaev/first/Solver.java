package com.epam.courses.jf.practice.igulyaev.first;

import com.epam.courses.jf.practice.common.first.ISolver;
import com.epam.courses.jf.practice.igulyaev.util.Reader;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static com.epam.courses.jf.practice.igulyaev.util.MatrixUtils.*;

public class Solver implements ISolver {

    @Override
    public void task1() {
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        String maxString = reader.readLine();
        String minString = maxString;
        for(int i = 1; i < n; ++i){
            String str = reader.readLine();
            if(str.length() >= maxString.length()){
                maxString = str;
            }
            if(str.length() <= minString.length()){
                minString = str;
            }
        }
        System.out.printf("MIN (%d): \"%s\"%n", minString.length(), minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxString.length(), maxString);
    }

    @Override
    public void task2(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        List<String> stringList = new ArrayList<>(n);

        for(int i = 0; i < n; ++i){
            stringList.add(reader.readLine());
        }
        stringList.sort((s1,s2) ->
                s1.length() < s2.length() ? -1 : (s1.length() > s2.length() ? 1 : s1.compareTo(s2)));

        stringList.forEach(s -> System.out.printf("(%d): \"%s\"%n", s.length(), s));
    }

    @Override
    public void task3(){
        final Reader reader = new Reader(System.in);
        List<String> stringList = new LinkedList<>();
        int n = Integer.parseInt(reader.readLine());
        long sizeSum = 0;
        for(int i = 0; i < n; ++i){
            String str = reader.readLine();
            sizeSum += str.length();
            stringList.add(str);
        }
        final long averageLength = sizeSum / n;
        System.out.printf("AVERAGE (%d)%n", averageLength);
        stringList.stream()
                .filter(s -> s.length() < averageLength)
                .forEach(s -> System.out.printf("(%d): \"%s\"%n", s.length(), s));
    }

    @Override
    public void task4(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        Arrays.stream(reader.readLine().split(" ", n))
                .filter(s -> s.matches("^\\p{Alpha}+$"))
                .reduce((first, second) ->
                        first.chars().distinct().count() <= second.chars().distinct().count() ? first : second)
                .ifPresent(System.out::println);
    }

    @Override
    public void task5(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        System.out.println(
                Arrays.stream(reader.readLine().split(" ", n))
                        .filter(s -> s.matches("^\\p{Alpha}+$"))
                        .filter(s ->
                                ((s.split("[aeiouy]", -1).length - 1) * 2) == s.length()
                        ).count()
        );
    }

    @Override
    public void task6(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        System.out.println(
                Arrays.stream(reader.readLine().split(" ", n))
                        .filter(s -> s.length() > 1)
                        .filter(s -> {
                            char buffer = 0;
                            for (char c : s.toCharArray() ) {
                                if(c <= buffer){
                                    return false;
                                }
                                buffer = c;
                            }
                            return true;
                        }).findFirst().orElse("NOT FOUND")
        );
    }

    @Override
    public void task7(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        List<String> stringList = Arrays.stream(reader.readLine().split(" ", n))
                .distinct()
                .filter(s -> s.chars().distinct().count() == s.length())
                .collect(Collectors.toList());
        if(stringList.isEmpty()){
            System.out.println("NOT FOUND");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringList.forEach(s -> stringBuilder.append(s).append(" "));
            stringBuilder.setLength(stringBuilder.length() - 1);
            System.out.println(stringBuilder);
        }
    }

    @Override
    public void task8(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        System.out.println(Arrays.stream(reader.readLine().split(" ", n))
                .filter(s -> s.matches("^\\d+$"))
                .filter(s ->{
                    char[] chars = s.toCharArray();
                    Stack<Character> stack = new Stack<>();
                    for(int i = 0; i < chars.length / 2; ++i){
                        stack.push(chars[i]);
                    }
                    for(int i = chars.length / 2 + chars.length % 2; i < chars.length; ++i){
                        char с = stack.pop();
                        if(с != chars[i]){
                            stack.push(с);
                        }
                    }
                    return stack.empty();
                }).limit(2).reduce((first, second) -> second).orElse("NOT FOUND")
        );
    }

    @Override
    public void task9(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i <= n*n; ++i){
            stringBuilder.append(i);
            stringBuilder.append(i % n == 0 ? "\n" :"\t");
        }
        System.out.print(stringBuilder);
    }

    @Override
    public void task10(){
        final Scanner scanner = new Scanner(System.in);
        final DecimalFormat df = new DecimalFormat("#0.##");
        final DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        final BigDecimal a = scanner.nextBigDecimal();
        final BigDecimal b = scanner.nextBigDecimal();
        final BigDecimal c = scanner.nextBigDecimal();
        final BigDecimal d = b.pow(2).subtract(a.multiply(c).multiply(BigDecimal.valueOf(4)));
        if(d.signum() < 0){
            System.out.println("No solution");
        } else if(d.signum() == 0){
            System.out.println("One solution: " +
                    df.format(b.negate().divide(a.multiply(BigDecimal.valueOf(2))))
                    );
        } else {
            BigDecimal sqrtD = BigDecimal.valueOf(StrictMath.sqrt(d.doubleValue()))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal minusB = b.negate();
            BigDecimal a2 = a.multiply(BigDecimal.valueOf(2));
            System.out.println("Two solutions: " +
                    df.format(minusB.subtract(sqrtD).divide(a2)) + ", " +
                    df.format(minusB.add(sqrtD).divide(a2))
                    );
        }
    }

    @Override
    public void task11(){
        final Reader reader = new Reader(System.in);
        int n;
        try{
            n = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException ex){
            n = 0;
        }
        String str;
        switch(n){
            case 1:
                str = "January";
                break;
            case 2:
                str = "February";
                break;
            case 3:
                str = "March";
                break;
            case 4:
                str = "April";
                break;
            case 5:
                str = "May";
                break;
            case 6:
                str = "June";
                break;
            case 7:
                str = "July";
                break;
            case 8:
                str = "August";
                break;
            case 9:
                str = "September";
                break;
            case 10:
                str = "October";
                break;
            case 11:
                str = "November";
                break;
            case 12:
                str = "December";
                break;
            default:
                str = "INCORRECT INPUT DATA";
        }
        System.out.println(str);
    }

    @Override
    public void task12(){
        final Scanner scanner = new Scanner(System.in);
        final int k = scanner.nextInt();
        int[][] matrix = readMatrix(scanner);
        Arrays.sort(matrix, Comparator.comparingInt(row -> row[k]));
        System.out.println(matrix.length);
        System.out.print(matrixToString(matrix));
    }

    @Override
    public void task13(){
        final Scanner scanner = new Scanner(System.in);
        final int k = scanner.nextInt();
        int[][] matrix = readMatrix(scanner);
        System.out.println(matrix.length);
        System.out.print(matrixToString(shiftRows(matrix, k)));
    }

    @Override
    public void task14(){
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        int maxCount = 0;
        int count = 0;
        int previousInt = scanner.nextInt();
        for (int i = 1; i < n; ++i){
            int currentInt = scanner.nextInt();
            if(currentInt > previousInt){
                if(++count > maxCount){
                    maxCount = count;
                }
            } else {
                count = 0;
            }
            previousInt = currentInt;
        }
        System.out.println(maxCount == 0 ? 0 : maxCount + 1);
    }

    @Override
    public void task15(){
        final Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        System.out.println(
                Arrays.stream(matrix).flatMapToInt(row -> {
                    int start = -1;
                    int end   = -1;
                    for(int i = 0; i < row.length; ++i){
                        if(row[i] > 0){
                            if(start == -1){
                                start = i;
                            } else if(end == -1){
                                end = i;
                            } else {
                                break;
                            }
                        }
                    }
                    if(start != -1 && end != -1 && end - start > 1){
                        return IntStream.of(Arrays.copyOfRange(row, start + 1, end));
                    } else {
                        return IntStream.empty();
                    }
                }).sum()
        );
    }

    @Override
    public void task16(){
        final Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        rotateMatrixLeft(matrix);
        System.out.println(matrix.length);
        System.out.print(matrixToString(matrix));
    }

    @Override
    public void task17(){
        final Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        Map<String, Long> determinantMap = new HashMap<>();
        System.out.println(determinant(matrix, determinantMap));
    }

    @Override
    public void task18(){
        final Scanner scanner = new Scanner(System.in);
        final int[][] matrix = readMatrix(scanner);
        final Set<Integer> invalidRows = new HashSet<>();
        final Set<Integer> invalidColumns = new HashSet<>();
        final int max = Stream.of(matrix).flatMapToInt(Arrays::stream).max().getAsInt();
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix.length; ++col) {
                if(matrix[row][col] == max){
                    invalidRows.add(row);
                    invalidColumns.add(col);
                }
            }
        }
        int[][] resultMatrix = deleteRowsAndColumns(invalidRows, invalidColumns, matrix);
        System.out.println(resultMatrix.length);
        System.out.println(resultMatrix[0].length);
        System.out.println(matrixToString(resultMatrix));
    }

    @Override
    public void task19(){
        final Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        Set<Integer> invalidRows = new HashSet<>();
        Set<Integer> invalidColumns = new HashSet<>();
        for (int row = 0; row < matrix.length; ++row) {
            if(IntStream.of(matrix[row]).map(Math::abs).sum() == 0){
                invalidRows.add(row);
            }
        }
        for (int col = 0; col < matrix.length; ++col){
            int sum = 0;
            for (int row = 0; row < matrix.length; ++row) {
                sum += Math.abs(matrix[row][col]);
            }
            if(sum == 0){
                invalidColumns.add(col);
            }
        }
        int[][] resultMatrix = deleteRowsAndColumns(invalidRows, invalidColumns, matrix);
        System.out.println(resultMatrix.length);
        System.out.println(resultMatrix[0].length);
        System.out.println(matrixToString(resultMatrix));
    }

    @Override
    public void task20(){
        final Scanner scanner = new Scanner(System.in);
        final int x2 = scanner.nextInt();
        final int y2 = scanner.nextInt();
        final int[][] matrix = readMatrix(scanner);
        int x1 = 0;
        int y1 = 0;
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix.length; ++col) {
                if(matrix[row][col] < matrix[x1][y1]){
                    x1 = row;
                    y1 = col;
                }
            }
        }
        replaceColumns(matrix, y1, y2);
        replaceRows(matrix, x1, x2);
        System.out.println(matrix.length);
        System.out.println(matrixToString(matrix));
    }

    @Override
    public void task21(){
        final Scanner scanner = new Scanner(System.in);
        final int[][] matrix = readMatrix(scanner);
        for (int row = 0; row < matrix.length; ++row) {
            matrix[row] = Arrays.stream(matrix[row]).boxed()
                    .sorted((Integer i1, Integer i2) -> {
                        if ((i1 == 0 && i2 == 0) || (i1 != 0 && i2 != 0)) {
                            return 0;
                        }
                        return i1 == 0 ? 1 : -1;
                    })
                    .mapToInt(i -> i).toArray();
        }
        System.out.println(matrix.length);
        System.out.print(matrixToString(matrix));
    }

    @Override
    public void task22(){
        final Scanner scanner = new Scanner(System.in);
        final double[][] matrix = readDoubleMatrix(scanner);
        int[][] intMatrix = new int[matrix.length][matrix.length];
        for (int row = 0; row < matrix.length; ++row) {
            intMatrix[row] = Arrays.stream(matrix[row]).mapToLong(Math::round).mapToInt(i -> (int)i).toArray();
        }
        System.out.println(intMatrix.length);
        System.out.print(matrixToString(intMatrix));
    }

    @Override
    public void task23(){
        final Scanner scanner = new Scanner(System.in);
        final int[][] matrix = readMatrix(scanner);
        List<Integer> minInRow = Stream.of(matrix).map(row -> IntStream.of(row).min().getAsInt())
                .collect(Collectors.toList());
        List<Integer> maxInCol = new ArrayList<>(matrix.length);
        for(int col = 0; col < matrix.length; ++col){
            int max = Integer.MIN_VALUE;
            for(int row = 0; row < matrix.length; ++row){
                if(matrix[row][col] > max){
                    max = matrix[row][col];
                }
            }
            maxInCol.add(max);
        }
        int count = 0;
        for(int col = 0; col < matrix.length; ++col){
            for(int row = 0; row < matrix.length; ++row){
                if(matrix[row][col] == minInRow.get(row) && matrix[row][col] == maxInCol.get(col)){
                    ++count;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    public void task24(){
        final Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        Arrays.sort(matrix, Comparator.comparingInt(row -> IntStream.of(row).sum()));
        System.out.println(matrix.length);
        System.out.print(matrixToString(matrix));
    }

    @Override
    public void task25(){
        final Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int[][] expandedMatrix = expandMatrix(matrix, Integer.MAX_VALUE);
        int count = 0;
        for (int row = 1; row < expandedMatrix.length - 1; ++row) {
            for (int col = 1; col < expandedMatrix.length - 1; ++col) {
                int value = expandedMatrix[row][col];
                if(value < expandedMatrix[row - 1][col - 1] && value < expandedMatrix[row - 1][col] &&
                        value < expandedMatrix[row - 1][col + 1] && value < expandedMatrix[row][col - 1] &&
                        value < expandedMatrix[row][col + 1] && value < expandedMatrix[row + 1][col - 1] &&
                        value < expandedMatrix[row + 1][col] && value < expandedMatrix[row + 1][col + 1]){
                    count ++;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    public void task26(){
        final Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int[][] expandedMatrix = expandMatrix(matrix, Integer.MIN_VALUE);
        List<Integer> localMins = new ArrayList<>();
        for (int row = 1; row < expandedMatrix.length - 1; ++row) {
            for (int col = 1; col < expandedMatrix.length - 1; ++col) {
                int value = expandedMatrix[row][col];
                if(value > expandedMatrix[row - 1][col - 1] && value > expandedMatrix[row - 1][col] &&
                        value > expandedMatrix[row - 1][col + 1] && value > expandedMatrix[row][col - 1] &&
                        value > expandedMatrix[row][col + 1] && value > expandedMatrix[row + 1][col - 1] &&
                        value > expandedMatrix[row + 1][col] && value > expandedMatrix[row + 1][col + 1]){
                    localMins.add(value);
                }
            }
        }
        System.out.println(
                localMins.stream().max(Integer::compareTo).map(Object::toString).orElse("NOT FOUND")
        );
    }

    @Override
    public void task27(){
        final Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        rotateMatrixLeft(matrix);
        Arrays.sort(matrix, Comparator.comparingInt(col -> IntStream.of(col).map(Math::abs).sum()));
        rotateMatrixRight(matrix);
        System.out.println(matrix.length);
        System.out.print(matrixToString(matrix));
    }

}
