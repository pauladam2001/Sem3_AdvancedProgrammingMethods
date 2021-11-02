package model.expression;

import exceptions.InvalidOperatorException;
import exceptions.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.ValueInterface;

public class RelationalExpression implements ExpressionInterface {
    private final ExpressionInterface firstExpression;
    private final ExpressionInterface secondExpression;
    private final String operator;

    public RelationalExpression(ExpressionInterface firstExpression, ExpressionInterface secondExpression, String operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable) throws Exception {
        ValueInterface firstValue, secondValue;
        firstValue = firstExpression.evaluate(symbolTable);
        secondValue = secondExpression.evaluate(symbolTable);

        if (!firstValue.getType().equals(new IntType()))
            throw new InvalidTypeException("First operand is not an integer!");
        secondValue = secondExpression.evaluate(symbolTable);
        if (!secondValue.getType().equals(new IntType()))
            throw new InvalidTypeException("Second operand is not an integer!");

        int firstint = ((IntValue)firstValue).getValue();
        int secondInt = ((IntValue)secondValue).getValue();

        if (operator.equals("<"))
            return new BoolValue(firstint < secondInt);
        if (operator.equals("<="))
            return new BoolValue(firstint <= secondInt);
        if (operator.equals("=="))
            return new BoolValue(firstint == secondInt);
        if (operator.equals("!="))
            return new BoolValue(firstint != secondInt);
        if (operator.equals(">"))
            return new BoolValue(firstint > secondInt);
        if (operator.equals(">="))
            return new BoolValue(firstint >= secondInt);
        else
            throw new InvalidOperatorException("Invalid operator!");
    }

    @Override
    public String toString() {
        return firstExpression.toString() + " " + operator + " " + secondExpression.toString();
    }
}
