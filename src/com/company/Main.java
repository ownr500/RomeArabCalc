package com.company;

import java.util.*;
import java.util.regex.Pattern;

import static com.company.Main.Operations.*;

public class Main {

    class Operations {
        final static String addition = "+"; // сложение
        final static String subtraction = "-"; // вычитание
        final static String multiplication = "*"; // умножение
        final static String division = "/"; // целочисленное деление
        final static String remainding = "%"; // остаток от деления
//        final static String exponentiation = "^"; // возведение в степень
    }

    static final Map<String, Integer> romanSourceMap = new LinkedHashMap<String, Integer>() {{ // source (adding order is important here, so using LinkedHashMap)
        put("M",1000);
        put("CM",900);
        put("D", 500);
        put("CD", 400);
        put("C", 100);
        put("XC", 90);
        put("L", 50);
        put("XL", 40);
        put("X", 10);
        put("IX", 9);
        put("V", 5);
        put("IV", 4);
        put("I", 1);
    }};

    static Map<String, Integer> rimToIntMap = new HashMap<String, Integer>() {{ // used
        put("N",0);
//        put("I",1);
//        put("II",2);
//        put("III",3);
//        put("IV",4);
//        put("V",5);
//        put("VI",6);
//        put("VII",7);
//        put("VIII",8);
//        put("IX",9);
//        put("X",10);
//        put("XI",11);
        for (int x=1; x <= 10000; ++x) {
            put(convertToRoman(x), x);
        }
    }};

    static HashMap<Integer, String> intToRimMap = new HashMap<Integer, String>(); // used
    static {
        for (String key : rimToIntMap.keySet()) {
            intToRimMap.put(rimToIntMap.get(key), key);
        }
    }

    static String convertToRoman(int num) {
        String str = "";
        for (Map.Entry<String, Integer> entry : romanSourceMap.entrySet()) {
            String i = entry.getKey();
            int romanI = entry.getValue();
            int q = (int) (Math.floor(num / romanI));
            num -= q * romanI;
            str +=  String.join("", Collections.nCopies(q, i)); // new String(new char[q]).replace("\0", i); // i repeat q times ///i.repeat(q)
        }
        return str;
    }


    static String[] splitByFirstOperation (String expression) { // например "5+6"
        String orPattern = "["+Pattern.quote(addition)+Pattern.quote(subtraction)+Pattern.quote(multiplication)+Pattern.quote(division)+"]";
        expression = expression.trim().replaceAll(orPattern, " $0 "); // surround operation with spaces

        String[] result = expression.split(" +"); // разбиваем по operation: + - * /
        if (result == null || result.length != 3) { // по условию допустима только одна операция для двух чисел
            return null;
        } else {
            return new String[] {result[0], result[2], result[1].trim()}; // например ["5", "6", "+"]
        }
    }


    static String toRim(int x) {
        return intToRimMap.get(x);
    }

    static int fromRim(String a) {
        a = a.toUpperCase();
        a = a.replaceAll("М", "M"); // кириллица (исправление опечатки)
        a = a.replaceAll("Л", "L"); // кириллица (исправление опечатки)
        a = a.replaceAll("Д", "D"); // кириллица (исправление опечатки)
        a = a.replaceAll("Н", "N"); // кириллица (исправление опечатки)
        a = a.replaceAll("С", "C"); // кириллица (исправление опечатки)
        a = a.replaceAll("Х", "X"); // кириллица (исправление опечатки)
        a = a.replaceAll("В", "V"); // кириллица (исправление опечатки)
        a = a.replaceAll("П", "V"); // кириллица (исправление опечатки)
        a = a.replaceAll("І", "I"); // дорев. кириллица (исправление опечатки)
        a = a.replaceAll("Ї", "I"); // укр. кириллица (исправление опечатки)
        a = a.replaceAll("Ï", "I"); // фр. латиница (исправление опечатки)
        return rimToIntMap.get(a);
    }

    static String toArab(int x) {
        return Integer.toString(x);
    }

    static int fromArab(String a) {
        int x = Integer.parseInt(a);
        return x;
    }

    public static String calc(String input) {
        String[] lr = splitByFirstOperation(input);

        String a = lr[0];
        String b = lr[1];
        final String operation = lr[2];

        int x = 0;
        int y = 0;

        boolean isArab = true;
        try {
            x = fromArab(a);
        } catch (Exception e) {
            x = fromRim(a);
            isArab = false;
        }
        if (isArab) {
            y = fromArab(b);
        } else {
            y = fromRim(b);
        }

        String zStr = null;
        if (x<0 || x>100 || y<0 || y>100) { // incorrect input
        } else {
            int z;
            switch (operation) {
                case addition:
                    z = x + y;
                    break;
                case subtraction:
                    z = x - y;
                    break;
                case multiplication:
                    z = x * y;
                    break;
                case division:
                    z = x / y;
                    break;
                case remainding:
                    z = x % y;
                    break;
                default:
                    z = x / 0; // exception for unsupported operation
            }

            if (isArab) {
                zStr = toArab(z);
            } else {
                zStr = toRim(z);
            }
        }
        return zStr.toString();  // toString for throws exception in case of wrong null
    }

    public static void main(String[] args) {
        String input = String.join(" ", args);
        System.out.println("Input: " + input);
        System.out.print("Output: ");
        try {
            System.out.println("" + calc(input));
        } catch (Exception e) {
            System.out.println();
            System.err.println("Exception: ");
            e.printStackTrace();
        }
    }

}
