package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

/**
 * Created by bogdan on 20.11.17.
 */
public class Graph extends ITestableTask13.AbstractGraph implements ITestableTask13{

    protected boolean matrixAdjacency[][];

    Graph(int numberNodes) {
        super(numberNodes);
        matrixAdjacency = new boolean[numberNodes][numberNodes];
    }

    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    @Override
    public void addEdge(int first, int second) {
        matrixAdjacency[first][second] = true;
        matrixAdjacency[second][first] = true;
    }

    @Override
    public void removeEdge(int first, int second) {
        matrixAdjacency[first][second] = false;
        matrixAdjacency[second][first] = false;
    }

    @Override
    public boolean isExistEdge(int first, int second) {
        return matrixAdjacency[first][second];
    }
}
