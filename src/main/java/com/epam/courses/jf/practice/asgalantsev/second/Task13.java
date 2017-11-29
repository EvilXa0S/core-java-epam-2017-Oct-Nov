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
                nodes.put(i, null);
        }

        @Override
        public void addEdge(int first, int second) {
            if(nodes.get(first) == null) {
                HashSet<Integer> set = new HashSet<>();
                set.add(second);
                nodes.put(first, set);
            }
            else {
                Set<Integer> oldSet = nodes.get(first);
                Set<Integer> newSet = new HashSet<>(oldSet);
                oldSet.add(second);
                nodes.put(first, oldSet);
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
            if (nodes.get(first) == null)
                return false;

            if(nodes.get(first).isEmpty())
                return false;

            if(nodes.get(first).contains(new Integer(second)))
                return true;
            return false;
        }
    }
}
