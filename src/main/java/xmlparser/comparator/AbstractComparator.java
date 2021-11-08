package xmlparser.comparator;

public class AbstractComparator {
    private String Mask = null;

    public void SetMask(String mask) {
        Mask = mask;
    }

    public String getMask() {
        return Mask;
    }

    public void start() {
    }

    public boolean compare(String context){
        return false;
    }

}
