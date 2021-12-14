package repository;

import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {
    private List<ProgramState> programStatesList;
    private final String logFilePath;

    public Repository(String logFilePath) {
        programStatesList = new ArrayList<ProgramState>();
        this.logFilePath = logFilePath;
    }

//    @Override
//    public ProgramState getCurrentProgramState() {
//        return programStatesList.get(this.programStatesList.size() - 1);
//    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        programStatesList.add(newProgramState);
//        programStatesList.add(programStatesList.size(), newProgramState);     // both work
    }

    @Override
    public void logProgramStateExecution(ProgramState state) throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.append(state.toString());
        logFile.close();
    }

    @Override
    public void clearLogFile() throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.write("");
        logFile.close();
    }

    @Override
    public List<ProgramState> getAllPrograms() {
        return programStatesList;
    }

    @Override
    public void setProgramsList(List<ProgramState> newProgramStates) {
//        programStatesList.clear();
//        programStatesList.addAll(newProgramStates);
        programStatesList = newProgramStates;
    }
}
