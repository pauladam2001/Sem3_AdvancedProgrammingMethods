package model.statement;

import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.ADT.StackInterface;
import model.expression.ExpressionInterface;
import model.type.TypeInterface;
import model.value.ValueInterface;

public class AssignmentStatement implements StatementInterface {
    private final String variableName;
    private final ExpressionInterface expression;

    public AssignmentStatement(String variableName, ExpressionInterface expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<Integer, ValueInterface> heap = state.getHeap();

        if (symbolTable.isDefined(variableName)) {
            ValueInterface value = expression.evaluate(symbolTable, heap);
            TypeInterface typeId = (symbolTable.getValue(variableName)).getType();
            if (value.getType().equals(typeId))
                symbolTable.update(variableName, value);
            else
                throw new InvalidTypeException("Declared type of variable " + variableName + " and type of the assigned expression do not match!");
        }
        else
            throw new VariableNotDefinedException("The used variable " + variableName + " was not declared before");
        return state;
    }

    @Override
    public String toString() {
        return variableName + "=" + expression.toString();
    }
}
