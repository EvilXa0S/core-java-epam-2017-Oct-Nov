package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TestableTask15Impl implements ITestableTask15 {
    private boolean isOnLine(I2DPoint lineFirst, I2DPoint lineSecond, I2DPoint point) {
        if (lineFirst.getX() == lineSecond.getX()) {
            return point.getX() == lineFirst.getX();
        }
        double k = (lineSecond.getY() - lineFirst.getY()) / (lineSecond.getX() - lineFirst.getX());
        double b = lineFirst.getY() - k * lineFirst.getX();
        return point.getY() == k * point.getX() + b;
    }

    private String formatLine(ILine line) {
        return line.getPoints()
                .stream()
                .map(p -> p.getX() + " " + p.getY())
                .collect(Collectors.joining(";"));
    }

    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        FileImpl ret = new FileImpl(output);

        for (I2DPoint first : points) {
            for (I2DPoint second : points) {
                if (first != second) {
                    Set<I2DPoint> set = new HashSet<>();
                    for (I2DPoint third : points) {
                        if (isOnLine(first, second, third)) {
                            set.add(third);
                        }
                    }
                    if (set.size() > 2) {
                        ret.addLine(new LineImpl(set));
                    }
                }
            }
        }

        try {
            ret.writeOut();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return ret;
    }

    private class LineImpl implements ILine {
        Set<I2DPoint> points;

        LineImpl() {
            points = new HashSet<>();
        }

        LineImpl(Set<I2DPoint> set) {
            points = set;
        }

        @Override
        public Set<I2DPoint> getPoints() {
            return points;
        }
    }

    private class FileImpl implements IFileWithLines {
        private Set<ILine> lines;
        private File file;

        FileImpl(File output) {
            lines = new HashSet<>();
            file = output;
        }

        void addLine(ILine line) {
            lines.add(line);
        }

        void writeOut() throws IOException {
            Files.write(file.toPath(), lines
                    .stream()
                    .map(TestableTask15Impl.this::formatLine)
                    .collect(Collectors.toList()));
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public Set<ILine> getLines() {
            return lines;
        }
    }
}
