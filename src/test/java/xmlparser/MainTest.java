package xmlparser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class MainTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void NoSearchInput() {
        String[] args = {"-f", "test.xml"};
        Main.main(args);
        String expected = "/file-776194140.xml\r\n" +
                "/dir-880176375/file-1073842118.java\r\n" +
                "/dir-880176375/dir-2145307015/file-1498940214.xhtml\r\n" +
                "/dir-880176375/file-123.java\r\n";
        Assertions.assertEquals(expected, outputStreamCaptor.toString());
    }

}