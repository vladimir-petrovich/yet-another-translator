package parsers;

import util.RomanToDecimal;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.Item2PriceDictionary.getInstance;

public class PriceStatementParser extends Parser {

    private static Logger LOG = Logger.getLogger(PriceStatementParser.class.getName());
    private static String REG_EXP = ".*([a-z ]+) ([a-zA-Z]+) is ([0-9]+) Credits.*";


    public PriceStatementParser(Parser successor) {
        this.setSuccessor(successor);
    }

    @Override
    public void parse(String input) throws IOException {
        if (canHandleInput(input)) {
            LOG.fine("A PriceStatementParser is handling the input : " + input);
            Pattern p = Pattern.compile(REG_EXP);
            Matcher m = p.matcher(input);
            if (m.find()) {
                int quantityInDecimalNumber = RomanToDecimal.transform(changeWordsToRomanNumbers(m.group(1)));
                getInstance().getItem2Quantity2Price()
                        .put(m.group(2), Integer.valueOf(m.group(3)) / quantityInDecimalNumber);
            }
        } else {
            super.parse(input);
        }
    }

    @Override
    protected boolean canHandleInput(String input) {
        //glob glob Silver is 34 Credits
        return Pattern.matches(REG_EXP, input);
    }


}
