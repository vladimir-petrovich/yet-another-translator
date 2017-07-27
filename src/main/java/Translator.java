import logic.ChainOfParsers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class Translator {

    // 2 Develop 2 junit tests main and file

    private static final Logger LOG = Logger.getLogger(Translator.class.getName());

    public static void main(String[] args) throws IOException, URISyntaxException {
        try {
            String inputFileName = getInputFileNameFromArgument(args);
            createOutputFile();
            List<String> inputLines =
                    Files.readAllLines(Paths.get(Translator.class.getResource(inputFileName).toURI()));
            LOG.fine(Arrays.toString(inputLines.toArray()));
            //Translate using chain of parsers
            ChainOfParsers.execute(inputLines);
        } catch (IOException e) {
            LOG.severe("IO issue, please see stack trace.");
            e.printStackTrace();
        }
    }

    private static String getInputFileNameFromArgument(String[] args) throws URISyntaxException {
        String inputFileName = "";
        if (args.length == 0 || args.length > 1) {
            System.err.println("Usage: java Translator input_file_name.txt");
            System.exit(1);
        } else {
            inputFileName = args[0];
        }
        //Check
        if (!Files.exists(Paths.get(Translator.class.getResource(inputFileName).toURI()))) {
            System.err.println("Input file: " + inputFileName + " does not exist.");
            System.exit(1);
        }

        return inputFileName;
    }

    private static void createOutputFile() throws IOException {
        String fileName = "output.txt";
        System.setProperty("alien.language.parser.output.filename", fileName);
        Files.deleteIfExists(Paths.get(fileName));
        Files.createFile(Paths.get(fileName));
    }
}
