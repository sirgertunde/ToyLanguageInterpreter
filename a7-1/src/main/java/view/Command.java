package view;

public abstract class Command {
    String key, description;
    public Command(String k, String descr){
        key = k;
        description = descr;
    }
    public abstract void execute();
    public String getKey(){
        return key;
    }
    public String getDescription(){
        return description;
    }
}