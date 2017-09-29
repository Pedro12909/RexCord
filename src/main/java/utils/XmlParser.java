package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

/**
 * Created by thales minussi on 28/09/17.
 * https://github.com/tminussi
 */
public class XmlParser {

    private XmlParser() {}

    public static <T>  T parseXml(String path, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        File file = new File(path);
        String xml = inputStreamToString(new FileInputStream(file));
        return objectMapper.readValue(xml, clazz);
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
