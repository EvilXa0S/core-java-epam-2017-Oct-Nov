package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.*;
import java.util.*;

/**
 * На клетчатой бумаге нарисован круг.
 * Вывести в файл описания всех клеток, целиком лежащих внутри круга.
 * Выводить в порядке возрастания расстояния от клетки до центра круга.
 * Использовать класс SortedMap.
 */

public class Task16 implements ITestableTask16 {

    /**
     * Точка, заданная координатами X, Y.
     */

    class Point2D implements I2DPoint{
        double x, y;

        Point2D(double x, double y){
            this.x = x;
            this.y = y;
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }
    }

    /**
     * Представляет файл, содержащий информацию о найденных точках.
     */

    class FileWithPoints implements IFileWithPoints {
        private File file;
        private Comparator<I2DPoint> comparator;

        FileWithPoints (File file, Comparator<I2DPoint> comparator) {
            this.file = file;
            this.comparator = comparator;
        }

        /**
         * @return Файл с результатами анализа.
         */

        public File getFile (){
            return file;
        }

        /**
         * Извлекает из файла информацию о хранящихся в нем точках.
         * @return Множество пар: точка + расстояние до центра.
         */

        public SortedMap<I2DPoint, Double> getPoints () {
            List<String> pointsString = readFile(file);
            SortedMap<I2DPoint, Double> pointsMap = new TreeMap<>(comparator);
            I2DPoint center = stringToPoint(pointsString.get(0));
            int size = pointsString.size();
            I2DPoint point;

            for (int j = 1; j < size; j++) {
                point = stringToPoint(pointsString.get(j));
                pointsMap.put(point, Task16.calculateDistance(point, center));
            }

            return pointsMap;
        }
    }

    /**
     * Вычисление расстояния между точками
     */

    static double calculateDistance(I2DPoint point1, I2DPoint point2) {
        double dist = Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
        return dist;
    }

    /**
     * Определение, лежит ли точка в круге
     * @param point
     * @param center
     * @param radius
     * @return ответ
     */

    boolean isPointInCircle(I2DPoint point, I2DPoint center, int radius){
        double distance = calculateDistance(point, center);

        if(distance < Double.valueOf(radius)){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Преоразование точки в строку для записи в файл.
     * @param point
     * @param center
     * @return Строка с координатами
     */

    String toStringForFile (I2DPoint point, I2DPoint center) {
        String str = String.valueOf(point.getX()) + "\t" + String.valueOf(point.getY()) + "\t" + String.valueOf(calculateDistance(point, center) + "\n");
        return str;
    }

    /**
     * Чтение файла
     * @param file файл для чтениея
     * @return Список строк файла
     */

    List<String> readFile(File file) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String str;
            List<String> result = new ArrayList<>();

            while ((str = br.readLine()) != null) {
                result.add(str);
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                fr.close();
                br.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Преобразование строки в объект Point
     * @param string строка с координатами
     * @return объект Point
     */

    I2DPoint stringToPoint(String string) {
        String[] splitString = string.split("\\t");
        Double x = Double.parseDouble(splitString[0]);
        Double y = Double.parseDouble(splitString[1]);
        I2DPoint point = new Point2D(x, y);

        return point;
    }

    /**
     * Осуществляет анализ переданных точек, находя среди них попавших внутрь круга.
     * @param center Точка, в которой расположен центр круга.
     * @param radius Радиус круга.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */

    @Override
    public IFileWithPoints analyze (I2DPoint center, int radius, File output) {
        I2DPoint point;
        double xCenter = (int) center.getX();
        double yCenter = (int) center.getY();
        int xFirst = (int) (xCenter - radius - 1);
        int xEnd = (int) (xCenter + radius + 1);
        int yFirst = (int) (yCenter - radius - 1);
        int yEnd = (int) (yCenter + radius + 1);

        Comparator<I2DPoint> comparator = (p1, p2) -> {
            Double distance1 = calculateDistance(p1, center);
            Double distance2 = calculateDistance(p2, center);
            int comparison = distance1.compareTo(distance2);

            if (comparison == 0) {
                return -1;
            }
            else {
                return comparison;
            }
        };

        SortedMap<I2DPoint, Double> map = new TreeMap<>(comparator);

        for (int j = xFirst; j <= xEnd; j++) {
            for (int k = yFirst; k <= yEnd; k++) {
                point = new Point2D(j, k);
                if (isPointInCircle(point, center, radius)) {
                    map.put(point, calculateDistance(point, center));
                }
            }
        }

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(output);
            bw = new BufferedWriter(fw);
            bw.write(toStringForFile(center, center));

            for (Map.Entry<I2DPoint, Double> entry : map.entrySet()) {
                point = entry.getKey();
                bw.write(toStringForFile(point, center));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new FileWithPoints(output, comparator);
    }

}