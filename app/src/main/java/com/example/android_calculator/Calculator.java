package com.example.android_calculator;

import java.io.Serializable;

public class Calculator implements Serializable {
    private String operator1;
    private String operator2;

    protected enum Action {
        Plus,
        Minus,
        Multiply,
        Division
    }

    private Action currentAction;

    public Calculator() {
        operator1 = "0";
        operator2 = null;
        currentAction = null;
    }

    public void choiceNumber(int num) {
        if (!"0".equals(operator1)) {
            operator1 = operator1.concat(Integer.toString(num));
        } else if (num != 0) {
            operator1 = Integer.toString(num);
        }
    }

    public void choiceAction(Action action) {
        currentAction = action;
        operator2 = operator1;
    }

    public void choiceEqual() {
        double op1 = Double.parseDouble(operator1);
        double op2;
        if (operator2 != null) {
            op2 = Double.parseDouble(operator2);
        } else {
            return;
        }

        switch (currentAction) {
            case Plus: {
                op1 += op2;
                break;
            }
            case Minus: {
                op1 -= op2;
                break;
            }
            case Multiply: {
                op1 *= op2;
                break;
            }
            case Division: {
                if (op2 != 0) {
                    op1 /= op2;
                } else {
                    throw new ArithmeticException("Division by zero/Деление на 0");
                }
                break;
            }
        }
        operator1 = Double.toString(op1);
    }

    public void choiceCancel() {
        operator1 = "";
        operator2 = null;
        currentAction = null;
    }

    public void choiceDot() {
        if (operator1.indexOf('.') != -1) {
            operator1 = operator1.concat(".");
        }
    }

    public String getOperator1InTextView(int length) {
        return operator1.substring(0, length);
    }
}
