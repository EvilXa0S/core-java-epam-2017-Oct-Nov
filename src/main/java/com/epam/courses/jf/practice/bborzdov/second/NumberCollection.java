package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.*;

/**
 * Created by bogdan on 20.11.17.
 */

/**
 * Коллекция для хранения чисел.
 */
public class NumberCollection implements ITestableTask14.INumberCollection {
    private List list;
    public NumberCollection(){
        list = new LinkedList<Number>();
    }

    /**
     * Ищет значение, ближайшее переданному value в коллекции
     * @param value Эталонное значение.
     * @return значение, ближайшее переданному value в коллекции
     */
    @Override
    public Number nearest(Number value) {
        Number delta = Double.MAX_VALUE;
        Number res = 0;
        for (Object o : list) {
            Number tmp = (Number) o;
            if (Math.abs(tmp.doubleValue() - value.doubleValue()) <= delta.doubleValue()) {
                delta = Math.abs(tmp.doubleValue() - value.doubleValue());
                res = tmp;
            }
        }
        return res;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * Проверяет, не пуста ли коллекция
     * @return false, если не пуста, иначе true
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Проверяет наличие переданного объекта o в коллекции
     * @param o проверяемый объект
     * @return true, если o содержится в коллекции, иначе false
     */
    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /**
     * Добавляет новый элемент в коллекцию
     * @param o
     * @return true, если объект был добавлен в коллекцию, иначе false
     */
    @Override
    public boolean add(Object o) {
        return list.add(o);
    }

    /**
     * Удаляет переданный объект o из коллекции
     * @param o
     * @return true, если o был успешно удалён, иначе false
     */
    @Override
    public boolean remove(Object o) {

        return list.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
