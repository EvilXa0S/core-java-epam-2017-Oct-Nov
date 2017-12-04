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

    private String[] Reader(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        String[] strings = new String[n];

        for (int i = 0; i < n; i++) {
            strings[i] = scanner.nextLine();
        }
        return strings;
    }

    private static String[] readedWords(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        return scanner.nextLine().split("\\s+", n);
    }

    private int[][] readMatrix(Scanner scanner) {
        int n = scanner.nextInt();
        int matrix[][] = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public void writeMatrix(int matrix[][]) {
        System.out.println(matrix.length);
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                System.out.print(matrix[row][column] + "\t");
            }
            System.out.println();
        }
    }
    /**
     * Описание:
     * Ввести N строк (каждая с новой строки). Найти среди них самую короткую и самую длинную. Вывести найденные строки
     * и их длину. Если строк, удовлетворяющих условию, более одной - вывести последнюю из них.
     * <p>
     * Формат входных данных:
     * N (целое число, 0 < N < 100) - количество доступных для чтения строк
     * Строка_1
     * Строка_2
     * ...
     * Строка_N
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task1 в выходной поток должны быть выведены две строки следующего вида:
     * System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
     * System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
     */
    @Override
    public void task1() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        TreeMap<Integer, String> treeMap = new TreeMap<>();

        if (N < 100 && N > 0) {
            for (int i = 1; i <= N; i++) {
                String str = in.next();
                int length = str.length();
                treeMap.put(length, str);
            }
        } else {
            System.out.println("Please enter correct value");
        }

        String minString = treeMap.firstEntry().getValue();
        String maxString = treeMap.lastEntry().getValue();
        int minLength = treeMap.firstKey();
        int maxLength = treeMap.lastKey();

        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }
    /**
     * Описание:
     * Ввести N строк (каждая с новой строки). Упорядочить и вывести строки в порядке возрастания значений их длины. В
     * случае, если длины строк совпадают - упорядочить их в лексикографическом порядке.
     * <p>
     * Формат входных данных:
     * N (целое число, 0 < N < 100) - количество доступных для чтения строк
     * Строка_1
     * Строка_2
     * ...
     * Строка_N
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
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());

        String[] strings = new String[N];
        if (N < 100 && N > 0) {
            for (int i = 0; i < N; i++) {
                strings[i] = in.nextLine();
            }
        } else {
            System.out.println("Please enter correct value");
        }


        Collections.sort(Arrays.asList(strings), Comparator.comparing(String::length).thenComparing(String::compareTo));
        for (String s : strings) {
            System.out.printf("(%d): \"%s\"%n", s.length(), s);
        }
    }
    /**
     * Описание:
     * Ввести N строк. Вывести те строки, длина которых меньше средней. Под 'средней' подразумевается
     * среднеарифметическая величина длины всех строк, округленная до целого в меньшую сторону.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество доступных для чтения строк
     * N строк
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

        for (int i = 0; i < N; i++) {
            if (strings[i].length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", strings[i].length(), strings[i]);
            }
        }
    }


    /**
     * Описание:
     * Ввести N слов, состоящих из символов английского алфавита. Найти слово, в котором число различных символов
     * минимально. Символы верхнего и нижнего регистра считать различными. Если таких слов несколько, найти первое из
     * них.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task4 в выходной поток должно быть выведено слово, содержащее наименьшее число
     * различных символов.
     */
    @Override
    public void task4() {
        Scanner scanner = new Scanner(System.in);
        String[] strings = readedWords(scanner);
        Map<String, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            int strLength = string.length();
            int endIntValue = strLength;

            while (string.length() != 0) {
                char charAt = string.charAt(0);
                int countChar = (int) string.
                        chars().
                        filter((ch) -> ch == charAt).
                        count();
                string = string.replaceAll(String.valueOf(charAt), "");

                if (countChar >= 2) {
                    endIntValue = endIntValue - countChar + 1;
                }
            }
            map.put(strings[i], endIntValue);
        }

        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(Collections.min(map.values()))) {
                System.out.println(entry.getKey());
                break;
            }
        }
    }


    /**
     * Описание:
     * Ввести N слов. Найти количество слов, содержащих только символы латинского алфавита, а среди них – количество
     * слов с равным числом гласных и согласных букв.
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

        Scanner scanner = new Scanner(System.in);
        String[] strings = readedWords(scanner);
        List<String> stringList = Arrays.asList(strings);

        int numbsOfWords = 0;
        for (String strList : stringList) {
            if (Pattern.matches("[a-zA-Z]+", strList)) {

                int vawelsInt = (int) strList.
                        chars().
                        mapToObj(i -> (char) i).
                        filter(c -> "aeiouy".contains(String.valueOf(c))).
                        count();

                if (strList.length() - vawelsInt == vawelsInt) {
                    numbsOfWords++;
                }
            }
        }
        System.out.println(numbsOfWords);
    }


    /**
     * Описание:
     * Ввести N слов. Найти слово, символы в котором идут в строгом порядке возрастания их кодов. Если таких слов
     * несколько, найти первое из них.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task6 в выходной поток должно быть выведено слово, коды символов которого следуют
     * в порядке возрастания (каждый следующий строго больше предыдущего). В случае, если такое слово не найдено, в
     * поток должно быть выведено "NOT FOUND". Слова состоящие из одного символа не учитывать.
     */
    @Override
    public void task6() {
        Scanner scanner = new Scanner(System.in);
        String[] strings = readedWords(scanner);
        List<String> stringList = Arrays.asList(strings);
        List slist = new ArrayList();

        for (String string : stringList) {
            if (string.length() > 1) {
                List<Integer> list = string.chars().
                        mapToObj((i) -> i).
                        collect(Collectors.toList());

                slist = list.stream().
                        filter(inx -> {
                            int buf = 0;
                            for (int i = 0; i < list.size(); i++) {

                                if (list.get(i) <= buf) {
                                    return false;
                                }
                                buf = list.get(i);
                            }
                            return true;
                        }).collect(Collectors.toList());


                if (!slist.isEmpty()) {
                    StringBuffer buf = new StringBuffer();

                    for (int i = 0; i < slist.size(); i++) {
                        buf.append((char) Integer.parseInt(slist.get(i).toString()));
                    }
                    System.out.println(buf);
                    break;
                }
            }
        }
        if (slist.isEmpty()) {
            System.out.println("NOT FOUND");
        }
    }

    /**
     * Описание:
     * Ввести N слов. Найти слова, состоящие только из различных символов. В случае, если слово встречается более одного
     * раза - вывести его единожды.
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
        String[] strings = readedWords(scanner);
        List<String> list = Arrays.asList(strings);
        Set<String> listOut = new HashSet<>();

        for (String str : list) {
            long i;
            boolean falseCount = true;
            for (int in = 0; in < str.length(); in++) {
                int cha = (int) str.charAt(in);
                i = str.chars()
                        .filter(ch -> ch == cha)
                        .count();

                if (i > 1) {
                    falseCount = false;
                    break;
                }
            }
            if (falseCount == true) {
                listOut.add(str);
            }
        }

        if (listOut.isEmpty()) {
            System.out.println("NOT FOUND");
        }

        String strOut = String.join(" ", listOut);
        System.out.println(strOut);
    }
    /**
     * Описание:
     * Ввести N слов. Помимо обычных слов, во входной последовательности могут встречаться целые числа. Среди них
     * необходимо найти число-палиндром (одинаково читающееся в обоих направлениях). Если таких чисел больше одного,
     * найти второе из них. Ограничения на размер числа нет. Одна цифра является палиндромом. Числа могут быть
     * большими.
     * <p>
     * Формат входных данных:
     * N (целое число) - количество слов в строке
     * Строка, содержащая указанное количество слов, разделенных пробелами
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task8 в выходной поток должны быть выведено найденное число-палиндром. В случае,
     * если не найдено ни одного такого числа - в поток должно быть выведено "NOT FOUND".
     */
    @Override
    public void task8() {
        Scanner scanner = new Scanner(System.in);
        String[] strs = readedWords(scanner);
        List<String> arr = new ArrayList<>(Arrays.asList(strs));
        List<String> arrOut = new ArrayList<>();

        while (arr.size() != 0 && Pattern.matches("\\d.*?\\b", arr.get(0))) {
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
        } else if (arrOut.size() > 1) {
            System.out.println(arrOut.get(1));
        } else System.out.println("NOT FOUND");
    }
    /**
     * Описание:
     * Написать программу, которая выводит числа от 1 до N^2 в виде матрицы NxN слева направо и сверху вниз.
     * <p>
     * Формат входных данных:
     * N (целое число) - размерность матрицы
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task9 в выходной поток должны быть выведены N строк, соответствующие строкам
     * матрицы. Внутри строки элементы матрицы разделяются знаком табуляции ('\t').
     */
    @Override
    public void task9() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] res = new int[n][n];
        int i = 1;

        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                res[row][column] = i;
                i++;
            }
        }

        for (int[] row : res) {
            for (int ind = 0; ind < row.length; ind++) {
                if (ind != row.length - 1) {
                    System.out.print(row[ind] + "\t");
                } else {
                    System.out.print(row[ind] + "\n");
                }
            }
        }
    }
    /**
     * Описание:
     * Написать программу, позволяющую корректно находить корни квадратного уравнения. Параметры уравнения должны
     * задаваться из стандартного входа.
     * <p>
     * Формат входных данных:
     * Во входном потоке последовательно расположены три целочисленных коэффициента A, B и C, задающие уравнение в общем
     * виде.
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
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();

        double D = B * B - 4 * A * C;

        if (D > 0) {
            BigDecimal x1 = new BigDecimal((-B + Math.sqrt(D)) / (2 * A));
            BigDecimal x2 = new BigDecimal((-B - Math.sqrt(D)) / (2 * A));
            System.out.println("Two solutions: " + x1.setScale(2, RoundingMode.HALF_UP)
                    + ", " + x2.setScale(2, RoundingMode.HALF_UP));
        } else if (D == 0) {
            BigDecimal x1 = new BigDecimal(((double) -B / (2 * A)));
            System.out.println("One solution: " + x1.setScale(2, RoundingMode.HALF_UP));
        } else if (D < 0) {
            System.out.println("No solution");
        }
    }
    /**
     * Описание:
     * Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу. При реализации
     * использовать оператор switch. Осуществить проверку корректности ввода числа.
     * <p>
     * Формат входных данных:
     * Во входном потоке расположена строка. Необходимо проверить, что она является целым числом в диапазоне 1-12.
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task11, в выходной поток посылается строка, соответствующая названию месяца (на
     * английском языке) с указанным порядковым номером (регистр букв не важен). В случае, если введенная строка не
     * удовлетворяет заданным условиям - в выходной поток посылается сообщение "INCORRECT INPUT DATA".
     */
    @Override
    public void task11() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        if (!Pattern.matches("\\d{1,2}", string)) {
            System.out.println("INCORRECT INPUT DATA");
            return;
        }
        int n = Integer.parseInt(string);

        if (n < 13 && n > 0) {
            switch (n) {
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
        } else System.out.println("INCORRECT INPUT DATA");
    }
    /**
     * Описание:
     * Упорядочить строки матрицы размерности N в порядке возрастания значений элементов k-го столбца.
     * <p>
     * Формат входных данных:
     * k (целое число, 0 <= k < N) - номер столбца
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task12 в выходной поток должна быть выведена преобразованная матрица
     */
    @Override
    public void task12() {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        int[][] matrix = readMatrix(scanner);

        Arrays.sort(matrix, Comparator.comparingInt(rows -> rows[k]));
        writeMatrix(matrix);

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
     * Матрица (описание представления матриц)
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task13 в выходной поток должна быть выведена преобразованная матрица.
     */
    @Override
    public void task13() {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());

        int[][] matrix = readMatrix(scanner);
        int[][] outMatrix = new int[matrix.length][];

        for (int i = 0; i < outMatrix.length; i++) {
            int shift = (i - k) % outMatrix.length;

            if (shift < 0) {
                shift = shift + outMatrix.length;
            }
            outMatrix[i] = matrix[shift];
        }

        writeMatrix(outMatrix);
    }
    /**
     * Описание:
     * Найти наибольшее число строго возрастающих элементов последовательности, идущих подряд. Оператор отношения можно
     * определить на множестве, включающем более одного элемента.
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
        int N = scanner.nextInt();
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
        }
        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        for (int i = 1; i < nums.length; i++) {

            if (nums[i - 1] < nums[i]) {
                count++;
            } else {
                integerList.add(count);
                count = 1;
            }
        }
        integerList.add(count);
        if (Collections.max(integerList) == 1) {
            System.out.println(0);
        } else {
            System.out.println(Collections.max(integerList));
        }
    }
    /**
     * Описание:
     * Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task15 в выходной поток должна быть выведена указанная сумма. В случае, если в
     * строке нет двух положительных элементов - сумма от это строки полагается равной нулю. Сумма между двумя рядом
     * расположенными элементами также равна нулю
     */
    @Override
    public void task15() {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);

        int result = 0;
        for (int[] row : matrix) {
            int sum = 0;
            boolean positive = false;

            for (int element : row) {
                if (!positive) {

                    if (element > 0) {
                        positive = true;
                    }
                } else {

                    if (element > 0) {
                        result += sum;
                        break;
                    } else {
                        sum += element;
                    }
                }
            }
        }

        System.out.println(result);
    }
    /**
     * Описание:
     * Повернуть матрицу на 90 градусов против часовой стрелки.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task16 в выходной поток должна быть выведена преобразованная матриц
     */
    @Override
    public void task16() {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = readMatrix(scanner);
        int[][] result = new int[matrix.length][matrix.length];

//        [0][0]  [0][1]  [0][2]  |   [0][2]  [1][2]  [2][2]
//        [1][0]  [1][1]  [1][2]  |   [0][1]  [1][1]  [2][1]
//        [2][0]  [2][1]  [2][2]  |   [0][0]  [1][0]  [2][0]

        for (int i = 0; i < matrix.length; i++) {
            int column = matrix.length - 1;
            for (int x = 0; x < matrix.length; x++) {
                result[column--][i] = matrix[i][x];
            }
        }
        writeMatrix(result);
    }
    /**
     * Описание:
     * Вычислить определитель матрицы.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task17 в выходной поток должен быть выведен определитель матрицы, представленный
     * целым числом.
     */
    @Override
    public void task17() {

        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        double[][] matrixRes = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrixRes[i][j] = matrix[i][j];
            }
        }

        double result = 1.0;

        for (int k = 0; k < matrix.length; k++) {
            if (matrixRes[k][k] == 0) {
                boolean checkDecrease = true;
                for (int i = k; i < matrix.length; i++) {
                    if (matrix[i][k] != 0) {
                        checkDecrease = false;
                        double[] swap = matrixRes[i];
                        matrixRes[i] = matrixRes[k];
                        matrixRes[k] = swap;
                        result *= -1;
                        break;
                    }
                }
                if (checkDecrease) {
                    System.out.println(0);
                    return;
                }
            }

            for (int i = k + 1; i < matrixRes.length; i++) {
                double coefficient = matrixRes[i][k] / matrixRes[k][k];
                for (int j = k; j < matrixRes[0].length; j++) {
                    matrixRes[i][j] -= coefficient * matrixRes[k][j];
                }
            }
        }

        for (int i = 0; i < matrixRes.length; i++) {
            result *= matrixRes[i][i];
        }

        System.out.println(Math.round(result));
    }


    /**
     * Описание:
     * Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
     * <p>
     * Формат входных данных:
     * Матрица
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

        System.out.println(matrix.length - rows.size());
        System.out.println(matrix.length - columns.size());
        for (int i = 0; i < matrix.length; i++){
            int N = 0;
            int M = 0;
            for (int x = 0; x < matrix.length; x++) {

                if(!rows.contains(i) && !columns.contains(x)) {
                    System.out.print(matrix[i][x] + "\t");
                    outMatrix[N][M] = matrix[i][x];
                    M++;
                }
            }
            System.out.println();
            N++;
        }
    }


    /**
     * Описание:
     * Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
     * <p>
     * Формат входных данных:
     * Матрица
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
        int N = scanner.nextInt();

        int[][] matrix = new int[N][N];

        Set<Integer> delLines = new HashSet<>();
        for (int i = 0; i < N; i++) {
            boolean deleteLine = true;
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextInt();
                if (matrix[i][j] != 0) {
                    deleteLine = false;
                }
            }
            if (deleteLine) {
                delLines.add(i);
            }
        }

        Set<Integer> columnsDel = new HashSet<>();
        for (int j = 0; j < N; j++) {
            boolean delColumn = true;
            for (int i = 0; i < N; i++) {
                if (matrix[i][j] != 0) {
                    delColumn = false;
                }
            }
            if (delColumn) {
                columnsDel.add(j);
            }
        }

        System.out.println(matrix.length - delLines.size());
        System.out.println(matrix.length - columnsDel.size());
        for (Integer i = 0; i < matrix.length; i++) {
            if (delLines.contains(i))
                continue;
            for (Integer j = 0; j < matrix.length; j++) {
                if (columnsDel.contains(j))
                    continue;
                System.out.print(matrix[i][j] + (j.equals(matrix.length - 1) ? "\n" : "\t"));
            }
        }
    }


    /**
     * Описание:
     * В матрице найти минимальный элемент и переместить его на место заданного элемента путем перестановки строк и
     * столбцов. Гарантируется, что минимальный элемент в матрице встречается ровно один раз.
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
    /**
     * Описание:
     * Округлить все элементы матрицы до целого числа.
     * Использовать округление к ближайшему целому — число округляется до целого с использованием Math.round();
     * <p>
     * Формат входных данных:
     * Матрица. В данном задании входная матрица в качестве элементов содержит не целые числа а вещественные (в формате
     * Double). Для их извлечения нужно воспользоваться методом scanner.nextDouble() класса Scanner.
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task22 в выходной поток должна быть выведена преобразованная матрица, в которой
     * элементы являются целыми числами. Вывод матрицы осуществляется по обычным правилам.
     */
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

    /**
     * Описание:
     * Найти количество всех седловых точек матрицы. Матрица А имеет седловую точку (i, j), если А[i, j] является
     * минимальным элементом в i-й строке и максимальным в j-м столбце.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task23 в выходной поток должна быть выведено целое число, отображающее количество
     * седловых точек в матрице.
     */
    @Override
    public void task23() {
        
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int count = 0;
        int outCount = 0;
        int N = 0;
        int M = 0;


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
                count++;
            }

            count = count != matrix.length ? 0 : outCount++;
        }
        System.out.println(outCount);
    }


    /**
     * Описание:
     * Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках полученной матрицы
     * возрастала.
     * <p>
     * Формат входных данных:
     * Матрица
     * <p>
     * Формат выходных данных:
     * В результате выполнения метода task24 в выходной поток должна быть выведена преобразованная матрица. Если сумма
     * нескольких строк совпадает - в результирующей матрицы они должны следовать в том же порядке что и в исходной.
     */
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
            map.put(i, sum);
        }

        LinkedHashMap<Integer, Integer> sortedMap =
                map.entrySet().stream().
                        sorted(Map.Entry.comparingByValue()).
                        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

        Iterator iter = sortedMap.entrySet().iterator();
        int[][] result = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            Map.Entry entry = (Map.Entry) iter.next();
            result[i] = matrix[(int)entry.getKey()];
        }
        writeMatrix(result);
    }


    /**
     * Описание:
     * Найти число локальных минимумов. Соседями элемента матрицы назовем элементы, имеющие с ним общую сторону или
     * угол. Элемент матрицы называется локальным минимумом, если он строго меньше всех своих соседей.
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
        int[][] matrix = readMatrix(scanner);

        int count = 0;
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
     * В результате выполнения метода task27 в выходной поток должна быть выведена преобразованная матрица, в которой
     * столбцы отсортированы по убыванию их характеристики.
     */
    @Override
    public void task27() {
        
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);

        Map<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < matrix.length; i++){
            int sum = 0;

            for (int x = 0; x < matrix.length; x++){
                sum += Math.abs(matrix[x][i]);
            }
            map.put(i, sum);
        }

        LinkedHashMap<Integer, Integer> sortedMap =
                map.entrySet().stream().
                        sorted(Map.Entry.comparingByValue()).
                        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));


        int[][] result = new int[matrix.length][matrix.length];
        ArrayList<Integer> intList = new ArrayList<>(sortedMap.keySet());
        Collections.reverse(intList);

        for (int i = 0; i < matrix.length; i++){
            for (int x = 0; x < matrix.length; x++) {
                result[x][i] = matrix[x][intList.get(i)];
            }
        }
        writeMatrix(result);
    }
}