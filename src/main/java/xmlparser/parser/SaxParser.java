package xmlparser.parser;

import xmlparser.argument.ArgumentException;
import xmlparser.comparator.AbstractComparator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SaxParser {

    public SaxParser(AbstractComparator comparator, String files) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        NodeParser handler = new NodeParser(comparator);

        SAXParser parser = factoryParser(factory);
        File file = new File(files);
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
