package com.example.android_calculator;

import java.io.Serializable;

public class Calculator implements Serializable {
    private final int maximumLengthOperatorSymbols = 12;
    private final String dot = ".";
    private String operator1;
    private String operator2;
    private String operator1ToView;

    private enum LastChoice {
        Equal,
        Dot,
        Number,
        Action
    }

    private LastChoice lastChoice;

    protected enum Action {
        Plus,
        Minus,
        Multiply,
        Division
    }

    private Action currentAction;

    public Calculator() {
        choiceCancel();
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

        if (lastChoice == LastChoice.Equal) {
            operator1 = "0";
            operator2 = null;
        }

        if (!"0".equals(operator1)) {
            operator1 = operator1.concat(Integer.toString(num));
        } else if (num != 0) {
            operator1 = Integer.toString(num);
        }

        lastChoice = LastChoice.Number;
    }

    public void choiceAction(Action action) {
        if (operator2 != null && lastChoice == LastChoice.Number) {
            choiceEqual();
            operator2 = null;
            operator1ToView = compression(operator1);
        }
        currentAction = action;
        if (lastChoice != LastChoice.Action) {
            operator2 = operator1;
            operator1 = "0";
            operator1ToView = compression(operator2);
            lastChoice = LastChoice.Action;
        }
    }

    public void choiceEqual() {
        double op1;
        double op2;

        if (operator2 != null) {
            if (lastChoice == LastChoice.Action) {
                op1 = op2 = Double.parseDouble(operator2);
            } else {
                op1 = Double.parseDouble(operator1);
                op2 = Double.parseDouble(operator2);
            }
        } else {
            return;
        }

        switch (currentAction) {
            case Plus: {
                op1 += op2;
                break;
            }
            case Minus: {
                op1 = lastChoice == LastChoice.Equal ? (op1 - op2) : (op2 - op1);
                break;
            }
            case Multiply: {
                op1 *= op2;
                break;
            }
            case Division: {
                try {
                    op1 = lastChoice == LastChoice.Equal ? (op1 / op2) : (op2 / op1);
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
        lastChoice = LastChoice.Equal;
    }

    public void choiceDot() {
        if (lastChoice == LastChoice.Equal || lastChoice == LastChoice.Action) {
            operator1 = "0".concat(dot);
            lastChoice = LastChoice.Dot;
        } else if (!operator1.contains(dot)) {
            operator1 = operator1.concat(dot);
            lastChoice = LastChoice.Dot;
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
            while (result.charAt(result.length() - 1) == '0' && lastChoice != LastChoice.Number) {
                result.delete(result.length() - 1, result.length());
            }
            if (dot.equals(String.valueOf(result.charAt(result.length() - 1))) && lastChoice != LastChoice.Dot) {
                result.delete(result.length() - 1, result.length());
            }
        }

        return result.toString();
    }
}
