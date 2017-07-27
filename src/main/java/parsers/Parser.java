package parsers;

import model.Word2RomanDictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

public abstract class Parser {

    private static final Logger LOG = Logger.getLogger(Parser.class.getName());


    private Parser successor;

    public void parse(String input) throws IOException {
        if (getSuccessor() != null) {
            getSuccessor().parse(input);
        } else {
            String output = "I have no idea what you are talking about: " + input + System.lineSeparator();;
            String fileName = System.getProperty("alien.language.parser.output.filename");
            Files.write(Paths.get(fileName), output.getBytes(), StandardOpenOption.APPEND);
            LOG.info(output);
        }
    }

    protected boolean canHandleInput(String input) {
        return false;
    }

    Parser getSuccessor() {
        return successor;
    }

    void setSuccessor(Parser successor) {
        this.successor = successor;
    }

    //Util method for all parsers
    protected String changeWordsToRomanNumbers(String inputWords) {
        Word2RomanDictionary word2RomanDictionary = Word2RomanDictionary.getInstance();
        String result = inputWords;
        for (String word : word2RomanDictionary.getWord2Roman().keySet()) {
            result = result.replaceAll(word, word2RomanDictionary.getWord2Roman().get(word));
        }
        return result.replaceAll(" ", "");
    }
}