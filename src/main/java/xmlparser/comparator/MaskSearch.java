package xmlparser.comparator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskSearch extends AbstractComparator{

    private Pattern pattern;

    @Override
    public void start() {
        String newMask = processingMask(getMask());
        pattern = Pattern.compile(newMask);
    }

    public String processingMask(String mask) {
        StringBuilder newMask = new StringBuilder();
        char[] chars = mask.toCharArray();
        for (char ch: chars) {
            switch (ch) {
                case '.':
                    newMask.append("\\.");
                    break;
                case '?':
                    newMask.append(".");
                    break;
                case '*':
                    newMask.append(".*");
                    break;
                default:
                    newMask.append(ch);
            }
        }
        return newMask.toString();
    }

    @Override
    public boolean compare(String context){
        Matcher m = pattern.matcher(context);
        return m.matches();
    }
}
