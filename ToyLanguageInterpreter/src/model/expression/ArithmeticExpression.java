package model.expression;

import exceptions.DivisionByZeroException;
import exceptions.InvalidOperatorException;
import exceptions.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.type.IntType;
import model.value.IntValue;
import model.value.ValueInterface;

public class ArithmeticExpression implements ExpressionInterface {
    private final ExpressionInterface firstExpression;
    private final ExpressionInterface secondExpression;
    private final String operator;

    public ArithmeticExpression(String operator, ExpressionInterface firstExpression, ExpressionInterface secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, DictionaryInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface firstValue, secondValue;
        firstValue = firstExpression.evaluate(symbolTable, heap);
        if (!firstValue.getType().equals(new IntType()))
            throw new InvalidTypeException("First operand is not an integer!");
        secondValue = secondExpression.evaluate(symbolTable, heap);
        if (!secondValue.getType().equals(new IntType()))
            throw new InvalidTypeException("Second operand is not an integer!");

        int firstInt = ((IntValue)firstValue).getValue();
        int secondInt = ((IntValue)secondValue).getValue();

        if (this.operator.equals("+")) {
            return new IntValue(firstInt + secondInt);
        }
        if (this.operator.equals("-")) {
            return new IntValue(firstInt - secondInt);
        }
        if (this.operator.equals("*")) {
            return new IntValue(firstInt * secondInt);
        }
        if (this.operator.equals("/")) {
            if (secondInt == 0)
                throw new DivisionByZeroException("Division by zero!");
            return new IntValue(firstInt / secondInt);
        } else {
            throw new InvalidOperatorException("Invalid operator!");
        }
    }

    @Override
    public String toString() {
        return firstExpression.toString() + " " + operator + " " + secondExpression.toString();
    }
}
