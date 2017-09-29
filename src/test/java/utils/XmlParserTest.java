package utils;

import model.Configuration;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by thales minussi on 28/09/17.
 * https://github.com/tminussi
 */
public class XmlParserTest {

    @Test
    public void parseXmlShouldReturnConfiguration() throws IOException {
        Configuration configuration = XmlParser.parseXml("configuration.xml", Configuration.class);
        assertEquals("123", configuration.getPrefix());
        assertEquals("123", configuration.getToken());
        assertEquals(3, configuration.getBannedCommands().size());
        assertTrue(configuration.getBannedCommands().contains("DEFAULT"));
        assertTrue(configuration.getBannedCommands().contains("SPEAK"));
        assertTrue(configuration.getBannedCommands().contains("MUTE"));
    }

}