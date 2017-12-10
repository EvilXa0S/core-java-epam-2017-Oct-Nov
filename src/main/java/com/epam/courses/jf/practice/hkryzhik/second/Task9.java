package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Task9 implements ITestableTask9 {
    @Override
    public HashSet<String> getUniqueWords(File input) {

        List<String> checkingWords = new ArrayList<>();

        HashSet<String> resultSet = new HashSet<>();

        if(input.canRead() && input.exists()){
            try {
                checkingWords = Files.lines(Paths.get(input.getPath())).collect(Collectors.toList());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        checkingWords.forEach(e -> resultSet.add(e.toLowerCase()));

        return resultSet;
    }
}
