package handler;

import event.Event;
import entity.ExcelStruct;
import entity.Pty;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import scheduler.SchedulerPriorityBlockingQueue;
import sql.MySQL;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/03/28 16:19:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class MakeExcelHandler extends Handler{

    private MakeExcelHandler() {}

    /**
     * 配合getInstance方法，实现安全的多线程环境下的单实例创建。
     * 类级的内部类，只有被调用的时候才会被加载，从而实现了延迟加载。
     */
    private static class MakeExcelHandlerHolder {
        private static MakeExcelHandler MAKE_EXCEL_HANDLER_HOLDER_INSTANCE = new MakeExcelHandler();
    }

    public static MakeExcelHandler getInstance() {
        return MakeExcelHandlerHolder.MAKE_EXCEL_HANDLER_HOLDER_INSTANCE;
    }

    @Override
    public int handle(Event event) {
        System.out.println("处理MakeExcelHandler");
            // 列名
            String columnNames[] = {
                    "DATA_SRC_URL", "DATA_SRC_ID", "DATA_SRC_ID_TXT", "ARCH_TM", "PTY_ID",
                    "PTY_CD", "PTY_SHORT_NM_CN","PTY_FULL_NM_CN","PTY_SHORT_NM_EN","PTY_FULL_NM_EN",
                    "PTY_BRAND_NM_CN","PTY_BRAND_NM_EN",
                    "PTY_CAT_CD","PTY_CAT_NM_CN","PTY_PTY_ID","PTY_PTY_CD", "PTY_PTY_SHORT_NM_CN",
                    "PTY_PTY_FULL_NM_CN","PTY_PTY_SHORT_NM_EN","PTY_PTY_FULL_NM_EN","PTY_PTY_CAT_CD","PTY_PTY_CAT_NM_CN",
                    "LOC_ID","LOC_CD","LOC_NM_CN","LOC_NM_EN","MEMO",
                    "CAT_CD","CAT_NM_CN","GIS_CD","GIS_NM_CN","CTRY_CD",
                    "CTRY_NM_CN", "ST_CD", "ST_NM", "CTY_CD","CTY_NM_CN",
                    "DIST_CD", "DIST_NM_CN","TOWN_CD","TOWN_NM_CN", "STR_CD",
                    "STR_NM_CN", "ADDR_LINE1","ADDR_LINE2","ZIP_CD", "BUS_HRS", "CONTC_NM1",
                    "CONTC_NM2", "TELEPHONE1", "TELEPHONE2", "MOBILE_PHONE1", "MOBILE_PHONE2",
                    "FAX1", "FAX2", "EMAIL1", "EMAIL2", "WECHAT",
                    "QQ" , "DELIV_OTTR", "GIFT_OTTR", "RTRN_NOTE_OTTR", "ORD_FREQ",
                    "ORD_FREQ_MU_CD" , "ORD_FREQ_MU_NM_CN", "MIN_DELIV_QTY", "MIN_DELIV_QTY_MU_CD", "MIN_DELIV_QTY_MU",
                    "MIN_GIFT_QTY", "MIN_GIFT_QTY_MU_CD", "MIN_GIFT_QTY_MU_NM_CN", "EXPI_RATIO", "FCLTY_CAT_CD",
                    "FCLTY_CAT_NM_CN", "VLD_FROM", "VLD_TO", "STAT_CD", "STAT_NM_CN",
                    "PREF_FLAG", "CRT_TM", "CRT_BY", "CRT_CHL_CD", "UPD_TM",
                    "UPD_BY", "UPD_CHL_CD", "EDIT_FLAG", "RELS_NBR",
                    "INSERT INTO STGTXT.LC_PTY_LOC " +
                    "(DATA_SRC_URL,ARCH_TM,PTY_CD,PTY_SHORT_NM_CN,PTY_FULL_NM_CN," +
                    "PTY_SHORT_NM_EN,PTY_FULL_NM_EN,PTY_BRAND_NM_CN,PTY_BRAND_NM_EN," +
                    "PTY_CAT_CD,PTY_CAT_NM_CN,MEMO,CAT_CD,CAT_NM_CN,ST_NM_CN,CTY_NM_CN,DIST_NM_CN,TOWN_NM_CN," +
                    "STR_NM_CN,ADDR_LINE1,BUS_HRS," +
                    "CRT_TM,CRT_BY,EDIT_FLAG,RELS_NBR)"
            };
            // map中的key
            String keys[] = {
                    "DATA_SRC_URL", "DATA_SRC_ID", "DATA_SRC_ID_TXT", "ARCH_TM", "PTY_ID",
                    "PTY_CD", "PTY_SHORT_NM_CN","PTY_FULL_NM_CN","PTY_SHORT_NM_EN","PTY_FULL_NM_EN",
                    "PTY_BRAND_NM_CN","PTY_BRAND_NM_EN",
                    "PTY_CAT_CD","PTY_CAT_NM_CN","PTY_PTY_ID","PTY_PTY_CD", "PTY_PTY_SHORT_NM_CN",
                    "PTY_PTY_FULL_NM_CN","PTY_PTY_SHORT_NM_EN","PTY_PTY_FULL_NM_EN","PTY_PTY_CAT_CD","PTY_PTY_CAT_NM_CN",
                    "LOC_ID","LOC_CD","LOC_NM_CN","LOC_NM_EN","MEMO",
                    "CAT_CD","CAT_NM_CN","GIS_CD","GIS_NM_CN","CTRY_CD",
                    "CTRY_NM_CN", "ST_CD", "ST_NM", "CTY_CD","CTY_NM_CN",
                    "DIST_CD", "DIST_NM_CN","TOWN_CD","TOWN_NM_CN", "STR_CD",
                    "STR_NM_CN", "ADDR_LINE1","ADDR_LINE2","ZIP_CD", "BUS_HRS", "CONTC_NM1",
                    "CONTC_NM2", "TELEPHONE1", "TELEPHONE2", "MOBILE_PHONE1", "MOBILE_PHONE2",
                    "FAX1", "FAX2", "EMAIL1", "EMAIL2", "WECHAT",
                    "QQ" , "DELIV_OTTR", "GIFT_OTTR", "RTRN_NOTE_OTTR", "ORD_FREQ",
                    "ORD_FREQ_MU_CD" , "ORD_FREQ_MU_NM_CN", "MIN_DELIV_QTY", "MIN_DELIV_QTY_MU_CD", "MIN_DELIV_QTY_MU",
                    "MIN_GIFT_QTY", "MIN_GIFT_QTY_MU_CD", "MIN_GIFT_QTY_MU_NM_CN", "EXPI_RATIO", "FCLTY_CAT_CD",
                    "FCLTY_CAT_NM_CN", "VLD_FROM", "VLD_TO", "STAT_CD", "STAT_NM_CN",
                    "PREF_FLAG", "CRT_TM", "CRT_BY", "CRT_CHL_CD", "UPD_TM",
                    "UPD_BY", "UPD_CHL_CD", "EDIT_FLAG", "RELS_NBR"};

        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        listmap.add(map);

        ExcelStruct result = (ExcelStruct) event.getData();
        int anchor = 1;
        /*for (HashMap step : result.getData()) {
//            System.out.print(String.valueOf((anchor++)) + (StringUtils.isNotBlank(step.get(5).toString())?step.get(5):getStNmCn(step))+"|||||");
            System.out.print(String.valueOf((anchor++)) + " || " + result.getFileName() + " || " + getStNmCn(step) + "|||||");
            System.out.print((StringUtils.isNotBlank(step.get(18).toString())?step.get(18):getCtyNmCn(step)) + "|||||");
            System.out.print((StringUtils.isNotBlank(step.get(7).toString())?step.get(7):getDistNmCn(step)) + "|||||");
            System.out.print((StringUtils.isNotBlank(step.get(8).toString())?step.get(8):getStrNmCn(step)) + "|||||");
            System.out.println((StringUtils.isNotBlank(step.get(10).toString())?step.get(10):getAddr(step)));
        }*/

        String fileName = result.getFileName().split("\\.")[0];
        Pty target = null;
        switch (fileName) {
            case "大润发":
                target = Pty.DaRunFa;
                break;
            case "迪亚天天":
                target = Pty.DiYaTianTian;
                break;
            case "好又多":
                target = Pty.HaoYouDuo;
                break;
            case "华联超市":
                target = Pty.HuaLianChaoShi;
                break;
            case "华润万家":
                target = Pty.HuaRunWanJia;
                break;
            case "家得利":
                target = Pty.JiaDeLi;
                break;
            case "家乐福":
                target = Pty.JiaLeFu;
                break;
            case "快客便利店":
                target = Pty.KuaiKeBianLiDian;
                break;
            case "乐购":
                target = Pty.LeGou;
                break;
            case "联华超市":
                target = Pty.LianHuaChaoShi;
                break;
            case "联家超市":
                target = Pty.LianJiaChaoShi;
                break;
            case "麦德龙":
                target = Pty.MaiDeLong;
                break;
            case "农工商":
                target = Pty.NongGongShang;
                break;
            case "欧尚":
                target = Pty.OuShang;
                break;
            case "全家便利店":
                target = Pty.QuanJiaBianLiDian;
                break;
            case "人人乐":
                target = Pty.RenRenLe;
                break;
            case "如海超市":
                target = Pty.RuHaiChaoShi;
                break;
            case "世纪联华":
                target = Pty.ShiJiLianHua;
                break;
            case "沃尔玛":
                target = Pty.WoErMa;
                break;
            case "伍缘":
                target = Pty.WuYuan;
                break;
            case "易初莲花":
                target = Pty.YiChuLianHua;
                break;
            case "易买得":
                target = Pty.YiMaiDe;
                break;
            case "中百超市":
                target = Pty.ZhongBaiChaoShi;
                break;
            default:
                System.out.println("未识别的excel文件："+fileName);
                break;
        }
        int cursorInt = 0;
        String cursorStr;
        int strLength = 5;
        for (HashMap step : result.getData()) {
            if (!step.get(0).equals("门店名称")) {
                Map<String, Object> mapValue = new HashMap<String, Object>();
                mapValue.put("DATA_SRC_URL", "158.网上爬取的收货点_现通_2017.04.25.xlsx");
                mapValue.put("DATA_SRC_ID", "");
                mapValue.put("DATA_SRC_ID_TXT", "");
                mapValue.put("ARCH_TM", "now()");
                mapValue.put("PTY_ID", "");
                cursorStr = String.valueOf(cursorInt++);
                if (cursorStr.length() < strLength) {
                    int missLength = strLength - cursorStr.length();
                    StringBuffer tailStrBuff = new StringBuffer();
                    while (tailStrBuff.length() < missLength) {
                        tailStrBuff.append("0");
                    }
                    cursorStr = tailStrBuff + cursorStr;
                }
                mapValue.put("PTY_CD", target.getPtyFullEm() + cursorStr);
                mapValue.put("PTY_SHORT_NM_CN", null!=step.get(1)?step.get(1):step.get(0));
                mapValue.put("PTY_FULL_NM_CN", null!=step.get(0)?step.get(0):step.get(1));
//                mapValue.put("PTY_SHORT_NM_EN", "Family Mart");
//                mapValue.put("PTY_FULL_NM_EN", "Family Mart");
//                mapValue.put("PTY_BRAND_NM_CN", "全家");
//                mapValue.put("PTY_BRAND_NM_EN", "FamilyMart");
                mapValue.put("PTY_SHORT_NM_EN", target.getPtyShortEm());
                mapValue.put("PTY_FULL_NM_EN", target.getPtyFullEm());
                mapValue.put("PTY_BRAND_NM_CN", target.getPtyBrandNm());
                mapValue.put("PTY_BRAND_NM_EN", target.getPtyBrandEn());
                mapValue.put("PTY_CAT_CD", "1170.241.300");
                mapValue.put("PTY_CAT_NM_CN", "现代通路");
                mapValue.put("PTY_PTY_ID", "");
                mapValue.put("PTY_PTY_CD", "");
                mapValue.put("PTY_PTY_SHORT_NM_CN", "");
                mapValue.put("PTY_PTY_FULL_NM_CN", "");
                mapValue.put("PTY_PTY_SHORT_NM_EN", "");
                mapValue.put("PTY_PTY_FULL_NM_EN", "");
                mapValue.put("PTY_PTY_CAT_CD", "");
                mapValue.put("PTY_PTY_CAT_NM_CN", "");
                mapValue.put("LOC_ID", "");
                mapValue.put("LOC_CD", "");
                mapValue.put("LOC_NM_CN", "");
                mapValue.put("LOC_NM_EN", "");
                mapValue.put("MEMO", "");
                mapValue.put("CAT_CD", "1220.210.310");
                mapValue.put("CAT_NM_CN", "通路门店的收货地址");
                mapValue.put("GIS_CD", "");
                mapValue.put("GIS_NM_CN", "");
                mapValue.put("CTRY_CD", "");
                mapValue.put("CTRY_NM_CN", "");
                mapValue.put("ST_CD", "");
                mapValue.put("CTY_CD", "");
                mapValue.put("DIST_CD", "");
                mapValue.put("TOWN_CD", "");
                mapValue.put("TOWN_NM_CN", "");
                mapValue.put("STR_CD", "");
                if (step.get(18).toString().equals("北京市")||step.get(18).toString().equals("上海市")
                        ||step.get(18).toString().equals("重庆市")||step.get(18).toString().equals("天津市")) {
                    mapValue.put("ST_NM", getStNmCn(step));
                    mapValue.put("CTY_NM_CN", StringUtils.isNotBlank(step.get(18).toString())?step.get(18):getCtyNmCn(step));
                    mapValue.put("DIST_NM_CN", StringUtils.isNotBlank(step.get(6).toString())?step.get(6):getDistNmCn(step));
                    mapValue.put("STR_NM_CN", StringUtils.isNotBlank(step.get(7).toString())?step.get(7):getDistNmCn(step));
                    mapValue.put("ADDR_LINE1",
                            mapValue.get("ST_NM").toString() + mapValue.get("CTY_NM_CN").toString() +
                                    mapValue.get("DIST_NM_CN").toString() + mapValue.get("STR_NM_CN").toString());
                } else {
                    mapValue.put("ST_NM", getStNmCn(step));
                    mapValue.put("CTY_NM_CN", StringUtils.isNotBlank(step.get(18).toString())?step.get(18):getCtyNmCn(step));
                    mapValue.put("DIST_NM_CN", StringUtils.isNotBlank(step.get(7).toString())?step.get(7):getDistNmCn(step));
                    mapValue.put("STR_NM_CN", StringUtils.isNotBlank(step.get(8).toString())?step.get(8):getStrNmCn(step));
                    mapValue.put("ADDR_LINE1",
                            mapValue.get("CTY_NM_CN").toString() +
                                    mapValue.get("DIST_NM_CN").toString() + mapValue.get("STR_NM_CN").toString());
                }
                mapValue.put("ADDR_LINE2", "");
                mapValue.put("ZIP_CD", "");
                mapValue.put("BUS_HRS", null!=step.get(14)?step.get(14):"");
                mapValue.put("CONTC_NM1", "");
                mapValue.put("CONTC_NM2", "");
                mapValue.put("TELEPHONE1", null!=step.get(17)?step.get(17):"");
                mapValue.put("TELEPHONE2", "");
                mapValue.put("MOBILE_PHONE1", "");
                mapValue.put("MOBILE_PHONE2", "");
                mapValue.put("FAX1", "");
                mapValue.put("FAX2", "");
                mapValue.put("EMAIL1", "");
                mapValue.put("EMAIL2", "");
                mapValue.put("WECHAT", "");
                mapValue.put("QQ", "");
                mapValue.put("DELIV_OTTR", "");
                mapValue.put("GIFT_OTTR", "");
                mapValue.put("RTRN_NOTE_OTTR", "");
                mapValue.put("ORD_FREQ", "");
                mapValue.put("ORD_FREQ_MU_CD", "");
                mapValue.put("ORD_FREQ_MU_NM_CN", "");
                mapValue.put("MIN_DELIV_QTY", "");
                mapValue.put("MIN_DELIV_QTY_MU_CD", "");
                mapValue.put("MIN_DELIV_QTY_MU", "");
                mapValue.put("MIN_GIFT_QTY", "");
                mapValue.put("MIN_GIFT_QTY_MU_CD", "");
                mapValue.put("MIN_GIFT_QTY_MU_NM_CN", "");
                mapValue.put("EXPI_RATIO", "");
                mapValue.put("FCLTY_CAT_CD", "");
                mapValue.put("FCLTY_CAT_NM_CN", "");
                mapValue.put("VLD_FROM", "");
                mapValue.put("VLD_TO", "");
                mapValue.put("STAT_CD", "");
                mapValue.put("STAT_NM_CN", "");
                mapValue.put("PREF_FLAG", "");
                mapValue.put("CRT_TM", "now()");
                mapValue.put("CRT_BY", "hanzhe");
                mapValue.put("CRT_CHL_CD", "");
                mapValue.put("UPD_TM", "");
                mapValue.put("UPD_BY", "");
                mapValue.put("UPD_CHL_CD", "");
                mapValue.put("EDIT_FLAG", "1100.200");
                mapValue.put("RELS_NBR", "1.0.0");

                listmap.add(mapValue);
            }
        }

        ArrayList<Workbook> resultList = createWorkBookList(listmap, keys, columnNames);

        System.out.println("Export excel Start.");
        try {
            if (resultList.size() == 1) {
                String targetFileAllPath = result.getFilePath().replace(result.getFileName(), "")
                        .replace("basic","result");
                File targetFolder = new File(targetFileAllPath);
                if (!targetFolder.exists()) {
                    targetFolder.mkdirs();
                }
                FileOutputStream fout =
                        new FileOutputStream(targetFileAllPath+result.getFileName());
                resultList.get(0).write(fout);
                fout.close();
            } else {
                int mark = 1;
                for (Workbook step : resultList) {
                    String[] fileNameArr = result.getFileName().split("/.");
                    String targetFileAllPath = result.getFilePath().replace(result.getFileName(),"")
                            .replace("basic","result");
                    File targetFolder = new File(targetFileAllPath);
                    if (!targetFolder.exists()) {
                        targetFolder.mkdirs();
                    }
                    FileOutputStream fout =
                            new FileOutputStream(targetFileAllPath+fileNameArr[0]+"-"+mark+"."+fileNameArr[1]);
                    step.write(fout);
                    fout.close();
                    mark++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Export excel end.");

        return 0;
    }

    @Override
    protected int dealEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj) {
        return 0;
    }

    @Override
    protected int createEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj) {
        return 0;
    }

    private String getStNmCn(HashMap source) {
        String result = "";

        /*int[] indexArr = {10, 4, 3};
        for (int step : indexArr) {
            result = cutOutString(source.get(step).toString(), null, "省");
        }*/

        if (StringUtils.isBlank(result)&&(null!=source.get(18))&&(!source.get(18).toString().equals("地区名"))) {
            MySQL mySQL = new MySQL();
            result = mySQL.formatStName(source.get(18).toString());
        }

        if (StringUtils.isNotBlank(result)) {
            if (result.equals("北京")||result.equals("上海")||result.equals("天津")||result.equals("重庆")) {
                return result + "市";
            } else {
                return result + "省";
            }
        } else {
            return result;
        }
    }

    private String getCtyNmCn(HashMap source) {
        return "";
    }

    private String getDistNmCn(HashMap source) {
        String result = "";

        int[] indexArr = {10, 3, 4};
        for (int step : indexArr) {
            result = cutOutString(source.get(step).toString(), "市", "区");
            if (StringUtils.isNotBlank(result)) {
                return result+"区";
            }
        }
        if (StringUtils.isBlank(result)) {
            for (int step : indexArr) {
                result = cutOutString(source.get(step).toString(), "市", "县");
                if (StringUtils.isNotBlank(result)) {
                    return result+"县";
                }
            }
        }
        if (StringUtils.isBlank(result)) {
            for (int step : indexArr) {
                result = cutOutString(source.get(step).toString(), "市", "市");
                if (StringUtils.isNotBlank(result)) {
                    return result+"市";
                }
            }
        }
        if (StringUtils.isBlank(result)) {
            for (int step : indexArr) {
                result = cutOutString(source.get(step).toString(), "州", "市");
                if (StringUtils.isNotBlank(result)) {
                    return result+"市";
                }
            }
        }

        return result;
    }

    private String getStrNmCn(HashMap source) {
        String result = "";

        int[] indexArr = {10, 3, 7, 8};
        for (int step : indexArr) {
            result = cutOutString(source.get(step).toString(), "区", null);
            if (StringUtils.isNotBlank(result)) {
                return result;
            }
        }

        return result;
    }

    private String getAddr(HashMap source) {
        return "";
    }

    public static String cutOutString(String source, String startStr, String endStr) {
        if (null != source) {
            int startIndex = 0;
            int endIndex = source.length();

            if (null != startStr) {
                startIndex = source.indexOf(startStr);
                startIndex = startIndex>=0?(startIndex+1):0;
            }
            if (null != endStr) {
//                endIndex = source.indexOf(endStr);
                endIndex = source.indexOf(endStr, startIndex);
                endIndex = endIndex>=0?(endIndex):startIndex;
            }

            if (startIndex <= endIndex) {
                return source.subSequence(startIndex, endIndex).toString();
            }
        }
        return "";
    }

    public static ArrayList<Workbook> createWorkBookList(List<Map<String, Object>> list, String []keys, String columnNames[]) {
        ArrayList<Workbook> workbookList = new ArrayList<>();
        int listSize = list.size();
        int thresholdValue = 50000;
        int groupSize = list.size()/thresholdValue;
        for (int i = 0; i <= groupSize; i++) {
            List<Map<String, Object>> listNew = new ArrayList<Map<String, Object>>();
            for (int j = 0; j < thresholdValue; j++) {
                if ((j+i*thresholdValue) < listSize) {
                    Map<String, Object> step = list.get(j+(i*thresholdValue));
//                    String line = String.valueOf(j+1);
//                    step.put("formula", "IF(S"+line+"=\"\",\"\",CONCATENATE($AA$1,\" VALUES('\",\"德邦物流报价.xlsx\",\"',\",\"now()\",\",'\",\"DEPPON\",\"','\",\"德邦物流\",\"','\",\"德邦物流股份有限公司\",\"','\",\"DEPPON\",\"','\",\"DEPPON\",\"','\",\"001\",\"','\",\"中国\",\"','\",B"+line+",\"','\",A"+line+",\"','\",D"+line+",\"','\",C"+line+",\"','\",F"+line+",\"','\",E"+line+",\"','\",\"001\",\"','\",\"中国\",\"','\",H"+line+",\"','\",G"+line+",\"','\",J"+line+",\"','\",I"+line+",\"','\",L"+line+",\"','\",K"+line+",\"','\",Z"+line+",\"','\",O"+line+",\"','\",P"+line+",\"','\",\"1145.200.325 \",\"','\",\"公斤\",\"','\",Q"+line+",\"','\",R"+line+",\"','\",\"1145.210.300\",\"','\",\"立方米\",\"','\",S"+line+",\"','\",T"+line+",\"','\",U"+line+",\"','\",V"+line+",\"','\",W"+line+",\"','\",M"+line+",\"','\",N"+line+",\"','\",X"+line+",\"','\",Y"+line+",\"','\",\"\",\"',\",\"now()\",\",'\",\"hanzhe\",\"','\",\"1100.200\",\"','\",\"1.0.0\",\"');\"))");
                    listNew.add(step);
                } else {
                    break;
                }
            }
            Workbook wb = createWorkBook(listNew, keys, columnNames);
            workbookList.add(wb);
        }
        return workbookList;
    }

    public static Workbook createWorkBook(List<Map<String, Object>> list, String []keys, String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("物流报价");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth( i, (357 * 15));
        }

        // 创建第一行
        Row row = sheet.createRow(0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

//        Font f3=wb.createFont();
//        f3.setFontHeightInPoints((short) 10);
//        f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);

        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);

            cell.setCellStyle(cs);
        }

        //设置每行每列的值
        for (int i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow( i);sheet.setForceFormulaRecalculation(true);
            // 在row行上创建一个方格
            for(int j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellStyle(cs2);
                if (j != (keys.length-1)) {
                    cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                } else {
                    cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
//                    cell.setCellFormula(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                }
            }
        }
        return wb;
    }
}
