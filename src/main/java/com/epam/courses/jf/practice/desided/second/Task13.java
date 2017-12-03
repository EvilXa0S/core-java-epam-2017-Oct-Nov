package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.HashMap;
import java.util.HashSet;

public class Task13 implements ITestableTask13 {
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    private class Graph extends AbstractGraph {

        private HashMap<Integer, HashSet<Integer>> edges = new HashMap<>();

        public Graph(int numberNodes) {
            super(numberNodes);
            for (int i = 0; i < numberNodes; i++) {
                edges.put(i, new HashSet<>());
            }
        }

        @Override
        public void addEdge(int first, int second) {
            if (edges.containsKey(first) && !edges.get(first).contains(second)){
                edges.get(first).add(second);
                edges.get(second).add(first);
            }
        }

        @Override
        public void removeEdge(int first, int second) {
            if (edges.containsKey(first) && edges.get(first).contains(second)){
                edges.get(first).remove(second);
                edges.get(second).remove(first);
            }

        }

        @Override
        public boolean isExistEdge(int first, int second) {
            return edges.containsKey(first) && edges.get(first).contains(second);
        }
    }
}
