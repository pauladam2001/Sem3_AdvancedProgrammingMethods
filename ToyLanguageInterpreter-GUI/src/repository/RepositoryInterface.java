package repository;

import model.ProgramState;

import java.util.List;

public interface RepositoryInterface {
//    ProgramState getCurrentProgramState();
    void addProgramState(ProgramState newProgramState);
    void logProgramStateExecution(ProgramState state) throws Exception;
    void clearLogFile() throws Exception;
    List<ProgramState> getAllPrograms();
    void setProgramsList(List<ProgramState> newProgramStates);
}
