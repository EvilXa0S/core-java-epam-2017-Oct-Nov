package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.ArrayList;
import java.util.HashSet;


public class Task13 implements ITestableTask13 {
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    class Graph extends AbstractGraph {

        ArrayList<HashSet<Integer>> nodes;

        public Graph(int numberNodes) {
            super(numberNodes);
            nodes = new ArrayList<>(numberNodes);
            for (int i = 0; i < numberNodes; i++) {
                nodes.add(new HashSet<>());
            }
        }

        public void addEdge(int first, int second) {
            if (first >= 0 && first < nodes.size() && second >= 0 && second < nodes.size()) {
                nodes.get(first).add(second);
                nodes.get(second).add(first);
            }
        }

        public void removeEdge(int first, int second) {
            if (first >= 0 && first < nodes.size() && second >= 0 && second < nodes.size()) {
                nodes.get(first).remove(second);
                nodes.get(second).remove(first);
            }
        }

        public boolean isExistEdge(int first, int second) {
            if (first >= 0 && first < nodes.size() && second >= 0 && second < nodes.size()) {
                return nodes.get(first).contains(second);
            }
            return false;
        }

    }
}

