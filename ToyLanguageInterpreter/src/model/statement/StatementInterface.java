package model.statement;

import model.ProgramState;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws Exception;
    String toString();
}
