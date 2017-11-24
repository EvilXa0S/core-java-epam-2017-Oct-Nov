package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.*;

public class Task13 implements ITestableTask13 {

    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    class Graph extends ITestableTask13.AbstractGraph {

        private HashMap<Integer, Set<Integer>> nodes;

        public Graph(int numberNodes) {
            super(numberNodes);
            nodes = new HashMap<>(numberNodes);
            for(int i=0; i < nodes.size(); i++)
                nodes.put(i, Collections.emptySet());
        }

        @Override
        public void addEdge(int first, int second) {
            if (isExistEdge(first, second))
                nodes.get(first).add(second);
            else {
                Set set = new HashSet(Arrays.asList(new int[]{second}));
                nodes.replace(first, set);
            }
        }

        @Override
        public void removeEdge(int first, int second) {
            if (isExistEdge(first, second))
                if (nodes.get(first).size() > 1)
                    nodes.get(first).remove(second);
                else
                    nodes.replace(first, Collections.emptySet());
        }

        @Override
        public boolean isExistEdge(int first, int second) {
            if (nodes.get(first) == null || nodes.get(first).isEmpty())
                return false;

            if(nodes.get(first).contains(second))
                return true;
            return false;
        }
    }
}
