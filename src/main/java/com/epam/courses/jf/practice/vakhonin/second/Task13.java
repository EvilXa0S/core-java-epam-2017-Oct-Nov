package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Реализовать класс Graph, представляющий собой неориентированный граф.
 * В конструкторе класса передается количество вершин в графе.
 * Методы должны поддерживать быстрое добавление и удаление ребер.
 */

public class Task13 implements ITestableTask13{

    /**
     * Класс граф.
     * При создании задается количество вершин.
     * Нумерация вершин начинается с 0.
     * Допустимы операции добавления, удаления и проверки существования ребер.
     */

    class Graph extends AbstractGraph {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        public Graph(int numberNodes) {
            super(numberNodes);
            Set<Integer> set;

            for(int j = 0; j < numberNodes; j++){
                set = new HashSet<>();
                map.put(j, new HashSet<>(set));
            }
        }

        /**
         * Добавление ребра в граф.
         * @param first Первая связываемая вершина.
         * @param second Вторая связываемая вершина.
         */

        @Override
        public void addEdge(int first, int second) {
            map.get(first).add(second);
            map.get(second).add(first);
        }

        /**
         * Удаление ребра из графа.
         * @param first Первая освобождаемая от связи вершина.
         * @param second Вторая освобождаемая от связи вершина.
         */

        @Override
        public void removeEdge(int first, int second) {
            map.get(first).remove(second);
            map.get(second).remove(first);
        }

        /**
         * Проверка наличия ребра.
         * @param first Первая вершина.
         * @param second Вторая вершина.
         */

        @Override
        public boolean isExistEdge(int first, int second) {
            if(map.get(first).contains(second)) {
                return true;
            }
            else{
                return false;
            }
        }

    }

    /**
     * @param numberNodes Количество вершин в графе.
     * @return Граф указанной конфигурации.
     */

    @Override
    public AbstractGraph createGraph(int numberNodes) {
        AbstractGraph graph = new Graph(numberNodes);

        return graph;
    }

}
