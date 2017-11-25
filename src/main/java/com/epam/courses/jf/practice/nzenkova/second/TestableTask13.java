package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;


public class TestableTask13 implements ITestableTask13 {
    /**
     * @param numberNodes The amount of vertices in the graph.
     * @return The graph of the specified configuration.
     */
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    class Graph extends AbstractGraph {
        private int[][] graph;

        public Graph(int numberNodes) {
            super(numberNodes);
            graph = new int[numberNodes][numberNodes];
        }


        @Override
        public void addEdge(int first, int second) {
            graph[first][second] = 1;
        }


        @Override
        public void removeEdge(int first, int second) {
            graph[first][second] = 0;
        }

        @Override
        public boolean isExistEdge(int first, int second) {
            return graph[first][second] == 1;
        }
    }

}
