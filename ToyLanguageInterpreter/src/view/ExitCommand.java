package view;

public class ExitCommand extends Command {
    public ExitCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() throws Exception {
        System.exit(0);
    }
}
