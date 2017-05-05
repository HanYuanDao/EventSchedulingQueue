package tool;

import java.io.UnsupportedEncodingException;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/26 11:03:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class StringHelper {

    /** 8 位 UCS 转换格式 */
    public static final String UTF_8 = "UTF-8";

    /** 中文超大字符集 */
    public static final String GBK = "GBK";

    public static String toGBK(String source) throws UnsupportedEncodingException {
        return changeCharset(source, GBK);
    }

    /**
     * 字符串编码转换的实现方法
     * @param str  待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }
}
