package com.epam.courses.jf.practice.sskovalevskiy.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.System;

/**
 * Created by asus on 17.10.2017.
 *
 * @author ss.kovalevskiy
 */
public class Solver implements ISolver {

    private final static String NOT_FOUND = "NOT FOUND";
    /**
     * Описание:
     * Ввести N строк, найти самую короткую и самую длинную строки. Вывести найденные строки и их длину.
     * Если строк, удовлетворяющих условию, более одной - вывести последнюю из них.
     * <p>
     * Формат входных данных:
     * N (целое число, 0 < N < 100) - количество доступных для чтения строк
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task1 в выходной поток должны быть выведены две строки следующего вида:
     * System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
     * System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
     */
    @Override
    public void task1() {

        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;
        String minString = null;
        String maxString = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine());

            for (int i = 0; i < N; i++) {
                String s = reader.readLine();

                if (minLength >= s.length()) {
                    minLength = s.length();
                    minString = s;
                }

                if (maxLength <= s.length()) {
                    maxLength = s.length();
                    maxString = s;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }

    /**
     * Описание:
     * Ввести N строк. Упорядочить и вывести строки в порядке возрастания значений их длины.
     * В случае, если длины строк совпадают - упорядочить их в лексикографическом порядке.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество доступных для чтения строк
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

        List<String> strings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int N = Integer.parseInt(reader.readLine());

            for (int i = 0; i < N; i++) {
                String s = reader.readLine();
                strings.add(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Comparator<String> comparator = (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            } else {
                return o1.length() - o2.length();
            }
        };

        strings.sort(comparator);

        for (String string : strings) {
            System.out.printf("(%d): \"%s\"%n", string.length(), string);
        }
    }

    /**
     * Описание:
     * Ввести N строк. Вывести те строки, длина которых меньше средней.
     * Под 'средней' подразумевается среднеарифметическая величина длины всех строк,
     * округленная до целого в меньшую сторону.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество доступных для чтения строк
     * N строк
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task3 в выходной поток должна быть выведена средняя длина (округленная до целого
     * в меньшую сторону) и строки, удовлетворяющие условию. Порядок появления строк в выходном потоке должен совпадать
     * с порядком их появления во входном.
     * <p>
     * AVERAGE (средняя длина)
     * (длина строки): "строка с длиной меньше средней"
     * ...
     * (длина строки): "строка с длиной меньше средней"
     */
    @Override
    public void task3() {

        List<String> strings = new ArrayList<>();
        int allStringsLength = 0;
        int averageLength = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int N = Integer.parseInt(reader.readLine());

            for (int i = 0; i < N; i++) {
                String s = reader.readLine();
                strings.add(s);
                allStringsLength += s.length();
            }

            averageLength = allStringsLength / N;

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("AVERAGE (%d)%n", averageLength);

        for (String string : strings) {
            if (string.length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", string.length(), string);
            }
        }
    }

    /**
     * Описание:
     * Ввести N слов, состоящих из символов английского алфавита. Найти слово, в котором число различных символов минимально.
     * Символы верхнего и нижнего регистра считать различными. Если таких слов несколько, найти первое из них.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task4 в выходной поток должно быть выведено слово, содержащее наименьшее число различных символов.
     */
    @Override
    public void task4() {

        Scanner reader = new Scanner(System.in);
        int N = reader.nextInt();

        String word = null;
        Set<Character> wordLetters = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String s = reader.next();

            Set<Character> characters = new HashSet<>();
            for (int j = 0; j < s.length(); j++) {
                characters.add(s.charAt(j));
            }

            if ((word == null) || (wordLetters.size() > characters.size())) {
                word = s;
                wordLetters = characters;
            }
        }

        System.out.println(word);
    }

    /**
     * Описание:
     * Ввести N слов. Найти количество слов, содержащих только символы латинского алфавита,
     * а среди них – количество слов с равным числом гласных и согласных букв.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task5 в выходной поток должно быть выведено количество слов, состоящих только из
     * символов латинского алфавита и содержащих равное количество гласных и согласных букв (количество различных
     * вхождений одной буквы в слове учитывается).
     */
    @Override
    public void task5() {

        Scanner reader = new Scanner(System.in);
        int N = reader.nextInt();

        List<String> englishWords = new ArrayList<>();
        for (int i = 0; i < N; i++) {

            String word = reader.next();
            if (word.matches("[A-Za-z]+")) {

                Pattern vocals = Pattern.compile("(?iu)[aeiouy]");
                Matcher m = vocals.matcher(word);

                int vocalCounter = 0;
                while (m.find()) {
                    vocalCounter++;
                }

                if (vocalCounter == word.length() / 2.0) {
                    englishWords.add(word);
                }
            }
        }

        System.out.println(englishWords.size());
    }

