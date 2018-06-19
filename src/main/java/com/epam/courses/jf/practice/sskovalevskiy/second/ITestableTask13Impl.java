package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asus on 01.11.2017.
 * <p>
 * Реализовать класс Graph, представляющий собой неориентированный граф.
 * В конструкторе класса передается количество вершин в графе.
 * Методы должны поддерживать быстрое добавление и удаление ребер.
 */
public class ITestableTask13Impl implements ITestableTask13 {

    /**
     * @param numberNodes Количество вершин в графе.
     * @return Граф указанной конфигурации.
     */
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    /**
     * Абстрактный граф.
     * При создании задается количество вершин.
     * Нумерация вершин начинается с 0.
     * Допустимы операции добавления, удаления и проверки существования ребер.
     */
    class Graph extends AbstractGraph {

        Set<Edge> edges;

        public Graph(int numberNodes) {
            super(numberNodes);
            edges = new HashSet<>();
        }

        /**
         * Добавление ребра в граф.
         *
         * @param first  Первая связываемая вершина.
         * @param second Вторая связываемая вершина.
         */
        @Override
        public void addEdge(int first, int second) {
            if (first < second) {
                edges.add(new Edge(first, second));
            } else {
                edges.add(new Edge(second, first));
            }
        }

        /**
         * Удаление ребра из графа.
         *
         * @param first  Первая освобождаемая от связи вершина.
         * @param second Вторая освобождаемая от связи вершина.
         */
        @Override
        public void removeEdge(int first, int second) {
            if (first < second) {
                edges.remove(new Edge(first, second));
            } else {
                edges.remove(new Edge(second, first));
            }

        }

        /**
         * Проверка наличия ребра.
         *
         * @param first  Первая вершина.
         * @param second Вторая вершина.
         */
        @Override
        public boolean isExistEdge(int first, int second) {
            if (first < second) {
                return edges.contains(new Edge(first, second));
            } else {
                return edges.contains(new Edge(second, first));
            }
        }

        class Edge<Integer> {
            private Integer first;
            private Integer second;

            public Edge(Integer first, Integer second) {
                this.first = first;
                this.second = second;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Edge<?> edge = (Edge<?>) o;

                return (first != null ? first.equals(edge.first) : edge.first == null) &&
                        (second != null ? second.equals(edge.second) : edge.second == null);
            }

            @Override
            public int hashCode() {
                int result = first != null ? first.hashCode() : 0;
                result = 31 * result + (second != null ? second.hashCode() : 0);
                return result;
            }
        }
    }
}
