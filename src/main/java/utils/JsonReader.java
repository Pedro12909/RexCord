package utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * PlaceHolder Text
 */
public class JsonReader {

    /**
     * Prevents class from being instantiated as it is
     * an utility class
     */
    private JsonReader() {

    }

    /**
     * PlaceHolder Description
     * @param rd PlaceHolder Description
     * @return PlaceHolder Description
     * @throws IOException PlaceHolder Description
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * PlaceHolder Description
     * @param url PlaceHolder Description
     * @return PlaceHolder Description
     * @throws IOException PlaceHolder Description
     * @throws JSONException PlaceHolder Description
     */
    public static JSONObject readJsonFromUrl(String url)
            throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
