package model.statement;

import exceptions.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.ADT.MyStack;
import model.ProgramState;
import model.type.BoolType;
import model.type.TypeInterface;

public class ForkStatement implements StatementInterface {
    private final StatementInterface threadInstructions;

    public ForkStatement(StatementInterface threadInstructions) {
        this.threadInstructions = threadInstructions;
    }

    @Override
    public ProgramState execute(ProgramState parentThread) throws Exception {
        if (threadInstructions == null)
            return null;

        return new ProgramState(new MyStack<StatementInterface>(),
                parentThread.getSymbolTable().clone(),
                parentThread.getOutput(),
                threadInstructions,
                parentThread.getFileTable(),
                parentThread.getHeap(),
                parentThread.getLatchTable());
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        threadInstructions.typeCheck(typeEnvironment.clone());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "Fork(" + threadInstructions.toString() + ")";
    }
}
