package model.statement;

import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.ReferenceType;
import model.type.TypeInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;

public class HeapWritingStatement implements StatementInterface {
    private final String variableName;
    private final ExpressionInterface expression;

    public HeapWritingStatement(String variableName, ExpressionInterface expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<Integer, ValueInterface> heap = state.getHeap();

        if (!symbolTable.isDefined(variableName))
            throw new VariableNotDefinedException(variableName + " is not defined in the symbol table!");

        ValueInterface variableValue = symbolTable.getValue(variableName);
        int positionInHeap = ((ReferenceValue) variableValue).getHeapAddress();
        if (!heap.isDefined(positionInHeap))
            throw new VariableNotDefinedException("Undefined variable at address " + positionInHeap);

        ValueInterface expressionValue = expression.evaluate(symbolTable, heap);
        TypeInterface expressionType = expressionValue.getType();
        ValueInterface referencedValue = heap.getValue(positionInHeap);
        TypeInterface referencedType = referencedValue.getType();

        if (!expressionType.equals(referencedType))
            throw new InvalidTypeException("The referenced type of " + variableName + " doesn't match the expression!");

        heap.update(positionInHeap, expressionValue);

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        TypeInterface expType = expression.typeCheck(typeEnvironment);
        if (typeEnvironment.getValue(variableName).equals(new ReferenceType(expType)))
            return typeEnvironment;
        else
            throw new InvalidTypeException("HeapWritingStatement: right hand side and left hand side have different types!");
    }

    @Override
    public String toString() {
        return "(" + variableName + ") = " + expression.toString() + ";";
    }
}
