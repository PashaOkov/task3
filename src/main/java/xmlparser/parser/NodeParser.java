package xmlparser.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlparser.comparator.AbstractComparator;
import xmlparser.constants.XConstant;

public class NodeParser extends DefaultHandler {

    private boolean active;
    private AbstractComparator comparator;

    public NodeParser(AbstractComparator comparator) {
        this.comparator = comparator;
    }

    private boolean CheckElement(Attributes attributes){
        return attributes.getLength() > 0 && attributes.getLocalName(0).equals(XConstant.IS_FILE);
    }
    private boolean CheckFile(Attributes attributes){
        return attributes.getValue(0).equals(XConstant.TRUE);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(CheckElement(attributes)){
            comparator.setFile(CheckFile(attributes));
        }
        active = qName.equals(XConstant.ACTIVE_NODE);

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals(XConstant.ACTIVE_NODE)){
            active = false;
        }
        if(qName.equals(XConstant.INCLUDE_NODE)){
            comparator.closeDir();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if(active){
            String value = new String(ch, start, length);
            comparator.store(value);
        }

    }

    @Override
    public void startDocument() throws SAXException {
        comparator.start();
        super.startDocument();
    }
}
