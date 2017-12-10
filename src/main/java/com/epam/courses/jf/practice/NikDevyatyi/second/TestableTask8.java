package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestableTask8 implements ITestableTask8 {
    @Override
    public boolean isNormalBrackets(String string) {
        Stack<Character> temp = new Stack<Character>();
        StringBuilder result = new StringBuilder();
        Pattern MY_PATTERN = Pattern.compile("[{}\\[\\]()]");
        Matcher m = MY_PATTERN.matcher(string);
        while (m.find()) {
            String s = m.group(0);
            result.append(s);
        }
        for(char item: result.toString().toCharArray()){
            if(!temp.empty()){
                switch (item){
                    case (')'):
                        if(temp.peek() == '('){
                            temp.pop();
                        }
                        else {
                            return false;
                        }
                        break;
                    case (']'):
                        if(temp.peek() == '['){
                            temp.pop();
                        }
                        else {
                            return false;
                        }
                        break;
                    case ('}'):
                        if(temp.peek() == '{'){
                            temp.pop();
                        }
                        else {
                            return false;
                        }
                        break;
                    default:
                        temp.push(item);
                }
            }
            else {
                temp.push(item);
            }
        }
        if(temp.empty()){
            return true;
        }
        return false;
    }
}
