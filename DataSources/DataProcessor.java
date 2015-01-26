package DataSources;
import ExceptionHandler.ModuleCodeInvalidException;
import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class DataProcessor {
    String classListFilePath;
    String marksFilePath;
    String attendanceFilePath;
    
    public DataProcessor(String classListFilePath, String marksFilePath, String attendanceFilePath) throws ModuleCodeInvalidException {
        this.attendanceFilePath = attendanceFilePath;
        this.marksFilePath = marksFilePath;
        this.classListFilePath = classListFilePath;
    }
    
    /*
     * Get the list of Students and return as an Arraylist of String[] values
     * student = {name, s (student id), awardcode, studytype, moduleCode, moduleTitle, moduleContact, };
     */
    public ArrayList<String[]> getClassList() throws ModuleCodeInvalidException {
        ArrayList<String[]> classList = new ArrayList();
        ArrayList<String[]> studentsAsStrings = new ArrayList();
        ExcelFetcher excelFetcher = new ExcelFetcher();
        WebFetcher wf = new WebFetcher();
        
        if (checkModuleCode(classListFilePath)){
            ArrayList<String> classListIDs = excelFetcher.getClassListIDs(classListFilePath, 9, 0, 1);
            for(String s:classListIDs){
                String name, awardcode, studytype, moduleCode, moduleTitle, moduleContact;
                name = excelFetcher.getName(classListFilePath, Integer.parseInt(s), 9, 0, 2, 1);
                awardcode = excelFetcher.getAward(classListFilePath, Integer.parseInt(s), 9, 0, 15, 1);
                studytype = excelFetcher.getStudyType(classListFilePath, Integer.parseInt(s), 9, 0, 10, 1);
                
                moduleCode = excelFetcher.getClassListModuleCode(classListFilePath);
                moduleContact = wf.getContact(moduleCode);
                moduleTitle = wf.getTitle(moduleCode);
                // Note how data will be returned
                String[] student = {name, s, awardcode, studytype, moduleCode, moduleTitle, moduleContact };
                studentsAsStrings.add(student);
            }
            return studentsAsStrings;
        }
        else {
            throw new ModuleCodeInvalidException("Module code is invalid, sorry");
        }
    }
    
    /* ATTENTION:
     * As we can't validate the module code, this isn't the most eloquent solution.
     * This could be better if we could use that hidden column.. 
     */
    public String getModuleAttendance(String studentId){
        OldExcelFetcher oef = new OldExcelFetcher();
        String attendanceValue = oef.getRegisterAttendance(attendanceFilePath, Integer.parseInt(studentId), 0, 1, 4, 3);
        return attendanceValue;
    }
    
    /*
     * getModuleMarks by putting student id in
     */
    public String getModuleMark(String studentID){
        OldExcelFetcher oef = new OldExcelFetcher();
        return oef.getMark(marksFilePath, Integer.parseInt(studentID), 0, 1, 14, 15);
    }
    /*
     * Validate the module code is legit, as we haven't been given a criteria I assume it must have a '-'
     */
    private boolean checkModuleCode(String path){
        ExcelFetcher excelFetcher = new ExcelFetcher();
        if(excelFetcher.getClassListModuleCode(path).charAt(excelFetcher.getClassListModuleCode(path).length()-2) == '-')
            return true;
        else
            return false;
    }
}
