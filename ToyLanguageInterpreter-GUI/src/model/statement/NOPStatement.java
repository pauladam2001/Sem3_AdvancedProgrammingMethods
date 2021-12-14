package model.statement;

import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.type.TypeInterface;

public class NOPStatement implements StatementInterface {
    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "NOP statement";
    }
}
