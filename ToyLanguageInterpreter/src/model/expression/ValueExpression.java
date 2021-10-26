package model.expression;

import model.ADT.DictionaryInterface;
import model.value.ValueInterface;

public class ValueExpression implements ExpressionInterface {
    private final ValueInterface value;

    public ValueExpression(ValueInterface value) {
        this.value = value;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable) throws Exception {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
