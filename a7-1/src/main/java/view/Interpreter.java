package view;

import controller.Controller;
import model.*;
import repository.RepoInterface;
import repository.Repository;

public class Interpreter {
    public static void main(String[] args){
        IStatement ex1 = new CompStatement(new VarDeclStatement("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExp(new IntValue(2))), new PrintStatement(new VarExp("v"))));
        MapInterface<String, TypeInterface> typeEnv1 = new MyMap<>();
        try{
            ex1.typeCheck(typeEnv1);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state1 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex1, new FileTable(), ProgramState.getNewId());
        RepoInterface repo1 = new Repository(state1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        IStatement ex2 = new CompStatement(new VarDeclStatement("a", new IntType()),
                new CompStatement(new VarDeclStatement("b", new IntType()),
                        new CompStatement(new AssignStatement("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStatement(new AssignStatement("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStatement(new VarExp("b"))))));
        MapInterface<String, TypeInterface> typeEnv2 = new MyMap<>();
        try{
            ex2.typeCheck(typeEnv2);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state2 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex2, new FileTable(), ProgramState.getNewId());
        RepoInterface repo2 = new Repository(state2, "log2.txt");
        Controller ctr2 = new Controller(repo2);

        IStatement ex3 = new CompStatement(new VarDeclStatement("a", new BoolType()),
                new CompStatement(new VarDeclStatement("v", new IntType()),
                        new CompStatement(new AssignStatement("a", new ValueExp(new BoolValue(true))),
                                new CompStatement(new IfStatement(new VarExp("a"), new AssignStatement("v", new ValueExp(new IntValue(2))),
                                        new AssignStatement("v", new ValueExp(new IntValue(3)))), new PrintStatement(new VarExp("v"))))));
        MapInterface<String, TypeInterface> typeEnv3 = new MyMap<>();
        try{
            ex3.typeCheck(typeEnv3);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state3 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex3, new FileTable(), ProgramState.getNewId());
        RepoInterface repo3 = new Repository(state3, "log3.txt");
        Controller ctr3 = new Controller(repo3);

        IStatement ex4 = new CompStatement(new VarDeclStatement("varf", new StringType()),
                new CompStatement(new AssignStatement("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStatement(new OpenRFile(new VarExp("varf")),
                                new CompStatement(new VarDeclStatement("varc", new IntType()),
                                        new CompStatement(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStatement(new PrintStatement(new VarExp("varc")),
                                                        new CompStatement(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStatement(new PrintStatement(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))))));
        MapInterface<String, TypeInterface> typeEnv4 = new MyMap<>();
        try{
            ex4.typeCheck(typeEnv4);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state4 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex4, new FileTable(), ProgramState.getNewId());
        RepoInterface repo4 = new Repository(state4, "log4.txt");
        Controller ctr4 = new Controller(repo4);

        IStatement ex5 = new CompStatement(new VarDeclStatement("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExp(new IntValue(4))),
                        new WhileStatement(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStatement(new PrintStatement(new VarExp("v")),
                                        new AssignStatement("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1))))))));
        MapInterface<String, TypeInterface> typeEnv5 = new MyMap<>();
        try{
            ex5.typeCheck(typeEnv5);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state5 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex5, new FileTable(), ProgramState.getNewId());
        RepoInterface repo5 = new Repository(state5, "log5.txt");
        Controller ctr5 = new Controller(repo5);

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
        MapInterface<String, TypeInterface> typeEnv6 = new MyMap<>();
        try{
            ex6.typeCheck(typeEnv6);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state6 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex6, new FileTable(), ProgramState.getNewId());
        RepoInterface repo6 = new Repository(state6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

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
        MapInterface<String, TypeInterface> typeEnv7 = new MyMap<>();
        try{
            ex7.typeCheck(typeEnv7);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state7 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex7, new FileTable(), ProgramState.getNewId());
        RepoInterface repo7 = new Repository(state7, "log7.txt");
        Controller ctr7 = new Controller(repo7);

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
        MapInterface<String, TypeInterface> typeEnv8 = new MyMap<>();
        try{
            ex8.typeCheck(typeEnv8);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state8 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex8, new FileTable(), ProgramState.getNewId());
        RepoInterface repo8 = new Repository(state8, "log8.txt");
        Controller ctr8 = new Controller(repo8);

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
        MapInterface<String, TypeInterface> typeEnv9 = new MyMap<>();
        try{
            ex9.typeCheck(typeEnv9);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state9 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex9, new FileTable(), ProgramState.getNewId());
        RepoInterface repo9 = new Repository(state9, "log9.txt");
        Controller ctr9 = new Controller(repo9);

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
        MapInterface<String, TypeInterface> typeEnv10 = new MyMap<>();
        try{
            ex10.typeCheck(typeEnv10);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state10 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex10, new FileTable(), ProgramState.getNewId());
        RepoInterface repo10 = new Repository(state10, "log10.txt");
        Controller ctr10 = new Controller(repo10);

        IStatement ex11 = new CompStatement(
                new VarDeclStatement("a", new RefType(new IntType())),
                new CompStatement(new VarDeclStatement("counter", new IntType()),
                        new WhileStatement(
                                new RelationalExp(new VarExp("counter"), new ValueExp(new IntValue(10)), "<"),
                                new CompStatement(new ForkStatement(new ForkStatement(new NewStatement("a", new VarExp("counter")))),
                                        new AssignStatement("counter", new ArithExp('+', new VarExp("counter"), new ValueExp(new IntValue(1))))))));
        MapInterface<String, TypeInterface> typeEnv11 = new MyMap<>();
        try{
            ex11.typeCheck(typeEnv11);
        }catch (MyException e){
            System.out.println(e.getMessage());
            return;
        }
        ProgramState state11 = new ProgramState(new MyStack<>(), new MyMap<>(), new MyList<>(), new MyHeap<>(), ex11, new FileTable(), ProgramState.getNewId());
        RepoInterface repo11 = new Repository(state11, "log11.txt");
        Controller ctr11 = new Controller(repo11);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
        menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
        menu.addCommand(new RunExample("10", ex10.toString(), ctr10));
        menu.addCommand(new RunExample("11", ex11.toString(), ctr11));
        menu.show();
    }
}
