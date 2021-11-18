package model.statement;

import model.ADT.MyStack;
import model.ProgramState;

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
                parentThread.getHeap());
    }

    @Override
    public String toString() {
        return "Fork(" + threadInstructions.toString() + ")";
    }
}
