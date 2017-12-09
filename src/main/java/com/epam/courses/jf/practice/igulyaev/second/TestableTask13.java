package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class TestableTask13 implements ITestableTask13 {

    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    private static class Graph extends ITestableTask13.AbstractGraph {

        private final List<Set<Integer>> vertices;

        public Graph(int numberNodes) {
            super(numberNodes);
            vertices = new ArrayList<>(NUMBER_NODES);
            for(int i = 0; i < NUMBER_NODES; ++i) {
                vertices.add(new HashSet<>());
            }
        }

        public void addEdge(int first, int second) {
            vertices.get(first).add(second);
            vertices.get(second).add(first);
        }

        public void removeEdge(int first, int second) {
            vertices.get(first).remove(second);
            vertices.get(second).remove(first);
        }

        public boolean isExistEdge(int first, int second) {
            return vertices.get(first).contains(second);
        }
    }
}