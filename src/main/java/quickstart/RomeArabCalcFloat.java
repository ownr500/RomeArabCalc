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

public class RomeArabCalcFloat extends RomeArabCalc {
    private boolean isArab;

    final int floatQuality = 5; // число знаков после запятой для float
    final float accuracy = fastPow(10, -floatQuality);
    final float accuracyMulter = fastPow(10, floatQuality);
   
    float fastPow (float number, float power) {
		Integer powerN = _partialRound (power);
		if (powerN != null) {
			return fastPow(number, (int)powerN);
		} else {
			return (float)Math.pow(number, power);
		}
	}
	
	Integer _partialRound (float number) {
		float r = accuracyMulter*(number-accuracy) - accuracyMulter*(number);
		if (r >= -0.1 && r <= 0.1) {
			return (int)number;
		} else {
			return null;
		}
	}

//    static float logY(float x, float y) {
//        return (float) (Math.log(x) / Math.log(y));
//    }

    protected String[] splitByFirstOperation (String expression) { // например "5.0+6.7"
        return super.splitByFirstOperation(expression);
    }

	protected String toRim(float x) { // may be negative too
//		System.out.println("ZZZ:"+x);
		int d = (int)fastPow(10, floatQuality);
		int mult = (x >= 0) ? (1) : (-1);
		x = x * mult; // abs

		x *= d;
		int a = (int)(x / d);
		int b = (int)(x % d);

//		System.out.println("a,b:"+a+"."+b);

		String b0 = super.toRim(a).toString();
		String b1 = mirrorStr(super.toRim(Integer.parseInt(mirrorStr(""+b))).toLowerCase());
		String pref = (mult > 0) ? ("") : ("-");
//		System.out.println("b:"+b0+"."+b1);
		if (b1.equals("n")) {
			return pref+b0;
		} else {
			return pref+b0+"."+b1;
		}
    }

	protected Number fromRim(String a) {
		String[] a2 = a.split("\\.");
		String b0 = null;
		String b1 = null;
		if (a2.length != 2) {
			if (a2.length == 1) {
				return (float)((int)super.fromRim(a));
			} else {
				return Float.parseFloat(null);
			}
		}
		b0 = (new Integer((int)super.fromRim(a2[0]))).toString();
//		System.out.println("b0"+b0);
		b1 = mirrorStr((new Integer((int)super.fromRim(mirrorStr(a2[1].toLowerCase().equals(a2[1]) ? a2[1] : null).toUpperCase()))).toString());
//		System.out.println(b0+'.'+b1);
        return Float.parseFloat(b0+'.'+b1);
    }

    protected String toArab(float x) {
        String result = Float.toString(x);
        String[] a = result.split("\\.");
        if (a.length == 2 && Integer.parseInt(a[1]) == 0) {
            result = a[0];
        }
        return result;
    }

    protected Number fromArab(String a) {
        Float x = Float.parseFloat(a);
        return x;
    }
    
    protected String _zStr(float z) {
		String zStr;
		try {
			if (isArab) {
				zStr = toArab(z); ///
			} else {
				zStr = toRim(z); ///
			}
		} catch (Exception e3) {
			e3.printStackTrace();
			System.err.println("ERROR: result overflow or convertion error (float)");
			zStr = null;
		}
		return zStr;
	}

    public String calc(String input) {
		String zStr = null;
        input = input.replaceAll("ь", "b"); // кириллица (исправление опечатки)
        input = input.replaceAll("Ь", "b"); // кириллица (исправление опечатки)
        String[] lr = splitByFirstOperation(input);

        String a = lr[0];
        String b = lr[1];
        final String operation = lr[2];

        float x = 0;
        float y = 0;
        String tildeCall = null;

        isArab = true;
        try {
			try {
				x = (float)fromArab(a);
			} catch (Exception e) {
				x = (float)fromRim(a);
				isArab = false;
			}
			if (operation.equals("~")) {
				tildeCall = b;
			} else {
			if (isArab) {
				y = (float)fromArab(b);
			} else {
				y = (float)fromRim(b);
				if (new Integer(0).equals(_partialRound(y))) {
					y = 0.0f;
				} else if (new Integer(1).equals(_partialRound(y))) {
					y = 1.0f;
				}
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
            Float z = null;
            try {
				if ((operation.equals(division) || operation.equals(remainding)) && (y == 0 || y == 0.0f || y == -0.0f)) {
					z = (float)(1 / 0);
				} else {
					switch (operation) {
						case tilde:
							switch (tildeCall) {
								case "ABS":
									z = (x >= 0) ? (x) : (-x);
									zStr = _zStr(z);
									break;
								case "MINUS":
									z = -x;
									zStr = _zStr(z);
									break;
								case "FACTORIAL":
									z = (float)factorial((int)x); ///
									zStr = _zStr(z);
									break;
								case "TOARAB":
									if (!isArab) {
										z = (float)fromRim(a);
										zStr = toArab(z);
									} else {
										zStr = a;
									}
									break;
								case "TOROMAN":
									if (!isArab) {
										zStr = a;
									} else {
										z = (float)fromArab(a);
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
							z = fastPow(x, y);
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
							z = logY(x, y);
							break;
						default:
							z = (float)(1 / 0); // exception for unsupported operation
					}
            }
			} catch (Exception e2) {
				System.err.println("ERROR: unsupported operation or error within operation");
				z = null;
			}
			if (z != null) {
				zStr = _zStr(z);
			} else {
				zStr = null;
			}
        }
        return zStr.toString();  // toString for throws exception in case of wrong null
    }

    public static void main(String[] args) {	
        String input = String.join(" ", args);
        System.out.println("Input (float): " + input);
        System.out.print("Output (float): ");
        try {
            System.out.println("" + new RomeArabCalcFloat().calc(input));
        } catch (Exception e) {
            System.out.println();
            System.err.println("Exception: ");
            e.printStackTrace();
        }
    }

}
