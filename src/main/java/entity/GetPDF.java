package entity;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/12/08 10:19:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class GetPDF {
    private String url;
    private String standardCd;
    private String standardNm;
    private String pdfUrl;

    private GetPDF() {

    }
    public GetPDF(String url, String standardCd, String standardNm, String pdfUrl) {
        this.url = url;
        this.standardCd = standardCd;
        this.standardNm = standardNm;
        this.pdfUrl = pdfUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getStandardCd() {
        return standardCd;
    }

    public String getStandardNm() {
        return standardNm;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    @Override
    public String toString() {
        return "GetPDF     url:" + url + ",standardCd:"
                + standardCd + ",standardNm:" + standardNm
                + ",pdfUrl:" + pdfUrl;
    }
}
