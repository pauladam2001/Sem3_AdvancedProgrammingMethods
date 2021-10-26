package model.statement;

import exceptions.AlreadyDefinedVariableException;
import exceptions.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.type.BoolType;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.ValueInterface;

public class VariableDeclarationStatement implements StatementInterface {
    private final String name;
    private final TypeInterface type;

    public VariableDeclarationStatement(String name, TypeInterface type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();

        if (symbolTable.isDefined(name))
            throw new AlreadyDefinedVariableException("Variable " + name + " is already defined!");

        if (type instanceof IntType)
            symbolTable.add(name, new IntValue(-1));        // -1 = default value
        else if (type instanceof BoolType)
            symbolTable.add(name, new BoolValue(false));    // false = default value
        else
            throw new InvalidTypeException("Invalid type for variable " + name);

        return state;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name.toString();
    }
}
