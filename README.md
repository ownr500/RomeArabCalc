# RomeArabCalc

Numbers calculator: Roman and Arabic

You can calculate on your prefered numeral system separately (not simultaneously): Roman numbers or Arabic decimal numbers.

## Usage

```
$ git clone https://github.com/proxy-m/RomeArabCalc
$ cd RomeArabCalc
$ cd ./src/main/java
$ javac quickstart/RomeArabCalc.java
$ javac quickstart/RomeArabCalcFloat.java

$ java -cp . quickstart.RomeArabCalc 'X-II'
$ java -cp . quickstart.RomeArabCalc '10-2'
$ java -cp . quickstart.RomeArabCalc 'X+II'
$ java -cp . quickstart.RomeArabCalc 'X*II'
$ java -cp . quickstart.RomeArabCalc 'III^II' # pow(III, II)
$ java -cp . quickstart.RomeArabCalc '8 b 2' # log2(8)
$ java -cp . quickstart.RomeArabCalc 'X̅C̅MMDXXI ~ TOARAB'
$ java -cp . quickstart.RomeArabCalc '347 ~ TOARAB'
$ java -cp . quickstart.RomeArabCalc '19 ~ TOROMAN'
$ java -cp . quickstart.RomeArabCalc 'XIX ~ TOROMAN'
$ java -cp . quickstart.RomeArabCalc '92521 ~ TOROMAN'
$ java -cp . quickstart.RomeArabCalcFloat 103993.0 / 33102
$ java -cp . quickstart.RomeArabCalcFloat '3.1 ~ TOROMAN'
$ java -cp . quickstart.RomeArabCalcFloat '3.14 ~ TOROMAN'
$ java -cp . quickstart.RomeArabCalcFloat '3.141 ~ TOROMAN'
$ java -cp . quickstart.RomeArabCalcFloat '3.1415 ~ TOROMAN'
$ java -cp . quickstart.RomeArabCalcFloat '3.14159 ~ TOROMAN'
$ java -cp . quickstart.RomeArabCalcFloat 'IX/II'
$ java -cp . quickstart.RomeArabCalc 'IX/II'
$ java -cp . quickstart.RomeArabCalc 'IX%II'
$ java -cp . quickstart.RomeArabCalc 'II / III'
$ java -cp . quickstart.RomeArabCalcFloat 'II.iii * VII.iivx' # 2.3 * 7.71
$ java -cp . quickstart.RomeArabCalcFloat 'I.ii + VI.x' # 1.2 + 6.01
$ java -cp . quickstart.RomeArabCalcFloat '5.2 + 4.8'
$ java -cp . quickstart.RomeArabCalcFloat '5 - 8'
```

## Prerequisites

Java 8+ JDK

