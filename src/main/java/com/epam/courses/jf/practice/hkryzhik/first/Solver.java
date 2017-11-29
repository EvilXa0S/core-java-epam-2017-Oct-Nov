package com.epam.courses.jf.practice.hkryzhik.first;


import com.epam.courses.jf.practice.common.first.ISolver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.abs;

public class Solver implements ISolver {

    private static int index;

    private static int checkMin(int[][] matrix, int DIMENSION, int row, int col) {
        int k = 0;
        if (row == 0){
            if (col == 0){
                for (int rowInd = 0; rowInd < row + 2; ++rowInd){
                    for (int colInd = 0; colInd < col + 2; ++ colInd){
                        if (matrix[rowInd][colInd] < matrix[row][col]){
                            k++;
                        }
                    }
                }
                if (k == 3){
                    return matrix[row][col];
                }
            } else if ( col == DIMENSION - 1){
                for (int rowInd = 0; rowInd < row + 2; ++rowInd) {
                    for (int colInd = col - 1; colInd < DIMENSION; ++colInd) {
                        if (matrix[rowInd][colInd] < matrix[row][col]) {
                            k++;
                        }
                    }
                }
                if (k == 3) {
                    return matrix[row][col];
                }
            } else {
                for (int rowInd = 0; rowInd < row + 2; ++rowInd) {
                    for (int colInd = col - 1; colInd < col + 2; ++colInd) {
                        if (matrix[rowInd][colInd] < matrix[row][col]) {
                            k++;
                        }
                    }
                }
                if (k == 5) {
                    return matrix[row][col];
                }
            }
        } else if (row == DIMENSION - 1){
            if (col == 0) {
                for (int rowInd = row - 1; rowInd < DIMENSION; ++rowInd) {
                    for (int colInd = 0; colInd < col + 2; ++colInd) {
                        if (matrix[rowInd][colInd] < matrix[row][col]) {
                            k++;
                        }
                    }
                }
                if (k == 3) {
                    return matrix[row][col];
                }
            } else if (col == DIMENSION - 1) {
                for (int rowInd = row - 1; rowInd < DIMENSION; ++rowInd) {
                    for (int colInd = col - 1; colInd < DIMENSION; ++colInd) {
                        if (matrix[rowInd][colInd] < matrix[row][col]) {
                            k++;
                        }
                    }
                }
                if (k == 3) {
                    return matrix[row][col];
                }
            } else {
                for (int rowInd = row - 1; rowInd < DIMENSION; ++rowInd) {
                    for (int colInd = col - 1; colInd < col + 2; ++colInd) {
                        if (matrix[rowInd][colInd] < matrix[row][col]) {
                            k++;
                        }
                    }
                }
                if (k == 5) {
                    return matrix[row][col];
                }
            }
        } else {
            if (col == 0) {
                for (int rowInd = row - 1; rowInd < row + 2; ++rowInd) {
                    for (int colInd = 0; colInd < col + 2; ++colInd) {
                        if (matrix[rowInd][colInd] < matrix[row][col]) {
                            k++;
                        }
                    }
                }
                if (k == 5) {
                    return matrix[row][col];
                }
            } else if (col == DIMENSION - 1) {
                for (int rowInd = row - 1; rowInd < row + 2; ++rowInd) {
                    for (int colInd = col - 1; colInd < DIMENSION; ++colInd) {
                        if (matrix[rowInd][colInd] < matrix[row][col]) {
                            k++;
                        }
                    }
                }
                if (k == 5) {
                    return matrix[row][col];
                }
            } else {
                for (int rowInd = row - 1; rowInd < row + 2; ++rowInd) {
                    for (int colInd = col - 1; colInd < col + 2; ++colInd) {
                        if (matrix[rowInd][colInd] < matrix[row][col]) {
                            k++;
                        }
                    }
                }
                if (k == 8) {
                    return matrix[row][col];
                }
            }
        }
        return matrix[row][col] - 1;
    }

    private static boolean isSorted(char[] characters){

        if(characters.length == 1){
            return false;
        }

        for(int i = 1; i < characters.length; i++) {

            if(characters[i - 1] >= characters[i]) {

                return false;
            }
        }
        return true;
    }

