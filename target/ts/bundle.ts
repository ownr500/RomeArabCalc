/* Generated from Java with JSweet 2.3.6 - http://www.jsweet.org */
namespace quickstart {
    export class Main {
        static __static_initialized : boolean = false;
        static __static_initialize() { if(!Main.__static_initialized) { Main.__static_initialized = true; Main.__static_initializer_0(); } }

        static tilde : string = "~";

        static addition : string = "+";

        static subtraction : string = "-";

        static exponentiation : string = "^";

        static multiplication : string = "*";

        static division : string = "/";

        static remainding : string = "%";

        static logarithm : string = "b";

        static floatQuality : number = 3;

        static accuracy : number; public static accuracy_$LI$() : number { Main.__static_initialize(); if(Main.accuracy == null) Main.accuracy = Main.fastPow(10, -Main.floatQuality); return Main.accuracy; };

        static accuracyMulter : number; public static accuracyMulter_$LI$() : number { Main.__static_initialize(); if(Main.accuracyMulter == null) Main.accuracyMulter = Main.fastPow(10, Main.floatQuality); return Main.accuracyMulter; };

        static isArab : boolean; public static isArab_$LI$() : boolean { Main.__static_initialize(); return Main.isArab; };

        static allowMoreForce : boolean = false;

        static romanSourceMap : java.util.Map<string, number>; public static romanSourceMap_$LI$() : java.util.Map<string, number> { Main.__static_initialize(); if(Main.romanSourceMap == null) Main.romanSourceMap = new Main.Main$0(); return Main.romanSourceMap; };

        static rimToIntMap : java.util.Map<string, number>; public static rimToIntMap_$LI$() : java.util.Map<string, number> { Main.__static_initialize(); if(Main.rimToIntMap == null) Main.rimToIntMap = new Main.Main$1(); return Main.rimToIntMap; };

        static intToRimMap : java.util.HashMap<number, string>; public static intToRimMap_$LI$() : java.util.HashMap<number, string> { Main.__static_initialize(); if(Main.intToRimMap == null) Main.intToRimMap = <any>(new java.util.HashMap<number, string>()); return Main.intToRimMap; };

        static __static_initializer_0() {
            for(let index121=Main.rimToIntMap_$LI$().keySet().iterator();index121.hasNext();) {
                let key = index121.next();
                {
                    Main.intToRimMap_$LI$().put(Main.rimToIntMap_$LI$().get(key), key);
                }
            }
        }

        static _convertToRoman(num : number) : string {
            let str : string = "";
            for(let index122=Main.romanSourceMap_$LI$().entrySet().iterator();index122.hasNext();) {
                let entry = index122.next();
                {
                    let i : string = entry.getKey();
                    let romanI : number = entry.getValue();
                    let q : number = (<number>(Math.floor((num / romanI|0)))|0);
                    num -= q * romanI;
                    str += /* replace */<string>new String((s => { let a=[]; while(s-->0) a.push(null); return a; })(q)).split("\u0000").join(i);
                }
            }
            return str;
        }

        static _convertFromRoman(romanNumStr : string) : number {
            let num : number = 0;
            let romanNumStrSymbols : string[] = (s => { let a=[]; while(s-->0) a.push(null); return a; })(romanNumStr.length + 1);
            let j : number = 0;
            for(let i : number = 0; i < romanNumStr.length; ++i) {{
                if(i < romanNumStr.length - 1 && (c => c.charCodeAt==null?<any>c:c.charCodeAt(0))(romanNumStr.charAt(i + 1)) == (c => c.charCodeAt==null?<any>c:c.charCodeAt(0))("M\u0305".charAt(1))) {
                    romanNumStrSymbols[j] = "" + romanNumStr.charAt(i) + romanNumStr.charAt(i + 1);
                    ++i;
                } else {
                    romanNumStrSymbols[j] = "" + romanNumStr.charAt(i);
                }
                j++;
            };}
            let lenFull : number = j;
            j = lenFull - 1;
            let maxH : number = -1;
            let k : number = 0;
            let lastWasPlus : boolean = true;
            while((j >= 0)) {{
                let h : number = Main.rimToIntMap_$LI$().get(romanNumStrSymbols[j--]);
                if(h != null) {
                    if(h === 0) {
                        k += 3;
                    } else if(h !== maxH) {
                        k = 1;
                    } else {
                        ++k;
                    }
                    if(k > 3) {
                        if(!Main.allowMoreForce) {
                            num = null;
                            break;
                        }
                    }
                    if(h > maxH) {
                        lastWasPlus = true;
                    }
                    if(h >= maxH && (lastWasPlus || Main.allowMoreForce)) {
                        num += h;
                        lastWasPlus = true;
                    } else {
                        if((lastWasPlus || Main.allowMoreForce) && 10 * h >= maxH && h === Math.round(Main.fastPow$float$int(10, (<number>Main.logY(h, 10)|0)))) {
                            num -= h;
                        } else {
                            num = null;
                            break;
                        }
                        lastWasPlus = false;
                    }
                    if(h >= maxH || Main.allowMoreForce) {
                        maxH = h;
                    }
                } else {
                    num = null;
                    break;
                }
            }};
            return num;
        }

