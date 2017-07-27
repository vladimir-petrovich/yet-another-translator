package parsers;

import model.Item2PriceDictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ItemPriceQuestionParser extends Parser {

    private static final Logger LOG = Logger.getLogger(ItemPriceQuestionParser.class.getName());

    private static String REG_EXP = ".*how many Credits is ([a-z ]+) ([a-zA-Z]{1,}) ([?]{1}).*";

    @SuppressWarnings("unused")
    public ItemPriceQuestionParser(Parser successor) {
        this.setSuccessor(successor);
    }

    public ItemPriceQuestionParser() {
    }

    @Override
    public void parse(String input) throws IOException {
        if (canHandleInput(input)) {
            LOG.fine("ItemPriceQuestionParser is handling the input : " + input);
            Pattern p = Pattern.compile(REG_EXP);
            Matcher m = p.matcher(input);
            if (m.find()) {
                //glob prok Silver is 68 Credits
                String output = m.group(1) + " of " + m.group(2) +
                        " is " +
                        Item2PriceDictionary.getInstance().getItem2Quantity2Price().get(m.group(2))
                        + System.lineSeparator();
                String fileName = System.getProperty("alien.language.parser.output.filename");
                Files.write(Paths.get(fileName), output.getBytes(), StandardOpenOption.APPEND);
                LOG.info(output);
            }
        } else {
            super.parse(input);
        }
    }

    @Override
    protected boolean canHandleInput(String input) {
        //how many Credits is glob prok Gold ?
        return Pattern.matches(REG_EXP, input);
    }

}
