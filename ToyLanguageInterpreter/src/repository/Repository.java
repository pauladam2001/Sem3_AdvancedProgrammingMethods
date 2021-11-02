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

    @Override
    public ProgramState getCurrentProgramState() {
        return programStatesList.get(this.programStatesList.size() - 1);
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        programStatesList.add(newProgramState);
//        programStatesList.add(programStatesList.size(), newProgramState);     // both work
    }

    @Override
    public void logProgramStateExecution() throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        for (ProgramState currentProgramState : this.programStatesList)
            logFile.append(currentProgramState.toString());
        logFile.close();
    }

    @Override
    public void clearLogFile() throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.write("");
        logFile.close();
    }
}
