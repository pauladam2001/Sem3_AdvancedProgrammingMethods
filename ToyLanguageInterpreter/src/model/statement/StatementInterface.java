package model.statement;

import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.type.TypeInterface;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws Exception;
    DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception;
    String toString();
}
