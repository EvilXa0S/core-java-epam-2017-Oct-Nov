package com.epam.courses.jf.practice.mallayarov.second;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;


/**
 * This visitor writes the visited files to set
 */
public class MyFileVisitor extends SimpleFileVisitor<Path> {

    /**
     * Set contains files that FileVisitor travelled
     */
    private Set<File> result;

    /**
     * Root of the files that visitor need to travel
     */
    private Path root;

    public MyFileVisitor(Set<File> result, Path root) {
        this.result = result;
        this.root = root;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        result.add(file.toFile());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException { // or use previsit
        if (dir != root) { // do not count root directory
            result.add(dir.toFile());
        }

        return FileVisitResult.CONTINUE;
    }
}