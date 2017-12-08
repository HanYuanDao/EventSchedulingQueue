package entity;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/12/08 10:17:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class GetPDFAddr {
    private String url;
    private String aimUrl;

    private GetPDFAddr() {

    }
    public GetPDFAddr(String url, String aimUrl){
        this.url = url;
        this.aimUrl = aimUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getAimUrl() {
        return aimUrl;
    }

    @Override
    public String toString(){
        return "GetPDFAddr     url:" + url + ",aimUrl:" + aimUrl;
    }
}
