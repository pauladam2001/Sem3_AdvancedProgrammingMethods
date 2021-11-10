package model.expression;

import exceptions.InvalidOperatorException;
import exceptions.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.ValueInterface;

public class LogicalExpression implements ExpressionInterface {
    private final ExpressionInterface firstExpression;
    private final ExpressionInterface secondExpression;
    private final String operator;

    public LogicalExpression(ExpressionInterface firstExpression, ExpressionInterface secondExpression, String operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, DictionaryInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface firstValue, secondValue;
        firstValue = firstExpression.evaluate(symbolTable, heap);
        if (!firstValue.getType().equals(new BoolType()))
            throw new InvalidTypeException("First operand is not a boolean!");
        secondValue = secondExpression.evaluate(symbolTable, heap);
        if (!secondValue.getType().equals(new BoolType()))
            throw new InvalidTypeException("Second operand is not a boolean!");

        boolean firstBool = ((BoolValue)firstValue).getValue();
        boolean secondBool = ((BoolValue)secondValue).getValue();

        if (this.operator.equals("&&")) {
            return new BoolValue(firstBool && secondBool);
        }
        if (this.operator.equals("||")) {
            return new BoolValue(firstBool || secondBool);
        }
        else
            throw new InvalidOperatorException("Invalid operator!");
    }

    @Override
    public String toString() {
        return firstExpression.toString() + " " + operator + " " + secondExpression.toString();
    }
}
