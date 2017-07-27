package logic;

import parsers.*;

import java.io.IOException;
import java.util.List;


public class ChainOfParsers {

    private static Parser getConfiguredParsersChain() {
        //No successor for this handler because this is the last in chain.
        Parser itemPriceQuestionParser = new ItemPriceQuestionParser();
        //ItemPriceQuestionParser is the successor of NumberQuestionParser.
        NumberQuestionParser numberQuestionParser = new NumberQuestionParser(itemPriceQuestionParser);
        //ItemPriceQuestionParser is the successor of PriceStatementParser.
        Parser priceStatementParser = new PriceStatementParser(numberQuestionParser);
        //PriceStatementParser is the successor of InitWord2RomanDictionaryParser.
        //The first handler in the chain.
        return new InitWord2RomanDictionaryParser(priceStatementParser);
    }

    public static void execute(List<String> inputStringsList) throws IOException {
        //List of input strings to parse as input
        for (String string : inputStringsList) {
            getConfiguredParsersChain().parse(string);
        }
    }

}