        public static fastPow$float$int(number : number, power : number) : number {
            if(power === 0) {
                return 1.0;
            } else if(power === 1) {
                return number;
            } else if(power < 0) {
                return (<any>Math).fround(1.0 / Main.fastPow$float$int(number, -power));
            } else if(power % 2 === 1) {
                return (<any>Math).fround(Main.fastPow$float$int(number, power - 1) * number);
            } else {
                return Main.fastPow$float$int((<any>Math).fround(number * number), (power / 2|0));
            }
        }

        public static fastPow(number? : any, power? : any) : any {
            if(((typeof number === 'number') || number === null) && ((typeof power === 'number') || power === null)) {
                return <any>quickstart.Main.fastPow$float$int(number, power);
            } else if(((typeof number === 'number') || number === null) && ((typeof power === 'number') || power === null)) {
                return <any>quickstart.Main.fastPow$float$float(number, power);
            } else throw new Error('invalid overload');
        }

        static fastPow$float$float(number : number, power : number) : number {
            let powerN : number = Main._partialRound(power);
            if(powerN != null) {
                return Main.fastPow$float$int(number, (<number>powerN|0));
            } else {
                return (<any>Math).fround(Math.pow(number, power));
            }
        }

        static _partialRound(number : number) : number {
            let r : number = (<any>Math).fround((<any>Math).fround(Main.accuracyMulter_$LI$() * ((<any>Math).fround(number - Main.accuracy_$LI$()))) - (<any>Math).fround(Main.accuracyMulter_$LI$() * (number)));
            if(r >= -0.1 && r <= 0.1) {
                return (<number>number|0);
            } else {
                return null;
            }
        }

        static logY(x : number, y : number) : number {
            return (<any>Math).fround((Math.log(x) / Math.log(y)));
        }

        static splitByFirstOperation(expression : string) : string[] {
            let orPattern : string = "[" + java.util.regex.Pattern.quote(Main.tilde) + java.util.regex.Pattern.quote(Main.addition) + java.util.regex.Pattern.quote(Main.subtraction) + java.util.regex.Pattern.quote(Main.exponentiation) + java.util.regex.Pattern.quote(Main.multiplication) + java.util.regex.Pattern.quote(Main.division) + java.util.regex.Pattern.quote(Main.remainding) + java.util.regex.Pattern.quote(Main.logarithm) + "]";
            expression = /* replaceAll */expression.trim().replace(new RegExp("(" + orPattern + ")", 'g')," $1 ");
            let p : java.util.regex.Pattern = java.util.regex.Pattern.compile("[ ]+");
            let result : string[] = p.split(expression);
            if(result == null || result.length !== 3) {
                return null;
            } else {
                return [result[0], result[2], result[1].trim()];
            }
        }

        static toRimFloat(x : number) : string {
            let d : number = (<number>Main.fastPow$float$int(10, Main.floatQuality)|0);
            let mult : number = (x >= 0)?(1):(-1);
            x = (<any>Math).fround(x * mult);
            x *= d;
            let a : number = (<number>((<any>Math).fround(x / d))|0);
            let b : number = (<number>((<any>Math).fround(x % d))|0);
            let b0 : string = Main.toRim(a).toString();
            let b1 : string = Main.mirrorStr(Main.toRim(javaemul.internal.IntegerHelper.parseInt(Main.mirrorStr("" + b))).toLowerCase());
            let pref : string = (mult > 0)?(""):("-");
            if(/* equals */(<any>((o1: any, o2: any) => { if(o1 && o1.equals) { return o1.equals(o2); } else { return o1 === o2; } })(b1,"n"))) {
                return pref + b0;
            } else {
                return pref + b0 + "." + b1;
            }
        }

        static toRim(x : number) : string {
            let result : string = Main.intToRimMap_$LI$().get(x);
            if(result == null) {
                result = Main._convertToRoman(x);
            }
            return result;
        }

