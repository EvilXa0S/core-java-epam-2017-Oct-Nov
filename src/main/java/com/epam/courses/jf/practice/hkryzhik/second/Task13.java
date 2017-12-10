package com.epam.courses.jf.practice.hkryzhik.second;

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

    public class AbstractGraph extends ITestableTask13.AbstractGraph{

        private Map<Integer, HashSet<Integer>> nodes = new HashMap<>();

        public AbstractGraph(int numberNodes) {

            super(numberNodes);

            for (int i = 0; i < numberNodes; i++) {

                nodes.put(i, new HashSet<>());

            }
        }

        @Override
        public void addEdge(int first, int second) {

            if(nodes.containsKey(first) && !nodes.get(first).contains(second)){

                nodes.get(first).add(second);

            }
        }

        @Override
        public void removeEdge(int first, int second) {

            if(nodes.containsKey(first)) {

                if(nodes.get(first).contains(second)) {

                    nodes.get(first).remove(second);

                }
            }
        }

        @Override
        public boolean isExistEdge(int first, int second) {
            if(nodes.containsKey(first)){

                if(nodes.get(first).contains(second)){

                    return true;

                }else return false;

            }else return false;
        }
    }
}
