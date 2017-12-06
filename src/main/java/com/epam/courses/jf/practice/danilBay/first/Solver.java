package com.epam.courses.jf.practice.danilBay.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.sqrt;

public class Solver implements ISolver {



    public void task1() {
        Scanner in = new Scanner(System.in);
        int numOfStrings=in.nextInt()-1;
        in.nextLine();
        String min=new String(in.nextLine());
        String max=min;
        String tmp=null;

        while((numOfStrings)!=0){
            tmp=in.nextLine();
            if(tmp.length()<=min.length())min=tmp;
            if(tmp.length()>=max.length())max=tmp;
            numOfStrings--;
        }

        System.out.printf("MIN (%d): \"%s\"%n", min.length(), min);
        System.out.printf("MAX (%d): \"%s\"%n", max.length(), max);
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.2">Задание №2</a>}
     */
    public void task2() {
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
            System.out.printf("(%d): \"%s\"%n", x.length(), x);

    }


    public void task3() {
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
        System.out.printf("AVERAGE (%d)%n", avg);
        for(String t : s){
            if(t.length()<avg)
                System.out.printf("(%d): \"%s\"%n", t.length(), t);

        }
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.4">Задание №4</a>}
     */
    public void task4() {

        Scanner in = new Scanner(System.in);
        int numOfWords=in.nextInt()-1;
        Set<Character> charSet=new HashSet<>();
        in.nextLine();
        String str=in.nextLine();
        StringBuffer minStr=new StringBuffer();
        int iter=0;
        int minimum=0;
        for(char tmp=str.charAt(iter);iter<str.length(); iter++){
            tmp=str.charAt(iter);
            if(tmp==' '|| tmp=='\n'){
                iter++;
                break;
            }
            minStr.append(tmp);
            charSet.add(tmp);

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
    public void task5() {

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
    public void task6() {
        Scanner scanner = new Scanner(System.in);
        int words =scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < words; i++) {
            char[] word = scanner.next().toCharArray();

            for (int j = 0; j < word.length - 1; j++) {
                if (word[j] >= word[j + 1]) break;
                if (j==word.length-2) {
                    System.out.println(new String(word));
                    return;
                }
            }
        }
        System.out.println("NOT FOUND");
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.7">Задание №7</a>}
     */
    public void task7() {

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
        if(!resultat.isEmpty()) {
            for (int i=0;i<resultat.size();i++) {
                System.out.print(resultat.get(i));
                if(i!=(resultat.size()-1))
                    System.out.print(" ");
            }
            System.out.println();
        }
        else
            System.out.println("NOT FOUND");
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.8">Задание №8</a>}
     */
    public void task8() {

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


            if(flag)
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
    public void task9() {
        Scanner in = new Scanner(System.in);
        int a=in.nextInt();
        int i=1;
        for(int g=0;g<a;g++) {
            for (int j = 0; j < (a-1); j++) {
                System.out.print(i + "\t");
                i++;
            }
            System.out.print(i++);
            System.out.print('\n');
        }

    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.10">Задание №10</a>}
     */
    public void task10() {
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

            BigDecimal bd=new BigDecimal(x);
            BigDecimal normalizedField = bd.setScale(2, RoundingMode.HALF_UP);
            System.out.println("One solution: "+normalizedField);
            return;
        }
        else {
            double x1 = (-b - sqrt(d))/2/a;
            double x2 = (-b + sqrt(d))/2/a;
            BigDecimal bd1=new BigDecimal(x1);
            BigDecimal bd2=new BigDecimal(x2);

            BigDecimal normalizedField1 = bd1.setScale(2, RoundingMode.HALF_UP);
            BigDecimal normalizedField2 = bd2.setScale(2, RoundingMode.HALF_UP);
            System.out.println("Two solutions: "+normalizedField1+", "+normalizedField2);
            return;
        }
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.11">Задание №11</a>}
     */
    public void task11() {
        Scanner in = new Scanner(System.in);
        String temp=in.nextLine();
        int a;
        try {
            a = Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            System.out.println("INCORRECT INPUT DATA");
            return;
        }

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
    public void task12() {
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

        System.out.println(b);
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
                System.out.print(x+"    ");

            System.out.println();

        }

    }//hueta

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.13">Задание №13</a>}
     */
    public void task13() {

        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int[][] res = new int[b][b];
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < b; j++)
                res[i][j] = in.nextInt();
        }
        System.out.println(b);

        if(a>=0)
            for (int x = 0; x < b; x++) {
                for (int y = 0; y < b; y++)
                    System.out.print(res[((a + x) % b)][y]+" ");

                System.out.println();
            }
        else
            for (int x = 0; x <b; x++) {
                for (int y = 0; y < b; y++)
                    System.out.print(res[((-a + x) % b)][y]+"    ");

                System.out.println();
            }

    }
    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.14">Задание №14</a>}
     */
    public void task14() {
        int velocity;
        Scanner in = new Scanner(System.in);
        int a = in.nextInt()-1;

        int maxPosl=1;
        int maxPosTemp=1;

        int first=in.nextInt();
        int second;

        if(a==0)
            maxPosl=0;

        while(a!=0){
            second=in.nextInt();
            if(second>first)
                maxPosTemp++;
            else
                if(maxPosTemp>maxPosl){
                maxPosl=maxPosTemp;
                maxPosTemp = 1;
            }
            first=second;
            a--;
        }
       if(maxPosl==1){
            maxPosl=0;
       }
        System.out.println(maxPosl);
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.15">Задание №15</a>}
     */
    public void task15() {
        int velocity;
        Scanner in = new Scanner(System.in);
        // int a = in.nextInt();
        int b = in.nextInt();
        int[][] res = new int[b][b];
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < b; j++)
                res[i][j] = in.nextInt();
        }
        int result=0;
        for(int i=0;i<b;i++)
            for(int j=0;j<b;j++){
                if(res[i][j]>0) {
                    while ((++j) < b && res[i][j] <= 0)
                        result += res[i][j];
                    break;
                }
            }
        System.out.println(result);
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.16">Задание №16</a>}
     */
    public void task16() {
        int velocity;
        Scanner in = new Scanner(System.in);
        // int a = in.nextInt();
        int b = in.nextInt();
        int[][] res = new int[b][b];
        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                res[i][j] = in.nextInt();

        int result[][]=new int[b][b];
        for(int j=0,x=0;j<b;j++,x++)
            for(int i=b-1,y=0;i>-1;i--,y++)
                result[i][j]=res[x][y];
        System.out.println(b);

        for (int i = 0; i < b; i++) {
            for (int j = 0; j < b; j++)
                System.out.print(result[i][j] + "    ");
            System.out.println();
        }
    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.17">Задание №17</a>}
     */
    public void task17() {
        class Determ{
            public  double matrixDeterminant (double[][] matrix) {
                double temporary[][];
                double result = 0;

                if (matrix.length == 1) {
                    result = matrix[0][0];
                    return (result);
                }

                if (matrix.length == 2) {
                    result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
                    return (result);
                }

                for (int i = 0; i < matrix[0].length; i++) {
                    temporary = new double[matrix.length - 1][matrix[0].length - 1];

                    for (int j = 1; j < matrix.length; j++) {
                        for (int k = 0; k < matrix[0].length; k++) {
                            if (k < i) {
                                temporary[j - 1][k] = matrix[j][k];
                            } else if (k > i) {
                                temporary[j - 1][k - 1] = matrix[j][k];
                            }
                        }
                    }

                    result += matrix[0][i] * Math.pow (-1, (double) i) * matrixDeterminant (temporary);
                }
                return (result);
            }
        }
        Determ determ=new Determ();
        Scanner in = new Scanner(System.in);
        // int a = in.nextInt();
        int b = in.nextInt();
        double[][] res = new double[b][b];
        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                res[i][j] = in.nextDouble();

        System.out.println((int)determ.matrixDeterminant(res));


    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.18">Задание №18</a>}
     */
    public void task18() {
        Scanner in = new Scanner(System.in);
        int b = in.nextInt();
        int[][] res = new int[b][b];
        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                res[i][j] = in.nextInt();
        Set<Integer> rows=new HashSet<>();
        Set<Integer> columns=new HashSet<>();

        int max=res[0][0];
        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                if(max<res[i][j])
                    max=res[i][j];

        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                if(max==res[i][j]){
                    rows.add(i);
                    columns.add(j);
                }
        int row=0,col=0;
        boolean flag = true;
        StringBuilder s=new StringBuilder();
        for (int i = 0; i < b; i++) {
            if (rows.contains(i))
                continue;
                row++;
                for (int j =  0; j < b; j++) {
                    if (columns.contains(j))
                        continue;
                  //  if(flag)
                        col++;
                    s.append(res[i][j] + "    ");

                }
                s.append("\n");


        }
        col/=row;

        System.out.println(row);
        System.out.println(col);
        System.out.print(s);


    }

    /**
     * {@see <a href="https://bitbucket.org/elefus/spring-java-school-epam-c-2017/wiki/Task%201.19">Задание №19</a>}
     */
    public void task19() {
        Scanner in = new Scanner(System.in);
        int b = in.nextInt();
        int[][] res = new int[b][b];
        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                res[i][j] = in.nextInt();
        Set<Integer> rows=new HashSet<>();
        Set<Integer> columns=new HashSet<>();
        boolean flag=true;
        for (int i = 0; i < b; i++){
            for (int j = 0; j < b; j++)
                if (res[i][j] != 0) {
                    flag = false;
                    break;
                }
            if(flag) {
                rows.add(i);

            }
            else
                flag=true;
        }

        for (int j = 0;j < b; j++){
            for (int i = 0; i < b; i++)
                if (res[i][j] != 0) {
                    flag = false;
                    break;
                }
                else
                    flag=true;
            if(flag) {
                columns.add(j);

            }
        }
        int col=0,row=0;
        StringBuilder s=new StringBuilder();
        for (int i = 0; i < b; i++) {
            if(rows.contains(i))
                continue;
            row++;
            for (int j = 0; j < b; j++) {
                if(columns.contains(j))
                    continue;
                col++;
                s.append(res[i][j]+"    ");

            }
            s.append("\n");
        }
        col/=row;
        System.out.println(row);
        System.out.println(col);
        System.out.print(s);

    }

    public void task20(){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();

        int b = in.nextInt();
        int[][] res = new int[b][b];
        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                res[i][j] = in.nextInt();

        int minimum=res[0][0];
        int min_row=0;
        int min_col=0;

        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                if(res[i][j]<minimum){
                    minimum=res[i][j];
                    min_col=j;
                    min_row=i;
                }
        int iterI,iterJ;
        System.out.println(b);
        for (int i = 0; i < b; i++) {
            if(i==min_row)
                iterI=row;
            else if(i==row)
                iterI=min_row;
            else
                iterI=i;
            for (int j = 0; j < b; j++) {
                if(j==min_col)
                    iterJ=col;
                else if(j==col)
                    iterJ=min_col;
                else
                    iterJ=j;

                System.out.print(res[iterI][iterJ]+"    ");
            }
            System.out.println();
        }
    }
    public void task21(){
        Scanner in = new Scanner(System.in);



        int b = in.nextInt();
        int[][] res = new int[b][b];
        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                res[i][j] = in.nextInt();

        for(int i=0;i<b;i++){

            for(int j=0;j<b;j++)
                for(int k=j+1;k<b;k++){
                    if(res[i][j]==0 && res[i][k]!=0){
                        res[i][j]=res[i][k];
                        res[i][k]=0;
                       // j++;k++;
                    }

                }

        }
        System.out.println(b);
        for (int i = 0; i < b; i++){
            for (int j = 0; j < b; j++)
                System.out.print(res[i][j]+"    ");

            System.out.println();
            }

    }

    public void task22(){
        Scanner in = new Scanner(System.in);
        int b = in.nextInt();
        in.nextLine();
        int[][] res = new int[b][b];
        for (int i = 0; i < b; i++) {

            for (int j = 0; j < b; j++) {
                  res[i][j] = (int) Math.round(in.nextDouble());

            }
        }
        System.out.println(b);
        for (int i = 0; i < b; i++){
            for (int j = 0; j < b; j++)
                System.out.print(res[i][j]+"    ");

            System.out.println();
        }
    }
    public void task23(){
        Scanner in = new Scanner(System.in);
        Set<Integer> rows=new HashSet<>();
        Set<Integer> cols=new HashSet<>();

        int b = in.nextInt();
        int[][] res = new int[b][b];
        for (int i = 0; i < b; i++)
            for (int j = 0; j < b; j++)
                res[i][j] = in.nextInt();

        int counter=0;//результат. количество седловых точек
        for(int i=0;i<b;i++) {

            if(!rows.contains(i))
            for (int j = 0; j < b; j++) {
                if (!cols.contains(j)){
                    boolean flag=true;
                    for(int n=0;n<b;n++){
                        if(res[i][j]>res[i][n]){
                            flag=false;
                            break;
                        }
                    }
                    if(flag)
                    for(int n=0;n<b;n++){
                        if(res[i][j]<res[n][j]){
                            flag=false;
                            break;
                        }
                    }
                    if(flag) {
                        rows.add(i);
                        cols.add(j);
                        counter++;
                        break;
                    }
                }

            }

        }
        System.out.println(counter);
    }
    public void task24(){
        Scanner in = new Scanner(System.in);
        LinkedList<Integer> conseq=new LinkedList<>();

        int b = in.nextInt();
        int[][] res = new int[b][b];
        int sum1;
        for (int i = 0; i < b; i++) {
            sum1=0;
            for (int j = 0; j < b; j++) {
                res[i][j] = in.nextInt();
                sum1+=res[i][j];
            }
            if(conseq.isEmpty()) {
                conseq.add(0, i);
                continue;
            }
            for(int k=0;k<conseq.size();k++){
                int sum2=0;
                for(int z=0;z<b;z++) {

                    sum2 += res[conseq.get(k)][z];
                }
                if(sum1<sum2){
                    conseq.add(k,i);
                    break;
                }
                else if((k+1)==conseq.size()) {
                    conseq.add(i);
                    break;
                }

            }
        }

        System.out.println(b);
        for(int x : conseq) {
            for (int i = 0; i < (b-1); i++) {
                System.out.print(res[x][i] + "    ");
            }
            System.out.print(res[x][b-1]);
            System.out.println();
        }

    }


    public void task25() {
        Scanner scanner= new Scanner(System.in);
        int b = scanner.nextInt();
        boolean s1,s2,s3,s4,s5,s6,s7,s8;

        int matrix[][]=new int[b][b];
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < b; j++) {
                matrix[i][j] = scanner.nextInt();

            }
        }
        int localMin = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                boolean leftY = (j - 1) >= 0;
                boolean rightY = (j + 1) < matrix.length;
                boolean toptX = (i - 1) >= 0;
                boolean bottomX = (i + 1) < matrix.length;

                s1 = true;
                s2 = true;
                s3 = true;
                s4 = true;
                s5 = true;
                s6 = true;
                s7 = true;
                s8 = true;

                if (toptX && leftY)
                    s1 = matrix[i][j] < matrix[i - 1][j - 1];

                if (toptX)
                    s2 = matrix[i][j] < matrix[i - 1][j];

                if (toptX && rightY)
                    s3 = matrix[i][j] < matrix[i - 1][j + 1];

                if (rightY)
                    s4 = matrix[i][j] < matrix[i][j + 1];

                if (bottomX && rightY)
                    s5 = matrix[i][j] < matrix[i + 1][j + 1];

                if (bottomX)
                    s6 = matrix[i][j] < matrix[i + 1][j];

                if (bottomX && leftY)
                    s7 = matrix[i][j] < matrix[i + 1][j - 1];

                if (leftY)
                    s8 = matrix[i][j] < matrix[i][j - 1];

                if (s1 && s2 && s3 && s4 && s5 && s6 && s7 && s8)
                    localMin++;

            }
        }

        System.out.println(localMin);
    }
    public void task26(){
        {
            Scanner scanner= new Scanner(System.in);
            int b = scanner.nextInt();
            boolean s1,s2,s3,s4,s5,s6,s7,s8;

            int matrix[][]=new int[b][b];
            for (int i = 0; i < b; i++) {
                for (int j = 0; j < b; j++) {
                    matrix[i][j] = scanner.nextInt();

                }
            }
            int localMin = 0;
            ArrayList<Integer>localMaxima=new ArrayList<>();

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {

                    boolean leftY = (j - 1) >= 0;
                    boolean rightY = (j + 1) < matrix.length;
                    boolean toptX = (i - 1) >= 0;
                    boolean bottomX = (i + 1) < matrix.length;

                    s1 = true;
                    s2 = true;
                    s3 = true;
                    s4 = true;
                    s5 = true;
                    s6 = true;
                    s7 = true;
                    s8 = true;

                    if (toptX && leftY)
                        s1 = matrix[i][j] < matrix[i - 1][j - 1];

                    if (toptX)
                        s2 = matrix[i][j] < matrix[i - 1][j];

                    if (toptX && rightY)
                        s3 = matrix[i][j] < matrix[i - 1][j + 1];

                    if (rightY)
                        s4 = matrix[i][j] < matrix[i][j + 1];

                    if (bottomX && rightY)
                        s5 = matrix[i][j] < matrix[i + 1][j + 1];

                    if (bottomX)
                        s6 = matrix[i][j] < matrix[i + 1][j];

                    if (bottomX && leftY)
                        s7 = matrix[i][j] < matrix[i + 1][j - 1];

                    if (leftY)
                        s8 = matrix[i][j] < matrix[i][j - 1];

                    if (s1 && s2 && s3 && s4 && s5 && s6 && s7 && s8){
                        localMaxima.add(matrix[i][j]);
                    }

                }
            }
            if(localMaxima.size()!=0) {
                Arrays.sort(localMaxima.toArray());
                System.out.println(localMaxima.get(localMaxima.size() - 1));
            }
            else System.out.println("NOT FOUND");
        }
    }
    public void task27(){
        Scanner in = new Scanner(System.in);
        LinkedList<Integer> conseq=new LinkedList<>();

        int b = in.nextInt();
        int[][] res = new int[b][b];
        int sum1,sum2;
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < b; j++) {
                res[i][j] = in.nextInt();

            }
        }
        for(int j=0;j<b;j++){

            sum1=0;
            for(int i=0;i<b;i++) {
                sum1 += abs(res[i][j]);
            }
            if(conseq.isEmpty()) {
                conseq.add(j);
                continue;
            }
            for(int k=0;k<conseq.size();k++){
                sum2=0;
                for(int z=0;z<b;z++) {

                    sum2 += abs(res[z][conseq.get(k)]);
                }
                if(sum1>sum2){
                    conseq.add(k,j);
                    break;
                }
                else if(sum1==sum2){
                    conseq.add(k+1,j);
                    break;
                }
                else if((k+1)==conseq.size()) {
                    conseq.add(j);
                    break;
                }

            }
        }

        System.out.println(b);
        for(int i = 0; i < b; i++) {
            for (int x:conseq) {
                System.out.print(res[i][x] + "    ");
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {

        Solver m= new Solver();
        m.task25();
    }
}
