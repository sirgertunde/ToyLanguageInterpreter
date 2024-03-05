package com.example.a7;

import controller.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

public class ControllerMain {
    private Controller controller;
    private ProgramState previous;
    @FXML
    private TableView<Pair<Integer, ValueInterface>> heapTable;

    @FXML
    private TableColumn<Pair<Integer, ValueInterface>, Integer> addressColumn;

    @FXML
    private TableColumn<Pair<Integer, ValueInterface>, String> valueColumn;

    @FXML
    private ListView<String> outputList;

    @FXML
    private ListView<String> fileList;

    @FXML
    private ListView<Integer> programStateList;

    @FXML
    private ListView<String> executionStackList;

    @FXML
    private TableView<Pair<String, ValueInterface>> symbolTable;

    @FXML
    private TableColumn<Pair<String, ValueInterface>, String> symVariableColumn;

    @FXML
    private TableColumn<Pair<String, ValueInterface>, String> symValueColumn;

    @FXML
    private TextField numberOfProgramStates;

    @FXML
    private Button oneStep;

    @FXML
    public void initialize() {
        addressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().first).asObject());
        valueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        symVariableColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().first));
        symValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        oneStep.setOnAction(actionEvent -> {
            if(controller == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            try {
                ProgramState currentProgramState = Objects.requireNonNull(getCurrentProgramState());
                boolean programStateLeft = currentProgramState.getStack().isEmpty();
                if(programStateLeft){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            try {
                previous = getCurrentProgramState();
                controller.oneStepAll();
                populate();
            } catch (IndexOutOfBoundsException | InterruptedException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        });
        programStateList.setOnMouseClicked(mouseEvent -> populate());
    }

    private ProgramState getCurrentProgramState(){
        if (controller.getPrograms().isEmpty())
            return null;
        int currentId = programStateList.getSelectionModel().getSelectedIndex();
        if (currentId == -1)
            return controller.getPrograms().get(0);
        return controller.getPrograms().get(currentId);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        populate();
    }

    private void populate() {
        populateHeap();
        populateProgramStateIdentifiers();
        populateFileTable();
        populateOutput();
        populateSymbolTable();
        populateExecutionStack();
    }

    private void populateHeap() {
        HeapInterface<Integer, ValueInterface> heap;
        if (!controller.getPrograms().isEmpty())
            heap = controller.getPrograms().get(0).getHeap();
        else heap = new MyHeap<>();
        List<Pair<Integer, ValueInterface>> heapTableList = new ArrayList<>();
        for (Map.Entry<Integer, ValueInterface> entry : heap.getContent().entrySet())
            heapTableList.add(new Pair<>(entry.getKey(), entry.getValue()));
        heapTable.setItems(FXCollections.observableList(heapTableList));
        heapTable.refresh();
    }

    private void populateProgramStateIdentifiers() {
        List<ProgramState> programStates = controller.getPrograms();
        List<Integer> idList = programStates.stream().map(ps -> ps.getId()).collect(Collectors.toList());
        programStateList.setItems(FXCollections.observableList(idList));
        numberOfProgramStates.setText(String.valueOf(programStates.size()));
    }

    private void populateFileTable() {
        FileTableInterface fileTable;
        if (!controller.getPrograms().isEmpty())
            fileTable = controller.getPrograms().get(0).getFileTable();
        else
            fileTable = new FileTable();
        List<String> fileTableList = new ArrayList<>();
        for (Map.Entry<StringValue, BufferedReader> entry : fileTable.getContent().entrySet())
            fileTableList.add(String.valueOf(entry.getKey()));
        fileList.setItems(FXCollections.observableArrayList(fileTableList));
        fileList.refresh();
    }

    private void populateOutput() {
        ListInterface<ValueInterface> output;
        if (!controller.getPrograms().isEmpty())
            output = controller.getPrograms().get(0).getList();
        else
            output = previous.getList();
        List<String> stringList = new ArrayList<>();
        for(ValueInterface v :output.getAll())
            stringList.add(v.toString());
        outputList.setItems(FXCollections.observableList(stringList));
        outputList.refresh();
    }

    private void populateSymbolTable() {
        ProgramState state = getCurrentProgramState();
        List<Pair<String, ValueInterface>> symbolTableList = new ArrayList<>();
        if (state != null)
            for (Map.Entry<String, ValueInterface> entry : state.getMap().getAll().entrySet())
                symbolTableList.add(new Pair<>(entry.getKey(), entry.getValue()));
        symbolTable.setItems(FXCollections.observableList(symbolTableList));
        symbolTable.refresh();
    }

    private void populateExecutionStack() {
        ProgramState state = getCurrentProgramState();
        List<String> executionStackListAsString = new ArrayList<>();
        if (state != null)
            for(IStatement s : state.getStack().getAll()){
                executionStackListAsString.add(s.toString());
            }
        executionStackList.setItems(FXCollections.observableList(executionStackListAsString));
        executionStackList.refresh();
    }
}