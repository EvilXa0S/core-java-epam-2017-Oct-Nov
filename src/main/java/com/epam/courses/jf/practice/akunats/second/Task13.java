package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Task13 implements ITestableTask13 {
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new AbstractGraph(numberNodes);
    }

    public class AbstractGraph extends ITestableTask13.AbstractGraph {
        // Ключ это вершина, set это набор связанныхс ней вершин
        private Map<Integer, Set<Integer>> graph = new HashMap<>();

        AbstractGraph(int numberNodes) {
            super(numberNodes);
            for (int i = 0; i < numberNodes; i++) {
                graph.put(i, new HashSet<>());
            }
        }

        @Override
        public void addEdge(int first, int second) {
            Set<Integer> firstSet = graph.get(first);
            firstSet.add(second);
            Set<Integer> secondSet = graph.get(second);
            secondSet.add(first);
        }

        @Override
        public void removeEdge(int first, int second) {
            Set<Integer> firstSet = graph.get(first);
            firstSet.remove(second);
            Set<Integer> secondSet = graph.get(second);
            secondSet.remove(first);
        }

        @Override
        public boolean isExistEdge(int first, int second) {
            Set<Integer> firstSet = graph.get(first);
            Set<Integer> secondSet = graph.get(second);
            return firstSet.contains(second) && secondSet.contains(first);
        }
    }
}
