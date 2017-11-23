package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

/**
 * Интерфейс для юнит-тестирования задания №13.
 *
 * Реализовать класс Graph, представляющий собой неориентированный граф.
 * В конструкторе класса передается количество вершин в графе.
 * Методы должны поддерживать быстрое добавление и удаление ребер.
 */
public class Task13 implements ITestableTask13 {

    @Override
    public AbstractGraph createGraph(int numberNodes) {

        return new Graph(numberNodes);
    }

    public class Graph extends AbstractGraph {

        /** Матрица смежности */
        boolean graph[][];

        public Graph(int numberNodes) {
            super(numberNodes);
            graph = new boolean[numberNodes][numberNodes];
        }

        /**
         * Добавление ребра в граф.
         * @param first Первая связываемая вершина.
         * @param second Вторая связываемая вершина.
         */
        @Override
        public void addEdge(int first, int second) {

            graph[first][second] = true;
        }

        /**
         * Удаление ребра из графа.
         * @param first Первая освобождаемая от связи вершина.
         * @param second Вторая освобождаемая от связи вершина.
         */
        @Override
        public void removeEdge(int first, int second) {

            graph[first][second] = false;
        }

        /**
         * Проверка наличия ребра.
         * @param first Первая вершина.
         * @param second Вторая вершина.
         */
        public  boolean isExistEdge(int first, int second) {

            return graph[first][second];
        }


    }

}
