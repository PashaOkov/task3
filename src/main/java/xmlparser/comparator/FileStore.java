package xmlparser.comparator;

import xmlparser.constants.XConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FileStore {
    private boolean isFile;
    private List<String> pathToFile = new ArrayList<>();

    public void setFile(boolean file) {
        isFile = file;
    }

    public String getCurDir() {
        return pathToFile.stream()
                .filter(e -> !equals(""))
                .collect(Collectors.joining());
    }

    public void store(String context){
        if(!isFile){
            pathToFile.add(pathToFile.size() < 1 ? context : context + XConstant.SPLIT_DIR);
        }
        else{
            pathToFile.add("");
            if(compare(context)){
                output(context);
            }
        }
    }

    public void output(String context){
        System.out.println(getCurDir() + context);
    }
    public void closeDir() {
        pathToFile.remove(pathToFile.size()-1);
    }

    public abstract boolean compare(String context);
}
