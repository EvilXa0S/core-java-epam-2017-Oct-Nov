package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Реализовать класс Graph, представляющий собой неориентированный граф (между двумя вершинами допустимо максимум одно
 * ребро).
 * В конструкторе класса передается количество вершин в графе.
 * Методы должны поддерживать быстрое добавление и удаление ребер.
 */
public class Task13 implements ITestableTask13 {

    /**
     * @param numberNodes Количество вершин в графе.
     * @return Граф указанной конфигурации.
     */
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    /**
     * При создании задается количество вершин.
     * Нумерация вершин начинается с 0.
     * Допустимы операции добавления, удаления и проверки существования ребер.
     */
    private class Graph extends AbstractGraph {

        private HashMap<Integer, HashSet<Integer>> edges = new HashMap<>();

        private Graph(int numberNodes) {
            super(numberNodes);
            for (int i = 0; i < numberNodes; i++) {
                edges.put(i, new HashSet<>());
            }
        }

        /**
         * Добавление ребра в граф.
         *
         * @param first  Первая связываемая вершина.
         * @param second Вторая связываемая вершина.
         */
        @Override
        public void addEdge(int first, int second) {
            if (edges.containsKey(first) && !edges.get(first).contains(second)) {
                edges.get(first).add(second);
                edges.get(second).add(first);
            }
        }

        /**
         * Удаление ребра из графа.
         *
         * @param first  Первая освобождаемая от связи вершина.
         * @param second Вторая освобождаемая от связи вершина.
         */
        @Override
        public void removeEdge(int first, int second) {
            if (edges.containsKey(first) && edges.get(first).contains(second)) {
                edges.get(first).remove(second);
                edges.get(second).remove(first);
            }
        }

        /**
         * Проверка наличия ребра.
         *
         * @param first  Первая вершина.
         * @param second Вторая вершина.
         */
        @Override
        public boolean isExistEdge(int first, int second) {
            return edges.containsKey(first) && edges.get(first).contains(second);
        }
    }

}
