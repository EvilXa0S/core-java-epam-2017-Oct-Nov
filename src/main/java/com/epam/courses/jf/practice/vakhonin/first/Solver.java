package com.epam.courses.jf.practice.vakhonin.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.util.*;

public class Solver implements ISolver {

    static final String NOT_FOUND = "NOT FOUND";

    /**
    * task1:
    *
    * Ввести N строк, найти самую короткую и самую длинную строки. Вывести найденные строки и их длину.
    * Если строк, удовлетворяющих условию, более одной - вывести последнюю из них.
    *
    * Формат входных данных:
    * -N (целое число, 0 < N < 100) - количество доступных для чтения строк
    * -N строк
    *
    * Формат выходных данных:
    * В результате выполнения метода task1 в выходной поток должны быть выведены две строки следующего вида:
    * System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
    * System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    */

    @Override
    public void task1() {
        Scanner in = new Scanner(System.in);
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

        for (String str : strings) {
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
    }

    /**
    * task2:
    *
    * Ввести N строк. Упорядочить и вывести строки в порядке возрастания значений их длины.
    * В случае, если длины строк совпадают - упорядочить их в лексикографическом порядке.
    *
    * Формат входных данных:
    * -N (целое число) - количество доступных для чтения строк
    * -N строк
    *
    * Формат выходных данных:
    * В результате выполнения метода task2 в выходной поток должны быть выведены строки следующего вида:
    * System.out.printf("(%d): \"%s\"%n", length, string);
    */

    @Override
    public void task2() {
        List<String> list = new ArrayList<>();
        Comparator<String> comparator = (s1, s2) -> {
            int length1 = s1.length();
            int length2 = s2.length();
            int result;
            if (length1 == length2) {
                result = s1.compareToIgnoreCase(s2);
            } else {
                result = (length1 - length2);
            }
            return result;
        };

        Scanner in = new Scanner(System.in);
        int numberOfStrings = Integer.valueOf(in.nextLine());

        for (int j = 0; j < numberOfStrings; j++) {
            list.add(in.nextLine());
        }

        list.sort(comparator);

        for (String s : list) {
            System.out.printf("(%d): \"%s\"%n", s.length(), s);
        }
    }

    /**
    * task3:
    *
    * Ввести N строк. Вывести те строки, длина которых меньше средней.
    * Под 'средней' подразумевается среднеарифметическая величина длины всех строк,
    * округленная до целого в меньшую сторону.
    *
    * Формат входных данных:
    * -N (целое число) - количество доступных для чтения строк
    * -N строк
    *
    * Формат выходных данных:
    * В результате выполнения метода task3 в выходной поток должна быть выведена средняя длина
    * округленная до целого в меньшую сторону) и строки,
    * удовлетворяющие условию.
    * Порядок появления строк в выходном потоке должен совпадать с порядком их появления во входном.
    * System.out.printf("AVERAGE (%d)%n", averageLength);
    * System.out.printf("(%d): \"%s\"%n", string.length(), string);
    */

    @Override
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
    }

    /**
    * task4:
    *
    * Ввести N слов, состоящих из символов английского алфавита.
    * Найти слово, в котором число различных символов минимально.
    * Символы верхнего и нижнего регистра считать различными.
    * Если таких слов несколько, найти первое из них.
    *
    * Формат входных данных:
    * -N (целое число) - количество слов в строке.
    * -N строка, содержащая указанное количество слов, разделенных пробелами
    *
    * Формат выходных данных:
    * В результате выполнения метода task4 в выходной поток должно быть выведено слово,
    * содержащее наименьшее число различных символов.
    */

