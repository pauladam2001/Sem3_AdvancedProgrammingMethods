package model.statement;

import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.ADT.DictionaryInterface;
import model.ADT.LatchTableInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.statement.StatementInterface;
import model.type.BoolType;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStatement implements StatementInterface {
    private String variableName;
    private ExpressionInterface expression;
    private static Lock myLock = new ReentrantLock();

    public NewLatchStatement(String variableName, ExpressionInterface expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<Integer, ValueInterface> heap = state.getHeap();
        LatchTableInterface<Integer, Integer> latchTable = state.getLatchTable();

//        if(!symbolTable.containsKey(this.variableName)){
//            throw new VariableNotDefinedException(variableName + " is not defined in the symbol table!");
//        }
//
//        if(!symbolTable.getValue(this.variableName).getType().equals(new IntType())){
//            throw new InvalidTypeException(variableName + " doesn't have type int!");
//        }

        ValueInterface num1 = expression.evaluate(symbolTable, heap);

        if(!num1.getType().equals(new IntType())){
            throw new InvalidTypeException(num1 + " is not an int!");
        }

        myLock.lock();

        int number1 = ((IntValue) num1).getValue();
        int firstAvailablePosition = latchTable.getFirstFreeLocation();
        latchTable.add(firstAvailablePosition, number1);

        if (symbolTable.containsKey(variableName))
            symbolTable.update(variableName, new IntValue(firstAvailablePosition));
        else
            throw new VariableNotDefinedException(variableName + " not defined in the symbol table!");
//            symbolTable.add(variableName, new IntValue(firstAvailablePosition));

        myLock.unlock();

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        if (!expression.typeCheck(typeEnvironment).equals(new IntType()))
            throw new InvalidTypeException(expression + " is not an int!");
        if (!typeEnvironment.getValue(variableName).equals(new IntType()))
            throw new InvalidTypeException(variableName + " is not an int!");

        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "newLatch( " + variableName + ", " + expression + ") ";
    }
}
