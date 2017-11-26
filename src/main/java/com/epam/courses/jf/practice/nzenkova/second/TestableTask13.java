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
         * Add the arc to the graph.
         * @param first The first vertex.
         * @param second The second vertex.
         */
        @Override
        public void addEdge(int first, int second) {
            graph[first][second] = 1;
        }

        /**
         * Delete the arc from the graph.
         * @param first The first vertex, which releases from the connection.
         * @param second The second vertex, which releases from the connection.
         */
        @Override
        public void removeEdge(int first, int second) {
            graph[first][second] = 0;
        }

        /**
         * Check of the existence of the arc.
         * @param first The first vertex.
         * @param second The second vertex.
         * @return
         */
        @Override
        public boolean isExistEdge(int first, int second) {
            return graph[first][second] == 1;
        }
    }
}
