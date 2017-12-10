package com.epam.courses.jf.practice.NikDevyatyi.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Month;
import java.util.*;
import java.util.stream.IntStream;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class Solver implements ISolver {
    @Override
    public void task1() {

        String tempSmall ="";
        String tempBig ="";
        Scanner scanner = new Scanner(System.in);
        String[] strings = getStrings(scanner);
        tempSmall = strings[0];
        tempBig = strings[0];
        for (String str: strings){

            if(str.length()<= tempSmall.length()){
                tempSmall = str;
            }
            if(str.length()>= tempBig.length()){
                tempBig = str;
            }
        }
        System.out.printf("MIN (%d): \"%s\"%n", tempSmall.length(), tempSmall);
        System.out.printf("MAX (%d): \"%s\"%n", tempBig.length(), tempBig);


    }
    @Override
    public void task2() {
        Scanner sc = new Scanner(System.in);
        String arrStr [] = getStrings(sc);
        Arrays.sort(arrStr,Comparator.comparingInt(String::length)
                .thenComparing(String::compareTo));

        for(String str: arrStr){
            System.out.printf("(%d): \"%s\"%n", str.length(), str);

        }

    }


    /***

     * @param scanner
     * @return String[]
     * <p>getStrings() method to get Strins from System.in()</p>
     */
    private String[] getStrings(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());

        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = scanner.nextLine();
        }
        return strings;
    }
    private String[] getStrings2(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());

        String[] strings = new String[n];
        strings = scanner.nextLine().split(" ");
        return strings;
    }
    @Override
    public void task3() {
        int summ = 0;
        Scanner sc = new Scanner(System.in);
        String arrStr[] = getStrings(sc);

        for (String str : arrStr) {
            summ+= str.length();
        }
        System.out.printf("AVERAGE (%d)%n", summ/arrStr.length);

        for (String str : arrStr){
            if (str.length() < summ/arrStr.length){
                System.out.printf("(%d): \"%s\"%n",str.length(),str);
            }
        }
    }
    @Override
    public  void task4(){
        Scanner sc = new Scanner(System.in);
        String[] s = getStrings2(sc);
        String result = s[0];
        int checkRepeat = checkLetterRepeat(s[0]);

        for(String word: s){
            if(word.length() == 1){
                result= word;
                break;
            }

            else if(checkRepeat > checkLetterRepeat(word)  ){
                result = word;
                checkRepeat = checkLetterRepeat(word);
            }

        }
        System.out.println(result);
    }

    /***

     * @param nameString
     * @return int
     *<p> checkLetterRepeat method to counting how many times letters repeated in word for task5()</p>
     */
    private static int checkLetterRepeat(String nameString){
        int result= 0;
        char[] symbols = nameString.toCharArray();

        int maxChar = (int)symbols[0];
        for(char c : symbols) {
            if (maxChar < (int)c) {
                maxChar = (int)c;
            }
        }
        int[] code = new int[maxChar+1];
        Arrays.fill(code, 0);
        for (char m : symbols) {
            if(code[(int)m]==0){
                code[(int)m]++;
            }
        }
        for(int item : code){
            result+= item;
        }
        return result;
    }
    @Override
    public  void task5(){
        Scanner sc = new Scanner(System.in);
        String[] arrStr = getStrings2(sc);

        int vowelsInWord = 0;
        int counter =0;
        for(String word: arrStr){
            if(word.length() % 2 == 0 && word.matches("[a-zA-Z]+")){
                for(char c : word.toLowerCase().toCharArray()){
                    if(Character.toString(c).matches("[aeiouy]")){
                        vowelsInWord++;
                    }
                }

                if(word.length()/2 == vowelsInWord){
                    counter++;
                }
                vowelsInWord = 0;
            }
        }
        System.out.println(counter);
    }
    /***

     * @param nameString
     * @return boolean
     * <p>checkTask6() checkTask6 is  method for task6() to search charsiquenceis like in condition of tsak6 or not</p>
     */

    public static  boolean checkTask6(String nameString) {
        char[] symbols = nameString.toLowerCase().toCharArray();
        int pastChar = symbols[0];
        if(symbols.length>1){
            for(char c : Arrays.copyOfRange( symbols, 1,symbols.length )) {
                if (pastChar < (int)c) {
                    pastChar = (int)c;
                }else{
                    return false;
                }
            }
        }
        else{
            return false;
        }
        return true;

    }
    @Override
    public void task6(){
        String result = "NOT FOUND";
        Scanner sc = new Scanner(System.in);
        String[] arrStr = getStrings2(sc);
        for(String word :arrStr){
            if(checkTask6(word) == true){
                result = word;
                break;

            }
        }
        System.out.println(result);
    }
    /***
     * @param nameString
     * @return boolean
     * <p>checkTask7() checkTask7 is  method for task7() to search charsiquenceis like in condition of tsak or not</p>
     */

    public static  boolean checkTask7(String nameString) {
        char[] symbols = nameString.toCharArray();
        int pastChar = symbols[0];
        char range[] = Arrays.copyOfRange( symbols, 1,symbols.length);
        Arrays.sort(range);
        if(symbols.length>1){
            for(char c : range) {
                if (pastChar == (int)c) {
                    return false;
                }
                pastChar = (int)c;
            }
        }
        return true;

    }

    @Override
    public void task7() {
        Scanner sc = new Scanner(System.in);
        String[] arrStr = getStrings2(sc);
        String tmp  = "";
        List<String> res = new ArrayList<>();
        for(String word :arrStr){
            if(checkTask7(word) == true){
                if(!tmp.equals(word)){
                    res.add(word);
                    tmp = word;
                }
            }
        }
        StringBuilder printResult = new StringBuilder();
        for(String item: res){
            if(item!=null){
                printResult.append(item + " ");
            }

        }
        if(printResult.length()!=0){
            System.out.println(printResult.toString().trim());
        }
        else System.out.println("NOT FOUND");

    }
    @Override
    public   void task8(){
        Scanner sc = new Scanner(System.in);
        String[] arrStr = getStrings2(sc);
        List<BigInteger> result = new ArrayList<BigInteger>();

        for(String word: arrStr) {
            if (word.matches("[0-9]+")) {

                if (word.equals(reverseIt(word))) {
                    result.add(new BigInteger(word));

                }
            }

        }
        if(result.size()>1){
            System.out.println(result.get(1));
        }
        else if(result.size() == 1) System.out.println(result.get(0));
        else System.out.println("NOT FOUND");
    }

    /***
     *
     * @param source
     * @return String
     * <p>reverseIt  method to reverse  String used in task8()</p>
     */

    public static String reverseIt(String source) {
        int i, len = source.length();
        StringBuilder dest = new StringBuilder(len);

        for (i = (len - 1); i >= 0; i--){
            dest.append(source.charAt(i));
        }

        return dest.toString();
    }
    @Override
    public void task9() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StringBuilder row = new StringBuilder();
        for(int i =1; i <= n; i++){
            for(int j =i*n+1-n; j < i*n+1; j++){
                row.append(j);
                row.append('\t');
            }
            System.out.println(row.toString().trim());
            row.setLength(0);
        }
    }
    @Override
    public void task10() {
        Scanner scanner = new Scanner(System.in);
        final int A = scanner.nextInt();
        final int B = scanner.nextInt();
        final int C = scanner.nextInt();
        final double D = B * B - 4 * A * C;
        double x1 =0;
        double x2 = 0;
        StringBuilder result = new StringBuilder();
        if(D > 0){
            x1 = (-B + Math.sqrt(D))/(2*A);
            x2 = (-B - Math.sqrt(D))/(2*A);
            //BigDecimal res = new BigDecimal(x1);
            result.append("Two solutions: ");
            result.append(new BigDecimal(x1).setScale(2, ROUND_HALF_UP));
            result.append(", ");
            result.append(new BigDecimal(x2).setScale(2, ROUND_HALF_UP));

        }
        else if(D == 0){
            x1 = -(double) B /(2*A);
            result.append("One solution: ");
            result.append(new BigDecimal(x1).setScale(2, ROUND_HALF_UP));

        }else {
            result.append("No solution");

        }
        System.out.println(result.toString());
    }
    @Override
    public void task11() {
        Scanner scanner = new Scanner(System.in);
        try {
            int month = scanner.nextInt();
            if (month<=12&&month>0) {
                //System.out.println(new StringBuilder(Month.of(month).name().toLowerCase()));
                System.out.println(Month.of(month).name().substring(0,1).toUpperCase() + Month.of(month).name().substring(1).toLowerCase());
            }
            else throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("INCORRECT INPUT DATA");
        }
    }

    /***
     *
     * @param scanner
     * @retur int[][]
     * <p>readMatrix  method to parse int from System.in and make from it square matrix int[][]/p>
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
    @Override
    public void task12() {
        Scanner scanner = new Scanner(System.in);
        int k =  scanner.nextInt();
        final int DIMENSION = scanner.nextInt();
        int[][] matrix = new int[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextInt();
            }
        }

        Arrays.sort(matrix, Comparator.comparing((int[] arr) -> arr[k]));
        //System.out.println(DIMENSION);
        printMatrixrRows(matrix);

    }

    /***
     *
     * @param matrix
     * <p>printRows method to print in System.out formated rows gets from matrix  used in a many tasks</p>
     */
    private void printMatrixrRows(int[][] matrix){
        StringBuilder result = new StringBuilder();
        int size = matrix.length;
        System.out.println(size);
        for(int[] row : matrix) {
            for (int item : row) {
                result.append(item);
                result.append('\t');
            }
            System.out.println(result.toString().trim());
            result.setLength(0);
        }
    }

    /***
     *
     * @param row
     * <p>printRows method to print in System.out formated row from array row used in task13</p>
     */
    private void printRows(int[] row){
        StringBuilder result = new StringBuilder();
        for (int item : row) {
            result.append(item);
            result.append('\t');
        }
        System.out.println(result.toString().trim());
    }
    @Override
    public void task13() {
        Scanner scanner = new Scanner(System.in);
        int k =  scanner.nextInt();
        final int DIMENSION = scanner.nextInt();
        int[][] matrix = new int[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextInt();
            }
        }
        if(k>=0){
            if(Math.abs(k)> matrix[0].length){
                k = k%matrix[0].length;
            }
            System.out.println(matrix[0].length);
            IntStream.range(matrix[0].length-k, matrix[0].length).forEach(
                    n -> {
                        printRows(matrix[n]);
                    }
            );
            IntStream.range(0, matrix[0].length-k).forEach(
                    n -> {
                        printRows(matrix[n]);
                    }
            );
        }
        if(k<0){
            if(Math.abs(k)> matrix[0].length){
                k = k%matrix[0].length;
            }
            System.out.println(matrix[0].length);
            IntStream.range(-k, matrix[0].length).forEach(
                    n -> {
                        printRows(matrix[n]);
                    }
            );
            IntStream.range(0, -k).forEach(
                    n -> {
                        printRows(matrix[n]);
                    }
            );
        }
    }
    @Override
    public void task14() {
        int count = 0;
        int max = 0;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int [] arr = new int[n];
        for(int i = 0; i<n;i++){
            arr[i] = scanner.nextInt();
        }
        int first = arr[0];
        int second  = 0;
        for(int i = 1; i< arr.length;i++){
            second = arr[i];
            if(second > first){
                count++;
                if(max<count){
                    max = count;
                }

            }
            else {
                count = 0;
            }
            first = second;
        }if(max == 0){
            System.out.println(max);
        }
        else {
            System.out.println(max + 1);
        }
    }
