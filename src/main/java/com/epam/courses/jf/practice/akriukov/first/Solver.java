package com.epam.courses.jf.practice.akriukov.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Solver implements ISolver {

    /**
     * Описание:
     * Ввести N строк (каждая с новой строки). Найти среди них самую короткую и самую длинную. Вывести найденные строки
     * и их длину. Если строк, удовлетворяющих условию, более одной - вывести последнюю из них.
     *
     * <p>
     * Формат входных данных:
     * N (целое число, 0 < N < 100) - количество доступных для чтения строк
     * Строка_1
     * Строка_2
     * ...
     * Строка_N
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task1 в выходной поток должны быть выведены две строки следующего вида:
     * System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
     * System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
     */
    @Override
    public void task1() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLines = Integer.parseInt(readLineFromConsole(reader));

        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;
        String minString = "";
        String maxString = "";

        for (int i = 0; i < numberOfLines; i++) {
            String s = readLineFromConsole(reader);
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

    /**
     * Описание:
     * Ввести N строк (каждая с новой строки). Упорядочить и вывести строки в порядке возрастания значений их длины. В
     * случае, если длины строк совпадают - упорядочить их в лексикографическом порядке.
     *
     * <p>
     * Формат входных данных:
     * N (целое число, 0 < N < 100) - количество доступных для чтения строк
     * Строка_1
     * Строка_2
     * ...
     * Строка_N
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task2 в выходной поток должны быть выведены N строк следующего вида:
     * (длина строки): "строка наименьшей длины"
     * (длина строки): "строка большей длины"
     * (длина строки): "строка большей длины"
     * ...
     * (длина строки): "строка наибольшей длины"
     */
    @Override
    public void task2() {
        String[] lines = readLinesToStringArray();
        Arrays.sort(lines, new compByLength());
        for (String s: lines) {
            System.out.printf("(%d): \"%s\"%n", s.length(), s);
        }
    }

    /**
     * Описание:
     * Ввести N строк. Вывести те строки, длина которых меньше средней. Под 'средней' подразумевается
     * среднеарифметическая величина длины всех строк, округленная до целого в меньшую сторону.
     *
     * <p>
     * Формат входных данных:
     * N (целое число) - количество доступных для чтения строк
     * N строк
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task3 в выходной поток должна быть выведена средняя длина (округленная до целого в
     * меньшую сторону) и строки, удовлетворяющие условию. Порядок появления строк в выходном потоке должен совпадать с
     * порядком их появления во входном.
     * AVERAGE (средняя длина)
     * (длина строки): "строка с длиной меньше средней"
     * ...
     * (длина строки): "строка с длиной меньше средней"
     */
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

    /**
     * Описание:
     * Ввести N слов, состоящих из символов английского алфавита. Найти слово, в котором число различных символов
     * минимально. Символы верхнего и нижнего регистра считать различными. Если таких слов несколько, найти первое из
     * них.
     *
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task4 в выходной поток должно быть выведено слово, содержащее наименьшее число
     * различных символов.
     */
    @Override
    public void task4() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfWords = Integer.parseInt(readLineFromConsole(reader));
        int minNumberOfLetters = Integer.MAX_VALUE;
        String minNumberOfLetterString = "";
        String[] strings = readLineFromConsole(reader).split(" ");
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

    /**
     * Описание:
     * Ввести N слов. Найти количество слов, содержащих только символы латинского алфавита, а среди них – количество
     * слов с равным числом гласных и согласных букв.
     *
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task5 в выходной поток должно быть выведено количество слов, состоящих только из
     * символов латинского алфавита и содержащих равное количество гласных и согласных букв (количество различных
     * вхождений одной буквы в слове учитывается).
     */
    @Override
    public void task5() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfWords = Integer.parseInt(readLineFromConsole(reader));
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        String[] strings = readLineFromConsole(reader).split(" ");
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

    /**
     * Описание:
     * Ввести N слов. Найти слово, символы в котором идут в строгом порядке возрастания их кодов. Если таких слов
     * несколько, найти первое из них.
     *
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task6 в выходной поток должно быть выведено слово, коды символов которого следуют
     * в порядке возрастания (каждый следующий строго больше предыдущего). В случае, если такое слово не найдено, в
     * поток должно быть выведено "NOT FOUND". Слова состоящие из одного символа не учитывать.
     */
    @Override
    public void task6() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfWords = Integer.parseInt(readLineFromConsole(reader));
        String[] strings = readLineFromConsole(reader).split(" ");
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

    /**
     * Описание:
     * Ввести N слов. Найти слова, состоящие только из различных символов. В случае, если слово встречается более одного
     * раза - вывести его единожды.
     *
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task7 в выходной поток должна быть выведена строка, содержащая найденные слова,
     * разделенные пробелами. В случае, если не найдено ни одного слова, удовлетворяющего условию - в поток должно быть
     * выведено "NOT FOUND".
     */
    @Override
    public void task7() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfWords = Integer.parseInt(readLineFromConsole(reader));
        StringBuilder resultString = new StringBuilder();
        String[] strings = readLineFromConsole(reader).split(" ");
        Set<String> printedWords = new HashSet<>();
        for (String str : strings) {
            if (printedWords.contains(str)) {
                continue;
            } else {
                Set<Character> word = new HashSet<>();
                for (int i = 0; i < str.length(); i++) {
                    word.add(str.charAt(i)); //if non-register sensitive algorithm is needed
                                            // Use Character.toLowerCase(str.charAt(i))
                }
                if (str.length() == word.size()) {
                    resultString.append(str);
                    resultString.append(" ");
                    printedWords.add(str);
                }
            }
        }
        if (printedWords.size() == 0) {
            System.out.println("NOT FOUND");
        } else {
            System.out.println(resultString.toString().trim());
        }
    }

    /**
     * Описание:
     * Ввести N слов. Помимо обычных слов, во входной последовательности могут встречаться целые числа. Среди них
     * необходимо найти число-палиндром (одинаково читающееся в обоих направлениях). Если таких чисел больше одного,
     * найти второе из них. Ограничения на размер числа нет. Одна цифра является палиндромом. Числа могут быть
     * большими.
     *
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task8 в выходной поток должны быть выведено найденное число-палиндром. В случае,
     * если не найдено ни одного такого числа - в поток должно быть выведено "NOT FOUND".
     */
    @Override
    public void task8() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfWords = Integer.parseInt(readLineFromConsole(reader));
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
        String[] strings = readLineFromConsole(reader).split(" ");
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

    /**
     * Описание:
     * Написать программу, которая выводит числа от 1 до N^2 в виде матрицы NxN слева направо и сверху вниз.
     *
     * <p>
     * Формат входных данных:
     * N (целое число) - размерность матрицы
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task9 в выходной поток должны быть выведены N строк, соответствующие строкам
     * матрицы. Внутри строки элементы матрицы разделяются знаком табуляции ('\t').
     */
    @Override
    public void task9() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int matrixDim = Integer.parseInt(readLineFromConsole(reader));
        int counter = 1;
        for (int i = 0; i < matrixDim; i++) {
            for (int j = 0; j < matrixDim; j++) {
                if (j == 0) {
                    System.out.print(counter);
                } else {
                    System.out.print("\t" + counter);
                }
                counter++;
            }
            System.out.println();
        }
    }

    /**
     * Описание:
     * Написать программу, позволяющую корректно находить корни квадратного уравнения. Параметры уравнения должны
     * задаваться из стандартного входа.
     *
     * <p>
     * Формат входных данных:
     * Во входном потоке последовательно расположены три целочисленных коэффициента A, B и C, задающие уравнение в общем
     * виде.
     *
     * <p>
     * Формат выходных данных:
     * В результате решения уравнения может быть получено три варианта ответа:
     * No solution
     * One solution: корень уравнения
     * Two solutions: первый корень уравнения, второй корень уравнения
     * В результате выполнения метода task10, в выходной поток посылается строка, соответствующая найденному решению.
     * Значения корней округляются до 2 знаков после запятой. Использовать округление BigDecimal.HALF_UP. В качестве
     * десятичного разделителя используется точка..
     */
    @Override
    public void task10() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        int b = reader.nextInt();
        int c = reader.nextInt();
        int d = b * b - 4 * a * c;

        if (d < 0) {
            System.out.println("No solution");
        } else if (d == 0) {
            BigDecimal x = new BigDecimal(-(double) b / (2 * a));
            x.setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("One solution: " + x);
        } else {
            BigDecimal x1 = new BigDecimal(((-b) - Math.sqrt((double) d)) / (2 * a))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal x2 = new BigDecimal(((-b) + Math.sqrt((double) d)) / (2 * a))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("Two solutions: " + x1 + ", " + x2);
        }
    }

    /**
     * Описание:
     * Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу. При реализации
     * использовать оператор switch. Осуществить проверку корректности ввода числа.
     *
     * <p>
     * Формат входных данных:
     * Во входном потоке расположена строка. Необходимо проверить, что она является целым числом в диапазоне 1-12.
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task11, в выходной поток посылается строка, соответствующая названию месяца (на
     * английском языке) с указанным порядковым номером (регистр букв не важен). В случае, если введенная строка не
     * удовлетворяет заданным условиям - в выходной поток посылается сообщение "INCORRECT INPUT DATA".
     */
    @Override
    public void task11() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int monthNumber = Integer.parseInt(readLineFromConsole(reader));

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

    /**
     * Описание:
     * Упорядочить строки матрицы размерности N в порядке возрастания значений элементов k-го столбца.
     *
     * <p>
     * Формат входных данных:
     * k (целое число, 0 <= k < N) - номер столбца
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task12 в выходной поток должна быть выведена преобразованная матрица
     */
    @Override
    public void task12() {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt(); //column to sort
        int[][] matrix = matrixInput(scanner);

        Arrays.sort(matrix, (o1, o2) -> o1[k] - o2[k]);

        matrixOutput(matrix);
    }

    /**
     * Описание:
     * Выполнить циклический сдвиг матрицы размерности N на k позиций вниз.
     *
     * <p>
     * Формат входных данных:
     * k (целое число) - количество сдвигов
     * Если k > 0 - производится циклический сдвиг матрицы вниз
     * Если k < 0 - производится циклический сдвиг матрицы вверх
     * Если k = 0 - матрица остается без изменений
     * Матрица (описание представления матриц)
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task13 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task13() {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt(); //move position
        int[][] inMatrix = matrixInput(scanner);
        int[][] outMatrix = moveRows(inMatrix, k);
        matrixOutput(outMatrix);

    }

    /**
     * Описание:
     * Найти наибольшее число строго возрастающих элементов последовательности, идущих подряд. Оператор отношения можно
     * определить на множестве, включающем более одного элемента.
     *
     * <p>
     * Формат входных данных:
     * N (целое число) - количество элементов последовательности
     * N целых чисел - элементы последовательности
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task14 в выходной поток должно быть выведено число, отображающее количество
     * элементов на самом большом возрастающем участке последовательности.
     */
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
        if (maxLength == 1) {
            System.out.println(0); //weired logic
        } else {
            System.out.println(maxLength);
        }

    }

    /**
     * Описание:
     * Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task15 в выходной поток должна быть выведена указанная сумма. В случае, если в
     * строке нет двух положительных элементов - сумма от это строки полагается равной нулю. Сумма между двумя рядом
     * расположенными элементами также равна нулю
     */
    @Override
    public void task15() {
        Scanner scanner = new Scanner(System.in);
        int dim = scanner.nextInt();
        int[][] a = new int[dim][dim];

        int sum = 0;
        for (int i = 0; i < dim; i++) {
            int count = 0;

            for (int j = 0; j < dim; j++) {
                a[i][j] = scanner.nextInt();
                if (a[i][j] > 0) {
                    count++;
                }
                if (count == 1 && a[i][j] < 0) {
                    sum += a[i][j];
                }
            }

        }

        System.out.println(sum);
    }

    /**
     * Описание:
     * Повернуть матрицу на 90 градусов против часовой стрелки.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task16 в выходной поток должна быть выведена преобразованная матриц
     */
    @Override
    public void task16() {
        Scanner scanner = new Scanner(System.in);
        int[][] inMatrix = matrixInput(scanner);
        int dim = inMatrix.length;
        int[][] outMatrix = new int[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                outMatrix[i][j] = inMatrix[j][dim - i - 1];
            }
        }
        matrixOutput(outMatrix);
    }

    /**
     * Описание:
     * Вычислить определитель матрицы.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task17 в выходной поток должен быть выведен определитель матрицы, представленный
     * целым числом.
     */
    @Override
    public void task17() {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = matrixInput(scanner);
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

    /**
     * Описание:
     * Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task18 в выходной поток должна быть выведена преобразованная матрица. Так как
     * матрица в результате преобразования может перестать быть квадратной - несколько изменяется формат её
     * представления при выводе в выходной поток:
     * N (целое число) - количество строк
     * M (целое число) - количество столбцов
     * N * M целых чисел, являющихся элементами матрицы
     */
    @Override
    public void task18() {
        Scanner scanner = new Scanner(System.in);
        int inMatrix[][] = matrixInput(scanner);
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
        int outMatrix[][] = new int[inMatrix.length - linesToDelete.size()][inMatrix[0].length
                - columnsToDelete.size()];
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

    /**
     * Описание:
     * Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task19 в выходной поток должна быть выведена преобразованная матрица. Так как
     * матрица в результате преобразования может перестать быть квадратной - несколько изменяется формат её
     * представления при выводе в выходной поток:
     * N (целое число) - количество строк
     * M (целое число) - количество столбцов
     * N * M целых чисел, являющихся элементами матрицы
     */
    @Override
    public void task19() {
        Scanner scanner = new Scanner(System.in);
        int inMatrix[][] = matrixInput(scanner);
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

        int outMatrix[][] = new int[inMatrix.length - linesToDelete.size()][inMatrix[0].length
                - columnsToDelete.size()];
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

    /**
     * Описание:
     * В матрице найти минимальный элемент и переместить его на место заданного элемента путем перестановки строк и
     * столбцов. Гарантируется, что минимальный элемент в матрице встречается ровно один раз.
     *
     * <p>
     * Формат входных данных:
     * X (целое число, 0 <= X < N) - номер строки
     * Y (целое число, 0 <= Y < N) - номер столбца
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task20 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task20() {
        Scanner scanner = new Scanner(System.in);
        int moveRowPosition = scanner.nextInt(); //throws exception if copy-paste input data in console,
                                                // but with typing works good
        int moveColumnPosition = scanner.nextInt(); //use scanner for copy-paste
        int matrix[][] = matrixInput(scanner);
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

    /**
     * Описание:
     * Преобразовать строки матрицы таким образом, чтобы элементы, равные нулю, располагались после всех остальных.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task21 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task21() {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = matrixInput(scanner);
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

    /**
     * Описание:
     * Округлить все элементы матрицы до целого числа.
     * Использовать округление к ближайшему целому — число округляется до целого с использованием Math.round();
     *
     * <p>
     * Формат входных данных:
     * Матрица. В данном задании входная матрица в качестве элементов содержит не целые числа а вещественные (в формате
     * Double). Для их извлечения нужно воспользоваться методом scanner.nextDouble() класса Scanner.
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task22 в выходной поток должна быть выведена преобразованная матрица, в которой
     * элементы являются целыми числами. Вывод матрицы осуществляется по обычным правилам.
     */
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
     * Описание:
     * Найти количество всех седловых точек матрицы. Матрица А имеет седловую точку (i, j), если А[i, j] является
     * минимальным элементом в i-й строке и максимальным в j-м столбце.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task23 в выходной поток должна быть выведено целое число, отображающее количество
     * седловых точек в матрице.
     */
    @Override
    public void task23() {
        Scanner scanner = new Scanner(System.in);
        int matrix[][] = matrixInput(scanner);
        int minElement;
        int maxElement;
        int saddlePointsNumber = 0;

        for (int i = 0; i < matrix.length; i++) {
            minElement = matrix[i][0];
            int minElementInColumn = 0;
            boolean minFlag = true;

            for (int j = 1; j < matrix.length; j++) {
                if (minElement >= matrix[i][j]) {
                    if (minElement == matrix[i][j]) {
                        minFlag = false;
                    } else {
                        minElement = matrix[i][j];
                        minElementInColumn = j;
                        minFlag = true;
                    }
                }
            }

            if (minFlag) {
                boolean maxFlag = true;
                maxElement = matrix[i][minElementInColumn];

                for (int j = 0; j < matrix.length; j++) {
                    if (j != i && maxElement <= matrix[j][minElementInColumn]) {
                        maxFlag = false;
                        break;
                    }
                }
                if (maxFlag) {
                    saddlePointsNumber++;
                }
            }
        }

        System.out.println(saddlePointsNumber);
    }

    /**
     * Описание:
     * Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках полученной матрицы
     * возрастала.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task24 в выходной поток должна быть выведена преобразованная матрица. Если сумма
     * нескольких строк совпадает - в результирующей матрицы они должны следовать в том же порядке что и в исходной.
     */
    @Override
    public void task24() {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = matrixInput(scanner);

        Arrays.sort(matrix, (o1, o2) -> {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < matrix.length; i++) {
                sum1 += o1[i];
                sum2 += o2[i];
            }
            return sum1 - sum2;
        });

        matrixOutput(matrix);
    }

    /**
     * Описание:
     * Найти число локальных минимумов. Соседями элемента матрицы назовем элементы, имеющие с ним общую сторону или
     * угол. Элемент матрицы называется локальным минимумом, если он строго меньше всех своих соседей.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task25 в выходной поток должно быть выведено число, соответствующее количеству
     * локальных минимумов в матрице.
     */
    @Override
    public void task25() {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = matrixInput(scanner);
        int result = 0;
        if (matrix.length == 1) {
            System.out.println(1);
            return;
        }

        int[][] servMatrix = new int[matrix.length + 2][matrix.length + 2];
        int max = matrix[0][0];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                servMatrix[i + 1][j + 1] = matrix[i][j];
                max = max(max, matrix[i][j]);
            }
        }

        for (int i = 0; i < servMatrix.length; i++) {
            servMatrix[i][0] = max;
            servMatrix[i][servMatrix.length - 1] = max;
            servMatrix[0][i] = max;
            servMatrix[servMatrix.length - 1][i] = max;
        }

        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix.length + 1; j++) {
                if (servMatrix[i][j] < servMatrix[i - 1][j - 1] &&
                        servMatrix[i][j] < servMatrix[i - 1][j] &&
                        servMatrix[i][j] < servMatrix[i - 1][j + 1] &&
                        servMatrix[i][j] < servMatrix[i][j - 1] &&
                        servMatrix[i][j] < servMatrix[i][j + 1] &&
                        servMatrix[i][j] < servMatrix[i + 1][j - 1] &&
                        servMatrix[i][j] < servMatrix[i + 1][j] &&
                        servMatrix[i][j] < servMatrix[i + 1][j + 1]) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    /**
     * Описание:
     * Найти наибольший среди локальных максимумов. Соседями элемента матрицы назовем элементы, имеющие с ним общую
     * сторону или угол. Элемент матрицы называется локальным максимумом, если он строго больше всех своих соседей.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task26 в выходной поток должно быть выведено число, соответствующее наибольшему
     * локальному максимуму. Если локальные максимумы в матрице отсутствуют - выводится строка "NOT FOUND".
     */
    @Override
    public void task26() {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = matrixInput(scanner);
        boolean localMaxExists = false;
        int result = 0;
        if (matrix.length == 1) {
            System.out.println(matrix[0][0]);
            return;
        }

        int[][] servMatrix = new int[matrix.length + 2][matrix.length + 2];
        int min = matrix[0][0];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                servMatrix[i + 1][j + 1] = matrix[i][j];
                min = min(min, matrix[i][j]);
            }
        }

        for (int i = 0; i < servMatrix.length; i++) {
            servMatrix[i][0] = min;
            servMatrix[i][servMatrix.length - 1] = min;
            servMatrix[0][i] = min;
            servMatrix[servMatrix.length - 1][i] = min;
        }

        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix.length + 1; j++) {
                if (servMatrix[i][j] > servMatrix[i - 1][j - 1]
                        && servMatrix[i][j] > servMatrix[i - 1][j]
                        && servMatrix[i][j] > servMatrix[i - 1][j + 1]
                        && servMatrix[i][j] > servMatrix[i][j - 1]
                        && servMatrix[i][j] > servMatrix[i][j + 1]
                        && servMatrix[i][j] > servMatrix[i + 1][j - 1]
                        && servMatrix[i][j] > servMatrix[i + 1][j]
                        && servMatrix[i][j] > servMatrix[i + 1][j + 1]) {
                    if (!localMaxExists) {
                        result = servMatrix[i][j];
                        localMaxExists = true;
                    } else {
                        result = max(result, servMatrix[i][j]);
                    }
                }
            }
        }

        if (localMaxExists) {
            System.out.println(result);
        } else {
            System.out.println("NOT_FOUND");
        }
    }

    /**
     * Описание:
     * Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы значения их характеристик убывали.
     * Характеристикой столбца прямоугольной матрицы называется сумма модулей его элементов. Если значения
     * характеристики совпадают - столбцы должны следовать в том же порядке, что и в исходной матрице.
     *
     * <p>
     * Формат входных данных:
     * Матрица
     *
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task27 в выходной поток должна быть выведена преобразованная матрица, в которой
     * столбцы отсортированы по убыванию их характеристики.
     */
    @Override
    public void task27() {
        class Struct {
            private int[] column;
            private int sumInColumn;
            private int columnId;

            private Struct(int[] column, int sumInColumn, int columnIndex) {
                this.column = column;
                this.sumInColumn = sumInColumn;
                this.columnId = columnIndex;
            }

            private int getSumInColumn() {
                return sumInColumn;
            }

            private int[] getColumn() {
                return column.clone();
            }

            private int getColumnId() {
                return columnId;
            }
        }

        Scanner scanner = new Scanner(System.in);
        int[][] matrix = matrixInput(scanner);
        List<Struct> structList = new ArrayList<>();

        for (int j = 0; j < matrix[0].length; j++) {
            int sum = 0;
            int[] column = new int[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                column[i] = matrix[i][j];
                sum += abs(matrix[i][j]);
            }
            structList.add(new Struct(column, sum, j));
        }

        structList.sort(Comparator.comparingInt(Struct::getSumInColumn).reversed()
                .thenComparing(Struct::getColumnId));

        int[][] result = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < result[0].length; j++) {
            int[] column = structList.get(j).getColumn();
            for (int i = 0; i < result.length; i++) {
                result[i][j] = column[i];
            }
        }

        matrixOutput(result);
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLines = Integer.parseInt(readLineFromConsole(reader));
        String[] lines = new String[numberOfLines];

        for (int i = 0; i < numberOfLines; i++) {
            lines[i] = readLineFromConsole(reader);
        }
        return lines;
    }

    /**
     * Reads one line from console
     * @return entered in console line or null
     */
    public static String readLineFromConsole(BufferedReader reader) {
        try {
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
    public static int[][] matrixInput(Scanner scanner) {
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
