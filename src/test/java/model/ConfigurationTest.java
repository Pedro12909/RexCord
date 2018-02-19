package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by thales on 01/10/17.
 * https://github.com/tminussi
 */
public class ConfigurationTest {

    private Configuration configuration;

    @Before
    public void setup() {
        this.configuration = new Configuration();
    }

    @Test
    public void isTokenSet_ShouldReturn_True() throws Exception {
        this.configuration.setToken("123");
        assertTrue(this.configuration.isTokenSet());
    }

    @Test
    public void isTokenSet_ShouldReturn_False_IfTokenIsEmpty() throws Exception {
        this.configuration.setToken("");
        assertFalse(this.configuration.isTokenSet());
    }

    @Test
    public void isTokenSet_ShouldReturn_False_IfTokenIsNull() throws Exception {
        this.configuration.setToken(null);
        assertFalse(this.configuration.isTokenSet());
    }

    @Test
    public void isPrefixSet_ShouldReturn_True() throws Exception {
        this.configuration.setPrefix("123");
        assertTrue(this.configuration.isPrefixSet());
    }

    @Test
    public void isPrefixSet_ShouldReturn_False_IfPrefixIsEmpty() throws Exception {
        this.configuration.setPrefix("");
        assertFalse(this.configuration.isPrefixSet());
    }

    @Test
    public void isPrefixSet_ShouldReturn_False_IfPrefixIsNull() throws Exception {
        this.configuration.setPrefix(null);
        assertFalse(this.configuration.isPrefixSet());
    }

    @Test
    public void isApiGiphyKeySet_ShouldReturn_True() throws Exception {
        this.configuration.setApiGiphyKey("123");
        assertTrue(this.configuration.isApiGiphyKeySet());
    }

    @Test
    public void isApiGiphyKeySet_ShouldReturn_False_IfApiGiphyKeyIsEmpty() throws Exception {
        this.configuration.setApiGiphyKey("");
        assertFalse(this.configuration.isApiGiphyKeySet());
    }

    @Test
    public void isApiGiphyKeySet_ShouldReturn_False_IfApiGiphyKeyIsNull() throws Exception {
        this.configuration.setApiGiphyKey(null);
        assertFalse(this.configuration.isApiGiphyKeySet());
    }

    @Test
    public void isListenChannelsSet_ShouldReturn_True() throws Exception {
        this.configuration.setListenChannels(Arrays.asList(123L));
        assertTrue(this.configuration.isListenChannelsSet());
    }

    @Test
    public void isListenChannelsSet_ShouldReturn_False_IfListenChannelsIsEmpty() throws Exception {
        this.configuration.setListenChannels(new ArrayList<>());
        assertFalse(this.configuration.isListenChannelsSet());
    }

    @Test
    public void isListenChannelsSet_ShouldReturn_False_IfListenChannelsIsNull() throws Exception {
        this.configuration.setListenChannels(null);
        assertFalse(this.configuration.isListenChannelsSet());
    }

}