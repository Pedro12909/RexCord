package model;

import java.util.List;

/**
 * Created by thales minussi on 28/09/17.
 * https://github.com/tminussi
 * Class responsible for representing the XML configuration file
 */
public final class Configuration {

    /**
     * token
     */
    private String token;

    /**
     * prefix
     */
    private String prefix;

    /**
     * listen channels
     */
    private List<Long> listenChannels;

    /**
     * giphy api key
     */
    private String apiGiphyKey;

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     * sets the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix
     * sets the prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return listenChannels
     */
    public List<Long> getListenChannels() {
        return listenChannels;
    }

    /**
     * @param listenChannels
     * sets listenChannels
     */
    public void setListenChannels(List<Long> listenChannels) {
        this.listenChannels = listenChannels;
    }

    /**
     * @return apiGiphyKey
     */
    public String getApiGiphyKey() {
        return apiGiphyKey;
    }

    /**
     * @param apiGiphyKey
     * sets giphyApiKey
     */
    public void setApiGiphyKey(String apiGiphyKey) {
        this.apiGiphyKey = apiGiphyKey;
    }

    /**
     * verifies if the token has already been set
     * @return true if the token has been set, false otherwise
     */
    public boolean isTokenSet() {
        return this.token != null && !this.token.isEmpty();
    }

    /**
     * verifies if the prefix has already been set
     * @return true if the prefix has been set, false otherwise
     */
    public boolean isPrefixSet() {
        return this.prefix != null && !this.prefix.isEmpty();
    }

    /**
     * verifies if giphyApiKey has already been set
     * @return true if giphyApiKey has been set, false otherwise
     */
    public boolean isApiGiphyKeySet() {
        return this.apiGiphyKey != null && !this.apiGiphyKey.isEmpty();
    }

    /**
     * verifies if listenChannels have already been set
     * @return true if listenChannels has been set, false otherwise
     */
    public boolean isListenChannelsSet() {
        return this.listenChannels != null && !this.listenChannels.isEmpty();
    }
}
