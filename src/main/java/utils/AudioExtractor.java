package utils;

import main.RexCord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * Class is used to extract audio from URLs that do not
 * directly link to an audio file
 */
public final class AudioExtractor {

    /**
     * Contructor
     */
    private AudioExtractor() { }

    /**
     * Method to extract audio file from Youtube
     * @param videoId id of the youtube video
     * @return returns URL linking to audio file and returns null for no audio
     * @throws IOException thrown if connection to API fails
     * @throws InterruptedException thrown if thread is interrupted
     */
    public static String extractYoutube(String videoId)
            throws IOException, InterruptedException {
        final int apiMaxId = 3500000;
        final int maxTimeout = 10;
        final long sleepTimeMs = 250;

        String apiUrl = String.format("http://api.convert2mp3.cc/check.php?"
                        + "v=%s&f=mp3&h=%d",
                        videoId,
                        new Random().nextInt(apiMaxId));

        //Keep pinging the API until API responds with the file being ready
        boolean isFilePending = true;
        String response = "";
        int timeoutCounter = 0;

        while (isFilePending) {
            URLConnection connection = new URL(apiUrl).openConnection();
            connection.setRequestProperty("User-Agent", RexCord.USER_AGENT);
            response = readFromConnection(connection);

            String[] status = response.split("\\|");

            if (status[0].equals("OK")) {
                isFilePending = false;
            } else if (status[0].equals("ERROR")
                    && (status[1].equals("1") || status[1].equals("5"))) {
                //Not a valid video id so return null
                return null;
            } else if (status[0].equals("DOWNLOAD")) {
                Thread.sleep(sleepTimeMs);
            } else {
                //no response...increment counter then timeout
                Thread.sleep(sleepTimeMs);
                if (++timeoutCounter > maxTimeout) {
                    return null;
                }
            }
        }

        String audioLink
                = String.format("http://dl%s.downloader.space/dl.php?id=%s",
                response.split("\\|")[1],
                response.split("\\|")[2]);
        return audioLink;
    }


    /**
     * Helper method to read response from url connections
     * @param connection URLConnection connecting to a server
     * @return response from the server
     * @throws IOException thrown if connection could not be made
     */
    public static String readFromConnection(URLConnection connection)
            throws IOException {
        StringBuilder response = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(
                                            connection.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    /**
     * Method that chooses which extraction method to use
     * @param link link to extract audio from
     * @return returns URL linking to the extracted audio file and
     *         returns null if audio could not be extracted
     * @throws IOException thrown if connection could not be made
     */
    public static String extract(String link) throws IOException {
        URL url = new URL(link);
        if (url.getHost().contains("youtube")) {
            //get the part after the "v=" in www.youtube.com/watch?v=...
            String id = url.getQuery().split("=")[1];
            try {
                return extractYoutube(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
