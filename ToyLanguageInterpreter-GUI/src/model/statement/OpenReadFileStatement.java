package model.statement;

import exceptions.AlreadyDefinedVariableException;
import exceptions.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.StringType;
import model.type.TypeInterface;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenReadFileStatement implements StatementInterface {
    private final ExpressionInterface filePath;

    public OpenReadFileStatement(ExpressionInterface filePath) {
        this.filePath = filePath;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        DictionaryInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface filePathValue = filePath.evaluate(symbolTable, heap);

        if (filePathValue.getType().equals(new StringType())) {
            String filePathString = ((StringValue)filePathValue).getValue();    // casting
            if (fileTable.isDefined((StringValue)filePathValue))
                throw new AlreadyDefinedVariableException("File path " + filePathString + " is already defined in the file table!");
            try {
                BufferedReader fileBuffer = new BufferedReader(new FileReader(filePathString));
                fileTable.add((StringValue)filePathValue, fileBuffer);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new InvalidTypeException("The file path is not a string!");
        }

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
        return "openRead(" + filePath + ")";
    }
}
