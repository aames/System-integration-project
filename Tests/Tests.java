package Tests;


import DataSources.ExcelFetcher;
import DataSources.OldExcelFetcher;
import DataSources.WebFetcher;
import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class Tests {

    public static void main(String[] args) {
        WebFetcher wf = new WebFetcher();
        System.out.println(wf.getContactEmail("CE00386-5"));
        System.out.println(wf.getContact("CE00386-5"));
        System.out.println(wf.getTitle("CE00386-5"));

        System.out.println(wf.getContactEmail("CE00526-5"));
        System.out.println(wf.getContact("CE00526-5"));
        System.out.println(wf.getTitle("CE00526-5"));

        ExcelFetcher ef = new ExcelFetcher();
        ArrayList<String> classListIDs = ef.getClassListIDs("c:/ClassList.xlsx", 9, 0, 1);
        for (String s : classListIDs) {
            System.out.println(s);
        }
        ArrayList<String> classListNames = ef.getClassListNames("c:/ClassList.xlsx", 9, 0, 2);
        for (String s : classListNames) {
            System.out.println(s);
        }
        ArrayList<String> classListStudyType = ef.getClassListStudyType("c:/ClassList.xlsx", 9, 0, 10);
        for (String s : classListStudyType) {
            System.out.println(s);
        }
        ArrayList<String> classListAwardCode = ef.getClassListAward("c:/ClassList.xlsx", 9, 0, 15);
        for (String s : classListAwardCode) {
            System.out.println(s);
        }

        OldExcelFetcher oef = new OldExcelFetcher();
        System.out.println("Second attendance method test");
        System.out.println(oef.getRegisterAttendance("c:/CE01096-5SESI.xls", 10000019, 0, 1, 4, 3));
        System.out.println("Mark getter test");
        System.out.println(oef.getMark("c:/CE01096-5marks.xls", 10000011, 0, 1, 14, 15));

        System.out.println("getStudyType Test");
        System.out.println(ef.getStudyType("c:/ClassList.xlsx", 10000020, 9, 0, 10, 1));
        System.out.println("getAward Test");
        System.out.println(ef.getAward("c:/ClassList.xlsx", 10000006, 9, 0, 15, 1));
        System.out.println("get full name test");
        System.out.println(ef.getName("c:/ClassList.xlsx", 10000012, 9, 0, 2, 1));
        System.out.println(ef.getClassListModuleCode("c:/ClassList.xlsx"));
        
        System.out.println(ef.getMarkListModuleCode("c:/CE01096-5marks.xls"));
    }
}
