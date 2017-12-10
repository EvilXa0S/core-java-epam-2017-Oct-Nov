package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask13;

import java.util.HashSet;

public class TestableTask13 implements ITestableTask13 {
    @Override
    public AbstractGraph createGraph(int numberNodes) {
        AbstractGraph grath = new Grath(numberNodes);
        return grath;
    }
    class Grath extends AbstractGraph {
        private HashSet<String> edges = new HashSet<>();
        public Grath(int numberNodes) {
            super(numberNodes);
        }

        @Override
        public void addEdge(int first, int second) {
            if(second<first){
                int tempVal = second;
                second = first;
                first = tempVal;
            }
            if(first < super.NUMBER_NODES && second <= super.NUMBER_NODES){
                StringBuilder temp = new StringBuilder();
                temp.append(first).append(second);
                this.edges.add(temp.toString());
            }
        }

        @Override
        public void removeEdge(int first, int second) {
            if(second<first){
                int tempVal = second;
                second = first;
                first = tempVal;
            }
            StringBuilder temp = new StringBuilder();
            temp.append(first).append(second);
            if((!this.edges.isEmpty()) && this.edges.contains(temp.toString())){
                this.edges.remove(temp.toString());
            }

        }

        @Override
        public boolean isExistEdge(int first, int second) {
            if(second<first){
                int tempVal = second;
                second = first;
                first = tempVal;
            }
            StringBuilder temp = new StringBuilder();
            temp.append(first).append(second);
            if(this.edges.contains(temp.toString())){
                return true;
            }
            return false;
        }
    }
}
