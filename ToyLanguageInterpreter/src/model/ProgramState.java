package model;

import model.ADT.DictionaryInterface;
import model.ADT.ListInterface;
import model.ADT.StackInterface;
import model.statement.StatementInterface;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;

public class ProgramState {
    private StackInterface<StatementInterface> executionStack;
    private DictionaryInterface<String, ValueInterface> symbolTable;
    private ListInterface<ValueInterface> output;
    private DictionaryInterface<StringValue, BufferedReader> fileTable;
    private DictionaryInterface<Integer, ValueInterface> heap;

//    private StatementInterface originalProgram;   // optional field, but good to have

    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String, ValueInterface> symbolTable,
                        ListInterface<ValueInterface> output, StatementInterface program, DictionaryInterface<StringValue, BufferedReader> fileTable,
                        DictionaryInterface<Integer, ValueInterface> heap) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
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

    public DictionaryInterface<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public DictionaryInterface<Integer, ValueInterface> getHeap() {
        return heap;
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

    public void setFileTable(DictionaryInterface<StringValue, BufferedReader> filetbl) {
        fileTable = filetbl;
    }

    public String toString() {
//        return executionStack.toString() + "\n" + symbolTable.toString() + "\n" + output.toString() + "\n";
        return "\nExecution stack:\n" + executionStack.toString() + "Symbol table:\n" + symbolTable.toString() +
                "Output:\n" +output.toString() + "File table:\n" + fileTable.toString() + "Heap:\n" +
                heap.toString() + "\n";
    }
}
