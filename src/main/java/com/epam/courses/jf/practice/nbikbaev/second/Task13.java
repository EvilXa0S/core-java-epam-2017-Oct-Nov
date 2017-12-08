package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.List;

public class Task13 implements ITestableTask13 {
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    class Graph extends AbstractGraph {

        boolean[][] edges;

        public Graph(int numberNodes) {
            super(numberNodes);
            this.edges = new boolean[numberNodes][numberNodes];
        }

        @Override
        public void addEdge(int first, int second) {
            edges[first][second] = true;
            edges[second][first] = true;
        }

        @Override
        public void removeEdge(int first, int second) {
            edges[first][second] = false;
            edges[second][first] = false;
        }

        @Override
        public boolean isExistEdge(int first, int second) {
            return edges[first][second];
        }
    }

}
