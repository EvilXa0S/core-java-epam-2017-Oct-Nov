package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by igorvahonin on 13.11.17.
 */
public class Task13 implements ITestableTask13{
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        AbstractGraph graph = new Graph(numberNodes);
        return graph;
    }

    class Graph extends AbstractGraph {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        public Graph(int numberNodes) {
            super(numberNodes);
            Set<Integer> set;
            for(int j = 0; j < numberNodes; j++){
                set = new HashSet<>();
//                set.add(j);
                map.put(j, new HashSet<>(set));
            }
        }

        @Override
        public void addEdge(int first, int second) {
            map.get(first).add(second);
            map.get(second).add(first);
        }

        @Override
        public void removeEdge(int first, int second) {
            map.get(first).remove(second);
            map.get(second).remove(first);
        }

        @Override
        public boolean isExistEdge(int first, int second) {
            if(map.get(first).contains(second)) {
                return true;
            }
            else{
                return false;
            }
        }

    }
}
