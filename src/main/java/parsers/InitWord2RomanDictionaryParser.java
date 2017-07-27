package parsers;

import model.Word2RomanDictionary;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InitWord2RomanDictionaryParser extends Parser {

    private static Logger LOG = Logger.getLogger(InitWord2RomanDictionaryParser.class.getName());
    private static String REG_EXP = ".*([a-zA-Z0-9]+) is ([IVXLCDM]).*";

    public InitWord2RomanDictionaryParser(Parser successor) {
        this.setSuccessor(successor);
    }

    @Override
    public void parse(String input) throws IOException {
        if (canHandleInput(input)) {
            LOG.fine("A InitWord2RomanDictionaryParser is handling the input : " + input);
            Pattern p = Pattern.compile(REG_EXP);
            Matcher m = p.matcher(input);
            if (m.find()) {
                Word2RomanDictionary.getInstance().getWord2Roman().put(m.group(1), m.group(2));
            }
        } else {
            super.parse(input);
        }
    }

    @Override
    protected boolean canHandleInput(String input) {
        //glob is I
        return Pattern.matches(REG_EXP, input);
    }

}
