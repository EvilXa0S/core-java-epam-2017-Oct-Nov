package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Task16 implements ITestableTask16 {

    private Double length(I2DPoint o, I2DPoint centre) {

        double A = Math.abs(o.getX()) - Math.abs(centre.getX());

        double B = Math.abs(o.getY()) - Math.abs(centre.getY());

        return Math.sqrt((A * A) + (B * B));
    }

    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {

        SortedMap<I2DPoint, Double> result = new TreeMap<>((o1, o2)
                -> Double.compare(length(o1, center), length(o2, center)) == 0 ? 1 : -1);

        double x0 = center.getX();
        double y0 = center.getY();

        for (int i = (int) (x0 - (radius - 1)); i < (int) Math.round(x0 + radius); i++) {

            for (int j = (int) (y0 - (radius - 1)); j < (int) Math.round(y0 + radius); j++) {

                I2DPoint point = new Point2D(i, j);

                double length = length(point, center);

                if (length < radius) {

                    result.put(point, length);
                }
            }
        }
        return new FileWithPoints(result, output, center);
    }

    class FileWithPoints implements IFileWithPoints {

        private File file;
        SortedMap<I2DPoint, Double> map;
        private I2DPoint center;

        FileWithPoints(SortedMap<I2DPoint, Double> map, File file, I2DPoint center) {

            this.center = center;
            this.map = map;
            this.file = file;

            try (PrintWriter writer = new PrintWriter(file)) {

                for (Map.Entry<I2DPoint, Double> entry : map.entrySet()) {

                    writer.write(entry.getKey().getX() + " "
                            + entry.getKey().getY() + " "
                            + entry.getValue() + "\n");
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public SortedMap<I2DPoint, Double> getPoints() {

            SortedMap<I2DPoint, Double> newMap = new TreeMap<>((o1, o2)
                    -> Double.compare(length(o1, center), length(o2, center)) == 0 ? -1 : 1);
            try {

                List<String> interList = Files
                        .lines(Paths.get(file.getPath()), Charset.forName("ISO-8859-1"))
                        .collect(Collectors.toList());

                for (String s : interList) {

                    String[] interMass = s.split(" ");

                    newMap.put(new Point2D(Double.parseDouble(interMass[0]),
                                    Double.parseDouble(interMass[1]))
                            , Double.parseDouble(interMass[2]));
                }
            } catch (Exception e) {

                System.out.println("Error while reading file");
            }

            return newMap;
        }

    }
}