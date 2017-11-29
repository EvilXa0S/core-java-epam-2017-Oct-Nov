package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;
import java.util.*;
import java.util.stream.Collectors;

public class Task18 implements ITestableTask18 {

    public class RectangularIntegerMatrix implements ITestableTask18.IRectangularIntegerMatrix {
        private int width;
        private int height;
        private int[][] matrix;

        RectangularIntegerMatrix(int width, int height) {
            this.width = width;
            this.height = height;
            this.matrix = new int[height][width];
        }
        RectangularIntegerMatrix(int[][] matrix) {
            this.matrix = matrix;
            this.height = matrix.length;
            this.width = matrix[0].length;
        }

        void setMatrix(int str, int column, int value) {
            this.matrix[str][column] = value;
        }

        @Override
        public int getWidth() {
            return this.width;
        }

        @Override
        public int getHeight() {
            return this.height;
        }

        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return matrix[indexHeight][indexWidth];
        }
    }
    private List<Integer[]> addToList(Integer[] secondStage1, Integer[] secondStage2){
        List<Integer[]> firstStage = new ArrayList<>();
        firstStage.add(secondStage1);
        firstStage.add(secondStage2);
        return firstStage;
    }
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        if (matrix.getWidth() >1 && matrix.getHeight() > 1) {
            Stack<Integer> stack = new Stack<>();
            for (int i = matrix.getHeight() - 1; i >= 0; i--) {
                for (int j = matrix.getWidth() - 1; j >= 0; j--) {
                    stack.push(matrix.getValue(j, i));
                }
            }
            int col = 0;
            int str = 0;
            int[] interMas = new int[matrix.getWidth()];
            List<List> storageCol = new ArrayList<>();
            List<List> storageStr = new ArrayList<>();
            while (stack.size() > 0) {
                int next = stack.pop();
                if (str == 0) {
                    interMas[col] = next;
                    col++;
                } else {
                    if (interMas[col] == next) {
                        Integer[] secondStage1 = {str, col};
                        Integer[] secondStage2 = {str - 1, col};
                        storageCol.add(addToList(secondStage1, secondStage2));
                    } else {
                        interMas[col] = next;
                    }
                    col++;
                }
                if (col > matrix.getWidth() - 1) {
                    for (int i = 0; i < matrix.getWidth(); i++) {
                        if (i > 0 && interMas[i] == interMas[i - 1]) {
                            Integer[] secondStage1 = {str, i};
                            Integer[] secondStage2 = {str, i - 1};
                            storageStr.add(addToList(secondStage1, secondStage2));
                        }
                    }
                    col = 0;
                    str++;
                }
            }
            List<List> result = new ArrayList<>();
            List<List> resStrList = createSubStrMatrix(storageStr, matrix);
            if (!resStrList.isEmpty()) {
                result.addAll(connector(resStrList).stream()
                        .sorted(Comparator.comparing(List::size, Collections.reverseOrder()))
                        .collect(Collectors.toList()));
            } else {
                if (storageStr.isEmpty()) {
                    result.add(storageCol.get(0));
                } else {
                    result.add(storageStr.get(0));
                }
            }
            List res = result.get(0); // Выбираем первый элемент отсотированной коллекции так как он максимальный
            // делим индексы строк и столбцов в разные листы
            Set<Integer> numCol = new HashSet<>();
            Set<Integer> numStr = new HashSet<>();
            res.forEach(i -> {
                Integer[] pointNext = (Integer[]) i;
                numCol.add(pointNext[1]);
                numStr.add(pointNext[0]);
            });
            List<Integer> numColList = new ArrayList<>(numCol.stream().sorted().collect(Collectors.toList()));
            List<Integer> numStrList = new ArrayList<>(numStr.stream().sorted().collect(Collectors.toList()));
            //
            RectangularIntegerMatrix resultMatrix = new RectangularIntegerMatrix(numColList.size(), numStrList.size());
            for (int i = 0; i < numStrList.size(); i++) {
                for (int j = 0; j < numColList.size(); j++) {
                    resultMatrix.setMatrix(i, j,
                            matrix.getValue(numColList.get(j), numStrList.get(i)));
                }
            }
            return resultMatrix;
        } else {
            return matrix;
        }
    }

    //  Соединение соседних подматриц
    private List<List> connector(List<List> resStrList) {
        List<Integer> garbage = new ArrayList<>();
        List<Integer> listsUse = new ArrayList<>();
        List<List> listForRemove = new ArrayList<>();
        resStrList.forEach(member -> {
            int useList = member.size();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < member.size(); i++) {
                Integer[] point = (Integer[]) member.get(i);
                resStrList.stream()
                        .filter(nextMember -> (!nextMember.equals(member)) // если следующий квдарат не равен предыдущему
                                && member.size() == nextMember.size()// и их расмеры равны
                                && !garbage.contains(resStrList.indexOf(nextMember)))
                        .forEach(nextMember -> {
                            int counter = 0; // счетчик количества совпадений
                            for (Object aNextMember : nextMember) {
                                Integer[] pointNext = (Integer[]) aNextMember;
                                if (Arrays.equals(point, pointNext)) {
                                    counter++;
                                }
                            }
                            if (map.containsKey(resStrList.indexOf(nextMember))) {
                                map.put(resStrList.indexOf(nextMember), map.get(resStrList.indexOf(nextMember)) + counter);
                            } else {
                                map.put(resStrList.indexOf(nextMember), counter);
                            }
                        });
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= member.size() / 2) {
                    // Добавляем уникальные элементы с одного листа в другой в листе ////
                    ArrayList<Integer> indexesForDel = new ArrayList<>();
                    for (Object aMember : member) {
                        Integer[] pM = (Integer[]) aMember;
                        for (int i = 0; i < member.size(); i++) {
                            Integer[] pNM = (Integer[]) resStrList.get(entry.getKey()).get(i);
                            if (Arrays.equals(pM, pNM)) {
                                indexesForDel.add(i);
                            }
                        }
                    }
                    int s = member.size();
                    for (int i = 0; i < s; i++) {
                        if (!indexesForDel.contains(i)) {
                            member.add(resStrList.get(entry.getKey()).get(i));
                        }
                    }
                    ///////
                    garbage.add(entry.getKey());
                    listsUse.add(entry.getKey());
                }
            }
            if (useList == member.size() && listsUse.contains(resStrList.indexOf(member))) {
                listForRemove.add(member);
            }
        });
        resStrList.removeAll(listForRemove);
        if (!listForRemove.isEmpty()) {
            // выполняем пока не создадут слитые матрицы
            return connector(resStrList);
        } else {
            return resStrList;
        }
    }

    // Создание подматриц из строк
    private List<List> createSubStrMatrix(List<List> storageStr, IRectangularIntegerMatrix matrix) {
        List<Integer[]> garbageList = new ArrayList<>();
        List<List> resStrList = new ArrayList<>();
        // Cоздаю подматрицу из этих строк n x 2
        for (List line : storageStr) { // первый шаг
            List<Integer[]> firstStage = new ArrayList<>();
            // Беру первые 2 индекса элемента массива
            Integer[] point1 = (Integer[]) line.get(0);
            Integer[] point2 = (Integer[]) line.get(1);
            for (List lineNext : storageStr) { // первый шаг
                // Беру вторые 2 индекса элемента массива
                Integer[] pointNextS1 = (Integer[]) lineNext.get(0);
                Integer[] pointNextS2 = (Integer[]) lineNext.get(1);
                // сравниваю вторые 2 индекса с первыми для нахождения высоты подматрицы
                if (!garbageList.contains(point1) && !garbageList.contains(point2) &&
                        !garbageList.contains(pointNextS1) && !garbageList.contains(pointNextS2)
                        && !line.equals(lineNext)
                        // Должны быть одинаковые столбцы для двух пар пары
                        && Objects.equals(pointNextS1[1], point1[1])
                        && Objects.equals(pointNextS2[1], point2[1])
                        // Должны быть одинаковые значения у последующих вниз элементов
                        && matrix.getValue(point1[1], point1[0]) == matrix.getValue(pointNextS1[1], pointNextS1[0])
                        && matrix.getValue(point2[1], point2[0]) == matrix.getValue(pointNextS2[1], pointNextS2[0])) {
                    firstStage.add(pointNextS1);
                    firstStage.add(pointNextS2);
                    garbageList.add(pointNextS1);
                    garbageList.add(pointNextS2);
                }
            }
            if (!firstStage.isEmpty()) {
                garbageList.add((Integer[]) line.get(0));
                garbageList.add((Integer[]) line.get(1));
                firstStage.add((Integer[]) line.get(0));
                firstStage.add((Integer[]) line.get(1));
                resStrList.add(firstStage);
            }
        }
        return resStrList;
    }
}