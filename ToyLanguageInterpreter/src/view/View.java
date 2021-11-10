package view;

import controller.Controller;
import model.ADT.*;
import model.ProgramState;
import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;
import java.util.Scanner;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    private void inputMenu() {
        System.out.println("Program 1: int v; v=2; print(v);");
        System.out.println("Program 2: int a; int b; a=2+3*5; b=a+1; print(b);");
        System.out.println("Program 3: bool a; int v; a=true; (if a then v=2 else v=3); print(v);");
        System.out.println("Program 4: int a; bool b; int c; c=7; (if c>a then b=true else b=false); print(b);");
        System.out.println("Program 5: string varf; varf = 'test.in'; openReadFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeReadFile(varf);");
        System.out.println("Your option: ");

        int option;
        Scanner myInput = new Scanner(System.in);
        option = myInput.nextInt();

        StatementInterface statement;

        if (option == 1) {
            statement = new CompoundStatement(
                    new VariableDeclarationStatement("v", new IntType()),
                    new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                            new PrintStatement(new VariableExpression("v"))));
        }
        else if (option == 2) {
            statement = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                    new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                            new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                    ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                    new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new ValueExpression(new
                                            IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        }
        else if (option == 3) {
            statement = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                    new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                            new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                    new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                            IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                            VariableExpression("v"))))));
        }
        else if (option == 4) {
             statement = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                    new CompoundStatement(new VariableDeclarationStatement("b",new BoolType()),
                            new CompoundStatement(new VariableDeclarationStatement("c",new IntType()),
                                    new CompoundStatement(new AssignmentStatement("c",new ValueExpression(new IntValue(7))),
                                            new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("c"),
                                                    new VariableExpression("a"), ">"), new AssignmentStatement("b",new ValueExpression(new BoolValue(true))),
                                                    new AssignmentStatement("b",new ValueExpression(new BoolValue(false)))),new PrintStatement(new VariableExpression("b")))))));
        }
        else {
            ExpressionInterface filename=new ValueExpression(new StringValue("C:\\Users\\paula\\IdeaProjects\\ToyLanguageInterpreter\\test.in"));
            statement = new CompoundStatement(new OpenReadFileStatement(filename),
                    new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                            new CompoundStatement(new ReadFileStatement(filename, "v"),
                                    new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                            new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(2)),">="),
                                                    new CompoundStatement(new ReadFileStatement(filename, "v"), new PrintStatement(new VariableExpression("v"))),
                                                    new PrintStatement(new ValueExpression(new IntValue(-1)))), new CloseReadFileStatement(filename))))));
        }

        StackInterface<StatementInterface> stack = new MyStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> symbolTable = new MyDictionary<String, ValueInterface>();
        ListInterface<ValueInterface> output = new MyList<ValueInterface>();
        DictionaryInterface<StringValue, BufferedReader> fileTable = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap = new MyHeap<>();
        ProgramState currentProgramState = new ProgramState(stack, symbolTable, output, statement, fileTable, heap);
        stack.push(statement);
        this.controller.addProgramState(currentProgramState);

        boolean opt = true;

        while (opt) {
            System.out.println("0. Exit;");
            System.out.println("1. One step execution;");
            System.out.println("2. Full program execution.");
            System.out.println("Your option:");

            option = myInput.nextInt();

            if (option == 0) {
//                System.out.println("See you later!");
                break;
            }
            else if (option == 1) {
                try {
                    this.controller.oneStepExecution(currentProgramState);
                }
                catch (Exception e) {
                    System.out.println("\n\n" + e.getMessage() + "\n\n");
                }
            }
            else {
                try {
                    this.controller.allStepsExecution();
                    opt = false;
                }
                catch (Exception e) {
                    System.out.println("\n\n" + e.getMessage() + "\n\n");
                }
            }
//            System.out.println(currentProgramState.getExecutionStack());
//            System.out.println(currentProgramState.getSymbolTable());
//            System.out.println(currentProgramState.getOutput());
//            System.out.println(currentProgramState);
        }
    }

    public void start() {
        int option = -1;
        String optionString;
        Scanner myInput = new Scanner(System.in);

        while (true) {
            System.out.println("0. Exit;");
            System.out.println("1. Choose a program.");
            System.out.println("Your option: ");

            optionString = myInput.next();

            try {
                option = Integer.parseInt(optionString);
            } catch (Exception e) {
                System.out.println("Invalid option!");
            }

            if (option == 0) {
                System.out.println("See you later!");
                break;
            }
            else if (option == 1) {
                inputMenu();
            }
            else {
                System.out.println("Invalid option!");
            }
        }
        myInput.close();
    }
}