    /**
     * Описание:
     * Ввести N слов. Найти слово, символы в котором идут в строгом порядке возрастания их кодов.
     * Если таких слов несколько, найти первое из них.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task6 в выходной поток должно быть выведено слово, коды символов которого следуют
     * в порядке возрастания (каждый следующий строго больше предыдущего). В случае, если такое слово не найдено, в поток
     * должно быть выведено "NOT FOUND". Слова состоящие из одного символа не учитывать.
     */
    @Override
    public void task6() {

        Scanner reader = new Scanner(System.in);
        int N = reader.nextInt();

        List<String> words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            words.add(reader.next());
        }

        int amountOfCorrectWords = 0;
        for (String word : words) {
            boolean isWordCorrect = true;

            if (word.length() != 1) {
                for (int i = 1; i < word.length(); i++) {

                    if ((word.charAt(i - 1) >= word.charAt(i))) {
                        isWordCorrect = false;
                        break;
                    }
                }
            } else isWordCorrect = false;

            if (isWordCorrect) {
                System.out.println(word);
                amountOfCorrectWords++;
                break;
            }
        }
        if (amountOfCorrectWords == 0) {
            System.out.println(NOT_FOUND);
        }
    }

    /**
     * Описание:
     * Ввести N слов. Найти слова, состоящие только из различных символов.
     * В случае, если слово встречается более одного раза - вывести его единожды.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task7 в выходной поток должна быть выведена строка, содержащая найденные слова,
     * разделенные пробелами. В случае, если не найдено ни одного слова, удовлетворяющего условию - в поток должно быть
     * выведено "NOT FOUND".
     */
    @Override
    public void task7() {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        Set<String> words = new LinkedHashSet<>();

        for (int i = 0; i < N; i++) {
            String s = scanner.next();
            int charsCount = (int) s.chars().distinct().count();
            if (charsCount == s.length()) {
                words.add(s);
            }
        }

        if (words.isEmpty()) {
            System.out.println(NOT_FOUND);
        } else {
            StringJoiner space = new StringJoiner(" ");
            for (String s : words) {
                space.add(s);
            }
            System.out.printf("%s%n", space.toString());
        }
    }

    /**
     * Описание:
     * Ввести N слов. Помимо обычных слов, во входной последовательности могут встречаться целые числа. Среди них
     * необходимо найти число-палиндром (одинаково читающееся в обоих направлениях). Если таких чисел больше одного,
     * найти второе из них. Ограничения на размер числа нет. Одна цифра является палиндромом.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task8 в выходной поток должны быть выведено найденное число-палиндром.
     * В случае, если не найдено ни одного такого числа - в поток должно быть выведено "NOT FOUND".
     */
    @Override
    public void task8() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int N = Integer.parseInt(reader.readLine());

            List<String> words = new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
            long palindrom = -1;
            int amountOfNumberPalindroms = 0;
            for (String s : words) {
                if (s.matches("\\d+") &&
                        ((s.length() == 1) || (new StringBuilder(s).reverse().toString().equals(s)))) {

                    palindrom = Long.parseLong(s);
                    amountOfNumberPalindroms++;

                } else continue;

                if (amountOfNumberPalindroms == 2) {
                    System.out.println(palindrom);
                    break;
                }
            }

            if (amountOfNumberPalindroms == 1) {
                System.out.println(palindrom);
            } else if (amountOfNumberPalindroms == 0) {
                System.out.println(NOT_FOUND);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Описание:
     * Написать программу, которая выводит числа от 1 до N^2 в виде матрицы NxN слева направо и сверху вниз.
     * <p>
     * Формат входных данных:
     * N (целое число) - размерность матрицы
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task9 в выходной поток должны быть выведены N строк, соответствующие строкам матрицы.
     * Внутри строки элементы матрицы разделяются знаком табуляции ('\t').
     */
    @Override
    public void task9() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine());

            for (int i = 0; i < N; i++) {
                for (int j = 1; j <= N; j++) {
                    System.out.print(i * N + j + (j != N ? "\t" : "\n"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Описание:
     * Написать программу, позволяющую корректно находить корни квадратного уравнения.
     * Параметры уравнения должны задаваться с командной строки.
     * <p>
     * Формат входных данных:
     * Во входном потоке последовательно расположены три целочисленных коэффициента A, B и C, задающие уравнение в общем виде.
     * <p>
     * Формат выходных данных:
     * В результате решения уравнения может быть получено три варианта ответа:
     * No solution
     * One solution: корень уравнения
     * Two solutions: первый корень уравнения, второй корень уравнения
     * В результате выполнения метода task10, в выходной поток посылается строка, соответствующая найденному решению.
     * Значения корней округляются до 2 знаков после запятой. В качестве десятичного разделителя используется точка.
     */
    @Override
    public void task10() {

        Scanner reader = new Scanner(System.in);

        int A = reader.nextInt();
        int B = reader.nextInt();
        int C = reader.nextInt();

        int D = B * B - 4 * A * C;
        BigDecimal x;
        if (D < 0) {
            System.out.printf("No solution%n");
        } else if (D == 0) {
            x = new BigDecimal(-(double) B / (2 * A));
            x.setScale(2, BigDecimal.ROUND_HALF_UP);
            String s = x.toString();
            System.out.printf("One solution: %s%n", s);
        } else {
            BigDecimal x1 = new BigDecimal(
                    ((-B) - Math.sqrt((double) D)) / (2 * A)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal x2 = new BigDecimal(
                    ((-B) + Math.sqrt((double) D)) / (2 * A)).setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("Two solutions: " + x1 + ", " + x2);
        }
    }

    /**
     * Описание:
     * Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу.
     * При реализации использовать оператор switch. Осуществить проверку корректности ввода числа.
     * <p>
     * Формат входных данных:
     * Во входном потоке расположена строка. Необходимо проверить, что она является целым числом в диапазоне 1-12.
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task11, в выходной поток посылается строка, соответствующая названию месяца
     * (на английском языке) с указанным порядковым номером (регистр букв не важен). В случае, если введенная строка не
     * удовлетворяет заданным условиям - в выходной поток посылается сообщение "INCORRECT INPUT DATA".
     */
    @Override
    public void task11() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int number = Integer.parseInt(reader.readLine());
            switch (number) {
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
        } catch (IOException | NumberFormatException ex) {
            System.out.println("INCORRECT INPUT DATA");
        }

    }

    /**
     * Описание:
     * Упорядочить строки матрицы размерности N в порядке возрастания значений элементов k-го столбца.
     * <p>
     * Формат входных данных:
     * k (целое число, 0 <= k < N) - номер столбца
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task12 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task12() {

        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        Integer[][] matrix = Matrix.readMatrix(scanner);

        Arrays.sort(matrix, (o1, o2) -> o1[k] - o2[k]);

        Matrix.printMatrix(matrix);
    }

    /**
     * Описание:
     * Выполнить циклический сдвиг матрицы размерности N на k позиций вниз.
     * <p>
     * Формат входных данных:
     * k (целое число) - количество сдвигов
     * Если k > 0 - производится циклический сдвиг матрицы вниз
     * Если k < 0 - производится циклический сдвиг матрицы вверх
     * Если k = 0 - матрица остается без изменений
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task13 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task13() {

        Scanner scanner = new Scanner(System.in);
        final int k = scanner.nextInt();

        Integer[][] matrix = Matrix.readMatrix(scanner);

//        Логика поведения элементов матрицы при сдвиге
//          [0 | 1 | 2] -> [2 | 0 | 1] -> [1 | 2 | 0] -> [0 | 1 | 2]
//          [0 | 1 | 2 | 3] -> [3 | 0 | 1 | 2] -> [2 | 3 | 0 | 1] -> [1 | 2 | 3 | 0]
//          Если при вычитании из индекса количества смещений получается отрицательное число - добавляем matrix.length-1

        for (int i = 0; i < Math.abs(k); i++) {
//            В цикле каждый раз элементы массива смещаются вниз на 1 позицию при k > 0,
//            либо поднимаются вверх на 1 позицию при k < 0.
            if (k > 0) {
                Integer[] temp = matrix[matrix.length - 1];
                for (int j = matrix.length - 1; j > 0; j--) {
                    matrix[j] = matrix[j - 1];
                }
                matrix[0] = temp;
            } else {
                Integer[] temp = matrix[0];
                for (int j = 0; j < matrix.length - 1; j++) {
                    matrix[j] = matrix[j + 1];
                }
                matrix[matrix.length - 1] = temp;
            }
        }

        Matrix.printMatrix(matrix);
    }

    /**
     * Описание:
     * Найти наибольшее число строго возрастающих элементов последовательности, идущих подряд.
     * Оператор отношения можно определить на множестве, включающем более одного элемента.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество элементов последовательности
     * N целых чисел - элементы последовательности
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task14 в выходной поток должно быть выведено число, отображающее количество
     * элементов на самом большом возрастающем участке последовательности.
     */
    @Override
    public void task14() {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = new ArrayList<>();
        final int N = scanner.nextInt();

        int maxLength = 1;
        int currentLength = 0;
        for (int i = 0; i < N; i++) {
            int currentNumber = scanner.nextInt();
            numbers.add(currentNumber);
            if (i > 0 && currentNumber > numbers.get(i - 1)) {
                currentLength++;
            } else {
                currentLength = 1;
            }
            if (maxLength < currentLength) {
                maxLength = currentLength;
            }
        }

        System.out.println((maxLength > 1) ? maxLength : 0);

    }

    /**
     * Описание:
     * Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
     * <p>
     * Формат входных данных:
     * Матрица (описание представления матриц)
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task15 в выходной поток должна быть выведена указанная сумма. В случае, если в
     * строке нет двух положительных элементов - сумма от это строки полагается равной нулю. Сумма между двумя рядом
     * расположенными элементами также равна нулю.
     */
    @Override
    public void task15() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[][] A = new int[N][N];

        int sum = 0;
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                A[i][j] = scanner.nextInt();
                if (A[i][j] > 0) {
                    count++;
                }
                if (count == 1 && A[i][j] < 0) {
                    sum += A[i][j];
                }
            }
        }

        System.out.println(sum);
    }

    /**
     * Описание:
     * Повернуть матрицу на 90 градусов против часовой стрелки.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task16 в выходной поток должна быть выведена преобразованная матрица.
     * <p>
     * input
     * 00 01 02
     * 10 11 12
     * 20 21 22
     * output
     * 02 12 22
     * 01 11 21
     * 00 10 20
     */
    @Override
    public void task16() {

        Scanner scanner = new Scanner(System.in);

        Integer[][] matrix = Matrix.readMatrix(scanner);

        Integer[][] result = new Integer[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[j][matrix.length - 1 - i];
            }
        }

        Matrix.printMatrix(result);
    }

    /**
     * Описание:
     * Вычислить определитель матрицы.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task17 в выходной поток должен быть выведен определитель матрицы, представленный целым числом.
     */
    @Override
    public void task17() {

        Scanner scanner = new Scanner(System.in);
        Integer[][] matrix = Matrix.readMatrix(scanner);

        if (matrix.length == 1) {
            System.out.println(matrix[0][0]);
        }

        int result = determinant(matrix.length, matrix);

        System.out.println(result);
    }

    private static int determinant(int dimension, Integer[][] matrix) {
        int sum = 0;
        if (dimension == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            for (int i = 0; i < dimension; i++) {
                sum += (int) Math.pow(-1, i) * matrix[0][i] * determinant(dimension - 1, getMinor(matrix, i));
            }
        }
        return sum;
    }

    private static Integer[][] getMinor(Integer[][] matrix, int column) {
        int minorLength = matrix.length - 1;
        Integer[][] minor = new Integer[minorLength][minorLength];
        int dJ;
        for (int i = 1; i <= minorLength; i++) {
            dJ = 0;
            for (int j = 0; j <= minorLength; j++) {
                if (j == column) {
                    dJ = 1;
                } else {
                    minor[i - 1][j - dJ] = matrix[i][j];
                }
            }
        }
        return minor;
    }

    /**
     * Описание:
     * Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
     * <p>
     * Формат входных данных:
     * Матрица (описание представления матриц)
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task18 в выходной поток должна быть выведена преобразованная матрица. Так как
     * матрица в результате преобразования может перестать быть квадратной - несколько изменяется формат её представления
     * при выводе в выходной поток:
     * N (целое число) - количество строк
     * M (целое число) - количество столбцов
     * N * M целых чисел, являющихся элементами матрицы
     */
    @Override
    public void task18() {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[][] matrix = new int[N][N];

        int maxElement = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextInt();
                if (maxElement < matrix[i][j]){
                    maxElement = matrix[i][j];
                }
            }
        }

        Set<Integer> linesToDelete = new HashSet<>();
        Set<Integer> columnsToDelete = new HashSet<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (maxElement == matrix[i][j]) {
                    linesToDelete.add(i);
                    columnsToDelete.add(j);
                }
            }
        }

        Matrix.printReducedMatrix(matrix,linesToDelete,columnsToDelete);
    }

    /**
     * Описание:
     * Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
     * <p>
     * Формат входных данных:
     * Матрица (описание представления матриц)
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task19 в выходной поток должна быть выведена преобразованная матрица. Так как матрица
     * в результате преобразования может перестать быть квадратной - несколько изменяется формат её представления при выводе
     * в выходной поток:
     * N (целое число) - количество строк
     * M (целое число) - количество столбцов
     * N * M целых чисел, являющихся элементами матрицы
     */
    @Override
    public void task19() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[][] matrix = new int[N][N];

        Set<Integer> linesToDelete = new HashSet<>();
        for (int i = 0; i < N; i++) {
            boolean deleteLine = true;
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextInt();
                if (matrix[i][j] != 0) {
                    deleteLine = false;
                }
            }
            if (deleteLine) {
                linesToDelete.add(i);
            }
        }

        Set<Integer> columnsToDelete = new HashSet<>();
        for (int j = 0; j < N; j++) {
            boolean deleteColumn = true;
            for (int i = 0; i < N; i++) {
                if (matrix[i][j] != 0) {
                    deleteColumn = false;
                }
            }
            if (deleteColumn) {
                columnsToDelete.add(j);
            }
        }

        Matrix.printReducedMatrix(matrix,linesToDelete,columnsToDelete);
    }

    /**
     * Описание:
     * В матрице найти минимальный элемент и переместить его на место заданного элемента путем перестановки строк и столбцов.
     * Гарантируется, что минимальный элемент в матрице встречается ровно один раз.
     * <p>
     * Формат входных данных:
     * X (целое число, 0 <= X < N) - номер строки
     * Y (целое число, 0 <= Y < N) - номер столбца
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task20 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task20() {
        Scanner scanner = new Scanner(System.in);
        int X = scanner.nextInt();
        int Y = scanner.nextInt();
        int N = scanner.nextInt();

        int[][] matrix = new int[N][N];

        int minElement = Integer.MAX_VALUE;
        int minElementX = -1;
        int minElementY = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextInt();
                if (minElement > matrix[i][j]) {
                    minElement = matrix[i][j];
                    minElementX = i;
                    minElementY = j;
                }
            }
        }

//        меняем строки местами
        int[] temp = matrix[X];
        matrix[X] = matrix[minElementX];
        matrix[minElementX] = temp;

//        меняем местами значения в столбцах
        for (int i = 0; i < N; i++) {
            int tmp = matrix[i][Y];
            matrix[i][Y] = matrix[i][minElementY];
            matrix[i][minElementY] = tmp;
        }

        Matrix.printMatrix(matrix);
    }

    /**
     * Описание:
     * Преобразовать строки матрицы таким образом, чтобы элементы, равные нулю, располагались после всех остальных.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task21 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task21() {

        Scanner scanner = new Scanner(System.in);
        Integer[][] matrix = Matrix.readMatrix(scanner);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length - 1; j++) {
                if (matrix[i][j] == 0) {
                    if (matrix[i][j + 1] == 0) {
                        int k = j + 2;
                        for (; k < matrix.length; k++) {
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

        Matrix.printMatrix(matrix);
    }

    /**
     * Описание:
     * Округлить все элементы матрицы до целого числа.
     * Использовать округление к ближайшему целому — число округляется до целого, модуль разности с которым у этого числа минимален:
     * если N+1 знак < 5, то N-ый знак сохраняют, а N+1 и все последующие
     * если N+1 знак ≥ 5, то N-ый знак увеличивают на единицу, а N+1 и все последующие обнуляют
     * <p>
     * Формат входных данных:
     * Матрица (описание представления матриц). В данном задании входная матрица в качестве элементов содержит не целые
     * числа а вещественные (в формате Double). Для их извлечения нужно воспользоваться методом scanner.nextDouble() класса Scanner.
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task22 в выходной поток должна быть выведена преобразованная матрица, в которой
     * элементы являются целыми числами. Вывод матрицы осуществляется по обычным правилам.
     */
    @Override
    public void task22() {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[][] A = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = (int) Math.round(scanner.nextDouble());
            }
        }

        Matrix.printMatrix(A);
    }

    /**
     * Описание:
     * Найти количество всех седловых точек матрицы. Матрица А имеет седловую точку (i, j), если А[i, j] является минимальным
     * элементом в i-й строке и максимальным в j-м столбце.
     * <p>
     * Формат входных данных:
     * Матрица (описание представления матриц)
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task23 в выходной поток должна быть выведено целое число, отображающее количество
     * седловых точек в матрице.
     */
    @Override
    public void task23() {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[][] A = new int[N][N];

        Map<Integer, Integer> coordinates = new HashMap<>();

//        Пробежим в цикле считаем элементы матрицы, заодно найдем в каждой строке минимальный элемент
//        и кинем его координаты в HashMap, где ключ - номер строки, значение - номер столбца.
//        Если в этой строке уже был минимальный элемент - он будет заменён новым значением.
//        Если такое значение уже находили - значит элемент не минимальный, следовательно - удаляем координаты из Map по ключу i
        for (int i = 0; i < N; i++) {
            int minElementInLine = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                A[i][j] = scanner.nextInt();

                if (minElementInLine > A[i][j]) {
                    minElementInLine = A[i][j];
                    coordinates.put(i, j);
                } else if (minElementInLine == A[i][j]) {
                    coordinates.remove(i);
                }
            }
        }

