package xmlparser.comparator;

public class RegularSearch extends AbstractComparator{

    @Override
    protected boolean compare(String context){
        return getMask().matches(context);
    }

}
