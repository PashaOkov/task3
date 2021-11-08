package xmlparser.comparator;

public class EqualsSearch extends AbstractComparator{

    @Override
    protected boolean compare(String context){
        return getMask().equals(context);
    }
}
