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

## Rules of Roman numeral system

- As of today following characters may be used in the Roman numeral system:

    -   I = 1; 
    -   V = 5; 
    -   X = 10;
    -   L = 50; 
    -   C = 100; 
    -   D = 500;
    -   M = 1000.

#### All integers from 1 to 3999 are represented using the digits and numbers from above with following rules:

-   if the larger digit comes before the smaller one, they add up:

    -   VI = 5 + 1 = 6;
    -   XV = 10 + 5 = 15;
    -   LX = 50 + 10 = 60;
    -   CL = 100 + 50 = 150;

-   if the smaller digit comes before the larger one (in which case it cannot be repeated), then the smaller one is subtracted from the larger one; only numbers representing 1 or powers of 10 can be subtracted; only the digit that is next in the number series to the one to be deducted may be reduced:

    -   IV = 5 - 1 = 4;
    -   IX = 10 - 1 = 9;
    -   XL = 50 - 10 = 40;
    -   XC = 100 - 10 = 90;

-   digits V, L, D cannot be repeated; digits I, X, C, M may be repeated not more than 3 times in a row:

    -   VIII = 8;
    -   LXXX = 80;
    -   DCCC = 800;
    -   MMMD = 3500.

-   A bar over a number increases its value by 1,000 times:

    -   V̅ = 5000;
    -   X̅ = 10000;
    -   L̅ = 50000;
    -   C̅ = 100000;
    -   D̅ = 500000;
    -   M̅ = 1000000.

- Basic Roman numerals

    -   1 = I\
    2 = II\
    3 = III\
    4 = IV\
    5 = V\
    6 = VI\
    7 = VII\
    8 = VIII\
    9 = IX\
    10 = X\
    20 = XX\
    30 = XXX\
    40 = XL\
    50 = L\
    60 = LX\
    70 = LXX\
    80 = LXXX\
    90 = XC\
    100 = C\
    200 = CC\
    300 = CCC\
    400 = CD\
    500 = D\
    600 = DC\
    700 = DCC\
    800 = DCCC\
    900 = CM\
    1 000 = M\
    2 000 = MM\
    3 000 = MMM\
    4 000 = MV̅ / I̅V̅\
    5 000 = V̅\
    6 000 = V̅M\
    7 000 = V̅MM\
    8 000 = V̅MMM\
    9 000 = MX̅ / I̅X̅\
    10 000 = X̅\
    20 000 = X̅X̅\
    30 000 = X̅X̅X̅\
    40 000 = X̅L̅\
    50 000 = L̅\
    60 000 = L̅X̅\
    70 000 = L̅X̅X̅\
    80 000 = L̅X̅X̅X̅\
    90 000 = X̅C̅\
    100 000 = C̅\
    200 000 = C̅C̅\
    300 000 = C̅C̅C̅\
    400 000 = C̅D̅\
    500 000 = D̅\
    600 000 = D̅C̅\
    700 000 = D̅C̅C̅\
    800 000 = D̅C̅C̅C̅\
    900 000 = C̅M̅\
    1 000 000 = M̅
