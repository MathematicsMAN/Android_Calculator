package com.example.android_calculator;

import java.io.Serializable;

public class Calculator implements Serializable {
    private final int maximumLengthOperatorSymbols = 12;
    private final String dot = ".";
    private String operator1;
    private String operator2;
    private String operator1ToView;

    private LastChoice lastChoice;
    private Action currentAction;

    public Calculator() {
        choiceCancel();
    }

    public void setOperator1(String operator1) {
        this.operator1 = operator1;
    }

    public void choiceCancel() {
        operator1 = "0";
        operator2 = null;
        currentAction = null;
        lastChoice = null;
        operator1ToView = null;
    }

    public void choiceNumber(int num) {
        if (operator1.length() >= maximumLengthOperatorSymbols) {
            return;
        }

        if (lastChoice == LastChoice.EQUAL) {
            operator1 = "0";
            operator2 = null;
        }

        if (!"0".equals(operator1)) {
            operator1 = operator1.concat(Integer.toString(num));
        } else if (num != 0) {
            operator1 = Integer.toString(num);
        }

        lastChoice = LastChoice.NUMBER;
    }

    public void choiceAction(Action action) {
        if (operator2 != null && lastChoice == LastChoice.NUMBER) {
            choiceEqual();
            operator2 = null;
            operator1ToView = compression(operator1);
        }
        currentAction = action;
        if (lastChoice != LastChoice.ACTION) {
            operator2 = operator1;
            operator1 = "0";
            operator1ToView = compression(operator2);
            lastChoice = LastChoice.ACTION;
        }
    }

    public void choiceEqual() {
        double op1;
        double op2;

        try {
            if (operator2 != null) {
                if (lastChoice == LastChoice.ACTION) {
                    op1 = op2 = Double.parseDouble(operator2);
                } else {
                    op1 = Double.parseDouble(operator1);
                    op2 = Double.parseDouble(operator2);
                }
            } else {
                return;
            }
        } catch (NullPointerException | NumberFormatException  ex){
            throw new ArithmeticException("Division by zero/Деление на 0");

        }

        switch (currentAction) {
            case PLUS: {
                op1 += op2;
                break;
            }
            case MINUS: {
                op1 = lastChoice == LastChoice.EQUAL ? (op1 - op2) : (op2 - op1);
                break;
            }
            case MULTIPLY: {
                op1 *= op2;
                break;
            }
            case DIVISION: {
                try {
                    op1 = lastChoice == LastChoice.EQUAL ? (op1 / op2) : (op2 / op1);
                } catch (ArithmeticException ex) {
                    throw new ArithmeticException("Division by zero/Деление на 0");
                }
                break;
            }
        }

        if (Math.abs(op1) >= Math.pow(10.0, (double) maximumLengthOperatorSymbols)) {
            throw new ArithmeticException("Overflow result/Переполнение разрядности");
        }

        operator1 = String.format("%.10f", op1).replace(',', dot.charAt(0));
        lastChoice = LastChoice.EQUAL;
    }

    public void choiceDot() {
        if (lastChoice == LastChoice.EQUAL || lastChoice == LastChoice.ACTION) {
            operator1 = "0".concat(dot);
            lastChoice = LastChoice.DOT;
        } else if (!operator1.contains(dot)) {
            operator1 = operator1.concat(dot);
            lastChoice = LastChoice.DOT;
        }
    }

    public String getOperator1() {
        String result;
        if (operator1ToView != null) {
            result = operator1ToView;
            operator1ToView = null;
        } else {
            result = operator1 = compression(operator1);
        }
        return result;
    }

    private String compression(String doubleNumberFormat) {
        StringBuffer result;

        if (doubleNumberFormat.length() < maximumLengthOperatorSymbols) {
            result = new StringBuffer(doubleNumberFormat);
        } else {
            result = new StringBuffer(doubleNumberFormat.substring(0, maximumLengthOperatorSymbols));
        }

        if (doubleNumberFormat.contains(dot)) {
            while (result.charAt(result.length() - 1) == '0' && lastChoice != LastChoice.NUMBER) {
                result.delete(result.length() - 1, result.length());
            }
            if (dot.equals(String.valueOf(result.charAt(result.length() - 1))) && lastChoice != LastChoice.DOT) {
                result.delete(result.length() - 1, result.length());
            }
        }

        return result.toString();
    }

    private enum LastChoice {
        EQUAL,
        DOT,
        NUMBER,
        ACTION
    }

    protected enum Action {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVISION
    }
}