/*checked tasks1-14*/
    /***
     *
     * @param row
     * @return int
     * <p>findMiddle  method used in task15 for searching first pair of two positive neighbors</p>
     */
    private int findMiddle(int[] row){
        boolean first =false;
        int result = 0;
        for(int i = 0; i< row.length; i++){
            if((row[i] > 0) && i < (row.length-1) && first == false){
                first = true;
                if(row[i+1]>0){
                    return 0;
                }
            }
            if(first == true && i < (row.length - 1)){
                if(row[i+1] > 0){
                    return result;
                }
                result+=row[i+1];
            }

        }
        return 0;
    }
    public void task15() {
        int sum =0;
        Scanner scanner = new Scanner(System.in);
        int[][] matrix  = readMatrix(scanner);
        for(int[] row: matrix){
            sum += findMiddle(row);
        }
        System.out.println(sum);
    }
    public void task16 () {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix  = readMatrix(scanner);
        int width = matrix.length;
        int height = matrix[0].length;

        int[][] matrix_new = new int[height][width];


        for (int x = 0; x <width; x++) {
            for (int y = height-1; y >=0; y--) {
                matrix_new[y][x] = matrix[x][y];
            }
        }
        System.out.println(width);
        for(int i = width-1; i>=0;i--){
            printRows(matrix_new[i]);//change matrix rows from []int to string of values
        }

    }
    /***
     *
     * @param matrix
     * @param size
     * @param row
     * @param col
     * @param newMatrix
     * @return int[][]
     * <p>getMatrixWithoutRowAndCol method to calculate minors in matrix</p>
     */
    private int[][] getMatrixWithoutRowAndCol(int [][]matrix, int size, int row, int col, int [][]newMatrix) {
        int offsetRow = 0; //Смещение индекса строки в матрице
        int offsetCol = 0; //Смещение индекса столбца в матрице
        for(int i = 0; i < size-1; i++) {
            //Пропустить row-ую строку
            if(i == row) {
                offsetRow = 1; //Как только встретили строку, которую надо пропустить, делаем смещение для исходной матрицы
            }

            offsetCol = 0; //Обнулить смещение столбца
            for(int j = 0; j < size-1; j++) {
                if(j == col) {
                    offsetCol = 1; //Встретили нужный столбец, проускаем его смещением
                }

                newMatrix[i][j] = matrix[i + offsetRow][j + offsetCol];
            }

        }
        return newMatrix;
    }
    /*checked tasks1-17*/
    @Override
    public void task17() {
        Scanner scanner =  new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int size = matrix.length;
        System.out.println(matrixDet(matrix,size));
    }

    /***
     *
     * @param matrix
     * @param size
     * @return int
     * <p>matrixDet calculate determinant of matrix</p>
     */
    private int matrixDet(int [][]matrix,int size) {
        int det = 0;
        int degree = 1;
        if(size == 1) {
            return matrix[0][0];
        }
        else if(size == 2) {
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }
        else {
            int [][]newMatrix = new int[size-1][size-1];
            for(int i = 0; i < size-1; i++) {
                newMatrix[i] = new int[size-1];
            }

            for(int j = 0; j < size; j++) {
                getMatrixWithoutRowAndCol(matrix, size, 0, j, newMatrix);
                det = det + (degree * matrix[0][j]* matrixDet(newMatrix, size-1));

                degree = -degree;
            }

            for(int i = 0; i < size-1; i++) {

            }

        }

        return det;
    }
    @Override
    public void task18() {
        Scanner scanner =  new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);

        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            row.add(i);
            col.add(i);
        }

        int max = matrix[0][0];
        for (int[] element : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                if (max < element[j]) {
                    max = element[j];
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == max) {
                    if(!row.isEmpty()){
                        row.remove(Integer.valueOf(i));
                    }
                    if(!col.isEmpty()){
                        col.remove(Integer.valueOf(j));
                    }

                }
            }
        }

        System.out.println(row.size());
        System.out.println(col.size());
        for (Integer r : row) {
            for (Integer c : col) {
                System.out.printf("%d\t", matrix[r][c]);
            }
            System.out.println();
        }


    }
    @Override
    public void task19() {
        Scanner scanner =  new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int count = 0;

        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {

            for(int j =0; j < matrix.length; j++){
                if (matrix[i][j]==0){
                    count++;
                }
            }
            if(count!=matrix.length){
                row.add(i);
            }
            count = 0;
        }
        count = 0;
        for (int j = 0;j < matrix[0].length;j++) {

            for(Integer index : row){
                if (matrix[index][j]==0){
                    count++;
                }
            }

            if(count!=row.size()){
                col.add(j);
            }
            count = 0;
        }
        System.out.println(row.size());
        System.out.println(col.size());

        for (Integer r : row) {
            for (Integer c : col) {
                System.out.printf("%d\t", matrix[r][c]);
            }
            System.out.println();
        }
    }
    /***
     *
     * @param matrix
     * @return int[][]
     * <p> trasposeMatrix method to transpose matrix for task27</p>
     */
    public  int[][] trasposeMatrix(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] trasposedMatrix = new int[n][m];

        for(int x = 0; x < n; x++)
        {
            for(int y = 0; y < m; y++)
            {
                trasposedMatrix[x][y] = matrix[y][x];
            }
        }

        return trasposedMatrix;

    }
    @Override
    public void task20() {
        Scanner scanner =  new Scanner(System.in);
        int n1  = scanner.nextInt();
        int m1 = scanner.nextInt();
        int[][] matrix = readMatrix(scanner);
        int minIndRow = 0;
        int minIndColl =0;
        int min = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length; j++) {
                if (min > matrix[i][j]) {
                    min = matrix[i][j];
                    minIndRow = i;
                    minIndColl = j;

                }
            }
        }
        int[] temp = matrix[minIndRow];
        matrix[minIndRow] = matrix[n1];
        matrix[n1] = temp;
        int[][] tr_matrix = trasposeMatrix(matrix);
        temp = tr_matrix[minIndColl];
        tr_matrix[minIndColl] = tr_matrix[m1];
        tr_matrix[m1] = temp;
        printMatrixrRows(trasposeMatrix(tr_matrix));
    }
    @Override
    public void task21() {
        Scanner scanner =  new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int size = matrix.length;
        int count = 0;
        for(int[] row: matrix){
            count =0;
            for(int i=0; i <size; i++){
                if(row[i] != 0){
                    row[count] = row[i];
                    count++;
                }
            }
            for (int i = count; i< size;i++){
                row[i] = 0;
            }
        }
        printMatrixrRows(matrix);

    }
    /***
     *
     * @param scanner
     * @return double[][]
     * <p>readDoubleMatrix from System.in gets int and make matrix of double for task22</p>
     */
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
    @Override
    public void task22() {
        Scanner scanner =  new Scanner(System.in);
        double[][] matrix = readDoubleMatrix(scanner);
        int size = matrix.length;
        int [][] buffer = new int[size][size];

        for(int row = 0; row< size; row++){
            for(int i=0; i <size; i++){
                buffer[row][i] = (int)Math.round(matrix[row][i]);

            }
        }

        printMatrixrRows(buffer);

    }
    @Override
    public void task23() {
        Scanner scanner =  new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int size = matrix.length;
        int count = 0;
        int max;
        int min;
        int colIndex =0;
        int rawIndex = 0;
        for(int i= 0; i< size;i++){

            min = Integer.MAX_VALUE;
            for(int j=0; j <size; j++){
                if(matrix[i][j] <= min){

                    min = matrix[i][j];
                    colIndex = j;
                }
            }
            max = Integer.MIN_VALUE;
            for (int k = 0; k< size;k++) {
                if (matrix[k][colIndex] >= max) {
                    max = matrix[k][colIndex];
                    rawIndex = k;
                }
            }
            if(i == rawIndex){
                count++;
            }
        }
        System.out.println(count);
    }

    /***
     *
     * @param arr
     * @return
     * <p>summ method to calculate summ of items some array for task24</p>
     */
    private int summ(int[] arr){
        int result = 0;
        for(int item: arr){
            result+=item;
        }
        return  result;
    }

    /*checked tasks1-24*/
    @Override
    public void task24() {
        Scanner scanner =  new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int size = matrix.length;
        int [] temp = new int[size];
        for(int k = 0;k<size;k++){
            for(int i= 0; i <size-1;i++){
                if(summ(matrix[i]) > summ(matrix[i+1])){
                    temp = matrix[i];
                    matrix[i] = matrix[i+1];
                    matrix[i+1] = temp;
                }
            }
        }
        printMatrixrRows(matrix);
    }
    /***
     *
     * @param scanner
     * @return int[][]
     * <p>readBigMatrixfrom System.in gets int and make matrix of int inside frame consist of Integer.MAX_VALUE  for task25<p/>
     */
    private int[][] readBigMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        int[][] matrix = new int[DIMENSION + 2][DIMENSION + 2];
        for (int row = 0; row < DIMENSION+2; ++row) {
            for (int col = 0; col < DIMENSION+2; ++col) {
                if(row == 0 || row == DIMENSION+1){
                    matrix[row][col] = Integer.MAX_VALUE;
                }
                else if ((col == 0 || col ==DIMENSION+1)&&!(row == 0 || row == DIMENSION+1)){
                    matrix[row][col] = Integer.MAX_VALUE;
                }
                else{
                    matrix[row][col] = scanner.nextInt();
                }

            }
        }
        return matrix;
    }
    /*checked tasks1-25*/
    @Override
    public void task25() {
        Scanner scanner =  new Scanner(System.in);
        int[][] matrix = readBigMatrix(scanner);
        int size = matrix.length;
        int counter = 0;
        for(int i =1; i < size -1; i++){
            for(int j =1; j < size -1; j++){
                int [] buffer = {
                        matrix[i-1][j], matrix[i + 1][j],
                        matrix[i-1][j-1], matrix[i][j-1],
                        matrix[i+1][j-1], matrix[i-1][j+1],
                        matrix[i][j+1], matrix[i + 1][j +1 ]
                };
                Arrays.sort(buffer);
                if(buffer[0]> matrix[i][j]){
                    counter++;
                }
            }

        }
        System.out.println(counter);

    }
    /***
     * @param scanner
     * @return int[][]
     *  <p> readSmallMatrix  from System.in gets int and make matrix of int inside frame consist of Integer.MIN_VALUE  for task26</p>
     */
    private int[][] readSmallMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        int[][] matrix = new int[DIMENSION + 2][DIMENSION + 2];
        for (int row = 0; row < DIMENSION+2; ++row) {
            for (int col = 0; col < DIMENSION+2; ++col) {
                if(row == 0 || row == DIMENSION+1){
                    matrix[row][col] = Integer.MIN_VALUE;
                }
                else if ((col == 0 || col ==DIMENSION+1)&&!(row == 0 || row == DIMENSION+1)){
                    matrix[row][col] = Integer.MIN_VALUE;
                }
                else{
                    matrix[row][col] = scanner.nextInt();
                }

            }
        }
        return matrix;
    }
    /*checked tasks1-26*/
    @Override
    public void task26() {
        Scanner scanner =  new Scanner(System.in);
        int[][] matrix = readSmallMatrix(scanner);
        int size = matrix.length;
        int counter = 0;
        List<Integer> result = new ArrayList<>();
        for(int i =1; i < size -1; i++){
            for(int j =1; j < size -1; j++){
                int [] buffer = {
                        matrix[i-1][j], matrix[i + 1][j],
                        matrix[i-1][j-1], matrix[i][j-1],
                        matrix[i+1][j-1], matrix[i-1][j+1],
                        matrix[i][j+1], matrix[i + 1][j +1 ]
                };
                Arrays.sort(buffer);
                if(buffer[buffer.length-1]< matrix[i][j]){
                    result.add(matrix[i][j]);
                }
            }

        }
        Collections.sort(result);

        if(result.size()!=0){
            System.out.println(result.get(result.size()-1));
        }
        else {
            System.out.println("NOT FOUND");
        }
    }
    /***
     * @name summAbs
     * @param arr
     * @return
     * calculate summ of abs(item) for task27
     */
    private int summAbs(int[] arr){
        int result = 0;
        for(int item: arr){
            result+=Math.abs(item);
        }
        return result;
    }

    @Override
    public void task27() {
        Scanner scanner =  new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int size = matrix.length;
        int [] res =  new int[size];
        int[][]transpose = trasposeMatrix(matrix);
        int [] temp = new int[size];
        for(int k = 0;k<size;k++){
            for(int i= 0; i <size-1;i++){
                if(summAbs(transpose[i]) < summAbs(transpose[i+1])){
                    temp = transpose[i];
                    transpose[i] = transpose[i+1];
                    transpose[i+1] = temp;
                }
            }

        }
        matrix =  trasposeMatrix(transpose);
        printMatrixrRows(matrix);
    }

}
