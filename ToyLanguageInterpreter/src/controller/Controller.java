package controller;

import exceptions.EmptyADTException;
import model.ADT.StackInterface;
import model.ProgramState;
import model.statement.StatementInterface;
import repository.RepositoryInterface;

public class Controller {
    private RepositoryInterface repository;

    public Controller(RepositoryInterface repository) {
        this.repository = repository;
    }

    public void addProgramState(ProgramState newProgramState) {
        this.repository.addProgramState(newProgramState);
    }

    public ProgramState oneStepExecution(ProgramState state) throws Exception {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        if (stack.size() == 0)
            throw new EmptyADTException("ProgramState stack is empty!");
        StatementInterface currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allStepsExecution() throws Exception {
        repository.clearLogFile();
        ProgramState currentProgramState = repository.getCurrentProgramState();
//        System.out.println(currentProgramState);
        repository.logProgramStateExecution();
        while (currentProgramState.getExecutionStack().size() > 0) {
            oneStepExecution(currentProgramState);
//            System.out.println(currentProgramState);
            repository.logProgramStateExecution();
        }
    }
}
