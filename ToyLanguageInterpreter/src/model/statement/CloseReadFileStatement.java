package model.statement;

import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.StringType;
import model.type.TypeInterface;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;

public class CloseReadFileStatement implements StatementInterface {
    private final ExpressionInterface filePath;

    public CloseReadFileStatement(ExpressionInterface filePath) {
        this.filePath = filePath;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        DictionaryInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface filePathValue = filePath.evaluate(symbolTable, heap);

        String filePathString = ((StringValue)filePathValue).getValue();    // casting

        if (!filePathValue.getType().equals(new StringType()))
            throw new InvalidTypeException("File path " + filePathString + " is not a string!");
        if (!fileTable.isDefined((StringValue)filePathValue))
            throw new VariableNotDefinedException("File path " + filePathString + " is not defined in the file table!");

        BufferedReader fileBuffer = fileTable.getValue((StringValue)filePathValue);
        fileBuffer.close();
        fileTable.remove((StringValue)filePathValue);

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        if (filePath.typeCheck(typeEnvironment).equals(new StringType()))
            return typeEnvironment;
        else
            throw new InvalidTypeException("File path should be a string!");
    }

    @Override
    public String toString() {
        return "closeRead(" + filePath + ")";
    }
}
