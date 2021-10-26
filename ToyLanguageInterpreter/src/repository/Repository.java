package repository;

import model.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {
    private List<ProgramState> programStatesList;

    public Repository() {
        programStatesList = new ArrayList<ProgramState>();
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
}
