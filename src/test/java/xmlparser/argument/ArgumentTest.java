package xmlparser.argument;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArgumentTest {

    @Test
    void ArgumentProcessZeroParam(){
        String[] args = {};
        ArgumentException exception = Assertions.assertThrows(ArgumentException.class, () -> new ArgumentProcess(args));
        Assertions.assertEquals("You must write 2 parameters", exception.getMessage());
    }

    @Test
    void ArgumentProcessOneParam(){
        String[] args = {"-f"};
        ArgumentException exception = Assertions.assertThrows(ArgumentException.class, () -> new ArgumentProcess(args));
        Assertions.assertEquals("You must write 2 parameters", exception.getMessage());
    }

    @Test
    void ArgumentProcessFileParam(){
        String[] args = {"-f", "test1.xml"};
        ArgumentException exception = Assertions.assertThrows(ArgumentException.class, () -> new ArgumentProcess(args));
        Assertions.assertEquals("input file doesn't exists", exception.getMessage());
    }

    @Test
    void ArgumentProcessFirstParam(){
        String[] args = {"-a", "test.xml"};
        ArgumentException exception = Assertions.assertThrows(ArgumentException.class, () -> new ArgumentProcess(args));
        Assertions.assertEquals("First param must be -f", exception.getMessage());
    }

    @Test
    void ArgumentProcessThirdParam(){
        String[] args = {"-f", "test.xml", "-p", "file.java"};
        ArgumentException exception = Assertions.assertThrows(ArgumentException.class, () -> new ArgumentProcess(args));
        Assertions.assertEquals("Third param must be -s or -S", exception.getMessage());
    }
}
