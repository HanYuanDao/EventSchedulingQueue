package access;

import event.MakeExcelEvent;
import handler.LoadDataFromExcelHandler;
import handler.MakeExcelHandler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/25 10:24:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class TestHandler {

    public static void main(String[] myArgs) {
        /*LoadDataFromExcelHandler a = LoadDataFromExcelHandler.getInstance();
        ArrayList<HashMap> result = a.loadValue();

        MakeExcelHandler makeExcelHandler = MakeExcelHandler.getInstance();
        MakeExcelEvent makeExcelEvent = new MakeExcelEvent(null, null, result, null);
        makeExcelHandler.handle(makeExcelEvent);*/

//        int lineNum = 0;
//        for (HashMap step : result) {
//            System.out.printf("%4d~", lineNum++);
//            for (int i = 0; i < 19; i++) {
//                System.out.printf("%22s|", step.get(i));
//            }
//            System.out.printf("\n");
//        }

        /*String a = "第一个市第二个市";
        String b = MakeExcelHandler.cutOutString(a, "市", "市");
        System.out.println(b);*/

        ArrayList<String> a = null;
        for (String step : a) {
            System.out.println(step);
        }
    }
}
