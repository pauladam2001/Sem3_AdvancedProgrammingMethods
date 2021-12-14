package model.statement;

import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.ADT.DictionaryInterface;
import model.ADT.MyHeap;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.ReferenceType;
import model.type.TypeInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;

public class HeapAllocationStatement implements StatementInterface {
    private final String variableName;
    private final ExpressionInterface expression;

    public HeapAllocationStatement(String variableName, ExpressionInterface expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<Integer, ValueInterface> heap = state.getHeap();

        if (!symbolTable.isDefined(variableName))
            throw new VariableNotDefinedException(variableName + " is not defined in the symbol table!");

        if (!(symbolTable.getValue(variableName).getType() instanceof ReferenceType))
            throw new InvalidTypeException("Associated type of " + variableName + " should be ReferenceType!");

        ValueInterface variableValue = symbolTable.getValue(variableName);
        ValueInterface expressionValue = expression.evaluate(symbolTable, heap);
        TypeInterface expressionType = expressionValue.getType();
        TypeInterface referencedType = ((ReferenceValue)variableValue).getLocationType();

        if (!referencedType.equals(expressionType))
            throw new InvalidTypeException("Expression can't be evaluated to " + referencedType);

        int newPositionInHeap = ((MyHeap<Integer, ValueInterface>)heap).getFirstFreeLocation();
        heap.add(newPositionInHeap, expressionValue);

        symbolTable.update(variableName, new ReferenceValue(newPositionInHeap, referencedType));

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        if (typeEnvironment.getValue(variableName).equals(new ReferenceType(expression.typeCheck(typeEnvironment))))
            return typeEnvironment;
        else
            throw new InvalidTypeException("HeapAllocationStatement: right hand side and left hand side have different types!");
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() + ")";
    }
}
