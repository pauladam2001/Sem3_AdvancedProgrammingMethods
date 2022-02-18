package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.ADT.*;
import model.ProgramState;
import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.ReferenceType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;
import repository.Repository;
import repository.RepositoryInterface;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class ProgramsListViewController {

    private final List<StatementInterface> programs = new ArrayList<>();
    private final List<Controller> controllers = new ArrayList<>();

    @FXML
    private ListView<String> programsListView = new ListView<>();

    public ProgramsListViewController(){}

    public void setProgramsListView() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("TypeCheck Error");

        //        StatementInterface variablesDeclaration =
//                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
//                        new CompoundStatement(new VariableDeclarationStatement("b",new ReferenceType(new IntType())),
//                                new VariableDeclarationStatement("v", new IntType())));
//        StatementInterface heapAllocations =
//                new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new IntValue(0))),
//                        new HeapAllocationStatement("b", new ValueExpression(new IntValue(0))));
//        StatementInterface heapWriting =
//                new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(1))),
//                        new HeapWritingStatement("b", new ValueExpression(new IntValue(2))));
//        StatementInterface firstConditional =
//                new ConditionalAssignmentStatement("v", new RelationalExpression(
//                        new HeapReadingExpression(new VariableExpression("a")),
//                        new HeapReadingExpression(new VariableExpression("b")), "<"),
//                        new ValueExpression(new IntValue(100)), new ValueExpression(new IntValue(200)));
//        StatementInterface secondConditional =
//                new ConditionalAssignmentStatement("v", new RelationalExpression(
//                        new ArithmeticExpression("-",new HeapReadingExpression(new VariableExpression("b")),
//                                new ValueExpression(new IntValue(2))),
//                        new HeapReadingExpression(new VariableExpression("a")), ">"),
//                        new ValueExpression(new IntValue(100)), new ValueExpression(new IntValue(200)));
//        StatementInterface statement13 =
//                new CompoundStatement(variablesDeclaration,
//                        new CompoundStatement(heapAllocations,
//                                new CompoundStatement(heapWriting,
//                                        new CompoundStatement(firstConditional,
//                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                        new CompoundStatement(secondConditional, new PrintStatement(new VariableExpression("v"))))))));





//        StackInterface<StatementInterface> stack1 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable1 = new MyDictionary<>();
//        ListInterface<ValueInterface> output1 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap1 = new MyHeap<>();
//
//        // int v; v=2; print(v);
//        StatementInterface statement1 = new CompoundStatement(
//                new VariableDeclarationStatement("v", new IntType()),
//                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
//                        new PrintStatement(new VariableExpression("v"))));
//        try {
//            RepositoryInterface repository1 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile1.txt");
//            Controller controller1 = new Controller(repository1);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement1.typeCheck(typeEnvironment);
//            ProgramState currentProgramState1 = new ProgramState(stack1, symbolTable1, output1, statement1, fileTable1, heap1);
//            controller1.addProgramState(currentProgramState1);
//
//            programs.add(statement1);
//            controllers.add(controller1);
//        } catch (Exception e) {
//            alert.setContentText("Example 1 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack2 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable2 = new MyDictionary<>();
//        ListInterface<ValueInterface> output2 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap2 = new MyHeap<>();
//
//        // int a; int b; a=2+3*5; b=a+1; print(b);
//        StatementInterface statement2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
//                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
//                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
//                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new ValueExpression(new
//                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
//        try {
//            RepositoryInterface repository2 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile2.txt");
//            Controller controller2 = new Controller(repository2);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement2.typeCheck(typeEnvironment);
//            ProgramState currentProgramState2 = new ProgramState(stack2, symbolTable2, output2, statement2, fileTable2, heap2);
//            controller2.addProgramState(currentProgramState2);
//
//            programs.add(statement2);
//            controllers.add(controller2);
//        } catch (Exception e) {
//            alert.setContentText("Example 2 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack3 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable3 = new MyDictionary<>();
//        ListInterface<ValueInterface> output3 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap3 = new MyHeap<>();
//
//        // bool a; int v; a=true; (if a then v=2 else v=3); print(v);
//        StatementInterface statement3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
//                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
//                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
//                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
//                                        VariableExpression("v"))))));
//        try {
//            RepositoryInterface repository3 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile3.txt");
//            Controller controller3 = new Controller(repository3);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement3.typeCheck(typeEnvironment);
//            ProgramState currentProgramState3 = new ProgramState(stack3, symbolTable3, output3, statement3, fileTable3, heap3);
//            controller3.addProgramState(currentProgramState3);
//
//            programs.add(statement3);
//            controllers.add(controller3);
//        } catch (Exception e) {
//            alert.setContentText("Example 3 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack4 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable4 = new MyDictionary<>();
//        ListInterface<ValueInterface> output4 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap4 = new MyHeap<>();
//
//        // int a; bool b; int c; c=7; (if c>a then b=true else b=false); print(b);
//        StatementInterface statement4 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("b",new BoolType()),
//                        new CompoundStatement(new VariableDeclarationStatement("c",new IntType()),
//                                new CompoundStatement(new AssignmentStatement("c",new ValueExpression(new IntValue(7))),
//                                        new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("c"),
//                                                new VariableExpression("a"), ">"), new AssignmentStatement("b",new ValueExpression(new BoolValue(true))),
//                                                new AssignmentStatement("b",new ValueExpression(new BoolValue(false)))),new PrintStatement(new VariableExpression("b")))))));
//        try {
//            RepositoryInterface repository4 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile4.txt");
//            Controller controller4 = new Controller(repository4);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement4.typeCheck(typeEnvironment);
//            ProgramState currentProgramState4 = new ProgramState(stack4, symbolTable4, output4, statement4, fileTable4, heap4);
//            controller4.addProgramState(currentProgramState4);
//
//            programs.add(statement4);
//            controllers.add(controller4);
//        } catch (Exception e) {
//            alert.setContentText("Example 4 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack5 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable5 = new MyDictionary<>();
//        ListInterface<ValueInterface> output5 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap5 = new MyHeap<>();
//
//        // string varf; varf = 'test.in'; openReadFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeReadFile(varf);
//        ExpressionInterface filename5=new ValueExpression(new StringValue("C:\\Users\\paula\\IdeaProjects\\ToyLanguageInterpreter\\test.in"));
//        StatementInterface statement5 = new CompoundStatement(new OpenReadFileStatement(filename5),
//                new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
//                        new CompoundStatement(new ReadFileStatement(filename5, "v"),
//                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                        new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(2)),">="),
//                                                new CompoundStatement(new ReadFileStatement(filename5, "v"), new PrintStatement(new VariableExpression("v"))),
//                                                new PrintStatement(new ValueExpression(new IntValue(-1)))), new CloseReadFileStatement(filename5))))));
//        try {
//            RepositoryInterface repository5 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile5.txt");
//            Controller controller5 = new Controller(repository5);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement5.typeCheck(typeEnvironment);
//            ProgramState currentProgramState5 = new ProgramState(stack5, symbolTable5, output5, statement5, fileTable5, heap5);
//            controller5.addProgramState(currentProgramState5);
//
//            programs.add(statement5);
//            controllers.add(controller5);
//        } catch (Exception e) {
//            alert.setContentText("Example 5 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack6 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable6 = new MyDictionary<>();
//        ListInterface<ValueInterface> output6 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable6 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap6 = new MyHeap<>();
//
//        // int v; v=4; (while (v>0) print(v);v=v-1);print(v
//        StatementInterface statement6 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
//                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(4))),
//                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
//                                new CompoundStatement(new PrintStatement( new VariableExpression("v")),new AssignmentStatement("v",
//                                        new ArithmeticExpression("-",new VariableExpression("v"),new ValueExpression(new IntValue(1)))))),
//                                new PrintStatement(new VariableExpression("v")))));
//        try {
//            RepositoryInterface repository6 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile6.txt");
//            Controller controller6 = new Controller(repository6);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement6.typeCheck(typeEnvironment);
//            ProgramState currentProgramState6 = new ProgramState(stack6, symbolTable6, output6, statement6, fileTable6, heap6);
//            controller6.addProgramState(currentProgramState6);
//
//            programs.add(statement6);
//            controllers.add(controller6);
//        } catch (Exception e) {
//            alert.setContentText("Example 6 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack7 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable7 = new MyDictionary<>();
//        ListInterface<ValueInterface> output7 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable7 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap7 = new MyHeap<>();
//
//        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
//        StatementInterface statement7 = new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
//                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new IntType()))),
//                                new CompoundStatement(new HeapAllocationStatement("a",new VariableExpression("v")),
//                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                new PrintStatement(new VariableExpression("a")))))));
//        try {
//            RepositoryInterface repository7 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile7.txt");
//            Controller controller7 = new Controller(repository7);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement7.typeCheck(typeEnvironment);
//            ProgramState currentProgramState7 = new ProgramState(stack7, symbolTable7, output7, statement7, fileTable7, heap7);
//            controller7.addProgramState(currentProgramState7);
//
//            programs.add(statement7);
//            controllers.add(controller7);
//        } catch (Exception e) {
//            alert.setContentText("Example 7 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack8 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable8 = new MyDictionary<>();
//        ListInterface<ValueInterface> output8 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable8 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap8 = new MyHeap<>();
//
//        //  Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
//        StatementInterface statement8 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
//                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
//                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
//                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
//                                                new PrintStatement(new ArithmeticExpression("+", new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
//        try {
//            RepositoryInterface repository8 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile8.txt");
//            Controller controller8 = new Controller(repository8);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement8.typeCheck(typeEnvironment);
//            ProgramState currentProgramState8 = new ProgramState(stack8, symbolTable8, output8, statement8, fileTable8, heap8);
//            controller8.addProgramState(currentProgramState8);
//
//            programs.add(statement8);
//            controllers.add(controller8);
//        } catch (Exception e) {
//            alert.setContentText("Example 8 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack9 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable9 = new MyDictionary<>();
//        ListInterface<ValueInterface> output9 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable9 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap9 = new MyHeap<>();
//
//        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
//        StatementInterface statement9 = new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
//                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
//                                new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
//                                        new PrintStatement(new ArithmeticExpression("+", new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
//        try {
//            RepositoryInterface repository9 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile9.txt");
//            Controller controller9 = new Controller(repository9);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement9.typeCheck(typeEnvironment);
//            ProgramState currentProgramState9 = new ProgramState(stack9, symbolTable9, output9, statement9, fileTable9, heap9);
//            controller9.addProgramState(currentProgramState9);
//
//            programs.add(statement9);
//            controllers.add(controller9);
//        } catch (Exception e) {
//            alert.setContentText("Example 9 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack10 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable10 = new MyDictionary<>();
//        ListInterface<ValueInterface> output10 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable10 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap10 = new MyHeap<>();
//
//        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
//        StatementInterface statement10 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
//                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
//                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
//                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
//                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
//        try {
//            RepositoryInterface repository10 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile10.txt");
//            Controller controller10 = new Controller(repository10);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement10.typeCheck(typeEnvironment);
//            ProgramState currentProgramState10 = new ProgramState(stack10, symbolTable10, output10, statement10, fileTable10, heap10);
//            controller10.addProgramState(currentProgramState10);
//
//            programs.add(statement10);
//            controllers.add(controller10);
//        } catch (Exception e) {
//            alert.setContentText("Example 10 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack11 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable11 = new MyDictionary<>();
//        ListInterface<ValueInterface> output11 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable11 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap11 = new MyHeap<>();
//
//        // int v; Ref int a; v=10; new(a,22); fork(wH(a,30); v=32; print(v); print(rH(a))); print(v); print(rH(a));
//        StatementInterface statement11 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
//                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
//                                new CompoundStatement(new HeapAllocationStatement("a",new ValueExpression(new IntValue(22))),
//                                        new CompoundStatement(
//                                                new ForkStatement(
//                                                        new CompoundStatement(new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
//                                                                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
//                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))
//                                                ),
//                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));
//        try {
//            RepositoryInterface repository11 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile11.txt");
//            Controller controller11 = new Controller(repository11);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement11.typeCheck(typeEnvironment);
//            ProgramState currentProgramState11 = new ProgramState(stack11, symbolTable11, output11, statement11, fileTable11, heap11);
//            controller11.addProgramState(currentProgramState11);
//
//            programs.add(statement11);
//            controllers.add(controller11);
//        } catch (Exception e) {
//            alert.setContentText("Example 11 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }

        // THROWS AN ERROR
//        StackInterface<StatementInterface> stack12 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable12 = new MyDictionary<>();
//        ListInterface<ValueInterface> output12 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable12 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap12 = new MyHeap<>();
//
//       // int a; bool b; int c; c="abc"; (if c>a then b=true else b=false); print(b);
//        StatementInterface statement12 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("b",new BoolType()),
//                        new CompoundStatement(new VariableDeclarationStatement("c",new IntType()),
//                                new CompoundStatement(new AssignmentStatement("c",new ValueExpression(new StringValue("abc"))),
//                                        new CompoundStatement(new IfStatement(new RelationalExpression(new VariableExpression("c"),
//                                                new VariableExpression("a"), ">"), new AssignmentStatement("b",new ValueExpression(new BoolValue(true))),
//                                                new AssignmentStatement("b",new ValueExpression(new BoolValue(false)))),new PrintStatement(new VariableExpression("b")))))));
//        try {
//            RepositoryInterface repository12 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile12.txt");
//            Controller controller12 = new Controller(repository12);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement12.typeCheck(typeEnvironment);
//            ProgramState currentProgramState12 = new ProgramState(stack12, symbolTable12, output12, statement12, fileTable12, heap12);
//            controller12.addProgramState(currentProgramState12);
//
//            programs.add(statement12);
//            controllers.add(controller12);
//        } catch (Exception e) {
//            alert.setContentText("Example 12 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }
//
//
//        StackInterface<StatementInterface> stack12 = new MyStack<>();
//        DictionaryInterface<String, ValueInterface> symbolTable12 = new MyDictionary<>();
//        ListInterface<ValueInterface> output12 = new MyList<>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable12 = new MyDictionary<>();
//        DictionaryInterface<Integer, ValueInterface> heap12 = new MyHeap<>();
//
//        // int v; Ref int a; v=10; new(a,22); fork(wH(a,30); v=32; print(v); print(rH(a))); fork(wh(a,30); print(v); print(rH(a)));
//        StatementInterface statement12 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
//                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
//                                new CompoundStatement(new HeapAllocationStatement("a",new ValueExpression(new IntValue(22))),
//                                        new CompoundStatement(
//                                                new ForkStatement(
//                                                        new CompoundStatement(new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
//                                                                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
//                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))
//                                                ),
//                                                new CompoundStatement(
//                                                        new ForkStatement(
//                                                                new CompoundStatement(new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
//                                                                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
//                                                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))
//                                                        ),
//                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))))));
//        try {
//            RepositoryInterface repository12 = new Repository("C:/Users/paula/IdeaProjects/ToyLanguageInterpreter/logFile12.txt");
//            Controller controller12 = new Controller(repository12);
//            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
//            statement12.typeCheck(typeEnvironment);
//            ProgramState currentProgramState12 = new ProgramState(stack12, symbolTable12, output12, statement12, fileTable12, heap12);
//            controller12.addProgramState(currentProgramState12);
//
//            programs.add(statement12);
//            controllers.add(controller12);
//        } catch (Exception e) {
//            alert.setContentText("Example 12 TypeCheck error: " + e.getMessage());
//            alert.showAndWait();
//        }


        StackInterface<StatementInterface> stack13 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable13 = new MyDictionary<>();
        ListInterface<ValueInterface> output13 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable13 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap13 = new MyHeap<>();
        LatchTableInterface<Integer, Integer> latchTable13 = new MyLatchTable();
        // Ref int a; Ref int b; int v; new(a,0); new(b,0);
        // wh(a,1); wh(b,2);
        // v=(rh(a)<rh(b))?100:200;
        // print(v);
        // v = ((rh(b)-2)>rh(a))?100:200;
        // print(v);
        StatementInterface statement13 = new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("b", new ReferenceType(new IntType())),
                        new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new IntValue(0))),
                                        new CompoundStatement(new HeapAllocationStatement("b", new ValueExpression(new IntValue(0))),
                                                new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(1))),
                                                        new CompoundStatement(new HeapWritingStatement("b", new ValueExpression(new IntValue(2))),
                                                                new CompoundStatement(new ConditionalAssignmentStatement("v",
                                                                        new RelationalExpression(new HeapReadingExpression(new VariableExpression("a")), new HeapReadingExpression(new VariableExpression("b")), "<"),
                                                                        new ValueExpression(new IntValue(100)),
                                                                        new ValueExpression(new IntValue(200))),
                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                                new CompoundStatement(new ConditionalAssignmentStatement("v",
                                                                                        new RelationalExpression(new ArithmeticExpression("-", new HeapReadingExpression(new VariableExpression("b")), new ValueExpression(new IntValue(2))), new HeapReadingExpression(new VariableExpression("a")), ">"),
                                                                                        new ValueExpression(new IntValue(100)),
                                                                                        new ValueExpression(new IntValue(200))),

                                                                                        new PrintStatement(new VariableExpression("v"))))))))))));
        try {
            RepositoryInterface repository13 = new Repository("C:\\Users\\paula\\IdeaProjects\\ToyLanguageInterpreter-GUI\\logFile13.txt");
            Controller controller13 = new Controller(repository13);
            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
            statement13.typeCheck(typeEnvironment);
            ProgramState currentProgramState13 = new ProgramState(stack13, symbolTable13, output13, statement13, fileTable13, heap13, latchTable13);
            controller13.addProgramState(currentProgramState13);

            programs.add(statement13);
            controllers.add(controller13);
        } catch (Exception e) {
            alert.setContentText("Example 13 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }


        StackInterface<StatementInterface> stack14 = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symbolTable14 = new MyDictionary<>();
        ListInterface<ValueInterface> output14 = new MyList<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable14 = new MyDictionary<>();
        DictionaryInterface<Integer, ValueInterface> heap14 = new MyHeap<>();
        LatchTableInterface<Integer, Integer> latchTable14 = new MyLatchTable();

        // Ref int v1; Ref int v2; Ref int v3; int cnt;
        // new(v1,2);
        // new(v2,3);
        // new(v3,4);
        // newLatch(cnt,rH(v2));
        // fork(wh(v1,rh(v1)*10));
        // print(rh(v1));
        // countDown(cnt);
        // fork(wh(v2,rh(v2)*10));
        // print(rh(v2));
        // countDown(cnt);
        // fork(wh(v3,rh(v3)*10));
        // print(rh(v3));
        // countDown(cnt))));
        // await(cnt);
        // print(100);
        // countDown(cnt);
        // print(100)
        StatementInterface declaration_v1_15 = new VariableDeclarationStatement("v1", new ReferenceType(new IntType()));
        StatementInterface declaration_v2_15 = new VariableDeclarationStatement("v2", new ReferenceType(new IntType()));
        StatementInterface declaration_v3_15 = new VariableDeclarationStatement("v3", new ReferenceType(new IntType()));
        StatementInterface declaration_cnt_15 = new VariableDeclarationStatement("cnt", new IntType());

        StatementInterface heapAllocation_v1_15 = new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2)));
        StatementInterface heapAllocation_v2_15 = new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3)));
        StatementInterface heapAllocation_v3_15 = new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4)));

        StatementInterface newLatch_15 = new NewLatchStatement("cnt", new HeapReadingExpression(new VariableExpression("v2")));


        StatementInterface print_v1_15 = new PrintStatement(new HeapReadingExpression( new VariableExpression("v1")));
        StatementInterface fork_v1_15 = new ForkStatement(new CompoundStatement(new HeapWritingStatement("v1", new ArithmeticExpression("*", new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))), new CompoundStatement(print_v1_15, new CountDownStatement("cnt"))));

        StatementInterface print_v2_15 = new PrintStatement(new HeapReadingExpression(new VariableExpression( "v2")));
        StatementInterface fork_v2_15 = new ForkStatement(new CompoundStatement(new HeapWritingStatement("v2", new ArithmeticExpression("*", new HeapReadingExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)))), new CompoundStatement(print_v2_15, new CountDownStatement("cnt"))));

        StatementInterface print_v3_15 = new PrintStatement(new HeapReadingExpression(new VariableExpression("v3")));
        StatementInterface fork_v3_15 = new ForkStatement(new CompoundStatement(new HeapWritingStatement("v3", new ArithmeticExpression("*", new HeapReadingExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)))), new CompoundStatement(print_v3_15, new CountDownStatement("cnt"))));


        StatementInterface await_15 = new AwaitStatement("cnt");

        StatementInterface print_100_15 = new PrintStatement(new ValueExpression(new IntValue(100)));

        StatementInterface statement14 = new CompoundStatement(declaration_v1_15, new CompoundStatement(declaration_v2_15, new CompoundStatement(declaration_v3_15, new CompoundStatement(declaration_cnt_15,
                new CompoundStatement(heapAllocation_v1_15, new CompoundStatement(heapAllocation_v2_15, new CompoundStatement(heapAllocation_v3_15, new CompoundStatement(newLatch_15, new CompoundStatement(fork_v1_15,
                        new CompoundStatement(fork_v2_15, new CompoundStatement(fork_v3_15, new CompoundStatement(await_15, new CompoundStatement(print_100_15, new CompoundStatement(new CountDownStatement("cnt"), print_100_15))))))))))))));
        try {
            RepositoryInterface repository14 = new Repository("C:\\Users\\paula\\IdeaProjects\\ToyLanguageInterpreter-GUI\\logFile14.txt");
            Controller controller14 = new Controller(repository14);
            DictionaryInterface<String, TypeInterface> typeEnvironment = new MyDictionary<>();
            statement14.typeCheck(typeEnvironment);
            ProgramState currentProgramState14 = new ProgramState(stack14, symbolTable14, output14, statement14, fileTable14, heap14, latchTable14);
            controller14.addProgramState(currentProgramState14);

            programs.add(statement14);
            controllers.add(controller14);
        } catch (Exception e) {
            alert.setContentText("Example 14 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }

//        ObservableList<String> programsObservableList = FXCollections.observableArrayList(programs.stream().map(Object::toString).collect(Collectors.toList()));
//        ObservableList<StatementInterface> programsObservableList = FXCollections.observableArrayList(programs);
//        programsListView.setItems(programsObservableList);

//        List<String> programsString;
//        programsString = programs.stream().map(item -> "Example 1" + item.toString()).collect(Collectors.toList());

        List<String> programsString;
        List<String> list = new ArrayList<>();
        int ct = 1;
        for (StatementInterface item : programs) {
            String s = "Example " + ct + ": " + item.toString();
            list.add(s);
            ct += 1;
        }
        programsString = list;

        ObservableList<String> programsObservableList = FXCollections.observableArrayList(programsString);
        programsListView.setItems(programsObservableList);
    }

    @FXML
    private void onRunProgramButton() {
        if (programsListView.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgramInterpreterWindow.fxml"));
                Parent programInterpreterView = loader.load();

                ProgramInterpreterWindowController controller = loader.getController();
                controller.setController(controllers.get(programsListView.getSelectionModel().getSelectedIndex()));

                Stage stage = new Stage();
                stage.setTitle("Program Interpreter");
                stage.setScene(new Scene(programInterpreterView));
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("You need to select one of the programs");
            alert.setContentText("Please select a program!");
            alert.showAndWait();
        }
    }
}
