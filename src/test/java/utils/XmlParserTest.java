package utils;

import model.Configuration;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by thales minussi on 28/09/17.
 * https://github.com/tminussi
 */
public class XmlParserTest {

    @Test
    public void parseXmlShouldReturnConfiguration() throws IOException {
        Configuration configuration = XmlParser.parseXml("config/example_config.xml", Configuration.class);
        assertEquals("//", configuration.getPrefix());
        assertEquals("NaTzNTAwSTY40TAwPFC5MzM4.DbcLvA.eU8UmqGM2NBKIGYti3ZpxCQLvDc", configuration.getToken());
        assertTrue(configuration.getListenChannels().contains(284687912687132846L));
        assertTrue(configuration.getListenChannels().contains(549871324898716543L));
        assertTrue(configuration.getListenChannels().contains(135798126574567981L));
    }

}