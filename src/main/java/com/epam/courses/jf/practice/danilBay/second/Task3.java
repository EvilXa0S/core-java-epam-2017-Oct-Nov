package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Task3 implements ITestableTask3{
    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        List<String> result=new LinkedList<>();
        for(IPoem x : poems)
            if(x.getAuthor().equals(author))
                for(int i=0;i<x.getLines().size();i++){
                    if(result.isEmpty()){
                        result.add(x.getLines().get(i));
                        continue;
                    }
                    else for(int g=0;g<result.size();g++){
                        if(x.getLines().get(i).length()<result.get(g).length()){
                            result.add(g,x.getLines().get(i));
                            break;
                        }
                        else if(g==result.size()-1){
                            result.add(x.getLines().get(i));
                            break;
                        }
                    }

                }

        return result;
    }
}
