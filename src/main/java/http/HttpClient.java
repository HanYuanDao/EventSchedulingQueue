package http;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;
import resource.HttpServerResource;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/03/27 17:19:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class HttpClient {
    private static final Logger logger = Logger.getLogger(HttpClient.class);

    private static WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);

    static {
        webClient.getOptions().setCssEnabled(false);//忽略Css
        webClient.getOptions().setJavaScriptEnabled(true);//忽略JavaScript
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
    }

    /**
     * Send a POST request
     *
     * @param  serverName
     *         The service name of request.It must be configured in the configuration file.
     * @param  param
     *         The params of request.It must be formatted like "name1=value1&name2=value2"
     *
     * @throws  IOException
     *
     * @author JasonHan
     * @creation 2017:02:07 23:05:07
     */
    public static String sendPost(String serverName, String param) throws IOException {
        String ip = HttpServerResource.getIp(serverName);
        String port = StringUtils.isNotBlank(HttpServerResource.getPort(serverName)) ? "" : (":"+HttpServerResource.getPort(serverName));
        String actionName = HttpServerResource.getAction(serverName);
        String url = "https://" + ip + port + "/" + actionName;
        PrintWriter out;
        BufferedReader in;
        StringBuilder result = new StringBuilder();
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        // 设置通用的请求属性
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        out = new PrintWriter(conn.getOutputStream());
        // 发送请求参数
        out.print(param);
        // flush输出流的缓冲
        out.flush();
        // 定义BufferedReader输入流来读取URL的响应
        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        // close the input and output stream
        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }
        return result.toString();
    }

    /**
     * Send a POST request
     *
     * @param  url
     *
     * @throws  IOException
     *
     * @author JasonHan
     * @creation 2017:02:07 23:05:07
     */
    public static String accessUrl(String url) throws IOException {
        try {
            HtmlPage htmlPage = (HtmlPage)webClient.getPage(url);
            return htmlPage.asXml();
        } catch (Exception e) {
            System.out.println("");
            return null;
        }

    }

}
