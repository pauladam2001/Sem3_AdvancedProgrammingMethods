package model.statement;

import model.ProgramState;

public class NOPStatement implements StatementInterface {
    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "NOP statement";
    }
}
