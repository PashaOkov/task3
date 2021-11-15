package xmlparser.comparator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ComparatorTest {

    @Test
    void FullCompare(){
        AbstractComparator comparator = new FullSearch();
        Assertions.assertTrue(comparator.compare("file-776194140.xml"));
        Assertions.assertTrue(comparator.compare("file-1073842118.java"));
        Assertions.assertTrue(comparator.compare("file-123.java"));
        Assertions.assertTrue(comparator.compare("file-1498940214.xhtml"));

    }

    @Test
    void EqualsCompare() {
        AbstractComparator comparator = new EqualsSearch();
        comparator.setMask("file-776194140.xml");
        Assertions.assertTrue(comparator.compare("file-776194140.xml"));
        Assertions.assertFalse(comparator.compare("file-1073842118.java"));
        Assertions.assertFalse(comparator.compare("file-123.java"));
        Assertions.assertFalse(comparator.compare("file-1498940214.xhtml"));
    }

    @Test
    void MaskCompare() {
        AbstractComparator comparator = new MaskSearch();
        comparator.setMask("*.java");
        comparator.start();
        Assertions.assertTrue(comparator.compare("file-1073842118.java"));
        Assertions.assertTrue(comparator.compare("file-123.java"));
        Assertions.assertFalse(comparator.compare("file-776194140.xml"));
        Assertions.assertFalse(comparator.compare("file-1498940214.xhtml"));
    }

    @Test
    void RegularCompare() {
        AbstractComparator comparator = new RegularSearch();
        comparator.setMask(".*?[a-z]{4}-\\d+.[a-z]+");
        comparator.start();
        Assertions.assertTrue(comparator.compare("file-776194140.xml"));
        Assertions.assertTrue(comparator.compare("file-1073842118.java"));
        Assertions.assertTrue(comparator.compare("file-123.java"));
        Assertions.assertTrue(comparator.compare("file-1498940214.xhtml"));
    }

}