//        Теперь пробежим по Map, дернем из нее координаты, и во внутреннем цикле проверим, 
//        является ли указанный элемент максимальным в столбце
        int numberOfSaddlePoints = 0;
        for (Map.Entry<Integer, Integer> entry : coordinates.entrySet()) {
            int maxElement = A[entry.getKey()][entry.getValue()];
            boolean isMaxElement = true;
            int j = entry.getValue();

            for (int i = 0; i < N; i++) {
                if (entry.getKey().equals(i)) {
//                  Чтобы не сравнивать с самим собой
                    continue;
                }

                if (maxElement <= A[i][j]) {
                    isMaxElement = false;
                }
            }

            if (isMaxElement) {
                numberOfSaddlePoints++;
            }
        }

        System.out.println(numberOfSaddlePoints);
    }

    /**
     * Описание:
     * Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках полученной матрицы возрастала.
     * <p>
     * Формат входных данных:
     * Матрица (описание представления матриц)
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task24 в выходной поток должна быть выведена преобразованная матрица. Если сумма
     * нескольких строк совпадает - в результирующей матрицы они должны следовать в том же порядке что и в исходной.
     */
    @Override
    public void task24() {

        Scanner scanner = new Scanner(System.in);

        Integer[][] matrix = Matrix.readMatrix(scanner);

        Arrays.sort(matrix, (o1, o2) -> {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < matrix.length; i++) {
                sum1 += o1[i];
                sum2 += o2[i];
            }
            return sum1 - sum2;
        });

        Matrix.printMatrix(matrix);
    }

    /**
     * Описание:
     * Найти число локальных минимумов. Соседями элемента матрицы назовем элементы, имеющие с ним общую сторону или угол.
     * Элемент матрицы называется локальным минимумом, если он строго меньше всех своих соседей.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task25 в выходной поток должно быть выведено число, соответствующее количеству
     * локальных минимумов в матрице.
     */
    @Override
    public void task25() {

        Scanner scanner = new Scanner(System.in);
        Integer[][] matrix = Matrix.readMatrix(scanner);

        int numberOfLocalMins = 0;

//        Визуальное представление индексов вокруг центрального элемента, для которого определяем является ли локальным минимумом
//        A[i-1][j-1] A[i-1][j] A[i-1][j+1]
//        A[ i ][j-1] A[ i ][j] A[ i ][j+1]
//        A[i+1][j-1] A[i+1][j] A[i+1][j+1]

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                boolean isLocalMinimum =
                        ((i == 0 || ((j == 0 || matrix[i][j] < matrix[i - 1][j - 1]) &&
                                (matrix[i][j] < matrix[i - 1][j]) &&
                                (j == matrix.length - 1 || matrix[i][j] < matrix[i - 1][j + 1]))) &&
                                ((j == 0 || matrix[i][j] < matrix[i][j - 1]) && (j == matrix.length - 1 || matrix[i][j] < matrix[i][j + 1])) &&
                                (i == matrix.length - 1 || ((j == 0 || matrix[i][j] < matrix[i + 1][j - 1]) &&
                                        (matrix[i][j] < matrix[i + 1][j]) &&
                                        (j == matrix.length - 1 || matrix[i][j] < matrix[i + 1][j + 1]))));

                if (isLocalMinimum) {
                    numberOfLocalMins++;
                }

            }
        }

        System.out.println(numberOfLocalMins);
    }

    /**
     * Описание:
     * Найти наибольший среди локальных максимумов. Соседями элемента матрицы назовем элементы, имеющие с ним общую
     * сторону или угол. Элемент матрицы называется локальным максимумом, если он строго больше всех своих соседей.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task26 в выходной поток должно быть выведено число, соответствующее наибольшему
     * локальному максимуму. Если локальные максимумы в матрице отсутствуют - выводится строка "NOT FOUND".
     */
    @Override
    public void task26() {

        Scanner scanner = new Scanner(System.in);

        Integer[][] matrix = Matrix.readMatrix(scanner);

        List<Integer> localMaxumums = new ArrayList<>();

//        Визуальное представление индексов вокруг центрального элемента, для которого определяем является ли локальным минимумом
//        A[i-1][j-1] A[i-1][j] A[i-1][j+1]
//        A[ i ][j-1] A[ i ][j] A[ i ][j+1]
//        A[i+1][j-1] A[i+1][j] A[i+1][j+1]

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                boolean isLocalMaximum =
                        ((i == 0 || ((j == 0 || matrix[i][j] > matrix[i - 1][j - 1]) &&
                                (matrix[i][j] > matrix[i - 1][j]) &&
                                (j == matrix.length - 1 || matrix[i][j] > matrix[i - 1][j + 1]))) &&
                                ((j == 0 || matrix[i][j] > matrix[i][j - 1]) && (j == matrix.length - 1 || matrix[i][j] > matrix[i][j + 1])) &&
                                (i == matrix.length - 1 || ((j == 0 || matrix[i][j] > matrix[i + 1][j - 1]) &&
                                        (matrix[i][j] > matrix[i + 1][j]) &&
                                        (j == matrix.length - 1 || matrix[i][j] > matrix[i + 1][j + 1]))));

                if (isLocalMaximum) {
                    localMaxumums.add(matrix[i][j]);
                }
            }
        }

        if (localMaxumums.size() == 0) {
            System.out.println(NOT_FOUND);
        } else {
            System.out.println(Collections.max(localMaxumums));
        }

    }

    /**
     * Описание:
     * Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы значения их характеристик убывали.
     * Характеристикой столбца прямоугольной матрицы называется сумма модулей его элементов. Если значения
     * характеристики совпадают - столбцы должны следовать в том же порядке, что и в исходной матрице.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task27 в выходной поток должна быть выведена преобразованная матрица,
     * в которой столбцы отсортированы по убыванию их характеристики.
     */
    @Override
    public void task27() {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        Integer[][] A = new Integer[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[j][i] = scanner.nextInt();
            }
        }

        Arrays.sort(A, (o1, o2) -> {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < N; i++) {
                sum1 += Math.abs(o1[i]);
                sum2 += Math.abs(o2[i]);
            }
            return sum2 - sum1;
        });

        System.out.println(N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(A[j][i] + ((j == (N - 1)) ? "\n" : "\t"));
            }
        }
    }
}
