package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class NodeParser extends DefaultHandler {

    private String currentElement;
    private Boolean IsFile = false;
    private Boolean NodeEnd = false;
    private String path = "";
    private final ArgumentProcess args;

    public NodeParser(ArgumentProcess args) {
        this.args = args;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;
        if(currentElement.equals(XConstant.INCLUDE_NODE)){
            if (attributes.getQName(0).equals(XConstant.IS_FILE)) {
                IsFile = attributes.getValue(0).equals(XConstant.TRUE);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals(XConstant.INCLUDE_NODE_2)){
            path = path.substring(0, path.length() - 1);
            path = GoUpDir(path);
        }

        currentElement = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String file;

        if(currentElement == null) {
            return;
        }

        if(currentElement.equals(XConstant.ACTIVE_NODE)) {
            if(IsFile) {
                file = new String(ch, start, length);
                path += file;
                if(CheckFile(file)) {
                    System.out.println(path);
                }
                path = GoUpDir(path);
            }
            else{
                path += new String(ch, start, length);
                if(NodeEnd) {
                    path += XConstant.SPLIT_DIR;
                }
                NodeEnd = true;
            }
        }

    }

    private Boolean CheckFile(String file) {
        if(args.getInputKeyFind() == null) {
            return true;
        } else if(args.getInputKeyFind().equals(XConstant.KEY_MACK)) {
            return CheckMack(file);
        } else if(args.getInputKeyFind().equals(XConstant.KEY_MACK_REGULAR)) {
            return CheckRegular(file);
        }
        return false;
    }

    private Boolean CheckMack(String file) {
        if(args.getMack()) {
            return file.contains(args.getInputFind());
        }
        else {
            return file.equals(args.getInputFind());
        }
    }

    private Boolean CheckRegular(String file) {
        return file.matches(args.getInputFind());
    }

    private String GoUpDir(String path){
        int lastPos = path.lastIndexOf(XConstant.SPLIT_DIR);
        return path.substring(0, lastPos +1);
    }
}
