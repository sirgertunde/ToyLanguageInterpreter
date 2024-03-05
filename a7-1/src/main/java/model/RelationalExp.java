package model;

public class RelationalExp implements Expression{
    Expression exp1, exp2;
    int op; // 1 - <, 2 - <=, 3 - ==, 4 - !=, 5 - >, 6 - >=
    public RelationalExp(Expression e1, Expression e2, String operator){
        exp1 = e1;
        exp2 = e2;
        switch (operator){
            case "<": {op = 1; break;}
            case "<=": {op = 2; break;}
            case "==": {op = 3; break;}
            case "!=": {op = 4; break;}
            case ">": {op = 5; break;}
            case ">=": {op = 6; break;}
        }
    }
    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> m, HeapInterface<Integer, ValueInterface> h) throws MyException {
        ValueInterface v1 = exp1.evaluate(m, h);
        ValueInterface v2 = exp2.evaluate(m, h);
        if (v1 instanceof IntValue && v2 instanceof IntValue) {
            int intValue1 = ((IntValue) v1).getValue();
            int intValue2 = ((IntValue) v2).getValue();
            switch (op) {
                case 1:
                    return new BoolValue(intValue1 < intValue2);
                case 2:
                    return new BoolValue(intValue1 <= intValue2);
                case 3:
                    return new BoolValue(intValue1 == intValue2);
                case 4:
                    return new BoolValue(intValue1 != intValue2);
                case 5:
                    return new BoolValue(intValue1 > intValue2);
                case 6:
                    return new BoolValue(intValue1 >= intValue2);
                default:
                    throw new MyException("Invalid relational operator");
            }
        } else {
            throw new MyException("Operands must be of type IntValue for relational expressions");
        }
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface type1, type2;
        type1 = exp1.typeCheck(typeEnv);
        type2 = exp2.typeCheck(typeEnv);
        if(type1.equals(new IntType()) && type2.equals(new IntType()))
            return new BoolType();
        else
            throw new MyException("Operands must be of IntType for relational expression");
    }

    public String toString(){
        if(op == 1)
            return exp1 + " < " + exp2;
        else if(op == 2)
            return exp1 + " <= " + exp2;
        else if(op == 3)
            return exp1 + " == " + exp2;
        else if(op == 4)
            return exp1 + " != " + exp2;
        else if(op == 5)
            return exp1 + " > " + exp2;
        else
            return exp1 + " >= " + exp2;
    }
}
