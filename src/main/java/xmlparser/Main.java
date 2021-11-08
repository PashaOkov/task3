package xmlparser;

import xmlparser.argument.ArgumentException;
import xmlparser.argument.ArgumentProcess;
import xmlparser.comparator.AbstractComparator;
import xmlparser.comparator.FactoryMethod;
import xmlparser.parser.MySaxParser;

public class Main {

    public static void main(String[] args) {

        try {
            ArgumentProcess arguments = new ArgumentProcess(args);

            FactoryMethod factoryMethod = new FactoryMethod();
            AbstractComparator comparator = factoryMethod.getComparator(arguments.getSearchType());
            comparator.SetMask(arguments.getMask());

            new MySaxParser(comparator, arguments.getInputFileName());

        } catch (ArgumentException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
