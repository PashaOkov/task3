package xmlparser.comparator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularSearch extends AbstractComparator{
    private Pattern pattern;

    @Override
    protected void start() {
        pattern = Pattern.compile(getMask());
    }

    @Override
    protected boolean compare(String context){
        Matcher m = pattern.matcher(context);
        return m.matches();
    }

}
