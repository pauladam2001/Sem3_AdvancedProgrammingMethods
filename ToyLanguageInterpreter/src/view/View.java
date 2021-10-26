package view;

import controller.Controller;
import model.ADT.*;
import model.ProgramState;
import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.ValueInterface;

import java.util.Scanner;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    private void inputMenu() {
        System.out.println("Program 1: int v; v=2; print(v)");
        System.out.println("Program 2: int a; int b; a=2+3*5; b=a+1; print(b)");
        System.out.println("Program 3: bool a; int v; a=true; (if a then v=2 else v=3); print(v)");
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
        else {
            statement = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                    new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                            new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                    new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                            IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                            VariableExpression("v"))))));
        }

        StackInterface<StatementInterface> stack = new MyStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> symbolTable = new MyDictionary<String, ValueInterface>();
        ListInterface<ValueInterface> output = new MyList<ValueInterface>();
        ProgramState currentProgramState = new ProgramState(stack, symbolTable, output, statement);
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
