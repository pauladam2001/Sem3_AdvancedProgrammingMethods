package model.statement;

import exceptions.AlreadyDefinedVariableException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.StringType;
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
        ValueInterface filePathValue = filePath.evaluate(symbolTable);

        if (filePathValue.getType().equals(new StringType())) {
            String filePathString = ((StringValue)filePathValue).getValue();    // casting
            if (fileTable.isDefined((StringValue)filePathValue))
                throw new AlreadyDefinedVariableException("File path " + filePathString + " is already defined in the file table!");
            BufferedReader fileBuffer = new BufferedReader(new FileReader(filePathString));
            fileTable.add((StringValue)filePathValue, fileBuffer);
        }

        return state;
    }

    @Override
    public String toString() {
        return "openRead(" + filePath + ")";
    }
}
