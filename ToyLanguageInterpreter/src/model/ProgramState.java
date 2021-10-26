package model;

import model.ADT.DictionaryInterface;
import model.ADT.ListInterface;
import model.ADT.StackInterface;
import model.statement.StatementInterface;
import model.value.ValueInterface;

public class ProgramState {
    private StackInterface<StatementInterface> executionStack;
    private DictionaryInterface<String, ValueInterface> symbolTable;
    private ListInterface<ValueInterface> output;

//    private StatementInterface originalProgram;   // optional field, but good to have

    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String, ValueInterface> symbolTable,
                        ListInterface<ValueInterface> output, StatementInterface program) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
//        originalProgram = program.deepcopy();  // ?
    }

    public StackInterface<StatementInterface> getExecutionStack() {
        return executionStack;
    }

    public DictionaryInterface<String, ValueInterface> getSymbolTable() {
        return symbolTable;
    }

    public ListInterface<ValueInterface> getOutput() {
        return output;
    }

    public void setExecutionStack(StackInterface<StatementInterface> stack) {
        executionStack = stack;
    }

    public void setSymbolTable(DictionaryInterface<String, ValueInterface> symtbl) {
        symbolTable = symtbl;
    }

    public void setOutput(ListInterface<ValueInterface> ot) {
        output = ot;
    }

    public String toString() {
//        return executionStack.toString() + "\n" + symbolTable.toString() + "\n" + output.toString() + "\n";
        return executionStack.toString() + symbolTable.toString() + output.toString() + "\n";
    }
}
