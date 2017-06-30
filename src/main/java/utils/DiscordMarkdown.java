/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Transforms messages in Markdown styles using
 * <a href="https://goo.gl/Ggkb1v">Markdown Text 101</a>
 */
public class DiscordMarkdown {
    /**
     * Transforms a certain message in <i>italic</i>
     * @param message Message being transformed
     * @return A String in italic
     */
    public static String italic(String message) {
        return "*" + message + "*";
    }

    /**
     * Transforms a certain message in <b>bold</b>
     * @param message Message being transformed
     * @return A String in bold
     */
    public static String bold(String message) {
        return "**" + message + "**";
    }

    /**
     * Transforms a certain message in <b><i>italic bold</i></b>
     * @param message Message being transformed
     * @return A String in italic bold
     */
    public static String boldItalic(String message) {
        return "***" + message + "***";
    }

    /**
     * Transforms a certain message in <u>underline</u>
     * @param message Message being transformed
     * @return A String underlined
     */
    public static String underline(String message) {
        return "__" + message + "__";
    }

    /**
     * Transforms a certain message in <u><i>underline italic</i></u>
     * @param message Message being transformed
     * @return A String underlined italic
     */
    public static String underlineItalic(String message) {
        return "__*" + message + "*__";
    }

    /**
     * Transforms a certain message in <u><b>underline bold</b></u>
     * @param message Message being transformed
     * @return A String underlined bold
     */
    public static String underlineBold(String message) {
        return "__**" + message + "**__";
    }

    /**
     * Transforms a certain message in <u><b><i>underline bold
     * italic</i></b></u>
     * @param message Message being transformed
     * @return A String underlined bold italic
     */
    public static String underlineBoldItalic(String message) {
        return "__***" + message + "***__";
    }
    /**
     * Hides default constructor
     */
    private DiscordMarkdown() { }
}
