package xmlparser.argument;

import xmlparser.constants.SearchType;
import xmlparser.constants.XConstant;

import java.io.File;

public class ArgumentProcess {
    private final String inputFileName;
    private SearchType searchType = SearchType.Full;
    private String mask = null;
    private boolean isMask = false;

    public String getInputFileName() {
        return inputFileName;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public String getMask() {
        return mask;
    }

    public ArgumentProcess(String[] args) {
        try {
            CheckFirstParam(args[0]);
            inputFileName = fileExists(args[1]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new ArgumentException("You must write 2 parameters");
        }
        if(args.length >= 4){
            mask = FindParam(args[3]);
            searchType = CheckThirdParam(args[2]);
        }
    }

    private void CheckFirstParam(String param){
        if (!param.equals(XConstant.KEY_INPUT_FILE)) {
            throw new ArgumentException("First param must be -f");
        }
    }

    private String fileExists(String fileName) {
        String path = System.getProperty("user.dir");
        File f = new File(path + File.separator + fileName);
        if(f.exists() && f.isFile()) {
            return f.getAbsolutePath();
        } else {
            throw new ArgumentException("input file doesn't exists");
        }
    }

    private SearchType CheckThirdParam(String param){
        if (param.equals(XConstant.KEY_MACK_REGULAR)) {
            return SearchType.Regular;
        } else if (isMask) {
                return SearchType.Mask;
        } else if (param.equals(XConstant.KEY_MACK)) {
            return SearchType.Equals;
        }
        else {
            throw new ArgumentException("Third param must be -s or -S");
        }
    }

    private String FindParam(String param){
        if((param.charAt(0) == XConstant.APOSTROPHE1)&&(param.charAt(param.length() - 1) == XConstant.APOSTROPHE2)){
            isMask = true;
            param = param.substring(1, param.length()-1);
        }
        return param;
    }

}
