package xmlparser.comparator;

public class RegularSearch extends AbstractComparator{

    @Override
    public boolean compare(String context){
        return getMask().matches(context);
    }

}
