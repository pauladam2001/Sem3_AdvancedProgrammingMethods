package model.statement;

import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.ADT.StackInterface;
import model.type.TypeInterface;

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
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        return secondStatement.typeCheck(firstStatement.typeCheck(typeEnvironment));
    }

    @Override
    public String toString() {
        return "(" + firstStatement.toString() + ";" + secondStatement.toString()+ ")";
    }
}
