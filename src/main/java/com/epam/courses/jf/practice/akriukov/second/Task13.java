package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

/**
 * Реализовать класс Graph, представляющий собой неориентированный граф (между двумя вершинами допустимо максимум одно ребро).
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
     * Implementation of graph with using adjacency matrix
     */
    public class Graph extends AbstractGraph {

        //Adjacency matrix
        public boolean graph[][];

        public Graph(int numberNodes) {
            super(numberNodes);
            graph = new boolean[numberNodes][numberNodes];
        }

        @Override
        public void addEdge(int first, int second) {
            graph[first][second] = true;
        }

        @Override
        public void removeEdge(int first, int second) {
            graph[first][second] = false;
        }

        @Override
        public boolean isExistEdge(int first, int second) {
            return graph[first][second];
        }
    }
}