    @Override
    public void task1() {

        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        ArrayList<String> inputStringList = new ArrayList<>();

        for(int i = 0; i < size; i++){
            inputStringList.add(inputData.nextLine());
        }

        inputStringList.sort((s1, s2) -> s2.length() - s1.length());

        System.out.printf("MIN (%d): \"%s\"%n" ,inputStringList.get(inputStringList.size() - 1).length(),
                inputStringList.get(inputStringList.size() - 1));

        inputStringList.sort((s1, s2) -> s1.length() - s2.length());

        System.out.printf("MAX (%d): \"%s\"%n" ,inputStringList.get(inputStringList.size() - 1).length(),
                inputStringList.get(inputStringList.size() - 1));

    }

    @Override
    public void task2() {

        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        ArrayList<String> inputStringList = new ArrayList<>();

        for(int i = 0; i < size; i++){
            inputStringList.add(inputData.nextLine());
        }

        inputStringList.sort((String s1, String s2) -> {
            if (s1.length() != s2.length()) {
                return s1.length() - s2.length();
            } else {
                for (int i = 0; i < s1.length(); i++) {
                    if ((int) s1.charAt(i) < (int) s2.charAt(i)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return 0;
            }
        });

        for (String string: inputStringList) {
            System.out.printf("(%d): \"%s\"\n", string.length(), string);
        }

    }

    @Override
    public void task3() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;
        int length = 0;


        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        ArrayList<String> inputStringList = new ArrayList<>();

        for(int i = 0; i < size; i++){
            inputStringList.add(inputData.nextLine());
        }

        for (String string : inputStringList) {
            length += string.length();
        }

        final int stringLength = length / size;

        System.out.println("AVERAGE (" + stringLength + ")");

        inputStringList.forEach((String s) ->{

            if(s.length() < stringLength){
                System.out.printf("(%d): \"%s\"\n", s.length(), s);
            }

        });

    }

    @Override
    public void task4() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        String[] inputStrings = inputData.nextLine().split(" ");

        ArrayList<HashSet> sets = new ArrayList<>();

        for(String str : inputStrings){
            HashSet<Character> characterSet = new HashSet<>();
            for (Character ch : str.toCharArray()) {
                characterSet.add(ch);
            }
            sets.add(characterSet);
        }

        System.out.println(inputStrings[sets.indexOf(sets.stream().min((ch1, ch2) -> ch1.size() - ch2.size()).get())]);

    }

    @Override
    public void task5() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        String[] inputStrings = inputData.nextLine().split(" ");

        int count = 0;

        for (String string : inputStrings) {

            String lowerCaseString = string.toLowerCase();

            if(lowerCaseString.matches("([a-z])\\w+")){

                Pattern vowels = Pattern.compile("([aeiouy])");
                Pattern consonants = Pattern.compile("([bcdfghjklmnpqrstvwxz])");

                Matcher vowelsMatcher = vowels.matcher(lowerCaseString);
                Matcher consonantsMatcher = consonants.matcher(lowerCaseString);
                int vowelsCount = 0;
                int consonantsCount = 0;
                while (vowelsMatcher.find()) {
                    vowelsCount++;
                }
                while (consonantsMatcher.find()) {
                    consonantsCount++;
                }

                if(vowelsCount == consonantsCount){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    public void task6() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        String[] inputStrings = inputData.nextLine().split(" ");

        ArrayList<String> strings = new ArrayList<>();
        boolean flag = false;

        for (String string : inputStrings) {

            char[] charactersInWord = string.toCharArray();

            if(isSorted(charactersInWord)){

                System.out.println(new String(charactersInWord));

                flag = true;
            }
        }
        if(!flag){
            System.out.println("NOT FOUND");
        }
    }

    @Override
    public void task7() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        String[] inputStrings = inputData.nextLine().split(" ");

        HashSet<String> result = new HashSet<>();

        for (String word : inputStrings) {

            char[] charactersInWord = word.toCharArray();
            HashSet<Character> charactersSet = new HashSet<>();

            for (char characterInWord : charactersInWord) {

                charactersSet.add(characterInWord);
            }

            if(charactersInWord.length == charactersSet.size()){

                result.add(new String(charactersInWord));

            }
        }
        if(result.isEmpty()){
            System.out.println("NOT FOUND");
        }else {
            StringBuilder formatedResult = new StringBuilder();
            for (String stringInResult : result) {

                formatedResult.append(stringInResult);
                formatedResult.append(' ');
            }
            formatedResult.deleteCharAt(formatedResult.length() - 1);
            System.out.println(formatedResult);
        }
    }

    @Override
    public void task8() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            try {
                size = Integer.parseInt(inputData.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Incorrect data format");
            }
        }

        String[] inputStrings = inputData.nextLine().split(" ");

        boolean flag = true;

        List<String> resultList = new ArrayList<>();

        for (String word : inputStrings) {

            try {
                if (Double.parseDouble(word) != 0) {
                    char[] charactersInWord = word.toCharArray();
                    int i1 = 0;
                    int i2 = charactersInWord.length - 1;
                    while (i2 > i1) {
                        if (charactersInWord[i1] != charactersInWord[i2]) {
                            flag = false;
                            break;
                        } else {
                            flag = true;
                        }
                        ++i1;
                        --i2;
                    }
                    if (flag) {
                        resultList.add(word);
                    }
                }
            }catch (NumberFormatException e){
                continue;
            }

        }
        if(resultList.isEmpty()){
            System.out.println("NOT FOUND");
        }else {
            System.out.println(resultList.get(resultList.size() - 1));
        }

    }

