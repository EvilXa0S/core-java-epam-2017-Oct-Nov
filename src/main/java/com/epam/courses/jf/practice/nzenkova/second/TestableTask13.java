package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

/**
 * Realize the class Graph, which is an undirected graph(there is only one arc between two vertices).
 * The constructor of the class takes the amount of the vertices in the graph.
 * The methods should support the quick addition and removal of the arcs.
 */
public class TestableTask13 implements ITestableTask13 {
    /**
     * @param numberNodes The amount of the vertices in the graph.
     * @return The graph of the specified configuration.
     */
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    /**
     * Abstract graph.
     * Specify the amount of the vertices with creation.
     * The numbering starts from 0.
     * Add, remove and check of the existence of the arcs are allowed.
     */
    class Graph extends AbstractGraph {
        private int[][] graph;

        public Graph(int numberNodes) {
            super(numberNodes);
            graph = new int[NUMBER_NODES][NUMBER_NODES];
        }


        /**
         *
         * @param first Первая связываемая вершина.
         * @param second Вторая связываемая вершина.
         */
        @Override
        public void addEdge(int first, int second) {
            graph[first][second] = 1;
        }

        /**
         *
         * @param first Первая освобождаемая от связи вершина.
         * @param second Вторая освобождаемая от связи вершина.
         */
        @Override
        public void removeEdge(int first, int second) {
            graph[first][second] = 0;
        }

        /**
         *
         * @param first Первая вершина.
         * @param second Вторая вершина.
         * @return
         */
        @Override
        public boolean isExistEdge(int first, int second) {
            return graph[first][second] == 1;
        }
    }
}
