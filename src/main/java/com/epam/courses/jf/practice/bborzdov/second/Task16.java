package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.*;
import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by bogdan on 22.11.17.
 */

/**
 *  На клетчатой бумаге нарисован круг.
 * Вывести в файл описания всех клеток, целиком лежащих внутри круга.
 * Выводить в порядке возрастания расстояния от клетки до центра круга.
 * Использовать класс SortedMap.
 */
public class Task16 implements ITestableTask16 {
    /**
     * Осуществляет анализ переданных точек, находя среди них попавших внутрь круга.
     * @param center Точка, в которой расположен центр круга.
     * @param radius Радиус круга.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        FileWithPoints file = new FileWithPoints(output);
        double radiusDouble = (double) radius / 2;
        SortedMap<I2DPoint, Double> points = new TreeMap<>();
        for (int i = (int)(center.getY() + radiusDouble); i > (center.getY() - radiusDouble - 1) ; i--) {
            for (int j = (int)(center.getX() - radiusDouble); j < (center.getX()+radiusDouble+1) ; j++) {
                Point2D point = new Point2D(j,i);
                BigDecimal distance =new BigDecimal(Task15.distance(point,center));
                distance = distance.setScale(4, BigDecimal.ROUND_DOWN);
                point.setDistance(distance.doubleValue());
                points.put(point,distance.doubleValue());
            }
        }
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(output));
            objectOutputStream.write(points.size());
            for (I2DPoint point : points.keySet()) {
                objectOutputStream.writeObject(point);
            }
            objectOutputStream.close();
        }catch (IOException e){
            System.out.println("Failed when writing to file!\n");
            e.printStackTrace();
        }
        return file;
    }
    /**
     * Представляет файл, содержащий информацию о найденных точках.
     */
    private class FileWithPoints implements ITestableTask16.IFileWithPoints{
        private File output;
        public FileWithPoints(File output){
            this.output = output;
        }
        /**
         * @return Файл с результатами анализа.
         */
        @Override
        public File getFile() {
            return output;
        }
        /**
         * Извлекает из файла информацию о хранящихся в нем точках.
         * @return Множество пар: точка + расстояние до центра.
         */
        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            SortedMap<I2DPoint, Double> points = new TreeMap<>();
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(output));
                int steps = ois.read();
                for (int i = 0; i < steps; i++) {
                    Point2D point =(Point2D) ois.readObject();
                    points.put(point,point.getDistance());
                }
                ois.close();
            }catch (IOException | ClassNotFoundException e){
                System.out.println("Fail when reading from file\n");
            }
            return points;
        }
    }

}
