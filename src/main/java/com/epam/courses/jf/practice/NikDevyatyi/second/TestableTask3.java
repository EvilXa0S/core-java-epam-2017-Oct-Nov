package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.*;

public class TestableTask3 implements ITestableTask3 {

    public TestableTask3(){
    }
    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        List<String> result = new ArrayList();
        for(IPoem poem : poems){
            if(poem.getAuthor().equals(author)){
                result.addAll(poem.getLines());
            }
        }
        Collections.sort(result,Comparator.comparingInt(String::length));
        return result;
    }


}
