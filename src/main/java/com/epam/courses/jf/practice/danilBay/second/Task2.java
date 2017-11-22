package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Task2 implements ITestableTask2 {
    static private Set<File>fileSet = new HashSet<>();
    static private File[]deepDarkFantasy(File[] fileArrray){

            for (File x: fileArrray) {
                if(!(x.listFiles() ==null)){
                    fileSet.add(x);
                    deepDarkFantasy(x.listFiles());
                }
                else {
                    fileSet.add(x);
                }
            }
        return null;
    }
    @Override
    public Set<File> getFiles(File directory) {
        File[] start={directory};
        deepDarkFantasy(start);
        return fileSet;
    }

    public static void main(String[] args) {
        Task2 m=new Task2();
        m.getFiles(new File("C:\\xampp\\htdocs\\jobPhp\\adminV"));
    }
}
