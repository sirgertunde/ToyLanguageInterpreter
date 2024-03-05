package view;

import controller.Controller;
import model.MyException;

public class RunExample extends Command{
    private Controller ctr;

    public RunExample(String k, String descr, Controller c) {
        super(k, descr);
        ctr = c;
    }

    @Override
    public void execute() {
        try {
            ctr.allSteps();
        }catch(MyException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
