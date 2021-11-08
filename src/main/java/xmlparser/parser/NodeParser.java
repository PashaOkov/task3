package xmlparser.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import xmlparser.comparator.AbstractComparator;
import xmlparser.constants.XConstant;

import java.util.ArrayList;

public class NodeParser extends DefaultHandler {

    private String currentElement;
    private Boolean IsFile = false;
    private Boolean NodeEnd = false;
    private ArrayList<String> PathToFile = new ArrayList<>();
    private AbstractComparator comparator;

    public NodeParser(AbstractComparator comparator) {
        this.comparator = comparator;
        comparator.start();
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
            PathToFile.remove(PathToFile.size() - 1);
            if(!PathToFile.isEmpty()){
                PathToFile.remove(PathToFile.size() - 1);
            }
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
                PathToFile.add(file);
                if(comparator.compare(file)) {
                    for(String a : PathToFile){
                        System.out.print(a);
                    }
                    System.out.println();
                }
                PathToFile.remove(PathToFile.size() - 1);
            }
            else{
                PathToFile.add(new String(ch, start, length));
                if(NodeEnd) {
                    PathToFile.add(XConstant.SPLIT_DIR);
                }
                NodeEnd = true;
            }
        }

    }

}
