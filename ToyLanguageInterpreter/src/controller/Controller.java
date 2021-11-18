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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private RepositoryInterface repository;
    private ExecutorService executor;       // The Java ExecutorService is the interface which allows us to execute tasks on threads asynchronously

    public Controller(RepositoryInterface repository) {
        this.repository = repository;
    }

    public void addProgramState(ProgramState newProgramState) {
        this.repository.addProgramState(newProgramState);
    }

    public void allStepsExecution() throws Exception {
        repository.clearLogFile();

        executor = Executors.newFixedThreadPool(2);

        List<ProgramState> programsList = removeCompletedPrograms(repository.getAllPrograms());

        while (programsList.size() > 0) {
            programsList.get(0).getHeap().setContent((HashMap<Integer, ValueInterface>) safeGarbageCollector(
                    getAddressFromSymbolTable(programsList.get(0).getSymbolTable().getContent().values(), programsList.get(0).getHeap().getContent().values()),
                    programsList.get(0).getHeap().getContent()));
            oneStepForAllPrograms(programsList);
            programsList = removeCompletedPrograms(repository.getAllPrograms());
        }

        executor.shutdownNow();

        repository.setProgramsList(programsList);
    }

    public void oneStepForAllPrograms(List<ProgramState> programsList) throws Exception {
        programsList.forEach(program -> {
            try {
                repository.logProgramStateExecution(program);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<Callable<ProgramState>> callableList = programsList.stream()       // Callable represents an asynchronous task which can be executed by a separate thread
                .map((ProgramState p) -> (Callable<ProgramState>)(() -> {return p.oneStepExecution();}))
                .collect(Collectors.toList());

        List<ProgramState> newProgramsList = executor.invokeAll(callableList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        programsList.addAll(newProgramsList);

        programsList.forEach(program -> {
            try {
                repository.logProgramStateExecution(program);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        repository.setProgramsList(programsList);
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

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> initialList) {
        return initialList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }
}
