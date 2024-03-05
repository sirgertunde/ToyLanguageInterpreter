package model;


public class AssignStatement implements IStatement{
    String id;
    Expression exp;
    public AssignStatement(String id1, Expression e){
        id = id1;
        exp = e;
    }
    public String toString(){
        return id+"="+exp.toString();
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<IStatement> stack = state.getStack();
        MapInterface<String, ValueInterface> m = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        if(m.isDefined(id)){
            ValueInterface val = exp.evaluate(m, heap);
            TypeInterface typeId = (m.lookUp(id)).getType();
            if((val.getType()).equals(typeId))
                m.update(id, val);
            else
                throw new MyException("declared type of variable "+id+" and type of the assigned expression do not match");}
        else
            throw new MyException("the used variable "+id+" was not declared before");
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface typeVar = typeEnv.lookUp(id);
        TypeInterface typeExp = exp.typeCheck(typeEnv);
        if(typeVar.equals(typeExp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types");
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(id, exp);
    }
}
