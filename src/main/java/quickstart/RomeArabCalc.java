package quickstart;
/**
 * RomeArabCalc
 * @license MIT
 */

import java.util.ArrayList;
import java.util.List;

import java.util.*;
import java.util.regex.Pattern;

import static quickstart.RomeArabCalc.Operations.*;

public class RomeArabCalc {

    class Operations {
        final static String tilde = "~"; // вызов функции (справа)
        final static String addition = "+"; // сложение
        final static String subtraction = "-"; // вычитание
        final static String exponentiation = "^"; // возведение левого числа в степень (справа)
        final static String multiplication = "*"; // умножение
        final static String division = "/"; // целочисленное деление
        final static String remainding = "%"; // остаток от деления
        final static String logarithm = "b"; // логарифм левого числа по основанию (справа)
    }

    private boolean isArab;

    protected static final boolean allowMoreForce = false; ///

    protected static final Map<String, Integer> romanSourceMap = new LinkedHashMap<String, Integer>() {{ // source (adding order is important here, so using LinkedHashMap)
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

    protected static Map<String, Integer> rimToIntMap = new HashMap<String, Integer>() {{ // used
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

    protected static HashMap<Integer, String> intToRimMap = new HashMap<Integer, String>(); // used
    static {
        for (String key : rimToIntMap.keySet()) {
            intToRimMap.put(rimToIntMap.get(key), key);
        }
    }

    private static String _convertToRoman(int num) {
        String str = "";
        for (Map.Entry<String, Integer> entry : romanSourceMap.entrySet()) {
            String i = entry.getKey();
            int romanI = entry.getValue();
            int q = (int) (Math.floor(num / romanI));
            num -= q * romanI;
            str += new String(new char[q]).replace("\0", i); // str +=  String.join("", Collections.nCopies(q, i)); // i repeat q times ///i.repeat(q)
        }
        return str;
    }

	private static Integer _convertFromRoman(String romanNumStr) {
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
					if ((lastWasPlus || allowMoreForce) && 10*h >= maxH && h == Math.round(Math.pow(10, (int)logY(h, 10)))) {
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

    public float fastPow (float number, int power) {
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

    public static float logY(float x, float y) {
        return (float) (Math.log(x) / Math.log(y));
    }

    protected String[] splitByFirstOperation (String expression) { // например "5+6"
        String orPattern = "["+Pattern.quote(tilde)+Pattern.quote(addition)+Pattern.quote(subtraction)+Pattern.quote(exponentiation)+Pattern.quote(multiplication)+Pattern.quote(division)+Pattern.quote(remainding)+Pattern.quote(logarithm)+"]";
        expression = expression.trim().replaceAll("("+orPattern+")", " $1 "); // surround operation with spaces

		Pattern p = Pattern.compile("[ ]+");  
        String[] result = p.split(expression); // разбиваем по operation: + - * /
        if (result == null || result.length != 3) { // по условию допустима только одна операция для двух чисел
            return null;
        } else {
            return new String[] {result[0], result[2], result[1].trim()}; // например ["5", "6", "+"]
        }
    }

    protected String toRim (Number x_) { // only 0 and positive integers
        int x = (Integer)x_;
        int multer = 1;
        if (x < 0) {
            x = -x;
            multer = -1;
        }
		String result = intToRimMap.get(x);
		if (result == null) {
			result = _convertToRoman(x);
		}
        return ((multer < 0) ? "-" : "") + result;
    }

    protected Number fromRim (String a) {
		if (!a.toUpperCase().equals(a)) {
			return new Integer(2 / 0);
		}
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
		return new Integer(result);
    }

    public String mirrorStr (String sourceStr) {
		String result = "";
		///System.out.println("sourceStr:"+sourceStr);
		for (int i=0; i<sourceStr.length(); ++i) {
            if (i<sourceStr.length()-1 && sourceStr.charAt(sourceStr.length() - 1 - i) == "M̅".charAt(1)) {
			    result += ""+sourceStr.charAt(sourceStr.length() - 2 - i)+sourceStr.charAt(sourceStr.length() - 1 - i);
                ++i;
            } else {
			    result += ""+sourceStr.charAt(sourceStr.length() - 1 - i);
            }
		}
		return result;
	}

    protected String toArab (Number x) {
        return Integer.toString((Integer)x);
    }

    protected Number fromArab (String a) {
        Integer x = Integer.parseInt(a);
        return x;
    }
    
    public static Integer factorial (int n) {
		if (n < 0) {
			return null;
		} else if (n == 0 || n == 1) {
			return 1;
		} else {
			return n*factorial(n-1);
		}
	}
    
    protected String _zStr (Number z, boolean isArab) {
		String zStr;
		try {
			if (isArab) {
				zStr = toArab(z); ///
			} else {
				zStr = toRim(z); ///
			}
		} catch (Exception e3) {
			e3.printStackTrace();
			System.err.println("ERROR: result overflow or convertion error");
			zStr = null;
		}
		return zStr;
	}

    public String calc (String input) {
		String zStr = null;
        input = input.replaceAll("ь", "b"); // кириллица (исправление опечатки)
        input = input.replaceAll("Ь", "b"); // кириллица (исправление опечатки)
        String[] lr = splitByFirstOperation(input);

        String a = lr[0];
        String b = lr[1];
        final String operation = lr[2];

        int x = 0;
        int y = 0;
        String tildeCall = null;

        isArab = true;
        try {
			try {
				x = (int)fromArab(a);
			} catch (Exception e) {
				x = (int)fromRim(a);
				isArab = false;
			}
			if (operation.equals("~")) {
				tildeCall = b;
			} else {
			if (isArab) {
				y = (int)fromArab(b);
			} else {
				y = (int)fromRim(b);
			}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.err.println("ERROR: wrong input");
			x = -1;
			y = -1;
			zStr = null;
			return zStr.toString();
		}

        if (x<0 || y<0) { // incorrect input
			System.err.println("ERROR: unsupported input, negative or too big numbers");
			zStr = null;
        } else {
            Integer z = null;
            try {
				if ((operation.equals(division) || operation.equals(remainding)) && (y == 0)) {
					z = 1 / 0;
				} else {
					switch (operation) {
						case tilde:
							switch (tildeCall) {
								case "ABS":
									z = (x >= 0) ? (x) : (-x);
									zStr = _zStr(z, isArab);
									break;
								case "MINUS":
									z = -x;
									zStr = _zStr(z, isArab);
									break;
								case "FACTORIAL":
									z = factorial(x); ///
									zStr = _zStr(z, isArab);
									break;
								case "TOARAB":
									if (!isArab) {
										z = (int)fromRim(a);
										zStr = toArab(z);
									} else {
										zStr = a;
									}
									break;
								case "TOROMAN":
									if (!isArab) {
										zStr = a;
									} else {
										z = (int)fromArab(a);
										zStr = toRim(z);
									}
									break;
								case "TORIM":
									zStr = "В Италию, на родину музыки!";
									break;
								default:
									zStr = null;								
							}
							return zStr.toString();
						case addition:
							z = x + y;
							break;
						case subtraction:
							z = x - y;
							break;
						case exponentiation:
							z = (int)fastPow(x, y);
							break;
						case multiplication:
							z = x * y;
							break;
						case division:
							z = (int)(x / y);
							break;
						case remainding:
							z = x % y;
							break;
						case logarithm:
							z = (int)logY(x, y);
							break;
						default:
							z = 1 / 0; // exception for unsupported operation
					}
            }
			} catch (Exception e2) {
				System.err.println("ERROR: unsupported operation or error within operation");
				z = null;
			}
			if (z != null) {
				zStr = _zStr(z, isArab);
			} else {
				zStr = null;
			}
        }
        return zStr.toString();  // toString for throws exception in case of wrong null
    }

    public static void main(String[] args) {	
        String input = String.join(" ", args);
        System.out.println("Input: " + input);
        System.out.print("Output: ");
        try {
            System.out.println("" + new RomeArabCalc().calc(input));
        } catch (Exception e) {
            System.out.println();
            System.err.println("Exception: ");
            e.printStackTrace();
        }
    }

}
