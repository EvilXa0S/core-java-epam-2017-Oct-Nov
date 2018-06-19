package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * При создании задается количество вершин.
 * Нумерация вершин начинается с 0.
 * Допустимы операции добавления, удаления и проверки существования ребер.
 */
public class MyGraph extends ITestableTask13.AbstractGraph {

    /**
     * Map of edges
     * Key - vertex, Value - Set of another vertex
     */
    private HashMap<Integer, Set<Integer>> edgesMap = new HashMap<>();

    public MyGraph(int numberNodes) {
        super(numberNodes);
    }

    @Override
    public void addEdge(int first, int second) {
        // key - is always the min of two values!
        int key = Math.min(first, second);
        int value = Math.max(first, second);

        if (!edgesMap.containsKey(key)) {
            edgesMap.put(key, new HashSet<>());
        }
        edgesMap.get(key).add(value);
    }

    @Override
    public void removeEdge(int first, int second) {
        int key = Math.min(first, second);
        int value = Math.max(first, second);

        if (isExistEdge(key, value)) {
            edgesMap.get(key).remove(value);
        }
    }

    @Override
    public boolean isExistEdge(int first, int second) {
        int key = Math.min(first, second);
        int value = Math.max(first, second);

        return edgesMap.containsKey(key) && edgesMap.get(key).contains(value);
    }
}