        static fromRim(a : string) : number {
            if(!/* equals */(<any>((o1: any, o2: any) => { if(o1 && o1.equals) { return o1.equals(o2); } else { return o1 === o2; } })(a.toUpperCase(),a))) {
                return (2 / 0|0);
            }
            a = /* replaceAll */a.replace(new RegExp("\u041c", 'g'),"M");
            a = /* replaceAll */a.replace(new RegExp("\u041b", 'g'),"L");
            a = /* replaceAll */a.replace(new RegExp("\u0414", 'g'),"D");
            a = /* replaceAll */a.replace(new RegExp("\u041d", 'g'),"N");
            a = /* replaceAll */a.replace(new RegExp("\u0421", 'g'),"C");
            a = /* replaceAll */a.replace(new RegExp("\u0425", 'g'),"X");
            a = /* replaceAll */a.replace(new RegExp("\u0412", 'g'),"V");
            a = /* replaceAll */a.replace(new RegExp("\u041f", 'g'),"V");
            a = /* replaceAll */a.replace(new RegExp("\u0406", 'g'),"I");
            a = /* replaceAll */a.replace(new RegExp("\u0407", 'g'),"I");
            a = /* replaceAll */a.replace(new RegExp("\u00cf", 'g'),"I");
            let result : number = Main.rimToIntMap_$LI$().get(a);
            if(result == null) {
                result = Main._convertFromRoman(a);
            }
            return result;
        }

        static fromRimFloat(a : string) : number {
            let a2 : string[] = a.split("\\.");
            let b0 : string = null;
            let b1 : string = null;
            if(a2.length !== 2) {
                if(a2.length === 1) {
                    return <number>Main.fromRim(a);
                } else {
                    return javaemul.internal.FloatHelper.parseFloat(null);
                }
            }
            b0 = (<number>new Number(Main.fromRim(a2[0]))).toString();
            b1 = Main.mirrorStr((<number>new Number(Main.fromRim(Main.mirrorStr(/* equals */(<any>((o1: any, o2: any) => { if(o1 && o1.equals) { return o1.equals(o2); } else { return o1 === o2; } })(a2[1].toLowerCase(),a2[1]))?a2[1]:null).toUpperCase()))).toString());
            return javaemul.internal.FloatHelper.parseFloat(b0 + '.' + b1);
        }

        static mirrorStr(sourceStr : string) : string {
            let result : string = "";
            for(let i : number = 0; i < sourceStr.length; ++i) {{
                result += "" + sourceStr.charAt(sourceStr.length - 1 - i);
            };}
            return result;
        }

        static toArab(x : number) : string {
            return /* toString */(''+(x));
        }

        static toArabFloat(x : number) : string {
            return /* toString */(''+(x));
        }

        static fromArab(a : string) : number {
            let x : number = javaemul.internal.IntegerHelper.parseInt(a);
            return x;
        }

        static fromArabFloat(a : string) : number {
            let x : number = javaemul.internal.FloatHelper.parseFloat(a);
            return x;
        }

        static factorial(n : number) : number {
            if(n < 0) {
                return null;
            } else if(n === 0 || n === 1) {
                return 1;
            } else {
                return n * Main.factorial(n - 1);
            }
        }

        static _zStr(z : number) : string {
            let zStr : string;
            try {
                if(Main.isArab_$LI$()) {
                    zStr = Main.toArabFloat(z);
                } else {
                    zStr = Main.toRimFloat(z);
                }
            } catch(e3) {
                console.error(e3.message, e3);
                console.error("ERROR: result overflow or convertion error");
                zStr = null;
            };
            return zStr;
        }

