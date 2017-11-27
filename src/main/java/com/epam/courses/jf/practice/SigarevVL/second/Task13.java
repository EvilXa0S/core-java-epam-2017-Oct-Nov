package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

/**
 * Implements the class Graph, which is an undirected graph.
 * The number of vertices in the graph is passed to the class constructor.
 * Methods support the rapid addition and removal of edges.
 */
public class Task13 implements ITestableTask13 {

    /**
     * @param numberNodes The number of vertices in the graph.
     * @return The graph of the specified configuration.
     */
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        return new Graph(numberNodes);
    }

    /**
     * Graph.
     * The numbering of vertices begins with 0.
     * The operations of adding, removing
     * and checking the existence of edges are available.
     */
    public class Graph extends AbstractGraph {

        /**
         * Adjacency matrix
         */
        boolean graph[][];

        public Graph(int numberNodes) {
            super(numberNodes);
            graph = new boolean[numberNodes][numberNodes];
        }

        /**
         * Adding an edge to a graph.
         *
         * @param first  The first bound vertex.
         * @param second The second bound vertex.
         */
        @Override
        public void addEdge(int first, int second) {
            graph[first][second] = true;
        }

        /**
         * Removing an edge from the graph.
         *
         * @param first  The first vertex is released from communication.
         * @param second The second vertex is released from communication.
         */
        @Override
        public void removeEdge(int first, int second) {
            graph[first][second] = false;
        }

        /**
         * Check the presence of an edge.
         *
         * @param first  The first vertex.
         * @param second The second vertex.
         */
        public boolean isExistEdge(int first, int second) {
            return graph[first][second];
        }


    }

}
