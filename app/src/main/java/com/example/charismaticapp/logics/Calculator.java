package com.example.charismaticapp.logics;

public class Calculator {
    private final Number a, b;

    public Calculator(String firstNumber, String secondNumber) {
        this.a = parseNumber(firstNumber);
        this.b = parseNumber(secondNumber);

        System.out.println(a);
        System.out.println(b);
    }

    public Number add() {
        return a.doubleValue() + b.doubleValue();
    }

    public Number minus() {
        return a.doubleValue() - b.doubleValue();
    }

    public Number divide() {
        return a.doubleValue() / b.doubleValue();
    }

    public Number multiply() {
        return a.doubleValue() * b.doubleValue();
    }

    private Number parseNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return Double.parseDouble(number);
        }
    }
}
