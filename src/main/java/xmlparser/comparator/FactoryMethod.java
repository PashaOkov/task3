package xmlparser.comparator;

import xmlparser.constants.SearchType;

public class FactoryMethod {
    public static AbstractComparator getComparator(SearchType type){
        switch (type){
            case Full    : return new FullSearch();
            case Equals  : return new EqualsSearch();
            case Mask    : return new MaskSearch();
            case Regular : return new RegularSearch();
            default : throw new IllegalStateException("Unexpected value :" + type);
        }
    }
}
