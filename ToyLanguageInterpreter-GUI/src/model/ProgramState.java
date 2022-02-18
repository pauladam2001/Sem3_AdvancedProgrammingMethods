package model;

import exceptions.EmptyADTException;
import model.ADT.DictionaryInterface;
import model.ADT.LatchTableInterface;
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
    private LatchTableInterface<Integer, Integer> latchTable;
    private static int globalThreadCount = 1;
    private int threadID;

    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String, ValueInterface> symbolTable,
                        ListInterface<ValueInterface> output, StatementInterface program, DictionaryInterface<StringValue, BufferedReader> fileTable,
                        DictionaryInterface<Integer, ValueInterface> heap,
                        LatchTableInterface<Integer, Integer> latchTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.latchTable = latchTable;
        executionStack.push(program);
        threadID = ProgramState.manageThreadID();           // OR I put one more parameter, newID, in constructor
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

    public LatchTableInterface<Integer, Integer> getLatchTable() {
        return latchTable;
    }

    public int getThreadID() {
        return threadID;
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

    public void setHeap(DictionaryInterface<Integer, ValueInterface> heap) {
        this.heap = heap;
    }

    public static synchronized int manageThreadID() {           // Synchronized keyword in Java has to do with thread-safety, that is, when multiple threads read or write the same variable. ... The synchronized keyword is used to define a block of code where multiple threads can access the same variable in a safe way.
        int newThreadID = ProgramState.globalThreadCount;
        ProgramState.globalThreadCount += 1;
        return newThreadID;
    }

//    public int getNewId(){return ++threadID;}

    public boolean isNotCompleted() {
        return !(executionStack.size() == 0);
    }

    public ProgramState oneStepExecution() throws Exception {
        if (executionStack.size() == 0)
            throw new EmptyADTException("ProgramState stack is empty!");
        StatementInterface currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public String toString() {
//        return executionStack.toString() + "\n" + symbolTable.toString() + "\n" + output.toString() + "\n";
        return "\nThread ID: " + threadID + "\nExecution stack:\n" + executionStack.toString() + "Symbol table:\n" + symbolTable.toString() +
                "Output:\n" +output.toString() + "File table:\n" + fileTable.toString() + "Heap:\n" +
                heap.toString() + "\n";
    }
}