    @Override
    public void task9() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        int out = 1;

        if(size != 0) {

            for (int i = 0; i < size; i++) {
                StringBuilder result = new StringBuilder();
                for (int j = 0; j < size; j++) {
                    result.append(out);
                    result.append('\t');
                    out++;
                }
                result.deleteCharAt(result.lastIndexOf("\t"));
                System.out.print(result + "\n");
            }
        }else {
            System.out.println(0);
        }
    }

    @Override
    public void task10() {
        Scanner inputData = new Scanner(System.in);

        String[] inputStrings = null;

        int[] conversionArray = new int[3];

        if(inputData.hasNextInt()){
            inputStrings = inputData.nextLine().split(" ");
        }

        try {
            for (int i = 0; i < 3; i++) {
                conversionArray[i] = Integer.valueOf(inputStrings[i]);
            }
        }catch (NullPointerException e){
            System.out.println("Wrong input type, try again nex time");
        }

        double a = (double) conversionArray[0];
        double b = (double) conversionArray[1];
        double c = (double) conversionArray[2];


        double d = Math.pow(b, 2) - (4 * a * c);

        double x1 = 0;
        double x2 = 0;

        if(d > 0){
            x1 = (-b + Math.sqrt(d)) / (2 * a);
            x2 = (-b - Math.sqrt(d)) / (2 * a);
            if(x1 == 0) {
                System.out.printf(Locale.US,"Two solutions: %.2f, 0\n", x2);
            }else if (x2 == 0){
                System.out.printf(Locale.US,"Two solutions: 0, %.2f\n", x1);
            }else{
                System.out.printf(Locale.US,"Two solutions: %.2f, %.2f\n", x2, x1);
            }
        }else if(d == 0){
            x1 = (-b) / (2 * a);
            if(x1 == 0) {
                System.out.printf(Locale.US,"One solution: 0\n");
            }else {
                System.out.printf(Locale.US,"One solution: %.2f\n", x1);
            }
        }else {
            System.out.printf(Locale.US,"No solution\n");
        }

    }

    @Override
    public void task11() {
        Scanner inputData = new Scanner(System.in);

        int input = 0;

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};

        if (inputData.hasNextInt()) {
            input = Integer.parseInt(inputData.nextLine());
        }

        if(input > 0 && input <= 12){
            System.out.println(months[input - 1]);
        }else{
            System.out.println("INCORRECT INPUT DATA");
        }
    }

    @Override
    public void task12() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if(inputData.hasNextInt()){
            index = Integer.parseInt(inputData.nextLine());
        }

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        ArrayList<int[]> matrixRowList = new ArrayList<>();

        for(int i = 0; i < size; i++){
            String[] inputStrings = inputData.nextLine().split(" ");
            int[] row = new int[size];
            for(int j = 0; j < size; j++){
                row[j] = Integer.valueOf(inputStrings[j]);
            }
            matrixRowList.add(row);
        }

        matrixRowList.sort(Comparator.comparingInt(e -> e[index]));

        System.out.println(size);

        for (int[] elementOfList : matrixRowList) {
            for(int i = 0; i < size; i++){
                System.out.printf("%d ", elementOfList[i]);
            }
            System.out.println();
        }
    }

    @Override
    public void task13() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        int shiftCount = 0;

        if (inputData.hasNextInt()) {
            shiftCount = Integer.parseInt(inputData.nextLine());
        }

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        int[][] inputArray = new int[size][size];

        for (int i = 0; i < size; i++) {

            String[] inputLine = inputData.nextLine().split(" ");
            for (int j = 0; j < size; j++) {

                inputArray[i][j] = Integer.valueOf(inputLine[j]);

            }
        }

        int[][] resultMatrix = new int[size][size];

        System.out.println(size);

        for (int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                resultMatrix[((size * abs(shiftCount)) + i + shiftCount) % size][j] = inputArray[i][j];
            }
        }

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                System.out.printf("%d ", resultMatrix[i][j]);

            }
            System.out.println();
        }
    }

    @Override
    public void task14() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        int[] inputArray = new int[size];

        String[] inputLine = inputData.nextLine().split(" ");
        for(int i = 0; i < size; i++){
            inputArray[i] = Integer.valueOf(inputLine[i]);
        }

        int count = 1;
        for (int i = 0; i < size-1; i++) {
            int j = i;
            int subArrayLength = 1;
            while (inputArray[j] < inputArray[j + 1]) {
                subArrayLength++;
                j++;
                if (j == size - 1) {
                    break;
                }
            }
            if (subArrayLength > count) {
                count = subArrayLength;
            }
        }
        if (count == 1) {
            System.out.println(0);
        }
        else {
            System.out.println(count);
        }

    }

    @Override
    public void task15() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        int[][] inputArray = new int[size][size];


        for(int i = 0; i < size; i++) {

            String[] inputLine = inputData.nextLine().split(" ");

            for (int j = 0; j < size; j++) {

                inputArray[i][j] = Integer.valueOf(inputLine[j]);
            }
        }

        int result = 0;

        for(int i = 0; i < size; i++){
            boolean flag = false;

            for(int j = 0; j < size; j++){

                if(!flag && inputArray[i][j] > 0){

                    flag = true;
                }
                else if(flag && inputArray[i][j] < 0){

                    result += inputArray[i][j];

                } else if (flag && inputArray[i][j] > 0) {
                    break;
                }
            }
        }

        System.out.println(result);
    }

    @Override
    public void task16() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        int[][] inputArray = new int[size][size];

        for(int i = 0; i < size; i++) {
            String[] inputLine = inputData.nextLine().split(" ");
            for (int j = 0; j < size; j++) {
                inputArray[j][i] = Integer.valueOf(inputLine[j]);
            }
        }

        System.out.println(size);

        for(int i = size - 1; i != -1; i--){
            for(int j = 0; j < size; j++){
                System.out.printf("%d ", inputArray[i][j]);
            }
            System.out.println();
        }

    }

    @Override
    public void task17() {

        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if(inputData.hasNextInt()){

            size = Integer.parseInt(inputData.nextLine());
        }

        double[][] inputArray = new double[size][size];

        for (int i = 0; i < size; i++) {

            String[] lineBuffer = inputData.nextLine().split(" ");

            for (int j = 0; j < size; j++) {

                inputArray[i][j] = Integer.parseInt(lineBuffer[j]);

            }
        }

        double determinant = 1;

        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            int lead = -1;
            for (int i = rowIndex; i < size; i++) {
                if (inputArray[i][rowIndex] != 0
                        && (lead == -1
                        || Math.abs(inputArray[i][rowIndex]) > Math.abs(inputArray[lead][rowIndex]))
                        ) {
                    lead = i;
                }
            }
            if (lead == -1) {
                determinant = 0;
                break;
            }

            double[] tmp = inputArray[rowIndex];
            inputArray[rowIndex] = inputArray[lead];
            inputArray[lead] = tmp;

            if ((lead - rowIndex) % 2 == 1) {
                determinant *= -1;
            }
            determinant *= inputArray[rowIndex][rowIndex];

            for (int i = rowIndex + 1; i < size; i++) {
                double multiplier = inputArray[i][rowIndex] / inputArray[rowIndex][rowIndex];
                for (int j = rowIndex; j < size; j++) {
                    inputArray[i][j] -= inputArray[rowIndex][j] * multiplier;
                }
            }
        }

        System.out.printf("%.0f%n", determinant);

    }

    @Override
    public void task18() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        int[][] inputArray = new int[size][size];


        for(int i = 0; i < size; i++) {

            String[] inputLine = inputData.nextLine().split(" ");

            for (int j = 0; j < size; j++) {

                inputArray[i][j] = Integer.valueOf(inputLine[j]);
            }
        }

        int maxValue = 0;

        //finding max value
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(inputArray[i][j] > maxValue){
                    maxValue = inputArray[i][j];
                }
            }
        }

        ArrayList<Integer> rowsIndex = new ArrayList<>();
        ArrayList<Integer> columsIndex = new ArrayList<>();

        for(int i = 0; i < size; i++) {

            boolean flag = false;

            for (int j = 0; j < size; j++) {
                if(inputArray[i][j] == maxValue){
                    flag = true;
                    break;
                }else {
                    flag = false;
                }
            }
            if(flag){
                rowsIndex.add(i);
            }
        }

        for(int j = 0; j < size; j++) {
            boolean flag = false;

            for (int i = 0; i < size; i++) {
                if(inputArray[i][j] == maxValue){
                    flag = true;
                    break;
                }else {
                    flag = false;
                }
            }
            if(flag){
                columsIndex.add(j);
            }
        }

        System.out.println(size - rowsIndex.size());
        System.out.println(size - columsIndex.size());

        for(int i = 0; i < size; i++){
            if(rowsIndex.contains(i)){
                continue;
            }else{
                for(int j = 0; j < size; j++){
                    if(columsIndex.contains(j)){
                        continue;
                    }else {
                        System.out.printf("%d ", inputArray[i][j]);
                    }
                }
                System.out.println();
            }
        }
    }

    @Override
    public void task19() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        int[][] inputArray = new int[size][size];


        for(int i = 0; i < size; i++) {

            String[] inputLine = inputData.nextLine().split(" ");

            for (int j = 0; j < size; j++) {

                inputArray[i][j] = Integer.valueOf(inputLine[j]);
            }
        }

        ArrayList<Integer> rowsIndex = new ArrayList<>();
        ArrayList<Integer> columsIndex = new ArrayList<>();

        for(int i = 0; i < size; i++) {

            boolean flag = false;

            for (int j = 0; j < size; j++) {
                if(inputArray[i][j] == 0){
                    flag = true;
                }else {
                    flag = false;
                    break;
                }
            }
            if(flag){
                rowsIndex.add(i);
            }
        }

        for(int j = 0; j < size; j++) {
            boolean flag = false;

            for (int i = 0; i < size; i++) {
                if(inputArray[i][j] == 0){
                    flag = true;
                }else {
                    flag = false;
                    break;
                }
            }
            if(flag){
                columsIndex.add(j);
            }
        }

        System.out.println(size - rowsIndex.size());
        System.out.println(size - columsIndex.size());

        for(int i = 0; i < size; i++){
            if(rowsIndex.contains(i)){
                continue;
            }else{
                for(int j = 0; j < size; j++){
                    if(columsIndex.contains(j)){
                        continue;
                    }else {
                        System.out.printf("%d ", inputArray[i][j]);
                    }
                }
                System.out.println();
            }
        }

    }

    @Override
    public void task20(){

        Scanner inputData = new Scanner(System.in);

        int X = 0;
        int Y = 0;

        int size = 0;

        if(inputData.hasNextInt()){

            X = Integer.parseInt(inputData.nextLine());
        }
        if(inputData.hasNextInt()){

            Y = Integer.parseInt(inputData.nextLine());
        }
        if(inputData.hasNextInt()){

            size = Integer.parseInt(inputData.nextLine());
        }

        int[][] inputArray = new int[size][size];


        for (int i = 0; i < size; i++) {

            String[] lineBuffer = inputData.nextLine().split(" ");

            for (int j = 0; j < size; j++) {

                inputArray[i][j] = Integer.parseInt(lineBuffer[j]);

            }
        }

        int minValue = 0;
        int minX = 0;
        int minY = 0;

        //finding min value
        for(int i = 0; i < size; i++){

            for(int j = 0; j < size; j++){

                if(inputArray[i][j] < minValue){

                    minValue = inputArray[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }

        int[] tempArray;

        tempArray = inputArray[minX];

        inputArray[minX] = inputArray[X];

        inputArray[X] = tempArray;

        for (int i = 0; i < size; i++) {

            int tmp;

            tmp = inputArray[i][minY];

            inputArray[i][minY] = inputArray[i][Y];

            inputArray[i][Y] = tmp;

        }

        System.out.println(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(j == size - 1){
                    System.out.printf("%d", inputArray[i][j]);
                }else{
                    System.out.printf("%d ", inputArray[i][j]);
                }
            }
            System.out.println();
        }
    }

    @Override
    public void task21() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        ArrayList<ArrayList<Integer>> inputArray = new ArrayList<>();

        for(int i = 0; i < size; i++) {

            String[] inputLine = inputData.nextLine().split(" ");

            ArrayList<Integer> bufferList = new ArrayList<>();

            for (int j = 0; j < size; j++) {
                if(Integer.valueOf(inputLine[j]) != 0) {
                    bufferList.add(Integer.valueOf(inputLine[j]));
                }
            }
            if(bufferList.isEmpty()){
                for(int k = 0; k <size; k++){
                    bufferList.add(0);
                }
            }else {
                if (bufferList.size() < size) {
                    for (int k = 0; k <= size - bufferList.size(); k++) {
                        bufferList.add(0);
                    }
                }
            }
            inputArray.add(bufferList);
        }

        System.out.println(size);

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.printf("%d ", inputArray.get(i).get(j));
            }
            System.out.println();
        }


    }

    @Override
    public void task22() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        Double[][] inputArray = new Double[size][size];


        for(int i = 0; i < size; i++) {

            String[] inputLine = inputData.nextLine().replace(',', '.').split(" ");

            for (int j = 0; j < size; j++) {

                inputArray[i][j] = Double.valueOf(inputLine[j]);
            }
        }

        System.out.println(size);

        for(int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                System.out.printf("%d ", Math.round(inputArray[i][j]));
            }
            System.out.println();
        }

    }

    @Override
    public void task23() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        ArrayList<ArrayList<Integer>> rowsList = new ArrayList<>();

        ArrayList<ArrayList<Integer>> collsList = new ArrayList<>();


        for (int i = 0; i < size; i++) {

            String[] inputLine = inputData.nextLine().split(" ");

            ArrayList<Integer> rowBuffer = new ArrayList<>();

            for (int j = 0; j < size; j++) {
                rowBuffer.add(Integer.valueOf(inputLine[j]));
            }
            rowsList.add(rowBuffer);
        }

        for (int i = 0; i < size; i++) {

            ArrayList<Integer> colBuffer = new ArrayList<>();

            for (int j = 0; j < size; j++) {
                colBuffer.add(rowsList.get(j).get(i));
            }

            collsList.add(colBuffer);
        }

        int result = 0;

        for (int i = 0; i < size; i++) {

            int minItemInRow = rowsList.get(i).stream().min((e1, e2) -> e1 - e2).get();

            for(int j = 0; j < size; j++) {

                int maxItemInColl = collsList.get(j).stream().max((e1, e2) -> e1 - e2).get();

                if (minItemInRow == maxItemInColl) {
                    result += 1;
                }
            }
        }
        System.out.println(result);
    }

    @Override
    public void task24() {
        Scanner inputData = new Scanner(System.in);

        if (inputData.hasNextInt()) {
            index = Integer.parseInt(inputData.nextLine());
        }

        ArrayList<int[]> inputArray = new ArrayList<>();


        for(int i = 0; i < index; i++) {

            String[] inputLine = inputData.nextLine().split(" ");

            int[] bufferArray = new int[index];

            for (int j = 0; j < index; j++) {
                bufferArray[j] = Integer.valueOf(inputLine[j]);
            }
            inputArray.add(bufferArray);
        }

        inputArray.sort((e1, e2) -> {

            int e1Count = 0;
            int e2Count = 0;

            for(int i = 0; i < index; i++){
                e1Count += e1[i];
                e2Count += e2[i];
            }

            return e1Count - e2Count;
        });

        System.out.println(index);

        for(int i = 0; i < index; i++){
            for(int j = 0; j < index; j++){
                System.out.printf("%d ", inputArray.get(i)[j]);
            }
            System.out.println();
        }

    }

    @Override
    public void task25() {

        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {

            size = Integer.parseInt(inputData.nextLine());
        }

        if (size == 1) {

            System.out.println(1);

        } else {

            int[][] inputArray = new int[size][size];

            for (int i = 0; i < size; i++) {

                String[] lineBuffer = inputData.nextLine().split(" ");

                for (int j = 0; j < size; j++) {

                    inputArray[i][j] = Integer.parseInt(lineBuffer[j]);

                }
            }

            int qt = 0;

            for (int row = 0; row < size; ++row) {

                for (int col = 0; col < size; ++col) {

                    if (checkMin(inputArray, size, row, col) == inputArray[row][col]) {

                        qt++;

                    }
                }
            }

            System.out.println(qt);
        }
    }

    @Override
    public void task26() {

        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {

            size = Integer.parseInt(inputData.nextLine());
        }

        if (size == 1) {

            System.out.println(1);

        } else {

            int[][] inputArray = new int[size][size];

            for (int i = 0; i < size; i++) {

                String[] lineBuffer = inputData.nextLine().split(" ");

                for (int j = 0; j < size; j++) {

                    inputArray[i][j] = Integer.parseInt(lineBuffer[j]);

                }
            }

            int qt = 0;

            int max = 0;

            for (int row = 0; row < size; ++row) {

                for (int col = 0; col < size; ++col) {

                    if (checkMin(inputArray, size, row, col) == inputArray[row][col]) {

                        qt++;

                        max = inputArray[row][col];
                    }
                }
            }

            if (qt == 0) {

                System.out.println("NOT FOUND");

            } else {

                for (int row = 0; row < size; ++row) {

                    for (int col = 0; col < size; ++col) {

                        if (checkMin(inputArray, size, row, col) == inputArray[row][col]) {

                            if (inputArray[row][col] > max) {

                                max = inputArray[row][col];
                            }
                        }
                    }
                }
                System.out.println(max);
            }
        }
    }

    @Override
    public void task27() {
        Scanner inputData = new Scanner(System.in);

        int size = 0;

        if (inputData.hasNextInt()) {
            size = Integer.parseInt(inputData.nextLine());
        }

        ArrayList<int[]> rowsList = new ArrayList<>();

        ArrayList<int[]> collsList = new ArrayList<>();


        for (int i = 0; i < size; i++) {

            String[] inputLine = inputData.nextLine().split(" ");

            int[] rowBuffer = new int[size];

            for (int j = 0; j < size; j++) {
                rowBuffer[j] = Integer.valueOf(inputLine[j]);
            }
            rowsList.add(rowBuffer);
        }

        for (int i = 0; i < size; i++) {

            int[] colBuffer = new int[size];

            for (int j = 0; j < size; j++) {
                colBuffer[j] = rowsList.get(j)[i];
            }

            collsList.add(colBuffer);
        }

        collsList.sort((e1, e2) -> {

            int collE1ItemsCount = 0;

            int collE2ItemsCount = 0;

            for(int i = 0; i < e1.length; i++){
                collE1ItemsCount += abs(e1[i]);
                collE2ItemsCount += abs(e2[i]);
            }
            if(collE1ItemsCount == collE2ItemsCount){
                return 0;
            }

            return collE2ItemsCount - collE1ItemsCount;
        });

        System.out.println(size);

        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.printf("%d ", collsList.get(j)[i]);
            }
            System.out.println();
        }
    }
}
