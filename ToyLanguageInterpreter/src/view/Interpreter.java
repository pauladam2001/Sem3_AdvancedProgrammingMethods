package view;

import controller.Controller;
import model.ADT.*;
import model.ProgramState;
import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;
import repository.Repository;
import repository.RepositoryInterface;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) {
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));


        StackInterface<StatementInterface> stack1 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable1 = new MyDictionary<>();
        ListInterface<ValueInterface> output1 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();

        StatementInterface statement1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        try {
            RepositoryInterface repository1 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile.txt");
            Controller controller1 = new Controller(repository1);
            ProgramState currentProgramState1 = new ProgramState(stack1, symbolTable1, output1, statement1, fileTable1);
            stack1.push(statement1);
            controller1.addProgramState(currentProgramState1);

            menu.addCommand(new RunExampleCommand("1", statement1.toString(), controller1));
        } catch (Exception e) {
            System.out.print("Example 1: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack2 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable2 = new MyDictionary<>();
        ListInterface<ValueInterface> output2 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();

        StatementInterface statement2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        try {
            RepositoryInterface repository2 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile.txt");
            Controller controller2 = new Controller(repository2);
            ProgramState currentProgramState2 = new ProgramState(stack2, symbolTable2, output2, statement2, fileTable2);
            stack2.push(statement2);
            controller2.addProgramState(currentProgramState2);

            menu.addCommand(new RunExampleCommand("2", statement2.toString(), controller2));
        } catch (Exception e) {
            System.out.print("Example 2: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack3 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable3 = new MyDictionary<>();
        ListInterface<ValueInterface> output3 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();

        StatementInterface statement3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        try {
            RepositoryInterface repository3 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile.txt");
            Controller controller3 = new Controller(repository3);
            ProgramState currentProgramState3 = new ProgramState(stack3, symbolTable3, output3, statement3, fileTable3);
            stack3.push(statement3);
            controller3.addProgramState(currentProgramState3);

            menu.addCommand(new RunExampleCommand("3", statement3.toString(), controller3));
        } catch (Exception e) {
            System.out.print("Example 3: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack4 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable4 = new MyDictionary<>();
        ListInterface<ValueInterface> output4 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();

        ExpressionInterface filename4=new ValueExpression(new StringValue("C:\\Users\\paula\\IdeaProjects\\ToyLanguageInterpreter\\test.in"));
        StatementInterface statement4 = new CompoundStatement(new OpenReadFileStatement(filename4),
                new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(new ReadFileStatement(filename4, "v"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(2)),">="),
                                                new CompoundStatement(new ReadFileStatement(filename4, "v"), new PrintStatement(new VariableExpression("v"))),
                                                new PrintStatement(new ValueExpression(new IntValue(-1)))), new CloseReadFileStatement(filename4))))));
        try {
            RepositoryInterface repository4 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile.txt");
            Controller controller4 = new Controller(repository4);
            ProgramState currentProgramState4 = new ProgramState(stack4, symbolTable4, output4, statement4, fileTable4);
            stack4.push(statement4);
            controller4.addProgramState(currentProgramState4);

            menu.addCommand(new RunExampleCommand("4", statement4.toString(), controller4));
        } catch (Exception e) {
            System.out.print("Example 4: " + e.getMessage() + "\n");
        }


//        StackInterface<StatementInterface> stack5 = new MyStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable5 = new MyDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> output5 = new MyList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
//
//        StatementInterface statement5 =  new CompoundStatement(new VariableDeclarationStatement("filePath", new StringType()),
//                new CompoundStatement(new AssignmentStatement("filePath", new ValueExpression(new StringValue("C:\\Users\\paula\\IdeaProjects\\ToyLanguageInterpreter\\test.in"))),
//                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("filePath")),
//                                new CompoundStatement(new VariableDeclarationStatement("variable", new IntType()),
//                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("filePath"), "variable"),
//                                                new CompoundStatement(new PrintStatement(new VariableExpression("variable")),
//                                                        new CloseReadFileStatement(new VariableExpression("filePath"))))))));
//        try {
//            RepositoryInterface repository5 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile.txt");
//            Controller controller5 = new Controller(repository5);
//            ProgramState currentProgramState5 = new ProgramState(stack5, symbolTable5, output5, statement5, fileTable5);
//            stack5.push(statement5);
//            controller5.addProgramState(currentProgramState5);
//
//            menu.addCommand(new RunExampleCommand("5", statement5.toString(), controller5));
//        } catch (Exception e) {
//            System.out.print("Example 5: " + e.getMessage() + "\n");
//        }


        menu.show();
    }
}
