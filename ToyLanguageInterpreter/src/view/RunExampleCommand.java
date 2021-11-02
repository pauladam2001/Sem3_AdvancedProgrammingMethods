package view;

import controller.Controller;

public class RunExampleCommand extends Command {
    private Controller controller;

    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() throws Exception {
        try {
            controller.allStepsExecution();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
