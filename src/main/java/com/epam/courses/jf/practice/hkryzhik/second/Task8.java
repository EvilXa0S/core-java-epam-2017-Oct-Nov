package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Task8 implements ITestableTask8{
    @Override
    public boolean isNormalBrackets(String string) {

        Character[] checkingCharacterArray = string.chars().mapToObj(c ->(char)c).toArray(Character[]::new);

        Stack<Character> characterStack = new Stack<>();

        if(string.isEmpty()){
            return true;
        }

        List<Character> openingCases = new ArrayList<>(Arrays.asList('(', '[', '{'));

        List<Character> closingCases = new ArrayList<>(Arrays.asList(')', ']', '}'));

        for (int i = 0; i < checkingCharacterArray.length; i++) {

            if(closingCases.contains(checkingCharacterArray[i]) && characterStack.empty()){

                return false;

            }else if(openingCases.contains(checkingCharacterArray[i])){

                characterStack.push(checkingCharacterArray[i]);

            }else if(!characterStack.empty() && closingCases.indexOf(checkingCharacterArray[i]) ==
                    openingCases.indexOf(characterStack.peek())){

                characterStack.pop();

            }else if(characterStack.empty() && openingCases.contains(checkingCharacterArray[i])){

                characterStack.push(checkingCharacterArray[i]);
            }

            if(i == checkingCharacterArray.length - 1 && !characterStack.empty()){

                return false;
            }

        }
        return true;
    }
}
