package model.expression;

import exceptions.VariableNotDefinedException;
import model.ADT.DictionaryInterface;
import model.value.ValueInterface;

public class VariableExpression implements ExpressionInterface {
    private final String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, DictionaryInterface<Integer, ValueInterface> heap) throws Exception {
        if (!symbolTable.isDefined(variableName))
            throw new VariableNotDefinedException("Variable " + variableName + " is not defined!");
        return symbolTable.getValue(variableName);
    }

    @Override
    public String toString() {
        return variableName;
    }
}
