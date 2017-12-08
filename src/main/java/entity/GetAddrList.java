package entity;

import access.GetAddr;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/12/08 10:28:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class GetAddrList {
    private String url;

    private GetAddrList() {

    }
    public GetAddrList(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "GetAddrList     url:" + url;
    }
}
