package repository;

import model.ProgramState;

public interface RepositoryInterface {
    ProgramState getCurrentProgramState();
    void addProgramState(ProgramState newProgramState);
}
