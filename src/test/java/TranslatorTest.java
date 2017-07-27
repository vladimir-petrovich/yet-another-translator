import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Translator Tester.
 */
public class TranslatorTest {

    @Before
    public void before() throws Exception {
        String fileName = "output.txt";
        System.setProperty("alien.language.parser.output.filename", fileName);
        Files.deleteIfExists(Paths.get(fileName));
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: main(String[] args)
     * Main success scenario
     */
    @Test
    public void testMain() throws Exception {
        ;
        Translator.main(new String[]{"input_example.txt"});
        String fileName = System.getProperty("alien.language.parser.output.filename");
        assertTrue(Files.exists(Paths.get(fileName)));
    }

    /**
     * Method: getInputFileNameFromArgument(String[] args)
     */
    @Test
    public void testGetInputFileNameFromArgument() throws Exception {
        try {
            Method method = Translator.class.getDeclaredMethod("getInputFileNameFromArgument", String[].class);
            method.setAccessible(true);
            String[] args = new String[]{"input_example.txt"};
            method.invoke(Translator.class, (Object) args);
        } catch (Exception e) {
            throw new AssertionError("testGetInputFileNameFromArgument failed.", e);
        }
    }

} 
