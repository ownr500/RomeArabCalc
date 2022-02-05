# RomeArabCalc

Numbers calculator: Roman and Arabic

You can calculate on your prefered numeral system separately (not simultaneously): Roman numbers or Arabic decimal numbers.

## Usage

```
$ git clone https://github.com/proxy-m/RomeArabCalc
$ cd RomeArabCalc
$ cd ./src/main/java
$ javac quickstart/Main.java

$ java -cp . quickstart.Main 'X-II'
$ java -cp . quickstart.Main '10-2'
$ java -cp . quickstart.Main 'X+II'
$ java -cp . quickstart.Main 'X*II'
$ java -cp . quickstart.Main 'III^II' # pow(III, II)
$ java -cp . quickstart.Main '8 b 2' # log2(8)
$ java -cp . quickstart.Main 'X̅C̅MMDXXI ~ TOARAB'
$ java -cp . quickstart.Main '347 ~ TOARAB'
$ java -cp . quickstart.Main '19 ~ TOROMAN'
$ java -cp . quickstart.Main 'XIX ~ TOROMAN'
$ java -cp . quickstart.Main '92521 ~ TOROMAN'
$ java -cp . quickstart.Main '3.1 ~ TOROMAN'
$ java -cp . quickstart.Main '3.14 ~ TOROMAN'
$ java -cp . quickstart.Main '3.141 ~ TOROMAN'
$ java -cp . quickstart.Main '3.1415 ~ TOROMAN'
$ java -cp . quickstart.Main 'IX/II'
$ java -cp . quickstart.Main 'IX%II'
$ java -cp . quickstart.Main 'II / III'
$ java -cp . quickstart.Main 'II.iii * VII.iivx' # 2.3 * 7.71
$ java -cp . quickstart.Main 'I.ii + VI.x' # 1.2 + 6.01
$ java -cp . quickstart.Main '5.2 + 4.8'
$ java -cp . quickstart.Main '5 - 8'
```

## Prerequisites

Java 8+ JDK

