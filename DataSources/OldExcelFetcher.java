package DataSources;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Andrew
 */
public class OldExcelFetcher {

    public OldExcelFetcher() {
    }

    public void getDataTest(String path) {
        try {
            InputStream oldxls = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(oldxls);
            System.out.println("Sheets:" + workbook.getNumberOfSheets());
            Sheet ws = workbook.getSheetAt(0);
            Row row1 = ws.getRow(3);
            System.out.println((int) row1.getCell(1).getNumericCellValue());
            System.out.println(row1.getCell(4).getNumericCellValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRegisterAttendance(String path, ArrayList<String> ids, int sheet, int idcell, int attCell, int datarow) {
        ArrayList<String> rows = new ArrayList();
        try {
            InputStream oldxls = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(oldxls);
            Sheet ws = workbook.getSheetAt(sheet);
            String theIDs[] = (String[]) ids.toArray(new String[ids.size()]);

            for (int i = datarow; i < ws.getLastRowNum(); i++) {
                Row theRow = ws.getRow(i);
                Cell theAttCell = theRow.getCell(attCell);
                if (i < theIDs.length + datarow) {
                    rows.add("" + theAttCell.getNumericCellValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public String getRegisterAttendance(String path, int id, int sheet, int idcell, int attcell, int datarow) {
        try {
            InputStream oldxls = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(oldxls);
            Sheet ws = workbook.getSheetAt(sheet);
            for (int i = datarow; i < ws.getLastRowNum(); i++){
                Row theRow = ws.getRow(i);
                Cell theAttCell = theRow.getCell(attcell);
                if ((int) (theRow.getCell(idcell).getNumericCellValue()) == id){
                    return ""+theAttCell.getNumericCellValue();
                }
            }
          
        } catch (IOException ex) {
            Logger.getLogger(OldExcelFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String getMark(String path, int id, int sheet, int idcell, int markcell, int datarow) {
        try {
            InputStream oldxls = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(oldxls);
            Sheet ws = workbook.getSheetAt(sheet);
            for (int i = datarow; i < ws.getLastRowNum(); i++){
                Row theRow = ws.getRow(i);
                Cell theMarkCell = theRow.getCell(markcell);
                if ((int) (theRow.getCell(idcell).getNumericCellValue()) == id){
                    return ""+(int)theMarkCell.getNumericCellValue();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
