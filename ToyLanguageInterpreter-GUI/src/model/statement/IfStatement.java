package model.statement;

import exceptions.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.ADT.StackInterface;
import model.expression.ExpressionInterface;
import model.type.BoolType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.ValueInterface;

public class IfStatement implements StatementInterface {
    private final ExpressionInterface expression;
    private final StatementInterface thenStatement;
    private final StatementInterface elseStatement;

    public IfStatement(ExpressionInterface expression, StatementInterface thenStatement, StatementInterface elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<Integer, ValueInterface> heap = state.getHeap();

        ValueInterface expressionValue = this.expression.evaluate(symbolTable, heap);

        if (expressionValue.getType().equals(new BoolType())) {
            if (((BoolValue) expressionValue).getValue())   // == true
                stack.push(thenStatement);
            else
                stack.push(elseStatement);
        }
        else
            throw new InvalidTypeException("Conditional expression is not a boolean!");
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        if (expression.typeCheck(typeEnvironment).equals(new BoolType())) {
            thenStatement.typeCheck(typeEnvironment.clone());
            elseStatement.typeCheck(typeEnvironment.clone());
            return typeEnvironment;
        }
        else
            throw new InvalidTypeException("The condition of IfStatement is not a boolean!");
    }

    @Override
    public String toString() {
        return "IF (" + expression.toString() + ") THEN (" + thenStatement.toString() + ") ELSE (" + elseStatement.toString() + ")";
    }
}
