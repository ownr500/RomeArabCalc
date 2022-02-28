package quickstart;
/**
 * RomeArabCalc
 * @license MIT
 */

import java.util.ArrayList;
import java.util.List;

import java.util.*;
import java.util.regex.Pattern;

import static quickstart.RomeArabCalcBase.Operations.*;

public interface RomeArabCalcBase<N extends Number> {

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

    static final boolean allowMoreForce = false; ///
   
    @SuppressWarnings("unchecked")
    public static <T extends Number> T genNumber (int numberValue) {
        return (T)((Integer)numberValue);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Number> T genNumber (float numberValue) {
        return (T)((Float)numberValue);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T genNumber (double numberValue) {
        return (T)((Double)numberValue);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Number> T genNumber (Number numberValue) {
        return (T)((Number)numberValue);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T genZero () {
        return genNumber((int)0);
    }
    
    public static int compare (Number x, Number y) {
        return Double.compare(x.doubleValue(), y.doubleValue());
    }
    
    default String _zStr (Number z, boolean isArab) {
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
    
    public static float logY (float x, float y) {
        return (float) (Math.log(x) / Math.log(y));
    }

    public float fastPow (float number, float power);
    
    public static float fastPow (float number, int power) {
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

    public String toRim (Number x_);

    public Number fromRim (String a);

    public String mirrorStr (String sourceStr);

    public String toArab (Number x);

    public Number fromArab (String a);
        
    public static Integer factorial (int n) {
		if (n < 0) {
			return null;
		} else if (n == 0 || n == 1) {
			return 1;
		} else {
			return n*factorial(n-1);
		}
	}

    default String[] splitByFirstOperation (String expression) { // например "5+6"
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

    default String calc (String input) {
		String zStr = null;
        input = input.replaceAll("ь", "b"); // кириллица (исправление опечатки)
        input = input.replaceAll("Ь", "b"); // кириллица (исправление опечатки)
        String[] lr = splitByFirstOperation(input);

        String a = lr[0];
        String b = lr[1];
        final String operation = lr[2];

        N x = genZero();
        N y = genZero();
        String tildeCall = null;

        boolean isArab;
        isArab = true;
        try {
			try {
				x = (N)fromArab(a);
			} catch (Exception e) {
				x = (N)fromRim(a);
				isArab = false;
			}
			if (operation.equals("~")) {
				tildeCall = b;
			} else {
			if (isArab) {
				y = (N)fromArab(b);
			} else {
				y = (N)fromRim(b);
			}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.err.println("ERROR: wrong input");
			x = genNumber(-1);
			y = genNumber(-1);
			zStr = null;
			return zStr.toString();
		}

        if (compare(x, genZero())<0 || compare(y, genZero())<0) { // incorrect input
			System.err.println("ERROR: unsupported input, negative or too big numbers");
			zStr = null;
        } else {
            N z = null;
            try {
				if ((operation.equals(division) || operation.equals(remainding)) && (compare(y, 0) == 0)) {
					z = genNumber(1 / 0);
				} else {
					switch (operation) {
						case tilde:
							switch (tildeCall) {
								case "ABS":
									z = (compare(x, genZero()) >= 0) ? (x) : (N)genNumber(-(x.doubleValue()));
									zStr = _zStr((N)z, isArab);
									break;
								case "MINUS":
									z = (N)genNumber(-(x.doubleValue()));
									zStr = _zStr((N)z, isArab);
									break;
								case "FACTORIAL":
									z = (N)genNumber(factorial(genNumber(x).intValue())); ///
									zStr = _zStr((N)z, isArab);
									break;
								case "TOARAB":
									if (!isArab) {
										z = (N)fromRim(a);
										zStr = toArab(z);
									} else {
										zStr = a;
									}
									break;
								case "TOROMAN":
									if (!isArab) {
										zStr = a;
									} else {
										z = (N)fromArab(a);
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
							z = (N)genNumber(x.doubleValue() + y.doubleValue()); // x + y;
							break;
						case subtraction:
							z = (N)genNumber(x.doubleValue() - y.doubleValue()); // x - y;
							break;
						case exponentiation:
							z = (N)genNumber(fastPow(x.floatValue(), y.floatValue()));
							break;
						case multiplication:
							z = (N)genNumber(x.doubleValue() * y.doubleValue()); // x * y;
							break;
						case division:
							z = (N)genNumber(x.doubleValue() / y.doubleValue()); // x / y;
							break;
						case remainding:
							z = (N)genNumber(x.doubleValue() % y.doubleValue()); // x % y;
							break;
						case logarithm:
							z = (N)genNumber(logY(x.floatValue(), y.floatValue()));
							break;
						default:
							z = (N)genNumber(1 / 0); // exception for unsupported operation
					}
            }
			} catch (Exception e2) {
				System.err.println("ERROR: unsupported operation or error within operation");
				z = null;
			}
			if (z != null) {
				zStr = _zStr((N)z, isArab);
			} else {
				zStr = null;
			}
        }
        return zStr.toString();  // toString for throws exception in case of wrong null
    }

}
