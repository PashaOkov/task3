package xmlparser.comparator;

public abstract class AbstractComparator extends FileStore {
    private String mask = null;

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getMask() {
        return mask;
    }

    public void start() {
    }

    public abstract boolean compare(String context);

}
