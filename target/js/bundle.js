/* Generated from Java with JSweet 2.3.6 - http://www.jsweet.org */
var quickstart;
(function (quickstart) {
    class Main {
        static __static_initialize() { if (!Main.__static_initialized) {
            Main.__static_initialized = true;
            Main.__static_initializer_0();
        } }
        static accuracy_$LI$() { Main.__static_initialize(); if (Main.accuracy == null)
            Main.accuracy = Main.fastPow(10, -Main.floatQuality); return Main.accuracy; }
        ;
        static accuracyMulter_$LI$() { Main.__static_initialize(); if (Main.accuracyMulter == null)
            Main.accuracyMulter = Main.fastPow(10, Main.floatQuality); return Main.accuracyMulter; }
        ;
        static isArab_$LI$() { Main.__static_initialize(); return Main.isArab; }
        ;
        static romanSourceMap_$LI$() { Main.__static_initialize(); return Main.romanSourceMap; }
        ;
        static rimToIntMap_$LI$() { Main.__static_initialize(); return Main.rimToIntMap; }
        ;
        static intToRimMap_$LI$() { Main.__static_initialize(); return Main.intToRimMap; }
        ;
        static __static_initializer_0() {
            Main.romanSourceMap = (new java.util.LinkedHashMap());
            Main.romanSourceMap_$LI$().put("M\u0305", 1000000);
            Main.romanSourceMap_$LI$().put("C\u0305M\u0305", 900000);
            Main.romanSourceMap_$LI$().put("D\u0305", 500000);
            Main.romanSourceMap_$LI$().put("C\u0305D\u0305", 400000);
            Main.romanSourceMap_$LI$().put("C\u0305", 100000);
            Main.romanSourceMap_$LI$().put("X\u0305C\u0305", 90000);
            Main.romanSourceMap_$LI$().put("L\u0305", 50000);
            Main.romanSourceMap_$LI$().put("X\u0305L\u0305", 40000);
            Main.romanSourceMap_$LI$().put("X\u0305", 10000);
            Main.romanSourceMap_$LI$().put("I\u0305X\u0305", 9000);
            Main.romanSourceMap_$LI$().put("V\u0305", 5000);
            Main.romanSourceMap_$LI$().put("I\u0305V\u0305", 4000);
            Main.romanSourceMap_$LI$().put("M", 1000);
            Main.romanSourceMap_$LI$().put("CM", 900);
            Main.romanSourceMap_$LI$().put("D", 500);
            Main.romanSourceMap_$LI$().put("CD", 400);
            Main.romanSourceMap_$LI$().put("C", 100);
            Main.romanSourceMap_$LI$().put("XC", 90);
            Main.romanSourceMap_$LI$().put("L", 50);
            Main.romanSourceMap_$LI$().put("XL", 40);
            Main.romanSourceMap_$LI$().put("X", 10);
            Main.romanSourceMap_$LI$().put("IX", 9);
            Main.romanSourceMap_$LI$().put("V", 5);
            Main.romanSourceMap_$LI$().put("IV", 4);
            Main.romanSourceMap_$LI$().put("I", 1);
            Main.rimToIntMap = (new java.util.HashMap());
            Main.rimToIntMap_$LI$().put("N", 0);
            for (let x = 1; x <= 10000; ++x) {
                {
                    Main.rimToIntMap_$LI$().put(Main._convertToRoman(x), x);
                }
                ;
            }
            for (let y = 1; y <= 1000; ++y) {
                {
                    Main.rimToIntMap_$LI$().put(Main._convertToRoman(y * 1000), y * 1000);
                }
                ;
            }
            Main.intToRimMap = (new java.util.HashMap());
            for (let index121 = Main.rimToIntMap_$LI$().keySet().iterator(); index121.hasNext();) {
                let key = index121.next();
                {
                    Main.intToRimMap_$LI$().put(Main.rimToIntMap_$LI$().get(key), key);
                }
            }
        }
        static _convertToRoman(num) {
            let str = "";
            for (let index122 = Main.romanSourceMap_$LI$().entrySet().iterator(); index122.hasNext();) {
                let entry = index122.next();
                {
                    let i = entry.getKey();
                    let romanI = entry.getValue();
                    let q = ((Math.floor((num / romanI | 0))) | 0);
                    num -= q * romanI;
                    str += i.repeat(q);
                }
            }
            return str;
        }
        static _convertFromRoman(romanNumStr) {
            let num = 0;
            let romanNumStrSymbols = (s => { let a = []; while (s-- > 0)
                a.push(null); return a; })(romanNumStr.length + 1);
            let j = 0;
            for (let i = 0; i < romanNumStr.length; ++i) {
                {
                    if (i < romanNumStr.length - 1 && (c => c.charCodeAt == null ? c : c.charCodeAt(0))(romanNumStr.charAt(i + 1)) == (c => c.charCodeAt == null ? c : c.charCodeAt(0))("M\u0305".charAt(1))) {
                        romanNumStrSymbols[j] = "" + romanNumStr.charAt(i) + romanNumStr.charAt(i + 1);
                        ++i;
                    }
                    else {
                        romanNumStrSymbols[j] = "" + romanNumStr.charAt(i);
                    }
                    j++;
                }
                ;
            }
            let lenFull = j;
            j = lenFull - 1;
            let maxH = -1;
            let k = 0;
            let lastWasPlus = true;
            while ((j >= 0)) {
                {
                    let h = Main.rimToIntMap_$LI$().get(romanNumStrSymbols[j--]);
                    if (h != null) {
                        if (h === 0) {
                            k += 3;
                        }
                        else if (h !== maxH) {
                            k = 1;
                        }
                        else {
                            ++k;
                        }
                        if (k > 3) {
                            if (!Main.allowMoreForce) {
                                num = null;
                                break;
                            }
                        }
                        if (h > maxH) {
                            lastWasPlus = true;
                        }
                        if (h >= maxH && (lastWasPlus || Main.allowMoreForce)) {
                            num += h;
                            lastWasPlus = true;
                        }
                        else {
                            if ((lastWasPlus || Main.allowMoreForce) && 10 * h >= maxH && h === Math.round(Main.fastPow$float$int(10, (Main.logY(h, 10) | 0)))) {
                                num -= h;
                            }
                            else {
                                num = null;
                                break;
                            }
                            lastWasPlus = false;
                        }
                        if (h >= maxH || Main.allowMoreForce) {
                            maxH = h;
                        }
                    }
                    else {
                        num = null;
                        break;
                    }
                }
            }
            ;
            return num;
        }
        static fastPow$float$int(number, power) {
            if (power === 0) {
                return 1.0;
            }
            else if (power === 1) {
                return number;
            }
            else if (power < 0) {
                return Math.fround(1.0 / Main.fastPow$float$int(number, -power));
            }
            else if (power % 2 === 1) {
                return Math.fround(Main.fastPow$float$int(number, power - 1) * number);
            }
            else {
                return Main.fastPow$float$int(Math.fround(number * number), (power / 2 | 0));
            }
        }
        static fastPow(number, power) {
            if (((typeof number === 'number') || number === null) && ((typeof power === 'number') || power === null)) {
                return quickstart.Main.fastPow$float$int(number, power);
            }
            else if (((typeof number === 'number') || number === null) && ((typeof power === 'number') || power === null)) {
                return quickstart.Main.fastPow$float$float(number, power);
            }
            else
                throw new Error('invalid overload');
        }
        static fastPow$float$float(number, power) {
            let powerN = Main._partialRound(power);
            if (powerN != null) {
                return Main.fastPow$float$int(number, (powerN | 0));
            }
            else {
                return Math.fround(Math.pow(number, power));
            }
        }
        static _partialRound(number) {
            let r = Math.fround(Math.fround(Main.accuracyMulter_$LI$() * (Math.fround(number - Main.accuracy_$LI$()))) - Math.fround(Main.accuracyMulter_$LI$() * (number)));
            if (r >= -0.1 && r <= 0.1) {
                return (number | 0);
            }
            else {
                return null;
            }
        }
        static logY(x, y) {
            return Math.fround((Math.log(x) / Math.log(y)));
        }
        static splitByFirstOperation(expression) {
            if (expression.indexOf(Main.subtraction) >= 0) {
                expression = expression.replace(/\-/g, " - ");
            }
            let orPattern = "[" + java.util.regex.Pattern.quote(Main.tilde) + java.util.regex.Pattern.quote(Main.addition) + java.util.regex.Pattern.quote(Main.exponentiation) + java.util.regex.Pattern.quote(Main.multiplication) + java.util.regex.Pattern.quote(Main.division) + java.util.regex.Pattern.quote(Main.remainding) + java.util.regex.Pattern.quote(Main.logarithm) + "]";
            expression = /* replaceAll */ expression.trim().replace(new RegExp("(" + orPattern + ")", 'g'), " $1 ");
            let p = java.util.regex.Pattern.compile("[ ]+");
            let result = expression.split(/[ ]+/g);///p.split(expression);
            if (result == null || result.length !== 3) {
                return null;
            }
            else {
                return [result[0], result[2], result[1].trim()];
            }
        }
        static toRimFloat(x) {
            let d = (Main.fastPow$float$int(10, Main.floatQuality) | 0);
            let mult = (x >= 0) ? (1) : (-1);
            x = Math.fround(x * mult);
            x *= d;
            let a = ((Math.fround(x / d)) | 0);
            let b = ((Math.fround(x % d)) | 0);
            let b0 = Main.toRim(a).toString();
            let b1 = Main.mirrorStr(Main.toRim(javaemul.internal.IntegerHelper.parseInt(Main.mirrorStr("" + b))).toLowerCase());
            let pref = (mult > 0) ? ("") : ("-");
            if ( /* equals */((o1, o2) => { if (o1 && o1.equals) {
                return o1.equals(o2);
            }
            else {
                return o1 === o2;
            } })(b1, "n")) {
                return pref + b0;
            }
            else {
                return pref + b0 + "." + b1;
            }
        }
        static toRim(x) {
            let result = Main.intToRimMap_$LI$().get(x);
            if (result == null) {
                result = Main._convertToRoman(x);
            }
            return result;
        }
        static fromRim(a) {
            if (!((o1, o2) => { if (o1 && o1.equals) {
                return o1.equals(o2);
            }
            else {
                return o1 === o2;
            } })(a.toUpperCase(), a)) {
                return (2 / 0 | 0);
            }
            a = /* replaceAll */ a.replace(new RegExp("\u041c", 'g'), "M");
            a = /* replaceAll */ a.replace(new RegExp("\u041b", 'g'), "L");
            a = /* replaceAll */ a.replace(new RegExp("\u0414", 'g'), "D");
            a = /* replaceAll */ a.replace(new RegExp("\u041d", 'g'), "N");
            a = /* replaceAll */ a.replace(new RegExp("\u0421", 'g'), "C");
            a = /* replaceAll */ a.replace(new RegExp("\u0425", 'g'), "X");
            a = /* replaceAll */ a.replace(new RegExp("\u0412", 'g'), "V");
            a = /* replaceAll */ a.replace(new RegExp("\u041f", 'g'), "V");
            a = /* replaceAll */ a.replace(new RegExp("\u0406", 'g'), "I");
            a = /* replaceAll */ a.replace(new RegExp("\u0407", 'g'), "I");
            a = /* replaceAll */ a.replace(new RegExp("\u00cf", 'g'), "I");
            let result = Main.rimToIntMap_$LI$().get(a);
            if (result == null) {
                result = Main._convertFromRoman(a);
            }
            return result;
        }
        static fromRimFloat(a) {
            let a2 = a.split(".");
            let b0 = null;
            let b1 = null;
            if (a2.length !== 2) {
                if (a2.length === 1) {
                    return Main.fromRim(a);
                }
                else {
                    return javaemul.internal.FloatHelper.parseFloat(null);
                }
            }
            b0 = new Number(Main.fromRim(a2[0])).toString();
            b1 = Main.mirrorStr(new Number(Main.fromRim(Main.mirrorStr(/* equals */ ((o1, o2) => { if (o1 && o1.equals) {
                return o1.equals(o2);
            }
            else {
                return o1 === o2;
            } })(a2[1].toLowerCase(), a2[1]) ? a2[1] : null).toUpperCase())).toString());
            return javaemul.internal.FloatHelper.parseFloat(b0 + '.' + b1);
        }
        static mirrorStr(sourceStr) {
            let result = "";
            for (let i = 0; i < sourceStr.length; ++i) {
                if (i < sourceStr.length - 1 && (c => c.charCodeAt == null ? c : c.charCodeAt(0))(sourceStr.charAt(sourceStr.length - 1 - i)) == (c => c.charCodeAt == null ? c : c.charCodeAt(0))("M\u0305".charAt(1))) {
                    result += "" + sourceStr.charAt(sourceStr.length - 2 - i) + sourceStr.charAt(sourceStr.length - 1 - i);
                    ++i;
                }
                else {
                    result += "" + sourceStr.charAt(sourceStr.length - 1 - i);
                }
                ;
            }
            return result;
        }
        static toArab(x) {
            return /* toString */ ('' + (x));
        }
        static toArabFloat(x) {
            return /* toString */ ('' + (x));
        }
        static fromArab(a) {
            let x = javaemul.internal.IntegerHelper.parseInt(a);
            return x;
        }
        static fromArabFloat(a) {
            let x = javaemul.internal.FloatHelper.parseFloat(a);
            return x;
        }
        static factorial(n) {
            if (n < 0) {
                return null;
            }
            else if (n === 0 || n === 1) {
                return 1;
            }
            else {
                return n * Main.factorial(n - 1);
            }
        }
        static _zStr(z) {
            let zStr;
            try {
                if (Main.isArab_$LI$()) {
                    zStr = Main.toArabFloat(z);
                }
                else {
                    zStr = Main.toRimFloat(z);
                }
            }
            catch (e3) {
                console.error(e3.message, e3);
                console.error("ERROR: result overflow or convertion error");
                zStr = null;
            }
            ;
            return zStr;
        }
        static calc(input) {
            let zStr = null;
            input = /* replaceAll */ input.replace(new RegExp("\u044c", 'g'), "b");
            input = /* replaceAll */ input.replace(new RegExp("\u042c", 'g'), "b");
            let lr = Main.splitByFirstOperation(input);
            let a = lr[0];
            let b = lr[1];
            let operation = lr[2];
            let x = 0;
            let y = 0;
            let tildeCall = null;
            Main.isArab = true;
            try {
                try {
                    x = Main.fromArabFloat(a);
                }
                catch (e) {
                    x = Main.fromRimFloat(a);
                    Main.isArab = false;
                }
                ;
                if ( /* equals */((o1, o2) => { if (o1 && o1.equals) {
                    return o1.equals(o2);
                }
                else {
                    return o1 === o2;
                } })(operation, "~")) {
                    tildeCall = b;
                }
                else {
                    if (Main.isArab_$LI$()) {
                        y = Main.fromArabFloat(b);
                    }
                    else {
                        y = Main.fromRimFloat(b);
                        if ( /* equals */((o1, o2) => { if (o1 && o1.equals) {
                            return o1.equals(o2);
                        }
                        else {
                            return o1 === o2;
                        } })(new Number(0), Main._partialRound(y))) {
                            y = 0.0;
                        }
                        else if ( /* equals */((o1, o2) => { if (o1 && o1.equals) {
                            return o1.equals(o2);
                        }
                        else {
                            return o1 === o2;
                        } })(new Number(1), Main._partialRound(y))) {
                            y = 1.0;
                        }
                    }
                }
            }
            catch (e1) {
                console.error(e1.message, e1);
                console.error("ERROR: wrong input");
                x = -1;
                y = -1;
                zStr = null;
                return zStr.toString();
            }
            ;
            if (x < 0 || y < 0) {
                console.error("ERROR: unsupported input, negative or too big numbers");
                zStr = null;
            }
            else {
                let z = null;
                try {
                    if (( /* equals */((o1, o2) => { if (o1 && o1.equals) {
                        return o1.equals(o2);
                    }
                    else {
                        return o1 === o2;
                    } })(operation, Main.division) || /* equals */ ((o1, o2) => { if (o1 && o1.equals) {
                        return o1.equals(o2);
                    }
                    else {
                        return o1 === o2;
                    } })(operation, Main.remainding)) && (y === 0.0 || y === -0.0)) {
                        z = ((1 / 0 | 0));
                    }
                    else {
                        switch ((operation)) {
                            case "~" /* tilde */:
                                switch ((tildeCall)) {
                                    case "ABS":
                                        z = (x >= 0) ? (x) : (-x);
                                        zStr = Main._zStr(z);
                                        break;
                                    case "MINUS":
                                        z = -x;
                                        zStr = Main._zStr(z);
                                        break;
                                    case "FACTORIAL":
                                        z = Main.factorial((x | 0));
                                        zStr = Main._zStr(z);
                                        break;
                                    case "TOARAB":
                                        if (!Main.isArab_$LI$()) {
                                            z = Main.fromRimFloat(a);
                                            zStr = Main.toArabFloat(z);
                                        }
                                        else {
                                            zStr = a;
                                        }
                                        break;
                                    case "TOROMAN":
                                        if (!Main.isArab_$LI$()) {
                                            zStr = a;
                                        }
                                        else {
                                            z = Main.fromArabFloat(a);
                                            zStr = Main.toRimFloat(z);
                                        }
                                        break;
                                    case "TORIM":
                                        zStr = "\u0412 \u0418\u0442\u0430\u043b\u0438\u044e, \u043d\u0430 \u0440\u043e\u0434\u0438\u043d\u0443 \u043c\u0443\u0437\u044b\u043a\u0438!";
                                        break;
                                    default:
                                        zStr = null;
                                }
                                return zStr.toString();
                            case "+" /* addition */:
                                z = Math.fround(x + y);
                                break;
                            case "-" /* subtraction */:
                                z = Math.fround(x - y);
                                break;
                            case "^" /* exponentiation */:
                                z = Main.fastPow$float$float(x, y);
                                break;
                            case "*" /* multiplication */:
                                z = Math.fround(x * y);
                                break;
                            case "/" /* division */:
                                z = Math.fround(x / y);
                                break;
                            case "%" /* remainding */:
                                z = Math.fround(x % y);
                                break;
                            case "b" /* logarithm */:
                                z = Main.logY(x, y);
                                break;
                            default:
                                z = ((1 / 0 | 0));
                        }
                    }
                }
                catch (e2) {
                    console.error("ERROR: unsupported operation or error within operation");
                    z = null;
                }
                ;
                if (z != null) {
                    zStr = Main._zStr(z);
                }
                else {
                    zStr = null;
                }
            }
            return zStr.toString();
        }
        static main(args) {
            let input = "X - II";
            console.info("Input: " + input);
            console.info("Output: ");
            try {
                console.info("" + Main.calc(input));
            }
            catch (e) {
                console.info();
                console.error("Exception: ");
                console.error(e.message, e);
            }
            ;
        }
    }
    Main.__static_initialized = false;
    Main.tilde = "~";
    Main.addition = "+";
    Main.subtraction = "-";
    Main.exponentiation = "^";
    Main.multiplication = "*";
    Main.division = "/";
    Main.remainding = "%";
    Main.logarithm = "b";
    
    Main.floatQuality = 5;
    Main.allowMoreForce = false;
    quickstart.Main = Main;
    Main["__class"] = "quickstart.Main";
    (function (Main) {
        class Main$0 extends java.util.LinkedHashMap {
            constructor() {
                super();
                (() => {
                    this.put("M\u0305", 1000000);
                    this.put("C\u0305M\u0305", 900000);
                    this.put("D\u0305", 500000);
                    this.put("C\u0305D\u0305", 400000);
                    this.put("C\u0305", 100000);
                    this.put("X\u0305C\u0305", 90000);
                    this.put("L\u0305", 50000);
                    this.put("X\u0305L\u0305", 40000);
                    this.put("X\u0305", 10000);
                    this.put("I\u0305X\u0305", 9000);
                    this.put("V\u0305", 5000);
                    this.put("I\u0305V\u0305", 4000);
                    this.put("M", 1000);
                    this.put("CM", 900);
                    this.put("D", 500);
                    this.put("CD", 400);
                    this.put("C", 100);
                    this.put("XC", 90);
                    this.put("L", 50);
                    this.put("XL", 40);
                    this.put("X", 10);
                    this.put("IX", 9);
                    this.put("V", 5);
                    this.put("IV", 4);
                    this.put("I", 1);
                })();
            }
        }
        Main.Main$0 = Main$0;
        Main$0["__interfaces"] = ["java.lang.Cloneable", "java.util.Map", "java.io.Serializable"];
        class Main$1 extends java.util.HashMap {
            constructor() {
                super();
                (() => {
                    this.put("N", 0);
                    for (let x = 1; x <= 10000; ++x) {
                        {
                            this.put(Main._convertToRoman(x), x);
                        }
                        ;
                    }
                    for (let y = 1; y <= 1000; ++y) {
                        {
                            this.put(Main._convertToRoman(y * 1000), y * 1000);
                        }
                        ;
                    }
                })();
            }
        }
        Main.Main$1 = Main$1;
        Main$1["__interfaces"] = ["java.lang.Cloneable", "java.util.Map", "java.io.Serializable"];
    })(Main = quickstart.Main || (quickstart.Main = {}));
})(quickstart || (quickstart = {}));
(function (quickstart) {
    /**
     * This class is used within the webapp/index.html file.
     * @class
     */
    class QuickStart {
        static main(args) {
            let l = (new java.util.ArrayList());
            l.add("Hello");
            l.add("world");
            let a = (new Array());
            a.push("Hello", "world");
            $("#target").text(l.toString());
            alert(a.toString());
        }
    }
    quickstart.QuickStart = QuickStart;
    QuickStart["__class"] = "quickstart.QuickStart";
})(quickstart || (quickstart = {}));
quickstart.Main.intToRimMap_$LI$();
quickstart.Main.rimToIntMap_$LI$();
quickstart.Main.romanSourceMap_$LI$();
quickstart.Main.isArab_$LI$();
quickstart.Main.accuracyMulter_$LI$();
quickstart.Main.accuracy_$LI$();
quickstart.Main.__static_initialize();
quickstart.Main.main(null);

