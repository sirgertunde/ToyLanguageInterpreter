package model;

import java.util.Map;

public class ForStatement implements IStatement{
    Expression e1, e2, e3;
    IStatement statement;
    public ForStatement(Expression ex1, Expression ex2, Expression ex3, IStatement s){
        e1 = ex1;
        e2 = ex2;
        e3 = ex3;
        statement = s;
    }
    public String toString(){
        return "for(v=" + e1.toString() + ";v<" + e2.toString() + ";v=" + e3.toString() +"){" + statement.toString() + "}";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<IStatement> stack = state.getStack();
        IStatement newStatement = new CompStatement(
                new VarDeclStatement("v", new IntType()),
                new CompStatement(
                        new AssignStatement("v", e1),
                        new WhileStatement(
                                new RelationalExp(new VarExp("v"), e2, "<"),
                                new CompStatement(statement, new AssignStatement("v", e3)))));
        stack.push(newStatement);
        //state.setStack(stack);
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        MapInterface<String, TypeInterface> table = new VarDeclStatement("v", new IntType()).typeCheck(clone(typeEnv));
        TypeInterface vType = table.lookUp("v");
        TypeInterface e1Type = this.e1.typeCheck(table);
        TypeInterface expression2Type = this.e2.typeCheck(table);
        TypeInterface expression3Type = this.e3.typeCheck(table);
        if(e1Type.equals(new IntType())){
            if(expression2Type.equals(new IntType())){
                if(expression3Type.equals(new IntType())){
                    if(vType.equals(new IntType())){
                        return table;
                    } else throw new MyException("v is not an int");
                } else throw new MyException("exp3 is not an int");
            } else throw new MyException("exp2 is not an int");
        } else throw new MyException("exp1 is not an int");
    }

    private MapInterface<String, TypeInterface> clone(MapInterface<String, TypeInterface> typeEnv) {
        MapInterface<String, TypeInterface> clonedMap = new MyMap<>();
        for (Map.Entry<String, TypeInterface> entry : typeEnv.getAll().entrySet())
            clonedMap.add(entry.getKey(), entry.getValue().deepCopy());
        return clonedMap;
    }

    @Override
    public IStatement deepCopy() {
        return new ForStatement(e1, e2, e3, statement.deepCopy());
    }
}
