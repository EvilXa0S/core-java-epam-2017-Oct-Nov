package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

/**
 * Created by bogdan on 20.11.17.
 */

/**
 * Реализовать класс Graph, представляющий собой неориентированный граф.
 * В конструкторе класса передается количество вершин в графе.
 * Методы должны поддерживать быстрое добавление и удаление ребер.
 */
public class Graph extends ITestableTask13.AbstractGraph implements ITestableTask13{

    private boolean matrixAdjacency[][];

    Graph(int numberNodes) {
        super(numberNodes);
        matrixAdjacency = new boolean[numberNodes][numberNodes];
    }
    /** Создаёт неориентированный граф с заданным количеством вершин
     * @param numberNodes Количество вершин в графе.
     * @return Граф указанной конфигурации.
     */
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    /**
     * Добавляет ребро между переданными вершинами графа
     * @param first Первая связываемая вершина.
     * @param second Вторая связываемая вершина.
     */
    @Override
    public void addEdge(int first, int second) {
        matrixAdjacency[first][second] = true;
        matrixAdjacency[second][first] = true;
    }

    /**
     * Удаляет ребро между переданными вершинами графа
     * @param first Первая освобождаемая от связи вершина.
     * @param second Вторая освобождаемая от связи вершина.
     */
    @Override
    public void removeEdge(int first, int second) {
        matrixAdjacency[first][second] = false;
        matrixAdjacency[second][first] = false;
    }

    /**
     * Проверяет наличие ребра между переданными вершинами графа
     * @param first Первая вершина.
     * @param second Вторая вершина.
     * @return true если ребро существует или false, если ребро отсутствует
     */
    @Override
    public boolean isExistEdge(int first, int second) {
        return matrixAdjacency[first][second];
    }
}
