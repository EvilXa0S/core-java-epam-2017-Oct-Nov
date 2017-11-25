package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

public class Task13 implements ITestableTask13 {
    @Override
    public AbstractGraph createGraph(int numberNodes) {

        return new Graph(numberNodes);
    }

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
