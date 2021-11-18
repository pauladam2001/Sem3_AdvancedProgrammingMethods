package view;

import controller.Controller;
import model.ADT.*;
import model.ProgramState;
import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.ReferenceType;
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
        menu.addCommand(new ExitCommand("0", "Exit;"));


        StackInterface<StatementInterface> stack1 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable1 = new MyDictionary<>();
        ListInterface<ValueInterface> output1 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap1 = new MyHeap<>();

        // int v; v=2; print(v);
        StatementInterface statement1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        try {
            RepositoryInterface repository1 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile1.txt");
            Controller controller1 = new Controller(repository1);
            ProgramState currentProgramState1 = new ProgramState(stack1, symbolTable1, output1, statement1, fileTable1, heap1);
            controller1.addProgramState(currentProgramState1);

            menu.addCommand(new RunExampleCommand("1", statement1.toString(), controller1));
        } catch (Exception e) {
            System.out.print("Example 1: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack2 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable2 = new MyDictionary<>();
        ListInterface<ValueInterface> output2 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap2 = new MyHeap<>();

        // int a; int b; a=2+3*5; b=a+1; print(b);
        StatementInterface statement2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        try {
            RepositoryInterface repository2 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile2.txt");
            Controller controller2 = new Controller(repository2);
            ProgramState currentProgramState2 = new ProgramState(stack2, symbolTable2, output2, statement2, fileTable2, heap2);
            controller2.addProgramState(currentProgramState2);

            menu.addCommand(new RunExampleCommand("2", statement2.toString(), controller2));
        } catch (Exception e) {
            System.out.print("Example 2: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack3 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable3 = new MyDictionary<>();
        ListInterface<ValueInterface> output3 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap3 = new MyHeap<>();

        // bool a; int v; a=true; (if a then v=2 else v=3); print(v);
        StatementInterface statement3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        try {
            RepositoryInterface repository3 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile3.txt");
            Controller controller3 = new Controller(repository3);
            ProgramState currentProgramState3 = new ProgramState(stack3, symbolTable3, output3, statement3, fileTable3, heap3);
            controller3.addProgramState(currentProgramState3);

            menu.addCommand(new RunExampleCommand("3", statement3.toString(), controller3));
        } catch (Exception e) {
            System.out.print("Example 3: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack4 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable4 = new MyDictionary<>();
        ListInterface<ValueInterface> output4 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap4 = new MyHeap<>();

        // int a; bool b; int c; c=7; (if c>a then b=true else b=false); print(b);
        StatementInterface statement4 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new BoolType()),
                        new CompoundStatement(new VariableDeclarationStatement("c",new IntType()),
                                new CompoundStatement(new AssignmentStatement("c",new ValueExpression(new IntValue(7))),
                                        new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("c"),
                                                new VariableExpression("a"), ">"), new AssignmentStatement("b",new ValueExpression(new BoolValue(true))),
                                                new AssignmentStatement("b",new ValueExpression(new BoolValue(false)))),new PrintStatement(new VariableExpression("b")))))));
        try {
            RepositoryInterface repository4 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile4.txt");
            Controller controller4 = new Controller(repository4);
            ProgramState currentProgramState4 = new ProgramState(stack4, symbolTable4, output4, statement4, fileTable4, heap4);
            controller4.addProgramState(currentProgramState4);

            menu.addCommand(new RunExampleCommand("4", statement4.toString(), controller4));
        } catch (Exception e) {
            System.out.print("Example 4: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack5 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable5 = new MyDictionary<>();
        ListInterface<ValueInterface> output5 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap5 = new MyHeap<>();

        // string varf; varf = 'test.in'; openReadFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeReadFile(varf);
        ExpressionInterface filename5=new ValueExpression(new StringValue("C:\\Users\\paula\\IdeaProjects\\ToyLanguageInterpreter\\test.in"));
        StatementInterface statement5 = new CompoundStatement(new OpenReadFileStatement(filename5),
                new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(new ReadFileStatement(filename5, "v"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(2)),">="),
                                                new CompoundStatement(new ReadFileStatement(filename5, "v"), new PrintStatement(new VariableExpression("v"))),
                                                new PrintStatement(new ValueExpression(new IntValue(-1)))), new CloseReadFileStatement(filename5))))));
        try {
            RepositoryInterface repository5 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile5.txt");
            Controller controller5 = new Controller(repository5);
            ProgramState currentProgramState4 = new ProgramState(stack5, symbolTable5, output5, statement5, fileTable5, heap5);
            controller5.addProgramState(currentProgramState4);

            menu.addCommand(new RunExampleCommand("5", statement5.toString(), controller5));
        } catch (Exception e) {
            System.out.print("Example 5: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack6 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable6 = new MyDictionary<>();
        ListInterface<ValueInterface> output6 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable6 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap6 = new MyHeap<>();

        // int v; v=4; (while (v>0) print(v);v=v-1);print(v
        StatementInterface statement6 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                                new CompoundStatement(new PrintStatement( new VariableExpression("v")),new AssignmentStatement("v",
                                        new ArithmeticExpression("-",new VariableExpression("v"),new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        try {
            RepositoryInterface repository6 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile6.txt");
            Controller controller6 = new Controller(repository6);
            ProgramState currentProgramState6 = new ProgramState(stack6, symbolTable6, output6, statement6, fileTable6, heap6);
            controller6.addProgramState(currentProgramState6);

            menu.addCommand(new RunExampleCommand("6", statement6.toString(), controller6));
        } catch (Exception e) {
            System.out.print("Example 6: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack7 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable7 = new MyDictionary<>();
        ListInterface<ValueInterface> output7 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable7 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap7 = new MyHeap<>();

        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        StatementInterface statement7 = new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a",new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));
        try {
            RepositoryInterface repository7 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile7.txt");
            Controller controller7 = new Controller(repository7);
            ProgramState currentProgramState7 = new ProgramState(stack7, symbolTable7, output7, statement7, fileTable7, heap7);
            controller7.addProgramState(currentProgramState7);

            menu.addCommand(new RunExampleCommand("7", statement7.toString(), controller7));
        } catch (Exception e) {
            System.out.print("Example 7: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack8 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable8 = new MyDictionary<>();
        ListInterface<ValueInterface> output8 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable8 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap8 = new MyHeap<>();

        //  Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        StatementInterface statement8 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression("+", new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
        try {
            RepositoryInterface repository8 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile8.txt");
            Controller controller8 = new Controller(repository8);
            ProgramState currentProgramState8 = new ProgramState(stack8, symbolTable8, output8, statement8, fileTable8, heap8);
            controller8.addProgramState(currentProgramState8);

            menu.addCommand(new RunExampleCommand("8", statement8.toString(), controller8));
        } catch (Exception e) {
            System.out.print("Example 8: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack9 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable9 = new MyDictionary<>();
        ListInterface<ValueInterface> output9 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable9 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap9 = new MyHeap<>();

        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        StatementInterface statement9 = new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression("+", new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
        try {
            RepositoryInterface repository9 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile9.txt");
            Controller controller9 = new Controller(repository9);
            ProgramState currentProgramState9 = new ProgramState(stack9, symbolTable9, output9, statement9, fileTable9, heap9);
            controller9.addProgramState(currentProgramState9);

            menu.addCommand(new RunExampleCommand("9", statement9.toString(), controller9));
        } catch (Exception e) {
            System.out.print("Example 9: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack10 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable10 = new MyDictionary<>();
        ListInterface<ValueInterface> output10 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable10 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap10 = new MyHeap<>();

        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        StatementInterface statement10 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
        try {
            RepositoryInterface repository10 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile10.txt");
            Controller controller10 = new Controller(repository10);
            ProgramState currentProgramState10 = new ProgramState(stack10, symbolTable10, output10, statement10, fileTable10, heap10);
            controller10.addProgramState(currentProgramState10);

            menu.addCommand(new RunExampleCommand("10", statement10.toString(), controller10));
        } catch (Exception e) {
            System.out.print("Example 10: " + e.getMessage() + "\n");
        }


        StackInterface<StatementInterface> stack11 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable11 = new MyDictionary<>();
        ListInterface<ValueInterface> output11 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable11 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap11 = new MyHeap<>();

        // int v; Ref int a; v=10; new(a,22); fork(wH(a,30); v=32; print(v); print(rH(a))); print(v); print(rH(a));
        StatementInterface statement11 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a",new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))
                                                ),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));
        try {
            RepositoryInterface repository11 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile11.txt");
            Controller controller11 = new Controller(repository11);
            ProgramState currentProgramState11 = new ProgramState(stack11, symbolTable11, output11, statement11, fileTable11, heap11);
            controller11.addProgramState(currentProgramState11);

            menu.addCommand(new RunExampleCommand("11", statement11.toString(), controller11));
        } catch (Exception e) {
            System.out.print("Example 11: " + e.getMessage() + "\n");
        }


        menu.show();
    }
}
