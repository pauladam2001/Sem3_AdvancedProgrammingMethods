package model.statement;

import exceptions.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.BoolType;
import model.type.TypeInterface;

public class ConditionalAssignmentStatement implements StatementInterface {
    private String variableName;
    private ExpressionInterface exp1;
    private ExpressionInterface exp2;
    private ExpressionInterface exp3;

    public ConditionalAssignmentStatement(String variableName, ExpressionInterface exp1, ExpressionInterface exp2, ExpressionInterface exp3) {
        this.variableName = variableName;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        StatementInterface newIfStatement = new IfStatement(exp1, new AssignmentStatement(variableName, exp2), new AssignmentStatement(variableName, exp3));

        state.getExecutionStack().push(newIfStatement);

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws Exception {
        if (! exp1.typeCheck(typeEnvironment).equals(new BoolType())) {
            throw new InvalidTypeException(exp1 + " is not a boolean!");
        }

        if (!typeEnvironment.getValue(variableName).equals(exp2.typeCheck(typeEnvironment)))
            throw new InvalidTypeException("ConditionalAssignStatement: variableName and exp2 have different types!");
        if (!typeEnvironment.getValue(variableName).equals(exp3.typeCheck(typeEnvironment)))
            throw new InvalidTypeException("ConditionalAssignStatement: variableName and exp3 have different types!");
        if (!exp2.typeCheck(typeEnvironment).equals(exp3.typeCheck(typeEnvironment)))
            throw new InvalidTypeException("ConditionalAssignStatement: exp2 and exp3 have different types!");

        return typeEnvironment;
    }

    @Override
    public String toString(){
        return variableName + "=( " + this.exp1 + " )?" + this.exp2 + ":" + this.exp3;
    }
}

