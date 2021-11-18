package model.statement;

import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.IntType;
import model.type.StringType;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;

public class ReadFileStatement implements StatementInterface {
    private final ExpressionInterface filePath;
    private final String variableName;

    public ReadFileStatement(ExpressionInterface filePath, String variableName) {
        this.filePath = filePath;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();

        if (!symbolTable.isDefined(variableName))
            throw new VariableNotDefinedException("Variable " + variableName + " is not defined in the symbol table!");
        if (!symbolTable.getValue(variableName).getType().equals(new IntType()))
            throw new InvalidTypeException("Variable " + variableName + " is not an integer!");

        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        DictionaryInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface filePathValue = filePath.evaluate(symbolTable, heap);

        if (!filePathValue.getType().equals(new StringType()))
            throw new InvalidTypeException("File path should be a string!");

        String filePathString = ((StringValue)filePathValue).getValue();   // casting

        if (!fileTable.isDefined((StringValue)filePathValue))
            throw new VariableNotDefinedException("File path " + filePathString + "is not defined in the file table!");

        BufferedReader fileBuffer = fileTable.getValue((StringValue)filePathValue);
        String currentLine = fileBuffer.readLine();
        if (currentLine == null)
            symbolTable.update(variableName, new IntValue(0));
        else
            symbolTable.update(variableName, new IntValue(Integer.parseInt(currentLine)));

        return null;
    }

    @Override
    public String toString() {
        return "readFile(" + filePath + ")";
    }
}
