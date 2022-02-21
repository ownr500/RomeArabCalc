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

public class RomeArabCalcFloat implements RomeArabCalcBase<Float> {
    private static final RomeArabCalc superR = new RomeArabCalc();
    
    private boolean isArab;

    protected final int floatQuality = 5; // число знаков после запятой для float
    protected final float accuracy = fastPow(10, -floatQuality);
    protected final float accuracyMulter = fastPow(10, floatQuality);
   	
	protected Integer _partialRound (float number) {
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

    public String[] splitByFirstOperation (String expression) { // например "5.0+6.7"
        return superR.splitByFirstOperation(expression);
    }
    
    public String mirrorStr (String sourceStr) {
        return superR.mirrorStr(sourceStr);
    }
    
    public float fastPow (float number, float power) {
		Integer powerN = _partialRound (power);
		if (powerN != null) {
			return superR.fastPow(number, (int)powerN);
		} else {
			return (float)Math.pow(number, power);
		}
	}

	public String toRim (Number x_) { // may be negative too
        float x = (Float)x_.floatValue();
//		System.out.println("ZZZ:"+x);
		int d = (int)fastPow(10, floatQuality);
		int mult = (x >= 0) ? (1) : (-1);
		x = x * mult; // abs

		x *= d;
		int a = (int)(x / d);
		int b = (int)(x % d);

//		System.out.println("a,b:"+a+"."+b);

		String b0 = superR.toRim(a).toString();
		String b1 = mirrorStr(superR.toRim(Integer.parseInt(mirrorStr(""+b))).toLowerCase());
		String pref = (mult > 0) ? ("") : ("-");
//		System.out.println("b:"+b0+"."+b1);
		if (b1.equals("n")) {
			return pref+b0;
		} else {
			return pref+b0+"."+b1;
		}
    }

	public Number fromRim (String a) {
		String[] a2 = a.split("\\.");
		String b0 = null;
		String b1 = null;
		if (a2.length != 2) {
			if (a2.length == 1) {
				return (float)((int)superR.fromRim(a));
			} else {
				return Float.parseFloat(null);
			}
		}
		b0 = (new Integer((int)superR.fromRim(a2[0]))).toString();
//		System.out.println("b0"+b0);
		b1 = mirrorStr((new Integer((int)superR.fromRim(mirrorStr(a2[1].toLowerCase().equals(a2[1]) ? a2[1] : null).toUpperCase()))).toString());
//		System.out.println(b0+'.'+b1);
        return Float.parseFloat(b0+'.'+b1);
    }

    public String toArab (Number x_) {
        float x = (Float)x_.floatValue();
        String result = Float.toString(x);
        String[] a = result.split("\\.");
        if (a.length == 2 && Integer.parseInt(a[1]) == 0) {
            result = a[0];
        }
        return result;
    }

    public Number fromArab (String a) {
        Float x = Float.parseFloat(a);
        return x;
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
