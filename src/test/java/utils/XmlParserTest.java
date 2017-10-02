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
        Configuration configuration = XmlParser.parseXml("config/config.xml", Configuration.class);
        assertEquals("123", configuration.getPrefix());
        assertEquals("123", configuration.getToken());
        assertEquals("DEFAULT", configuration.getBannedCommands());
        assertTrue(configuration.getListenChannels().contains(50L));
        assertTrue(configuration.getListenChannels().contains(60L));
        assertTrue(configuration.getListenChannels().contains(70L));
        assertEquals("33a79488-c78c-448b-9c67-5723879e055e", configuration.getApiGiphyKey());
    }

}