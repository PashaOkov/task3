package com.company;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class MySaxParser {

    public void parse(ArgumentProcess arguments) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        NodeParser handler = new NodeParser(arguments);

        SAXParser parser = factoryParser(factory);
        File file = new File(arguments.getInputFileName());
        ParserParse(parser, file, handler);
    }

    private SAXParser factoryParser(SAXParserFactory factory){
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            return parser;
        } catch (Exception e) {
            throw new ArgumentException(e.toString());
        }
    }

    private void ParserParse(SAXParser parser, File file , NodeParser handler){
        try {
            parser.parse(file, handler);
        } catch (Exception ex) {
            throw new ArgumentException(ex.toString());
        }
    }
}
