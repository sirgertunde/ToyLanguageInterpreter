package model;

public class VarDeclStatement implements IStatement{
    String name;
    TypeInterface type;
    public VarDeclStatement(String varName, TypeInterface varType){
        name = varName;
        type = varType;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MapInterface<String, ValueInterface> m = state.getMap();

        if (!m.isDefined(name)) {
            ValueInterface defaultValue = type.defaultValue();
            m.add(name, defaultValue);
        } else {
            throw new MyException("Variable " + name + " is already declared");
        }
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        typeEnv.add(name, type);
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclStatement(name, type);
    }

    @Override
    public String toString(){
        return type.toString() + "=" + name;
    }
}
