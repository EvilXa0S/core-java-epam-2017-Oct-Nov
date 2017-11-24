package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Task9 implements ITestableTask9{
    @Override
    public HashSet<String> getUniqueWords(File input) {
        HashMap<String, String> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        try {
            list = Files.readAllLines(Paths.get(input.toURI()), Charset.defaultCharset());
        } catch (IOException e) {}

        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            String s = it.next();
            map.put(s.toLowerCase(), s);
        }

        return new HashSet<String>(map.values());
    }
}
