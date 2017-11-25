package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.*;
import java.util.SortedMap;
import java.util.TreeMap;


public class TestableTask16 implements ITestableTask16 {
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        findPoints(output, radius, center);
        return new FileWithPoints(output, center);
    }

    /**
     * записывает в файл найденные точки
     *
     * @param output файл для записи
     */
    private void findPoints(File output, int radius, I2DPoint center) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(output.getAbsoluteFile()))) {
            for (int i = (int) center.getX() - radius - 1; i < (int) center.getX() + radius + 1; i++) {
                for (int j = (int) center.getY() - radius - 1; j < (int) center.getY() + radius + 1; j++) {
                    if (isInCircle(radius, i, j, center)) {
                        out.write(i + " " + j + " " + dist(new TwoDPoint(i, j), center) + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param radius радиус круга
     * @param x      абсцисса точки
     * @param y      ордината точки
     * @param center центр круга
     * @return true, если точка в круге, false иначе
     */
    private boolean isInCircle(int radius, int x, int y, I2DPoint center) {
        return Double.compare(Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2), Math.pow(radius, 2)) < 0;
    }

    /**
     * @param point  точка
     * @param center центр круга
     * @return расстояние от точки до центра
     */
    private double dist(I2DPoint point, I2DPoint center) {
        return Math.sqrt(Math.pow(point.getX() - center.getX(), 2) + Math.pow(point.getY() - center.getY(), 2));
    }

    class FileWithPoints implements ITestableTask16.IFileWithPoints {
        private File out;
        private I2DPoint center;

        public FileWithPoints(File out, I2DPoint center) {
            this.out = out;
            this.center = center;
        }

        @Override
        public File getFile() {
            return out;
        }

        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            SortedMap<I2DPoint, Double> map = new TreeMap<>((o1, o2) -> {
                int res = Double.compare(dist(o1), dist(o2));
                if (res == 0) {
                    int x = Double.compare(o1.getX(), o2.getX());
                    int y = Double.compare(o1.getY(), o2.getY());
                    return x != 0 ? x : y;
                }
                return res;
            });
            try (BufferedReader in = new BufferedReader(new FileReader(out))) {
                String line;
                while ((line = in.readLine()) != null) {
                    String[] data = line.split(" ");
                    map.put(new TwoDPoint((double) Integer.parseInt(data[0]), (double) Integer.parseInt(data[1])), Double.parseDouble(data[2]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        }

        /**
         * расстояние от точки до центра
         */
        private double dist(I2DPoint point) {
            return Math.sqrt(Math.pow(point.getX() - center.getX(), 2) + Math.pow(point.getY() - center.getY(), 2));
        }
    }
}
