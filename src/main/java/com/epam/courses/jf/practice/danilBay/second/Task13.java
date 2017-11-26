package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;
import java.util.ArrayList;

public class Task13 implements ITestableTask13 {
    class Graph extends AbstractGraph{
        int[][] matrix;
        Graph(int num){
            super(num);
            matrix=new int[num][num];


        }
        Graph(){
            super(10);
            matrix=new int[10][10];

        }
        @Override
        public void addEdge(int first, int second) {
            matrix[first][second]=1;

        }

        @Override
        public void removeEdge(int first, int second) {
            matrix[first][second]=0;
        }

        @Override
        public boolean isExistEdge(int first, int second) {
           if(matrix[first][second]==1) {
               return true;
           }
           else {
               return false;
           }
        }
    }
    @Override
    public AbstractGraph createGraph(int numberNodes) {


        return new Graph(numberNodes);
    }

    public static void main(String[] args) {
        Task13 d=new Task13();
        AbstractGraph f=d.createGraph(10);
        f.addEdge(1,2);
        f.addEdge(1,3);
        System.out.println(f.isExistEdge(1,2));
        System.out.println(f.isExistEdge(1,1));
        System.out.println(f.isExistEdge(1,3));
        System.out.println(f.isExistEdge(0,2));


    }
}
