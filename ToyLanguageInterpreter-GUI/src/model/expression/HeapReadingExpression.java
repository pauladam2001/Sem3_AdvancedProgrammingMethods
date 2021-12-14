package model.expression;

import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.ADT.DictionaryInterface;
import model.type.ReferenceType;
import model.type.TypeInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;

public class HeapReadingExpression implements ExpressionInterface {
    private final ExpressionInterface expression;

    public HeapReadingExpression(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, DictionaryInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface expressionValue = expression.evaluate(symbolTable, heap);

        int heapAddress = ((ReferenceValue) expressionValue).getHeapAddress();
        if (!heap.isDefined(heapAddress))
            throw new VariableNotDefinedException("Undefined variable at address " + heapAddress);
        return heap.getValue(heapAddress);
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        TypeInterface type = expression.typeCheck(typeEnvironment);
        if (type instanceof ReferenceType) {
            ReferenceType refType = (ReferenceType) type;
            return refType.getInnerType();
        } else
            throw new InvalidTypeException("The HeapReading expression is not a Reference Type!");
    }

    @Override
    public String toString() {
        return "read(" + expression.toString() + ")";
    }
}
