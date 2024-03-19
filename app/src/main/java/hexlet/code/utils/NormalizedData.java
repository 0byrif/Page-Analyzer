package hexlet.code.utils;

import java.net.URL;

public class NormalizedData {
    public static String getNormalizedUrl(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("Input URL cannot be null");
        }

        StringBuilder normalizedURL = new StringBuilder();
        normalizedURL.append(url.getProtocol())
                .append("://")
                .append(url.getHost())
                .append(url.getPort() == -1 ? "" : ":" + url.getPort());
        return normalizedURL.toString();
    }
}
