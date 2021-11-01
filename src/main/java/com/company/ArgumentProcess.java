package com.company;

import java.io.File;

public class ArgumentProcess {
    private final String inputFileName;
    private String inputKeyFind = null;
    private String inputFind = null;
    private Boolean IsMack = false;

    public String getInputFileName() {
        return inputFileName;
    }

    public String getInputKeyFind() {
        return inputKeyFind;
    }

    public String getInputFind() {
        return inputFind;
    }

    public Boolean getMack() {
        return IsMack;
    }

    public ArgumentProcess(String[] args) {
        try {
            CheckFirstParam(args[0]);
            inputFileName = fileExists(args[1]);
        }
        catch (Exception e){
            throw new ArgumentException("You mush write 2 parameters ");
        }
        if(args.length >= 4){
            inputKeyFind = CheckThirdParam(args[2]);
            inputFind = FindParam(args[3]);
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
        if(f.exists() && f.isFile()){
            return f.getAbsolutePath();
        }else{
            throw new ArgumentException("input file doesn't exists");
        }
    }

    private String CheckThirdParam(String param){
        if(param.equals(XConstant.KEY_MACK) || (param.equals(XConstant.KEY_MACK_REGULAR))){
            return param;
        }
        else{
            throw new ArgumentException("Third param must be -s or -S");
        }
    }

    private String FindParam(String param){
        if((param.charAt(0) == XConstant.APOSTROPHE1)&&(param.charAt(param.length() - 1) == XConstant.APOSTROPHE2)){
            this.IsMack = true;
            param = param.substring(1, param.length()-1);
        }
        return param;
    }

}
