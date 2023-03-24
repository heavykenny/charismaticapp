package com.example.charismaticapp.logics;

public class CalculatorController {
    private final Number a, b;

    public CalculatorController(String firstNumber, String secondNumber) {
        this.a = parseNumber(firstNumber);
        this.b = parseNumber(secondNumber);
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
