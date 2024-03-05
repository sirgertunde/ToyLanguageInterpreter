package repository;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepoInterface{

    List<ProgramState> prgList;
    String logFilePath;
    public Repository(ProgramState prg, String file){
        logFilePath = file;
        prgList = new ArrayList<>();
        prgList.add(prg);
    }

    @Override
    public List<ProgramState> getPrgList() {
        return prgList;
    }

    @Override
    public void setPrgList(List<ProgramState> programList) {
        prgList = programList;
    }

    @Override
    public void logPrgStateExec(ProgramState prgState) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(prgState.toString());
            logFile.close();
        } catch (IOException exception) {
            throw new MyException("Error writing to the log file: " + exception.getMessage());
        }
    }
}
