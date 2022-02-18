package gui;

import controller.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.ADT.DictionaryInterface;
import model.ADT.LatchTableInterface;
import model.ProgramState;
import model.statement.StatementInterface;
import model.value.ValueInterface;

import java.util.*;
import java.util.stream.Collectors;

public class ProgramInterpreterWindowController {

    private Controller controller;
    private ProgramState selectedProgram;

    @FXML
    private TableView<HashMap.Entry<Integer, String>> heapTableView=new TableView<>();
    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, Integer> heapAddressColumn=new TableColumn<>();
    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, String> heapValueColumn=new TableColumn<>();

    @FXML
    private ListView<String> outputListView=new ListView<>();

    @FXML
    private ListView<String> fileListView= new ListView<>();

    @FXML
    private ListView<Integer> programStatesView=new ListView<>();

    @FXML
    private TableView<Map.Entry<String, String>> symTableView=new TableView<>();
    @FXML
    private TableColumn<Map.Entry<String, String>, String> symVarNameColumn=new TableColumn<>();
    @FXML
    private TableColumn<Map.Entry<String, String>, String> symValueColumn=new TableColumn<>();

    @FXML
    private TableView<Map.Entry<Integer, Integer>> latchTableView=new TableView<>();
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> locationLatchColumn=new TableColumn<>();
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> valueLatchColumn=new TableColumn<>();

    @FXML
    private ListView<String> exeStackView=new ListView<>();

    @FXML
    private TextField nrProgramStatesField=new TextField("");


    public ProgramInterpreterWindowController() {}

    public void setController(Controller controller) {
        this.controller = controller;
        selectedProgram = controller.getRepository().getAllPrograms().get(0);
        loadData();
    }

    private void loadData() {
        programStatesView.getItems().setAll(controller.getRepository().getAllPrograms().stream()
                .map(ProgramState::getThreadID).collect(Collectors.toList()));

        if (selectedProgram != null) {
            nrProgramStatesField.setText(Integer.toString(controller.getRepository().getAllPrograms().size()));

            outputListView.getItems().setAll(selectedProgram.getOutput().getList().stream()
                    .map(Object::toString).collect(Collectors.toList()));

            fileListView.getItems().setAll(String.valueOf(selectedProgram.getFileTable().getContent().keySet()));

            exeStackView.getItems().setAll(selectedProgram.getExecutionStack().getStack().stream()          // we can use directly .setItems() only when we have an ObservableList
                    .map(StatementInterface::toString).collect(Collectors.toList()));

            DictionaryInterface<Integer, ValueInterface> heapTable = selectedProgram.getHeap();
            List<Map.Entry<Integer, String>> heapTableList = new ArrayList<>();
            for (Map.Entry<Integer, ValueInterface> elem : heapTable.getContent().entrySet()) {
                Map.Entry<Integer, String> el = new AbstractMap.SimpleEntry<>(elem.getKey(), elem.getValue().toString());
                heapTableList.add(el);
            }
            heapTableView.setItems(FXCollections.observableList(heapTableList));
            heapTableView.refresh();

            heapAddressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
            heapValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));

            DictionaryInterface<String, ValueInterface> symbolTable = selectedProgram.getSymbolTable();
            List<Map.Entry<String, String>> symbolTableList = new ArrayList<>();
            for (Map.Entry<String, ValueInterface> elem : symbolTable.getContent().entrySet()) {
                Map.Entry<String, String> el = new AbstractMap.SimpleEntry<>(elem.getKey(), elem.getValue().toString());
                symbolTableList.add(el);
            }
            symTableView.setItems(FXCollections.observableList(symbolTableList));
            symTableView.refresh();

            symVarNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
            symValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));

            LatchTableInterface<Integer, Integer> latchTable = selectedProgram.getLatchTable();
            List<Map.Entry<Integer, Integer>> lockList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry: latchTable.getContent().entrySet())
                lockList.add(entry);
            latchTableView.setItems(FXCollections.observableList(lockList));
            latchTableView.refresh();

            locationLatchColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
            valueLatchColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());
        }
    }

    @FXML
    public void onRunOneStepButton() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");

        if (controller == null) {
            alert.setHeaderText("No program selected");
            alert.setContentText("Please select a program!");
            alert.showAndWait();
        }

        if (selectedProgram.getExecutionStack().size() == 0) {
            alert.setHeaderText("The program is over");
            alert.setContentText("Select a new program to execute!");
            alert.showAndWait();
        }

        controller.oneStepExecution();
        loadData();
    }

    @FXML
    public void setSelectedProgram() {
        if (programStatesView.getSelectionModel().getSelectedIndex() >= 0) {
            selectedProgram = controller.getRepository().getAllPrograms().get(programStatesView.getSelectionModel().getSelectedIndex());
            loadData();
        }
    }
}
