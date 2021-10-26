package model.statement;

import model.ProgramState;
import model.ADT.StackInterface;

public class CompoundStatement implements StatementInterface {
    private final StatementInterface firstStatement;
    private final StatementInterface secondStatement;

    public CompoundStatement(StatementInterface firstStatement, StatementInterface secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        return state;
    }

    @Override
    public String toString() {
        return "(" + firstStatement.toString() + ";" + secondStatement.toString()+ ")";
    }
}
