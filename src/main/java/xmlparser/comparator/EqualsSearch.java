package xmlparser.comparator;

public class EqualsSearch extends AbstractComparator{

    @Override
    protected void start() {
    }

    @Override
    protected boolean compare(String context){
        return getMask().equals(context);
    }
}
