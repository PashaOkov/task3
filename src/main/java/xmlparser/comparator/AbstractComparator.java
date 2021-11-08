package xmlparser.comparator;

public class AbstractComparator {
    private String Mask = null;

    public void SetMask(String mask) {
        Mask = mask;
    }

    public String getMask() {
        return Mask;
    }

    public boolean startCompare(String context) {
        start();
        return compare(context);
    }

    protected void start() {
    }

    protected boolean compare(String context){
        return false;
    }

}
