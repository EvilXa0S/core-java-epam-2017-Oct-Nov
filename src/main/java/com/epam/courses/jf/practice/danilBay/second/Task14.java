package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
public class Task14 implements ITestableTask14 {
    class Col<T extends Number>extends AbstractCollection<T> implements INumberCollection<T> {
        ArrayList<T>c;
        Col(){
            c=new ArrayList<>();
        }
        @Override
        public Number nearest(Number value) {
            double near=Math.abs(c.get(0).doubleValue()-value.doubleValue());
            int res=0;
            double buf;
            for( int i=1; i<c.size(); i++){
                buf=Math.abs(c.get(i).doubleValue()-value.doubleValue());
                if(buf<near){
                    res=i;
                    near=buf;
                }
            }
            return c.get(res);

        }

        @Override
        public int size() {
            return c.size();
        }


        @Override
        public boolean contains(Object o) {

            if(c.contains((T)o))return true;
            else return false;
        }

        @NotNull
        @Override
        public Iterator iterator() {
            return c.iterator();
        }



        @Override
        public boolean add(T  o) {
            return add(o);

        }



    }
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {

        return new Col<T>();
    }

    public static void main(String[] args) {
        Task14 d=new Task14();

        //Col<Integer> s=d.createCollection(Integer.TYPE);
    }
}
