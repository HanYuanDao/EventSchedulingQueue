package resource;

import java.io.*;
import java.util.Properties;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/03/27 17:19:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */

public class HttpServerResource {
    private static Properties properties = new Properties();

    static {
        try {
            InputStream in = HttpServerResource.class.getResourceAsStream("../httpServer.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HttpServerResource() {}

    public static String getIp(String serviceName) {
        return properties.getProperty(serviceName + ".ip");
    }

    /**
     * Get the port of server
     * @param serverName
     * @return
     */
    public static String getPort(String serverName) {
        return properties.getProperty(serverName + ".port");
    }

    public static String getAction(String serverName) {
        return properties.getProperty(serverName + ".server");
    }
}
