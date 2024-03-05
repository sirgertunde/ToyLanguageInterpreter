package model;

public class LogicExp implements Expression{
    Expression e1, e2;
    int op; // 1 -> and, 2 -> or
    public LogicExp(Expression exp1, Expression exp2, int operator){
        e1 = exp1;
        e2 = exp2;
        op = operator;
    }
    public String toString(){
        if(op == 1)
            return e1 + " and " + e2;
        else
            return e1 + " or " + e2;

    }
    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> m, HeapInterface<Integer, ValueInterface> h) throws MyException {
        ValueInterface v1, v2;
        v1 = e1.evaluate(m, h);
        if(v1.getType().equals(new BoolType())) {
            v2 = e2.evaluate(m, h);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean bool1, bool2;
                bool1 = b1.getValue();
                bool2 = b2.getValue();
                if (op == 1)
                    return new BoolValue(bool1 && bool2);
                if (op == 2)
                    return new BoolValue(bool1 || bool2);
            } else
                throw new MyException("Second operand is not a boolean");
        }
        else
            throw new MyException("First operand is not a boolean");
        return null;
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if(type1.equals(new BoolType()))
            if(type2.equals(new BoolType()))
                return new BoolType();
            else
                throw new MyException("Second operand is not a boolean");
        else
            throw new MyException("First operand is not a boolean");
    }
}
