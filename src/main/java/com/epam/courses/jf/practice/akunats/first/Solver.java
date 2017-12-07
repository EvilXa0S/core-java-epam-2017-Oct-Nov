package com.epam.courses.jf.practice.akunats.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Solver implements ISolver {
    @Override
    public void task1() {
        Scanner scanner = new Scanner(System.in);
        int numberOfStrings = scanner.nextInt();
        if (numberOfStrings <= 0 || numberOfStrings >= 100) {
            throw new IllegalArgumentException("Вы ввели неверные данные.");
        }
        String[] inputStrings = new String[numberOfStrings];
        scanner.nextLine();
        for (int i = 0; i < inputStrings.length; i++) {
            inputStrings[i] = scanner.nextLine();
        }
        String minString = Arrays.stream(inputStrings)
                .reduce((a, b) -> a.length() < b.length() ? a : b)
                .orElse("");
        String maxString = Arrays.stream(inputStrings)
                .reduce((a, b) -> a.length() > b.length() ? a : b)
                .orElse("");
        int minLength = minString.length();
        int maxLength = maxString.length();
        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }

    @Override
    public void task2() {
        Scanner scanner = new Scanner(System.in);
        int numberOfStrings = scanner.nextInt();
        if (numberOfStrings <= 0 || numberOfStrings >= 100) {
            throw new IllegalArgumentException("Вы ввели неверные данные.");
        }
        String[] inputStrings = new String[numberOfStrings];
        scanner.nextLine();
        for (int i = 0; i < inputStrings.length; i++) {
            inputStrings[i] = scanner.nextLine();
        }
        Arrays.sort(inputStrings);
        List<String> resultStrings = Arrays.stream(inputStrings)
                .sorted(Comparator.comparingInt(String::length))
                .collect(toList());
        for (String string : resultStrings) {
            System.out.printf("(%d): \"%s\"%n", string.length(), string);
        }
    }

    @Override
    public void task3() {
        Scanner scanner = new Scanner(System.in);
        int numberOfStrings = scanner.nextInt();
        String[] inputStrings = new String[numberOfStrings];
        scanner.nextLine();
        for (int i = 0; i < inputStrings.length; i++) {
            inputStrings[i] = scanner.nextLine();
        }
        int sumLength = 0;
        for (String inputString : inputStrings) {
            sumLength += inputString.length();
        }
        int averageLength = sumLength / inputStrings.length;
        System.out.printf("AVERAGE (%d)%n", averageLength);
        for (String string : inputStrings) {
            if (string.length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", string.length(), string);
            }
        }
    }

    @Override
    public void task4() {
        Scanner scanner = new Scanner(System.in);
        int numberOfStrings = scanner.nextInt();
        scanner.nextLine();
        String strings = scanner.nextLine();
        String[] inputStrings = strings.split(" ");
        if (numberOfStrings != inputStrings.length) {
            throw new IndexOutOfBoundsException("Ввели неверный массив");
        }
        List<Integer> numOfUniqueChars = new ArrayList<>();
        for (String inputString : inputStrings) {
            Map<String, Integer> interMap = new HashMap<>();
            Arrays.stream(inputString.split("")).forEach(i -> {
                if (interMap.containsKey(i)) {
                    interMap.put(i, interMap.get(i) + 1);
                } else {
                    interMap.put(i, 0);
                }
            });
            int numOfUniqueCharsInString = 0;
            for (Map.Entry<String, Integer> entry : interMap.entrySet()) {
                numOfUniqueCharsInString += entry.getValue();
            }
            numOfUniqueChars.add(numOfUniqueCharsInString);
        }
        int minNumberUniqueChars = numOfUniqueChars.get(0);
        int indexWord;
        String resultString = "";
        if (numOfUniqueChars.stream().allMatch(i -> i.equals(0))) {
            resultString = Arrays.stream(inputStrings)
                    .sorted(Comparator.comparing(String::length))
                    .collect(Collectors.toList()).get(0);
        } else {
            for (int i = 1; i < numOfUniqueChars.size(); i++) {
                if (minNumberUniqueChars < numOfUniqueChars.get(i)) {
                    minNumberUniqueChars = numOfUniqueChars.get(i);
                    indexWord = i;
                    resultString = inputStrings[indexWord];
                }
            }
        }
        System.out.println(resultString);
    }

    @Override
    public void task5() {
        Scanner scanner = new Scanner(System.in);
        int numberOfStrings = scanner.nextInt();
        scanner.nextLine();
        String strings = scanner.nextLine();
        String[] inputStrings = strings.split(" ");
        if (numberOfStrings != inputStrings.length) {
            throw new IndexOutOfBoundsException("Ввели неверный массив");
        }
        int numStringResult = 0;
        for (String inputString : inputStrings) {
            Map<String, Integer> interMap = new HashMap<>();
            interMap.put("vowel", 0);
            interMap.put("consonant", 0);
            Arrays.stream(inputString.split("")).forEach(i -> {
                if (Pattern.compile("^(?i:[aeiouy]).*").matcher(i).matches()) {
                    interMap.put("vowel", interMap.get("vowel") + 1);
                } else if (Pattern.compile("^(?i:[bcdfghjklmnpqrstvwxz]).*").matcher(i).matches()) {
                    interMap.put("consonant", interMap.get("consonant") + 1);
                }
            });
            if (interMap.get("vowel").equals(interMap.get("consonant")) &
                    !interMap.get("vowel").equals(0) & !interMap.get("consonant").equals(0)) {
                numStringResult++;
            }
        }
        System.out.println(numStringResult);
    }

    @Override
    public void task6() {
        Scanner scanner = new Scanner(System.in);
        int numberOfStrings = scanner.nextInt();
        scanner.nextLine();
        String strings = scanner.nextLine();
        String[] inputStrings = strings.split(" ");
        if (numberOfStrings != inputStrings.length) {
            throw new IndexOutOfBoundsException("Ввели неверный массив");
        }
        List<String> resultList = new ArrayList<>();
        for (String inputString : inputStrings) {
            List<Integer> interList = new ArrayList<>();
            char[] charMass = inputString.toCharArray();
            for (char charMas : charMass) interList.add((int) charMas);
            int count = 0;
            for (int i = 1; i < interList.size(); i++) {
                if (interList.get(i) > interList.get(i - 1)) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == interList.size() - 1) {
                resultList.add(inputString);
            }
        }
        if (resultList.size() == 0) {
            System.out.println("NOT FOUND");
        } else {
            String result = "";
            for (String s : resultList) {
                if (s.length() != 1) {
                    result = s;
                    break;
                }
            }
            if (result.equals("")) {
                System.out.println("NOT FOUND");
            } else {
                System.out.println(result);
            }
        }
    }

    @Override
    public void task7() {
        Scanner scanner = new Scanner(System.in);
        int numberOfStrings = scanner.nextInt();
        scanner.nextLine();
        String strings = scanner.nextLine();
        String[] inputStrings = strings.split(" ");
        if (numberOfStrings != inputStrings.length) {
            throw new IndexOutOfBoundsException("Ввели неверный массив");
        }
        Set<String> resultSet = new HashSet<>();
        for (String inputString : inputStrings) {
            Set<String> interSet = Arrays.stream(inputString.split(""))
                    .collect(toSet());
            if (interSet.size() == inputString.length()) {
                resultSet.add(inputString);
            }
        }
        String result = "";
        for (String s : resultSet) {
            if (resultSet.size() == 1) {
                result = s;
            } else {
                if (result.equals("")) {
                    result = s;
                } else {
                    result = result.concat(" " + s);
                }
            }
        }
        if (result.equals("")) {
            System.out.println("NOT FOUND");
        } else {
            System.out.println(result);
        }
    }

    @Override
    public void task8() {
        Scanner scanner = new Scanner(System.in);
        int numberOfStrings = scanner.nextInt();
        scanner.nextLine();
        String strings = scanner.nextLine();
        String[] inputStrings = strings.split(" ");
        if (numberOfStrings != inputStrings.length) {
            throw new IndexOutOfBoundsException("Ввели неверный массив");
        }
        List<String> resultList = new ArrayList<>();
        for (String inputString : inputStrings) {
            List<String> interList = new ArrayList<>();
            Arrays.stream(inputString.split("")).forEach(i -> {
                if (Pattern.compile("^(?i:[0-9]).*").matcher(i).matches()) {
                    interList.add(i);
                }
            });
            if (interList.size() != 0) {
                int countActions = 0;
                int countTrue = 0;
                for (int i = 0; i <= (interList.size() / 2) - 1; i++) {
                    if (interList.get(i).equals(interList.get(interList.size() - (i + 1)))) {
                        countActions++;
                    }
                    countTrue++;
                }
                if (countActions == countTrue) {
                    String stringToInt = interList.stream().reduce(String::concat).orElse(null);
                    resultList.add(stringToInt);
                }
            }
        }
        String result;
        if (resultList.isEmpty()) {
            result = "NOT FOUND";
        } else {
            result = resultList.get(resultList.size() - 1);
        }
        System.out.println(result);
    }

    @Override
    public void task9() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        int index = 1;
        for (int i = 0; i < arrayDimension; i++) {
            inputMatrix[i][0] = index++;
            System.out.print(inputMatrix[i][0]);
            for (int j = 1; j < arrayDimension; j++) {
                inputMatrix[i][j] = index++;
                System.out.printf("\t" + inputMatrix[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void task10() {
        Scanner scanner = new Scanner(System.in);
        String parametersEquationString = scanner.nextLine();
        int[] parametersEquationInt = Arrays.stream(parametersEquationString.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        double A = parametersEquationInt[0];
        double B = parametersEquationInt[1];
        double C = parametersEquationInt[2];
        double discriminant = (B * B) - (4 * A * C);
        if (discriminant > 0) {
            BigDecimal x1 = new BigDecimal((-B - Math.sqrt(discriminant)) / (2 * A));
            x1 = x1.equals(new BigDecimal(0))
                    ? new BigDecimal(0)
                    : x1.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal x2 = new BigDecimal((-B + Math.sqrt(discriminant)) / (2 * A));
            x2 = x2.setScale(2, BigDecimal.ROUND_HALF_UP);
            Double x1Out = Double.parseDouble(x1.toString());
            Double x2Out = Double.parseDouble(x2.toString());
            System.out.println("Two solutions: " + x1Out + ", " + x2Out);
        } else if (discriminant == 0) {
            BigDecimal x = new BigDecimal((-B) / (2 * A));
            x = x.setScale(2, BigDecimal.ROUND_HALF_UP);
            Double xOut = Double.parseDouble(x.toString());
            System.out.println("One solution: " + xOut);
        } else {
            System.out.println("No solution");
        }
    }

    @Override
    public void task11() {
        Scanner scanner = new Scanner(System.in);
        int numberMonth = 0;
        String result = "";
        try {
            numberMonth = scanner.nextInt();
        } catch (InputMismatchException e) {
            result = "INCORRECT INPUT DATA";

        }
        if (result.equals("")) {
            switch (numberMonth) {
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
        } else {
            System.out.println(result);
        }
    }

    @Override
    public void task12() {
        Scanner scanner = new Scanner(System.in);
        int numColumn = scanner.nextInt();
        scanner.nextLine();
        int arrayDimension = scanner.nextInt();
        if (numColumn < 0 || numColumn >= arrayDimension) {
            throw new IllegalArgumentException("Вы ввели неверные данные.");
        }
        scanner.nextLine();
        Integer[][] inputMatrix = new Integer[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        List<Integer> resultList = new ArrayList<>();
        Arrays.stream(inputMatrix)
                .sorted(Comparator.comparing(a -> a[numColumn]))
                .forEach(a -> resultList.addAll(Arrays.asList(a).subList(0, arrayDimension)));
        System.out.println(arrayDimension);
        for (int i = 0; i < resultList.size(); i++) {
            System.out.print(resultList.get(i) + " ");
            if ((i + 1) % arrayDimension == 0) {
                System.out.print("\n");
            }
        }
    }

    @Override
    public void task13() {
        Scanner scanner = new Scanner(System.in);
        int numShift = scanner.nextInt();
        scanner.nextLine();
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        int[] shiftIndexes = new int[arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            int newIndex = i + numShift + arrayDimension;
            if (numShift < 0 || numShift > 0) {
                if (newIndex >= arrayDimension) {
                    shiftIndexes[i] = newIndex % arrayDimension;
                } else if (newIndex < 0) {
                    shiftIndexes[i] = Math.abs(newIndex % arrayDimension);
                } else {
                    shiftIndexes[i] = newIndex;
                }
            } else {
                shiftIndexes[i] = i;
            }
        }
        int[][] resultMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            int index = 0;
            for (int j = 0; j < arrayDimension; j++) {
                if (shiftIndexes[j] == i)
                    index = j;
            }
            resultMatrix[i] = inputMatrix[index];
        }
        System.out.println(arrayDimension);
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void task14() {
        Scanner scanner = new Scanner(System.in);
        int numElements = scanner.nextInt();
        scanner.nextLine();
        int[] inputMassive = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        List<Integer> numberSequences = new ArrayList<>();
        int num = 1;
        for (int i = 1; i < numElements; i++) {
            if (inputMassive[i] > inputMassive[i - 1]) {
                num++;
            } else {
                if (num != 1) {
                    numberSequences.add(num);
                    num = 1;
                }
            }
            if (i == numElements - 1) {
                if (num != 1) {
                    numberSequences.add(num);
                    num = 1;
                }
            }
        }
        String result = numberSequences.isEmpty() ? "0" : Collections.max(numberSequences).toString();
        System.out.println(result);
    }

    @Override
    public void task15() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        int result = 0;
        for (int i = 0; i < arrayDimension; i++) {
            List<Integer> interList = new ArrayList<>();
            for (int j = 0; j < arrayDimension; j++) {
                if (inputMatrix[i][j] > 0 && interList.size() < 2) {
                    interList.add(j);
                }
                if (interList.size() == 2 && j == arrayDimension - 1) {
                    if (interList.get(1) - interList.get(0) == 1) {
                        result += 0;
                    } else {
                        for (int k = interList.get(0) + 1; k < interList.get(1); k++) {
                            result += inputMatrix[i][k];
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    @Override
    public void task16() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        int[][] resultMatrix = new int[arrayDimension][arrayDimension];
        if (arrayDimension > 1) {
            for (int i = 0; i < arrayDimension / 2; i++) {
                int j = (arrayDimension - 1) - i; // берем противоположные индексы для движениии
                // строк и стоолбцов с разных сторон, алгоритм представлен далее
                if (!(arrayDimension % 2 == 0)) {
                    resultMatrix[arrayDimension / 2][arrayDimension / 2]
                            = inputMatrix[arrayDimension / 2][arrayDimension / 2];
                }
                // Заметил зависимость при повороте столбцов и строк матрицы,
                //  * * * * *   при движение квадратов матрицы, тоесть отдельно двигаются
                //  * - - - *   сначало * потом - , по приведенному ниже правилу.
                //  * - 0 - *
                //  * - - - *
                //  * * * * *
                for (int k = 0; k < arrayDimension; k++) {
                    resultMatrix[(arrayDimension - 1) - k][i] = inputMatrix[i][k]; // Движение стобцов одной стороны
                    resultMatrix[(arrayDimension - 1) - k][j] = inputMatrix[j][k]; // Движение стобцов другой стороны
                    resultMatrix[i][k] = inputMatrix[k][j]; // Движение строк одной стороны
                    resultMatrix[j][k] = inputMatrix[k][i]; // Движение строк одной стороны
                }
            }
        } else {
            resultMatrix[0][0] = 1;
        }
        System.out.println(arrayDimension);
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void task17() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        System.out.println(arrayDimension != 1 ? determinant(arrayDimension, inputMatrix) : 1);
    }

    private int determinant(int arrayDimension, int[][] inputMatrix) {
        int matrixDeterminant = 0;
        for (int i = 0; i < arrayDimension; i++) {
            int[][] interMatrix = new int[arrayDimension - 1][arrayDimension - 1];
            int p = 0;
            for (int j = 0; j < arrayDimension; j++) {
                int m = 0;
                for (int k = 0; k < arrayDimension; k++) {
                    if (j != 0 && k != i && p < arrayDimension - 1 && m < arrayDimension - 1) {
                        interMatrix[p][m] = inputMatrix[j][k]; // минор матрицы
                        m++;
                    }
                }
                if (m != 0) {
                    p++;
                }
            }
            if (interMatrix.length == 1) {
                matrixDeterminant += Math.pow(-1, i) * inputMatrix[0][i] * interMatrix[0][0];
            } else if (interMatrix.length == 2) {
                int minor = interMatrix[0][0] * interMatrix[1][1]
                        - interMatrix[0][1] * interMatrix[1][0];
                matrixDeterminant += Math.pow(-1, i) * inputMatrix[0][i] * minor;
            } else {
                int minor = determinant(interMatrix.length, interMatrix);
                matrixDeterminant += Math.pow(-1, i) * inputMatrix[0][i] * minor;
            }
        }
        return matrixDeterminant;
    }

    @Override
    public void task18() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        int max = Arrays.stream(inputMatrix)
                .mapToInt(i -> Arrays.stream(i).reduce((m1, m2) -> m1 > m2 ? m1 : m2).orElse(0))
                .reduce((a, b) -> a > b ? a : b)
                .orElse(0);
        Set<Integer> columnsSet = new HashSet<>();
        Set<Integer> stringsSet = new HashSet<>();
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                if (inputMatrix[i][j] == max) {
                    columnsSet.add(j);
                    stringsSet.add(i);
                }
            }
        }
        int[][] resultMatrix = resultMatrix(arrayDimension, inputMatrix, columnsSet, stringsSet);
        int numStringsResult = arrayDimension - stringsSet.size();
        int numColumnsResult = arrayDimension - columnsSet.size();
        System.out.println(numStringsResult);
        System.out.println(numColumnsResult);
        for (int i = 0; i < numStringsResult; i++) {
            for (int j = 0; j < numColumnsResult; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private int[][] resultMatrix(int arrayDimension, int[][] inputMatrix,
                                 Set<Integer> columnsList, Set<Integer> stringsList) {
        int[][] resultMatrix = new int[arrayDimension - stringsList.size()][arrayDimension - columnsList.size()];
        int str = 0;
        int col = 0;
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                int iforEquals = i;
                int jforEquals = j;
                if (!(stringsList.stream().anyMatch(a -> a.equals(iforEquals))
                        || columnsList.stream().anyMatch(a -> a.equals(jforEquals)))) {
                    resultMatrix[str][col] = inputMatrix[i][j];
                    if (col == arrayDimension - columnsList.size() - 1) {
                        str++;
                        col = 0;
                    } else if (col < arrayDimension - columnsList.size() - 1) {
                        col++;
                    }
                }
            }
        }
        return resultMatrix;
    }

    @Override
    public void task19() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        Set<Integer> columnsSet = new HashSet<>();
        Set<Integer> stringsSet = new HashSet<>();
        for (int i = 0; i < arrayDimension; i++) {
            int string = 0;
            int indexString = 0;
            int column = 0;
            int indexColumn = 0;
            for (int j = 0; j < arrayDimension; j++) {
                if (inputMatrix[i][j] == 0) {
                    string++;
                    indexString = i;
                }
                if (inputMatrix[j][i] == 0) {
                    column++;
                    indexColumn = i;
                }
            }
            if (column == arrayDimension) {
                columnsSet.add(indexColumn);
            }
            if (string == arrayDimension) {
                stringsSet.add(indexString);
            }
        }
        int[][] resultMatrix = resultMatrix(arrayDimension, inputMatrix, columnsSet, stringsSet);
        int numStringsResult = arrayDimension - stringsSet.size();
        int numColumnsResult = arrayDimension - columnsSet.size();
        System.out.println(numStringsResult);
        System.out.println(numColumnsResult);
        for (int i = 0; i < numStringsResult; i++) {
            for (int j = 0; j < numColumnsResult; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void task20() {
        Scanner scanner = new Scanner(System.in);
        int strInput = scanner.nextInt();
        scanner.nextLine();
        int colInput = scanner.nextInt();
        scanner.nextLine();
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        if (strInput < 0 || strInput >= arrayDimension || colInput < 0 || colInput >= arrayDimension) {
            throw new IllegalArgumentException("Вы ввели неверные данные.");
        }
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        int strMin = 0;
        int colMin = 0;
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                if (inputMatrix[i][j] <= inputMatrix[strMin][colMin]) {
                    strMin = i;
                    colMin = j;
                }
            }
        }
        int[][] resultMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                resultMatrix[i][j] = inputMatrix[i == strInput ? strMin : (i == strMin ? strInput : i)]
                        [j == colInput ? colMin : (j == colMin ? colInput : j)];
            }
        }
        System.out.println(arrayDimension);
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void task21() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        int resultMatrix[][] = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            int col = 0;
            for (int j = 0; j < arrayDimension; j++) {
                if (inputMatrix[i][j] != 0) {
                    resultMatrix[i][col] = inputMatrix[i][j];
                    col++;
                }
            }
        }
        System.out.println(arrayDimension);
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void task22() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = (int) Math.round(scanner.nextDouble());
            }
        }
        if (arrayDimension != 1) {
            System.out.println(arrayDimension);
            for (int i = 0; i < arrayDimension; i++) {
                for (int j = 0; j < arrayDimension; j++) {
                    System.out.print(inputMatrix[i][j] + " ");
                }
                System.out.print("\n");
            }
        } else {
            System.out.println(arrayDimension);
            System.out.println(1);
        }
    }

    @Override
    public void task23() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        int result = 0;
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                int str = 0;
                int col = 0;
                for (int m = 0; m < arrayDimension; m++) {
                    // Если элемент в строке равен или больше числа то увеличиваем счетчик строк
                    if (inputMatrix[i][j] <= inputMatrix[i][m]) {
                        str++;
                    }
                    // Если элемент в столбце равен или больше числа то увеличиваем счетчик столбцов
                    if (inputMatrix[i][j] >= inputMatrix[m][j]) {
                        col++;
                    }
                }
                if (str == arrayDimension && col == arrayDimension) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    @Override
    public void task24() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        Integer[][] inputMatrix = new Integer[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        Integer[][] sumStrings = new Integer[arrayDimension][2];
        for (int i = 0; i < arrayDimension; i++) {
            int sumString = 0;
            for (int j = 0; j < arrayDimension; j++) {
                sumString += inputMatrix[i][j];
            }
            sumStrings[i][0] = i;
            sumStrings[i][1] = sumString;
        }
        List<Integer> interList = new ArrayList<>();
        Arrays.stream(sumStrings).sorted((a, b) -> !a[1].equals(b[1])
                ? a[1].compareTo(b[1]) : 1)
                .forEach(o -> interList.add(o[0]));
        Integer[][] resultMatrix = new Integer[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            System.arraycopy(inputMatrix[interList.get(i)], 0,
                    resultMatrix[i], 0, arrayDimension);
        }
        System.out.println(arrayDimension);
        printMatrix(resultMatrix, arrayDimension);
    }

    @Override
    public void task25() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        if (arrayDimension != 1) {
            int localMin = 0;
            for (int i = 0; i < arrayDimension; i++) {
                for (int j = 0; j < arrayDimension; j++) {
                    if (i > 0 && i < arrayDimension - 1) {
                        if (j > 0 && j < arrayDimension - 1 && inputMatrix[i][j] < inputMatrix[i - 1][j - 1]
                                && inputMatrix[i][j] < inputMatrix[i - 1][j] &&
                                inputMatrix[i][j] < inputMatrix[i - 1][j + 1] && inputMatrix[i][j] < inputMatrix[i][j - 1] &&
                                inputMatrix[i][j] < inputMatrix[i][j + 1] && inputMatrix[i][j] < inputMatrix[i + 1][j - 1] &&
                                inputMatrix[i][j] < inputMatrix[i + 1][j] && inputMatrix[i][j] < inputMatrix[i + 1][j + 1]) {
                            localMin++;
                        } else if (j == 0 && inputMatrix[i][j] < inputMatrix[i - 1][j] &&
                                inputMatrix[i][j] < inputMatrix[i - 1][j + 1] && inputMatrix[i][j] < inputMatrix[i][j + 1] &&
                                inputMatrix[i][j] < inputMatrix[i + 1][j] && inputMatrix[i][j] < inputMatrix[i + 1][j + 1]) {
                            localMin++;
                        } else if (j == arrayDimension - 1 && inputMatrix[i][j] < inputMatrix[i - 1][j - 1]
                                && inputMatrix[i][j] < inputMatrix[i - 1][j] && inputMatrix[i][j] < inputMatrix[i][j - 1] &&
                                inputMatrix[i][j] < inputMatrix[i + 1][j - 1] && inputMatrix[i][j] < inputMatrix[i + 1][j]) {
                            localMin++;
                        }
                    }
                    if (i == 0) {
                        if (j == 0 && inputMatrix[i][j] < inputMatrix[i][j + 1] && inputMatrix[i][j] < inputMatrix[i + 1][j]
                                && inputMatrix[i][j] < inputMatrix[i + 1][j + 1]) {
                            localMin++;
                        }
                        if (j == arrayDimension - 1 && inputMatrix[i][j] < inputMatrix[i][j - 1] && inputMatrix[i][j] < inputMatrix[i + 1][j - 1]
                                && inputMatrix[i][j] < inputMatrix[i + 1][j]) {
                            localMin++;
                        }
                    }
                    if (i == arrayDimension - 1) {
                        if (j == 0 && inputMatrix[i][j] < inputMatrix[i - 1][j]
                                && inputMatrix[i][j] < inputMatrix[i - 1][j + 1]
                                && inputMatrix[i][j] < inputMatrix[i][j + 1]) {
                            localMin++;
                        }
                        if (j == arrayDimension - 1 && inputMatrix[i][j] < inputMatrix[i - 1][j]
                                && inputMatrix[i][j] < inputMatrix[i - 1][j - 1]
                                && inputMatrix[i][j] < inputMatrix[i][j - 1]) {
                            localMin++;
                        }
                    }
                }
            }
            System.out.println(localMin);
        } else {
            System.out.println(1);
        }
    }

    @Override
    public void task26() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        int[][] inputMatrix = new int[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        if (arrayDimension != 1) {
            List<Integer> resultList = new ArrayList<>();
            for (int i = 0; i < arrayDimension; i++) {
                for (int j = 0; j < arrayDimension; j++) {
                    if (i > 0 && i < arrayDimension - 1) {
                        if (j > 0 && j < arrayDimension - 1 && inputMatrix[i][j] > inputMatrix[i - 1][j - 1]
                                && inputMatrix[i][j] > inputMatrix[i - 1][j] &&
                                inputMatrix[i][j] > inputMatrix[i - 1][j + 1] && inputMatrix[i][j] > inputMatrix[i][j - 1] &&
                                inputMatrix[i][j] > inputMatrix[i][j + 1] && inputMatrix[i][j] > inputMatrix[i + 1][j - 1] &&
                                inputMatrix[i][j] > inputMatrix[i + 1][j] && inputMatrix[i][j] > inputMatrix[i + 1][j + 1]) {
                            resultList.add(inputMatrix[i][j]);
                        } else if (j == 0 && inputMatrix[i][j] > inputMatrix[i - 1][j] &&
                                inputMatrix[i][j] > inputMatrix[i - 1][j + 1] && inputMatrix[i][j] > inputMatrix[i][j + 1] &&
                                inputMatrix[i][j] > inputMatrix[i + 1][j] && inputMatrix[i][j] > inputMatrix[i + 1][j + 1]) {
                            resultList.add(inputMatrix[i][j]);
                        } else if (j == arrayDimension - 1 && inputMatrix[i][j] > inputMatrix[i - 1][j - 1]
                                && inputMatrix[i][j] > inputMatrix[i - 1][j] && inputMatrix[i][j] > inputMatrix[i][j - 1] &&
                                inputMatrix[i][j] > inputMatrix[i + 1][j - 1] && inputMatrix[i][j] > inputMatrix[i + 1][j]) {
                            resultList.add(inputMatrix[i][j]);
                        }
                    }
                    if (i == 0) {
                        if (j == 0 && inputMatrix[i][j] > inputMatrix[i][j + 1] && inputMatrix[i][j] > inputMatrix[i + 1][j]
                                && inputMatrix[i][j] > inputMatrix[i + 1][j + 1]) {
                            resultList.add(inputMatrix[i][j]);
                        }
                        if (j == arrayDimension - 1 && inputMatrix[i][j] > inputMatrix[i][j - 1] && inputMatrix[i][j] > inputMatrix[i + 1][j - 1]
                                && inputMatrix[i][j] > inputMatrix[i + 1][j]) {
                            resultList.add(inputMatrix[i][j]);
                        }
                    }
                    if (i == arrayDimension - 1) {
                        if (j == 0 && inputMatrix[i][j] > inputMatrix[i - 1][j]
                                && inputMatrix[i][j] > inputMatrix[i - 1][j + 1]
                                && inputMatrix[i][j] > inputMatrix[i][j + 1]) {
                            resultList.add(inputMatrix[i][j]);
                        }
                        if (j == arrayDimension - 1 && inputMatrix[i][j] > inputMatrix[i - 1][j]
                                && inputMatrix[i][j] > inputMatrix[i - 1][j - 1]
                                && inputMatrix[i][j] > inputMatrix[i][j - 1]) {
                            resultList.add(inputMatrix[i][j]);
                        }
                    }
                }
            }
            if (resultList.size() == 0) {
                System.out.println("NOT FOUND");
            } else {
                System.out.println(Collections.max(resultList));
            }
        } else {
            System.out.println(1);
        }
    }

    @Override
    public void task27() {
        Scanner scanner = new Scanner(System.in);
        int arrayDimension = scanner.nextInt();
        scanner.nextLine();
        Integer[][] inputMatrix = new Integer[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            String[] scannerString = scanner.nextLine().split(" ");
            for (int j = 0; j < arrayDimension; j++) {
                inputMatrix[i][j] = Integer.parseInt(scannerString[j]);
            }
        }
        Integer[][] sumStrings = new Integer[arrayDimension][2];
        for (int i = 0; i < arrayDimension; i++) {
            int sumString = 0;
            for (int j = 0; j < arrayDimension; j++) {
                sumString += Math.abs(inputMatrix[j][i]);
            }
            sumStrings[i][0] = i;
            sumStrings[i][1] = sumString;
        }
        List<Integer> interList = new ArrayList<>();
        Arrays.stream(sumStrings)
                .sorted((a, b) -> !a[1].equals(b[1]) ? a[1].compareTo(b[1]) : -1)
                .forEach(o -> interList.add(o[0]));
        Integer[][] resultMatrix = new Integer[arrayDimension][arrayDimension];
        for (int i = 0; i < arrayDimension; i++) {
            for (int j = 0; j < arrayDimension; j++) {
                resultMatrix[j][i] = inputMatrix[j][interList.get((arrayDimension - 1) - i)];
            }
        }
        System.out.println(arrayDimension);
        printMatrix(resultMatrix, arrayDimension);
    }

    private void printMatrix(Integer[][] matrix, int arrayDimension) {
        for (int i = 0; i < arrayDimension; i++) {
            System.out.print((int) matrix[i][0]);
            for (int j = 1; j < arrayDimension; j++) {
                System.out.printf("\t" + (int) matrix[i][j]);
            }
            System.out.println();
        }
    }

}
