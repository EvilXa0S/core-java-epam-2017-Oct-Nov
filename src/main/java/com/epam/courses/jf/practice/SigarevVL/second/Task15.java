package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Интерфейс для юнит-тестирования задания №15.
 *
 * На плоскости задано N точек.
 * Вывести в файл описания всех прямых, которые проходят более чем через 2 точки из заданных.
 */
public class Task15 implements ITestableTask15 {

    /**
     * Осуществляет анализ переданных точек, вычисляя линии, которые проходят более чем через 2 точки.
     * @param points Множество точек на плоскости.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {

        //List<ILineImpl> list = new ArrayList<>();
        Set<ILine> setLines = new HashSet<>();

        for (I2DPoint point : points) {
            for (I2DPoint anotherPoint : points) {
                if (!point.equals(anotherPoint)) {
                    setLines.add(new ILineImpl(point, anotherPoint));
                }
            }
        }

        for (ILine line : setLines) {

            List<I2DPoint> linePoints = new ArrayList<>(line.getPoints());
            double x1 = linePoints.get(0).getX();
            double x2 = linePoints.get(1).getX();
            double y1 = linePoints.get(0).getY();
            double y2 = linePoints.get(1).getY();

            //((x3 - x1) * (y2 - y1) == (x2 - x1) * (y3 - y1))
            for (I2DPoint point : points) {
                if ((point.getX() - x1) * (y2 - y1)
                        == (x2 - x1) * (point.getY() - y1)) {
                    line.getPoints().add(point);

                }
            }
        }
        //setLines.addAll(list);
        return new IFileWithLinesImpl(output, setLines);
    }

    /**
     * Представляет файл, содержащий информацию о найденных линиях.
     */
    public class IFileWithLinesImpl implements IFileWithLines {

        private File file;
        private Set<ILine> set;

        public IFileWithLinesImpl(File file, Set<ILine> set) {
            this.file = file;
            this.set = new HashSet<>(set);
        }

        /**
         * @return Файл с результатами анализа.
         */
        @Override
        public File getFile() {
            return file;
        }

        /**
         * Извлекает из файла информацию о хранящихся в нем линиях.
         * @return Множество линий, найденных в результате анализа.
         */
        @Override
        public Set<ILine> getLines() {
            return set;
        }
    }

    /**
     * Прямая, заданная точками, входящими в исходное множество.
     */
    public class ILineImpl implements ILine {

        Set<I2DPoint> setLinePoint = new HashSet<>();


        public ILineImpl(I2DPoint firstPoint, I2DPoint secondPoint) {

            setLinePoint.add(firstPoint);
            setLinePoint.add(secondPoint);
        }






        /** @return Точки, через которые проходит прямая */
        @Override
        public Set<I2DPoint> getPoints() {
            return setLinePoint;
        }

    }
}
