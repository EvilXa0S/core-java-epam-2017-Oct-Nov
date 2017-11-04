package com.epam.courses.jf.practice.common.first;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.sqrt;

/**
 * Интерфейс первого задания.
 * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Задание%20№1">Описание задания</a>}
 */
public interface ISolver {

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.1">Задание №1</a>}
     */
    default void task1() {
        Scanner in = new Scanner(System.in);
        int numOfStrings=in.nextInt()-1;
        in.nextLine();
        String min=new String(in.nextLine());
        String max=min;
        String tmp=null;

        while((numOfStrings)!=0){
            tmp=in.nextLine();
            if(tmp.length()<=min.length())min=tmp;
            else if(tmp.length()>=max.length())max=tmp;
            numOfStrings--;
        }

        System.out.printf("MIN (%d): \"%s\"%n", min.length(), min);
        System.out.printf("MAX (%d): \"%s\"%n", max.length(), max);
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.2">Задание №2</a>}
     */
    default void task2() {
        Comparator<String> comparator = (String o1, String o2) -> {
            if(o1.length()<o2.length()) return -1;
            else if (o1.length()>o2.length()) return 1;
            else
                return o1.compareTo(o2);
        };
        ArrayList<String> s=new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int numOfStrings=in.nextInt();
        in.nextLine();

        while((numOfStrings)!=0){
            s.add(in.nextLine());

            numOfStrings--;
        }
        Collections.sort(s,comparator);
        for(String x:s)
            System.out.println(x);

    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.3">Задание №3</a>}
     */
    default void task3() {
        Scanner in = new Scanner(System.in);
        int numOfStrings=in.nextInt();

        in.nextLine();
        ArrayList<String> s=new ArrayList<>(numOfStrings);
        int avg=0,tmp=numOfStrings;
        String buff;

            while((tmp--)!=0){
            buff=in.nextLine();
            avg+=buff.length();
            s.add(buff);
        }
        avg/=numOfStrings;
        System.out.println(avg+"\n");
            for(String t : s){
                if(t.length()<avg)
                         System.out.println(t);
            }
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.4">Задание №4</a>}
     */
    default void task4() {

        Scanner in = new Scanner(System.in);
        int numOfWords=in.nextInt()-1;
        Set<Character> charSet=new HashSet<>();
        in.nextLine();
        String str=in.nextLine();
        StringBuffer minStr=new StringBuffer();
        int iter=0;
        int minimum=0;
        for(char tmp=str.charAt(iter);; iter++){
            if(tmp==' '){
                iter++;
                break;
            }
            minStr.append(tmp);
            charSet.add(tmp);
            tmp=str.charAt(iter+1);
        }
        minimum=charSet.size();
        for(;numOfWords!=0;numOfWords--){
            charSet.clear();
            StringBuffer potentialMinStr=new StringBuffer();

            for(char tmp;iter<str.length(); iter++){
                tmp=str.charAt(iter);

                if(tmp==' '){
                    iter++;
                    break;
                }
                potentialMinStr.append(tmp);
                charSet.add(tmp);
            }
            if(charSet.size()<minimum) {
                minimum = charSet.size();
                minStr = potentialMinStr;
            }
        }
        System.out.println(minStr);
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.5">Задание №5</a>}
     */
    default void task5() {

        ArrayList<Integer> gl=new ArrayList<>();
        gl.add(65);
        gl.add(69);
        gl.add(73);
        gl.add(79);
        gl.add(85);
        for(int i=0;i<5;i++)
            gl.add(gl.get(i)+32);
        int result=0;
        int delta=32;
        Pattern p = Pattern.compile("[\n ]");
        ArrayList<String> words=new ArrayList<>();
        String buf;
        Scanner in = new Scanner(System.in);
        int numOfWords=in.nextInt();
        Set<Character> charSet=new HashSet<>();
        in.nextLine();
        in.useDelimiter(p);
        while (numOfWords!=0){

        buf=in.next();
        if(buf.matches("[a-zA-Z]+")){
            int glasniye=0;
            int soglasniye=0;

            for(char i : buf.toCharArray()){
                int tmp=i;
                if(gl.contains(tmp))
                    glasniye++;
                else soglasniye++;
            }

            if(glasniye==soglasniye)
                result++;
            }
            numOfWords--;

        }
        System.out.println(result);

    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.6">Задание №6</a>}
     */
    default void task6() {
        int result=0;
        int delta=32;
        Pattern p = Pattern.compile("[\n ]");
        ArrayList<String> words=new ArrayList<>();
        String buf;
        Scanner in = new Scanner(System.in);
        int numOfWords=in.nextInt();

        in.nextLine();
        in.useDelimiter(p);
        while (numOfWords!=0) {
            buf=in.next();
            boolean fag=true;
            for(int i=1;i<buf.length();i++){
                if(buf.charAt(i)<buf.charAt(i-1)){
                    fag=false;
                    break;
                }

            }
            if(fag && buf.length()!=1){
                System.out.println(buf);
                return;
            }
        }
        System.out.println("NOT FOUND");
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.7">Задание №7</a>}
     */
    default void task7() {

        ArrayList<Integer> gl=new ArrayList<>();

        Pattern p = Pattern.compile("[\n ]");
        ArrayList<String> words=new ArrayList<>();
        ArrayList<String> resultat=new ArrayList<>();
        String buf;
        Scanner in = new Scanner(System.in);
        int numOfWords=in.nextInt();
        int result=0;
        Set<Character> charSet=new HashSet<>();
        in.nextLine();
        in.useDelimiter(p);
        while (numOfWords!=0){
            charSet.clear();
            buf=in.next();


                boolean flag=true;
                for(char i : buf.toCharArray()){

                    if(!charSet.add(i)){
                        flag=false;
                        break;
                    }

                }
                if(flag && !resultat.contains(buf))
                    resultat.add(buf);

            numOfWords--;

        }
        if(!resultat.isEmpty())
            for(String s : resultat)
                System.out.print(s+" ");
        else
            System.out.println("NOT FOUND");
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.8">Задание №8</a>}
     */
    default void task8() {

        ArrayList<Integer> gl=new ArrayList<>();
        Pattern p = Pattern.compile("[\n ]");
        ArrayList<String> words=new ArrayList<>();
        ArrayList<String> resultat=new ArrayList<>();
        String buf;
        Scanner in = new Scanner(System.in);
        int numOfWords=in.nextInt();
        int result=0;
        Set<Character> charSet=new HashSet<>();
        in.nextLine();
        in.useDelimiter(p);
        while (numOfWords!=0){

            buf=in.next();
            try {
                Integer.parseInt(buf);

            }
            catch (NumberFormatException e){
                numOfWords--;
                continue;
            }
            boolean flag=true;

            for(int i=0,j=buf.length()-1;i<=j;i++,j--){
                if(buf.charAt(i)!=buf.charAt(j)) {
                    flag = false;
                }
            }


            if(flag && !resultat.contains(buf))
                resultat.add(buf);

            numOfWords--;

        }
        if(resultat.isEmpty())
            System.out.println("NOT FOUND");
        else if(resultat.size()>1)
            System.out.println(resultat.get(1));
        else
            System.out.println(resultat.get(0));



    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.9">Задание №9</a>}
     */
    default void task9() {
        Scanner in = new Scanner(System.in);
        int a=in.nextInt();
        int i=1;
        for(int g=0;g<a;g++) {
            for (int j = 0; j < a; j++) {
                System.out.print(i + "\t");
                i++;
            }
            System.out.print('\n');
        }

    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.10">Задание №10</a>}
     */
    default void task10() {
        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        double d = b*b - 4*a*c;
        if (d < 0) {
            System.out.println("No solution");
            return;
        }
        if (d == 0) {
            double x = -b/2/a;
            System.out.println("One solution: "+x);
            return;
        }
        else {
            double x1 = (-b - sqrt(d))/2/a;
            double x2 = (-b + sqrt(d))/2/a;
            System.out.println("Two solutions: "+x1+", "+x2);
            return;
        }
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.11">Задание №11</a>}
     */
    default void task11() {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        switch (a){
            case 1:
                System.out.println("January");
                break;
            case 2:
                System.out.println("February");
                break;
            case 3:
                System.out.println("March");
                break;
            case 4:
                System.out.println("April");
                break;
            case 5:
                System.out.println("May");
                break;
            case 6:
                System.out.println("June");
                break;
            case 7:
                System.out.println("July");
                break;
            case 8:
                System.out.println("August");
                break;
            case 9:
                System.out.println("Septmber");
                break;
            case 10:
                System.out.println("October");
                break;
            case 11:
                System.out.println("November");
                break;
            case 12:
                System.out.println("December");
                break;
            default:
                System.out.println("INCORRECT INPUT DATA");
        }
        return;
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.12">Задание №12</a>}
     */
    default void task12() {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b=in.nextInt();
        int[][] res=new int[b][b];
        for(int i=0;i<b;i++){
            for(int j=0;j<b;j++)
                res[i][j]=in.nextInt();
        }

        int[]r=new int[b];
        for(int x=0;x<b;x++)
            r[x]=res[x][a];


        for(int y=0;y<b;y++) {
            int min = res[y][a];
            int minAd=y;
            for (int g = y + 1; g < b; g++) {
                if (res[g][a]< min) {
                    min=res[g][a];
                    minAd=g;
                };
            }

            int tmp[]=res[minAd];
            res[minAd]=res[y];
            res[y]=tmp;

            for(int x : res[y])
                System.out.print(x+"\t");

            System.out.print("\n");

    }

    }//hueta

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.13">Задание №13</a>}
     */
    default void task13()  { throw new UnsupportedOperationException("Task 13 not implemented yet."); }
    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.14">Задание №14</a>}
     */
    default void task14() { throw new UnsupportedOperationException("Task 14 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.15">Задание №15</a>}
     */
    default void task15() { throw new UnsupportedOperationException("Task 15 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.16">Задание №16</a>}
     */
    default void task16() { throw new UnsupportedOperationException("Task 16 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.17">Задание №17</a>}
     */
    default void task17() { throw new UnsupportedOperationException("Task 17 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.18">Задание №18</a>}
     */
    default void task18() { throw new UnsupportedOperationException("Task 18 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.19">Задание №19</a>}
     */
    default void task19() { throw new UnsupportedOperationException("Task 19 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.20">Задание №20</a>}
     */
    default void task20() { throw new UnsupportedOperationException("Task 20 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.21">Задание №21</a>}
     */
    default void task21() { throw new UnsupportedOperationException("Task 21 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.22">Задание №22</a>}
     */
    default void task22() { throw new UnsupportedOperationException("Task 22 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.23">Задание №23</a>}
     */
    default void task23() { throw new UnsupportedOperationException("Task 23 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.24">Задание №24</a>}
     */
    default void task24() { throw new UnsupportedOperationException("Task 24 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.25">Задание №25</a>}
     */
    default void task25() { throw new UnsupportedOperationException("Task 25 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.26">Задание №26</a>}
     */
    default void task26() { throw new UnsupportedOperationException("Task 26 not implemented yet."); }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.27">Задание №27</a>}
     */
    default void task27() { throw new UnsupportedOperationException("Task 27 not implemented yet."); }
}
