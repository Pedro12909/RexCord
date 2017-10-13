package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;


/**
 * Created by thales minussi on 28/09/17.
 * https://github.com/tminussi
 * Class responsible for parsing a given XML file into a POJO.
 */
public class XmlParser {

    /**
     * Prevents class from being instantiated as it is
     * an utility class
     */
    private XmlParser() {

    }

    /**
     * @param path of the file
     * @param c for the result to be binded to
     * @param <T> Type of c
     * @return parsed XML into a POJO
     * @throws IOException if there's anything wrong upon reading
     * the file (permission, invalid path, etc)
     */
    public static <T>  T parseXml(String path, Class<T> c) throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        File file = new File(path);
        String xml = inputStreamToString(new FileInputStream(file));
        return objectMapper.readValue(xml, c);
    }


    /**
     * @param inputStream the stream of the file
     * @return the inputStream as String
     * @throws IOException if there's anything wrong upon reading
     * the file (permission, invalid path, etc)
     */
    private static String inputStreamToString(FileInputStream inputStream)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br =
                new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
