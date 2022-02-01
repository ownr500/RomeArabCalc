package com.company;
/**
 * RomeArabCalc
 * @license MIT
 */

import java.util.*;
import java.util.regex.Pattern;

import static com.company.Main.Operations.*;

public class Main {

    class Operations {
        final static String addition = "+"; // сложение
        final static String subtraction = "-"; // вычитание
        final static String exponentiation = "^"; // возведение левого числа в степень (справа)
        final static String multiplication = "*"; // умножение
        final static String division = "/"; // целочисленное деление
        final static String remainding = "%"; // остаток от деления
        final static String logarithm = "b"; // логарифм левого числа по основанию (справа)
    }

    static final int floatQuality = 3; // число знаков после запятой для float

    static final boolean allowMoreForce = true; ///

    static final Map<String, Integer> romanSourceMap = new LinkedHashMap<String, Integer>() {{ // source (adding order is important here, so using LinkedHashMap)
        put("M̅", 1000000);
        put("C̅M̅", 900000);
        put("D̅",  500000);
        put("C̅D̅", 400000);
        put("C̅",  100000);
        put("X̅C̅",  90000);
        put("L̅",   50000);
        put("X̅L̅",  40000);
        put("X̅",   10000);
        put("I̅X̅",   9000); // or "MX̅"
        put("V̅",    5000);
        put("I̅V̅",   4000); // or "MV̅"
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
            put(_convertToRoman(x), x);
        }
        for (int y=1; y <= 1000; ++y) { // warning
            put(_convertToRoman(y*1000), y*1000);
        }
    }};

    static HashMap<Integer, String> intToRimMap = new HashMap<Integer, String>(); // used
    static {
        for (String key : rimToIntMap.keySet()) {
            intToRimMap.put(rimToIntMap.get(key), key);
        }
    }

    static String _convertToRoman(int num) {
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

	static Integer _convertFromRoman(String romanNumStr) {
        Integer num = 0;
        String[] romanNumStrSymbols = new String[romanNumStr.length()+1]; //romanNumStr.toCharArray(); ///romanNumStr.split("(?!^)");
        int j = 0;
        for (int i=0; i<romanNumStr.length(); ++i) {
			if (i<romanNumStr.length()-1 && romanNumStr.charAt(i+1) == "M̅".charAt(1)) {
				romanNumStrSymbols[j] = ""+romanNumStr.charAt(i)+romanNumStr.charAt(i+1);
				++i;
			} else {
				romanNumStrSymbols[j] = ""+romanNumStr.charAt(i);
			}
			j++;
			///System.err.println("\n"+romanNumStrSymbols[j]+',');
		}
		int lenFull = j;

		j = lenFull - 1;
		int maxH = -1; // последняя проверенная римская цифра
		int k = 0; // количество повторов последней цифры
		boolean lastWasPlus = true;
		while (j >= 0) {
			Integer h = rimToIntMap.get(romanNumStrSymbols[j--]);
			if (h != null) {
				if (h == 0) {
//					System.err.println("k += 3");
					k += 3;
				} else if (h != maxH) {
//					System.err.println("k = 1");
					k = 1;
				} else {
//					System.err.println("++k");
					++k;
				}
//				System.err.println("\n k:"+k);
				if (k > 3) {
					if (!allowMoreForce) {
						num = null;
						break;
					}
				}
				if (h > maxH) {
					lastWasPlus = true;
				}
				if (h >= maxH && (lastWasPlus || allowMoreForce)) {
					num += h;
					lastWasPlus = true;
				} else {
					if ((lastWasPlus || allowMoreForce) && 10*h >= maxH && h == Math.round(fastPow(10, (int)logY(h, 10)))) {
						num -= h;
					} else {
						num = null;
						break;
					}
					lastWasPlus = false;
				}
				if (h >= maxH || allowMoreForce) {
					maxH = h;
				}
//				System.err.println("maxH:"+maxH);
			} else {
				num = null;
				break;
			}
		}
		///System.err.println("num: "+num);
        return num;
    }

    static float fastPow (float number, int power) {
        if (power == 0) {
            return 1.0f;
        } else if (power == 1) {
            return number;
        } else if (power < 0) {
            return 1.0f / fastPow(number, -power);
        } else if (power % 2 == 1) {
            return fastPow(number, power - 1) * number;
        } else {
            return fastPow(number * number, power / 2);
        }
    }

    static float logY(float x, int y) {
        return (float) (Math.log(x) / Math.log(y));
    }

    static String[] splitByFirstOperation (String expression) { // например "5+6"
        String orPattern = "["+Pattern.quote(addition)+Pattern.quote(subtraction)+Pattern.quote(exponentiation)+Pattern.quote(multiplication)+Pattern.quote(division)+Pattern.quote(remainding)+Pattern.quote(logarithm)+"]";
        expression = expression.trim().replaceAll(orPattern, " $0 "); // surround operation with spaces

        String[] result = expression.split(" +"); // разбиваем по operation: + - * /
        if (result == null || result.length != 3) { // по условию допустима только одна операция для двух чисел
            return null;
        } else {
            return new String[] {result[0], result[2], result[1].trim()}; // например ["5", "6", "+"]
        }
    }

	static String toRimFloat(float x) {
//		System.out.println("ZZZ:"+x);
		int d = (int)fastPow(10, floatQuality);

		x *= d;
		int a = (int)(x / d);
		int b = (int)(x % d);

//		System.out.println("a,b:"+a+"."+b);

		String b0 = toRim(a).toString();
		String b1 = mirrorStr(toRim(Integer.parseInt(mirrorStr(""+b))).toLowerCase());
//		System.out.println("b:"+b0+"."+b1);
        return b0+'.'+b1;
    }

    static String toRim(int x) {
		String result = intToRimMap.get(x);
		if (result == null) {
			result = _convertToRoman(x);
		}
        return result;
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
        Integer result = rimToIntMap.get(a);
        if (result == null) {
			result = _convertFromRoman(a);
		}
		return result;
    }

	static float fromRimFloat(String a) {
		String[] a2 = a.split("\\.");
		String b0 = null;
		String b1 = null;
		if (a2.length != 2) {
			if (a2.length == 1) {
				return (float)fromRim(a);
			} else {
				return Float.parseFloat(null);
			}
		}
		b0 = new Integer(fromRim(a2[0])).toString();
//		System.out.println("b0"+b0);
		b1 = mirrorStr(new Integer(fromRim(mirrorStr(a2[1].toLowerCase().equals(a2[1]) ? a2[1] : null).toUpperCase())).toString());
//		System.out.println(b0+'.'+b1);
        return Float.parseFloat(b0+'.'+b1);
    }

    static String mirrorStr (String sourceStr) {
		String result = "";
		///System.out.println("sourceStr:"+sourceStr);
		for (int i=0; i<sourceStr.length(); ++i) {
			result += ""+sourceStr.charAt(sourceStr.length() - 1 - i);
		}
		return result;
	}

    static String toArab(int x) {
        return Integer.toString(x);
    }

    static String toArabFloat(float x) {
        return Float.toString(x);
    }

    static int fromArab(String a) {
        int x = Integer.parseInt(a);
        return x;
    }

    static float fromArabFloat(String a) {
        float x = Float.parseFloat(a);
        return x;
    }

    public static String calc(String input) {
        input = input.replaceAll("ь", "b"); // кириллица (исправление опечатки)
        input = input.replaceAll("Ь", "b"); // кириллица (исправление опечатки)
        String[] lr = splitByFirstOperation(input);

        String a = lr[0];
        String b = lr[1];
        final String operation = lr[2];

        float x = 0;
        float y = 0;

        boolean isArab = true;
        try {
            x = fromArabFloat(a);
        } catch (Exception e) {
            x = fromRimFloat(a);
            isArab = false;
        }
        if (isArab) {
            y = fromArabFloat(b);
        } else {
            y = fromRimFloat(b);
        }

        String zStr = null;
        if (x<0 || y<0) { // incorrect input
        } else {
            float z;
            switch (operation) {
                case addition:
                    z = x + y;
                    break;
                case subtraction:
                    z = x - y;
                    break;
                /*case exponentiation:
                    z = (int) fastPow(x, y);
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
                case logarithm:
                    z = (int) logY(x, y);
                    break;*/
                default:
                    z = x / 0; // exception for unsupported operation
            }

            if (isArab) {
                zStr = toArabFloat(z); ///
            } else {
                zStr = toRimFloat(z); ///
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
