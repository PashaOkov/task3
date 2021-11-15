package xmlparser.comparator;

public class EqualsSearch extends AbstractComparator{

    @Override
    public boolean compare(String context){
        return getMask().equals(context);
    }
}