    @Override
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
    }

    /**
     * task5:
     *
     * Ввести N слов.
     * Найти количество слов, содержащих только символы латинского алфавита,
     * а среди них – количество слов с равным числом гласных и согласных букв.
     *
     * Формат входных данных:
     * -N (целое число) - количество слов в строке.
     * -N строка, содержащая указанное количество слов, разделенных пробелами
     *
     * Формат выходных данных:
     * В результате выполнения метода task5 в выходной поток должно быть выведено количество слов,
     * состоящих только из символов латинского алфавита и содержащих равное количество гласных и согласных букв
     * (количество различных вхождений одной буквы в слове учитывается).
     */

    @Override
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
    }

    /**
     * task6:
     *
     * Ввести N слов.
     * Найти слово, символы в котором идут в строгом порядке возрастания их кодов.
     * Если таких слов несколько, найти первое из них.
     *
     * Формат входных данных:
     * -N (целое число) - количество слов в строке.
     * -N строка, содержащая указанное количество слов, разделенных пробелами
     *
     * Формат выходных данных:
     * В результате выполнения метода task6 в выходной поток должно быть выведено слово,
     * коды символов которого следуют в порядке возрастания (каждый следующий строго больше предыдущего).
     * В случае, если такое слово не найдено, в поток должно быть выведено "NOT FOUND".
     * Слова состоящие из одного символа не учитывать.
     */

    @Override
    public void task6() {
        Scanner in = new Scanner(System.in);

        int numberOfWords = Integer.valueOf(in.next());
        int code, prevCode = -1;
        String result = NOT_FOUND;
        String[] strings = new String[numberOfWords];

        for (int j = 0; j < numberOfWords; j++) {
            strings[j] = in.next();
        }

        for (String word : strings) {
            if (word.length() == 1) {
                continue;
            }

            for (Character ch : word.toCharArray()) {
                code = (int) ch;

                if (code > prevCode) {
                    prevCode = code;
                } else {
                    prevCode = -1;
                    break;
                }
            }

            if (prevCode != -1) {
                result = word;
                break;
            }
        }

        System.out.printf("%s%n", result);
    }

    /**
     * task7:
     *
     * Ввести N слов.
     * Ввести N слов. Найти слова, состоящие только из различных символов.
     * В случае, если слово встречается более одного раза - вывести его единожды.
     *
     * Формат входных данных:
     * -N (целое число) - количество слов в строке.
     * -N строка, содержащая указанное количество слов, разделенных пробелами
     *
     * Формат выходных данных:
     * В результате выполнения метода task7 в выходной поток должна быть выведена строка,
     * содержащая найденные слова, разделенные пробелами.
     * В случае, если не найдено ни одного слова,
     * удовлетворяющего условию - в поток должно быть выведено "NOT FOUND".
     */

    @Override
    public void task7() {
        Scanner in = new Scanner(System.in);
        int numberOfWords = Integer.valueOf(in.nextLine());
        int wordsSize;
        String[] strings = new String[numberOfWords];

        for (int j = 0; j < numberOfWords; j++) {
            strings[j] = in.next();
        }

        int length;
        Set<Character> charSet;
        StringBuilder result;
        result = new StringBuilder("");
        Set<String> wordsSet = new LinkedHashSet<>();

        for (String word : strings) {
            charSet = new LinkedHashSet();
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
        } else {
            for (String s : wordsSet) {
                result.append(s);
                result.append(" ");
            }

            result.deleteCharAt(result.length() - 1);
        }

        System.out.println(result.toString());
    }

    /**
     * task8:
     *
     * Ввести N слов. Помимо обычных слов, во входной последовательности могут встречаться целые числа.
     * Среди них необходимо найти число-палиндром (одинаково читающееся в обоих направлениях).
     * Если таких чисел больше одного, найти второе из них. Ограничения на размер числа нет.
     * Одна цифра является палиндромом.
     *
     * Формат входных данных:
     * -N (целое число) - количество слов в строке.
     * -N строка, содержащая указанное количество слов, разделенных пробелами
     *
     * Формат выходных данных:
     * В результате выполнения метода task8 в выходной поток должны быть выведено найденное число-палиндром.
     * В случае, если не найдено ни одного такого числа - в поток должно быть выведено "NOT FOUND".
     */

    @Override
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

        for (String str : stringList) {
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
    }

    /**
     * task9:
     *
     * Написать программу, которая выводит числа от 1 до N^2 в виде матрицы NxN слева направо и сверху вниз.
     *
     * Формат входных данных:
     * - N (целое число) - размерность матрицы
     *
     * Формат выходных данных:
     * В результате выполнения метода task9 в выходной поток должны быть выведены N строк,
     * соответствующие строкам матрицы.
     * Внутри строки элементы матрицы разделяются знаком табуляции ('\t').
     */

    @Override
    public void task9() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                matrix[j][k] = j * n + k + 1;
            }
        }

        printMatrix(matrix);
    }

    /**
     * task10:
     *
     * Написать программу, позволяющую корректно находить корни квадратного уравнения.
     * Параметры уравнения должны задаваться с командной строки.
     *
     * Формат входных данных:
     * Во входном потоке последовательно расположены три целочисленных коэффициента A, B и C, задающие уравнение в общем виде.
     *
     * Формат выходных данных:
     * В результате решения уравнения может быть получено три варианта ответа:
     *
     * 1) No solution
     * 2) One solution: корень уравнения
     * 3) Two solutions: первый корень уравнения, второй корень уравнения
     *
     * В результате выполнения метода task10, в выходной поток посылается строка, соответствующая найденному решению.
     * Значения корней округляются до 2 знаков после запятой.
     * В качестве десятичного разделителя используется точка.
     */

    @Override
    public void task10() {
        Scanner in = new Scanner(System.in);
        double d;
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        BigDecimal x1, x2;
        String result = "";

        if (a == 0) {
            if (b == 0) {
                result = "No solution";
            }
            else {
                x1 = (new BigDecimal(-c / b)).setScale(2, BigDecimal.ROUND_HALF_UP);
                result = "One solution: " + x1;
            }
        }
        else {
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
    }

    /**
     * task11:
     *
     * Ввести число от 1 до 12.
     * Вывести на консоль название месяца, соответствующего данному числу.
     * При реализации использовать оператор switch.
     * Осуществить проверку корректности ввода числа.
     *
     * Формат входных данных:
     * Во входном потоке расположена строка.
     * Необходимо проверить, что она является целым числом в диапазоне 1-12.
     *
     * Формат выходных данных:
     * В результате выполнения метода task11,
     * в выходной поток посылается строка, соответствующая названию месяца
     * (на английском языке) с указанным порядковым номером (регистр букв не важен).
     * В случае,
     * если введенная строка не удовлетворяет заданным условиям -
     *  - в выходной поток посылается сообщение "INCORRECT INPUT DATA".
     */

    @Override
    public void task11() {
        Scanner in = new Scanner(System.in);
        String month = "";
        String result = "";
        String data = in.nextLine();
        int numberOfMonth;

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
        }
        else {
            result += "INCORRECT INPUT DATA";
        }

        System.out.println(result);
    }

    /**
     * task12:
     *
     * Упорядочить строки матрицы размерности N
     * в порядке возрастания значений элементов k-го столбца.
     *
     * Формат входных данных:
     * - k (целое число, 0 <= k < N) - номер столбца
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task12
     * в выходной поток должна быть выведена преобразованная матрица.
     */

    @Override
    public void task12() {
        Scanner in = new Scanner(System.in);
        int numberOfColumn = in.nextInt();
        int[][] matrix = enterMatrix(in);
        Arrays.sort(matrix, Comparator.comparingInt(row -> row[numberOfColumn]));
        System.out.println(matrix.length);
        printMatrix(matrix);
    }

    /**
     * task13:
     *
     * Выполнить циклический сдвиг матрицы размерности N на k позиций вниз.
     *
     * Формат входных данных:
     * - k (целое число) - количество сдвигов:
     * 1) Если k > 0 - производится циклический сдвиг матрицы вниз
     * 2) Если k < 0 - производится циклический сдвиг матрицы вверх
     * 3) Если k = 0 - матрица остается без изменений
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task13
     * в выходной поток должна быть выведена преобразованная матрица.
     */

    @Override
    public void task13() {
        Scanner in = new Scanner(System.in);
        int k, shift;
        int[][] matrix;
        k = in.nextInt();
        matrix = enterMatrix(in);
        int size = matrix.length;
        int[][] newMatrix = new int[size][size];
        shift = -(size + k % size) % size + size;

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                newMatrix[j][q] = matrix[(j + shift) % size][q];
            }
        }

        System.out.println(size);
        printMatrix(newMatrix);
    }

    /**
     * task14:
     *
     * Найти наибольшее число строго возрастающих элементов последовательности, идущих подряд.
     * Оператор отношения можно определить на множестве, включающем более одного элемента.
     *
     * Формат входных данных:
     * - N (целое число) - количество элементов последовательности
     * - N целых чисел - элементы последовательности
     *
     * Формат выходных данных:
     * В результате выполнения метода task14 в выходной поток должно быть выведено число,
     * отображающее количество элементов на самом большом возрастающем участке последовательности.
     */

    @Override
    public void task14() {
        Scanner in = new Scanner(System.in);
        int n = Integer.valueOf(in.nextLine());
        int[] seq = new int[n];
        int count = 1;
        int max = 1;
        int result;

        for (int j = 0; j < n; j++) {
            seq[j] = in.nextInt();
        }

        for (int j = 1; j < n; j++) {
            if (seq[j] > seq[j - 1]) {
                count++;
            }
            else {
                max = count;
                count = 1;
            }
        }

        if (count > max) {
            max = count;
        }

        if (max == 1) {
            result = 0;
        }
        else {
            result = max;
        }

        System.out.println(result);
    }

    /**
     * task15:
     *
     * Найти сумму элементов матрицы,
     * расположенных между первым и вторым положительными элементами каждой строки.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task15 в выходной поток должна быть выведена указанная сумма.
     * В случае, если в строке нет двух положительных элементов -
     * - сумма от это строки полагается равной нулю.
     * Сумма между двумя рядом расположенными элементами также равна нулю.
     */

    @Override
    public void task15() {
        Scanner in = new Scanner(System.in);
        int sumOfRow;
        int totalSum = 0;
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;


        for (int[] row: matrix) {
            int counter = 0;
            int index1 = -1;
            int index2 = -1;

            for (int j = 0; !((counter == 2) || (j == size)); j++) {
                if (row[j] > 0) {
                    counter++;

                    if (index1 > -1) {
                        index2 = j;
                    } else {
                        index1 = j;
                    }
                }
            }

            sumOfRow = 0;

            if ((counter == 2) && ((index2 - index1) > 1)) {
                for (int j = index1 + 1; j < index2; j++) {
                    sumOfRow += row[j];
                }
            }

            totalSum += sumOfRow;
        }
        System.out.println(totalSum);
    }

    /**
     * task16:
     *
     * Повернуть матрицу на 90 градусов против часовой стрелки.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task16
     * в выходной поток должна быть выведена преобразованная матрица.
     */

    @Override
    public void task16() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        int[][] matrixNew = new int[size][size];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                matrixNew[size - q - 1][j] = matrix[j][q];
            }
        }

        System.out.println(size);
        printMatrix(matrixNew);
    }

    /**
     * task17:
     *
     * Вычислить определитель матрицы.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task17
     * в выходной поток должен быть выведен определитель матрицы,
     * представленный целым числом.
     */

    @Override
    public void task17() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        int el;
        int det = 1;

        for (int j = 0; j < size; j++) {
            for (int k = j; k < size; k++) {
                el = matrix[j][k];

                if (el != 0) {
                    swapRows(matrix, j, k);
                    break;
                }

                det = 0;
                break;
            }

            if (det != 0) {
                for (int k = j + 1; k < size; k++) {
                    for (int i = size - 1; i >= j; i--) {
                        matrix[k][i] = matrix[j][j] * matrix[k][i] - matrix[k][j] * matrix[j][i];
                    }
                }
            }
        }

        if (det != 0) {
            det = matrix[size - 1][size - 1];

            for (int j = 0; j < size; j++) {
                for (int k = 0; k < (size - j - 2); k++) {
                    det /= matrix[j][j];
                }
            }
        }

        System.out.println(det);
    }

    /**
     * task18:
     *
     * Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы,
     * его содержащие.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task18
     * в выходной поток должна быть выведена преобразованная матрица.
     * Так как матрица в результате преобразования может перестать быть квадратной -
     * - несколько изменяется формат её представления при выводе в выходной поток:
     *
     * - N (целое число) - количество строк;
     * - M (целое число) - количество столбцов;
     * - N * M целых чисел, являющихся элементами матрицы.
     */

    @Override
    public void task18() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        Set<Integer> rows = new HashSet();
        Set<Integer> columns = new HashSet();
        int max = matrix[0][0];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                if (matrix[j][q] >= max) {
                    if (matrix[j][q] != max) {
                        rows = new HashSet();
                        columns = new HashSet();
                        max = matrix[j][q];
                    }

                    rows.add(j);
                    columns.add(q);
                }
            }
        }

        int[][] matrixNew = new int[size - rows.size()][size - columns.size()];
        int i, k = 0;

        for (int j = 0; j < size; j++) {
            if (!rows.contains(j)) {
                i = 0;

                for (int q = 0; q < size; q++) {
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
        printMatrix(matrixNew);
    }

    /**
     * task19:
     *
     * Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
     *
     * Формат входных данных:
     * - Матрица
     *
     * В результате выполнения метода task19
     * в выходной поток должна быть выведена преобразованная матрица.
     * Так как матрица в результате преобразования может перестать быть квадратной -
     * - несколько изменяется формат её представления при выводе в выходной поток:
     */

    @Override
    public void task19() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int[][] tMatrix = transposeMatrix(matrixWithoutZeroRows(matrix));
        int[][] resultMatrix = transposeMatrix(matrixWithoutZeroRows(tMatrix));
        System.out.println(resultMatrix.length);
        System.out.println(resultMatrix[0].length);
        printMatrix(resultMatrix);
    }

    /**
     * task20:
     *
     * В матрице найти минимальный элемент
     * и переместить его на место заданного элемента путем перестановки строк и столбцов.
     * Гарантируется, что минимальный элемент в матрице встречается ровно один раз.
     *
     * Формат входных данных:
     * X (целое число, 0 <= X < N) - номер строки
     * Y (целое число, 0 <= Y < N) - номер столбца
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task20
     * в выходной поток должна быть выведена преобразованная матрица.
     */

    @Override
    public void task20() {
        Scanner in = new Scanner(System.in);
        int targetRow = in.nextInt();
        int targetColumn = in.nextInt();
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        int row = 0;
        int column = 0;
        int min = matrix[0][0];
        int[] temp;

        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                if (matrix[j][k] <= min) {
                    min = matrix[j][k];
                    row = j;
                    column = k;
                }
            }
        }

        swapRows(matrix, targetRow, row);
        int[][] matrixT = transposeMatrix(matrix);
        swapRows(matrixT, targetColumn, column);
        int[][] resultMatrix = transposeMatrix(matrixT);
        System.out.println(size);
        printMatrix(resultMatrix);
    }

    /**
     * task21:
     *
     * Преобразовать строки матрицы таким образом,
     * чтобы элементы, равные нулю, располагались после всех остальных.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task21
     * в выходной поток должна быть выведена преобразованная матрица.
     */

    @Override
    public void task21() {
        Scanner in = new Scanner(System.in);
        Integer[][] matrix = enterMatrixInteger(in);
        int size = matrix.length;

        Comparator<Integer> comparator = (r1, r2) -> {
            int result = 0;

            if ((r1 == 0) &&  (r2 != 0)) {
                result = 1;
            }

            if ((r1 != 0) && (r2 == 0)) {
                result = -1;
            }

            return result;
        };

        for (Integer[] arr : matrix) {
            Arrays.sort(arr, comparator);
        }

        System.out.println(size);
        printMatrix(matrix);
    }

    /**
     * task22:
     *
     * Округлить все элементы матрицы до целого числа.
     * Использовать округление к ближайшему целому — число округляется до целого,
     * модуль разности с которым у этого числа минимален.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task22
     * в выходной поток должна быть выведена преобразованная матрица,
     * в которой элементы являются целыми числами.
     * Вывод матрицы осуществляется по обычным правилам.
     */

    @Override
    public void task22() {
        Scanner in = new Scanner(System.in);
        double[][] matrix = enterMatrixDouble(in);
        int size = matrix.length;
        int[][] matrixNew = new int[size][size];

        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                matrixNew[j][k] = (int) Math.round(matrix[j][k]);
            }
        }

        System.out.println(size);
        printMatrix(matrixNew);
    }

    /**
     * task23:
     *
     * Найти количество всех седловых точек матрицы.
     * Матрица А имеет седловую точку (i, j),
     * если А[i, j] является минимальным элементом в i-й строке и максимальным в j-м столбце.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task23
     * в выходной поток должна быть выведено целое число,
     * отображающее количество седловых точек в матрице.
     */

    @Override
    public void task23() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int[][] matrixT = transposeMatrix(matrix.clone());
        int size = matrix.length;
        int number = 0;
        int min, max, index;
        Map<String, Integer> map;

        for (int j = 0; j < size; j++) {
            map = minElementOfArrayWithIndex(matrix[j]);
            min = map.get("minElement");
            index = map.get("index");
            max = maxElementOfArray(matrixT[index]);

            if (min == max) {
                number++;
            }
        }

        System.out.println(number);
    }

    /**
     * task24:
     *
     * Перестроить матрицу, переставляя в ней строки так,
     * чтобы сумма элементов в строках полученной матрицы возрастала.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task24
     * в выходной поток должна быть выведена преобразованная матрица.
     * Если сумма нескольких строк совпадает -
     * - в результирующей матрицы они должны следовать в том же порядке что и в исходной.
     */

    @Override
    public void task24() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;

        Comparator<int[]> comparator = (arr1, arr2) -> {
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
        };

        Arrays.sort(matrix, comparator);
        System.out.println(size);
        printMatrix(matrix);
    }

    /**
     * task25
     *
     * Найти число локальных минимумов.
     * Соседями элемента матрицы назовем элементы,
     * имеющие с ним общую сторону или угол.
     * Элемент матрицы называется локальным минимумом,
     * если он строго меньше всех своих соседей.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task25
     * в выходной поток должно быть выведено число,
     * соответствующее количеству локальных минимумов в матрице.
     */

    @Override
    public void task25() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = enterMatrix(in);
        int size = matrix.length;
        int[][] matrixExpanded = new int[size + 2][size + 2];
        int numberOfMinimum = 0;
        int el;

        for (int j = 0; j < (size + 2); j++) {
            matrixExpanded[j][0] = Integer.MAX_VALUE;
            matrixExpanded[j][size + 1] = Integer.MAX_VALUE;
            matrixExpanded[0][j] = Integer.MAX_VALUE;
            matrixExpanded[size + 1][j] = Integer.MAX_VALUE;
        }

        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                matrixExpanded[j + 1][k + 1] = matrix[j][k];
            }
        }

        for (int j = 1; j < size + 1; j++) {
            for (int k = 1; k < size + 1; k++) {
                el = matrixExpanded[j][k];
                if (((el < matrixExpanded[j - 1][k]) && (el < matrixExpanded[j - 1][k - 1]) && (el < matrixExpanded[j][k - 1]) && (el < matrixExpanded[j + 1][k - 1]) && (el < matrixExpanded[j + 1][k + 1]) && (el < matrixExpanded[j + 1][k]) && (el < matrixExpanded[j][k + 1]) && (el < matrixExpanded[j - 1][k + 1]))) {
                    numberOfMinimum++;
                }
            }
        }

        System.out.println(numberOfMinimum);
    }

    /**
     * task26:
     *
     * Найти наибольший среди локальных максимумов.
     * Соседями элемента матрицы назовем элементы,
     * имеющие с ним общую сторону или угол.
     * Элемент матрицы называется локальным максимумом,
     * если он строго больше всех своих соседей.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task26
     * в выходной поток должно быть выведено число,
     * соответствующее наибольшему локальному максимуму.
     * Если локальные максимумы в матрице отсутствуют -
     * - выводится строка "NOT FOUND".
     */

    @Override
    public void task26() {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[][] matrix = new int[size + 2][size + 2];
        int maximum = Integer.MIN_VALUE;
        String result;

        for (int q = 1; q < size + 1; q++) {
            for (int j = 1; j < size + 1; j++) {
                matrix[j][q] = in.nextInt();
            }
        }

        for (int j = 0; j < size + 2; j++) {
            matrix[j][0] = Integer.MIN_VALUE;
            matrix[j][size + 1] = Integer.MIN_VALUE;
            matrix[0][j] = Integer.MIN_VALUE;
            matrix[size + 1][j] = Integer.MIN_VALUE;
        }

        for (int j = 1; j < size + 1; j++) {
            for (int k = 1; k < size + 1; k++) {
                if ((matrix[j][k] > maximum) && ((matrix[j][k] > matrix[j - 1][k]) && (matrix[j][k] > matrix[j - 1][k - 1]) && (matrix[j][k] > matrix[j][k - 1]) && (matrix[j][k] > matrix[j + 1][k - 1]) && (matrix[j][k] > matrix[j + 1][k + 1]) && (matrix[j][k] > matrix[j + 1][k]) && (matrix[j][k] > matrix[j][k + 1]) && (matrix[j][k] > matrix[j - 1][k + 1]))) {
                    maximum = matrix[j][k];
                }
            }
        }

        if (maximum == Integer.MIN_VALUE) {
            result = NOT_FOUND;
        } else {
            result = "" + maximum;
        }

        System.out.println(result);
    }

    /**
     * task27:
     *
     * Перестроить заданную матрицу,
     * переставляя в ней столбцы так,
     * чтобы значения их характеристик убывали.
     * Характеристикой столбца прямоугольной матрицы называется сумма модулей его элементов.
     * Если значения характеристики совпадают - столбцы должны следовать в том же порядке,
     * что и в исходной матрице.
     *
     * Формат входных данных:
     * - Матрица
     *
     * Формат выходных данных:
     * В результате выполнения метода task27
     * в выходной поток должна быть выведена преобразованная матрица,
     * в которой столбцы отсортированы по убыванию их характеристики.
     */

    @Override
    public void task27() {
        Scanner in = new Scanner(System.in);
        int[][] matrix = transposeMatrix(enterMatrix(in));
        int size = matrix.length;
        Comparator<int[]> comparator = (line1, line2) -> characteristic(line2) - characteristic(line1);
        Arrays.sort(matrix, comparator);
        int[][] resultMatrix = transposeMatrix(matrix);
        System.out.println(size);
        printMatrix(resultMatrix);
    }

    /**
     computation characteristic
     @param line - int array
     @return - characteristic
     */

    int characteristic(int[] line) {
        int result = 0;

        for (int j : line) {
            result += Math.abs(j);
        }

        return result;
    }

    /**
     sum of int array elements
     @param arr - int array
     @return - sum of arr elements
     */

    int sum(int[] arr) {
        int sum = 0;

        for (int j : arr) {
            sum += j;
        }

        return sum;
    }

    /**
     swap two rows in int matrix
     @param matrix - int matrix
     @param j - index of first row
     @param k - index of second row
     */

    void swapRows(int[][] matrix, int j, int k) {
        int[] temp = matrix[j];
        matrix[j] = matrix[k];
        matrix[k] = temp;
    }

    /**
     method for reading matrix help us Scanner
     @param in - Scanner(System.in)
     @return Integer[][] matrix
     */

    Integer[][] enterMatrixInteger(Scanner in) {
        int size = in.nextInt();
        Integer[][] matrix = new Integer[size][size];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                matrix[j][q] = in.nextInt();
            }
        }

        return matrix;
    }

    /**
     method for reading matrix help us Scanner
     @param in - Scanner(System.in)
     @return int[][] matrix
     */

    int[][] enterMatrix(Scanner in) {
        int size = in.nextInt();
        int[][] matrix = new int[size][size];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                matrix[j][q] = in.nextInt();
            }
        }

        return matrix;
    }

    /**
     method for reading matrix help us Scanner
     @param in - Scanner(System.in)
     @return double[][] matrix
     */

    double[][] enterMatrixDouble(Scanner in) {
        int size = in.nextInt();
        double[][] matrix = new double[size][size];

        for (int j = 0; j < size; j++) {
            for (int q = 0; q < size; q++) {
                matrix[j][q] = in.nextDouble();
            }
        }

        return matrix;
    }

    /**
     Printing Integer[][] matrix
     @param matrix of Integer's
     */

    void printMatrix(Integer[][] matrix) {
        StringBuilder stringMatrix = new StringBuilder();
        int height = matrix.length;
        int width = matrix[0].length;
        int length;

        for (int j = 0; j < height; j++) {
            for (int q = 0; q < width; q++) {
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

    /**
     Printing int[][] matrix
     @param matrix of int's
     */

    void printMatrix(int[][] matrix) {
        StringBuilder stringMatrix = new StringBuilder();
        int height = matrix.length;
        int width = matrix[0].length;
        int length;

        for (int j = 0; j < height; j++) {
            for (int q = 0; q < width; q++) {
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

    /**
     transpose matrix
     @param matrix
     @return tMatrix
     */

    int[][] transposeMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] tMatrix = new int[width][height];

        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                tMatrix[k][j] = matrix[j][k];
            }
        }

        return tMatrix;
    }

    /**
     Find minimum in int array
     @param arr - int array
     @return Map - minimum and index of this
     */

    Map<String, Integer> minElementOfArrayWithIndex(int[] arr) {
        Map<String, Integer> map = new HashMap<>();
        Integer min = Integer.MAX_VALUE;
        Integer index = 0;
        Integer el;
        int n = arr.length;

        for (int j = 0; j < n; j++) {
            el = arr[j];

            if (el < min) {
                min = el;
                index = j;
            }
        }

        map.put("index", index);
        map.put("minElement", min);

        return map;
    }

    /**
     Find maximum in int array
     @param arr - int array
     @return max - maximum
     */

    int maxElementOfArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (Integer el : arr) {
            if (el > max) {
                max = el;
            }
        }

        return max;
    }

    /**
     Remove zero rows from matrix and complete new suitable matrix
     @param matrix - int matrix
     @return new matrix wthout zero rows
     */

    int[][] matrixWithoutZeroRows(int[][] matrix){
        List<int[]> listOfRows = new ArrayList<>();

        for (int[] row: matrix) {
            for (int x: row) {
                if (x != 0) {
                    listOfRows.add(row);
                    break;
                }
            }
        }

        int[][] newMatrix = new int[listOfRows.size()][matrix.length];

        for (int j = 0; j < listOfRows.size(); j++) {
            newMatrix[j] = listOfRows.get(j);
        }

        return newMatrix;
    }
}
