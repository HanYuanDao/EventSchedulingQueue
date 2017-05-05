package javaBean;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/27 15:50:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public enum Pty {

    DaRunFa("RT-MART","RT-MART","RT-MART","大润发","RT-MART"),
    DiYaTianTian("Dia","Dia","Dia","迪亚天天","Dia"),
    HaoYouDuo("TRUST-MART","TRUST-MART","TRUST-MART","好又多","TRUST-MART"),
    HuaLianChaoShi("HUALIAN","HuaLian Supermarket","HuaLian Supermarket","华联超市",""),
    HuaRunWanJia("Vanguard","Vanguard","Vanguard","华润万家","Vanguard"),
    JiaDeLi("JIADELI","JiaDeLi","JiaDeLi","上海家得利超市有限公司",""),
    JiaLeFu("Carrefour","Carrefour","Carrefour","家乐福","Carrefour"),
    KuaiKeBianLiDian("QUIK","KuaiKe","KuaiKe","快客","QUIK"),
    LeGou("TESCO","TESCO","TESCO","乐购","TESCO"),
    LianHuaChaoShi("LIANHUA","LianHua Supermarket","LianHua Supermarket","联华超市",""),
    LianJiaChaoShi("LIANJIA","LianJia Supermarket","LianJia Supermarket","联家超市",""),
    MaiDeLong("METRO","METRO","METRO","麦德龙","METRO"),
    NongGongShang("NGS","NGS","NGS","农工商","NGS"),
    OuShang("Auchan","Auchan","Auchan", "欧尚","Auchan"),
    QuanJiaBianLiDian("FamilyMart","Family Mart","FamilyMart","全家","FamilyMart"),
    RenRenLe("RENRENLE","RenRenLe","RenRenLe","人人乐","REN REN LE"),
    RuHaiChaoShi("RUHAI","RuHai Supermarket","RuHai Supermarket","如海",""),
    ShiJiLianHua("LIANHUA0001","CenturyMart","CenturyMart","世纪联华","CenturyMart"),
    WoErMa("Walmart","Walmart","Walmart","沃尔玛","Walmart"),
    WuYuan("WUYUAN","WuYuan","WuYuan","伍缘",""),
    YiChuLianHua("LOTUS","LOTUS","LOTUS","卜蜂莲花","LOTUS"),
    YiMaiDe("emart","emart","emart","易买得","emart"),
    ZhongBaiChaoShi("ZHONGBAI","ZhongBai Supermarket","ZhongBai Supermarket","中百超市",""),
    ;

    private String ptyCd;
    private String ptyShortEm;
    private String ptyFullEm;
    private String ptyBrandNm;
    private String ptyBrandEn;

    Pty(String s, String s1, String s2, String s3, String s4) {
        ptyCd = s;
        ptyShortEm = s1;
        ptyFullEm = s2;
        ptyBrandNm = s3;
        ptyBrandEn = s4;
    }

    public String getPtyCd() {
        return ptyCd;
    }

    public String getPtyShortEm() {
        return ptyShortEm;
    }

    public String getPtyFullEm() {
        return ptyFullEm;
    }

    public String getPtyBrandNm() {
        return ptyBrandNm;
    }

    public String getPtyBrandEn() {
        return ptyBrandEn;
    }
}