        public static calc(input : string) : string {
            let zStr : string = null;
            input = /* replaceAll */input.replace(new RegExp("\u044c", 'g'),"b");
            input = /* replaceAll */input.replace(new RegExp("\u042c", 'g'),"b");
            let lr : string[] = Main.splitByFirstOperation(input);
            let a : string = lr[0];
            let b : string = lr[1];
            let operation : string = lr[2];
            let x : number = 0;
            let y : number = 0;
            let tildeCall : string = null;
            Main.isArab = true;
            try {
                try {
                    x = Main.fromArabFloat(a);
                } catch(e) {
                    x = Main.fromRimFloat(a);
                    Main.isArab = false;
                };
                if(/* equals */(<any>((o1: any, o2: any) => { if(o1 && o1.equals) { return o1.equals(o2); } else { return o1 === o2; } })(operation,"~"))) {
                    tildeCall = b;
                } else {
                    if(Main.isArab_$LI$()) {
                        y = Main.fromArabFloat(b);
                    } else {
                        y = Main.fromRimFloat(b);
                        if(/* equals */(<any>((o1: any, o2: any) => { if(o1 && o1.equals) { return o1.equals(o2); } else { return o1 === o2; } })(<number>new Number(0),Main._partialRound(y)))) {
                            y = 0.0;
                        } else if(/* equals */(<any>((o1: any, o2: any) => { if(o1 && o1.equals) { return o1.equals(o2); } else { return o1 === o2; } })(<number>new Number(1),Main._partialRound(y)))) {
                            y = 1.0;
                        }
                    }
                }
            } catch(e1) {
                console.error(e1.message, e1);
                console.error("ERROR: wrong input");
                x = -1;
                y = -1;
                zStr = null;
                return zStr.toString();
            };
            if(x < 0 || y < 0) {
                console.error("ERROR: unsupported input, negative or too big numbers");
                zStr = null;
            } else {
                let z : number = null;
                try {
                    if((/* equals */(<any>((o1: any, o2: any) => { if(o1 && o1.equals) { return o1.equals(o2); } else { return o1 === o2; } })(operation,Main.division)) || /* equals */(<any>((o1: any, o2: any) => { if(o1 && o1.equals) { return o1.equals(o2); } else { return o1 === o2; } })(operation,Main.remainding))) && (y === 0.0 || y === -0.0)) {
                        z = <number>((1 / 0|0));
                    } else {
                        switch((operation)) {
                        case "~" /* tilde */:
                            switch((tildeCall)) {
                            case "ABS":
                                z = (x >= 0)?(x):(-x);
                                zStr = Main._zStr(z);
                                break;
                            case "MINUS":
                                z = -x;
                                zStr = Main._zStr(z);
                                break;
                            case "FACTORIAL":
                                z = <number>Main.factorial((<number>x|0));
                                zStr = Main._zStr(z);
                                break;
                            case "TOARAB":
                                if(!Main.isArab_$LI$()) {
                                    z = Main.fromRimFloat(a);
                                    zStr = Main.toArabFloat(z);
                                } else {
                                    zStr = a;
                                }
                                break;
                            case "TOROMAN":
                                if(!Main.isArab_$LI$()) {
                                    zStr = a;
                                } else {
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
                            z = (<any>Math).fround(x + y);
                            break;
                        case "-" /* subtraction */:
                            z = (<any>Math).fround(x - y);
                            break;
                        case "^" /* exponentiation */:
                            z = Main.fastPow$float$float(x, y);
                            break;
                        case "*" /* multiplication */:
                            z = (<any>Math).fround(x * y);
                            break;
                        case "/" /* division */:
                            z = (<any>Math).fround(x / y);
                            break;
                        case "%" /* remainding */:
                            z = (<any>Math).fround(x % y);
                            break;
                        case "b" /* logarithm */:
                            z = Main.logY(x, y);
                            break;
                        default:
                            z = <number>((1 / 0|0));
                        }
                    }
                } catch(e2) {
                    console.error("ERROR: unsupported operation or error within operation");
                    z = null;
                };
                if(z != null) {
                    zStr = Main._zStr(z);
                } else {
                    zStr = null;
                }
            }
            return zStr.toString();
        }

        public static main(args : string[]) {
            let input : string = "X-II";
            console.info("Input: " + input);
            console.info("Output: ");
            try {
                console.info("" + Main.calc(input));
            } catch(e) {
                console.info();
                console.error("Exception: ");
                console.error(e.message, e);
            };
        }
    }
    Main["__class"] = "quickstart.Main";


    export namespace Main {

        export class Main$0 extends java.util.LinkedHashMap<string, number> {
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
        Main$0["__interfaces"] = ["java.lang.Cloneable","java.util.Map","java.io.Serializable"];



        export class Main$1 extends java.util.HashMap<string, number> {
            constructor() {
                super();
                (() => {
                    this.put("N", 0);
                    for(let x : number = 1; x <= 10000; ++x) {{
                        this.put(Main._convertToRoman(x), x);
                    };}
                    for(let y : number = 1; y <= 1000; ++y) {{
                        this.put(Main._convertToRoman(y * 1000), y * 1000);
                    };}
                })();
            }
        }
        Main$1["__interfaces"] = ["java.lang.Cloneable","java.util.Map","java.io.Serializable"];


    }

}
namespace quickstart {
    /**
     * This class is used within the webapp/index.html file.
     * @class
     */
    export class QuickStart {
        public static main(args : string[]) {
            let l : java.util.List<string> = <any>(new java.util.ArrayList<any>());
            l.add("Hello");
            l.add("world");
            let a : Array<string> = <any>(new Array<any>());
            a.push("Hello", "world");
            $("#target").text(l.toString());
            alert(a.toString());
        }
    }
    QuickStart["__class"] = "quickstart.QuickStart";

}


quickstart.Main.intToRimMap_$LI$();

quickstart.Main.rimToIntMap_$LI$();

quickstart.Main.romanSourceMap_$LI$();

quickstart.Main.isArab_$LI$();

quickstart.Main.accuracyMulter_$LI$();

quickstart.Main.accuracy_$LI$();

quickstart.Main.__static_initialize();

quickstart.Main.main(null);

quickstart.QuickStart.main(null);
