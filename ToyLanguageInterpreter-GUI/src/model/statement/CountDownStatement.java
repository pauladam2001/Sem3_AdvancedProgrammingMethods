package model.statement;

import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.ADT.DictionaryInterface;
import model.ADT.LatchTableInterface;
import model.ProgramState;
import model.statement.StatementInterface;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStatement implements StatementInterface {
    private String variableName;
    private static Lock myLock = new ReentrantLock();

    public CountDownStatement (String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        LatchTableInterface<Integer, Integer> latchTable = state.getLatchTable();

        if (!symbolTable.containsKey(variableName))
            throw new VariableNotDefinedException(variableName + " is not defined in the symbol table!");

        if (!symbolTable.getValue(variableName).getType().equals(new IntType()))
            throw new InvalidTypeException(variableName + " doesn't have type int!");

        int latchIndexAsInteger = ((IntValue)(symbolTable.getValue(variableName))).getValue();

        myLock.lock();

        if (!latchTable.containsKey(latchIndexAsInteger))
            throw new VariableNotDefinedException(latchIndexAsInteger + " is not defined in the latch table!");

        int count = state.getLatchTable().getValue(latchIndexAsInteger);

        if (count > 0) {
            latchTable.update(latchIndexAsInteger, count - 1);
//            state.getOutput().addToEnd(new StringValue("(latch) " + state.getThreadID()));
        }
        state.getOutput().addToEnd(new StringValue("(latch) " + state.getThreadID()));

        myLock.unlock();

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        if (!typeEnvironment.getValue(variableName).equals(new IntType()))
            throw new InvalidTypeException(variableName + " is not an int!");

        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "countDown( " + variableName + " )";
    }
}

