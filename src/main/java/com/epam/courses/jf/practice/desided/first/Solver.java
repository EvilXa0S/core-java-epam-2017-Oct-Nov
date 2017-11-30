package com.epam.courses.jf.practice.desided.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solver implements ISolver {

    private String[] Reader(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        String[] strings = new String[n];

        for(int i = 0; i < n; i++){
            strings[i] = scanner.nextLine();
        }
        return strings;
    }

    public int[][] readMatrix(Scanner scanner){
        scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int matrix[][] = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public void writeMatrix(int matrix[][]){
        System.out.println(matrix.length);
        for (int row = 0; row < matrix.length; row++){
            for (int column = 0; column < matrix.length; column++){
                System.out.print(matrix[row][column] + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public void task1() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        TreeMap<Integer, String> treeMap = new TreeMap<>();

        if (N < 100 && N > 0){
            for (int i = 1; i <= N; i++){
                String str = in.next();
                int length = str.length();
                treeMap.put(length, str);
            }
        }
        else {
            System.out.println("Please enter correct value");
        }

        String minString = treeMap.firstEntry().getValue();
        String maxString = treeMap.lastEntry().getValue();
        int minLength = treeMap.firstKey();
        int maxLength = treeMap.lastKey();

        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }

    @Override
    public void task2() {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());

//        List<String> strings = new ArrayList<>();
        String[] strings = new String[N];
        if (N < 100 && N > 0){
            for (int i = 0; i < N; i++){
                strings[i] = in.nextLine();
//                System.out.println(strings[i]);
            }
        }
        else {
            System.out.println("Please enter correct value");
        }


        Collections.sort(Arrays.asList(strings), Comparator.comparing(String::length).thenComparing(String::compareTo));
        for(String s : strings){
            System.out.printf("(%d): \"%s\"%n", s.length(), s);
        }
    }

    @Override
    public void task3() {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        int sumLength = 0;
        String[] strings = new String[N];

        for (int i = 0; i < N; i++) {
            String inStr = in.nextLine();
            strings[i] = inStr;
            sumLength += inStr.length();
        }
        int averageLength = sumLength / N;
        System.out.printf("AVERAGE (%d)%n", averageLength);

        for (int i = 0; i < N; i++){
            if(strings[i].length() < averageLength){
                System.out.printf("(%d): \"%s\"%n", strings[i].length(), strings[i]);
            }
        }
    }

    @Override
    public void task4() {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        List<String> stringList;
        stringList = Arrays.asList(in.split(" "));
        Map<Integer, String> map = new LinkedHashMap<>();

        for (int i = 0; i < stringList.size(); i++) {
            String string = stringList.get(i);
            int strLength = string.length();
            int endIntValue = strLength;

            while (string.length() != 0){
                char charAt = string.charAt(0);
                int countChar = (int) string.
                        chars().
                        filter((ch) -> ch == charAt).
                        count();
                string = string.replaceAll(String.valueOf(charAt), "");

                if(countChar >= 2){
                    endIntValue = endIntValue - countChar + 1;
                }
            }
            map.put(endIntValue, stringList.get(i));
        }

        System.out.println(map.get(Collections.min(map.keySet())));
    }

    @Override
    public void task5() {
        String str = "Язык программирования Java is widespread aaasdd really";
        Pattern p = Pattern.compile("[a-zA-Z].*");
        Matcher m = p.matcher(str);
        String outStr = new String();

        if(m.find()){
            outStr = m.group(0);
        }

        List<String> stringList = Arrays.asList(outStr.split(" "));

        int numbsOfWords = 0;
        for (String strList : stringList){

            int vawelsInt = (int)strList.
                    chars().
                    mapToObj(i -> (char) i).
                    filter(c -> "aeiouy".contains(String.valueOf(c))).
                    count();

            if(strList.length() - vawelsInt == vawelsInt){
                numbsOfWords++;
            }
        }
        System.out.println(numbsOfWords);
    }

    @Override
    public void task6() {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());

        String stringOut = in.nextLine();
        List<String> stringList = Arrays.asList(stringOut.split(" "));

        for(String string : stringList) {

            List<Integer> list = string.chars().
                    mapToObj((i) -> i).
                    collect(Collectors.toList());

            List slist = list.stream().
                    filter(inx -> {
                        int buf = 0;

                        for (int i = 0; i < list.size(); i++) {

                            if (list.get(i) < buf) {
                                return false;
                            }
                            buf = list.get(i);
                        }
                        return true;
                    }).collect(Collectors.toList());


            if(!slist.isEmpty()){

                StringBuffer buf = new StringBuffer();

                for(int i = 0; i < slist.size(); i++){
                    buf.append((char) Integer.parseInt(slist.get(i).toString()));
                }
                System.out.println(buf);
                break;
            }

        }

    }

    @Override
    public void task7() {
        String string = "The Java programming language is a general-purpose, concurrent, class-based, object-oriented language";
        List<String> list = new ArrayList<>();
        list = Arrays.asList(string.split(" "));
        List<String> listOut = new ArrayList<>();
        for (String str : list){
            long i;
            boolean falseCount = true;

            for (int in = 0; in < str.length(); in++){
                int cha = (int)str.charAt(in);
                i = str.chars()
                        .filter(ch -> ch == cha)
                        .count();

                if(i > 1){
                    falseCount = false;
                    break;
                }
            }
            if (falseCount == true){
                listOut.add(str);
            }
        }
        String strOut = String.join(" ", listOut);
        System.out.println(strOut);
    }

    @Override
    public void task8() {
        String string = "Chapter 11 describes exceptions, chapter 13 describes binary compatibility... chapter 22 presents a syntactic grammar 1";
        Pattern pattern = Pattern.compile("\\d.*?\\b");
        Matcher matcher = pattern.matcher(string);
        List<String> arr = new ArrayList<>();
        List<String> arrOut = new ArrayList<>();

        while (matcher.find()) {
            arr.add(matcher.group());
        }

        while (arr.size() != 0) {
            char[] strings = arr.get(0).toCharArray();
            Stream<Character> stream = IntStream
                    .range(0, strings.length)
                    .mapToObj(i -> strings[i]);

            List<Character> listt = stream.collect(Collectors.toList());
            Deque<Character> stack = new LinkedList<>(listt);

            boolean check = true;
            while (stack.size() != 0 && stack.size() != 1) {
                if (stack.peekFirst() == stack.peekLast()) {
                    stack.removeFirst();
                    stack.removeLast();
                    System.out.println("true");
                } else {
                    check = false;
                    break;
                }
            }

            if (check == true) {
                arrOut.add(arr.get(0));
            }
            arr.remove(0);
        }
        if (arrOut.size() == 1) {
            System.out.println(arrOut.get(0));
        }
        else if (arrOut.size() > 1){
            System.out.println(arrOut.get(1));
        }
        else System.out.println("NOT FOUND");
    }

    @Override
    public void task9() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] res = new int[n][n];
        int i = 1;

        for (int row = 0; row < n; row++){
            for (int column = 0; column < n; column++){
                res[row][column] = i;
//                System.out.print(i);
                i++;
            }
        }
    }

    @Override
    public void task10() {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();

        double D = B * B - 4 * A * C;

        if(D > 0){
            BigDecimal x1 = new BigDecimal((-B + Math.sqrt(D)) / (2 * A));
            BigDecimal x2 = new BigDecimal((-B - Math.sqrt(D)) / (2 * A));
            System.out.println("Two solutions: " + x1.setScale(2, RoundingMode.HALF_UP)
                    + ", " + x2.setScale(2, RoundingMode.HALF_UP));
        }
        else if (D == 0){
            BigDecimal x1 = new BigDecimal((-B / (2 * A)));
            System.out.println("One solution: " + x1.setScale(2, RoundingMode.HALF_UP));
        }
        else if (D < 0){
            System.out.println("No solutions");
        }
    }

    @Override
    public void task11() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String month;

        if (n < 13 && n > 0){
            switch (n){
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
                    break;
            }
        }
        else System.out.println("INCORRECT INPUT DATA");
    }

    @Override
    public void task12() {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        readMatrix(scanner);
        int[][] matrix = readMatrix(scanner);

        Arrays.sort(matrix, Comparator.comparingInt(rows -> rows[k]));
        writeMatrix(matrix);

    }

    @Override
    public void task13() {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        
        int[][] matrix = readMatrix(scanner);
        int[][] outMatrix = new int[matrix.length][];

        for (int i = 0; i < outMatrix.length; i++) {
            int shift = Math.abs((i - k) % outMatrix.length);

            if(shift < 0) {
                shift = shift + outMatrix.length;
            }
            outMatrix[i] = matrix[shift];
        }

        writeMatrix(outMatrix);
    }

    @Override
    public void task14() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] nums = new int[N];

        for (int i = 0; i < N; i++){
            nums[i] = scanner.nextInt();
        }

        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        for (int i = 1; i < nums.length; i++){

            if(nums[i - 1] < nums[i]){
                count++;
            }

            else {
                integerList.add(count);
                count = 1;
            }

        }
        integerList.add(count);
        System.out.println(Collections.max(integerList));
    }

    @Override
    public void task15() {
        Scanner scanner = new Scanner(System.in);
        
        int[][] matrix = readMatrix(scanner);
        int[][] result = new int[matrix.length][matrix.length];

//        [0][0]  [0][1]  [0][2]  |   [0][2]  [1][2]  [2][2]
//        [1][0]  [1][1]  [1][2]  |   [0][1]  [1][1]  [2][1]
//        [2][0]  [2][1]  [2][2]  |   [0][0]  [1][0]  [2][0]

        System.out.println(matrix.length);
        for (int i = 0; i < matrix.length; i++){
            int column = matrix.length - 1;

            for (int x = 0; x < matrix.length; x++){
                result[column--][i] = matrix[i][x];
                System.out.println(column + 1);
            }
        }
        writeMatrix(result);
    }

    @Override
    public void task16() {
        Scanner scanner = new Scanner(System.in);
        
        int[][] matrix = readMatrix(scanner);
        int[][] result = new int[matrix.length][matrix.length];

        int a = 0;
        int b = 0;
        int max = 0;

        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++){

                if (matrix[i][x] > max){
                    max = matrix[i][x];
                    a = i;
                    b = x;
                }
            }
        }
        System.out.println(max + " " + a + " "+ b);
        int outM = matrix.length;
        int outN = matrix.length;

        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++){
                if(i == a){
                    outM--;
                    break;
                }
                if(x == b){
                    outN--;
                    break;
                }

            }
        }
        System.out.println(outM + " " + outN);
    }

    @Override
    public void task17() {
        
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);

        int D = 0;

        for (int i = 0; i < matrix.length; i++){
            int N = i;
            int sum = 1;

            for (int z = 0; z < matrix.length; z++) {
                int num = matrix[N][z];
                sum = num * sum;
                N++;

                if (N >= matrix.length){
                    N = N - matrix.length;
                }
            }
            D += sum;
        }

        for (int i = 0; i < matrix.length; i++){
            int N = i;
            int M = matrix.length - 1;
            int sum = 1;

            for (int z = 0; z < matrix.length; z++) {
                int num = matrix[N][M];
                sum = num * sum;
                N++;
                M--;

                if (N >= matrix.length){
                    N = N - matrix.length;
                }
            }
            D -= sum;
        }

        System.out.println(D);
    }

    @Override
    public void task18() {
        
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);

        int max = matrix[0][0];
        Set<Integer> rows = new HashSet<>();
        Set<Integer> columns = new HashSet<>();

        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++){
                if(max < matrix[i][x]){
                    max = matrix[i][x];
                }
            }
        }

        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++) {
                if(matrix[i][x] == max){
                    rows.add(i);
                    columns.add(x);
                }
            }
        }

        int[][] outMatrix = new int[matrix.length - rows.size()][matrix.length - columns.size()];

        for (int i = 0; i < matrix.length; i++){
            int N = 0;
            int M = 0;
            for (int x = 0; x < matrix.length; x++) {

                if(!rows.contains(i) && !columns.contains(x)) {
                    System.out.println(matrix[i][x]);
                    outMatrix[N][M] = matrix[i][x];
                    M++;
                }
            }
            N++;
        }
    }

    @Override
    public void task19() {
        
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        Map<Integer, Integer> mapRows = new HashMap<>();
        Map<Integer, Integer> mapColumns = new HashMap<>();

        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++){

                if (matrix[i][x] == 0){
                    if (mapRows.containsKey(0)){
                        mapRows.put(i, x);
                    }
                    else if (mapColumns.containsValue(0)){
                        mapColumns.put(x, i);
                    }
                    mapRows.put(i, x);
                    mapColumns.put(x, i);

                }
            }
        }

        System.out.println(" ");
        for (Map.Entry i : mapRows.entrySet()){
            System.out.println(i);
        }
    }

    @Override
    public void task20() {
        
        Scanner scanner = new Scanner(System.in);
        int X = scanner.nextInt();
        int Y = scanner.nextInt();
        int[][] matrix = readMatrix(scanner);

        int min = matrix[0][0];
        int N = 0;
        int M = 0;

        for (int i = 0; i < matrix.length; i++){
            for (int z = 0; z < matrix.length; z++){
                if (min > matrix[i][z]){
                    min = matrix[i][z];
                    N = i;
                    M = z;
                }
            }
        }

        int[] swap = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++){
            if (i == N){
                swap = matrix[i];
                matrix[i] = matrix[X];
                matrix[X] = swap;
            }
        }

        List<Integer> swapList = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++){
            for (int z = 0; z < matrix.length; z++) {

                if (z == M && i == N) {
                    for (int innerI = 0; innerI < matrix.length; innerI++) {
                        swapList.add(matrix[innerI][z]);
                        matrix[innerI][z] = matrix[innerI][Y];
                    }

                    for (int innerI = 0; innerI < matrix.length; innerI++) {
                        matrix[innerI][Y] = swapList.get(innerI);
                    }
                }
            }
        }

        writeMatrix(matrix);
    }

    @Override
    public void task21() {
        
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);

        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++){

                if(matrix[i][x] == 0 && x != matrix.length - 1){
                    for (int x1 = x; x1 < matrix.length; x1++){

                        if (matrix[i][x1] != 0){
                            int swap = matrix[i][x];
                            matrix[i][x] = matrix[i][x1];
                            matrix[i][x1] = swap;
                            break;
                        }
                    }
                }
            }
        }
        writeMatrix(matrix);
    }

    @Override
    public void task22() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.FRANCE);
        int n = scanner.nextInt();
        double[][] matrix = new double[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = (scanner.nextDouble());
            }
        }

        int[][] result = new int[n][n];
        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++){
                result[i][x] = (int) Math.round(matrix[i][x]);
            }
        }
        
        writeMatrix(result);
    }

    @Override
    public void task23() {
        
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int count = 0;
        int N = 0;
        int M = 0;

        /* for 1 element **/
        for (int i = 0; i < matrix.length; i++){
            int min = matrix[i][0];
            for (int x = 0; x < matrix.length; x++){

                if (matrix[i][x] <= min){
                    min = matrix[i][x];
                    N = i;
                    M = x;
                }
            }

            for (int z = 0; z < matrix.length; z++){
                int max = min;

                if (max < matrix[z][M]){
                    break;
                }
                System.out.println(matrix[z][M]);
            }
            count++;
        }
        count = count / (matrix.length - 1);

        System.out.println(count);
    }

    @Override
    public void task24() {
        
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);


        List<Integer> sumArr = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < matrix.length; i++){
            int sum = 0;

            for (int x = 0; x < matrix.length; x++){
                sum += matrix[i][x];
            }
            map.put(sum, i);
        }

        Iterator iter = map.entrySet().iterator();
        int[][] result = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            Map.Entry entry = (Map.Entry) iter.next();
            result[i] = matrix[(int)entry.getValue()];
        }
        writeMatrix(result);
    }

    @Override
    public void task25() {
        

        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);

        int count = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++){
                System.out.println(" ");

                boolean catchBreak = true;
                int rowDecrease = i + 2;
                int columnDecrease = x + 2;
                int innX = x - 1;
                int innI = i - 1;

                for (int inni = innI; inni < rowDecrease; inni++) {
                    for (int innx = innX; innx < columnDecrease; innx++) {
                        try {

                            if (inni != i || innx != x) {
                                if (matrix[inni][innx] <= matrix[i][x]) {
                                    catchBreak = false;
                                    break;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException ex){
                        }
                    }

                    if (!catchBreak){
                        break;
                    }
                }

                if(catchBreak){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    public void task26() {
        

        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);

        int N = 0;
        int M = 0;

        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++){

                boolean catchBreak = true;
                int rowDecrease = i + 2;
                int columnDecrease = x + 2;
                int innX = x - 1;
                int innI = i - 1;

                for (int inni = innI; inni < rowDecrease; inni++) {
                    for (int innx = innX; innx < columnDecrease; innx++) {
                        try {

                            if (inni != i || innx != x) {
                                if (matrix[inni][innx] >= matrix[i][x]) {
                                    catchBreak = false;
                                    break;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException ex){
                        }
                    }

                    if (!catchBreak){
                        break;
                    }
                }

                if(catchBreak){
                    N = i;
                    M = x;
                }
            }
        }
        System.out.println(matrix[N][M]);
    }

    @Override
    public void task27() {
        
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);


        List<Integer> sumArr = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < matrix.length; i++){
            int sum = 0;

            for (int x = 0; x < matrix.length; x++){
                sum += Math.abs(matrix[x][i]);
            }
            map.put(sum, i);
        }

        Iterator iter = map.entrySet().iterator();
        int[][] result = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++){
            Map.Entry entry = (Map.Entry) iter.next();
            for (int x = 0; x < matrix.length; x++) {

                result[x][i] = matrix[x][(int) entry.getValue()];
            }
        }
        writeMatrix(result);
    }
}