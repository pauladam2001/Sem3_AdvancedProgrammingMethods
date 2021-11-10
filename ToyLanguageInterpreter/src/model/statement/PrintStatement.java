package model.statement;

import model.*;
import model.ADT.DictionaryInterface;
import model.ADT.ListInterface;
import model.expression.ExpressionInterface;
import model.value.ValueInterface;

public class PrintStatement implements StatementInterface {
    private final ExpressionInterface expression;

    public PrintStatement(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        ListInterface<ValueInterface> output = state.getOutput();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<Integer, ValueInterface> heap = state.getHeap();
        output.addToEnd(this.expression.evaluate(symbolTable, heap));
        return state;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
