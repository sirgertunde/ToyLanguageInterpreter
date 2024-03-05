package model;

public class ArithExp implements Expression{
    Expression e1, e2;
    int op; // 1 -> +, 2 -> -, 3 -> *, 4 -> /
    public ArithExp(char operation, Expression exp1, Expression exp2){
        e1 = exp1;
        e2 = exp2;
        if(operation == '+')
            op = 1;
        if(operation == '-')
            op = 2;
        if(operation == '*')
            op = 3;
        if(operation == '/')
            op = 4;
    }
    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> m, HeapInterface<Integer, ValueInterface> h) throws MyException {
        ValueInterface v1, v2;
        v1 = e1.evaluate(m, h);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.evaluate(m, h);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op == 1)
                    return new IntValue(n1 + n2);
                if (op == 2)
                    return new IntValue(n1 - n2);
                if (op == 3)
                    return new IntValue(n1 * n2);
                if (op == 4)
                    if (n2 == 0)
                        throw new MyException("Division by 0");
                    else
                        return new IntValue(n1 / n2);
            } else
                throw new MyException("Second operand is not an integer");

        } else
            throw new MyException("First operand is not an integer");
        return null;
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if(type1.equals(new IntType()))
            if(type2.equals(new IntType()))
                return new IntType();
            else
                throw new MyException("Second operand is not an integer");
        else
            throw new MyException("First operand is not an integer");

    }

    @Override
    public String toString() {
        if(op == 1)
            return e1 + "+" + e2;
        else if(op == 2)
            return e1 + "-" + e2;
        else if(op == 3)
            return e1 + "*" + e2;
        else
            return e1 + "/" + e2;
    }

}
