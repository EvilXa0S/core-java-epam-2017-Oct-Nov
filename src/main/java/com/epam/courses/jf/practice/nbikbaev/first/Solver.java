package com.epam.courses.jf.practice.nbikbaev.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver implements ISolver {

    @Override
    public void task1() {
        int n;
        String maxString = null;
        String minString = null;
        int minLength = 0;
        int maxLength = 0;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String strLine = in.readLine();
            n = Integer.valueOf(strLine);
            strLine = in.readLine();
            maxString = strLine;
            minString = strLine;
            for (int i = 0; i < n - 1; i++) {
                strLine = in.readLine();
                if (strLine.length() >= maxString.length()) {
                    maxString = strLine;
                }
                if (strLine.length() <= minString.length()) {
                    minString = strLine;
                }
            }
            minLength = minString.length();
            maxLength = maxString.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }

    @Override
    public void task2() {
        int n;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String strLine = in.readLine();
            n = Integer.valueOf(strLine);
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                strings.add(in.readLine());
            }
            strings.sort((o1, o2) -> {
                int res = Integer.compare(o1.length(), o2.length());
                if (res == 0) {
                    return o1.compareTo(o2);
                }
                return res;
            });
            for (String s : strings) {
                System.out.printf("(%d): \"%s\"%n", s.length(), s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void task3() {
        int n;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String strLine = in.readLine();
            n = Integer.valueOf(strLine);
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                strings.add(in.readLine());
            }
            strings.sort(Comparator.comparingInt(String::length));
            int totalLength = 0;
            for (String s : strings) {
                totalLength += s.length();
            }
            int average = totalLength / n;
            System.out.printf("AVERAGE (%d)%n", average);
            for (String s : strings) {
                if (s.length() < average) {
                    System.out.printf("(%d): \"%s\"%n", s.length(), s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void task4() {
        int n;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String strLine = in.readLine();
            n = Integer.valueOf(strLine);
            int min = Integer.MAX_VALUE;
            String minWord = null;
            strLine = in.readLine();
            String[] words = strLine.trim().split(" ");
            Set<Character> uniqKeys = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < words[i].length(); k++) {
                    uniqKeys.add(words[i].charAt(k));
                }
                if (uniqKeys.size() < min) {
                    min = uniqKeys.size();
                    minWord = words[i];
                }
                uniqKeys.clear();
            }
            System.out.println(minWord);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void task5() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String sentence = in.readLine();
            Pattern vowelPattern = Pattern.compile("[AEIOUaeiou]");
            int k = 0;
            for (String word : sentence.trim().split(" ")) {
                if (word.matches("\\w+")) {
                    int vowelCount = 0;
                    Matcher vowelMatcher = vowelPattern.matcher(word);
                    while (vowelMatcher.find()) {
                        vowelCount++;
                    }
                    if (vowelCount * 2 == word.length()) {
                        k++;
                    }
                }
            }
            System.out.println(k);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void task6() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int k = 0;
            String result = null;
            int n = Integer.valueOf(in.readLine());
            String sentence = in.readLine();
            for (String word : sentence.trim().split(" ")) {
                if (word.length() == 1) {
                    continue;
                }
                char[] letters = word.toCharArray();
                Arrays.sort(letters);
                String sortedWord = new String(letters);
                if (sortedWord.equals(word)) {
                    k++;
                    result = word;
                    if (k == 1) {
                        break;
                    }
                }
            }
            if (result != null) {
                System.out.println(result);
            }else {
                System.out.println("NOT FOUND");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void task7() {

    }

    @Override
    public void task8() {

    }

    @Override
    public void task9() {

    }

    @Override
    public void task10() {

    }

    @Override
    public void task11() {

    }

    @Override
    public void task12() {

    }

    @Override
    public void task13() {

    }

    @Override
    public void task14() {

    }

    @Override
    public void task15() {

    }

    @Override
    public void task16() {

    }

    @Override
    public void task17() {

    }

    @Override
    public void task18() {

    }

    @Override
    public void task19() {

    }

    @Override
    public void task20() {

    }

    @Override
    public void task21() {

    }

    @Override
    public void task22() {

    }

    @Override
    public void task23() {

    }

    @Override
    public void task24() {

    }

    @Override
    public void task25() {

    }

    @Override
    public void task26() {

    }

    @Override
    public void task27() {

    }
}
