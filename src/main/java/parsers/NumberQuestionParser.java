package parsers;

import util.RomanToDecimal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberQuestionParser extends Parser {

    private static final Logger LOG = Logger.getLogger(NumberQuestionParser.class.getName());

    private static String REG_EXP = ".*how much is ([a-z ]+) ([?]{1}).*";

    public NumberQuestionParser(Parser successor) {
        this.setSuccessor(successor);
    }

    @Override
    public void parse(String input) throws IOException {
        if (canHandleInput(input)) {
            LOG.fine("ItemPriceQuestionParser is handling the input : " + input);
            Pattern p = Pattern.compile(REG_EXP);
            Matcher m = p.matcher(input);
            int number;
            if (m.find()) {
                number = RomanToDecimal.transform(changeWordsToRomanNumbers(m.group(1)));
                //pish tegj glob glob is 42
                String output = m.group(1) + " is " + number + System.lineSeparator();
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
        //how much is pish tegj glob glob ?
        return Pattern.matches(REG_EXP, input);
    }

}
