package DataSources;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author Andrew Edwards
 */
public class ExcelFetcher {

    private Workbook wb;
    private String path;

    public ExcelFetcher() {
    }

    public void getDataTest() {
        try {
            wb = WorkbookFactory.create(new FileInputStream(this.path));
            Sheet ws = wb.getSheetAt(0);
            Row row1 = ws.getRow(1);
            
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
    
    public String getClassListModuleCode(String path){
        String s = "";
        try {
            wb = WorkbookFactory.create(new FileInputStream(path));
            Sheet ws = wb.getSheetAt(0);
            Row row1 = ws.getRow(2);
            s = row1.getCell(5).toString();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return s;
    }
    /*
     * This method could be used to validate if the hidden field were accurate
     * I have left it in the code just to show that it can be done.
     */
    public String getMarkListModuleCode(String path){
        String s = "";
        try {
            wb = WorkbookFactory.create(new FileInputStream(path));
            Sheet ws = wb.getSheetAt(0);
            Row row1 = ws.getRow(2);
            s = row1.getCell(7).toString();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return s;
    }
    public ArrayList<String> getClassListIDs(String path, int r, int s, int c) {
        this.path = path;
        ArrayList<String> rows = new ArrayList();
        try {
            wb = WorkbookFactory.create(new FileInputStream(this.path));
            Sheet ws = wb.getSheetAt(s);
            for (int i = r; i < ws.getLastRowNum() - 1; i++) {
                Row row = ws.getRow(i);
                Cell cell = row.getCell(c);
                rows.add("" + (int) cell.getNumericCellValue());
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public ArrayList<String> getClassListStudyType(String path, int row, int sheet, int cell) {
        ArrayList<String> rows = new ArrayList();
        this.path = path;
        try {
            wb = WorkbookFactory.create(new FileInputStream(this.path));
            Sheet ws = wb.getSheetAt(sheet);
            for (int i = row; i < ws.getLastRowNum() - 1; i++) {
                Row theRow = ws.getRow(i);
                Cell theCell = theRow.getCell(cell);
                rows.add("" + theCell.getStringCellValue());
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public ArrayList<String> getClassListAward(String path, int row, int sheet, int cell) {
        ArrayList<String> rows = new ArrayList();
        this.path = path;
        try {
            wb = WorkbookFactory.create(new FileInputStream(this.path));
            Sheet ws = wb.getSheetAt(sheet);
            for (int i = row; i < ws.getLastRowNum() - 1; i++) {
                Row theRow = ws.getRow(i);
                Cell theCell = theRow.getCell(cell);
                rows.add("" + theCell.getStringCellValue());
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public ArrayList<String> getClassListNames(String path, int r, int sheet, int c) {
        this.path = path;
        ArrayList<String> rows = new ArrayList();
        try {
            wb = WorkbookFactory.create(new FileInputStream(this.path));
            Sheet ws = wb.getSheetAt(sheet);
            for (int i = r; i < ws.getLastRowNum() - 1; i++) {
                Row row = ws.getRow(i);
                Cell cell = row.getCell(c);
                rows.add("" + cell.getStringCellValue());
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        //get REAL names and spaces correctly
        ArrayList<String> realNames = new ArrayList();
        for (String s : rows) {
            String s1 = s.substring(s.indexOf(" ") + 1);
            s1 += s.substring(0, s.indexOf(" "));
            char[] string1 = s1.toCharArray();
            // remove whitespace
            for (int i = 0; i < string1.length; i++) {
                if (string1[i] == ' ' && string1[i + 1] == ' ') {
                    for (int j = i; j < string1.length - 1; j++) {
                        string1[j] = string1[j + 1];
                    }
                }
            }
            String string2 = "";
            for (int i = 0; i < string1.length - 1; i++) {
                string2 = string2.concat(string1[i] + "");
            }
            realNames.add(string2);
        }
        return realNames;
    }

    public String getName(String path, int id, int row, int sheet, int datacell, int idcell) {
        String s = null;
        this.path = path;
        try {
            wb = WorkbookFactory.create(new FileInputStream(this.path));
            Sheet ws = wb.getSheetAt(sheet);
            for (int i = row; i < ws.getLastRowNum() - 1; i++) {
                Row theRow = ws.getRow(i);
                Cell theCell = theRow.getCell(datacell);
                if ((int) theRow.getCell(idcell).getNumericCellValue() == id) {
                    s = theCell.getStringCellValue();
                }
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        String s1 = s.substring(s.indexOf(" ") + 1);
        s1 += s.substring(0, s.indexOf(" "));
        char[] string1 = s1.toCharArray();
        // remove whitespace
        for (int i = 0; i < string1.length; i++) {
            if (string1[i] == ' ' && string1[i + 1] == ' ') {
                for (int j = i; j < string1.length - 1; j++) {
                    string1[j] = string1[j + 1];
                }
            }
        }
        String string2 = "";
        for (int i = 0; i < string1.length - 1; i++) {
            string2 = string2.concat(string1[i] + "");
        }
        return string2;
    }

    public String getAward(String path, int id, int row, int sheet, int datacell, int idcell) {
        this.path = path;
        try {
            wb = WorkbookFactory.create(new FileInputStream(this.path));
            Sheet ws = wb.getSheetAt(sheet);
            for (int i = row; i < ws.getLastRowNum() - 1; i++) {
                Row theRow = ws.getRow(i);
                Cell theCell = theRow.getCell(datacell);
                if ((int) theRow.getCell(idcell).getNumericCellValue() == id) {
                    return "" + theCell.getStringCellValue();
                }
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStudyType(String path, int id, int row, int sheet, int datacell, int idcell) {
        this.path = path;
        try {
            wb = WorkbookFactory.create(new FileInputStream(this.path));
            Sheet ws = wb.getSheetAt(sheet);
            for (int i = row; i < ws.getLastRowNum() - 1; i++) {
                Row theRow = ws.getRow(i);
                Cell theCell = theRow.getCell(datacell);
                if ((int) theRow.getCell(idcell).getNumericCellValue() == id) {
                    return "" + theCell.getStringCellValue();
                }
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}