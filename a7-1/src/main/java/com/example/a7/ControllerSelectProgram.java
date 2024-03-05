package com.example.a7;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;
import repository.RepoInterface;
import repository.Repository;

public class ControllerSelectProgram {

    @FXML
    private Button displayButton;

    @FXML
    private ListView<IStatement> statements;
    private ControllerMain cm;
    public void setProgramController(ControllerMain cm1) {
        this.cm = cm1;
    }
    public static IStatement[] exampleList(){
        IStatement ex1 = new CompStatement(new VarDeclStatement("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExp(new IntValue(2))), new PrintStatement(new VarExp("v"))));
        IStatement ex2 = new CompStatement(new VarDeclStatement("a", new IntType()),
                new CompStatement(new VarDeclStatement("b", new IntType()),
                        new CompStatement(new AssignStatement("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStatement(new AssignStatement("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStatement(new VarExp("b"))))));
        IStatement ex3 = new CompStatement(new VarDeclStatement("a", new BoolType()),
                new CompStatement(new VarDeclStatement("v", new IntType()),
                        new CompStatement(new AssignStatement("a", new ValueExp(new BoolValue(true))),
                                new CompStatement(new IfStatement(new VarExp("a"), new AssignStatement("v", new ValueExp(new IntValue(2))),
                                        new AssignStatement("v", new ValueExp(new IntValue(3)))), new PrintStatement(new VarExp("v"))))));
        IStatement ex4 = new CompStatement(new VarDeclStatement("varf", new StringType()),
                new CompStatement(new AssignStatement("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStatement(new OpenRFile(new VarExp("varf")),
                                new CompStatement(new VarDeclStatement("varc", new IntType()),
                                        new CompStatement(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStatement(new PrintStatement(new VarExp("varc")),
                                                        new CompStatement(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStatement(new PrintStatement(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))))));
        IStatement ex5 = new CompStatement(new VarDeclStatement("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExp(new IntValue(4))),
                        new WhileStatement(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStatement(new PrintStatement(new VarExp("v")),
                                        new AssignStatement("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1))))))));
        IStatement ex6 = new CompStatement(
                new VarDeclStatement("v", new RefType(new IntType())),
                new CompStatement(
                        new NewStatement("v", new ValueExp(new IntValue(20))),
                        new CompStatement(
                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(
                                        new NewStatement("a", new VarExp("v")),
                                        new CompStatement(
                                                new NewStatement("v", new ValueExp(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
        IStatement ex7 = new CompStatement(
                new VarDeclStatement("v", new RefType(new IntType())),
                new CompStatement(
                        new NewStatement("v", new ValueExp(new IntValue(20))),
                        new CompStatement(
                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(
                                        new NewStatement("a", new VarExp("v")),
                                        new CompStatement(
                                                new PrintStatement(new VarExp("v")),
                                                new PrintStatement(new VarExp("a")))))));
        IStatement ex8 = new CompStatement(
                new VarDeclStatement("v", new RefType(new IntType())),
                new CompStatement(
                        new NewStatement("v", new ValueExp(new IntValue(20))),
                        new CompStatement(
                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(
                                        new NewStatement("a", new VarExp("v")),
                                        new CompStatement(
                                                new PrintStatement(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStatement(
                                                        new ArithExp('+',
                                                                new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),
                                                                new ValueExp(new IntValue(5)))))))));
        IStatement ex9 = new CompStatement(
                new VarDeclStatement("v", new RefType(new IntType())),
                new CompStatement(
                        new NewStatement("v", new ValueExp(new IntValue(20))),
                        new CompStatement(
                                new PrintStatement(new ReadHeapExp(new VarExp("v"))),
                                new CompStatement(
                                        new WriteHeapStatement("v", new ValueExp(new IntValue(30))),
                                        new PrintStatement(new ArithExp('+',
                                                new ReadHeapExp(new VarExp("v")),
                                                new ValueExp(new IntValue(5))))))));
        IStatement ex10 = new CompStatement(
                new VarDeclStatement("v", new IntType()),
                new CompStatement(
                        new AssignStatement("v", new ValueExp(new IntValue(10))),
                        new CompStatement(
                                new VarDeclStatement("a", new RefType(new IntType())),
                                new CompStatement(
                                        new NewStatement("a", new ValueExp(new IntValue(22))),
                                        new CompStatement(
                                                new ForkStatement(
                                                        new CompStatement(
                                                                new WriteHeapStatement("a", new ValueExp(new IntValue(30))),
                                                                new CompStatement(
                                                                        new AssignStatement("v", new ValueExp(new IntValue(32))),
                                                                        new CompStatement(
                                                                                new PrintStatement(new VarExp("v")),
                                                                                new PrintStatement(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStatement(
                                                        new PrintStatement(new VarExp("v")),
                                                        new PrintStatement(new ReadHeapExp(new VarExp("a")))))))));
        IStatement ex11 = new CompStatement(
                new VarDeclStatement("a", new RefType(new IntType())),
                new CompStatement(new VarDeclStatement("counter", new IntType()),
                        new WhileStatement(
                                new RelationalExp(new VarExp("counter"), new ValueExp(new IntValue(10)), "<"),
                                new CompStatement(new ForkStatement(new ForkStatement(new NewStatement("a", new VarExp("counter")))),
                                        new AssignStatement("counter", new ArithExp('+', new VarExp("counter"), new ValueExp(new IntValue(1))))))));
        IStatement ex12 = new CompStatement(
                new VarDeclStatement("a", new RefType(new IntType())),
                new CompStatement(
                        new NewStatement("a", new ValueExp(new IntValue(20))),
                        new CompStatement(
                                new ForStatement(
                                        new ValueExp(new IntValue(0)),
                                        new ValueExp(new IntValue(3)),
                                        new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))),
                                        new ForkStatement(new CompStatement(
                                                new PrintStatement(new VarExp("v")),
                                                new AssignStatement("v", new ArithExp('*', new VarExp("v"), new ReadHeapExp(new VarExp("a"))))))),
                                new PrintStatement(new ReadHeapExp(new VarExp("a"))))));
        return new IStatement[]{ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12};
    }
    @FXML
    public void initialize() {
        statements.setItems(FXCollections.observableArrayList(exampleList()));
        displayButton.setOnAction(actionEvent -> {
            int index = statements.getSelectionModel().getSelectedIndex();
            if (index < 0)
                return;
            ProgramState state = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), exampleList()[index], new FileTable(), ProgramState.getNewId());
            RepoInterface repository = new Repository(state, "log.txt");
            Controller controller = new Controller(repository);
            MapInterface<String, TypeInterface> typeEnv = new MyMap<>();
            try{
                exampleList()[index].typeCheck(typeEnv);
                cm.setController(controller);
            } catch (MyException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }
        });
    }
}
