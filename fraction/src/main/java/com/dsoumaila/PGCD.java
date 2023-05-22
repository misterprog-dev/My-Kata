package com.dsoumaila;

public class PGCD {
    public static Integer pgcd(int firstNumber, int secondNumber) {
        if (firstNumber == 0 && secondNumber == 0) return null;

        if ((firstNumber == 0 && secondNumber != 0) || (firstNumber != 0 && secondNumber == 0)) {
            return firstNumber + secondNumber;
        }

        Integer pgcd = 1;
        for (int i = 1; i<=firstNumber && i<=secondNumber; i++) {
            if (firstNumber % i == 0 && secondNumber % i == 0)
                pgcd = i;
        }

        return pgcd;
    }
}
