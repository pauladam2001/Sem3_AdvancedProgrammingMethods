package controller;

import exceptions.EmptyADTException;
import model.ADT.StackInterface;
import model.ProgramState;
import model.statement.StatementInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;
import repository.RepositoryInterface;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            currentProgramState.getHeap().setContent((HashMap<Integer, ValueInterface>) safeGarbageCollector(
                    getAddressFromSymbolTable(currentProgramState.getSymbolTable().getContent().values(), currentProgramState.getHeap().getContent().values()),
                    currentProgramState.getHeap().getContent()));
//            repository.logProgramStateExecution();
        }
    }

    public Map<Integer, ValueInterface> safeGarbageCollector(List<Integer> symbolTableAddress, Map<Integer, ValueInterface> heap) {
        return heap.entrySet().stream()
                .filter(e -> symbolTableAddress.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddressFromSymbolTable(Collection<ValueInterface> symbolTableValues, Collection<ValueInterface> heap) {
        return Stream.concat(
                heap.stream()
                        .filter(v -> v instanceof ReferenceValue)
                        .map(v -> {ReferenceValue v1 = (ReferenceValue) v; return v1.getHeapAddress();}),
                symbolTableValues.stream()
                        .filter(v -> v instanceof ReferenceValue)
                        .map(v -> {ReferenceValue v1 = (ReferenceValue) v; return v1.getHeapAddress();})
        )
        .collect(Collectors.toList());
    }
}
